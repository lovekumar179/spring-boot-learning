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

  @GetMapping
  public List<User> getAllUsers(){
    return new ArrayList<>(userDb.values());
  }

}
