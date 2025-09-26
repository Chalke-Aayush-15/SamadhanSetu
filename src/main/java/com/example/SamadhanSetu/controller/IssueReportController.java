package com.example.SamadhanSetu.controller;

import com.example.SamadhanSetu.dao.Entity.IssueReport;
import com.example.SamadhanSetu.dto.RequestDto.IssueReportRequestDto;
import com.example.SamadhanSetu.dto.ResponseDto.ApiResponse;
import com.example.SamadhanSetu.dto.ResponseDto.IssueReportResponseDto;
import com.example.SamadhanSetu.mapper.IssueReportMapper;
import com.example.SamadhanSetu.service.impl.IssueReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")

public class IssueReportController {


    private final IssueReportService issueReportService;

    private final IssueReportMapper issueReportMapper;

    public IssueReportController(IssueReportService issueReportService, IssueReportMapper issueReportMapper) {
        this.issueReportService = issueReportService;
        this.issueReportMapper = issueReportMapper;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping(value = "/report-issue", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ApiResponse<IssueReportResponseDto>> reportIssue(
            @RequestPart("report") IssueReportRequestDto requestDto,
            @RequestPart(value = "media", required = false) MultipartFile mediaFile) {

        IssueReport createdIssue = issueReportService.createIssueReport(requestDto, mediaFile);
        IssueReportResponseDto responseDto = issueReportMapper.toResponseDto(createdIssue);

        ApiResponse<IssueReportResponseDto> response = new ApiResponse<>(
                true,
                "Issue reported successfully!",
                responseDto
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}