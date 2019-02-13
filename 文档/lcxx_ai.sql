/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : lcxx_ai

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-02-13 17:04:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_assessment
-- ----------------------------
DROP TABLE IF EXISTS `tb_assessment`;
CREATE TABLE `tb_assessment` (
  `project_id` int(11) DEFAULT NULL COMMENT '项目标识',
  `cid` int(11) DEFAULT NULL COMMENT '沟通标识',
  `rid` int(11) DEFAULT NULL COMMENT '策略标识',
  `phone` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `flag` int(4) DEFAULT NULL COMMENT '沟通顺序',
  `question` varchar(512) DEFAULT NULL COMMENT '问题',
  `answer` varchar(256) DEFAULT NULL COMMENT '回答',
  `keyword` varchar(32) DEFAULT NULL COMMENT '关键词',
  `assessment` int(2) DEFAULT '0' COMMENT '评估  0中立  1否定 2 肯定'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='沟通分析';

-- ----------------------------
-- Table structure for tb_communicate
-- ----------------------------
DROP TABLE IF EXISTS `tb_communicate`;
CREATE TABLE `tb_communicate` (
  `cid` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增标识',
  `project_id` int(8) DEFAULT NULL COMMENT '归属项目',
  `use_code` varchar(128) DEFAULT NULL COMMENT '画像代码',
  `call_from` varchar(16) DEFAULT NULL COMMENT '主叫号码',
  `phone` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `name` varchar(64) DEFAULT NULL COMMENT '联系人',
  `company` varchar(128) DEFAULT NULL COMMENT '公司名称',
  `call_date` datetime DEFAULT NULL COMMENT '呼叫时间',
  `call_length` int(8) DEFAULT NULL COMMENT '拨打时长',
  `call_times` int(4) DEFAULT NULL COMMENT '呼叫次数',
  `user_type` varchar(8) DEFAULT NULL COMMENT '用户类型',
  `note` varchar(256) DEFAULT NULL,
  `content` mediumblob COMMENT '沟通内容',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修订时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=7607 DEFAULT CHARSET=utf8 COMMENT='沟通详情';

-- ----------------------------
-- Table structure for tb_contacts
-- ----------------------------
DROP TABLE IF EXISTS `tb_contacts`;
CREATE TABLE `tb_contacts` (
  `contacts_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '联系人标识',
  `project_id` int(11) DEFAULT NULL COMMENT '项目标识',
  `status` int(2) DEFAULT '0' COMMENT '状态 1已呼',
  `contacts` varchar(32) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `company` varchar(64) DEFAULT NULL COMMENT '公司名称',
  `gmt_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修订时间',
  PRIMARY KEY (`contacts_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4905 DEFAULT CHARSET=utf8 COMMENT='项目联系人信息';

-- ----------------------------
-- Table structure for tb_project
-- ----------------------------
DROP TABLE IF EXISTS `tb_project`;
CREATE TABLE `tb_project` (
  `project_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '项目标识',
  `project_name` varchar(64) NOT NULL COMMENT '项目名称',
  `total_num` int(11) DEFAULT '0' COMMENT '总量',
  `connected_num` int(11) DEFAULT '0' COMMENT '接通数量',
  `sure_num` int(11) DEFAULT '0' COMMENT '肯定数量',
  `negative_num` int(11) DEFAULT '0' COMMENT '否定数量',
  `neutral_num` int(11) DEFAULT '0' COMMENT '中立数量',
  `s_code` varchar(8) DEFAULT NULL COMMENT '统计代码',
  `note` varchar(256) DEFAULT NULL COMMENT '备注',
  `gmt_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修订时间',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='AI项目';

-- ----------------------------
-- Table structure for tb_regulations
-- ----------------------------
DROP TABLE IF EXISTS `tb_regulations`;
CREATE TABLE `tb_regulations` (
  `regulations_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '话术标识',
  `project_id` int(11) DEFAULT NULL COMMENT '项目标识',
  `regulations` varchar(64) DEFAULT NULL COMMENT '话术',
  `content` varchar(512) DEFAULT NULL COMMENT '内容',
  `uscode` varchar(8) DEFAULT NULL COMMENT '画像代码',
  `usorder` varchar(8) DEFAULT NULL COMMENT '识别顺序',
  `as_num` int(11) DEFAULT '0' COMMENT '回复总量',
  `as_yes` varchar(512) DEFAULT NULL COMMENT '肯定用词',
  `as_yes_num` int(11) DEFAULT '0' COMMENT '肯定回复总量',
  `as_no` varchar(512) DEFAULT NULL COMMENT '否定用词',
  `as_no_num` int(11) DEFAULT '0' COMMENT '否定回复总量',
  `as_other` varchar(512) DEFAULT NULL COMMENT '其它用词',
  `as_other_num` int(11) DEFAULT '0' COMMENT '其它用词总量',
  `gmt_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修订时间',
  PRIMARY KEY (`regulations_id`)
) ENGINE=InnoDB AUTO_INCREMENT=766 DEFAULT CHARSET=utf8 COMMENT='项目流程话术';
