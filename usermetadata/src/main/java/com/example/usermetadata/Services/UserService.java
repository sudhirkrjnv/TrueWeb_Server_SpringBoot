package com.example.usermetadata.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.usermetadata.DTO.ApiResponse;
import com.example.usermetadata.DTO.LoginRequest;
import com.example.usermetadata.DTO.RegisterRequest;
import com.example.usermetadata.DTO.ServiceResponse;
import com.example.usermetadata.Entity.UserMetaData;
import com.example.usermetadata.Repository.UserRepo;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public ServiceResponse register(RegisterRequest registerRequest) {
		
		
        if (userRepo.findByEmail(registerRequest.getEmail()).isPresent()) {
            return new ServiceResponse(new ApiResponse("User already present, try different email!", false), 409);
        }
        if (userRepo.findByUsername(registerRequest.getUsername()).isPresent()) {
            return new ServiceResponse(new ApiResponse("Username is already taken!", false), 409);
        }

        
        UserMetaData newUser = new UserMetaData();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        //newUser.setPassword(registerRequest.getPassword());
        
        //newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        
        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());
        newUser.setPassword(hashedPassword);
        
        userRepo.save(newUser);
        
		return new ServiceResponse(new ApiResponse("Account created successfully", true), 200);
		
	}
	
	public ServiceResponse login(LoginRequest loginRequest) {
		
		Optional<UserMetaData> userOptional = userRepo.findByEmail(loginRequest.getEmail());
        if (userOptional.isEmpty()) {
            return new ServiceResponse(new ApiResponse("User not found!", false), 400);
        }
        
        
        UserMetaData user = userOptional.get();
        
//        if (!user.getPassword().equals(loginRequest.getPassword())) {
//            return new ServiceResponse(new ApiResponse("Invalid credentials!", false), 401);
//        }
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return new ServiceResponse(new ApiResponse("Invalid credentials!", false), 401);
        }
        
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("_id", user.getId());
        userDetails.put("username", user.getUsername());
        userDetails.put("email", user.getEmail());
        userDetails.put("profilePicture", user.getProfilePicture());
        userDetails.put("coverPicture", user.getCoverPicture());
        userDetails.put("name", user.getName());
        userDetails.put("bio", user.getBio());
        userDetails.put("gender", user.getGender());
        userDetails.put("dob", user.getDob());

        Map<String, String> workEducation = new HashMap<>();
        workEducation.put("university", user.getUniversity());
        workEducation.put("college", user.getCollege());
        workEducation.put("school", user.getSchool());
        userDetails.put("workEducation", workEducation);

        Map<String, String> locations = new HashMap<>();
        locations.put("currentLocation", user.getCurrentLocation());
        locations.put("permanentLocation", user.getPermanentLocation());
        userDetails.put("locations", locations);

        Map<String, String> contactInfo = new HashMap<>();
        contactInfo.put("whatsapp", user.getWhatsapps());
        contactInfo.put("instagram", user.getInstagram());
        userDetails.put("contactInfo", contactInfo);

        userDetails.put("familyRelationships", user.getFamilyRelationships());

        
        return new ServiceResponse(new ApiResponse("Welcome back " + user.getUsername(), true, userDetails), 200);
        
	}
}
