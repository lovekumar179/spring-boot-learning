package com.project.fitness.controller;

import com.project.fitness.dto.ActivityRequestDto;
import com.project.fitness.dto.ActivityResponseDto;
import com.project.fitness.service.ActivityService;
import com.project.fitness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {
  private final ActivityService activityService;

  @PostMapping
  public ResponseEntity<ActivityResponseDto> trackActivity(
      @RequestBody ActivityRequestDto activityRequestDto
  ) {
    return ResponseEntity.ok(activityService.trackActivity(activityRequestDto));
  }

//  @GetMapping
//  public ResponseEntity<List<ActivityResponseDto>> trackActivity() {
//    return "";
//  }

}
