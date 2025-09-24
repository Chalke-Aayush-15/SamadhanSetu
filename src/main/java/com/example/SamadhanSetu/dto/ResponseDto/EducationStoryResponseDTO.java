package com.example.SamadhanSetu.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationStoryResponseDTO {

    private Long id;
    private String title;
    private String category;
    private String content;
    private LocalDateTime uploadedAt;
}
