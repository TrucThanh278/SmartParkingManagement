package com.ou.pojo;

import com.ou.pojo.BookingInformation;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-10T22:24:58", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Report.class)
public class Report_ { 

    public static volatile SingularAttribute<Report, Float> rating;
    public static volatile SingularAttribute<Report, String> comment;
    public static volatile SingularAttribute<Report, Integer> id;
    public static volatile SingularAttribute<Report, BookingInformation> bookingInfoId;

}