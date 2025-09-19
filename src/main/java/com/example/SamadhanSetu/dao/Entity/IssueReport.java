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

    private String category;

    @Column(length = 1000)
    private String description;

    private String photoUrl;

    private String locationText;
    private Double latitude;
    private Double longitude;

    private String reporterName;    // optional
    private String reporterPhone;   // optional

    private String status;

    private LocalDateTime submittedAt;


}
