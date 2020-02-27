package com.elasticity.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.elasticity.main.Main;

public class InstancesAwsCredentials {
	
	public static void setInitialNbrInstances(int instances) throws IOException
	{    	
		//Open the file
    	File file = new File(Main.terraformGlobalPath+"awsCredentials.json");
    	
    	// put the file content on a sting variable
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
        }
	}
}
