package com.ou.pojo;

import com.ou.pojo.ParkingSpot;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-08-27T23:09:30")
@StaticMetamodel(ParkingLot.class)
public class ParkingLot_ { 

    public static volatile SingularAttribute<ParkingLot, String> address;
    public static volatile SingularAttribute<ParkingLot, Integer> totalSpots;
    public static volatile SingularAttribute<ParkingLot, String> name;
    public static volatile SingularAttribute<ParkingLot, Float> pricePerHour;
    public static volatile SingularAttribute<ParkingLot, String> description;
    public static volatile SingularAttribute<ParkingLot, Date> startTime;
    public static volatile SingularAttribute<ParkingLot, Integer> id;
    public static volatile SingularAttribute<ParkingLot, Date> endTime;
    public static volatile SetAttribute<ParkingLot, ParkingSpot> parkingSpotSet;

}