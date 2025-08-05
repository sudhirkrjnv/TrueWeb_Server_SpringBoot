package com.example.usermetadata.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermetadata.DTO.ApiResponse;
import com.example.usermetadata.DTO.LoginRequest;
import com.example.usermetadata.DTO.RegisterRequest;
import com.example.usermetadata.DTO.ServiceResponse;
import com.example.usermetadata.Entity.UserMetaData;
import com.example.usermetadata.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
		ServiceResponse serviceResponse = userService.register(registerRequest);
		return ResponseEntity.status(serviceResponse.getStatusCode()).body(serviceResponse.getApiResponse());
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		ServiceResponse serviceResponse = userService.login(loginRequest);
		return ResponseEntity.status(serviceResponse.getStatusCode()).body(serviceResponse.getApiResponse());
	}
}
