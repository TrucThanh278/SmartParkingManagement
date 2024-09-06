package com.ou.controllers;

import com.ou.dto.request.ReportRequestDTO;
import com.ou.pojo.Report;
import com.ou.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ApiReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity<Report> addReport(@RequestBody ReportRequestDTO dtoReportRequest) {
        Report report = reportService.addReport(dtoReportRequest);
        return ResponseEntity.ok(report);
    }
}
