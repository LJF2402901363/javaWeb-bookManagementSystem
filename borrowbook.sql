/*
Navicat MySQL Data Transfer

Source Server         : rempte-mysql
Source Server Version : 80018
Source Host           : 111.229.145.9:3306
Source Database       : javaweb

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-02-12 19:23:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for borrowbook
-- ----------------------------
DROP TABLE IF EXISTS `borrowbook`;
CREATE TABLE `borrowbook` (
  `accountid` int(11) DEFAULT NULL,  
  `bookid` int(11) DEFAULT NULL,
  `borrowtime` datetime DEFAULT NULL,
  `returntime` datetime DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrowbook
-- ----------------------------
INSERT INTO `borrowbook` VALUES ('1', '4', '2020-02-09 19:39:56', '2020-03-07 19:40:24', '1');
INSERT INTO `borrowbook` VALUES ('1', '1', '2020-02-09 19:39:56', '2020-02-09 19:39:58', '2');
INSERT INTO `borrowbook` VALUES ('1', '8', '2020-02-09 19:39:56', '2020-02-09 19:39:58', '3');
INSERT INTO `borrowbook` VALUES ('1', '2', '2020-02-09 19:39:56', '2020-02-09 19:39:58', '4');
INSERT INTO `borrowbook` VALUES ('19', '3', '2020-02-09 16:32:24', null, '6');
INSERT INTO `borrowbook` VALUES ('19', '5', '2020-02-10 07:47:11', null, '7');
INSERT INTO `borrowbook` VALUES ('19', '6', '2020-02-10 08:12:51', null, '8');
INSERT INTO `borrowbook` VALUES ('27', '30', '2020-02-12 11:07:16', null, '9');
