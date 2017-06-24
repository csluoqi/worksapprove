CREATE DATABASE  IF NOT EXISTS `worksApproval` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `worksApproval`;
-- MySQL dump 10.13  Distrib 5.5.53, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: worksApproval
-- ------------------------------------------------------
-- Server version	5.5.53-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `page_info`
--

DROP TABLE IF EXISTS `page_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `page_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(256) NOT NULL COMMENT '页面链接',
  `page_name` varchar(125) NOT NULL COMMENT '文件中文名字',
  `notes` varchar(512) DEFAULT NULL COMMENT '注释',
  `create_time` datetime DEFAULT NULL,
  `last_updated_time` datetime DEFAULT NULL,
  `use_state` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='页面管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page_info`
--

LOCK TABLES `page_info` WRITE;
/*!40000 ALTER TABLE `page_info` DISABLE KEYS */;
INSERT INTO `page_info` VALUES (2,'测试2.jsp','测试2','测试2.jsp','2017-01-15 22:28:43','2017-01-15 22:28:43',0),(3,'TEST2','测试2','1212','2017-02-02 14:03:03','2017-02-02 14:03:03',1);
/*!40000 ALTER TABLE `page_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_page`
--

DROP TABLE IF EXISTS `role_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_order` int(11) DEFAULT NULL,
  `use_state` tinyint(4) DEFAULT '1',
  `page_id` bigint(20) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qpp62xuuwyrufo5u57pcl9w4g` (`page_id`),
  KEY `FK_ebacn0bwsikixbxdp1whpfsre` (`role_id`),
  CONSTRAINT `FK_ebacn0bwsikixbxdp1whpfsre` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`),
  CONSTRAINT `FK_qpp62xuuwyrufo5u57pcl9w4g` FOREIGN KEY (`page_id`) REFERENCES `page_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_page`
--

LOCK TABLES `role_page` WRITE;
/*!40000 ALTER TABLE `role_page` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site_message`
--

DROP TABLE IF EXISTS `site_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `site_message` (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `create_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `recipients_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FK_joqtmbil270u9alh7rne39g5f` (`recipients_user_id`),
  CONSTRAINT `FK_joqtmbil270u9alh7rne39g5f` FOREIGN KEY (`recipients_user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site_message`
--

LOCK TABLES `site_message` WRITE;
/*!40000 ALTER TABLE `site_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `site_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(125) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` int(11) DEFAULT '1',
  `password` varchar(20) NOT NULL,
  `phone_number` varchar(11) DEFAULT NULL,
  `profile` longtext,
  `real_name` varchar(255) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `user_name` varchar(50) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_nd4xxe4sfscx08oods9gi8y2v` (`user_name`),
  KEY `FK_2vttj9oexs8oh5oxmmllnidmy` (`role_id`),
  CONSTRAINT `FK_2vttj9oexs8oh5oxmmllnidmy` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'aa','2017-01-07 23:26:16','asdf',1,'111111','13222222222','qwqwqwqw','qq','2017-01-07 23:26:16','qaasdf',1),(2,'aa','2017-01-07 23:27:40','asdf',1,'111111','13222222222','qwqwqwqw','qq','2017-01-07 23:27:40','wwww',1);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(256) DEFAULT NULL,
  `shortName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'管理员','管理员'),(3,'编辑','编辑'),(5,'中级评审','中级评审'),(7,'初级评审','初级评审'),(9,'作者','作者');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `nice_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `works`
--

DROP TABLE IF EXISTS `works`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `works` (
  `work_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_Date` datetime DEFAULT NULL,
  `file_path` varchar(512) DEFAULT NULL,
  `file_size` int(11) DEFAULT NULL,
  `name` varchar(256) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `author_id` bigint(20) NOT NULL,
  `theme_id` bigint(20) NOT NULL,
  `summary` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`work_id`),
  KEY `FK_shrtdto2c4uwiyteott5vedhb` (`author_id`),
  KEY `FK_6iijyqhyye6e0oocmlqdh1y0d` (`theme_id`),
  CONSTRAINT `FK_6iijyqhyye6e0oocmlqdh1y0d` FOREIGN KEY (`theme_id`) REFERENCES `works_theme` (`theme_id`),
  CONSTRAINT `FK_shrtdto2c4uwiyteott5vedhb` FOREIGN KEY (`author_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `works`
--

LOCK TABLES `works` WRITE;
/*!40000 ALTER TABLE `works` DISABLE KEYS */;
INSERT INTO `works` VALUES (16,'2017-01-08 20:31:05','/home/rocky/Develop/job/jianzhi/xieru/test/uploadServer/pom.xml',0,'pom.xml',NULL,1,23,'啊实打实的发生的'),(18,'2017-01-08 20:41:59','/home/rocky/Develop/job/jianzhi/xieru/test/uploadServer/sfk_BBS02.war',10610798,'sfk_BBS02.war',NULL,1,11,'阿斯顿发斯蒂芬'),(19,'2017-01-08 20:57:33','/home/rocky/Develop/job/jianzhi/xieru/test/uploadServer/火狐截图_2016-10-03T02-14-22.493Z.png',147,'火狐截图_2016-10-03T02-14-22.493Z.png',NULL,1,11,''),(22,'2017-01-08 21:43:07','/home/rocky/Develop/job/jianzhi/xieru/test/uploadServer/中医诊断学（供中医、针灸专业用）【邓铁涛】.pdf',7771,'中医诊断学（供中医、针灸专业用）【邓铁涛】.pdf',NULL,1,12,'awsdasdf'),(23,'2017-01-08 21:45:06','/home/rocky/Develop/job/jianzhi/xieru/test/uploadServer/中医诊断学（供中医、针灸专业用）【邓铁涛】.pdf',7771,'中医诊断学（供中医、针灸专业用）【邓铁涛】.pdf',NULL,1,13,'asdfsadf'),(24,'2017-01-08 21:48:14','/home/rocky/Develop/job/jianzhi/xieru/test/uploadServer/中医内科学-第五版.pdf',6875,'中医内科学-第五版.pdf',NULL,1,18,'asdfasdf'),(25,'2017-01-15 17:20:57','/home/rocky/Develop/job/jianzhi/xieru/test/uploadServer/pom.xml',7,'pom.xml',NULL,1,11,'12313');
/*!40000 ALTER TABLE `works` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `works_theme`
--

DROP TABLE IF EXISTS `works_theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `works_theme` (
  `theme_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` longtext,
  `create_date` datetime DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `theme_order` int(11) DEFAULT NULL,
  `sign` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`theme_id`),
  UNIQUE KEY `UK_2hd2t2vnjt9ro6br6hlkmrd5p` (`name`),
  UNIQUE KEY `UK_imkqxml3w59ilm25jiauwsirp` (`sign`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `works_theme`
--

LOCK TABLES `works_theme` WRITE;
/*!40000 ALTER TABLE `works_theme` DISABLE KEYS */;
INSERT INTO `works_theme` VALUES (11,'23阿萨德飞洒qwe','2017-01-15 11:07:53','asdf',23,NULL),(12,'阿斯蒂芬','2017-01-15 11:08:10','qwq恩恩',2,NULL),(13,'阿萨德','2017-01-02 14:25:59','1我',3,NULL),(16,'阿斯蒂芬','2017-01-02 14:27:41','撒旦法',2,NULL),(17,'345','2017-01-02 14:30:18','43',3,NULL),(18,'55','2017-01-02 14:31:02','55',555,NULL),(22,'阿斯蒂芬','2017-01-02 22:42:10','阿斯ss',12,NULL),(23,'阿萨德','2017-01-02 22:53:40','阿斯蒂芬是',33,NULL),(24,'xiexei谢谢诶','2017-01-04 22:27:57','谢谢诶',12,NULL),(25,'同时疼痛','2017-01-04 22:28:28','test',123,NULL),(26,'超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长超长','2017-01-04 22:46:40','超长',34,NULL),(27,'阿萨德','2017-01-05 00:01:25','阿萨德吧',23,NULL);
/*!40000 ALTER TABLE `works_theme` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-02 14:10:43
