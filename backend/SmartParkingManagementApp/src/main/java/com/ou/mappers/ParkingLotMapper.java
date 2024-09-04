package com.ou.mappers;

import com.ou.dto.response.ParkingLotResponseDTO;
import com.ou.pojo.ParkingLot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingLotMapper {
    ParkingLotResponseDTO toParkingLotReponse (ParkingLot parkingLot);
}
