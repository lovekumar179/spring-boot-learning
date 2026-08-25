package com.backend.security;
// Configuration file for spring security 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  DataSource dataSource; // have connection details and gots from application.properties

  // TODO: Centralized role based access control (RBAC)
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) {
    http.authorizeHttpRequests(authorizeRequests ->
            authorizeRequests.requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
//                    .requestMatchers("/user/**").hasRole("USER")
                    .anyRequest().authenticated());
    http.httpBasic(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user1 = User.withUsername("user1").password("{noop}pass1") // only for learning not recommended
            .roles("USER").build();

    UserDetails admin = User.withUsername("admin").password("{noop}adminPass") // only for learning not recommended
            .roles("ADMIN") // ROLE_ADMIN
            .build();

    UserDetails user2 = User.withUsername("user2").password("{noop}pass2") // only for learning not recommended
            .roles("USER") // ROLE_USER
            .build();

//    return new InMemoryUserDetailsManager(user1);
    JdbcUserDetailsManager userDetailsManager = new
            JdbcUserDetailsManager(dataSource);
    userDetailsManager.createUser(user1);
    userDetailsManager.createUser(user2);
    userDetailsManager.createUser(admin);
    return userDetailsManager;

  }

}
