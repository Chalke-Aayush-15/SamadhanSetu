package com.example.SamadhanSetu.mapper;

import com.example.SamadhanSetu.dao.Entity.IssueReport;
import com.example.SamadhanSetu.dto.RequestDto.IssueReportRequestDto;
import com.example.SamadhanSetu.dto.ResponseDto.IssueReportResponseDto;
import org.springframework.stereotype.Component;

@Component
public class IssueReportMapper {

    public IssueReport toEntity(IssueReportRequestDto dto) {
        if (dto == null) {
            return null;
        }
        IssueReport entity = new IssueReport();
        entity.setDescription(dto.getDescription());
        entity.setCategory(dto.getCategory());
        entity.setLocationType(dto.getLocationType());
        entity.setLocation(dto.getLocation());
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        return entity;
    }

    /**
     * Converts an IssueReport entity to a response DTO.
     * @param entity The IssueReport entity.
     * @return The response DTO.
     */
    public IssueReportResponseDto toResponseDto(IssueReport entity) {
        if (entity == null) {
            return null;
        }
        IssueReportResponseDto dto = new IssueReportResponseDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setCategory(entity.getCategory());
        dto.setLocationType(entity.getLocationType());
        dto.setLocation(entity.getLocation());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setMediaUrl(entity.getMediaUrl());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
