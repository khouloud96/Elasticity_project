package com.elasticity.csm;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CSM 
{
	//=========================	Attributes	======================================//
	@JsonProperty("States")
	private List<State> states;
	
	@JsonProperty("Transition")
	private List<Transition> transitions;
	
	@JsonProperty("Cloud Resources")
	private CloudResources cloudResources;
	//=============================================================================//

	
	
	//=============================  Constructor ==================================//
	public CSM() 
	{
		super();
	}
	//=============================================================================//
	
	
	
	//========================== Getters & Setters ================================//
	public List<State> getStates() {
		return states;
	}
	public void setStates(List<State> states) {
		this.states = states;
	}
	public List<Transition> getTransitions() {
		return transitions;
	}
	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}
	public CloudResources getCloudResources() {
		return cloudResources;
	}
	public void setCloudResources(CloudResources cloudResources) {
		this.cloudResources = cloudResources;
	}
	//=============================================================================//


}
