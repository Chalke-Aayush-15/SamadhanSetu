package com.example.SamadhanSetu.service.impl;

import com.example.SamadhanSetu.dao.Entity.IssueReport;
import com.example.SamadhanSetu.dao.Repository.IssueReportRepository;
import com.example.SamadhanSetu.dto.RequestDto.IssueReportRequestDto;
import com.example.SamadhanSetu.mapper.IssueReportMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service

public class IssueReportService {

    @Autowired
    private IssueReportRepository issueReportRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private final IssueReportMapper issueReportMapper;

    public IssueReportService(IssueReportRepository issueReportRepository,
                              FileStorageService fileStorageService,
                              IssueReportMapper issueReportMapper) {
        this.issueReportRepository = issueReportRepository;
        this.fileStorageService = fileStorageService;
        this.issueReportMapper = issueReportMapper;
    }

    public IssueReport createIssueReport(IssueReportRequestDto requestDto, MultipartFile mediaFile) {
        // 1. Convert DTO to entity
        IssueReport issueReport = issueReportMapper.toEntity(requestDto);

        // 2. If a media file exists, store it and set the URL
        if (mediaFile != null && !mediaFile.isEmpty()) {
            String fileName = fileStorageService.storeFile(mediaFile);
            // In a real app, you would construct a full URL to serve the file
            String fileDownloadUri = "/uploads/" + fileName;
            issueReport.setMediaUrl(fileDownloadUri);
        }

        // 3. Save the entity to the database
        return issueReportRepository.save(issueReport);
    }

}
