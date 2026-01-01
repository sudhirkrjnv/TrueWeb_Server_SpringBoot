package com.example.usermetadata.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermetadata.Config.JwtUtil;
import com.example.usermetadata.Config.SecurityContext;
import com.example.usermetadata.DTO.EditProfileRequest;
import com.example.usermetadata.DTO.LoginRequest;
import com.example.usermetadata.DTO.MessageResponse;
import com.example.usermetadata.DTO.RegisterRequest;
import com.example.usermetadata.DTO.UserApiResponse;
import com.example.usermetadata.DTO.UserResponse;
import com.example.usermetadata.Exception.UserException;
import com.example.usermetadata.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/connected/v1/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
    private JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest) throws UserException {
		MessageResponse message = userService.register(registerRequest);
		return new ResponseEntity<> (message, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
    public ResponseEntity<UserApiResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws UserException {
        UserApiResponse data = userService.login(loginRequest);
        
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken( loginRequest.getEmail(), null, AuthorityUtils.createAuthorityList("ROLE_USER"));

        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtUtil.generateToken();
       
        return ResponseEntity.ok().header(SecurityContext.HEADER, "Bearer " + token).body(data);
    }
	
	@GetMapping("/getuserdetails/{id}")
	public ResponseEntity<UserResponse> login( @PathVariable Long id) throws UserException {
		UserResponse data = userService.getUserDetails(id);
		return ResponseEntity.ok().body(data);
	}
	
	@GetMapping("/getuserdetails")
	public ResponseEntity<UserResponse> getUserDetails(Authentication auth) throws UserException {
	    UserResponse data = userService.getUserDetail(auth);
	    return ResponseEntity.ok(data);
	}
	
	@PostMapping("/profile/edit")
	public ResponseEntity<UserResponse> editProfile( Authentication auth, @RequestBody EditProfileRequest request) throws UserException {
	    UserResponse response = userService.editProfile(auth, request);
	    return ResponseEntity.ok(response);
	}
}
