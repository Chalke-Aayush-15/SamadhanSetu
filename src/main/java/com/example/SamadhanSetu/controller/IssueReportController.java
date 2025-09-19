package com.example.SamadhanSetu.controller;

import com.example.SamadhanSetu.dao.Entity.IssueReport;
import com.example.SamadhanSetu.dto.ApiResponse;
import com.example.SamadhanSetu.dto.IssueReportDto;
import com.example.SamadhanSetu.service.IssueReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/issue_report")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IssueReportController {

    @Autowired
    private IssueReportService issueReportService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<IssueReport>> registerIssue(
            @RequestPart("data") String data,
            @RequestPart(value = "photo", required = false) MultipartFile photo) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        IssueReportDto dto = mapper.readValue(data, IssueReportDto.class);

        IssueReport savedReport = issueReportService.registerIssue(dto, photo);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Issue reported successfully ✅", savedReport)
        );


    }
}