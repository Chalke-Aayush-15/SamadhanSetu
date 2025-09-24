package com.example.SamadhanSetu.mapper;

import com.example.SamadhanSetu.dao.Entity.Event;
import com.example.SamadhanSetu.dto.EventCreateDTO;
import com.example.SamadhanSetu.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventDTO toDto(Event event);

    Event toEntity(EventCreateDTO eventCreateDTO);
}