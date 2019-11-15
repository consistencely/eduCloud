/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : educloud_system

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2019-11-14 18:09:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `educloud_grade`
-- ----------------------------
DROP TABLE IF EXISTS `educloud_grade`;
CREATE TABLE `educloud_grade` (
  `grade_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(32) NOT NULL COMMENT '学段名称',
  `sort` int(11) NOT NULL DEFAULT '99' COMMENT '排序',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0：正常，1：删除',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COMMENT='学段信息';

-- ----------------------------
-- Records of educloud_grade
-- ----------------------------
INSERT INTO `educloud_grade` VALUES ('2', '高二', '2', '0', '2019-10-30 11:14:38', '2019-10-30 11:14:38');
INSERT INTO `educloud_grade` VALUES ('101', '高一', '1', '0', '2019-11-11 16:23:07', '2019-10-30 11:14:30');
INSERT INTO `educloud_grade` VALUES ('105', '高三', '3', '0', '2019-11-11 16:23:10', '2019-10-30 11:14:43');

-- ----------------------------
-- Table structure for `educloud_kpoint`
-- ----------------------------
DROP TABLE IF EXISTS `educloud_kpoint`;
CREATE TABLE `educloud_kpoint` (
  `kpoint_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父级ID',
  `kpoint_name` varchar(64) NOT NULL COMMENT '考点名称',
  `grade_id` int(11) NOT NULL COMMENT '学段ID',
  `subject_id` int(11) NOT NULL COMMENT '学科ID',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '第几级',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0：正常，1：删除）',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`kpoint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='考点';

-- ----------------------------
-- Records of educloud_kpoint
-- ----------------------------
INSERT INTO `educloud_kpoint` VALUES ('27', '0', '考点1', '1', '1', '1', '0', '2019-10-30 15:44:27', '2019-10-30 15:44:27');

-- ----------------------------
-- Table structure for `educloud_subject`
-- ----------------------------
DROP TABLE IF EXISTS `educloud_subject`;
CREATE TABLE `educloud_subject` (
  `subject_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(32) NOT NULL COMMENT '学科名称',
  `sort` int(11) NOT NULL DEFAULT '99' COMMENT '排序',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0：正常，1：删除',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='学科信息';

-- ----------------------------
-- Records of educloud_subject
-- ----------------------------
INSERT INTO `educloud_subject` VALUES ('1', '数学', '1', '0', '2019-10-29 17:19:38', '2019-10-28 15:44:26');
INSERT INTO `educloud_subject` VALUES ('2', '语文', '2', '0', '2019-10-29 17:19:36', '2019-10-29 17:19:36');

-- ----------------------------
-- Table structure for `educloud_subject_grade`
-- ----------------------------
DROP TABLE IF EXISTS `educloud_subject_grade`;
CREATE TABLE `educloud_subject_grade` (
  `grade_id` int(11) NOT NULL COMMENT '年级ID',
  `subject_id` int(11) NOT NULL COMMENT '学科ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '关联时间',
  PRIMARY KEY (`grade_id`,`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='年级_学科关联表';

-- ----------------------------
-- Records of educloud_subject_grade
-- ----------------------------
INSERT INTO `educloud_subject_grade` VALUES ('1', '1', '2019-10-29 17:21:32');
INSERT INTO `educloud_subject_grade` VALUES ('1', '2', '2019-10-29 17:21:34');
