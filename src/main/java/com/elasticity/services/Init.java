package com.elasticity.services;

import java.io.IOException;

import com.elasticity.config.Utility;

public class Init {
	public void Init_Infrastructure(String globalPath) {

		try {
			Utility.scriptShellCall(globalPath + "init.sh", globalPath, "", "");
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}
