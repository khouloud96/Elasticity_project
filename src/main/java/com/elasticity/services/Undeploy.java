package com.elasticity.services;

import java.io.IOException;

import com.elasticity.config.Utility;

public class Undeploy {
	public void undeploy_app(String globalPath) {

		try {
			Utility.scriptShellCall(globalPath + "undeploy-vote-app.sh", globalPath, "", "");
		} catch (IOException e) {
			e.getStackTrace();
		}

	}
}
