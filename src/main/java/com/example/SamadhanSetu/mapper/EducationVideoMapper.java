package com.example.SamadhanSetu.mapper;

import com.example.SamadhanSetu.dao.Entity.EducationVideo;
import com.example.SamadhanSetu.dto.ResponseDto.EducationVideoResponseDTO;

public class EducationVideoMapper {

    public static EducationVideoResponseDTO toDto(EducationVideo video) {
        EducationVideoResponseDTO dto = new EducationVideoResponseDTO();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setCategory(video.getCategory());
        dto.setDescription(video.getDescription());
        dto.setVideoUrl(video.getVideoUrl());
        dto.setUploadedAt(video.getUploadedAt());
        return dto;
    }
}
