package com.project.fitness.controller;

import com.project.fitness.dto.ActivityRequestDto;
import com.project.fitness.dto.ActivityResponseDto;
import com.project.fitness.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping
  public ResponseEntity<List<ActivityResponseDto>> getUserActivities(
      @RequestHeader(value = "X-User-ID") String userId
  ) {
    return ResponseEntity.ok(activityService.getAllActivities(userId));
  }

}
