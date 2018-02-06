package com.jinsong.model;

public class Order {

	private String orderNumber;
	private String acceptTime;
	private String engineerName;
	private String engineerTel;
	private String estimatedArrivalTime;
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getEngineerName() {
		return engineerName;
	}
	public void setEngineerName(String engineerName) {
		this.engineerName = engineerName;
	}
	public String getEngineerTel() {
		return engineerTel;
	}
	public void setEngineerTel(String engineerTel) {
		this.engineerTel = engineerTel;
	}
	public String getEstimatedArrivalTime() {
		return estimatedArrivalTime;
	}
	public void setEstimatedArrivalTime(String estimatedArrivalTime) {
		this.estimatedArrivalTime = estimatedArrivalTime;
	}
	
	
	
}
