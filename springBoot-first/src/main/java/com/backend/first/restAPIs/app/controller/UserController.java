package com.backend.first.restAPIs.app.controller;

import com.backend.first.restAPIs.app.model.User;
import com.backend.first.restAPIs.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /* @PostMapping
    public String createUser(@RequestBody User user){
      System.out.println(user.getEmail());
      userDb.putIfAbsent(user.getId(), user);
      return "User created successfully...!";
    }*/
  //NOTE: with response entity
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    User createdUser = userService.createUser(user);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

/* // 1, -> John, john@email.com
  // 1, -> Alice, john@email.com
  @PutMapping
  public String updateUser(@RequestBody User user){
    if (userDb.containsKey(user.getId()))
      userDb.put(user.getId(), user);
    return "Update successful...!";
  }*/

  /* @PutMapping
  public ResponseEntity<String> updateUser(@RequestBody User user){
    if (!userDb.containsKey(user.getId()))
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body("User not found for with ID: "+user.getId());
    
    userDb.put(user.getId(), user);
    return new ResponseEntity<>("Update Successful!", HttpStatus.OK);
  }*/

  @PutMapping
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    User updated = userService.updateUser(user);
    if (updated == null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    return ResponseEntity.ok(updated);
  }

  // /user/1, /user/2, /user/3
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable int id) {
    boolean isDeleted = userService.deleteUser(id);
    if (!isDeleted)
      return ResponseEntity.notFound().build();
    return ResponseEntity.noContent().build();
  }

//  this syntax is also allowed
//  @GetMapping({"users", "/user/{id}"}) assigned to URLs to same mapping

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getALlUsers();
  }

// TODO: Started Learning Dynamic URLs

  // NOTE: @PathVariable

  // /user/1, /user/2, /user/3
  /* @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable int id){
    if (!userDb.containsKey(id))
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok(userDb.get(id));
  }*/

  // made userId optional
  @GetMapping("/{userId}")
  public ResponseEntity<User> getUser(
      @PathVariable(value = "userId", required = false) int id
  ) {
    User user = userService.getUserById(id);
    if (user == null)
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok(user);
  }

  @GetMapping("/{userId}/orders/{orderId}")
  public ResponseEntity<User> getUserOrder(
      @PathVariable("userId") int id,
      @PathVariable int orderId
  ) {
    User user = userService.getUserById(id);
    if (user == null)
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok(user);
  }

  // NOTE: Learning @RequestParameters
  // example Mandatory request Param

  // /users/search?name=john
  /* @GetMapping("/search")
  public ResponseEntity<List<User>> searchUsers(@RequestParam String name){
    System.out.println(name);
    return ResponseEntity.ok(new ArrayList<>(userDb.values()));
  }*/


  // example optional request Param
  // /users/search?name=john
  @GetMapping("/search")
  public ResponseEntity<List<User>> searchUsers(
      @RequestParam(required = false, defaultValue = "love") String name,
      @RequestParam(required = false, defaultValue = "love@gmail.com") String email
  ) {

    return ResponseEntity.ok(userService.searchUsers(name, email));
  }

//  TODO: learning about request headers

//  @GetMapping("/info")
//  public String getInfo(@RequestHeader("User-Agent") String UserAgent){
//    return "User Agent: "+UserAgent;
//  }

  //using all annotations in one api
  @GetMapping("/info/{id}")
  public String getInfo(
      @PathVariable int id,
      @RequestParam String name,
      @RequestHeader("User-Agent") String UserAgent
  ) {
    return "User Agent: " + UserAgent
        + " : " + id
        + " : " + name;
  }


}

