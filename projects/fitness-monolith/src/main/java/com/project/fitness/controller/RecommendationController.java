package com.project.fitness.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.fitness.dto.RecommendationRequestDto;
import com.project.fitness.model.Recommendation;
import com.project.fitness.service.RecommendationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
  
}
