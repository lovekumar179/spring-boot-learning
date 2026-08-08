package com.backend.first.restAPIs.app;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

  private final Map<Integer, User> userDb = new HashMap<>();

  public User createUser(User user) {
    System.out.println(user.getEmail());
    userDb.putIfAbsent(user.getId(), user);

//    return ResponseEntity
//        .status(HttpStatus.CREATED)
//        .body(user);
    return user;
  }

  public User updateUser(User user) {
    if (!userDb.containsKey(user.getId()))
      return null;
//      return ResponseEntity.notFound().build();
    userDb.put(user.getId(), user);
//    return ResponseEntity.status(HttpStatus.OK).body(user);
    return user;
  }

  public boolean deleteUser(int id) {
    if (!userDb.containsKey(id))
      return false;
    userDb.remove(id);
    return true;
  }

  public List<User> getALlUsers() {
    return new ArrayList<>(userDb.values());
  }

  public User getUserById(int id) {
    return userDb.get(id);
  }

  public List<User> searchUsers(String name, String email) {
//    System.out.println(name);
    return userDb.values().stream()
        .filter(u -> u.getName().equalsIgnoreCase(name))
        .filter(u -> u.getEmail().equalsIgnoreCase(email))
        .toList();
  }
}
