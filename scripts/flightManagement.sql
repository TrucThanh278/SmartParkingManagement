-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: flightManagement
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking_information`
--

DROP TABLE IF EXISTS `booking_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_information` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parking_spot_id` int DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `payment_status` tinyint(1) DEFAULT NULL,
  `vehicle_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_booking` (`start_time`,`end_time`,`parking_spot_id`),
  KEY `parking_spot_id` (`parking_spot_id`),
  KEY `vehicle_id` (`vehicle_id`),
  CONSTRAINT `booking_information_ibfk_1` FOREIGN KEY (`parking_spot_id`) REFERENCES `parking_spot` (`id`),
  CONSTRAINT `booking_information_ibfk_2` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_information`
--

LOCK TABLES `booking_information` WRITE;
/*!40000 ALTER TABLE `booking_information` DISABLE KEYS */;
INSERT INTO `booking_information` VALUES (1,1,'2024-08-10 09:00:00','2024-08-10 11:00:00',1,1),(2,2,'2024-08-10 10:00:00','2024-08-10 12:00:00',0,2),(3,3,'2024-08-10 11:00:00','2024-08-10 13:00:00',1,3),(4,4,'2024-08-10 12:00:00','2024-08-10 14:00:00',1,4),(5,5,'2024-08-10 13:00:00','2024-08-10 15:00:00',0,5),(42,1,'2024-09-13 10:38:00','2024-09-13 11:38:00',0,6),(43,1,'2024-09-13 14:19:00','2024-09-13 15:19:00',0,6),(44,2,'2024-09-13 14:23:00','2024-09-13 15:23:00',0,6),(45,3,'2024-09-13 14:32:00','2024-09-13 15:32:00',0,6),(46,3,'2024-09-13 16:35:00','2024-09-13 06:35:00',0,6),(47,3,'2024-09-13 08:52:00','2024-09-13 09:52:00',0,6);
/*!40000 ALTER TABLE `booking_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_lot`
--

DROP TABLE IF EXISTS `parking_lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking_lot` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `total_spots` int DEFAULT NULL,
  `price_per_hour` float DEFAULT NULL,
  `description` text,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_lot`
--

LOCK TABLES `parking_lot` WRITE;
/*!40000 ALTER TABLE `parking_lot` DISABLE KEYS */;
INSERT INTO `parking_lot` VALUES (1,'Central Parking','123 Main St',100,5.5,'Downtown parking lot','2024-08-10 06:00:00','2024-08-10 22:00:00'),(2,'Airport Parking','456 Airport Rd',200,10,'24/7 airport parking','2024-08-10 00:00:00','2024-08-10 23:59:59'),(3,'Mall Parking','789 Shopping Ave',300,3,'Free for first 2 hours','2024-08-10 09:00:00','2024-08-10 21:00:00'),(4,'Hotel Parking','101 Luxury Blvd',50,15,'Valet service available','2024-08-10 00:00:00','2024-08-10 23:59:59'),(5,'Park & Ride','202 Commuter Way',150,2.5,'Convenient for public transport','2024-08-10 05:00:00','2024-08-10 23:00:00'),(7,'aaaaa','aaaaaaa',15,3.555,'asfdsafsafsafsafsadf','2024-09-02 17:38:00','2024-09-02 18:39:00'),(9,'bbbbbbb','bbbbb',40,3.555,'asfdsafsafsafsafsadf','2024-09-04 21:56:00','2024-09-05 21:56:00');
/*!40000 ALTER TABLE `parking_lot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_spot`
--

DROP TABLE IF EXISTS `parking_spot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking_spot` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parking_lot_id` int DEFAULT NULL,
  `spot_number` varchar(10) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parking_lot_id` (`parking_lot_id`),
  CONSTRAINT `parking_spot_ibfk_1` FOREIGN KEY (`parking_lot_id`) REFERENCES `parking_lot` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_spot`
--

LOCK TABLES `parking_spot` WRITE;
/*!40000 ALTER TABLE `parking_spot` DISABLE KEYS */;
INSERT INTO `parking_spot` VALUES (1,1,'A1',0),(2,1,'A2',0),(3,1,'A3',0),(4,1,'A4',0),(5,1,'A5',0),(6,2,'B1',1),(7,2,'B2',1),(8,2,'B3',0),(9,2,'B4',1),(10,2,'B5',1),(14,7,'AA1',0),(15,7,'AA2',0),(16,7,'AA3',0),(17,7,'AA4',0),(18,7,'AA5',0),(19,7,'AA6',0),(20,7,'AA7',0),(21,7,'AA8',0),(22,7,'AA9',0),(23,7,'AA10',0),(24,7,'AA11',0),(25,7,'AA12',0),(26,7,'AA13',0),(27,7,'AA14',0),(28,7,'AA15',0),(59,9,'BB1',0),(60,9,'BB2',0),(61,9,'BB3',0),(62,9,'BB4',0),(63,9,'BB5',0),(64,9,'BB6',0),(65,9,'BB7',0),(66,9,'BB8',0),(67,9,'BB9',0),(68,9,'BB10',0),(69,9,'BB11',0),(70,9,'BB12',0),(71,9,'BB13',0),(72,9,'BB14',0),(73,9,'BB15',0),(74,9,'BB16',0),(75,9,'BB17',0),(76,9,'BB18',0),(77,9,'BB19',0),(78,9,'BB20',0),(79,9,'BB21',0),(80,9,'BB22',0),(81,9,'BB23',0),(82,9,'BB24',0),(83,9,'BB25',0),(84,9,'BB26',0),(85,9,'BB27',0),(86,9,'BB28',0),(87,9,'BB29',0),(88,9,'BB30',0),(89,9,'BB31',0),(90,9,'BB32',0),(91,9,'BB33',0),(92,9,'BB34',0),(93,9,'BB35',0),(94,9,'BB36',0),(95,9,'BB37',0),(96,9,'BB38',0),(97,9,'BB39',0),(98,9,'BB40',0);
/*!40000 ALTER TABLE `parking_spot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booking_info_id` int DEFAULT NULL,
  `rating` float DEFAULT NULL,
  `comment` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `booking_info_id` (`booking_info_id`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`booking_info_id`) REFERENCES `booking_information` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,1,4.5,'Great service!'),(2,2,3,'Average experience'),(3,3,5,'Excellent parking facilities'),(4,4,2.5,'Limited spaces available'),(5,5,4,'Good location, a bit pricey');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` enum('ROLE_USER','ROLE_ADMIN') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `last_name` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Smith','John','johnsmith','john@email.com','$2a$12$rbg0Lc38PjBLQI2ZjwiW/u22aBOUQLxMDq3C/DwMjZSyD6sqV1qm2','123 Oak St','555-1234','avatar1.jpg',1,0),(2,'Johnson','Emily','emilyj','emily@email.com','$2a$12$rbg0Lc38PjBLQI2ZjwiW/u22aBOUQLxMDq3C/DwMjZSyD6sqV1qm2','456 Elm St','555-5678','avatar2.jpg',1,0),(3,'Brown','Michael','mikebrown','michael@email.com','$2a$12$rbg0Lc38PjBLQI2ZjwiW/u22aBOUQLxMDq3C/DwMjZSyD6sqV1qm2','789 Pine St','555-9012','avatar3.jpg',2,1),(4,'Davis','Sarah','sarahd','sarah@email.com','$2a$12$rbg0Lc38PjBLQI2ZjwiW/u22aBOUQLxMDq3C/DwMjZSyD6sqV1qm2','101 Maple Ave','555-3456','avatar4.jpg',1,0),(5,'Wilson','David','davidw','david@email.com','$2a$12$rbg0Lc38PjBLQI2ZjwiW/u22aBOUQLxMDq3C/DwMjZSyD6sqV1qm2','202 Cedar Ln','555-7890','avatar5.jpg',1,0),(6,'Thanh','Vu','thanhvu','thanhvu@gmail.com','$2a$12$rbg0Lc38PjBLQI2ZjwiW/u22aBOUQLxMDq3C/DwMjZSyD6sqV1qm2','adasd','12312','avatar6.jpg',2,0),(7,'Doe','John','johndoe','john.doe@example.com','$2a$12$rbg0Lc38PjBLQI2ZjwiW/u22aBOUQLxMDq3C/DwMjZSyD6sqV1qm2','123 Main St','0123456789','https://res.cloudinary.com/dsvodlq5d/image/upload/v1723910549/bvgpctzukgrkfqv6bm37.jpg',1,0),(8,'tuan','van','vantuan','vantuan@gmail.com','$2a$10$iPNi1W2HwesES1YFVXcAJ.O1x7xmZ3Ja6wgxXVaecgTsbFNxOhC8i','123 tran thi nhuong','0931284711','https://res.cloudinary.com/dsvodlq5d/image/upload/v1723910698/dwo1d3gbmpfmp6parrt1.jpg',1,0),(9,'Vũ','asdad','asda','thasdaanhvu080803@gmail.com','$2a$10$TtFAmQCnhi2wm0s6ZzvojOGNkuTUsZy10MxumUvYu05f8Z/7Vo2hy','asdsad','0931825490','https://res.cloudinary.com/dsvodlq5d/image/upload/v1723958928/xtoynabnuvvllvl9vvxx.png',1,0),(81,'Thanh','Vu','thanhvu2323','2151050561vu@ou.edu.vn','$2a$10$hHnkmAheqksAYXYUWaj6eOcbDEmsgmR49C.cNPFRr.d8obpQTBHty','113d asdasasdsa','4234123','http://res.cloudinary.com/dsvodlq5d/image/upload/v1724595271/erpmgwi8prejgqgiyfal.jpg',1,0),(82,'adsadsa','adasdas','thanhthanh123456','thanhthieasdsadt@gmail.com','$2a$10$QX3.Z3erJBmUIfqt.04hee9Qg.d0XJvv46YCbd0m/A8N06ArWV.tK','qweqewq','12321321','http://res.cloudinary.com/dsvodlq5d/image/upload/v1724596340/btvtnke83sqz6osx4zoo.jpg',1,0),(96,'Nguyen','Truc','trucnguyen','trucnguyendev2708@gmail.com','$2a$12$rbg0Lc38PjBLQI2ZjwiW/u22aBOUQLxMDq3C/DwMjZSyD6sqV1qm2','asdfgsafsafasfd','0365984219','http://res.cloudinary.com/djyggobeq/image/upload/v1725113827/lol1jfjz6idonhmiebbp.jpg',2,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vehicle_category_id` int DEFAULT NULL,
  `plate_number` varchar(20) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `plate_number` (`plate_number`),
  KEY `vehicle_category_id` (`vehicle_category_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`vehicle_category_id`) REFERENCES `vehicle_category` (`id`),
  CONSTRAINT `vehicle_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,1,'ABC123',1),(2,2,'XYZ789',2),(3,3,'DEF456',3),(4,4,'GHI789',4),(5,5,'JKL012',5),(6,1,'77C1',96);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_category`
--

DROP TABLE IF EXISTS `vehicle_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_category`
--

LOCK TABLES `vehicle_category` WRITE;
/*!40000 ALTER TABLE `vehicle_category` DISABLE KEYS */;
INSERT INTO `vehicle_category` VALUES (1,'Sedan'),(2,'SUV'),(3,'Truck'),(4,'Motorcycle'),(5,'Van');
/*!40000 ALTER TABLE `vehicle_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verificationtoken`
--

DROP TABLE IF EXISTS `verificationtoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verificationtoken` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `user_id` int NOT NULL,
  `expiry_date` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `verificationtoken_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verificationtoken`
--

LOCK TABLES `verificationtoken` WRITE;
/*!40000 ALTER TABLE `verificationtoken` DISABLE KEYS */;
INSERT INTO `verificationtoken` VALUES (1,'example-token-123',1,'2024-08-26 05:00:00'),(10,'15a7a8bd-3365-428a-abe9-2944b266d45b',81,'2024-08-26 14:14:32'),(11,'36796d20-41a4-491b-bbd4-b34ce8ac353a',82,'2024-08-26 14:32:21'),(15,'8b15b67a-9125-40f2-96fc-5136eae35fd4',96,'2024-09-01 14:17:07');
/*!40000 ALTER TABLE `verificationtoken` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-18 10:48:14
