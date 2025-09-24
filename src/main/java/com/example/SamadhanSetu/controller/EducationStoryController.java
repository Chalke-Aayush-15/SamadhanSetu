package com.example.SamadhanSetu.controller;

import com.example.SamadhanSetu.dto.RequestDto.EducationStoryRequestDTO;
import com.example.SamadhanSetu.service.EducationStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Education_stories")
@RequiredArgsConstructor
public class EducationStoryController {

    private final EducationStoryService storyService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadStory(@RequestBody EducationStoryRequestDTO dto) {
        return storyService.uploadStory(dto);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllStories() {
        return storyService.getAllStories();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getStoriesByCategory(@PathVariable String category) {
        return storyService.getStoriesByCategory(category);
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestStories() {
        return storyService.getLatestStories();
    }
}
