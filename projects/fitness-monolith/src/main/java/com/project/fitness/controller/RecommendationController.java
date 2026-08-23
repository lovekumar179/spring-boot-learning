package com.project.fitness.controller;

import org.springframework.web.bind.annotation.*;

import com.project.fitness.dto.RecommendationRequestDto;
import com.project.fitness.model.Recommendation;
import com.project.fitness.service.RecommendationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

  private final RecommendationService recommendationService;

  @PostMapping("/generate")
  public ResponseEntity<Recommendation> generateRecommendation(
    @RequestBody RecommendationRequestDto requestDto
  ) {  
      Recommendation recommendation = recommendationService.generateRecommendation(requestDto);
      return ResponseEntity.ok(recommendation);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Recommendation>> getUserRecommendation(
    @PathVariable String userId
  ) {
      List<Recommendation> recommendationList = recommendationService.getUserRecommendation(userId);
      return ResponseEntity.ok(recommendationList);
  }

}
