package com.example.usermetadata.DTO;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceResponse {
	
	@Autowired
	ApiResponse apiResponse;
	private int statusCode;
	
	
	public ServiceResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ServiceResponse(ApiResponse apiResponse, int statusCode) {
		super();
		this.apiResponse = apiResponse;
		this.statusCode = statusCode;
	}


	public ApiResponse getApiResponse() {
		return apiResponse;
	}


	public void setApiResponse(ApiResponse apiResponse) {
		this.apiResponse = apiResponse;
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
	
}
