package com.elasticity.csm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceRequirements {
	
	//=========================	Attributes	======================================//
	@JsonProperty("instances")
	private String instances;
	
	@JsonProperty("provider")
	private String provider;
	
	@JsonProperty("cpu")
	private String cpu;
	
	@JsonProperty("memory")
	private String memory;
	
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
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
}
