package com.ou.mappers;

import com.ou.dto.response.DTOParkingLotResponse;
import com.ou.pojo.ParkingLot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingLotMapper {
    DTOParkingLotResponse toParkingLotReponse (ParkingLot parkingLot);
}
