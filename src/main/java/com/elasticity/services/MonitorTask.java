	package com.elasticity.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.kie.api.runtime.rule.FactHandle;
import com.elasticity.csm.CSMModel;
import com.elasticity.csm.Transition;
import com.elasticity.events.Q_Event;
import com.elasticity.main.Main;
import com.elasticity.responseNodeModel.NodeResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.elasticity.responseNodeModel.Result;


public class MonitorTask implements  Runnable{
	
	//============================================================================================

	// Color output values
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_RED = "\u001B[31m";

	public static FactHandle handle = null;
	public static boolean firstTime = true;
	

	// Calculate number of metric values
	private Q_Event Event;
	
	//======================================================================================================================
	public MonitorTask(Q_Event Event)
	{
		this.Event = Event;
	}
	//======================================================================================================================
	/********************************************************
	 *  	Get Data From Server							*
	 * ******************************************************/
	public void getDataFromServer() throws IOException 
	{	
		// Read response from web server, using HTTP Basic Authentication
		RestAPIConnetion connection = new RestAPIConnetion();
		URLConnection conn = connection.getConnection(Event.getQueryMetric(), Main.terraformGlobalPath+"awsCredentials.json");
		
		// Read JsonResponse using httpResponseReader
		BufferedReader httpResponseReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuffer response = new StringBuffer();
		String lineRead;
		while ((lineRead = httpResponseReader.readLine()) != null)
		{
			response.append(lineRead);
		}
		httpResponseReader.close();
		
		
		// Read JsonResponse using ObjectMapper
		MapJsonToJavaObject jsonResponse = (MapJsonToJavaObject) new MapJsonToJavaObject();
		String responseString = response.toString();
		NodeResponse nodeResponse = (NodeResponse) jsonResponse.parseDataFromJson(responseString,new TypeReference<NodeResponse>() {});
		
		
		//Choix de la stratégie
		// Cela signifie qu'il faut considerer toutes les instances
		if(Event.getGroup().equals("true"))
		{
			//initialisation de la valeur de la métrique
			float metricValue = 0;
			int nodesNumber = 0;
				
			//Get Metric Values
			for (Result temp : nodeResponse.getData().getResult()) 
			{
				//If it's not the master node
				if(!temp.getMetric().getNode_name().equals(MasterPrivateDNS.get_DNS().split("\\.")[0]))
				{
					metricValue += Float.parseFloat((temp.getValue().get(1)).toString());
					nodesNumber++;
				}
			}
				
			metricValue /= nodesNumber;
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			System.out.print("\n\n\n"+dtf.format(now)+"\tGetting "+Event.getMetric()+" of "+ ANSI_PURPLE + Event.getTransition().getIdentifier()+" Q-Event "+ANSI_GREEN + metricValue + " %" + ANSI_RESET);
			System.out.println(" ==> " + ANSI_RED + "Fire the "+Event.getTransition().getIdentifier()+" Q-Event"+ ANSI_RESET);

			Event.setValueMetric(Float.toString(metricValue));
			Event.setCaptureDate(new Date());
			handle = Main.kSession.insert(Event);
			Main.kSession.update(handle, Event);
				
		}else // Cela signifie qu'il suffit d'avoir une seule instance qui satisfait la condition
		{
			System.out.println("\n\n");
			
			//Get Metric Values
			for (Result temp : nodeResponse.getData().getResult()) 
			{
				// If it's not the master node
				if(!temp.getMetric().getNode_name().equals(MasterPrivateDNS.get_DNS().split("\\.")[0]))
				{
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
					LocalDateTime now = LocalDateTime.now();  
					System.out.print(dtf.format(now)+"\tGetting "+Event.getMetric()+" of "+ ANSI_PURPLE + Event.getTransition().getIdentifier()+" Q-Event "+temp.getMetric().getNode_name()+" : "+ANSI_GREEN + Float.parseFloat((temp.getValue().get(1)).toString()) + " %" + ANSI_RESET);
					System.out.println(" ==> " + ANSI_RED + "Fire the "+Event.getTransition().getIdentifier()+" Q-Event"+ ANSI_RESET);
					
					Event.setValueMetric(temp.getValue().get(1).toString());
					Event.setCaptureDate(new Date());
					
					handle = Main.kSession.insert(Event);
					Main.kSession.update(handle, Event);
					
					try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}
						
		if (httpResponseReader != null) httpResponseReader.close();
	}
	//======================================================================================================================
	public void run() 
	{ 
		try 
		{ 
			getDataFromServer(); 
		} catch (IOException e) { e.printStackTrace(); }
	}
	//======================================================================================================================
	public static String getQuery(String metric, String type, String service)
	{
		String query = ":9090/api/v1/query?query=";
		String CPU_Usage_VM = "(1-avg(irate(node_cpu_seconds_total{mode=\"idle\"}[ s])*on(instance)group_left(node_id,node_name)node_meta)by(node_id,node_name))*100";
		String Memory_Usage_VM = "(1-((avg_over_time(node_memory_MemFree_bytes[ s])%2Bavg_over_time(node_memory_Cached_bytes[ s])%2Bavg_over_time(node_memory_Buffers_bytes[ s]))/avg_over_time(node_memory_MemTotal_bytes[ s])))*100*on(instance)group_left(node_name)node_meta";
		String CPU_Usage_Container = "(avg(irate(container_cpu_usage_seconds_total{container_label_com_docker_swarm_service_name=\"+service+\"}[ s])*on(container_label_com_docker_swarm_node_id)group_left(node_name)node_meta)by(node_name))*100";
		String Memory_Usage_Container = "(avg(irate(container_cpu_usage_seconds_total{container_label_com_docker_swarm_service_name=\"srv\"}[ s])*on(container_label_com_docker_swarm_node_id)group_left(node_name)node_meta)by(node_name))*100";

		
		//Choose the query
		if(type.equals("Compute"))
		{
			switch(metric)
			{
				case "cpuusage" :  query += CPU_Usage_VM; break;
				case "memoryusage" :  query += Memory_Usage_VM; break;
				default: System.out.println("No match");
			}
		}else
		{
			switch(metric)
			{
				case "cpuusage" :  query += CPU_Usage_Container; break;
				case "memoryusage" :  query += Memory_Usage_Container; break;
				default: System.out.println("No match");
			}
		}

		
		return query;
	}
	//======================================================================================================================
	public static void MonitorMetrics(CSMModel csm) throws IOException, NumberFormatException, InterruptedException
	{
		String jsonResponseUrl;
	    ListIterator<Transition> it = csm.getCsm().getTransitions().listIterator();
	    
	    //Foreach transition
		while(it.hasNext())
		{
			//Get the transition
			Transition T = it.next();
			
			//Get Master DNS
			jsonResponseUrl = "http://" + MasterPublicDNS.get_DNS(Main.terraformGlobalPath);
			
			//if the event of the transition is Q-Event
			if(T.getEvent().getType().equals("Q-Event"))
			{
				//Get the query of the metric
				String query = MonitorTask.getQuery(T.getEvent().getProperties().getMetric(),csm.getCsm().getCloudResources().getRessource().getType(),csm.getCsm().getCloudResources().getRessource().getName());
				
				//Set the value of the window
		        Pattern pattern = Pattern.compile(" ");
		        Matcher matcher = pattern.matcher(query);
		        while (matcher.find())
		        {
		        	String result = matcher.group(0);
		            String replacedString = result.replace(" ", T.getEvent().getProperties().getWindow());
		            query = matcher.replaceAll(replacedString);
		        }
				
		        //URL JSON Response
				jsonResponseUrl += query;

				//Create the Q_Event
				Q_Event event = new Q_Event();
				event.setAttributes(T);
				event.setQueryMetric(jsonResponseUrl);
				event.setInstance(csm.getCsm().getCloudResources().getRessource().getName());
				
				//Monitor the metric
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();  
				System.out.print("\n"+dtf.format(now));
				System.out.println("\tMonitoring "+event.getMetric()+" of "+T.getIdentifier()+" Q-Event ");//+"\nURL ==> "+jsonResponseUrl+"\n");

				ThreadMonitoring thread = new ThreadMonitoring();
				thread.processExecution(event);
			}
		}
		
	}
	//======================================================================================================================
}
