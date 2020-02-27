package com.elasticity.mappers.credentials;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessCredentials{
	

	@JsonProperty("credentialName")
	private String credentialname;
	
	@JsonProperty("access-key")
	private String access_key;
	
	@JsonProperty("secret-key")
	private String secret_key;
	
	//Getters & Setters
	public String getCredentialname() {
		return credentialname;
	}
	public void setCredentialname(String credentialname) {
		this.credentialname = credentialname;
	}
	public String getAccess_key() {
		return access_key;
	}
	public void setAccess_key(String access_key) {
		this.access_key = access_key;
	}
	public String getSecret_key() {
		return secret_key;
	}
	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}
	
	//Constructor
	public AccessCredentials() {
		super();
	}
	
	
	

}
