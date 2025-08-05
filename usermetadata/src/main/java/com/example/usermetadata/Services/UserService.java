package com.example.usermetadata.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.usermetadata.DTO.ApiResponse;
import com.example.usermetadata.DTO.ServiceResponse;
import com.example.usermetadata.Entity.UserMetaData;
import com.example.usermetadata.Repository.UserRepo;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	
	public ServiceResponse register(UserMetaData registerData) {
		
		if (registerData.getUsername() == null || registerData.getEmail() == null || registerData.getPassword() == null) {
            return new ServiceResponse(new ApiResponse("Please fill required details!", false), 400);
        }

        if (userRepo.findByEmail(registerData.getEmail()).isPresent()) {
            return new ServiceResponse(new ApiResponse("User already present, try different email!", false), 409);
        }

        userRepo.save(registerData);
		return new ServiceResponse(new ApiResponse("Account created successfully", true), 200);
		
	}
	
	public ServiceResponse login(UserMetaData loginData) {
		
		if (loginData.getEmail() == null || loginData.getPassword() == null) {
            return new ServiceResponse(new ApiResponse("Please fill required details!", false), 400);
        }
		
		Optional<UserMetaData> userOptional = userRepo.findByEmail(loginData.getEmail());
        if (userOptional.isEmpty()) {
            return new ServiceResponse(new ApiResponse("User not found!", false), 400);
        }
        
        
        UserMetaData user = userOptional.get();
        if (!user.getPassword().equals(loginData.getPassword())) {
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
