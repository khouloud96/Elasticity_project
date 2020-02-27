package com.elasticity.csm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionProperties {

	//=========================	Attributes	======================================//
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("resource-target")
	private String resourceTarget;
	
	@JsonProperty("provider")
	private String provider;

	@JsonProperty("adjustment-type")
	private String adjustementType;
	
	@JsonProperty("adjust")
	private int adjust;
	
	@JsonProperty("cooldown")
	private int cooldown;
	//============================================================================//
	
	
	
	//=============================  Constructor ==================================//
	public ActionProperties()
	{
		super();
	}
	//============================================================================//
	
	
	
	//========================== Getters & Setters ================================//
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceTarget() {
		return resourceTarget;
	}

	public void setResourceTarget(String resourceTarget) {
		this.resourceTarget = resourceTarget;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getAdjustementType() {
		return adjustementType;
	}

	public void setAdjustementType(String adjustementType) {
		this.adjustementType = adjustementType;
	}

	public int getAdjust() {
		return adjust;
	}

	public void setAdjust(int adjust) {
		this.adjust = adjust;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	//============================================================================//

}
