package com.backend.first.restAPIs.app.service;

import com.backend.first.restAPIs.app.exceptions.UserNotFoundException;
import com.backend.first.restAPIs.app.model.User;
import com.backend.first.restAPIs.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

  private UserRepository userRepository;
  private final Logger logger = LoggerFactory.getLogger(UserService.class);

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(User user) {
    logger.info("Creating User... INFO");
    logger.debug("Creating User... DEBUG");
    logger.trace("Creating User... TRACE");
    logger.warn("Creating User... WARN");
    logger.error("Creating User... ERROR");
    System.out.println(user.getEmail());

    if (user.getProfile() != null)
      user.getProfile().setUser(user);
    
    if (user.getPosts() != null)
      user.getPosts().forEach(post -> post.setUser(user));

    return userRepository.save(user);
  }

  public User updateUser(User user) {
    User existing = userRepository.findById(user.getId())
        .orElseThrow(
            () -> new UserNotFoundException("User with ID: " + user.getId() + " does not exist!.")
        );
    existing.setName(user.getName());
    existing.setEmail(user.getEmail());
    return userRepository.save(existing);
  }

  public boolean deleteUser(int id) {
    if (!userRepository.existsById(id))
      throw new UserNotFoundException("User with ID: "+id+" does not exist!.");
    userRepository.deleteById(id);
    return true;
  }

  public List<User> getALlUsers() {
    List<User> users = userRepository.findAll();
    if (users.isEmpty())
      throw new NullPointerException("No Users Found in the database");
    return users;
  }

  public User getUserById(int id) {
    return userRepository.findById(id)
        .orElseThrow(
          () -> new UserNotFoundException("User with ID: " + id + " does not exist!.")
        );
  }

  public List<User> searchUsers(String name, String email) {
    return userRepository.findByNameIgnoreCaseAndEmailIgnoreCase(name, email);
  }
}
