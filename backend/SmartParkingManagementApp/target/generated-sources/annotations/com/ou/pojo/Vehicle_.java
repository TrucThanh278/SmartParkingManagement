package com.ou.pojo;

import com.ou.pojo.BookingInformation;
import com.ou.pojo.User;
import com.ou.pojo.VehicleCategory;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-09-06T22:44:06")
@StaticMetamodel(Vehicle.class)
public class Vehicle_ { 

    public static volatile ListAttribute<Vehicle, BookingInformation> bookingInformationList;
    public static volatile SingularAttribute<Vehicle, VehicleCategory> vehicleCategoryId;
    public static volatile SingularAttribute<Vehicle, Integer> id;
    public static volatile SingularAttribute<Vehicle, String> plateNumber;
    public static volatile SingularAttribute<Vehicle, User> userId;

}