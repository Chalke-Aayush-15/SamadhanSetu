package com.example.SamadhanSetu.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationVideoRequestDTO {
    private String title;
    private String category;
    private String description;
}
