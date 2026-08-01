package com.crud.controller;

import com.crud.service.UserService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  public void createUser(String name) {
    userService.addUser(name);
    System.out.println("User created: "+name);
  }

  public void listUser() {
    List<String> users = userService.getAllUsers();
    System.out.println("All users: "+users);
  }

}
