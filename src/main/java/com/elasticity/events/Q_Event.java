package com.elasticity.events;

import java.util.Date;

import com.elasticity.csm.Transition;

public class Q_Event
{
	private String metric;
	private String type; //Exp : measurable
	private String group;
	private String function;//Exp : average
	private String operator; //Exp : >=
	private String threshold;
	private String unit;
	private String window;
	
	private String instance;
	private Transition transition;
	private String queryMetric;
	private String valueMetric;
	private Date captureDate;
	
	public void setAttributes(Transition T)
	{
		metric = T.getEvent().getProperties().getMetric();
		type = T.getEvent().getProperties().getType();
		function = T.getEvent().getProperties().getFunction();
		operator = T.getEvent().getProperties().getOperator();
		threshold = T.getEvent().getProperties().getThreshold();
		unit = T.getEvent().getProperties().getUnit();
		window = T.getEvent().getProperties().getWindow();
		group = T.getEvent().getProperties().getGroup();
		this.setTransition(T);
	}
	
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
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
	public String getQueryMetric() {
		return queryMetric;
	}
	public void setQueryMetric(String queryMetric) {
		this.queryMetric = queryMetric;
	}
	public String getValueMetric() {
		return valueMetric;
	}
	public void setValueMetric(String valueMetric) {
		this.valueMetric = valueMetric;
	}
	public String getWindow() {
		return window;
	}
	public void setWindow(String window) {
		this.window = window;
	}

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public Date getCaptureDate() {
		return captureDate;
	}

	public void setCaptureDate(Date captureDate) {
		this.captureDate = captureDate;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
}
