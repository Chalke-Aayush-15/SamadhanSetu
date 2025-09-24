package com.example.SamadhanSetu.dao.Entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(schema="education_videos")
@Builder
public class EducationVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;
    private String description;
    private String videoUrl;

    private LocalDateTime uploadedAt;
}
