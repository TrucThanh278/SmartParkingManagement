/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.ou.smartparkingmanagementdemo;

import com.ou.pojo.ParkingLot;
import com.ou.pojo.ParkingSpot;
import com.ou.repository.impl.ParkingLotImpl;
import com.ou.repository.impl.ParkingSpotImpl;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trucn
 */
public class SmartParkingManagementDemo {

    public static void main(String[] args) {
        //Tao moi bai do xe
//        ParkingLotImpl s = new ParkingLotImpl();
//        ParkingLot p = s.createParkingLot("Parking Lot A", "Do Xuan Hop", 150, 3.5f, "Tuyet voi", LocalDateTime.of(2024, 8, 11, 8, 0), LocalDateTime.of(2024, 8, 11, 22, 0));
//        System.out.println(">>>> Bai do xe moi: " + p.getStartTime());
        
        // Lay danh sach bai do xe
//        ParkingLotImpl s = new ParkingLotImpl();
//        List<ParkingLot> rs = s.getParkingLots();
//        rs.forEach(r -> System.out.println(r.getName()));

        //Lay thong tin chi tiet bai do xe
//        ParkingLotImpl s = new ParkingLotImpl();
//        ParkingLot p = s.getParkingLotDetail(1);
//        System.out.println(">>> thong tin chi tiet bai do xe: " + p.toString());

        //Tao bai do xe moi
//        ParkingLotImpl s = new ParkingLotImpl();
//        ParkingLot p = s.updateParkingLotInfo((long) 1);
//        System.out.println(">>> thong tin chi tiet bai do xe: " + p.toString());

        //Cap nhat bai do xe
//        ParkingLot parkingLot = new ParkingLot();
//        parkingLot.setId(1); // ID của parking lot
//        parkingLot.setName("Downtown Parking"); // Tên của parking lot
//        parkingLot.setAddress("123 Main St, Cityville"); // Địa chỉ
//        parkingLot.setTotalSpots(150); // Tổng số chỗ đậu xe
//        parkingLot.setPricePerHour(5.0f); // Giá đậu xe theo giờ
//        parkingLot.setDescription("Spacious parking lot near downtown with easy access to public transportation."); // Mô tả
////        parkingLot.setStartTime(LocalDateTime.of(2024, 8, 1, 6, 0)); // Thời gian mở cửa (6:00 AM)
////        parkingLot.setEndTime(LocalDateTime.of(2024, 8, 1, 22, 0)); // Thời gian đóng cửa (10:00 PM)
//        
//        ParkingLotImpl s = new ParkingLotImpl();
//        ParkingLot p = s.updateParkingLotInfo((long) 1, parkingLot);
//        System.out.println(">>>>> Updated Parkinglot: "+ p.toString());
        
        
//        ParkingLot p1 = s.getParkingLotDetail((long) 1);
//        System.out.println(">>> thong tin chi tiet bai do xe: " + p1.toString());
        
        //Xoa bai do xe


        // Tim danh sach bai giu xe theo ten
//       Map<String, String> params = new HashMap<>();
//       params.put("q", "Bai");
//       ParkingLotImpl s = new ParkingLotImpl();
//       List<ParkingLot> rs = s.getParkingLotsByName(params);
//       rs.forEach(r -> System.out.printf("%s\n", r.getName()));


//        Lay danh sach cho do xe theo khoa ngoai
//        ParkingSpotImpl s = new ParkingSpotImpl();
//        List<ParkingSpot> rs = s.getParkingSpotsWithParkingLot(1);
//        rs.forEach(r -> System.out.printf("%s - %s\n", r.getSpotNumber(), r.getParkingLotId().getName()));
    }
}
