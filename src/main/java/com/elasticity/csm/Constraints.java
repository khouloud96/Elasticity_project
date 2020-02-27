package com.elasticity.csm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Constraints 
{
	//=============================  Attributes ==================================//
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("operator")
	private String operator;
	
	@JsonProperty("value")
	private String value;
	
	@JsonProperty("unit")
	private String unit;
	//=============================================================================//

	
	
	//=============================  Constructor ==================================//
	public Constraints() 
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	//=============================================================================//

}
