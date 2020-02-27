package com.elasticity.mappers.credentials;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CaddyServerKeys {
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;

	public CaddyServerKeys() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
