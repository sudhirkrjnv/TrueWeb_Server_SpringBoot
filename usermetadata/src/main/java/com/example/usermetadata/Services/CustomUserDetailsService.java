package com.example.usermetadata.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.usermetadata.Repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<com.example.usermetadata.Entity.UserMetaData> opt = userRepo.findByEmail(username);
		if(opt.isPresent()) {
			com.example.usermetadata.Entity.UserMetaData user = opt.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			return new User(user.getEmail(), user.getPassword(), authorities);
		}
		throw new BadCredentialsException("user not found with username" + username);
	}

}

