/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : mealplanner

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2014-05-09 13:06:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for foodtype
-- ----------------------------
DROP TABLE IF EXISTS `foodtype`;
CREATE TABLE `foodtype` (
  `foodTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `foodTypeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`foodTypeId`),
  UNIQUE KEY `foodTypeId_UNIQUE` (`foodTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='菜品类别:辣，微辣，川菜等';

-- ----------------------------
-- Records of foodtype
-- ----------------------------
INSERT INTO `foodtype` VALUES ('1', '清真菜');
INSERT INTO `foodtype` VALUES ('2', '川菜');
INSERT INTO `foodtype` VALUES ('3', '北京菜');

-- ----------------------------
-- Table structure for friendinfo
-- ----------------------------
DROP TABLE IF EXISTS `friendinfo`;
CREATE TABLE `friendinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL COMMENT '发送好友请求的用户ID',
  `friendId` int(11) DEFAULT NULL COMMENT '被邀请的好友ID',
  `status` int(11) DEFAULT NULL COMMENT '好友请求状态\n0好友请求中\n1: friendId已经同意\n2: friendId拒绝\n3: 已删除\n\n',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='好友信息表';

-- ----------------------------
-- Records of friendinfo
-- ----------------------------
INSERT INTO `friendinfo` VALUES ('1', '1', '2', '1');
INSERT INTO `friendinfo` VALUES ('2', '1', '3', '1');
INSERT INTO `friendinfo` VALUES ('3', '4', '1', '1');
INSERT INTO `friendinfo` VALUES ('4', '5', '6', '1');
INSERT INTO `friendinfo` VALUES ('5', '4', '6', '1');

-- ----------------------------
-- Table structure for mealfriend
-- ----------------------------
DROP TABLE IF EXISTS `mealfriend`;
CREATE TABLE `mealfriend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mealId` int(11) DEFAULT NULL,
  `friendId` int(11) DEFAULT NULL COMMENT '被邀请的用户ID',
  `status` int(11) DEFAULT NULL COMMENT '饭局中被邀请的好友的反馈状态\n0：待接受\n1：已接受\n2：已拒绝\n3：已过期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='饭局中的好友信息';

-- ----------------------------
-- Records of mealfriend
-- ----------------------------
INSERT INTO `mealfriend` VALUES ('1', '1', '3', '1');
INSERT INTO `mealfriend` VALUES ('2', '1', '4', '0');
INSERT INTO `mealfriend` VALUES ('3', '1', '5', '2');
INSERT INTO `mealfriend` VALUES ('4', '1', '6', '0');
INSERT INTO `mealfriend` VALUES ('5', '2', '4', '0');
INSERT INTO `mealfriend` VALUES ('6', '2', '5', '0');
INSERT INTO `mealfriend` VALUES ('7', '5', '2', '0');
INSERT INTO `mealfriend` VALUES ('8', '5', '3', '0');

-- ----------------------------
-- Table structure for mealinfo
-- ----------------------------
DROP TABLE IF EXISTS `mealinfo`;
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

-- ----------------------------
-- Records of mealinfo
-- ----------------------------
INSERT INTO `mealinfo` VALUES ('1', '1', '1', '2014-05-10 00:00:00', '2014-05-06 00:00:00', '0');
INSERT INTO `mealinfo` VALUES ('2', '1', '2', '2014-05-10 00:00:00', '2014-05-06 00:00:00', '0');
INSERT INTO `mealinfo` VALUES ('3', '1', '1', '2010-10-01 00:00:00', '2014-05-06 23:55:51', '0');
INSERT INTO `mealinfo` VALUES ('4', '1', '1', '2010-10-01 00:00:00', '2014-05-07 00:00:10', '0');
INSERT INTO `mealinfo` VALUES ('5', '1', '1', '2010-10-01 00:00:00', '2014-05-07 00:04:11', '0');

-- ----------------------------
-- Table structure for menuinfo
-- ----------------------------
DROP TABLE IF EXISTS `menuinfo`;
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

-- ----------------------------
-- Records of menuinfo
-- ----------------------------
INSERT INTO `menuinfo` VALUES ('1', '1', '椒麻鸡', '25', '1', '1', '1', '1', '1', null);
INSERT INTO `menuinfo` VALUES ('2', '1', '干煸豆角', '18', '1', '1', '1', '1', '1', null);
INSERT INTO `menuinfo` VALUES ('3', '1', '羊肉串', '2', '1', '1', '1', '1', '1', null);

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `mealId` int(11) DEFAULT NULL COMMENT '饭局邀请ID',
  `restId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `contactInfo` varchar(45) DEFAULT NULL COMMENT '联系方式，默认为饭局创建者的手机号码，同时可以修改',
  `actualPeopleNum` int(11) DEFAULT NULL COMMENT '该饭局中的用户数',
  `menuIds` varchar(45) DEFAULT NULL,
  `mealTime` datetime DEFAULT NULL,
  `seatId` int(11) DEFAULT NULL COMMENT '预定的座位ID',
  `operationUserId` int(11) DEFAULT NULL COMMENT '最后操作的用户ID，如管理员，餐厅管理者等',
  `status` int(11) DEFAULT NULL COMMENT '订单状态：\n0：订单提交成功\n1：订单提交失败(无座位)\n2：已经审核通过\n3：被取消\n4：已完成',
  PRIMARY KEY (`orderId`),
  UNIQUE KEY `orderId_UNIQUE` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='订单信息';

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES ('1', '1', '1', '1', '15310582672', '4', '1,2,3,4', '2014-05-10 00:00:00', '1', '1', '3');
INSERT INTO `orderinfo` VALUES ('2', '-1', '1', '3', '15310582675', '2', '1,2', '2014-05-10 00:00:00', '2', '3', '2');
INSERT INTO `orderinfo` VALUES ('3', '-1', '1', '5', '15310582673', '3', '1,2,3', '2014-05-23 17:34:20', null, '5', '0');
INSERT INTO `orderinfo` VALUES ('5', '-1', '1', '5', '15310582673', '3', '1,2,3', '2014-05-23 17:34:20', '1', '5', '0');
INSERT INTO `orderinfo` VALUES ('6', '-1', '1', '4', '15310582670', '3', '1,2,3', '2014-05-23 17:34:20', '3', '4', '0');
INSERT INTO `orderinfo` VALUES ('7', '-1', '1', '5', '15310582673', '3', '1,2,3', '2014-05-23 17:34:20', '4', '5', '0');

-- ----------------------------
-- Table structure for restaurantinfo
-- ----------------------------
DROP TABLE IF EXISTS `restaurantinfo`;
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

-- ----------------------------
-- Records of restaurantinfo
-- ----------------------------
INSERT INTO `restaurantinfo` VALUES ('1', '阿里食府', '010-12345678', '1', '交大东路', 'http://www.baidu.com', '1', '1', '1');
INSERT INTO `restaurantinfo` VALUES ('2', '西贝', '010-12345679', '1', '学苑公寓', 'http://www.google.com', '1', '1', '1');

-- ----------------------------
-- Table structure for restcity
-- ----------------------------
DROP TABLE IF EXISTS `restcity`;
CREATE TABLE `restcity` (
  `cityId` int(11) NOT NULL AUTO_INCREMENT,
  `cityName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cityId`),
  UNIQUE KEY `cityId_UNIQUE` (`cityId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='餐馆所在城市信息';

-- ----------------------------
-- Records of restcity
-- ----------------------------
INSERT INTO `restcity` VALUES ('1', '北京');
INSERT INTO `restcity` VALUES ('2', '上海');
INSERT INTO `restcity` VALUES ('3', '成都');

-- ----------------------------
-- Table structure for resttype
-- ----------------------------
DROP TABLE IF EXISTS `resttype`;
CREATE TABLE `resttype` (
  `restTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `restTypeName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`restTypeId`),
  UNIQUE KEY `restTypeId_UNIQUE` (`restTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='餐馆类别';

-- ----------------------------
-- Records of resttype
-- ----------------------------
INSERT INTO `resttype` VALUES ('1', '清真');
INSERT INTO `resttype` VALUES ('2', '自助');
INSERT INTO `resttype` VALUES ('3', '西北菜');

-- ----------------------------
-- Table structure for restuser
-- ----------------------------
DROP TABLE IF EXISTS `restuser`;
CREATE TABLE `restuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `restId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与餐厅对应关系';

-- ----------------------------
-- Records of restuser
-- ----------------------------

-- ----------------------------
-- Table structure for seatinfo
-- ----------------------------
DROP TABLE IF EXISTS `seatinfo`;
CREATE TABLE `seatinfo` (
  `seatId` int(11) NOT NULL AUTO_INCREMENT,
  `restId` int(11) DEFAULT NULL,
  `seatNo` int(11) DEFAULT NULL COMMENT '座位编号',
  `peopleNum` int(11) DEFAULT NULL COMMENT '最大可容纳多少人',
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`seatId`),
  UNIQUE KEY `seatId_UNIQUE` (`seatId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='店内座位信息';

-- ----------------------------
-- Records of seatinfo
-- ----------------------------
INSERT INTO `seatinfo` VALUES ('1', '1', '1', '4', null);
INSERT INTO `seatinfo` VALUES ('2', '1', '2', '2', null);
INSERT INTO `seatinfo` VALUES ('3', '1', '3', '6', null);
INSERT INTO `seatinfo` VALUES ('4', '1', '4', '8', null);
INSERT INTO `seatinfo` VALUES ('5', '1', '5', '25', null);
INSERT INTO `seatinfo` VALUES ('6', '1', '6', '4', null);

-- ----------------------------
-- Table structure for seatstatus
-- ----------------------------
DROP TABLE IF EXISTS `seatstatus`;
CREATE TABLE `seatstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seatId` int(11) DEFAULT NULL,
  `restId` int(11) DEFAULT NULL,
  `dateDay` varchar(45) DEFAULT NULL COMMENT '预定的日期-天',
  `dateClock` int(11) DEFAULT NULL COMMENT '预定的日期-时(24小时)',
  `state` int(11) DEFAULT NULL COMMENT '座位当前状态\n0：空闲\n1：被预定\n2：用餐中',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='座位当前状态(预定产生的未来状态根据orderinfo/menuinfo/seatinfo来共同决定)';

-- ----------------------------
-- Records of seatstatus
-- ----------------------------
INSERT INTO `seatstatus` VALUES ('1', '1', '1', '2014-05-23', '17', '1');
INSERT INTO `seatstatus` VALUES ('2', '1', '1', '2014-05-23', '18', '1');
INSERT INTO `seatstatus` VALUES ('3', '3', '1', '2014-05-23', '17', '1');
INSERT INTO `seatstatus` VALUES ('4', '3', '1', '2014-05-23', '18', '1');
INSERT INTO `seatstatus` VALUES ('5', '4', '1', '2014-05-23', '17', '1');
INSERT INTO `seatstatus` VALUES ('6', '4', '1', '2014-05-23', '18', '1');

-- ----------------------------
-- Table structure for sequenceinfo
-- ----------------------------
DROP TABLE IF EXISTS `sequenceinfo`;
CREATE TABLE `sequenceinfo` (
  `seqId` int(11) NOT NULL AUTO_INCREMENT,
  `restId` varchar(45) DEFAULT NULL COMMENT '排队的餐厅ID',
  `userId` int(11) DEFAULT NULL COMMENT '用户ID',
  `peopleNum` int(11) DEFAULT NULL COMMENT '人数',
  `seqNo` int(11) DEFAULT NULL COMMENT '排队编号,1-99',
  `seqDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '排队状态\n0：等待，排队中\n1：等待结束，用餐\n2：取消',
  PRIMARY KEY (`seqId`),
  UNIQUE KEY `seqId_UNIQUE` (`seqId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='排队信息(店内排序)';

-- ----------------------------
-- Records of sequenceinfo
-- ----------------------------
INSERT INTO `sequenceinfo` VALUES ('1', '1', '1', '4', '1', '2014-05-09 13:05:58', '0');
INSERT INTO `sequenceinfo` VALUES ('2', '2', '2', '3', '1', '2014-05-09 13:06:05', '0');
INSERT INTO `sequenceinfo` VALUES ('3', '1', '3', '3', '2', '2014-05-09 13:06:12', '0');
INSERT INTO `sequenceinfo` VALUES ('4', '2', '4', '4', '2', '2014-05-09 13:06:15', '0');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
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

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'minxin', 'minxinfeng@gmail.com', '15310582672', '123456', '2014-05-04 23:12:22', '3');
INSERT INTO `userinfo` VALUES ('2', 'fxm', 'fxm@qq.com', '15310582671', 'fxm', '2014-05-04 23:12:22', '3');
INSERT INTO `userinfo` VALUES ('3', 'mm', 'mm@qq.com', '15310582675', 'mm', '2014-05-04 23:24:18', '3');
INSERT INTO `userinfo` VALUES ('4', 'gg', 'gg@qq.com', '15310582670', 'gg', '2014-05-05 14:17:20', '3');
INSERT INTO `userinfo` VALUES ('5', 'yr', 'yr@qq.com', '15310582673', 'yr', '2014-05-05 14:17:20', '3');
INSERT INTO `userinfo` VALUES ('6', 'cyt', 'cyt@qq.com', '15310582674', 'cyt', '2014-05-05 14:17:20', '3');
