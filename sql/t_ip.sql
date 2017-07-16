/*
Navicat MySQL Data Transfer

Source Server         : MyData
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : sky_net

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-07-16 15:35:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_ip
-- ----------------------------
DROP TABLE IF EXISTS `t_ip`;
CREATE TABLE `t_ip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hostName` varchar(50) NOT NULL DEFAULT '',
  `address` varchar(128) NOT NULL DEFAULT '',
  `status` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_ip
-- ----------------------------
INSERT INTO `t_ip` VALUES ('1', 'huangxiaojun_pc', '192.168.0.108', '1');
INSERT INTO `t_ip` VALUES ('2', 'huangxiaojun_pc', '127.0.0.1', '1');
INSERT INTO `t_ip` VALUES ('3', 'lijiangyun_pc', '192.168.0.1', '1');
