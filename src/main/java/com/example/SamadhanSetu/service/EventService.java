package com.example.SamadhanSetu.service;

import com.example.SamadhanSetu.Exceptions.ResourceNotFoundException;
import com.example.SamadhanSetu.dao.Entity.Event;
import com.example.SamadhanSetu.dao.Entity.EventStatus;
import com.example.SamadhanSetu.dao.Repository.EventRepository;
import com.example.SamadhanSetu.dto.EventCreateDTO;
import com.example.SamadhanSetu.dto.EventDTO;
import com.example.SamadhanSetu.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toDto)
                .collect(Collectors.toList());
    }

    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
        return eventMapper.toDto(event);
    }

    // admin api
    public EventDTO createEvent(EventCreateDTO eventCreateDTO) {
        // Set default status if the incoming DTO does not provide one
        if (eventCreateDTO.getStatus() == null) {
            eventCreateDTO.setStatus(EventStatus.SCHEDULE);
        }

        // Now, map the DTO to the entity. The entity's status will now be set.
        Event event = eventMapper.toEntity(eventCreateDTO);

        // Set current attendees from the DTO, defaulting to 0 if not provided
        if (eventCreateDTO.getCurrentAttendees() == null) {
            event.setCurrentAttendees(0);
        }

        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDto(savedEvent);
    }

    public EventDTO updateEvent(Long id, EventCreateDTO eventCreateDTO) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));

        // Update fields from DTO
        existingEvent.setTitle(eventCreateDTO.getTitle());
        existingEvent.setDate(eventCreateDTO.getDate());
        existingEvent.setTime(eventCreateDTO.getTime());
        existingEvent.setLocation(eventCreateDTO.getLocation());
        existingEvent.setType(eventCreateDTO.getType());
        existingEvent.setDescription(eventCreateDTO.getDescription());
        existingEvent.setImageUrl(eventCreateDTO.getImageUrl());
        existingEvent.setMaxAttendees(eventCreateDTO.getMaxAttendees());
        existingEvent.setCurrentAttendees(eventCreateDTO.getCurrentAttendees());
        existingEvent.setStatus(eventCreateDTO.getStatus());

        Event updatedEvent = eventRepository.save(existingEvent);
        return eventMapper.toDto(updatedEvent);
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }
}