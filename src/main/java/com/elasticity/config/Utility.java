package com.elasticity.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
 
public class Utility {
 
    // file path that contains application description in json
    String DSM_File_Path = null;
    static FileReader file;
 
    // Variable contain file content
    static String content;
 
    // Process to execute Script Shell
    static Process p;
 
    // Variable contain shell command output
    static String s;
    
	private static ResourceBundle resourceBundle;
	
	
	public static String getFilePath(String key){
		resourceBundle = ResourceBundle.getBundle("resource.filesPathBundle");
		return  resourceBundle.getString(key);	
	}
    
    public static boolean scriptShellCall(String DSM_File_Path, String arg1, String arg2, String arg3) throws IOException {
    	 
        Boolean error = false;
        System.out.println(DSM_File_Path);
        System.out.println(arg1);
        System.out.println(arg2);
        String[] command = { "/bin/bash", DSM_File_Path ,arg1,arg2,arg3};
        p = Runtime.getRuntime().exec(command);
 
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
 
        // read the output from the command
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s+"\n");
        }
 
        // read any errors from the attempted command
        if ((s = stdError.readLine()) != null) {
            System.out.println(s+"\n");
            error = true;
        }
        return error;
         
    }
}