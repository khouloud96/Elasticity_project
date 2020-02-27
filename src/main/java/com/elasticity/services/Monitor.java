package com.elasticity.services;

import java.io.IOException;

import com.elasticity.config.Utility;


public class Monitor {

	public void monitoringApp(String globalPath) {

		try {
			Utility.scriptShellCall(globalPath + "monitor.sh", globalPath, "", "");
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}
