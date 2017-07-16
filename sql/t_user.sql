/*
Navicat MySQL Data Transfer

Source Server         : MyData
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : sky_net

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-07-16 15:35:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '用户密码',
  `sex` varchar(2) NOT NULL DEFAULT '男' COMMENT '性别:男或女',
  `phone` bigint(20) NOT NULL DEFAULT '0' COMMENT '手机号码',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '账号状态:0未激活，1激活',
  `email` varchar(20) NOT NULL DEFAULT '' COMMENT '邮箱',
  `updatetime` datetime NOT NULL COMMENT '最近更新时间',
  `authcode` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最新的验证码',
  `accoutType` int(10) NOT NULL DEFAULT '0' COMMENT '账号类型：8普通用户 9管理员 10超级管理员',
  `name` varchar(20) NOT NULL,
  `onlineaddress` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `userName_index` (`userName`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'hxj', '123', '男', '123456789', '1', 'xxxxxxxx@qq.com', '2017-07-16 15:20:16', '', '9', '黄孝君', '0:0:0:0:0:0:0:1');
