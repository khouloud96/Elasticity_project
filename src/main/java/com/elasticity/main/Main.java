package com.elasticity.main;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.elasticity.config.AwsCredentialsParserServiceImpl;
import com.elasticity.config.InstancesAwsCredentials;
import com.elasticity.csm.CSMModel;
import com.elasticity.services.Cooldown;
import com.elasticity.services.CurrentState;
import com.elasticity.services.Deploy;
import com.elasticity.services.Init;
import com.elasticity.services.MasterPublicDNS;
import com.elasticity.services.Monitor;
import com.elasticity.services.MonitorTask;
import com.elasticity.services.Undeploy;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	
	//Get paths to script files and CSM instance
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.filesPathBundle");
	public static String terraformGlobalPath = resourceBundle.getString("terraformGlobalPath");
	public static String jsonCSM = resourceBundle.getString("jsonCSM");
	
	//The knowledge session of Drools
	public static KieSession kSession;
	
	//Colors
	static final String ANSI_PURPLE = "\u001B[35m";
	static final String ANSI_RESET = "\u001B[0m";
	
	public static void main(String[] args) {
    try {
        	long startTime, endTime, totalTime;

			/******************************************************************************
			 * Part 1 : Create the infrastructure/ Deploy the app and the monitor system  *
			 ******************************************************************************/
        	
			// Get instances of the CSM
			ObjectMapper objectMapper = new ObjectMapper();
			CSMModel csm = objectMapper.readValue(new File(jsonCSM), CSMModel.class);
        	
			// Terraform Create infrastructure on AWS
			startTime = System.nanoTime();
			CreateInfrastructure(csm);
			endTime   = System.nanoTime();
        	totalTime = (long) ((endTime - startTime) / Math.pow(10, 9));
        	System.out.println("\n\nTime to create infrastructure ====> " + ANSI_PURPLE + totalTime +" seconds" + ANSI_RESET);        	

			// Deploy exemple of application define in yml file	
        	startTime = System.nanoTime();
			Deploy deploy = new Deploy();
			deploy.deploy_app(terraformGlobalPath);
			endTime   = System.nanoTime();
        	totalTime = (long) ((endTime - startTime) / Math.pow(10, 9));
        	System.out.println("\nTime to deploy an application ====> " + ANSI_PURPLE + totalTime +" seconds" + ANSI_RESET + "\n");

        	// Deploy monitor system
        	startTime = System.nanoTime();
        	Monitor monitor = new Monitor();
			monitor.monitoringApp(terraformGlobalPath);
			endTime   = System.nanoTime();
        	totalTime = (long) ((endTime - startTime) / Math.pow(10, 9));
        	System.out.println("\nTime to deploy monitor system ====> " + ANSI_PURPLE + totalTime +" seconds" + ANSI_RESET + "\n");
           	
			//Links
			System.out.println("Visualizer	==> http://"+MasterPublicDNS.get_DNS(terraformGlobalPath)+":8080/");
			System.out.println("Vote page	==> http://"+MasterPublicDNS.get_DNS(terraformGlobalPath)+":5000/");
			System.out.println("Resultat page	==> http://"+MasterPublicDNS.get_DNS(terraformGlobalPath)+":5001/");
        	System.out.println("Prometheus	==> http://"+MasterPublicDNS.get_DNS(terraformGlobalPath)+":9090/");
        	System.out.println("Grafana		==> http://"+MasterPublicDNS.get_DNS(terraformGlobalPath)+":3000/");
        	
        	/******************************************************************************
			 * Part 2 : Drools Rule Engine/ Monitor the metrics  						  *
			 ******************************************************************************/
        	// load up the knowledge base
        	KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();			
			
			// Get stateful session
			kSession = kContainer.newKieSession("ksession-rules");
			
			//Set the initial State
			CurrentState S = new CurrentState();
			S.setS(S.initialState(csm));
			kSession.insert(S);
			
			//Set the default cooldown
			Cooldown cooldown = new Cooldown();
			cooldown.setC(true);
			cooldown.setCooldownDate(new Date());
			kSession.insert(cooldown);

			//Fire rules until halt
	        new Thread(new Runnable() {
	            public void run() {
	            	kSession.fireUntilHalt();
	            }
	        }).start();
	        
	        //Monitor the metric of each Q_Event in the CSM
			MonitorTask.MonitorMetrics(csm);

	        /******************************************************************************
			 * Part 3 : Undeploy the infrastructure										  *
			 ******************************************************************************/
			boolean x;
			System.out.println("Do you want do undeploy ?");
			Scanner sc = new Scanner(System.in);
			x = sc.nextBoolean();
			if(x == true)
			{
				Undeploy undeploy = new Undeploy();
				undeploy.undeploy_app(terraformGlobalPath);
			}
			sc.close();
			
	    } catch (Throwable t) { t.printStackTrace(); }
	}
	
	/**************************************************************************************************/
	// Function to create the infrastructure with the number of instances define in CSM
	static void CreateInfrastructure(CSMModel csm) throws NumberFormatException, IOException
	{
		//put initial number of instances
		InstancesAwsCredentials.setInitialNbrInstances(Integer.parseInt(CurrentState.instancesOfInitialState(csm)));
		
		//Generate Terraform Credentials File
		AwsCredentialsParserServiceImpl awsCredentialsParser = new AwsCredentialsParserServiceImpl();
		awsCredentialsParser.generateTerraformCredentialsFile(terraformGlobalPath);
		
		//Init task (Create infrastructure - Terraform): Initialization
		Init init = new Init();
		init.Init_Infrastructure(terraformGlobalPath);
	}
}
