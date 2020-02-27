package com.elasticity.csm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ressource {
	//=============================  Attributes ==================================//
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("Constraints")
	private Constraints constraints;
	
	@JsonProperty("ResourceRequirements")
	private ResourceRequirements resourceRequirements;
	//=============================================================================//

	
	//=============================  Constructor ==================================//
	public Ressource() 
	{
		super();
	}
	//=============================================================================//
	
	
	//========================== Getters & Setters ================================//
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Constraints getConstraints() {
		return constraints;
	}
	public void setConstraints(Constraints constraints) {
		this.constraints = constraints;
	}
	public ResourceRequirements getResourceRequirements() {
		return resourceRequirements;
	}
	public void setResourceRequirements(ResourceRequirements resourceRequirements) {
		this.resourceRequirements = resourceRequirements;
	}
	//=============================================================================//
}
