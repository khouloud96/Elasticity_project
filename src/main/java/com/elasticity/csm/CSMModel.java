package com.elasticity.csm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CSMModel {
	
	//=========================	Attributes	======================================//
	@JsonProperty("CSM version")
	private String CSMversion;
	
	@JsonProperty("description")
	private String description;

	@JsonProperty("CSM")
	private CSM csm;

	//========================== Getters & Setters ================================//
	public CSM getCsm() {
		return csm;
	}

	public void setCsm(CSM csm) {
		this.csm = csm;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCSMversion() {
		return CSMversion;
	}

	public void setCSMversion(String cSMversion) {
		CSMversion = cSMversion;
	}
	

}
