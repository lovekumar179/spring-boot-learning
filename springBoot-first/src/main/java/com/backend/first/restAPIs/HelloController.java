package com.backend.first.restAPIs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") // on class level
public class HelloController {

  @GetMapping("/hello")
  public String sayHello(){
    return "HELLO WORLD...!";
  }

//  @GetMapping("/user") -> is shortcut of below line 
  @RequestMapping(value = "/user", method = RequestMethod.GET) // on method level
  public User getUser(){
    return new User(1, "Love Kumar", "love@gmail.com");
  }

}
