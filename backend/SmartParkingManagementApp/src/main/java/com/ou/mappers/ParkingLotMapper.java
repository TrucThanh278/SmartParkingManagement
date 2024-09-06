package com.ou.mappers;

import com.ou.dto.response.ParkingLotResponseDTO;
import com.ou.pojo.ParkingLot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParkingLotMapper {
    @Mapping(target = "parkingSpotList", source = "parkingSpotList")
    ParkingLotResponseDTO toParkingLotReponse (ParkingLot parkingLot);
}
