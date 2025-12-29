package com.example.usermetadata.DTO;

public class UserApiResponse {
	
	private String message;
	private UserResponse user;
	
	public UserApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserApiResponse(String message, UserResponse user) {
		super();
		this.message = message;
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}
	
}
