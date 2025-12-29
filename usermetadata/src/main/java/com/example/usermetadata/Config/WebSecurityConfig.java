package com.example.usermetadata.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        
            .csrf(csrf -> csrf.disable()) // Disable CSRF (stateless REST API)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 	// ❌ Disable sessions

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/connected/v1/user/register").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin() .and() .httpBasic();
            
//            .formLogin(form -> form.disable())
//            .httpBasic(httpBasic -> httpBasic.disable());	//Disable basic auth credentials
        
        http
        	.addFilterBefore(new JwtTokenValidationFilter(), BasicAuthenticationFilter.class)
        	.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class);
        
        return http.build();
    }

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
