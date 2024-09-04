package com.ou.pojo;

import com.ou.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-09-05T00:38:57")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile ListAttribute<Role, User> userList;
    public static volatile SingularAttribute<Role, String> name;
    public static volatile SingularAttribute<Role, Integer> id;

}