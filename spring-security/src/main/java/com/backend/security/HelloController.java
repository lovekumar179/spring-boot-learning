package com.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JWTUtils jwtUtils;

  //  @PreAuthorize("hasRole('ADMIN')") // Method lvl RBAC(role based access control)
  @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // MBAC(method based access control)
  @GetMapping("/hello")
  public String sayHello() {
    return "Hello Spring Security";
  }

  @GetMapping("/admin/hello")
  public String sayAdminHello() {
    return "Hello, Admin Spring Security";
  }

  @GetMapping("/user/hello")
  public String sayUserHello() {
    return "Hello, User Spring Security";
  }

  @PostMapping("/signin")
  public String login(@RequestBody LoginRequest loginRequest) {
    Authentication authentication; // represents an authenticated User
    try {
      authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      loginRequest.getUserName(),
                      loginRequest.getPassword()
              )
      );
    } catch (AuthenticationException e) {
      e.printStackTrace();
      return "Could Not Authenticate";
    }

    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String jwtToken = jwtUtils.generateTokenFormUsername(userDetails.getUsername());

    return jwtToken;

  }

}
