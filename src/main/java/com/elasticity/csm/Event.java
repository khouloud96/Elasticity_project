package com.elasticity.csm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
	
	//=========================	Attributes	======================================//
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("properties")
	private EventProperties properties;
	//============================================================================//

	
	
	//=============================  Constructor ==================================//
	public Event() 
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

	public EventProperties getProperties() {
		return properties;
	}

	public void setProperties(EventProperties properties) {
		this.properties = properties;
	}
	//==============================================================================//

}
