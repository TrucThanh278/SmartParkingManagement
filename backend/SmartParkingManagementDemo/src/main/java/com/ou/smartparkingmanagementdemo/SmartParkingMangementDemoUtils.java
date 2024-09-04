/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.smartparkingmanagementdemo;

import com.ou.pojo.BookingInformation;
import com.ou.pojo.ParkingLot;
import com.ou.pojo.ParkingSpot;
import com.ou.pojo.Report;
import com.ou.pojo.Role;
import com.ou.pojo.User;
import com.ou.pojo.Vehicle;
import com.ou.pojo.VehicleCategory;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author trucn
 */
public class SmartParkingMangementDemoUtils {
    private static final SessionFactory factory;
    
    static {
        Configuration conf = new Configuration();
        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "Vu0932868903");
        props.put(Environment.URL, "jdbc:mysql://localhost/parkingmanagement");
        props.put(Environment.SHOW_SQL, true);

        conf.setProperties(props);
        conf.addAnnotatedClass(ParkingLot.class);
        conf.addAnnotatedClass(ParkingSpot.class);
        conf.addAnnotatedClass(BookingInformation.class);
        conf.addAnnotatedClass(Vehicle.class);
        conf.addAnnotatedClass(VehicleCategory.class);
        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(Report.class);
        conf.addAnnotatedClass(Role.class);



        
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                                                applySettings(conf.getProperties()).build();
        
        factory = conf.buildSessionFactory(serviceRegistry);
    }

    /**
     * @return the factory
     */
    public static SessionFactory getFactory() {
        return factory;
    }
}
