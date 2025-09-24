package com.example.SamadhanSetu.dao.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "community_events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    private String location;

    private String type;

    @Column(length = 2000)
    private String description;

    private String imageUrl; // A URL to an image hosted externally

    private Integer maxAttendees;

    private Integer currentAttendees;

    @Enumerated(EnumType.STRING) // Maps enum to string
    private EventStatus status;
}