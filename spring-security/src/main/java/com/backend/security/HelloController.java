package com.backend.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//  @PreAuthorize("hasRole('ADMIN')") // Method lvl RBAC(role based access control)
  @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // MBAC(method based access control)
  @GetMapping("/hello")
  public String sayHello(){
    return "Hello Spring Security";
  }

  @GetMapping("/admin/hello")
  public String sayAdminHello(){
    return "Hello, Admin Spring Security";
  }

  @GetMapping("/user/hello")
  public String sayUserHello(){
    return "Hello, User Spring Security";
  }

}
