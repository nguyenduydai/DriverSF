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
-- Table structure for table `tblhub`
--

DROP TABLE IF EXISTS `tblhub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tblhub` (
  `hub_id` int(11) NOT NULL AUTO_INCREMENT,
  `hub_name` varchar(45) DEFAULT NULL,
  `hub_user_id` int(11) NOT NULL,
  `hub_workingshift_id` int(11) NOT NULL,
  `hub_createddate` datetime DEFAULT NULL,
  `hub_createdby` varchar(45) DEFAULT NULL,
  `hub_modifieddate` datetime DEFAULT NULL,
  `hub_modifiedby` varchar(45) DEFAULT NULL,
  `hub_ordernumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`hub_id`),
  KEY `hub_user_id_idx` (`hub_user_id`),
  KEY `hub_workingshift_id_idx` (`hub_workingshift_id`),
  CONSTRAINT `hub_user_id` FOREIGN KEY (`hub_user_id`) REFERENCES `tbluser` (`user_id`),
  CONSTRAINT `hub_workingshift_id` FOREIGN KEY (`hub_workingshift_id`) REFERENCES `tblworkingshift` (`workingshift_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblhub`
--

LOCK TABLES `tblhub` WRITE;
/*!40000 ALTER TABLE `tblhub` DISABLE KEYS */;
INSERT INTO `tblhub` VALUES (12,'dang ky',2,803,NULL,NULL,NULL,NULL,0),(13,'dang ky',2,833,NULL,NULL,NULL,NULL,0),(14,'dang ky',2,925,NULL,NULL,NULL,NULL,0),(15,'dang ky',2,910,NULL,NULL,NULL,NULL,0),(16,'dang ky',2,1060,NULL,NULL,NULL,NULL,0),(17,'dang ky',2,998,NULL,NULL,NULL,NULL,0),(18,'dang ky',2,983,NULL,NULL,NULL,NULL,0),(19,'dang ky',2,623,NULL,NULL,NULL,NULL,0),(20,'dang ky',2,638,NULL,NULL,NULL,NULL,0),(21,'dang ky',2,578,NULL,NULL,NULL,NULL,0),(22,'dang ky',2,488,NULL,NULL,NULL,NULL,0),(23,'dang ky',2,520,NULL,NULL,NULL,NULL,0),(25,'dang ky',1,863,NULL,NULL,NULL,NULL,0),(27,'dang ky',3,851,NULL,NULL,NULL,NULL,0),(28,'dang ky',3,883,NULL,NULL,NULL,NULL,0),(29,'dang ky',3,838,NULL,NULL,NULL,NULL,0),(30,'dang ky',3,703,NULL,NULL,NULL,NULL,0),(31,'dang ky',3,627,NULL,NULL,NULL,NULL,0),(32,'dang ky',3,762,NULL,NULL,NULL,NULL,0),(33,'dang ky',3,717,NULL,NULL,NULL,NULL,0),(34,'dang ky',1,879,NULL,NULL,NULL,NULL,0),(35,'dang ky',1,849,NULL,NULL,NULL,NULL,0),(36,'dang ky',1,984,NULL,NULL,NULL,NULL,0),(37,'dang ky',1,1029,NULL,NULL,NULL,NULL,0),(38,'dang ky',1,2477,NULL,NULL,NULL,NULL,0),(39,'dang ky',1,2492,NULL,NULL,NULL,NULL,0),(40,'dang ky',1,2554,NULL,NULL,NULL,NULL,0),(41,'dang ky',1,2612,NULL,NULL,NULL,NULL,0),(42,'dang ky',1,2642,NULL,NULL,NULL,NULL,0),(43,'dang ky',1,2672,NULL,NULL,NULL,NULL,0),(44,'dang ky',1,2718,NULL,NULL,NULL,NULL,0),(45,'dang ky',1,2747,NULL,NULL,NULL,NULL,0),(46,'dang ky',1,2762,NULL,NULL,NULL,NULL,0),(47,'dang ky',1,3107,NULL,NULL,NULL,NULL,0),(48,'dang ky',1,2972,NULL,NULL,NULL,NULL,0),(49,'dang ky',1,2988,NULL,NULL,NULL,NULL,0),(50,'dang ky',1,3049,NULL,NULL,NULL,NULL,0),(51,'dang ky',1,3019,NULL,NULL,NULL,NULL,0),(52,'dang ky',1,490,NULL,NULL,NULL,NULL,0),(53,'dang ky',1,519,NULL,NULL,NULL,NULL,0),(54,'dang ky',1,533,NULL,NULL,NULL,NULL,0),(55,'dang ky',1,579,NULL,NULL,NULL,NULL,0),(56,'dang ky',1,608,NULL,NULL,NULL,NULL,0),(57,'dang ky',1,594,NULL,NULL,NULL,NULL,0),(58,'dang ky',1,759,NULL,NULL,NULL,NULL,0),(59,'dang ky',1,715,NULL,NULL,NULL,NULL,0),(60,'dang ky',1,804,NULL,NULL,NULL,NULL,0),(61,'dang ky',1,894,NULL,NULL,NULL,NULL,0),(62,'dang ky',1,939,NULL,NULL,NULL,NULL,0),(63,'dang ky',1,1045,NULL,NULL,NULL,NULL,0),(64,'dang ky',1,1060,NULL,NULL,NULL,NULL,0),(65,'dang ky',2,2747,NULL,NULL,NULL,NULL,0),(66,'dang ky',2,2762,NULL,NULL,NULL,NULL,0),(67,'dang ky',1,2823,NULL,NULL,NULL,NULL,0),(68,'dang ky',1,3169,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `tblhub` ENABLE KEYS */;
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
