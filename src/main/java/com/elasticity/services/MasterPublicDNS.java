package com.elasticity.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MasterPublicDNS {

	static public String get_DNS(String globalPath) throws IOException
	{	
		String s;
		String[] command = { "/bin/bash", globalPath+"masterDNS.sh",globalPath};
        Process p = Runtime.getRuntime().exec(command);
        
        BufferedReader DNS = new BufferedReader(new InputStreamReader(p.getInputStream()));
        
        if ((s = DNS.readLine()) != null) 
        {
        	return s;
        }
        
        return null;
	}
	
}
