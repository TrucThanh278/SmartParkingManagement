package com.ou.mappers;

import com.ou.dto.response.ParkingLotResponseDTO;
import com.ou.dto.response.ParkingSpotDTO;
import com.ou.pojo.ParkingLot;
import com.ou.pojo.ParkingSpot;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-06T22:44:07+0700",
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

        parkingLotResponseDTO.parkingSpotList( parkingSpotListToParkingSpotDTOList( parkingLot.getParkingSpotList() ) );
        parkingLotResponseDTO.id( parkingLot.getId() );
        parkingLotResponseDTO.name( parkingLot.getName() );
        parkingLotResponseDTO.address( parkingLot.getAddress() );
        parkingLotResponseDTO.totalSpots( parkingLot.getTotalSpots() );
        parkingLotResponseDTO.pricePerHour( parkingLot.getPricePerHour() );
        parkingLotResponseDTO.description( parkingLot.getDescription() );
        parkingLotResponseDTO.startTime( parkingLot.getStartTime() );
        parkingLotResponseDTO.endTime( parkingLot.getEndTime() );

        return parkingLotResponseDTO.build();
    }

    protected ParkingSpotDTO parkingSpotToParkingSpotDTO(ParkingSpot parkingSpot) {
        if ( parkingSpot == null ) {
            return null;
        }

        ParkingSpotDTO.ParkingSpotDTOBuilder parkingSpotDTO = ParkingSpotDTO.builder();

        parkingSpotDTO.id( parkingSpot.getId() );
        parkingSpotDTO.spotNumber( parkingSpot.getSpotNumber() );

        return parkingSpotDTO.build();
    }

    protected List<ParkingSpotDTO> parkingSpotListToParkingSpotDTOList(List<ParkingSpot> list) {
        if ( list == null ) {
            return null;
        }

        List<ParkingSpotDTO> list1 = new ArrayList<ParkingSpotDTO>( list.size() );
        for ( ParkingSpot parkingSpot : list ) {
            list1.add( parkingSpotToParkingSpotDTO( parkingSpot ) );
        }

        return list1;
    }
}
