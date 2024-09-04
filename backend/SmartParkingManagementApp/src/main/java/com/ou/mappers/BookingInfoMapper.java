package com.ou.mappers;

import com.ou.dto.request.DTOBookingInfoRequest;
import com.ou.dto.request.DTOBookingInfoUpdateRequest;
import com.ou.dto.response.DTOBookingInformationResponse;
import com.ou.pojo.BookingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")

public interface BookingInfoMapper {

    @Mappings({
        @Mapping(target = "startTime", source = "startTime"),
        @Mapping(target = "endTime", source = "endTime"),
        @Mapping(target = "parkingSpotId.id", source = "parkingSpotId"),
        @Mapping(target = "vehicleId.id", source = "vehicleId"),
        @Mapping(target = "paymentStatus", constant = "false")
    })
    BookingInformation addBookingInfo(DTOBookingInfoRequest bookingInfoRequest);

    @Mappings({
        @Mapping(target = "startTime", source = "startTime"),
        @Mapping(target = "endTime", source = "endTime"),})
    void updateBookingInfoFromDto(DTOBookingInfoUpdateRequest dtoBookingInfoUpdateRequest,
            @MappingTarget BookingInformation bookingInformation);

    @Mappings({
        @Mapping(target = "startTime", source = "startTime"),
        @Mapping(target = "endTime", source = "endTime"),
        @Mapping(target = "parkingSpotId", source = "parkingSpotId.id"), 
        @Mapping(target = "vehicleId", source = "vehicleId.id") 
    })
    DTOBookingInformationResponse toDto(BookingInformation bookingInformation);

}
