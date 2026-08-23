package com.project.fitness.service;

import com.project.fitness.dto.ActivityRequestDto;
import com.project.fitness.dto.ActivityResponseDto;
import com.project.fitness.model.Activity;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {
  private final ActivityRepository activityRepository;
  private final UserRepository userRepository;


  public ActivityResponseDto trackActivity(ActivityRequestDto activityRequestDto) {
    User user = userRepository.findById(activityRequestDto.getUserId())
        .orElseThrow(() -> new RuntimeException("Invalid User" + activityRequestDto.getUserId()));
    Activity activity = Activity.builder()
        .user(user)
        .type(activityRequestDto.getType())
        .duration((activityRequestDto.getDuration()))
        .caloriesBurned(activityRequestDto.getCaloriesBurned())
        .startTime(activityRequestDto.getStartTime())
        .additionalMetrics(activityRequestDto.getAdditionalMetrics())
        .build();
    Activity savedActivity = activityRepository.save(activity);

    return mapToResponse(savedActivity);

  }

  private ActivityResponseDto mapToResponse(Activity activity) {
    ActivityResponseDto activityResponseDto = new ActivityResponseDto();
    activityResponseDto.setId(activity.getId());
    activityResponseDto.setUserId(activity.getUser().getId());
    activityResponseDto.setType(activity.getType());
    activityResponseDto.setDuration(activity.getDuration());
    activityResponseDto.setCaloriesBurned(activity.getCaloriesBurned());
    activityResponseDto.setStartTime(activity.getStartTime());
    activityResponseDto.setAdditionalMetrics(activity.getAdditionalMetrics());
    activityResponseDto.setCreatedAt(activity.getCreatedAt());
    activityResponseDto.setUpdateAt(activity.getUpdateAt());

    return activityResponseDto;

  }
}
