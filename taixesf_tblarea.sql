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
-- Table structure for table `tblarea`
--

DROP TABLE IF EXISTS `tblarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tblarea` (
  `area_id` int(11) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(45) NOT NULL,
  `area_code` varchar(45) DEFAULT NULL,
  `area_createddate` datetime DEFAULT NULL,
  `area_createdby` varchar(45) DEFAULT NULL,
  `area_modifieddate` datetime DEFAULT NULL,
  `area_modifiedby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblarea`
--

LOCK TABLES `tblarea` WRITE;
/*!40000 ALTER TABLE `tblarea` DISABLE KEYS */;
INSERT INTO `tblarea` VALUES (1,'Nam Từ Liêm','Nam Từ Liêm A',NULL,NULL,NULL,NULL),(2,'Nam Từ Liêm','Nam Từ Liêm B',NULL,NULL,NULL,NULL),(3,'Nam Từ Liêm','Nam Từ Liêm C',NULL,NULL,NULL,NULL),(4,'Bắc Từ Liêm','Bắc Từ Liêm A',NULL,NULL,NULL,NULL),(5,'Bắc Từ Liêm','Bắc Từ Liêm B',NULL,NULL,NULL,NULL),(6,'Bắc Từ Liêm','Bắc Từ Liêm C',NULL,NULL,NULL,NULL),(7,'Cầu Giấy','Cầu Giấy A',NULL,NULL,NULL,NULL),(8,'Cầu Giấy','Cầu Giấy B',NULL,NULL,NULL,NULL),(9,'Cầu Giấy','Cầu Giấy C',NULL,NULL,NULL,NULL),(10,'Hồ Tây','Hồ Tây A',NULL,NULL,NULL,NULL),(11,'Hồ Tây','Hồ Tây B',NULL,NULL,NULL,NULL),(12,'Hồ Tây','Hồ Tây C',NULL,NULL,NULL,NULL),(13,'Hoài Đức','Hoài Đức A',NULL,NULL,NULL,NULL),(14,'Hoài Đức','Hoài Đức B',NULL,NULL,NULL,NULL),(15,'Hoài Đức','Hoài Đức C',NULL,NULL,NULL,NULL),(16,'Nam Từ Liêm','Nam Từ Liêm D',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tblarea` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-12  1:18:19
