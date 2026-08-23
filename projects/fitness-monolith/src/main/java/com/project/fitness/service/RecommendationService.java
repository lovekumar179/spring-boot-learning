package com.project.fitness.service;

import org.springframework.stereotype.Service;

import com.project.fitness.dto.RecommendationRequestDto;
import com.project.fitness.model.Activity;
import com.project.fitness.model.Recommendation;
import com.project.fitness.model.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.RecommendationRepository;
import com.project.fitness.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecommendationService {
  private final UserRepository userRepository;
  private final ActivityRepository activityRepository;
  private final RecommendationRepository recommendationRepository;

  public Recommendation generateRecommendation(RecommendationRequestDto requestDto) {
    User user = userRepository.findById(requestDto.getUserId())
          .orElseThrow(() -> new RuntimeException("User Not Found: "+requestDto.getUserId()));
    Activity activity = activityRepository.findById(requestDto.getActivityId())
          .orElseThrow(() -> new RuntimeException("Activity Not Found: "+requestDto.getActivityId()));

    Recommendation recommendation = Recommendation.builder()
          .user(user)
          .activity(activity)
          .improvements(requestDto.getImprovements())
          .suggestions(requestDto.getSuggestions())
          .safety(requestDto.getSafety())
          .build();
        
    return recommendationRepository.save(recommendation);
    
  }

}
