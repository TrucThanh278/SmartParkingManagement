package com.ou.pojo;

import com.ou.pojo.Vehicle;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-09-17T22:55:24")
@StaticMetamodel(VehicleCategory.class)
public class VehicleCategory_ { 

    public static volatile SingularAttribute<VehicleCategory, String> name;
    public static volatile SingularAttribute<VehicleCategory, Integer> id;
    public static volatile ListAttribute<VehicleCategory, Vehicle> vehicleList;

}