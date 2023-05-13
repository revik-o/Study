-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: dz6
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `advertising`
--

DROP TABLE IF EXISTS `advertising`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advertising` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` text,
  `description` longtext,
  `path_to_main_image` text,
  `state` tinyint(1) DEFAULT NULL,
  `seo_block_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_seo_block_idx` (`seo_block_id`),
  CONSTRAINT `fk_seo_block` FOREIGN KEY (`seo_block_id`) REFERENCES `seo_block` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertising`
--

LOCK TABLES `advertising` WRITE;
/*!40000 ALTER TABLE `advertising` DISABLE KEYS */;
/*!40000 ALTER TABLE `advertising` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cafe_bar`
--

DROP TABLE IF EXISTS `cafe_bar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cafe_bar` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `title` text,
  `description` longtext,
  `path_to_main_image` text,
  `state` tinyint(1) NOT NULL,
  `cinema_id` bigint unsigned NOT NULL,
  `seo_block_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_cinema_id6_idx` (`cinema_id`),
  KEY `fk_seo_block_id6_idx` (`seo_block_id`),
  CONSTRAINT `fk_cinema_id6` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id6` FOREIGN KEY (`seo_block_id`) REFERENCES `seo_block` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cafe_bar`
--

LOCK TABLES `cafe_bar` WRITE;
/*!40000 ALTER TABLE `cafe_bar` DISABLE KEYS */;
/*!40000 ALTER TABLE `cafe_bar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `children_s_room`
--

DROP TABLE IF EXISTS `children_s_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `children_s_room` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `title` text,
  `description` longtext,
  `path_to_main_image` text,
  `state` tinyint(1) NOT NULL,
  `cinema_id` bigint unsigned NOT NULL,
  `seo_block_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_cinema_id8_idx` (`cinema_id`),
  KEY `fk_seo_block_id8_idx` (`seo_block_id`),
  CONSTRAINT `fk_cinema_id8` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id8` FOREIGN KEY (`seo_block_id`) REFERENCES `seo_block` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `children_s_room`
--

LOCK TABLES `children_s_room` WRITE;
/*!40000 ALTER TABLE `children_s_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `children_s_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cinema`
--

DROP TABLE IF EXISTS `cinema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cinema` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` text,
  `description` longtext,
  `about_cinema` longtext,
  `conditions` longtext,
  `path_to_logo_image` text,
  `path_to_top_banner_image` text,
  `seo_block_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_seo_block_idx` (`seo_block_id`),
  CONSTRAINT `fk_seo_block_id` FOREIGN KEY (`seo_block_id`) REFERENCES `seo_block` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cinema`
--

LOCK TABLES `cinema` WRITE;
/*!40000 ALTER TABLE `cinema` DISABLE KEYS */;
/*!40000 ALTER TABLE `cinema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacts` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `address` longtext,
  `coordinate_for_map` text,
  `state` tinyint(1) NOT NULL,
  `cinema_id` bigint unsigned NOT NULL,
  `seo_block_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_cinema_id9_idx` (`cinema_id`),
  KEY `fk_seo_block_id9_idx` (`seo_block_id`),
  CONSTRAINT `fk_cinema_id9` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id9` FOREIGN KEY (`seo_block_id`) REFERENCES `seo_block` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery_for_cafe_bar`
--

DROP TABLE IF EXISTS `gallery_for_cafe_bar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery_for_cafe_bar` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `path_to_image` text NOT NULL,
  `cafe_bar_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_cafe_bar_id_idx` (`cafe_bar_id`),
  CONSTRAINT `fk_cafe_bar_id` FOREIGN KEY (`cafe_bar_id`) REFERENCES `cafe_bar` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery_for_cafe_bar`
--

LOCK TABLES `gallery_for_cafe_bar` WRITE;
/*!40000 ALTER TABLE `gallery_for_cafe_bar` DISABLE KEYS */;
/*!40000 ALTER TABLE `gallery_for_cafe_bar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery_for_children_s_room`
--

DROP TABLE IF EXISTS `gallery_for_children_s_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery_for_children_s_room` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `path_to_image` text NOT NULL,
  `children_s_room_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_children_s_room_id_idx` (`children_s_room_id`),
  CONSTRAINT `fk_children_s_room_id` FOREIGN KEY (`children_s_room_id`) REFERENCES `children_s_room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery_for_children_s_room`
--

LOCK TABLES `gallery_for_children_s_room` WRITE;
/*!40000 ALTER TABLE `gallery_for_children_s_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `gallery_for_children_s_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery_for_cinema`
--

DROP TABLE IF EXISTS `gallery_for_cinema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery_for_cinema` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `path_to_image` text NOT NULL,
  `cinema_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_cinema_id10_idx` (`cinema_id`),
  CONSTRAINT `fk_cinema_id10` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery_for_cinema`
--

LOCK TABLES `gallery_for_cinema` WRITE;
/*!40000 ALTER TABLE `gallery_for_cinema` DISABLE KEYS */;
/*!40000 ALTER TABLE `gallery_for_cinema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery_for_contacts`
--

DROP TABLE IF EXISTS `gallery_for_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery_for_contacts` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `path_to_image` text NOT NULL,
  `contacts_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_contacts_id_idx` (`contacts_id`),
  CONSTRAINT `fk_contacts_id` FOREIGN KEY (`contacts_id`) REFERENCES `contacts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery_for_contacts`
--

LOCK TABLES `gallery_for_contacts` WRITE;
/*!40000 ALTER TABLE `gallery_for_contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `gallery_for_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery_for_hall`
--

DROP TABLE IF EXISTS `gallery_for_hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery_for_hall` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `path_to_image` text NOT NULL,
  `hall_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_hall_id2_idx` (`hall_id`),
  CONSTRAINT `fk_hall_id2` FOREIGN KEY (`hall_id`) REFERENCES `hall` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery_for_hall`
--

LOCK TABLES `gallery_for_hall` WRITE;
/*!40000 ALTER TABLE `gallery_for_hall` DISABLE KEYS */;
/*!40000 ALTER TABLE `gallery_for_hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery_for_movie_page`
--

DROP TABLE IF EXISTS `gallery_for_movie_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery_for_movie_page` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `path_to_image` text NOT NULL,
  `movie_page_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_movie_page_id1_idx` (`movie_page_id`),
  CONSTRAINT `fk_movie_page_id1` FOREIGN KEY (`movie_page_id`) REFERENCES `movie_page` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery_for_movie_page`
--

LOCK TABLES `gallery_for_movie_page` WRITE;
/*!40000 ALTER TABLE `gallery_for_movie_page` DISABLE KEYS */;
/*!40000 ALTER TABLE `gallery_for_movie_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery_for_news`
--

DROP TABLE IF EXISTS `gallery_for_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery_for_news` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `path_to_image` text NOT NULL,
  `news_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_news_id_idx` (`news_id`),
  CONSTRAINT `fk_news_id` FOREIGN KEY (`news_id`) REFERENCES `news` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery_for_news`
--

LOCK TABLES `gallery_for_news` WRITE;
/*!40000 ALTER TABLE `gallery_for_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `gallery_for_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery_for_stock`
--

DROP TABLE IF EXISTS `gallery_for_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery_for_stock` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `path_to_image` text NOT NULL,
  `stock_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_stock_id_idx` (`stock_id`),
  CONSTRAINT `fk_stock_id` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery_for_stock`
--

LOCK TABLES `gallery_for_stock` WRITE;
/*!40000 ALTER TABLE `gallery_for_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `gallery_for_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery_for_vip_hall`
--

DROP TABLE IF EXISTS `gallery_for_vip_hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery_for_vip_hall` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `path_to_image` text NOT NULL,
  `vip_hall_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_vip_hall_id_idx` (`vip_hall_id`),
  CONSTRAINT `fk_vip_hall_id` FOREIGN KEY (`vip_hall_id`) REFERENCES `vip_hall` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery_for_vip_hall`
--

LOCK TABLES `gallery_for_vip_hall` WRITE;
/*!40000 ALTER TABLE `gallery_for_vip_hall` DISABLE KEYS */;
/*!40000 ALTER TABLE `gallery_for_vip_hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gender` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hall` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `number` text NOT NULL,
  `description` longtext,
  `path_to_layout_hall_image` text,
  `path_to_top_banner_image` text,
  `cinema_id` bigint unsigned NOT NULL,
  `seo_block_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_cinema_id_idx` (`cinema_id`),
  KEY `fk_seo_block_id1_idx` (`seo_block_id`),
  CONSTRAINT `fk_cinema_id2` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id2` FOREIGN KEY (`seo_block_id`) REFERENCES `seo_block` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall_place`
--

DROP TABLE IF EXISTS `hall_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hall_place` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `row` int NOT NULL,
  `number_of_place` int NOT NULL,
  `hall_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_hall_id_idx` (`hall_id`),
  CONSTRAINT `fk_hall_id` FOREIGN KEY (`hall_id`) REFERENCES `hall` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall_place`
--

LOCK TABLES `hall_place` WRITE;
/*!40000 ALTER TABLE `hall_place` DISABLE KEYS */;
/*!40000 ALTER TABLE `hall_place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` text,
  `rating` float DEFAULT NULL,
  `year` int DEFAULT NULL,
  `country` text,
  `director` text,
  `producer` text,
  `screenwriter` text,
  `genre` text,
  `age` text,
  `time` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_page`
--

DROP TABLE IF EXISTS `movie_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_page` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `title` text,
  `description` text,
  `path_to_main_image` text,
  `trailer_link` text,
  `movie_id` bigint unsigned NOT NULL,
  `type_movie_id` bigint unsigned NOT NULL,
  `seo_block_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_movie_id_idx` (`movie_id`),
  KEY `fk_type_movie_id_idx` (`type_movie_id`),
  KEY `fk_seo_block_id1_idx` (`seo_block_id`),
  CONSTRAINT `fk_movie_id` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id1` FOREIGN KEY (`seo_block_id`) REFERENCES `seo_block` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_type_movie_id` FOREIGN KEY (`type_movie_id`) REFERENCES `type_movie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_page`
--

LOCK TABLES `movie_page` WRITE;
/*!40000 ALTER TABLE `movie_page` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `title` text,
  `description` longtext,
  `date_` date DEFAULT NULL,
  `path_to_main_image` text,
  `video_link` text,
  `state` tinyint(1) DEFAULT NULL,
  `cinema_id` bigint unsigned NOT NULL,
  `seo_block_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_cinema_id3_idx` (`cinema_id`),
  KEY `fk_seo_block_id3_idx` (`seo_block_id`),
  CONSTRAINT `fk_cinema_id3` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id3` FOREIGN KEY (`seo_block_id`) REFERENCES `seo_block` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seo_block`
--

DROP TABLE IF EXISTS `seo_block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seo_block` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `seo_url` text,
  `seo_title` text,
  `seo_keywords` text,
  `seo_description` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seo_block`
--

LOCK TABLES `seo_block` WRITE;
/*!40000 ALTER TABLE `seo_block` DISABLE KEYS */;
/*!40000 ALTER TABLE `seo_block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showing_movie`
--

DROP TABLE IF EXISTS `showing_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `showing_movie` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `movie_page_id` bigint unsigned NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_movie_page_id_idx` (`movie_page_id`),
  CONSTRAINT `fk_movie_page_id` FOREIGN KEY (`movie_page_id`) REFERENCES `movie_page` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showing_movie`
--

LOCK TABLES `showing_movie` WRITE;
/*!40000 ALTER TABLE `showing_movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `showing_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `title` text,
  `description` longtext,
  `path_to_main_image` text,
  `video_link` text,
  `state` tinyint(1) NOT NULL,
  `cinema_id` bigint unsigned NOT NULL,
  `seo_block_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_cinema_id5_idx` (`cinema_id`),
  KEY `fk_seo_block_id5_idx` (`seo_block_id`),
  CONSTRAINT `fk_cinema_id5` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id5` FOREIGN KEY (`seo_block_id`) REFERENCES `seo_block` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `take_up_place_list`
--

DROP TABLE IF EXISTS `take_up_place_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `take_up_place_list` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `showing_movie_id` bigint unsigned NOT NULL,
  `hall_place_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_showing_movie_id_idx` (`showing_movie_id`),
  KEY `fk_hall_place_id_idx` (`hall_place_id`),
  CONSTRAINT `fk_hall_place_id` FOREIGN KEY (`hall_place_id`) REFERENCES `hall_place` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_showing_movie_id` FOREIGN KEY (`showing_movie_id`) REFERENCES `showing_movie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `take_up_place_list`
--

LOCK TABLES `take_up_place_list` WRITE;
/*!40000 ALTER TABLE `take_up_place_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `take_up_place_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_booking`
--

DROP TABLE IF EXISTS `ticket_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_booking` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `take_up_place_list_id` bigint unsigned NOT NULL,
  `date_` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_user_id_idx` (`user_id`),
  KEY `fk_take_up_place_list_id_idx` (`take_up_place_list_id`),
  CONSTRAINT `fk_take_up_place_list_id` FOREIGN KEY (`take_up_place_list_id`) REFERENCES `take_up_place_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_booking`
--

LOCK TABLES `ticket_booking` WRITE;
/*!40000 ALTER TABLE `ticket_booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_movie`
--

DROP TABLE IF EXISTS `type_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_movie` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `three d` tinyint(1) NOT NULL,
  `two d` tinyint(1) NOT NULL,
  `imax` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_movie`
--

LOCK TABLES `type_movie` WRITE;
/*!40000 ALTER TABLE `type_movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `type_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `surname` text NOT NULL,
  `nickname` text NOT NULL,
  `e_mail` text,
  `address` text,
  `password` text NOT NULL,
  `card_number` text,
  `language` text,
  `gender_id` bigint unsigned NOT NULL,
  `phone` text,
  `birthday` date DEFAULT NULL,
  `city_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_gender_id_idx` (`gender_id`),
  KEY `fk_city_id_idx` (`city_id`),
  CONSTRAINT `fk_city_id` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_gender_id` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_hall`
--

DROP TABLE IF EXISTS `vip_hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vip_hall` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `title` text,
  `description` longtext,
  `path_to_main_image` text,
  `state` tinyint(1) NOT NULL,
  `cinema_id` bigint unsigned NOT NULL,
  `seo_block_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `fk_cinema_id7_idx` (`cinema_id`),
  KEY `fk_seo_block_id7_idx` (`seo_block_id`),
  CONSTRAINT `fk_cinema_id7` FOREIGN KEY (`cinema_id`) REFERENCES `cinema` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id7` FOREIGN KEY (`seo_block_id`) REFERENCES `seo_block` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_hall`
--

LOCK TABLES `vip_hall` WRITE;
/*!40000 ALTER TABLE `vip_hall` DISABLE KEYS */;
/*!40000 ALTER TABLE `vip_hall` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-26  1:56:08
