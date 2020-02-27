package com.elasticity.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import com.elasticity.events.Q_Event;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;



public class ThreadMonitoring {	
	
	public static List<ScheduledFuture<?>> threads = new ArrayList<ScheduledFuture<?>>();
	//===========================================================================================================================================
	
	public void processExecution(Q_Event Event) throws JsonParseException, JsonMappingException, IOException, NumberFormatException, InterruptedException 
	{
		
		//Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		//create and execute tasks that run periodically until cancelled
		MonitorTask monitorTask = new MonitorTask(Event);
		
		final ScheduledFuture<?> Handle = executor.scheduleAtFixedRate
		(monitorTask,
		Long.parseLong(Event.getWindow()),
		Long.parseLong(Event.getWindow()),
		TimeUnit.SECONDS);
		
		threads.add(Handle);
			
	}
}
