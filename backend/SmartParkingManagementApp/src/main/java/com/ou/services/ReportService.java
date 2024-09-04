package com.ou.services;

import com.ou.dto.request.ReportRequestDTO;
import com.ou.pojo.Report;

public interface ReportService {
    Report addReport(ReportRequestDTO dtoReportRequest);
}
