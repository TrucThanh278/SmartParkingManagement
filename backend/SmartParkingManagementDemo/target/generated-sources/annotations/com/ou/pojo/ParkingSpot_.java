package com.ou.pojo;

import com.ou.pojo.BookingInformation;
import com.ou.pojo.ParkingLot;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-10T22:24:58", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(ParkingSpot.class)
public class ParkingSpot_ { 

    public static volatile ListAttribute<ParkingSpot, BookingInformation> bookingInformationList;
    public static volatile SingularAttribute<ParkingSpot, String> spotNumber;
    public static volatile SingularAttribute<ParkingSpot, ParkingLot> parkingLotId;
    public static volatile SingularAttribute<ParkingSpot, Integer> id;
    public static volatile SingularAttribute<ParkingSpot, Boolean> status;

}