package com.project.fitness.controller;

import com.project.fitness.dto.RegisterRequestDto;
import com.project.fitness.dto.UserResponseDto;
import com.project.fitness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final UserService userService;
  
  @PostMapping("/register")
  public ResponseEntity<UserResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
    return ResponseEntity.ok(userService.register(registerRequestDto));
  }


}
