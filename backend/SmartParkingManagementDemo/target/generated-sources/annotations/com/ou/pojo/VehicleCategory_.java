package com.ou.pojo;

import com.ou.pojo.Vehicle;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-10T22:24:58", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(VehicleCategory.class)
public class VehicleCategory_ { 

    public static volatile SingularAttribute<VehicleCategory, String> name;
    public static volatile SingularAttribute<VehicleCategory, Integer> id;
    public static volatile ListAttribute<VehicleCategory, Vehicle> vehicleList;

}