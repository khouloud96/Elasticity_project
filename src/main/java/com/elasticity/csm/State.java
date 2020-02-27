package com.elasticity.csm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class State {
	//=========================	Attributes	======================================//
	@JsonProperty("name")
	private String name;
		
	@JsonProperty("ResourceRequirements")
	private ResourceRequirementsState resourceRequirements;
	
	//========================== Getters & Setters ================================//
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ResourceRequirementsState getResourceRequirements() {
		return resourceRequirements;
	}
	public void setResourceRequirements(ResourceRequirementsState resourceRequirements) {
		this.resourceRequirements = resourceRequirements;
	}

}
