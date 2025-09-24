package com.example.SamadhanSetu.service;

import com.example.SamadhanSetu.dto.RequestDto.EducationVideoRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface EducationVideoService {
    ResponseEntity<?> uploadVideo(EducationVideoRequestDTO dto, MultipartFile file);
    ResponseEntity<?> getAllVideos();
    ResponseEntity<?> getVideosByCategory(String category);
    ResponseEntity<?> getLatestVideos();

}
