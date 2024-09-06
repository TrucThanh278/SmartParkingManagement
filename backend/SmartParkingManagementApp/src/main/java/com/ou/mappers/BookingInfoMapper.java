package com.ou.mappers;

import com.ou.dto.request.BookingInfoRequestDTO;
import com.ou.dto.request.BookingInfoUpdateRequestDTO;
import com.ou.dto.response.BookingInformationResponseDTO;
import com.ou.pojo.BookingInformation;
import java.util.List;
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
    BookingInformation addBookingInfo(BookingInfoRequestDTO bookingInfoRequest);

    @Mappings({
        @Mapping(target = "startTime", source = "startTime"),
        @Mapping(target = "endTime", source = "endTime"),})
    void updateBookingInfoFromDto(BookingInfoUpdateRequestDTO dtoBookingInfoUpdateRequest,
            @MappingTarget BookingInformation bookingInformation);

    @Mappings({
        @Mapping(target = "startTime", source = "startTime"),
        @Mapping(target = "endTime", source = "endTime"),
        @Mapping(target = "parkingSpotId", source = "parkingSpotId.id"),
        @Mapping(target = "vehicleId", source = "vehicleId.id")
    })
    BookingInformationResponseDTO toDto(BookingInformation bookingInformation);

   
}
