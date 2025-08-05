package com.example.usermetadata.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usermetadata.Entity.UserMetaData;

public interface UserRepo extends JpaRepository<UserMetaData, Long> {
	Optional<UserMetaData> findByEmail(String email);
}