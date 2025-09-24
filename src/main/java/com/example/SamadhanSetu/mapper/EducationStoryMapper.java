package com.example.SamadhanSetu.mapper;

import com.example.SamadhanSetu.dao.Entity.EducationStory;
import com.example.SamadhanSetu.dto.ResponseDto.EducationStoryResponseDTO;
import org.springframework.stereotype.Component;

@Component

public class EducationStoryMapper {
    public static EducationStoryResponseDTO toDto(EducationStory story) {
        EducationStoryResponseDTO dto = new EducationStoryResponseDTO();
        dto.setId(story.getId());
        dto.setTitle(story.getTitle());
        dto.setCategory(story.getCategory());
        dto.setContent(story.getContent());
        dto.setUploadedAt(story.getUploadedAt());
        return dto;
    }
}
