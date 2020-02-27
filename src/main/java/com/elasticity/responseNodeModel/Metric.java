package com.elasticity.responseNodeModel;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metric {

	@JsonProperty("__name__")
	private String name;
	private String cpu;
	private String instance;
	private String node_id;
	private String node_name;
	private String job;
	private String mode;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCpu() {
		return cpu;
	}


	public void setCpu(String cpu) {
		this.cpu = cpu;
	}


	public String getInstance() {
		return instance;
	}


	public void setInstance(String instance) {
		this.instance = instance;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
	}


	public String getNode_name() {
		return node_name;
	}


	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}


	public String getNode_id() {
		return node_id;
	}


	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}



}