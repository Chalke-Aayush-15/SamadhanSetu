package com.example.SamadhanSetu.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationStoryRequestDTO {

    private String title;
    private String category;
    private String content;

}
