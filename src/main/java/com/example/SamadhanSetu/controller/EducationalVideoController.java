package com.example.SamadhanSetu.controller;

import com.example.SamadhanSetu.dto.RequestDto.EducationVideoRequestDTO;
import com.example.SamadhanSetu.dto.ResponseDto.ApiResponse;
import com.example.SamadhanSetu.service.EducationVideoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/Education_Video")
@RequiredArgsConstructor
public class EducationalVideoController {

    private final EducationVideoService videoService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadVideo(
            @RequestPart("data") String data,
            @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        EducationVideoRequestDTO dto = mapper.readValue(data, EducationVideoRequestDTO.class);

        return videoService.uploadVideo(dto, file);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getVideosByCategory(@PathVariable String category) {
        return videoService.getVideosByCategory(category);
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestVideos() {
        return videoService.getLatestVideos();
    }

}
