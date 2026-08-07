package com.backend.first.restAPIs.app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
  private Map<Integer, User> userDb = new HashMap<>();

  /* @PostMapping
  public String createUser(@RequestBody User user){
    System.out.println(user.getEmail());
    userDb.putIfAbsent(user.getId(), user);
    return "User created successfully...!";
  }*/
  //NOTE: with response entity
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user){
    System.out.println(user.getEmail());
    userDb.putIfAbsent(user.getId(), user);

//    return ResponseEntity
//        .status(HttpStatus.CREATED)
//        .body(user);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
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
    return new ResponseEntity<>("Update Successful..!", HttpStatus.OK);
  }*/

  @PutMapping
  public ResponseEntity<User> updateUser(@RequestBody User user){
    if (!userDb.containsKey(user.getId()))
      return ResponseEntity.notFound().build();
    userDb.put(user.getId(), user);
//    return ResponseEntity.status(HttpStatus.OK).body(user);
    return ResponseEntity.ok(user);
  }

  // /user/1, /user/2, /user/3
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable int id){
    if (!userDb.containsKey(id))
      return ResponseEntity.notFound().build();
    userDb.remove(id);
//    return ResponseEntity.ok("User Deleted Successfully...!");
    return ResponseEntity.noContent().build();
  }

//  this syntax is also allowed
//  @GetMapping({"users", "/user/{id}"}) assigned to URLs to same mapping

  @GetMapping
  public List<User> getAllUsers(){
    return new ArrayList<>(userDb.values());
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
  ){
    if (!userDb.containsKey(id))
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok(userDb.get(id));
  }

  @GetMapping("/{userId}/orders/{orderId}")
  public ResponseEntity<User> getUserOrder(
      @PathVariable("userId") int id,
      @PathVariable int orderId
  ){
    System.out.println("ORDER ID: "+orderId);
    if (!userDb.containsKey(id))
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok(userDb.get(id));
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
  ){
    System.out.println(name);
    List<User> users = userDb.values().stream()
        .filter(u -> u.getName().equalsIgnoreCase(name))
        .filter(u -> u.getEmail().equalsIgnoreCase(email))
        .toList();
    return ResponseEntity.ok(users);
  }

}
