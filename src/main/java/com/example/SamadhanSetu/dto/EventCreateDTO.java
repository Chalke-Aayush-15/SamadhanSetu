package com.example.SamadhanSetu.dto;

import com.example.SamadhanSetu.dao.Entity.EventStatus;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventCreateDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Time is required")
    private LocalTime time;

    private String location;
    private String type;
    private String description;
    private String imageUrl;
    private Integer maxAttendees;
    private Integer currentAttendees; // New field to be set by the admin
    private EventStatus status; // New field
}
