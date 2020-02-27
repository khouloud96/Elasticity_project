package com.elasticity.csm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Action {

	//=========================	Attributes	======================================//
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("properties")
	private ActionProperties properties;
	//============================================================================//
	
	

	//=============================  Constructor ==================================//
	public Action()
	{
		super();
	}
	//=============================================================================//
	
	
	
	//========================== Getters & Setters ================================//
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ActionProperties getProperties() {
		return properties;
	}

	public void setProperties(ActionProperties properties) {
		this.properties = properties;
	}
	//==============================================================================//
}
