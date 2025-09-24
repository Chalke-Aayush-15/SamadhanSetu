package com.example.SamadhanSetu.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueReportResponseDto {

    private Long id;
    private String description;
    private String category;
    private String locationType;
    private String location;
    private String name;
    private String phone;
    private String mediaUrl;
    private LocalDateTime createdAt;
}