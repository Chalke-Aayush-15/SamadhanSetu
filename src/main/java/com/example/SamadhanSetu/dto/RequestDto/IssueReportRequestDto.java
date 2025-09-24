package com.example.SamadhanSetu.dto.RequestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class IssueReportRequestDto {

    private String description;
    private String category;
    private String locationType;
    private String location;
    private String name;
    private String phone;
//    @NotBlank(message = "Category is required")
//    private String category;
//
//    @NotBlank(message = "Description is required")
//    private String description;
//
//    private MultipartFile photo; // image upload
//
//    private String locationText;
//    private Double latitude;
//    private Double longitude;
//
//    private String reporterName;   // optional
//    private String reporterPhone;


}
