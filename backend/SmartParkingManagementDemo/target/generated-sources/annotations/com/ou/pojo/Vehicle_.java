package com.ou.pojo;

import com.ou.pojo.BookingInformation;
import com.ou.pojo.User;
import com.ou.pojo.VehicleCategory;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-10T22:24:58", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Vehicle.class)
public class Vehicle_ { 

    public static volatile ListAttribute<Vehicle, BookingInformation> bookingInformationList;
    public static volatile SingularAttribute<Vehicle, VehicleCategory> vehicleCategoryId;
    public static volatile SingularAttribute<Vehicle, Integer> id;
    public static volatile SingularAttribute<Vehicle, String> plateNumber;
    public static volatile SingularAttribute<Vehicle, User> userId;

}