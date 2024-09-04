package com.ou.mappers;

import com.ou.dto.response.ParkingLotResponseDTO;
import com.ou.pojo.ParkingLot;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-05T01:17:07+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class ParkingLotMapperImpl implements ParkingLotMapper {

    @Override
    public ParkingLotResponseDTO toParkingLotReponse(ParkingLot parkingLot) {
        if ( parkingLot == null ) {
            return null;
        }

        ParkingLotResponseDTO.ParkingLotResponseDTOBuilder parkingLotResponseDTO = ParkingLotResponseDTO.builder();

        parkingLotResponseDTO.id( parkingLot.getId() );
        parkingLotResponseDTO.name( parkingLot.getName() );
        parkingLotResponseDTO.address( parkingLot.getAddress() );
        parkingLotResponseDTO.pricePerHour( parkingLot.getPricePerHour() );
        parkingLotResponseDTO.description( parkingLot.getDescription() );
        parkingLotResponseDTO.startTime( parkingLot.getStartTime() );
        parkingLotResponseDTO.endTime( parkingLot.getEndTime() );

        return parkingLotResponseDTO.build();
    }
}
