package com.example.usermetadata.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermetadata.DTO.ApiResponse;
import com.example.usermetadata.DTO.ServiceResponse;
import com.example.usermetadata.Entity.UserMetaData;
import com.example.usermetadata.Services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(@RequestBody UserMetaData registerData) {
		ServiceResponse serviceResponse = userService.register(registerData);
		return ResponseEntity.status(serviceResponse.getStatusCode()).body(serviceResponse.getApiResponse());
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@RequestBody UserMetaData loginData) {
		ServiceResponse serviceResponse = userService.login(loginData);
		return ResponseEntity.status(serviceResponse.getStatusCode()).body(serviceResponse.getApiResponse());
	}
}
