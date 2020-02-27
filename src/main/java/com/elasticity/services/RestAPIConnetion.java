package com.elasticity.services;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import com.elasticity.mappers.credentials.ConstraintsSpecification;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAPIConnetion {

	private String user;
	private String password;
	private ObjectMapper objectMapper;
	private String notEncoded;
	private String encodedAuth;
	private HttpURLConnection urlConnection;

	//===================================================================================
	public URLConnection getConnection(String url, String filePath) throws IOException {

		// Connect to the web server endpoint
		URL serverUrl = new URL(url);

		// open connection
		urlConnection = (HttpURLConnection) serverUrl.openConnection();
		urlConnection.setRequestMethod("GET");

		// Include the HTTP Basic Authentication
		String encodedAuth = createHttpHeaders(filePath);
		urlConnection.addRequestProperty("Authorization", encodedAuth);

		return urlConnection;
	}
	
	//===================================================================================
	public String createHttpHeaders(String filePath) throws JsonParseException, JsonMappingException, IOException {

		objectMapper = new ObjectMapper();
		ConstraintsSpecification spec;

		spec = objectMapper.readValue(new File(filePath), ConstraintsSpecification.class);

		user = spec.getCaddyServerKeys().getUsername();
		//System.out.println("Username for Caddy Server: " + user);
		password = spec.getCaddyServerKeys().getPassword();
		//System.out.println("Password for Caddy server: " + password);

		notEncoded = user + ":" + password;
		encodedAuth = "Basic " + Base64.getEncoder().encodeToString(notEncoded.getBytes());

		return encodedAuth;
	}

}
