package com.bridgeit.fundoonote.userservice.model;

public class Response {

	private String status;
	private Object payload;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
public Response() {
		
	}
	public Response(String status, Object payload) {
		super();
		this.status = status;
		this.payload = payload;
	}
}
