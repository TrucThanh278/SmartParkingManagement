package com.ou.pojo;

import com.ou.pojo.ParkingSpot;
import com.ou.pojo.Report;
import com.ou.pojo.Vehicle;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-10T22:24:58", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(BookingInformation.class)
public class BookingInformation_ { 

    public static volatile SingularAttribute<BookingInformation, ParkingSpot> parkingSpotId;
    public static volatile SingularAttribute<BookingInformation, Report> report;
    public static volatile SingularAttribute<BookingInformation, Date> startTime;
    public static volatile SingularAttribute<BookingInformation, Integer> id;
    public static volatile SingularAttribute<BookingInformation, Date> endTime;
    public static volatile SingularAttribute<BookingInformation, Vehicle> vehicleId;
    public static volatile SingularAttribute<BookingInformation, Boolean> paymentStatus;

}