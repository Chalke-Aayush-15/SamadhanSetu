package com.example.SamadhanSetu.dto;

import com.example.SamadhanSetu.dao.Entity.EventStatus;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String type;
    private String description;
    private String imageUrl;
    private Integer maxAttendees;
    private Integer currentAttendees;
    private EventStatus status; // New field
}
