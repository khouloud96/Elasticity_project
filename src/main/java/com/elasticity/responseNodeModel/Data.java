package com.elasticity.responseNodeModel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

	private String resultType;
	private List<Result> result;



	public String getResultType() {
		return resultType;
	}


	public void setResultType(String resultType) {
		this.resultType = resultType;
	}


	public List<Result> getResult() {
		return result;
	}


	public void setResult(List<Result> result) {
		this.result = result;
	}















}