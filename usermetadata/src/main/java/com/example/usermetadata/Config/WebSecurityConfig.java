package com.example.usermetadata.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain seciurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        	.csrf(csrf -> csrf.disable())	//Disable Cross-Site Request Forgery, 403 forbidden error	
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/connected/v1/user/register", "/connected/v1/user/login").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> httpBasic.disable())	//Disable basic auth credentials
            .formLogin(form -> form.disable());
            
        return httpSecurity.build();
    }

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
