package com.ou.pojo;

import com.ou.pojo.ParkingSpot;
import com.ou.pojo.Report;
import com.ou.pojo.Vehicle;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-09-06T22:27:42")
@StaticMetamodel(BookingInformation.class)
public class BookingInformation_ { 

    public static volatile SingularAttribute<BookingInformation, ParkingSpot> parkingSpotId;
    public static volatile SingularAttribute<BookingInformation, Report> report;
    public static volatile SingularAttribute<BookingInformation, LocalDateTime> startTime;
    public static volatile SingularAttribute<BookingInformation, Integer> id;
    public static volatile SingularAttribute<BookingInformation, LocalDateTime> endTime;
    public static volatile SingularAttribute<BookingInformation, Vehicle> vehicleId;
    public static volatile SingularAttribute<BookingInformation, Boolean> paymentStatus;

}