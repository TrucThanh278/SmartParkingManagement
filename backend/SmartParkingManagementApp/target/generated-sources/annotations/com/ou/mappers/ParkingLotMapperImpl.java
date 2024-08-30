package com.ou.mappers;

import com.ou.dto.response.DTOParkingLotResponse;
import com.ou.pojo.ParkingLot;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-30T23:14:39+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class ParkingLotMapperImpl implements ParkingLotMapper {

    @Override
    public DTOParkingLotResponse toParkingLotReponse(ParkingLot parkingLot) {
        if ( parkingLot == null ) {
            return null;
        }

        DTOParkingLotResponse.DTOParkingLotResponseBuilder dTOParkingLotResponse = DTOParkingLotResponse.builder();

        dTOParkingLotResponse.id( parkingLot.getId() );
        dTOParkingLotResponse.name( parkingLot.getName() );
        dTOParkingLotResponse.address( parkingLot.getAddress() );
        dTOParkingLotResponse.pricePerHour( parkingLot.getPricePerHour() );
        dTOParkingLotResponse.description( parkingLot.getDescription() );
        dTOParkingLotResponse.startTime( parkingLot.getStartTime() );
        dTOParkingLotResponse.endTime( parkingLot.getEndTime() );

        return dTOParkingLotResponse.build();
    }
}
