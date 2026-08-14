package com.backend.springDataJPA;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserClassSpringBoot> createUser(@RequestBody UserClassSpringBoot user){
    return ResponseEntity.ok(userService.createUser(user));
  }

  @GetMapping
  public ResponseEntity<List<UserClassSpringBoot>> getAllUsers(){
    return ResponseEntity.ok(userService.getAllUsers());
  }

}
