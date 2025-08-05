package com.example.usermetadata.Entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name="User")
public class UserMetaData {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String username;

	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	private String name;
	private String profilePicture;
	private String coverPicture;
	private String bio;
	private String gender;
	private String university;
	private String college;
	private String school;
	private String currentLocation;
	private String permanentLocation;
	private String whatsapps;
	private String instagram;
	private String familyRelationships;
	
	@Temporal(TemporalType.DATE)
	private Date dob;

	@CreationTimestamp
	@Column(nullable=false, updatable=false)
	private LocalDateTime createdAt;

	public UserMetaData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserMetaData(Long id, String username, String email, String password, String name, String profilePicture,
			String coverPicture, String bio, String gender, String university, String college, String school,
			String currentLocation, String permanentLocation, String whatsapps, String instagram,
			String familyRelationships, Date dob, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.profilePicture = profilePicture;
		this.coverPicture = coverPicture;
		this.bio = bio;
		this.gender = gender;
		this.university = university;
		this.college = college;
		this.school = school;
		this.currentLocation = currentLocation;
		this.permanentLocation = permanentLocation;
		this.whatsapps = whatsapps;
		this.instagram = instagram;
		this.familyRelationships = familyRelationships;
		this.dob = dob;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
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

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getPermanentLocation() {
		return permanentLocation;
	}

	public void setPermanentLocation(String permanentLocation) {
		this.permanentLocation = permanentLocation;
	}

	public String getWhatsapps() {
		return whatsapps;
	}

	public void setWhatsapps(String whatsapps) {
		this.whatsapps = whatsapps;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getFamilyRelationships() {
		return familyRelationships;
	}

	public void setFamilyRelationships(String familyRelationships) {
		this.familyRelationships = familyRelationships;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
}
