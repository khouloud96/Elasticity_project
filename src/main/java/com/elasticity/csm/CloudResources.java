package com.elasticity.csm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudResources {

	//=========================	Attributes	======================================//
	@JsonProperty("Ressource")
	private Ressource ressource;
	//=============================================================================//

	
	//=============================  Constructor ==================================//
	public CloudResources() 
	{
		super();
	}
	//=============================================================================//

	//========================== Getters & Setters ================================//
	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}
	//=============================================================================//

}
