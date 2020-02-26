-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: truck-trans
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `company` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bank` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `iban` varchar(26) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nip` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `place` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `post_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `street` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `street_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_67yh51mk03ohg2f73lagmk999` (`nip`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`id`, `bank`, `iban`, `name`, `nip`, `place`, `post_code`, `street`, `street_number`) VALUES (1,'Santander','42150013313706439256596152','Truck Giant','7629736580','Leśna','59-870','Wojska Polskiego','49a');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contractors`
--

DROP TABLE IF EXISTS `contractors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contractors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nip` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `place` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `post_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `street` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `street_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5fw340bmfmgytvtabnupl8pxa` (`nip`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contractors`
--

LOCK TABLES `contractors` WRITE;
/*!40000 ALTER TABLE `contractors` DISABLE KEYS */;
INSERT INTO `contractors` (`id`, `name`, `nip`, `place`, `post_code`, `street`, `street_number`) VALUES (1,'Truck Tech','7629736580','Leśna','59-870','Wojska Polskiego','49a'),(2,'Grand Logistics','3923391981','Bielsko-Biała','34-287','Wiśniowa','17'),(3,'Deltacar Spedition Ltd.','1145439600','Warszawa','22-100','Widokowa','11'),(4,'Usługi Transportowe, Jurga Stanisław','9517462575','Wrocław','50-009','T. Kościuszki','59'),(5,'Majewski Krzysztof, Usługi transportowe','1258071082','Jelenia Góra','58-560','Al. Jana Pawła II','39b'),(6,'Transport Towarowy Tranz-Silesia','4966959182','Wałbrzych','58-300','Bolesława Chrobrego','2a/13');
/*!40000 ALTER TABLE `contractors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drivers`
--

DROP TABLE IF EXISTS `drivers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `drivers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_of_birth` datetime DEFAULT NULL,
  `driving_licence_validity` datetime DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_osh` datetime DEFAULT NULL,
  `next_osh` datetime DEFAULT NULL,
  `pesel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tractor_id` bigint DEFAULT NULL,
  `trailer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9ry60rohe27cohvo60leneep6` (`pesel`),
  UNIQUE KEY `UK_1q4fy9g8nuei7woi2y7b5ti3l` (`tractor_id`),
  UNIQUE KEY `UK_atxcptucd0v7mkriaw2rrvt9h` (`trailer_id`),
  CONSTRAINT `FK6yfsiwygybdd3dddalxs9vwv` FOREIGN KEY (`tractor_id`) REFERENCES `tractors` (`id`),
  CONSTRAINT `FKr8o3sfoyo367edxs6on2por4d` FOREIGN KEY (`trailer_id`) REFERENCES `trailers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drivers`
--

LOCK TABLES `drivers` WRITE;
/*!40000 ALTER TABLE `drivers` DISABLE KEYS */;
INSERT INTO `drivers` (`id`, `date_of_birth`, `driving_licence_validity`, `first_name`, `last_name`, `last_osh`, `next_osh`, `pesel`, `phone`, `tractor_id`, `trailer_id`) VALUES (1,'1980-08-14 00:00:00','2027-03-03 00:00:00','Jan','Kowalski','2019-07-16 00:00:00','2020-08-16 00:00:00','27040937840','560123432',1,1),(5,'2020-02-13 00:00:00','2020-02-19 00:00:00','Krzysztof ','Litwiniuk','2020-02-13 00:00:00','2020-02-20 00:00:00','55101566360','3324234',2,2),(7,'2020-02-04 00:00:00','2020-02-20 00:00:00','Jarosław','Stempel','2020-02-03 00:00:00','2020-02-27 00:00:00','24062743417','9874239',3,3);
/*!40000 ALTER TABLE `drivers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drivers_orders`
--

DROP TABLE IF EXISTS `drivers_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `drivers_orders` (
  `order_id` bigint NOT NULL,
  `driver_id` bigint NOT NULL,
  KEY `FKmx2jpllhmly0c67h7wp0yr5dj` (`driver_id`),
  KEY `FKp9r67qyhr87dl81juvqyrj1od` (`order_id`),
  CONSTRAINT `FKmx2jpllhmly0c67h7wp0yr5dj` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`),
  CONSTRAINT `FKp9r67qyhr87dl81juvqyrj1od` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drivers_orders`
--

LOCK TABLES `drivers_orders` WRITE;
/*!40000 ALTER TABLE `drivers_orders` DISABLE KEYS */;
INSERT INTO `drivers_orders` (`order_id`, `driver_id`) VALUES (1,1);
/*!40000 ALTER TABLE `drivers_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoices`
--

DROP TABLE IF EXISTS `invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `invoices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `annotations` varchar(600) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `days` int NOT NULL,
  `invoice_date` datetime NOT NULL,
  `invoice_number` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `is_paid` bit(1) DEFAULT NULL,
  `payment_method` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `place` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `service_date` datetime NOT NULL,
  `company_id` bigint DEFAULT NULL,
  `contractor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l1x55mfsay7co0r3m9ynvipd5` (`invoice_number`),
  KEY `FKevet91hxx72epfdxlw2spog9w` (`company_id`),
  KEY `FKngqn6pfe7xo9krs6df6xoqpwl` (`contractor_id`),
  CONSTRAINT `FKevet91hxx72epfdxlw2spog9w` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKngqn6pfe7xo9krs6df6xoqpwl` FOREIGN KEY (`contractor_id`) REFERENCES `contractors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoices`
--

LOCK TABLES `invoices` WRITE;
/*!40000 ALTER TABLE `invoices` DISABLE KEYS */;
INSERT INTO `invoices` (`id`, `annotations`, `days`, `invoice_date`, `invoice_number`, `is_paid`, `payment_method`, `place`, `service_date`, `company_id`, `contractor_id`) VALUES (1,'Ilość WZ - 5 szt.',30,'2020-02-16 00:00:00','1/2020',_binary '','bank transfer','Leśna','2020-02-16 00:00:00',1,1),(2,'Nr zamówienia: 1-R/2020',30,'2020-02-16 00:00:00','2/2020',_binary '\0','bank transfer','Leśna','2020-02-16 00:00:00',1,2);
/*!40000 ALTER TABLE `invoices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` double DEFAULT NULL,
  `service_description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `unit` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `vat_rate` int DEFAULT NULL,
  `invoice_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi3fv29v33apu6j1klh8nld3re` (`invoice_id`),
  CONSTRAINT `FKi3fv29v33apu6j1klh8nld3re` FOREIGN KEY (`invoice_id`) REFERENCES `invoices` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` (`id`, `quantity`, `service_description`, `unit`, `unit_price`, `vat_rate`, `invoice_id`) VALUES (9,123,'Sprzedaż piasku','tona',24.5,23,1),(23,4,'Transport żwiru','kurs',120,23,2),(25,4,'Transport żwiru','kurs',120,23,2),(27,3,'Transport żwiru','kurs',125,23,2);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(600) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` datetime NOT NULL,
  `direction_from` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `direction_to` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `goods` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `order_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `terms` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `unit` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `value` double DEFAULT NULL,
  `company_id` bigint DEFAULT NULL,
  `contractor_id` bigint DEFAULT NULL,
  `tractor_id` bigint DEFAULT NULL,
  `trailer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nthkiu7pgmnqnu86i2jyoe2v7` (`order_number`),
  KEY `FKjp7lhaugpjb7u4su3h2khmnb3` (`company_id`),
  KEY `FK3xvcmuk6a7ktrx3awo4ye55fs` (`contractor_id`),
  KEY `FK6r5i6mpoa1p12r3h82v87q36n` (`tractor_id`),
  KEY `FK6v22jbmoruiby80oqny5hbnre` (`trailer_id`),
  CONSTRAINT `FK3xvcmuk6a7ktrx3awo4ye55fs` FOREIGN KEY (`contractor_id`) REFERENCES `contractors` (`id`),
  CONSTRAINT `FK6r5i6mpoa1p12r3h82v87q36n` FOREIGN KEY (`tractor_id`) REFERENCES `tractors` (`id`),
  CONSTRAINT `FK6v22jbmoruiby80oqny5hbnre` FOREIGN KEY (`trailer_id`) REFERENCES `trailers` (`id`),
  CONSTRAINT `FKjp7lhaugpjb7u4su3h2khmnb3` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `comment`, `date`, `direction_from`, `direction_to`, `goods`, `order_number`, `quantity`, `terms`, `unit`, `value`, `company_id`, `contractor_id`, `tractor_id`, `trailer_id`) VALUES (1,NULL,'2020-02-16 00:00:00','Okmiany','Gryżyce','Żwir','1/2020',20,'','kurs',215,1,4,NULL,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tractors`
--

DROP TABLE IF EXISTS `tractors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tractors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `insurance_expires` datetime DEFAULT NULL,
  `make` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `model` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `next_technical_inspection` datetime DEFAULT NULL,
  `registration_number` varchar(14) COLLATE utf8mb4_unicode_ci NOT NULL,
  `vin` varchar(17) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1dgeve44abvhnilkr7y6xp274` (`vin`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tractors`
--

LOCK TABLES `tractors` WRITE;
/*!40000 ALTER TABLE `tractors` DISABLE KEYS */;
INSERT INTO `tractors` (`id`, `insurance_expires`, `make`, `model`, `next_technical_inspection`, `registration_number`, `vin`) VALUES (1,'2020-07-12 00:00:00','DAF ','CF','2020-08-11 00:00:00','DLB3421','98076543212345678'),(2,'2028-06-20 00:00:00','MAN','TGX','2020-09-19 00:00:00','DLU 3245','3D7KS29C67G763440'),(3,'2020-02-29 00:00:00','Scania','XT','2020-02-29 00:00:00','FZ 465FT','5XYKT3A66DG370762'),(4,'2020-02-22 00:00:00','DAF','CF','2022-04-08 00:00:00','DLB 45TG','1FVHCYDJ85HV14123'),(5,'2021-12-20 00:00:00','DAF','XF','2021-06-23 00:00:00','DLU 45654','4S3BK6354S6355265'),(6,'2020-04-14 00:00:00','MAN','TGS','2020-07-06 00:00:00','DLU 35PO','1G8DC18D0CF150367');
/*!40000 ALTER TABLE `tractors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trailers`
--

DROP TABLE IF EXISTS `trailers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `trailers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `insurance_expires` datetime DEFAULT NULL,
  `make` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `model` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `next_technical_inspection` datetime DEFAULT NULL,
  `registration_number` varchar(14) COLLATE utf8mb4_unicode_ci NOT NULL,
  `vin` varchar(17) COLLATE utf8mb4_unicode_ci NOT NULL,
  `body` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_j5xm0mrbr1la9ygtk5qde13ub` (`vin`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trailers`
--

LOCK TABLES `trailers` WRITE;
/*!40000 ALTER TABLE `trailers` DISABLE KEYS */;
INSERT INTO `trailers` (`id`, `insurance_expires`, `make`, `model`, `next_technical_inspection`, `registration_number`, `vin`, `body`) VALUES (1,'2021-03-19 00:00:00','Wielton','','2022-08-13 00:00:00','DLU8865','98086754212345678','steel'),(2,'2034-06-13 00:00:00','Schmitz','','2020-12-20 00:00:00','DLB 44UG','JTDKB20U983336905','aluminium'),(3,'2020-02-22 00:00:00','Schmitz','','2020-02-29 00:00:00','FZ PO678','1HGFA16807L092733','steel'),(5,'2020-07-18 00:00:00','Wielton',NULL,'2023-08-18 00:00:00','DLB 437P','JNKBY31AXYM379859','aluminium'),(6,'2020-07-25 00:00:00','Wielton',NULL,'2021-05-10 00:00:00','DLB F633','1GNKRGKD8DJ145866','aluminium');
/*!40000 ALTER TABLE `trailers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `login` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-26 21:57:26
