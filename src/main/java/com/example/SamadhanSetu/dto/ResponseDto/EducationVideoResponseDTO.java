package com.example.SamadhanSetu.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationVideoResponseDTO {

    private Long id;
    private String title;
    private String category;
    private String description;
    private String videoUrl;
    private LocalDateTime uploadedAt;
}
