package com.example.usermetadata.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermetadata.DTO.ApiResponse;
import com.example.usermetadata.DTO.LoginRequest;
import com.example.usermetadata.DTO.MessageResponse;
import com.example.usermetadata.DTO.RegisterRequest;
import com.example.usermetadata.DTO.ServiceResponse;
import com.example.usermetadata.DTO.UserApiResponse;
import com.example.usermetadata.Entity.UserMetaData;
import com.example.usermetadata.Exception.UserException;
import com.example.usermetadata.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/connected/v1/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest) throws UserException {
		//ServiceResponse serviceResponse = userService.register(registerRequest);
		MessageResponse message = userService.register(registerRequest);
		//return ResponseEntity.status(serviceResponse.getStatusCode()).body(serviceResponse.getApiResponse());
		return new ResponseEntity<MessageResponse> (message, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserApiResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws UserException {
		//ServiceResponse serviceResponse = userService.login(loginRequest);
		UserApiResponse data = userService.login(loginRequest);
		//return ResponseEntity.status(serviceResponse.getStatusCode()).body(serviceResponse.getApiResponse());
		return new ResponseEntity<UserApiResponse> (data, HttpStatus.OK);
	}
}
