/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : educloud_user

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-11-11 23:33:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `educloud_role`
-- ----------------------------
DROP TABLE IF EXISTS `educloud_role`;
CREATE TABLE `educloud_role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：未删除，1：已删除）',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of educloud_role
-- ----------------------------
INSERT INTO `educloud_role` VALUES ('101', '管理员', '管理员', '0', '2019-11-11 16:22:39', '2019-11-06 10:28:09');
INSERT INTO `educloud_role` VALUES ('102', '学生', '学生', '0', '2019-11-11 16:22:41', '2019-11-06 10:28:15');

-- ----------------------------
-- Table structure for `educloud_user`
-- ----------------------------
DROP TABLE IF EXISTS `educloud_user`;
CREATE TABLE `educloud_user` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(16) NOT NULL COMMENT '系统账号',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(16) NOT NULL COMMENT '昵称',
  `source_id` int(11) DEFAULT NULL COMMENT '生源ID',
  `organization_id` int(11) NOT NULL COMMENT '机构ID',
  `register_from` tinyint(1) NOT NULL DEFAULT '1' COMMENT '注册来源（1：PC端，2：移动端，3：手机浏览器，4：管理员后台创建）',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `school` varchar(128) DEFAULT NULL COMMENT '就读学校',
  `uuid` varchar(16) DEFAULT NULL COMMENT '随机字符串',
  `expire_time` date DEFAULT NULL COMMENT '过期日期',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号状态（0：注销，1：正常，2：冻结）',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uni_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of educloud_user
-- ----------------------------
INSERT INTO `educloud_user` VALUES ('1', 'XG_25fegfyd5678', '13112345678', '123456', 'http://www.baidu.com', '5678', '6', '88', '4', '2019-11-08', '家里蹲大学', null, null, '1', '2019-11-08 17:01:15', '2019-11-11 15:17:50');
INSERT INTO `educloud_user` VALUES ('2', 'XG_0z3s46135678', '13112345678', 'e10adc3949ba59abbe56e057f20f883e', 'http://www.baidu.com', '5678', '6', '88', '4', null, null, null, null, '1', '2019-11-08 17:11:39', '2019-11-11 15:17:50');

-- ----------------------------
-- Table structure for `educloud_user_address`
-- ----------------------------
DROP TABLE IF EXISTS `educloud_user_address`;
CREATE TABLE `educloud_user_address` (
  `address_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '地址ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `province` varchar(16) DEFAULT NULL COMMENT '省',
  `city` varchar(16) DEFAULT NULL COMMENT '市',
  `county` varchar(16) DEFAULT NULL COMMENT '县',
  `addr_detail` varchar(64) DEFAULT NULL COMMENT '详细地址',
  `tel` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：未删除，1：已删除）',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户地址表';

-- ----------------------------
-- Records of educloud_user_address
-- ----------------------------
INSERT INTO `educloud_user_address` VALUES ('1', '1', '广东省', '广州市', '天河区', '穗丰大厦A座803', '8008208820', '0', '2019-11-08 17:01:14', '2019-11-08 17:01:14');
INSERT INTO `educloud_user_address` VALUES ('2', '1', null, null, null, null, null, '0', '2019-11-11 11:51:17', '2019-11-08 17:11:38');

-- ----------------------------
-- Table structure for `educloud_user_grade`
-- ----------------------------
DROP TABLE IF EXISTS `educloud_user_grade`;
CREATE TABLE `educloud_user_grade` (
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `grade_id` int(11) NOT NULL COMMENT '年级ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '关联时间',
  PRIMARY KEY (`user_id`,`grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户年级表';

-- ----------------------------
-- Records of educloud_user_grade
-- ----------------------------
INSERT INTO `educloud_user_grade` VALUES ('1', '101', '2019-11-11 15:27:50');
INSERT INTO `educloud_user_grade` VALUES ('1', '105', '2019-11-11 15:27:50');
INSERT INTO `educloud_user_grade` VALUES ('2', '101', '2019-11-11 15:27:50');
INSERT INTO `educloud_user_grade` VALUES ('2', '105', '2019-11-11 15:27:50');
INSERT INTO `educloud_user_grade` VALUES ('1001', '4', '2019-11-08 16:11:01');
INSERT INTO `educloud_user_grade` VALUES ('1001', '5', '2019-11-08 16:11:01');

-- ----------------------------
-- Table structure for `educloud_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `educloud_user_role`;
CREATE TABLE `educloud_user_role` (
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '关联时间',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of educloud_user_role
-- ----------------------------
INSERT INTO `educloud_user_role` VALUES ('1', '101', '2019-11-08 17:01:14');
INSERT INTO `educloud_user_role` VALUES ('1', '102', '2019-11-11 16:54:37');
INSERT INTO `educloud_user_role` VALUES ('2', '101', '2019-11-08 17:11:38');
INSERT INTO `educloud_user_role` VALUES ('1001', '3', '2019-11-08 16:07:49');
INSERT INTO `educloud_user_role` VALUES ('1001', '101', '2019-11-08 16:07:49');
INSERT INTO `educloud_user_role` VALUES ('1001', '102', '2019-11-08 16:07:49');
