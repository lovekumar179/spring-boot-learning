package com.project.fitness.service;

import com.project.fitness.dto.RegisterRequestDto;
import com.project.fitness.dto.UserResponseDto;
import com.project.fitness.model.User;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserResponseDto register(RegisterRequestDto requestDto) {
//    User user = new User(null, requestDto.getEmail(), requestDto.getPassword(), requestDto.getFirstName(), requestDto.getLastName(), Instant.parse("2026-08-18T14:32:00Z").atZone(ZoneOffset.UTC).toLocalDateTime(), Instant.parse("2026-08-18T14:32:00Z").atZone(ZoneOffset.UTC).toLocalDateTime(), List.of(), List.of());
    //NOTE: by using builder pattern
      User user = User.builder()
          .email(requestDto.getEmail())
          .firstName(requestDto.getFirstName())
          .lastName(requestDto.getLastName())
          .password(requestDto.getPassword())
          .build(); // all other fields will be set null
    User savedUser = userRepository.save(user);
    return mapToResponse(savedUser);
  }

  private UserResponseDto mapToResponse(User savedUser) {
    UserResponseDto responseDto = new UserResponseDto();
    responseDto.setId(savedUser.getId());
    responseDto.setEmail(savedUser.getEmail());
    responseDto.setPassword(savedUser.getPassword());
    responseDto.setFirstName(savedUser.getFirstName());
    responseDto.setLastName(savedUser.getLastName());
    responseDto.setCreatedAt(savedUser.getCreatedAt());
    responseDto.setUpdatedAt(savedUser.getUpdateAt());
    return responseDto;
  }
}
