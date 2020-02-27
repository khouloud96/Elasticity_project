package com.elasticity.mappers.credentials;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InstancesConstraints {

	@JsonProperty("nbWorker")
	private int nbWorker;

	@JsonProperty("nbManager")
	private int nbManager;

	public InstancesConstraints() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNbWorker() {
		return nbWorker;
	}

	public void setNbWorker(int nbWorker) {
		this.nbWorker = nbWorker;
	}

	public int getNbManager() {
		return nbManager;
	}

	public void setNbManager(int nbManager) {
		this.nbManager = nbManager;
	}

}
