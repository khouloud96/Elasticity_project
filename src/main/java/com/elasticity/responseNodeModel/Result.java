package com.elasticity.responseNodeModel;


import org.apache.chemistry.opencmis.commons.impl.json.JSONArray;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Result {

	private Metric metric;
	private JSONArray value;



	public Metric getMetric() {
		return metric;
	}



	public void setMetric(Metric metric) {
		this.metric = metric;
	}



	public JSONArray getValue() {
		return value;
	}



	public void setValue(JSONArray value) {
		this.value = value;
	}
}