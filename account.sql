/*
Navicat MySQL Data Transfer

Source Server         : rempte-mysql
Source Server Version : 80018
Source Host           : 111.229.145.9:3306
Source Database       : javaweb

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-02-12 19:23:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键，自增',
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(50) NOT NULL COMMENT '密码',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT '0',
  `type` int(11) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `hobby` varchar(128) DEFAULT NULL,
  `signature` varchar(128) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('19', 'b', 'b', '2020-02-09 00:00:00', '0', '2', '中性', '打篮球', '我的世界我做主', '54');
INSERT INTO `account` VALUES ('21', 'a', 'a', '2020-02-09 11:04:55', '0', '3', '男', '打篮球', '我是一只小小鸟', '54');
INSERT INTO `account` VALUES ('22', 'ee', 'a', '2020-02-09 11:04:55', '0', '1', '男', '打篮球', '我是一只小小鸟', '54');
INSERT INTO `account` VALUES ('23', 'ff', 'a', '2020-02-09 00:00:00', '0', '2', '男性', '打篮球', '你是狗吗？', '54');
INSERT INTO `account` VALUES ('24', 'gg', 'a', '2020-02-09 00:00:00', '0', '1', '男性', '的的', '的的', '54');
INSERT INTO `account` VALUES ('25', 'tt', 'a', '2020-02-09 00:00:00', '0', '1', '男性', 'dd', 'dd', '54');
INSERT INTO `account` VALUES ('26', 'jj', 'a', '2020-02-09 11:04:55', '0', '1', '男', '打篮球', '我是一只小小鸟', '54');
INSERT INTO `account` VALUES ('27', 't', 't', '2020-02-12 11:06:36', '0', '2', '男性', null, null, '0');
