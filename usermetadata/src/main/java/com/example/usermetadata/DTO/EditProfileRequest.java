package com.example.usermetadata.DTO;

import java.sql.Date;
import java.util.Map;

public class EditProfileRequest {
	
	private String name;
    private String bio;
    private String gender;
    private Date dob;

    private Map<String, String> workEducation;
    private Map<String, String> locations;
    private Map<String, String> contactInfo;

    private String familyRelationships;

	public EditProfileRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EditProfileRequest(String name, String bio, String gender, Date dob, Map<String, String> workEducation,
			Map<String, String> locations, Map<String, String> contactInfo, String familyRelationships) {
		super();
		this.name = name;
		this.bio = bio;
		this.gender = gender;
		this.dob = dob;
		this.workEducation = workEducation;
		this.locations = locations;
		this.contactInfo = contactInfo;
		this.familyRelationships = familyRelationships;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Map<String, String> getWorkEducation() {
		return workEducation;
	}

	public void setWorkEducation(Map<String, String> workEducation) {
		this.workEducation = workEducation;
	}

	public Map<String, String> getLocations() {
		return locations;
	}

	public void setLocations(Map<String, String> locations) {
		this.locations = locations;
	}

	public Map<String, String> getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(Map<String, String> contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getFamilyRelationships() {
		return familyRelationships;
	}

	public void setFamilyRelationships(String familyRelationships) {
		this.familyRelationships = familyRelationships;
	}
    
    
}
