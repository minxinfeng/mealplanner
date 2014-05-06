CREATE DATABASE  IF NOT EXISTS `mealplanner` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mealplanner`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: mealplanner
-- ------------------------------------------------------
-- Server version	5.5.15

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
-- Table structure for table `foodtype`
--

DROP TABLE IF EXISTS `foodtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `foodtype` (
  `foodTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `foodTypeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`foodTypeId`),
  UNIQUE KEY `foodTypeId_UNIQUE` (`foodTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='菜品类别:辣，微辣，川菜等';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodtype`
--

LOCK TABLES `foodtype` WRITE;
/*!40000 ALTER TABLE `foodtype` DISABLE KEYS */;
INSERT INTO `foodtype` VALUES (1,'北京菜'),(2,'川菜');
/*!40000 ALTER TABLE `foodtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendinfo`
--

DROP TABLE IF EXISTS `friendinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friendinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL COMMENT '发送好友请求的用户ID',
  `friendId` int(11) DEFAULT NULL COMMENT '被邀请的好友ID',
  `status` int(11) DEFAULT NULL COMMENT '好友请求状态\n0好友请求中\n1: friendId已经同意\n2: friendId拒绝\n3: 已删除\n\n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='好友信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendinfo`
--

LOCK TABLES `friendinfo` WRITE;
/*!40000 ALTER TABLE `friendinfo` DISABLE KEYS */;
INSERT INTO `friendinfo` VALUES (1,1,2,1),(2,1,3,1),(3,4,1,1),(4,5,6,1),(5,4,6,1);
/*!40000 ALTER TABLE `friendinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mealfriend`
--

DROP TABLE IF EXISTS `mealfriend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mealfriend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mealId` int(11) DEFAULT NULL,
  `friendId` int(11) DEFAULT NULL COMMENT '被邀请的用户ID',
  `status` int(11) DEFAULT NULL COMMENT '饭局中被邀请的好友的反馈状态\n0：待接受\n1：已接受\n2：已拒绝\n3：已过期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='饭局中的好友信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mealfriend`
--

LOCK TABLES `mealfriend` WRITE;
/*!40000 ALTER TABLE `mealfriend` DISABLE KEYS */;
INSERT INTO `mealfriend` VALUES (1,1,3,1),(2,1,4,0),(3,1,5,2),(4,1,6,0),(5,2,4,0),(6,2,5,0),(7,5,2,0),(8,5,3,0);
/*!40000 ALTER TABLE `mealfriend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mealinfo`
--

DROP TABLE IF EXISTS `mealinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mealinfo` (
  `mealId` int(11) NOT NULL AUTO_INCREMENT,
  `mealOrganizeUserId` int(11) DEFAULT NULL COMMENT '组织者的用户ID',
  `restId` int(11) DEFAULT NULL COMMENT '餐厅ID',
  `mealTime` datetime DEFAULT NULL COMMENT '开始用餐时间',
  `organizationTime` datetime DEFAULT NULL COMMENT '发起邀请时间',
  `mealStatus` int(11) DEFAULT NULL COMMENT '饭局状态\n0：正在进行\n1：成功，已下订单\n2：过期',
  PRIMARY KEY (`mealId`),
  UNIQUE KEY `mealId_UNIQUE` (`mealId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='饭局邀请';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mealinfo`
--

LOCK TABLES `mealinfo` WRITE;
/*!40000 ALTER TABLE `mealinfo` DISABLE KEYS */;
INSERT INTO `mealinfo` VALUES (1,1,1,'2014-05-10 00:00:00','2014-05-06 00:00:00',0),(2,1,2,'2014-05-10 00:00:00','2014-05-06 00:00:00',0),(3,1,1,'2010-10-01 00:00:00','2014-05-06 23:55:51',0),(4,1,1,'2010-10-01 00:00:00','2014-05-07 00:00:10',0),(5,1,1,'2010-10-01 00:00:00','2014-05-07 00:04:11',0);
/*!40000 ALTER TABLE `mealinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menuinfo`
--

DROP TABLE IF EXISTS `menuinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menuinfo` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `restId` int(11) DEFAULT NULL COMMENT '餐馆ID',
  `menuName` varchar(45) DEFAULT NULL COMMENT '菜名',
  `menuPrice` double DEFAULT NULL COMMENT '菜价',
  `foodType` int(11) DEFAULT NULL COMMENT '食物类型',
  `searchTime` int(11) DEFAULT NULL COMMENT '搜索次数',
  `orderTime` int(11) DEFAULT NULL COMMENT '点的次数',
  `recommand` int(11) DEFAULT NULL COMMENT '推荐菜品\n1：推荐\n0：非推荐',
  `hot` int(11) DEFAULT NULL COMMENT '热门菜品\n0：非热门\n1：热门',
  `foodUrl` varchar(255) DEFAULT NULL COMMENT '食物图片对应的URL',
  PRIMARY KEY (`menuId`),
  UNIQUE KEY `menuId_UNIQUE` (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='菜单信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menuinfo`
--

LOCK TABLES `menuinfo` WRITE;
/*!40000 ALTER TABLE `menuinfo` DISABLE KEYS */;
INSERT INTO `menuinfo` VALUES (1,1,'宫保鸡丁',20,1,0,0,1,1,NULL),(2,1,'干煸豆角',18,1,1,1,1,1,NULL),(3,2,'test',48,2,NULL,NULL,1,1,NULL);
/*!40000 ALTER TABLE `menuinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderinfo`
--

DROP TABLE IF EXISTS `orderinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderinfo` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `mealId` int(11) DEFAULT NULL COMMENT '饭局邀请ID',
  `restId` int(11) DEFAULT NULL,
  `contactInfo` varchar(45) DEFAULT NULL COMMENT '联系方式，默认为饭局创建者的手机号码，同时可以修改',
  `actualPeopleNum` int(11) DEFAULT NULL COMMENT '该饭局中的用户数',
  `menuIds` varchar(45) DEFAULT NULL,
  `mealTime` datetime DEFAULT NULL,
  `seatId` int(11) DEFAULT NULL COMMENT '预定的座位ID',
  `operationUserId` int(11) DEFAULT NULL COMMENT '最后操作的用户ID，如管理员，餐厅管理者等',
  `status` int(11) DEFAULT NULL COMMENT '订单状态：\n0：订单提交成功\n1：订单提交失败(无座位)\n2：已经审核通过\n3：被取消\n4：已完成',
  PRIMARY KEY (`orderId`),
  UNIQUE KEY `orderId_UNIQUE` (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderinfo`
--

LOCK TABLES `orderinfo` WRITE;
/*!40000 ALTER TABLE `orderinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurantinfo`
--

DROP TABLE IF EXISTS `restaurantinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurantinfo` (
  `restId` int(11) NOT NULL AUTO_INCREMENT,
  `restName` varchar(45) DEFAULT NULL,
  `restPhone` varchar(45) DEFAULT NULL,
  `restCity` int(11) DEFAULT NULL COMMENT '餐厅所在城市',
  `restAddress` varchar(45) DEFAULT NULL COMMENT '详细地址',
  `restWebsite` varchar(45) DEFAULT NULL,
  `restType` int(11) DEFAULT NULL COMMENT '餐厅类型：如北京菜，川菜，火锅',
  `restSearchTime` varchar(45) DEFAULT '0' COMMENT '搜索次数',
  `hot` int(11) DEFAULT NULL COMMENT '是否是热门餐厅\n1 热门\n0 非热门',
  PRIMARY KEY (`restId`),
  UNIQUE KEY `restId_UNIQUE` (`restId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='餐厅信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurantinfo`
--

LOCK TABLES `restaurantinfo` WRITE;
/*!40000 ALTER TABLE `restaurantinfo` DISABLE KEYS */;
INSERT INTO `restaurantinfo` VALUES (1,'天外天','111',1,'111','111',1,'1',1),(2,'西门','1',1,'1','1',1,'1',1);
/*!40000 ALTER TABLE `restaurantinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restcity`
--

DROP TABLE IF EXISTS `restcity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restcity` (
  `cityId` int(11) NOT NULL AUTO_INCREMENT,
  `cityName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cityId`),
  UNIQUE KEY `cityId_UNIQUE` (`cityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='餐馆所在城市信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restcity`
--

LOCK TABLES `restcity` WRITE;
/*!40000 ALTER TABLE `restcity` DISABLE KEYS */;
/*!40000 ALTER TABLE `restcity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resttype`
--

DROP TABLE IF EXISTS `resttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resttype` (
  `restTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `restTypeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`restTypeId`),
  UNIQUE KEY `restTypeId_UNIQUE` (`restTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='餐馆类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resttype`
--

LOCK TABLES `resttype` WRITE;
/*!40000 ALTER TABLE `resttype` DISABLE KEYS */;
INSERT INTO `resttype` VALUES (1,'自助');
/*!40000 ALTER TABLE `resttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seatinfo`
--

DROP TABLE IF EXISTS `seatinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seatinfo` (
  `seatId` int(11) NOT NULL AUTO_INCREMENT,
  `restId` int(11) DEFAULT NULL,
  `seatNo` int(11) DEFAULT NULL COMMENT '座位编号',
  `peopleNum` int(11) DEFAULT NULL COMMENT '最大可容纳多少人',
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`seatId`),
  UNIQUE KEY `seatId_UNIQUE` (`seatId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店内座位信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seatinfo`
--

LOCK TABLES `seatinfo` WRITE;
/*!40000 ALTER TABLE `seatinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `seatinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seatstatus`
--

DROP TABLE IF EXISTS `seatstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seatstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seatId` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '座位当前状态\n0：空闲\n1：被预定\n2：用餐中',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='座位当前状态(预定产生的未来状态根据orderinfo/menuinfo/seatinfo来共同决定)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seatstatus`
--

LOCK TABLES `seatstatus` WRITE;
/*!40000 ALTER TABLE `seatstatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `seatstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequenceinfo`
--

DROP TABLE IF EXISTS `sequenceinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequenceinfo` (
  `seqId` int(11) NOT NULL AUTO_INCREMENT,
  `restId` varchar(45) DEFAULT NULL COMMENT '排队的餐厅ID',
  `peopleNum` int(11) DEFAULT NULL COMMENT '人数',
  `userId` int(11) DEFAULT NULL COMMENT '用户ID',
  `seqDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '排队状态\n0：等待，排队中\n1：等待结束，用餐\n2：取消',
  PRIMARY KEY (`seqId`),
  UNIQUE KEY `seqId_UNIQUE` (`seqId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='排队信息(店内排序)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequenceinfo`
--

LOCK TABLES `sequenceinfo` WRITE;
/*!40000 ALTER TABLE `sequenceinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `sequenceinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinfo` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phoneNum` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `registerDate` datetime DEFAULT NULL,
  `userType` int(11) DEFAULT NULL COMMENT '用户类型，1是系统管理员，2是餐厅管理员，3是普通用户',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId_UNIQUE` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,'minxin',NULL,'15210582672','123456',NULL,1),(2,'fxm','111','111111','111','2014-05-04 23:12:22',2),(3,'1','1','1','1','2014-05-04 23:24:18',3),(4,'11','11','11','11','2014-05-05 14:17:20',3),(5,'yr','yr','yr','yr',NULL,3),(6,'cyt','cyt','cyt','cyt',NULL,3);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-07  0:14:22
