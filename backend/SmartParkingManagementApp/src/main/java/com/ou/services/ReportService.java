package com.ou.services;

import com.ou.dto.request.DTOReportRequest;
import com.ou.pojo.Report;

public interface ReportService {
    Report addReport(DTOReportRequest dtoReportRequest);
}
