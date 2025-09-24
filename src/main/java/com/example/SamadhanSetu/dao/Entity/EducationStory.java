package com.example.SamadhanSetu.dao.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "educational_stories")
@Getter
@Setter
@Builder
public class EducationStory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;

    @Column(length = 5000)
    private String content;

    private LocalDateTime uploadedAt;
}
