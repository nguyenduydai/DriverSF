CREATE DATABASE  IF NOT EXISTS `taixesf` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `taixesf`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: taixesf
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbluser`
--

DROP TABLE IF EXISTS `tbluser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbluser` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_phone` varchar(11) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_age` int(11) DEFAULT NULL,
  `user_sex` varchar(11) DEFAULT NULL,
  `user_email` varchar(45) DEFAULT NULL,
  `user_hometown` varchar(45) DEFAULT NULL,
  `user_image` varchar(45) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL,
  `user_area_id` int(11) DEFAULT NULL,
  `user_role_id` int(11) NOT NULL,
  `user_createddate` datetime DEFAULT NULL,
  `user_createdby` varchar(45) DEFAULT NULL,
  `user_modifieddate` datetime DEFAULT NULL,
  `user_modifiedby` varchar(45) DEFAULT NULL,
  `user_password` varchar(45) DEFAULT NULL,
  `user_salary` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_role_id_idx` (`user_role_id`),
  KEY `user_area_id_idx` (`user_area_id`),
  CONSTRAINT `user_area_id` FOREIGN KEY (`user_area_id`) REFERENCES `tblarea` (`area_id`),
  CONSTRAINT `user_role_id` FOREIGN KEY (`user_role_id`) REFERENCES `tblrole` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbluser`
--

LOCK TABLES `tbluser` WRITE;
/*!40000 ALTER TABLE `tbluser` DISABLE KEYS */;
INSERT INTO `tbluser` VALUES (1,'0865520508','Nguyễn Duy Đại',21,'Nam','nguyenduydai0@gmail.com','Nam Định','tx1.jpg',1,1,1,'2024-01-08 12:34:56',NULL,'2024-06-27 00:00:00',NULL,'c4ca4238a0b923820dcc509a6f75849b',4320000),(2,'0971234567','Chu Quốc Khánh',21,'Nam','chukhanh1@gmai.com','Bắc Giang','tx2.jpg',1,1,1,'2024-02-08 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',1215000),(3,'0378729548','Nguyễn Đức Anh',21,'Nam','ducanh2@gmai.com','Bắc Ninh','tx3.jpg',1,4,1,'2023-11-08 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',3244000),(4,'0672346567','Trần Văn Duy',20,'Nam','duytran3@gmail.com','Nam Định','tx4.jpg',1,7,1,'2023-06-08 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',2340000),(5,'0172343444','Phạm Việt Đức',20,'Nam','ducpham4@gmai.com','Ninh Bình','tx5.jpg',1,10,1,'2024-02-01 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',3500000),(6,'1','An Văn Anh',32,'Nữ','nguyenduydai5588@gmail.com','Nam Định','ad1.jpg',1,1,2,'2022-08-08 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',80000000),(7,'2','An Văn Nam',35,'Nam ','dainguyen123@gmail.com','Hà Nội','ad2.jpg',1,4,3,'2022-10-08 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',79000000),(8,'0271234354','Nguyễn Thị An',19,'Nữ','nguyen1@gmail.com','Hà Nội','tx6.jpg',1,4,1,'2023-12-12 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',1999000),(9,'3','An Dương Khang',43,'Nam','caadmin123@gmail.com','Hà Nam','ad3.jpg',1,7,4,'2022-09-08 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',85000000),(10,'4','An Thị Nga',45,'Nữ','nga123@gmail.com','Phú Thọ','ad4.jpg',1,10,5,'2022-01-08 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',99000000),(11,'0812135532','Nguyễn Thanh Thế',22,'Nam','thanhte2@gmail.com','Bắc Cạn','tx9.jpg',0,7,1,'2023-07-08 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',6000000),(12,'0913257124','Phan Thanh Tý',23,'Nam','phanthanh21@gmail.com','Ninh Bình','tx10.jpg',0,10,1,'2023-03-08 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',2000000),(13,'0923246195','Nguyễn Thị Đào',25,'Nam','sao1@gmail.com','Hưng Yên','tx8.jpg',2,10,1,'2024-06-09 00:00:00',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',0),(14,'0853243244','Hoàng Quang Sáng',21,'Nam','sang2@gmail.com','Hà Nội','tx7.jpg',1,7,1,'2024-03-08 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',1233000),(15,'0832346655','Lê Cửu Mười',20,'Nữ','muoi10@gmail.com','Hà Nam','tx11.jpg',1,10,1,'2024-02-08 12:34:56',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',0),(16,'0884453555','moi',55,'Nam','dai@gmail.com','Nam nam','tx11.jpg',2,1,1,'2024-06-12 00:00:00',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',0),(17,'0322345544','032',22,'Nam','d@gmail.com','123','tx11.jpg',2,1,1,'2024-06-12 00:00:00',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',0),(18,'0987654321','CHU QUỐC KHÁNH',19,'Nam','khanh@gmail.com','bac giang','ad2.jpg',1,1,1,'2024-06-21 00:00:00',NULL,'2024-06-21 00:00:00',NULL,'089043a428662498f8654deda8566ed5',0),(23,'0981231231','N',18,'Nam','m@gmail.com','nam dinh','tx1.jpg',2,1,1,'2024-06-27 00:00:00',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',0),(24,'0981231230','dai',19,'Nam','n@gmail.com','nam dinh','ad4.jpg',2,1,1,'2024-06-27 00:00:00',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',0),(25,'0981231232','nguyen',18,'Nam','dai@gmail.com','nam định','ad4.jpg',2,1,1,'2024-06-27 00:00:00',NULL,NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',0);
/*!40000 ALTER TABLE `tbluser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-12  1:18:18
