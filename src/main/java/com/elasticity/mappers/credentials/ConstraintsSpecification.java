package com.elasticity.mappers.credentials;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstraintsSpecification {

	@JsonProperty("accessCredentials")
	private AccessCredentials accessCredentials;
	
	@JsonProperty("CaddyServerKeys")
	private CaddyServerKeys caddyServerKeys;
	
	@JsonProperty("instancesConstraints")
	private InstancesConstraints instancesConstraints;

	public ConstraintsSpecification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccessCredentials getAccessCredentials() {
		return accessCredentials;
	}

	public void setAccessCredentials(AccessCredentials accessCredentials) {
		this.accessCredentials = accessCredentials;
	}

	public CaddyServerKeys getCaddyServerKeys() {
		return caddyServerKeys;
	}

	public void setCaddyServerKeys(CaddyServerKeys caddyServerKeys) {
		this.caddyServerKeys = caddyServerKeys;
	}

	public InstancesConstraints getInstancesConstraints() {
		return instancesConstraints;
	}

	public void setInstancesConstraints(InstancesConstraints instancesConstraints) {
		this.instancesConstraints = instancesConstraints;
	}

	
}
