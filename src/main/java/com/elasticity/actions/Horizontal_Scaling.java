package com.elasticity.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.elasticity.config.AwsCredentialsParserServiceImpl;
import com.elasticity.config.Utility;
import com.elasticity.main.Main;
import com.elasticity.services.Nodes;

public class Horizontal_Scaling 
{    
	//=============================================================================================
    
    public static void Docker_Containers(String name, String service, String provider, String adjustementType,int adjust) throws IOException
    {
        //Execute shell commands
		long startTime = System.nanoTime();
        Utility.scriptShellCall(Main.terraformGlobalPath+"scale_Docker.sh", Main.terraformGlobalPath, service, Integer.toString(adjust));
		long endTime   = System.nanoTime();
		long totalTime = (long) ((endTime - startTime) / Math.pow(10, 9));
    	System.out.println("HS Containers ======> " + totalTime);
    }
    
	//===================================================================================================
	
	public static void Terraform_VM(String name, String provider, String adjustementType,int adjust) throws IOException
	{	
		int instances = getInstances(name,adjustementType,adjust);
    	
		//Open the file
    	File file = new File(Main.terraformGlobalPath+"awsCredentials.json");
    	
    	// input the file content to a sting variable
		@SuppressWarnings("deprecation")
		String content = FileUtils.readFileToString(file);
		
		//Search the replicas of the service using regex
        Pattern pattern = Pattern.compile("\"nbWorker\"(\\s)*:(\\s)*\\d+");
        
        Matcher matcher = pattern.matcher(content);
		
        if (matcher.find())
        {
        	String result = matcher.group(0);
            String replacedString = result.replaceAll("([0-9]+)$", Integer.toString(instances));
            String finalResult = matcher.replaceAll(replacedString);
            
            // write the new String with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(Main.terraformGlobalPath+"awsCredentials.json");
            fileOut.write(finalResult.getBytes());
            fileOut.close();
            
			//Generate Terraform Credentials File
			AwsCredentialsParserServiceImpl awsCredentialsParser = new AwsCredentialsParserServiceImpl();
			awsCredentialsParser.generateTerraformCredentialsFile(Main.terraformGlobalPath);
            
			//Execute shell commands
			long startTime = System.nanoTime();
            Utility.scriptShellCall(Main.terraformGlobalPath+"scale.sh", Main.terraformGlobalPath, "", "");
			long endTime   = System.nanoTime();
			long totalTime = (long) ((endTime - startTime) / Math.pow(10, 9));
        	System.out.println("HS VMS ======> " + totalTime);
            
        	//Execute shell commands Load Balancing
        	startTime = System.nanoTime();
            Utility.scriptShellCall(Main.terraformGlobalPath+"LoadBalancing.sh", Main.terraformGlobalPath, "", "");
			endTime   = System.nanoTime();
			totalTime = (long) ((endTime - startTime) / Math.pow(10, 9));
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			System.out.print("\n ======================================================== "+dtf.format(now));
        	System.out.println("Load balancing ======> " + totalTime);
        }
	}
	
	//===================================================================================================
	
	public static int getInstances(String name, String adjustementType, int adjust) throws IOException
	{
		int instances = 0;
		int nbrNodes = Nodes.get_NbrNodes() - 1;
		System.out.println(Nodes.get_NbrNodes());
		switch(adjustementType)
		{
		case "exact-capacity" : instances = adjust; break;
		case "change-in-capacity" : 
			if(name == "scale-in") 
				instances =  nbrNodes - adjust;
			else instances =  nbrNodes + adjust;
			break;
		case "percent-change-in-capacity" : 
			if(name == "scale-in") 
				instances =  nbrNodes - (adjust*nbrNodes)/100;
			else instances =  nbrNodes + (adjust*nbrNodes)/100;
			break;
		default: break;
		}
		
		return instances;
	}
	
	//===================================================================================================

}
