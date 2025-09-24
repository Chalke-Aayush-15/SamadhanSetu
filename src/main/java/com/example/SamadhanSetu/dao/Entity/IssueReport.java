package com.example.SamadhanSetu.dao.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(schema = "issue_report")
public class IssueReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String locationType;

    @Column(nullable = false, length = 500)
    private String location; // Could store JSON string for coordinates

    private String name; // Optional

    private String phone; // Optional

    private String mediaUrl; // Path to the stored media file

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }


}
