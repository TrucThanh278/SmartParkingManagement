package com.ou.mappers;

import com.ou.dto.request.ReportRequestDTO;
import com.ou.pojo.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "bookingInfoId.id", source = "bookingInfoId")
    Report addReport(ReportRequestDTO dtoReportRequest);
}
