package com.example.SamadhanSetu.service;

import com.example.SamadhanSetu.dto.RequestDto.EducationStoryRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EducationStoryService {

    ResponseEntity<?> uploadStory(EducationStoryRequestDTO dto);
    ResponseEntity<?> getAllStories();
    ResponseEntity<?> getStoriesByCategory(String category);
    ResponseEntity<?> getLatestStories();
}
