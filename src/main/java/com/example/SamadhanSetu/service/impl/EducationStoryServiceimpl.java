package com.example.SamadhanSetu.service.impl;

import com.example.SamadhanSetu.Exceptions.ResourceNotFoundException;
import com.example.SamadhanSetu.dao.Entity.EducationStory;
import com.example.SamadhanSetu.dao.Repository.EducationStoryRepository;
import com.example.SamadhanSetu.dto.RequestDto.EducationStoryRequestDTO;
import com.example.SamadhanSetu.dto.ResponseDto.ApiResponse;
import com.example.SamadhanSetu.dto.ResponseDto.EducationStoryResponseDTO;
import com.example.SamadhanSetu.mapper.EducationStoryMapper;
import com.example.SamadhanSetu.service.EducationStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationStoryServiceimpl implements EducationStoryService {

    private final EducationStoryRepository storyRepo;

    @Override
    public ResponseEntity<?> uploadStory(EducationStoryRequestDTO dto) {
        EducationStory story = EducationStory.builder()
                .title(dto.getTitle())
                .category(dto.getCategory())
                .content(dto.getContent())
                .uploadedAt(LocalDateTime.now())
                .build();

        storyRepo.save(story);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Story uploaded successfully ✅", EducationStoryMapper.toDto(story))
        );
    }

    @Override
    public ResponseEntity<?> getAllStories() {
        List<EducationStoryResponseDTO> stories = storyRepo.findAll()
                .stream()
                .map(EducationStoryMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(true, "All stories fetched ✅", stories));
    }

    @Override
    public ResponseEntity<?> getStoriesByCategory(String category) {
        List<EducationStoryResponseDTO> stories = storyRepo.findByCategoryIgnoreCase(category)
                .stream()
                .map(EducationStoryMapper::toDto)
                .collect(Collectors.toList());

        if (stories.isEmpty()) {
            throw new ResourceNotFoundException("No stories found in category: " + category);
        }

        return ResponseEntity.ok(new ApiResponse<>(true, "Stories fetched ✅", stories));
    }

    @Override
    public ResponseEntity<?> getLatestStories() {
        List<EducationStoryResponseDTO> stories = storyRepo.findAll()
                .stream()
                .sorted((a, b) -> b.getUploadedAt().compareTo(a.getUploadedAt()))
                .limit(5)
                .map(EducationStoryMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(true, "Latest stories fetched ✅", stories));
    }



}
