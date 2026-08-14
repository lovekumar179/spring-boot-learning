package com.backend.first.restAPIs.app.repository;

import com.backend.first.restAPIs.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  List<User> findByNameIgnoreCaseAndEmailIgnoreCase(String name, String email);
}
