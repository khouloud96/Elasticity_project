package com.elasticity.csm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventProperties {

	//=========================	Attributes	======================================//
	@JsonProperty("metric")
	private String metric; //Exp : cpuusage
	
	@JsonProperty("type")
	private String type; //Exp : measurable
	
	@JsonProperty("group")
	private String group; //true (Monitor the  or false
	
	@JsonProperty("function")
	private String function;//Exp : average
	
	@JsonProperty("operator")
	private String operator; //Exp : >=
	
	@JsonProperty("threshold")
	private String threshold;
	
	@JsonProperty("unit")
	private String unit;
	
	@JsonProperty("window")
	private String window;
	
	@JsonProperty("frequency")
	private String frequency;
	
	@JsonProperty("expression")
	private String expression;
	//============================================================================//

	
	
	//=============================  Constructor ==================================//	
	public EventProperties() 
	{
		super();
	}
	//============================================================================//

	
	
	//========================== Getters & Setters ================================//
	public String getMetric() {
		return metric;
	}
	public void setMetric(String metric) {
		this.metric = metric;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getThreshold() {
		return threshold;
	}
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getWindow() {
		return window;
	}
	public void setWindow(String window) {
		this.window = window;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	//============================================================================//

}
