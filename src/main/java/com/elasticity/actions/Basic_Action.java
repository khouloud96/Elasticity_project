package com.elasticity.actions;

import java.util.ListIterator;
import java.util.concurrent.ScheduledFuture;
import com.elasticity.main.Main;
import com.elasticity.services.ThreadMonitoring;
import com.elasticity.services.Undeploy;

public class Basic_Action 
{
	//===================================================================================================

	public static void Delete()
	{
		//Stop rules engine
		Main.kSession.halt();
				
		//Stop monitoring threads
		ListIterator<ScheduledFuture<?>> it = ThreadMonitoring.threads.listIterator();
		while(it.hasNext())
		{
			ScheduledFuture<?> thread = it.next();
			thread.cancel(true);
		}

		//Undeploy 
		Undeploy undeploy = new Undeploy();
		undeploy.undeploy_app(Main.terraformGlobalPath);
	}
	
	//===================================================================================================
}
