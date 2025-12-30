package com.example.usermetadata.Config;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.usermetadata.Services.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String header = request.getHeader(SecurityContext.HEADER);
		
		if(header != null && header.startsWith("Bearer ")) {
			
			try {
				
				String token = header.substring(7);
				
				Claims claims = jwtUtil.extractAllClaims(token);
				
				String username = claims.getSubject();
                String authorities = (String) claims.get("authorities");
                
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken( username, null, grantedAuthorities );
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
                
			} catch (Exception e) {
				throw new BadCredentialsException("Invalid JWT Token");
			}
			
		}
		
		filterChain.doFilter(request, response);
	}
	
}
