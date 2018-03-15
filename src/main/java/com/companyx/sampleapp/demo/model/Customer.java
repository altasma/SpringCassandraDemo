package com.companyx.sampleapp.demo.model;

public class Customer {
	
	private int id = -1;
	private String name = null;
	private String status = null;
	
	public Customer() {
		
	}
	public Customer(int id, String name, String status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", status=" + status + "]";
	}

	

}
