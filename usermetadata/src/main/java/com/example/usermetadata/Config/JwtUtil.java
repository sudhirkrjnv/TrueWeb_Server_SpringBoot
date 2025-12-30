package com.example.usermetadata.Config;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	public String generateToken() {
		
		SecretKey key = Keys.hmacShaKeyFor(SecurityContext.SECRET_KEY.getBytes());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return Jwts.builder()
				.setIssuer("Sudhir Kumar")
				.setSubject(authentication.getName())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + 300000000))
				.claim("authorities", populatedAuthorities(authentication.getAuthorities()))
				.signWith(key)
				.compact();
				
	}

	public String populatedAuthorities(Collection<? extends GrantedAuthority> collection) {
		// TODO Auto-generated method stub
		Set<String> authorities = new HashSet<>();
		for(GrantedAuthority authority:collection) {
			authorities.add(authority.getAuthority());
		}
		return String.join(",", authorities);
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SecurityContext.SECRET_KEY.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}




