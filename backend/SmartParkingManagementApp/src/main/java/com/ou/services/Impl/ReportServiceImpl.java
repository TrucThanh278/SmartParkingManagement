package com.ou.services.Impl;

import com.ou.dto.request.ReportRequestDTO;
import com.ou.mappers.ReportMapper;
import com.ou.pojo.BookingInformation;
import com.ou.pojo.Report;
import com.ou.repositories.BookingInformationRepository;
import com.ou.repositories.ReportRepository;
import com.ou.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private BookingInformationRepository bookingInformationRepository;


    @Override
    public Report addReport(ReportRequestDTO dtoReportRequest) {
        Report report = reportMapper.addReport(dtoReportRequest);

        BookingInformation bookingInformation = bookingInformationRepository.findById(dtoReportRequest.getBookingInfoId());

        report.setBookingInfoId(bookingInformation);

        Report rpsave = reportRepository.save(report);

        return rpsave;
    }
}
