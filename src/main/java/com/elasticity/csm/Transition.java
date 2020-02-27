package com.elasticity.csm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transition {

	//=========================	Attributes	======================================//
	@JsonProperty("Identifier")
	private String identifier;
	
	@JsonProperty("Source")
	private String source;
	
	@JsonProperty("Target")
	private String target;
	
	@JsonProperty("Event")
	private Event event;
	
	@JsonProperty("Action")
	private Action action;
	
	//========================== Getters & Setters ================================//
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
}
