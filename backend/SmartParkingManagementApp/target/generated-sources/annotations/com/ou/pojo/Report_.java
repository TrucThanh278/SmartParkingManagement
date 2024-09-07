package com.ou.pojo;

import com.ou.pojo.BookingInformation;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-09-07T04:00:42")
@StaticMetamodel(Report.class)
public class Report_ { 

    public static volatile SingularAttribute<Report, Float> rating;
    public static volatile SingularAttribute<Report, String> comment;
    public static volatile SingularAttribute<Report, Integer> id;
    public static volatile SingularAttribute<Report, BookingInformation> bookingInfoId;

}