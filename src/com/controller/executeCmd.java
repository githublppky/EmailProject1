package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class executeCmd {
	public static String executeCmd() throws IOException { 
		String command="wmic csproduct get UUID";
        Runtime runtime = Runtime.getRuntime(); 
        Process process = runtime.exec("cmd /c " + command); 
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8")); 
        String line = null; 
        StringBuilder build = new StringBuilder(); 
        while ((line = br.readLine()) != null) { 
         build.append(line); 
        } 
        return build.toString().replaceAll("UUID", "").replaceAll(" ", ""); 
   }
}
