package com.ou.pojo;

import com.ou.pojo.User;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-08-30T23:14:38")
@StaticMetamodel(VerificationToken.class)
public class VerificationToken_ { 

    public static volatile SingularAttribute<VerificationToken, LocalDateTime> expiryDate;
    public static volatile SingularAttribute<VerificationToken, Long> id;
    public static volatile SingularAttribute<VerificationToken, User> user;
    public static volatile SingularAttribute<VerificationToken, String> token;

}