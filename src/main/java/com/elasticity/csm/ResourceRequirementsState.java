package com.elasticity.csm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceRequirementsState {
	
	//=========================	Attributes	======================================//
	@JsonProperty("Instances")
	private String instances;
	
	@JsonProperty("Type")
	private String type;
	
	@JsonProperty("Provider")
	private String provider;
	
	//========================== Getters & Setters ================================//
	public String getInstances() {
		return instances;
	}
	public void setInstances(String instances) {
		this.instances = instances;
	}
	
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
