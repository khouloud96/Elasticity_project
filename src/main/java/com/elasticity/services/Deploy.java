package com.elasticity.services;

import java.io.IOException;

import com.elasticity.config.Utility;

public class Deploy {
	public void deploy_app(String globalPath) throws IOException {
        
		try {
			Utility.scriptShellCall(globalPath + "deploy-vote-app.sh", globalPath, "", "");
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}
