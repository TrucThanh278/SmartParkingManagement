package com.ou.pojo;

import com.ou.pojo.ParkingSpot;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-10T22:24:58", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(ParkingLot.class)
public class ParkingLot_ { 

    public static volatile SingularAttribute<ParkingLot, String> address;
    public static volatile SingularAttribute<ParkingLot, Integer> totalSpots;
    public static volatile SingularAttribute<ParkingLot, String> name;
    public static volatile SingularAttribute<ParkingLot, Float> pricePerHour;
    public static volatile SingularAttribute<ParkingLot, String> description;
    public static volatile ListAttribute<ParkingLot, ParkingSpot> parkingSpotList;
    public static volatile SingularAttribute<ParkingLot, Date> startTime;
    public static volatile SingularAttribute<ParkingLot, Integer> id;
    public static volatile SingularAttribute<ParkingLot, Date> endTime;

}