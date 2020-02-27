package com.elasticity.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.elasticity.main.Main;

public class MasterPrivateDNS {

	static public String get_DNS() throws IOException
	{	
		String s;
		String[] command = { "/bin/bash", Main.terraformGlobalPath+"masterPrivateDNS.sh",Main.terraformGlobalPath};
        Process p = Runtime.getRuntime().exec(command);
        
        BufferedReader DNS = new BufferedReader(new InputStreamReader(p.getInputStream()));
        
        if ((s = DNS.readLine()) != null) 
        {
        	return s;
        }
        
        return null;
	}
}
