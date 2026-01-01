package com.example.usermetadata.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.usermetadata.DTO.EditProfileRequest;
import com.example.usermetadata.DTO.LoginRequest;
import com.example.usermetadata.DTO.MessageResponse;
import com.example.usermetadata.DTO.RegisterRequest;
import com.example.usermetadata.DTO.UserApiResponse;
import com.example.usermetadata.DTO.UserResponse;
import com.example.usermetadata.Entity.UserMetaData;
import com.example.usermetadata.Exception.UserException;
import com.example.usermetadata.Repository.UserRepo;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public MessageResponse register(RegisterRequest registerRequest) throws UserException {
		
        if (userRepo.findByEmail(registerRequest.getEmail()).isPresent()) {
        	throw new UserException("User already present, try different email!");
        }
        if (userRepo.findByUsername(registerRequest.getUsername()).isPresent()) {
        	throw new UserException("Username is already taken!");
        }

        
        UserMetaData newUser = new UserMetaData();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        
        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());
        newUser.setPassword(hashedPassword);
        
        userRepo.save(newUser);
        
        return new MessageResponse("Account created successfully!");
		
	}
	
	public UserApiResponse login(LoginRequest loginRequest) throws UserException {     

        Optional<UserMetaData> userOptional = userRepo.findByEmail(loginRequest.getEmail());
        if (userOptional.isEmpty()) {
            throw new UserException("User not found!");
        }
        
        
        UserMetaData user = userOptional.get();
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        	throw new UserException("Invalid Credentials!");
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

        return new UserApiResponse("Welcome back " + user.getUsername(), new UserResponse(user));
        
	}
	
	
	public UserResponse getUserDetails(Long id) throws UserException {
	  
		 Optional<UserMetaData> userOptional = userRepo.findById(id); 
		 if(userOptional.isEmpty()) {
			 throw new UserException("User not found!"); 
		 }
		 UserMetaData user = userOptional.get();
		  
		 return new UserResponse(user);
	  
	}
	 
	
	public UserResponse getUserDetail(Authentication auth) throws UserException {
		
		if(auth == null) {
			throw new UserException("Invalid Token");
		}
	    Optional<UserMetaData> opt = userRepo.findByEmail(auth.getName());

	    if (!opt.isPresent()) {
	        throw new UserException("User Not Found");
	    }

	    UserMetaData user = opt.get();

	    return new UserResponse(user);
	}
	
	public UserResponse editProfile(Authentication auth, EditProfileRequest request) throws UserException {

	    if (auth == null) {
	        throw new UserException("Invalid Token");
	    }

	    UserMetaData user = userRepo.findByEmail(auth.getName())
	            .orElseThrow(() -> new UserException("User not found"));

	    if (request.getName() != null) user.setName(request.getName());
	    if (request.getBio() != null) user.setBio(request.getBio());
	    if (request.getGender() != null) user.setGender(request.getGender());
	    if (request.getDob() != null) user.setDob(request.getDob());
	    if (request.getFamilyRelationships() != null)
	        user.setFamilyRelationships(request.getFamilyRelationships());

	    if (request.getWorkEducation() != null) {
	        user.setUniversity(request.getWorkEducation().get("university"));
	        user.setCollege(request.getWorkEducation().get("college"));
	        user.setSchool(request.getWorkEducation().get("school"));
	    }

	    if (request.getLocations() != null) {
	        user.setCurrentLocation(request.getLocations().get("currentLocation"));
	        user.setPermanentLocation(request.getLocations().get("permanentLocation"));
	    }

	    if (request.getContactInfo() != null) {
	        user.setWhatsapps(request.getContactInfo().get("whatsapp"));
	        user.setInstagram(request.getContactInfo().get("instagram"));
	    }

	    userRepo.save(user);
	    return new UserResponse(user);
	}


}
