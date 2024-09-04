package com.ou.pojo;

import com.ou.pojo.ParkingSpot;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-09-02T18:01:48")
@StaticMetamodel(ParkingLot.class)
public class ParkingLot_ { 

    public static volatile SingularAttribute<ParkingLot, String> address;
    public static volatile SingularAttribute<ParkingLot, Integer> totalSpots;
    public static volatile SingularAttribute<ParkingLot, String> name;
    public static volatile SingularAttribute<ParkingLot, Float> pricePerHour;
    public static volatile SingularAttribute<ParkingLot, String> description;
    public static volatile ListAttribute<ParkingLot, ParkingSpot> parkingSpotList;
    public static volatile SingularAttribute<ParkingLot, LocalDateTime> startTime;
    public static volatile SingularAttribute<ParkingLot, Integer> id;
    public static volatile SingularAttribute<ParkingLot, LocalDateTime> endTime;

}