package com.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/students/**", "/cart/**").permitAll() // Public endpoints
//                        .requestMatchers("/admin/**").hasRole("ADMIN") // Admin-only endpoints
//                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // User and admin can access
                        .anyRequest().authenticated() // Secure all other endpoints
                )
                .httpBasic(Customizer.withDefaults()); // Enable HTTP Basic Authentication

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withUsername("Ivvu")
                .password(passwordEncoder().encode("Thesuko"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
