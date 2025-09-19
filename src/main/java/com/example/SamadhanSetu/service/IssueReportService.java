package com.example.SamadhanSetu.service;

import com.example.SamadhanSetu.dao.Entity.IssueReport;
import com.example.SamadhanSetu.dao.Repository.IssueReportRepository;
import com.example.SamadhanSetu.dto.IssueReportDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueReportService {

    @Autowired
    private IssueReportRepository issueReportRepository;

    @Autowired
    private GeoCodeService geoCodingService;

    @Autowired
    private FileStorageService fileStorageService;


    public IssueReport registerIssue(IssueReportDto dto, MultipartFile photo) {
        String photoUrl = null;
        if (photo != null && !photo.isEmpty()) {
            photoUrl = fileStorageService.saveFile(photo);
        }

        IssueReport report = new IssueReport();
        report.setCategory(dto.getCategory());
        report.setDescription(dto.getDescription());
        report.setPhotoUrl(photoUrl);

        // ✅ Location logic
        if (dto.getLatitude() != null && dto.getLongitude() != null) {
            // auto location fetched from device
            report.setLatitude(dto.getLatitude());
            report.setLongitude(dto.getLongitude());
            report.setLocationText("Auto-fetched from GPS"); // optional note
        } else {
            // manual entry
            report.setLocationText(dto.getLocationText());
        }

        report.setReporterName(dto.getReporterName());
        report.setReporterPhone(dto.getReporterPhone());
        report.setStatus("RECEIVED");
        report.setSubmittedAt(LocalDateTime.now());

        return issueReportRepository.save(report);
    }

}
