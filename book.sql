/*
Navicat MySQL Data Transfer

Source Server         : rempte-mysql
Source Server Version : 80018
Source Host           : 111.229.145.9:3306
Source Database       : javaweb

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-02-12 19:23:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键，自增',
  `name` varchar(50) NOT NULL COMMENT '书名',
  `author` varchar(50) NOT NULL COMMENT '作者',
  `price` double NOT NULL COMMENT '价格',
  `type` varchar(10) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `description` varchar(128) DEFAULT NULL,
  `sbn` varchar(32) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='书籍表';

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('10', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 00:00:00');
INSERT INTO `book` VALUES ('11', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('12', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('13', '千年一叹', '余秋雨', '666', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 00:00:00');
INSERT INTO `book` VALUES ('14', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('15', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('16', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('17', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('18', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('19', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('20', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('21', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('22', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('23', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('24', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('25', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('26', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('27', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('28', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('29', '围城', '钱钟书', '45', '文学', '0', '这是一本文学书', '1226333', '2020-02-09 11:01:09');
INSERT INTO `book` VALUES ('30', '围城', '钱钟书', '45', '文学', '2', '这是一本文学书', '1226333', '2020-02-09 00:00:00');
