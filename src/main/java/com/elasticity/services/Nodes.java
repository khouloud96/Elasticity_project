package com.elasticity.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.elasticity.main.Main;

public class Nodes 
{
	public static int get_NbrNodes() throws IOException 
	{
		String[] command = { "/bin/bash", Main.terraformGlobalPath+"nodes.sh",Main.terraformGlobalPath};
        Process p = Runtime.getRuntime().exec(command); 
		int numberNodes = 0;
        
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
 
        // read the output from the command
        while (stdInput.readLine() != null) numberNodes++;
        
        return numberNodes;
	}
}
