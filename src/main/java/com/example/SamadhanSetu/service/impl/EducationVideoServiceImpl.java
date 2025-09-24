package com.example.SamadhanSetu.service.impl;

import com.example.SamadhanSetu.Exceptions.ResourceNotFoundException;
import com.example.SamadhanSetu.dao.Entity.EducationVideo;
import com.example.SamadhanSetu.dao.Repository.EducationVideoRepository;
import com.example.SamadhanSetu.dto.RequestDto.EducationVideoRequestDTO;
import com.example.SamadhanSetu.dto.ResponseDto.ApiResponse;
import com.example.SamadhanSetu.dto.ResponseDto.EducationVideoResponseDTO;
import com.example.SamadhanSetu.mapper.EducationVideoMapper;
import com.example.SamadhanSetu.service.EducationVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationVideoServiceImpl implements EducationVideoService {

    private final EducationVideoRepository videoRepo;

    @Override
    public ResponseEntity<?> uploadVideo(EducationVideoRequestDTO dto, MultipartFile file) {
        String fileUrl = (file != null) ? "/videos/" + file.getOriginalFilename() : null;

        EducationVideo video = EducationVideo.builder()
                .title(dto.getTitle())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .videoUrl(fileUrl)
                .uploadedAt(LocalDateTime.now())
                .build();

        videoRepo.save(video);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Video uploaded successfully ✅", EducationVideoMapper.toDto(video))
        );
    }

    @Override
    public ResponseEntity<?> getAllVideos() {
        List<EducationVideoResponseDTO> videos = videoRepo.findAll()
                .stream()
                .map(EducationVideoMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(true, "All videos fetched ✅", videos));
    }

    @Override
    public ResponseEntity<?> getVideosByCategory(String category) {
        List<EducationVideoResponseDTO> videos = videoRepo.findByCategoryIgnoreCase(category)
                .stream()
                .map(EducationVideoMapper::toDto)
                .collect(Collectors.toList());

        if (videos.isEmpty()) {
            throw new ResourceNotFoundException("No videos found in category: " + category);
        }

        return ResponseEntity.ok(new ApiResponse<>(true, "Videos fetched ✅", videos));
    }

    @Override
    public ResponseEntity<?> getLatestVideos() {
        List<EducationVideoResponseDTO> videos = videoRepo.findAll()
                .stream()
                .sorted((a, b) -> b.getUploadedAt().compareTo(a.getUploadedAt()))
                .limit(5)
                .map(EducationVideoMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(true, "Latest videos fetched ✅", videos));
    }

}
