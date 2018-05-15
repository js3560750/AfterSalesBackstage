/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : aftersales

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-05-15 09:53:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员唯一标识id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录修改时间'',''如果记录没有修改'',''gmt_create和gmt_modified一致',
  `account` varchar(16) DEFAULT NULL COMMENT '管理员登录账号',
  `authority` varchar(255) DEFAULT NULL COMMENT '权限',
  `password` varchar(255) DEFAULT NULL COMMENT '管理员登录密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account` (`account`),
  KEY `pk_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '2018-02-06 10:47:48', '2018-02-06 10:47:48', '18571686931', 'ROLE_ADMIN', '$2a$10$lY/YLkIVz8.NYJ4epqNe6ecFbXI2x3Gv.KG9qlW4gR2AYmJ4xF5mG');
INSERT INTO `admin` VALUES ('5', null, null, '34234', 'ROLE_USER', '$2a$10$mWKNAbP55Wa.jiADHJZOBO9K1xm/M92Tj07ej64ObjO9MBCPtl3v.');
INSERT INTO `admin` VALUES ('6', null, '2018-04-09 10:52:56', '11111111', 'ROLE_USER', '$2a$10$vQmVPowMWprjdBGGnEbbxurcOKenEb7F88Y0vPnc8muRX5BEWjVAW');
INSERT INTO `admin` VALUES ('8', '2018-04-10 10:28:09', '2018-04-10 10:28:09', 'admin', 'ROLE_ADMIN', '$2a$10$aYjlFegw/PzN9rb4fOHSCeiTUD21u6C2NWVpieNGFmrRl3r57ZQDS');
INSERT INTO `admin` VALUES ('9', '2018-04-10 10:42:49', '2018-04-10 10:42:49', 'hello', 'ROLE_USER', '$2a$10$TUGtAIaEOO5t5UdYpX5uCuxbpFMz4XdTaNm5VfHR7O4NNU95KwyOi');

-- ----------------------------
-- Table structure for engineer
-- ----------------------------
DROP TABLE IF EXISTS `engineer`;
CREATE TABLE `engineer` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '工程师唯一标识id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录最近修改时间',
  `engineer` varchar(50) DEFAULT NULL COMMENT '工程师姓名',
  `tel` varchar(16) DEFAULT NULL COMMENT '工程师电话',
  `account` varchar(16) DEFAULT NULL COMMENT '工程师登录账号',
  `password` varchar(16) DEFAULT NULL COMMENT '工程师登录密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_engineer` (`engineer`),
  UNIQUE KEY `uk_account` (`account`),
  KEY `pk_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of engineer
-- ----------------------------
INSERT INTO `engineer` VALUES ('4', '2018-02-06 10:47:03', '2018-02-06 10:47:03', '天使踩麻木', '185716869366', '185716869312', '123456');
INSERT INTO `engineer` VALUES ('5', '2018-02-06 10:47:16', '2018-02-06 10:47:16', '黎未央', '18571686934', '18571686934', '123456');
INSERT INTO `engineer` VALUES ('6', '2018-02-06 10:47:32', '2018-02-06 10:47:32', '幻觉肆虐', '15571686935', '15571686935', '123456');
INSERT INTO `engineer` VALUES ('8', '2018-02-06 14:43:57', '2018-02-06 14:43:57', '哈哈哈', '18871686931', '18871686931', '123456');
INSERT INTO `engineer` VALUES ('9', '2018-02-06 14:48:46', '2018-02-06 14:48:46', '嘻嘻嘻', '18871686931', '18871686932', '123456');
INSERT INTO `engineer` VALUES ('10', '2018-04-09 09:45:09', '2018-04-09 09:45:09', 'helloworld', '185716386931', '123456789', '88888888');
INSERT INTO `engineer` VALUES ('14', '2018-04-09 10:48:06', '2018-04-09 10:48:06', '4333333', 'gg33', 'g5g4', '4g35');
INSERT INTO `engineer` VALUES ('17', '2018-04-23 17:40:07', '2018-04-23 17:40:07', '报修接收号码', '18571686931', '18571686931', '123456');

-- ----------------------------
-- Table structure for error
-- ----------------------------
DROP TABLE IF EXISTS `error`;
CREATE TABLE `error` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '故障表唯一标识id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录修改时间',
  `serial_number` varchar(255) DEFAULT NULL COMMENT '产品序列号',
  `time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '故障日期',
  `phenomenon` varchar(255) DEFAULT NULL COMMENT '故障现象',
  `reason` varchar(255) DEFAULT NULL COMMENT '故障原因',
  `solution` varchar(255) DEFAULT NULL COMMENT '解决方案',
  `product_model` varchar(255) DEFAULT NULL COMMENT '产品型号',
  `error_type` varchar(255) DEFAULT NULL COMMENT '故障种类\r\n电子\r\n软件\r\n机构\r\n拍照\r\n自动识别\r\n系统\r\n其他',
  `engineer` varchar(255) DEFAULT NULL COMMENT '维修工程师姓名',
  `product_version` varchar(255) DEFAULT NULL COMMENT '产品版本',
  `replace_name_code` varchar(255) DEFAULT NULL COMMENT '更换备件名称及编号',
  `month_sample_count` int(11) DEFAULT NULL COMMENT '月表标本量总数',
  `day_sample_count` int(11) DEFAULT NULL COMMENT '日表标本量总数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_id` (`id`),
  KEY `uk_serial_number` (`serial_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of error
-- ----------------------------
INSERT INTO `error` VALUES ('1', '2018-04-09 11:20:18', '2018-04-09 11:20:18', '22c2d2', '2018-04-09 11:20:15', 'h7', '32d', null, null, null, null, null, null, null, null);
INSERT INTO `error` VALUES ('2', '2018-04-25 15:54:53', '2018-04-25 15:54:53', '666', '2018-04-23 17:44:16', '8484', '8484946', '4646516', '666', '自动识别', '报修接收号码', '888888', '1651', '1515', '66');

-- ----------------------------
-- Table structure for factory
-- ----------------------------
DROP TABLE IF EXISTS `factory`;
CREATE TABLE `factory` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '出厂唯一标识Id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录修改时间',
  `serial_number` varchar(255) DEFAULT NULL COMMENT '仪器序列号本台仪器编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `product_model` varchar(255) DEFAULT NULL COMMENT '产品型号',
  `picking_number` varchar(255) DEFAULT NULL COMMENT '领料批号',
  `batch_number` varchar(255) DEFAULT NULL COMMENT '批数量',
  `batch_code` varchar(255) DEFAULT NULL COMMENT '批次编号',
  `produce_code` varchar(255) DEFAULT NULL COMMENT '生产批号',
  `product_version` varchar(255) DEFAULT NULL COMMENT '产品版本',
  `software_version` varchar(255) DEFAULT NULL COMMENT '软件版本',
  `structure_version` varchar(255) DEFAULT NULL COMMENT '结构版本',
  `electronic_version` varchar(255) DEFAULT NULL COMMENT '电子版本',
  `algorithm_version` varchar(255) DEFAULT NULL COMMENT '算法版本',
  `liquid_version` varchar(255) DEFAULT NULL COMMENT '液路版本',
  `industrial_control_module` varchar(255) DEFAULT NULL COMMENT '工控模组',
  `sampling_module` varchar(255) DEFAULT NULL COMMENT '取样模组',
  `microscopic_examination_module` varchar(255) DEFAULT NULL COMMENT '镜检模组',
  `gold_standard_module` varchar(255) DEFAULT NULL COMMENT '金标模组',
  `light_board` varchar(255) DEFAULT NULL COMMENT '金标卡灯板',
  `traits_board` varchar(255) DEFAULT '' COMMENT '性状灯板',
  `control_board` varchar(255) DEFAULT NULL COMMENT '主控板',
  `motor_driver_board` varchar(255) DEFAULT NULL COMMENT '步进电机驱动板',
  `drop_sensor` varchar(255) DEFAULT NULL COMMENT '液滴传感器',
  `camera` varchar(255) DEFAULT NULL COMMENT '摄像头',
  `computer` varchar(255) DEFAULT NULL COMMENT '电脑主机',
  `monitor` varchar(255) DEFAULT NULL COMMENT '显示器',
  `microscope` varchar(255) DEFAULT NULL COMMENT '显微镜',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `time` datetime DEFAULT NULL COMMENT '信息记入的时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_id` (`id`),
  UNIQUE KEY `uk_serial_number` (`serial_number`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of factory
-- ----------------------------
INSERT INTO `factory` VALUES ('3', '2018-04-08 15:45:30', '2018-04-08 15:45:30', 'b', '6g', '8b', '878', 'bn', '8b', '87y', '87nb', '87b', '87bn', '87bn', '87b', 'n', '87b', '7', 'nb7b', 'n78', 'bny', '7n7', 'bn87', 'b', 'nbby', '87n', '78', '87', '87bn87', 'nb7', '2018-04-08 15:45:30');
INSERT INTO `factory` VALUES ('4', '2018-04-08 15:45:51', '2018-04-08 15:45:51', 'g', 'nm7nmg', '87nm', '78', 'gh78', 'm78nm', '76', 'n76', 'nm7', 'nm7', 'nm7nm', '9n', 'g7', 'nmg7', 'nm7', '9nmg7', 'nm', '9gh7', 'mg,7', 'nmg7', '6nm76', 'nm', '79', '76g79', 'nmg', '76nm', '7nmg7', '2018-04-08 15:45:51');
INSERT INTO `factory` VALUES ('5', '2018-04-08 15:49:44', '2018-04-08 15:49:44', 'ynh87', 'yn8', '7', '87', 'n87', 'y78', 'bn7', 'bn78', 'y', 'b', '87bn', '87y', '87y', '87bn', '87bn', '78y', '7bn', 'y7bn', '87', '87b', 'n87nb', '87b', 'n78', 'bn87', 'b7', 'bn87', 'bn87', '2018-04-08 15:49:44');
INSERT INTO `factory` VALUES ('9', '2018-04-09 05:54:01', '2018-04-08 16:44:58', 'sff', 'dsfs', 'fsdfds', 'fdsf', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null);
INSERT INTO `factory` VALUES ('10', '2018-04-09 05:54:31', '2018-04-08 16:45:07', 'adad', 'sdfsd', 'fsdfds', 'fdsfsf', 'sdfsdf', 'fsfsfd', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null);
INSERT INTO `factory` VALUES ('14', null, null, 'IYG', '111111111111', '897', '97Y', '7Y', '97N', '8YN', '9Y', '7BN', '786', 'BG', '68', '7B', '87NH', '687', 'N687', 'BN', '86N', '76', 'N7', 'F3F', 'N679', 'NB', '87N', '87B', '87', '78', null);
INSERT INTO `factory` VALUES ('15', '2018-04-09 05:51:25', '2018-04-08 16:40:19', '松岛枫松岛枫', 'dfd', 'fdsfdxf', 'sdfsd', 'fsdf', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-04-08 16:40:19');
INSERT INTO `factory` VALUES ('16', '2018-04-09 05:53:38', '2018-04-08 16:40:34', 'fssd', 'sdf', 'fsdf', 'sfsd', 'fsf', 'sfsd', 'sfs', 'fsf', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-04-08 16:40:34');
INSERT INTO `factory` VALUES ('17', '2018-04-08 16:45:45', '2018-04-08 16:45:45', 'g67b', 'y8n', '8nyh', '87y', 'mn8y', '8nmnm', '87nm', '87nm', '87nm', '7', '7', 'y', 'm87y', '7ym', '', '8n', 'm8', 'my8', 'nm8', 'nm', '7y', '7m', '8n', '8', 'm7yn', '8', 'nym87', '2018-04-08 16:45:45');
INSERT INTO `factory` VALUES ('18', '2018-04-08 17:42:24', '2018-04-08 17:42:24', 'v34v443', 'yb87n', '8y', '', 'yn78', 'ny7y7y7y7y7b8n', '', '8nm78', 'n87', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '2018-04-08 17:42:24');

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '图片唯一主键',
  `order_number` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES ('1', 'wx2018020743651', '/img/222.png');
INSERT INTO `image` VALUES ('2', 'wx2018020743651', '/img/111.png');
INSERT INTO `image` VALUES ('3', 'wx2018020743651', '/img/444.jpg');
INSERT INTO `image` VALUES ('4', 'wx2018020743651', '/img/555.jpg');
INSERT INTO `image` VALUES ('6', 'wx2018020751199', 'C:/Users/18894/Desktop/Design/JAVA/MoemilAfterSales/src/main/resources/img/wx72624f63b0523850.o6zAJs0KKyToz-sEJZyzuKmliFIU.27ff96719cc32500d990678ab98b9ead.jpg');
INSERT INTO `image` VALUES ('7', 'wx2018020751199', 'C:/Users/18894/Desktop/Design/JAVA/MoemilAfterSales/src/main/resources/img/wx72624f63b0523850.o6zAJs0KKyToz-sEJZyzuKmliFIU.9d40b75e453033c39529b5dc39d7a857.jpg');
INSERT INTO `image` VALUES ('8', 'wx2018020751199', 'C:/Users/18894/Desktop/Design/JAVA/MoemilAfterSales/src/main/resources/img/wx72624f63b0523850.o6zAJs0KKyToz-sEJZyzuKmliFIU.be32c8a8bfe9331e30a7606f9bedfeca.jpg');
INSERT INTO `image` VALUES ('9', 'wx2018020752020', 'C:/Users/18894/Desktop/Design/JAVA/MoemilAfterSales/src/main/resources/img/wx72624f63b0523850.o6zAJs0KKyToz-sEJZyzuKmliFIU.a4d5f8971d8f5cc9c3742509b03ba16e.png');
INSERT INTO `image` VALUES ('10', 'wx2018020718232', 'C:/Users/18894/Desktop/Design/JAVA/MoemilAfterSales/src/main/resources/img/wx72624f63b0523850.o6zAJs0KKyToz-sEJZyzuKmliFIU.9d40b75e453033c39529b5dc39d7a857.jpg');
INSERT INTO `image` VALUES ('11', 'wx20180509918', '/img/wxe88b19e5efcf6399.o6zAJs0KKyToz-sEJZyzuKmliFIU.zaTHavYnuhKed92c0ccbf1ad95bf714c123af6f99e88.jpg');
INSERT INTO `image` VALUES ('12', 'wx20180509131', '/img/wxe88b19e5efcf6399.o6zAJs0KKyToz-sEJZyzuKmliFIU.2gawI6AQgG7Ed92c0ccbf1ad95bf714c123af6f99e88.jpg');
INSERT INTO `image` VALUES ('13', 'wx20180509543', '/img/wxe88b19e5efcf6399.o6zAJs0KKyToz-sEJZyzuKmliFIU.rhBZng2pfxpqd92c0ccbf1ad95bf714c123af6f99e88.jpg');
INSERT INTO `image` VALUES ('14', 'wx20180509445', '/img/wxe88b19e5efcf6399.o6zAJs0KKyToz-sEJZyzuKmliFIU.yBQfwGt16QaZ6ca7eaa4c90494b70448ad71293ace41.jpg');
INSERT INTO `image` VALUES ('15', 'wx20180509715', '/img/wxe88b19e5efcf6399.o6zAJs0KKyToz-sEJZyzuKmliFIU.ubYs38gDW5cq6ca7eaa4c90494b70448ad71293ace41.jpg');
INSERT INTO `image` VALUES ('16', 'wx20180509233', '/img/wxe88b19e5efcf6399.o6zAJs0KKyToz-sEJZyzuKmliFIU.zwRpUvqWIV7U6ca7eaa4c90494b70448ad71293ace41.jpg');
INSERT INTO `image` VALUES ('17', 'wx20180509448', '/img/wxe88b19e5efcf6399.o6zAJs0KKyToz-sEJZyzuKmliFIU.cxo7I4fY0MxH6ca7eaa4c90494b70448ad71293ace41.jpg');
INSERT INTO `image` VALUES ('18', 'wx20180509267', '/img/wxe88b19e5efcf6399.o6zAJs0KKyToz-sEJZyzuKmliFIU.KOI7REWNgMUK6ca7eaa4c90494b70448ad71293ace41.jpg');
INSERT INTO `image` VALUES ('19', 'wx20180509979', '/img/wxe88b19e5efcf6399.o6zAJs0KKyToz-sEJZyzuKmliFIU.3XqfQcQTBIiN6ca7eaa4c90494b70448ad71293ace41.jpg');
INSERT INTO `image` VALUES ('20', 'wx20180509570', '/img/wxe88b19e5efcf6399.o6zAJs0KKyToz-sEJZyzuKmliFIU.ZWi9b75hQiRS6ca7eaa4c90494b70448ad71293ace41.jpg');
INSERT INTO `image` VALUES ('21', 'wx20180509411', '/img/wxe88b19e5efcf6399.o6zAJs0KKyToz-sEJZyzuKmliFIU.qTjWiZggKvsR6ca7eaa4c90494b70448ad71293ace41.jpg');
INSERT INTO `image` VALUES ('22', 'wx20180510779', '/img/wxe88b19e5efcf6399.o6zAJs0KKyToz-sEJZyzuKmliFIU.sfNu4RNTLEhm6ca7eaa4c90494b70448ad71293ace41.jpg');

-- ----------------------------
-- Table structure for install
-- ----------------------------
DROP TABLE IF EXISTS `install`;
CREATE TABLE `install` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录修改时间',
  `order_number` varchar(255) DEFAULT NULL COMMENT '工单编号',
  `engineer` varchar(255) DEFAULT NULL COMMENT '工程师姓名',
  `engineer_tel` varchar(255) DEFAULT NULL COMMENT '工程师电话',
  `status` varchar(255) DEFAULT NULL COMMENT '订单状态进行中已完成',
  `estimated_arrival_time` datetime DEFAULT NULL COMMENT '预计到达时间',
  `arrivaled_time` datetime DEFAULT NULL COMMENT '实际到达时间',
  `order_time` datetime DEFAULT NULL COMMENT '派单时间',
  `finished_time` datetime DEFAULT NULL COMMENT '订单完成时间',
  `hospital_name` varchar(255) DEFAULT NULL COMMENT '医院名称',
  `hospital_department` varchar(255) DEFAULT NULL COMMENT '医院科室名称',
  `hospital_address` varchar(255) DEFAULT NULL COMMENT '医院地址',
  `zip_code` varchar(255) DEFAULT NULL COMMENT '邮编',
  `product_type` varchar(255) DEFAULT NULL COMMENT '仪器类型',
  `hospital_contact` varchar(255) DEFAULT NULL COMMENT '医院负责人姓名',
  `hospital_tel` varchar(255) DEFAULT NULL COMMENT '医院负责人电话',
  `serial_number` varchar(255) DEFAULT NULL COMMENT '仪器序列号',
  `product_model` varchar(255) DEFAULT NULL COMMENT '仪器型号',
  `packaging` varchar(255) DEFAULT NULL COMMENT '外包装情况',
  `out_appearance` varchar(255) DEFAULT NULL COMMENT '仪器外观',
  `lis` varchar(255) DEFAULT NULL COMMENT 'LIS状态',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP地址',
  `install_stauts` varchar(255) DEFAULT NULL COMMENT '安装状态',
  `is_return_purchase` tinyint(3) unsigned DEFAULT '0' COMMENT '是否退货',
  `is_monitor` tinyint(3) unsigned DEFAULT '1' COMMENT '电脑显示器情况是否正常',
  `is_computer` tinyint(3) unsigned DEFAULT '1' COMMENT '电脑主机状态是否正常',
  `is_return_status` tinyint(3) unsigned DEFAULT '1' COMMENT '返回状态是否正常',
  `is_microscope` tinyint(3) unsigned DEFAULT '1' COMMENT '显微镜状态是否正常',
  `is_liquid` tinyint(3) unsigned DEFAULT '1' COMMENT '液路系统状态是否正常',
  `is_pinch` tinyint(3) unsigned DEFAULT '1' COMMENT '夹管阀状态是否正常',
  `is_auto_identify` tinyint(3) unsigned DEFAULT '1' COMMENT '镜检自动识别状态是否正常',
  `is_step_motor` tinyint(3) unsigned DEFAULT '1' COMMENT '步进电机状态是否正常',
  `is_micro_switch` tinyint(3) unsigned DEFAULT '1' COMMENT '微动开关状态是否正常',
  `is_gold` tinyint(3) unsigned DEFAULT '1' COMMENT '金标卡自动识别状态是否正常',
  `is_dc_motor` tinyint(3) unsigned DEFAULT '1' COMMENT '直流电机状态是否正常',
  `is_opto` tinyint(3) unsigned DEFAULT '1' COMMENT '光耦状态是否正常',
  `is_stool_trait` tinyint(3) unsigned DEFAULT '1' COMMENT '粪便性状识别状态是否正常',
  `is_peristaltic_pump` tinyint(3) unsigned DEFAULT '1' COMMENT '蠕动泵状态是否正常',
  `is_piston_pump` tinyint(3) unsigned DEFAULT '1' COMMENT '柱塞泵是否正常',
  `is_stool_color` tinyint(3) unsigned DEFAULT '1' COMMENT '粪便颜色识别状态是否正常',
  `is_import_sample` tinyint(3) unsigned DEFAULT '1' COMMENT '自动进样状态是否正常',
  `is_expose_sample` tinyint(3) unsigned DEFAULT '1' COMMENT '自动出样状态是否正常',
  `is_cross_pollution` tinyint(3) unsigned DEFAULT '1' COMMENT '交叉污染状态是否正常',
  `is_counting_pool` tinyint(3) unsigned DEFAULT '1' COMMENT '计数池状态是否正常',
  `is_switch` tinyint(3) unsigned DEFAULT '1' COMMENT '仪器开关状态是否正常',
  `is_mix_system` tinyint(3) unsigned DEFAULT '1' COMMENT '自动搅拌系统状态是否正常',
  `is_power_box` tinyint(3) unsigned DEFAULT '1' COMMENT '交流电源盒状态是否正常',
  `is_d15` tinyint(3) unsigned DEFAULT '1' COMMENT 'D15开关状态是否正常',
  `is_electric_auto` tinyint(3) unsigned DEFAULT '1' COMMENT '电气自动化系统状态是否正常',
  `is_electirc_circuit` tinyint(3) unsigned DEFAULT '1' COMMENT '电路系统状态是否正常',
  `is_buck` tinyint(3) unsigned DEFAULT '1' COMMENT '降压系统状态是否正常',
  `zero_voltage` varchar(255) DEFAULT NULL COMMENT '零地电压',
  `app_version` varchar(255) DEFAULT NULL COMMENT '仪器APP应用版本',
  `hard_disk` varchar(255) DEFAULT NULL COMMENT '主机硬盘',
  `computer_version` varchar(255) DEFAULT NULL COMMENT '电脑系统版本',
  `cpu` varchar(255) DEFAULT NULL COMMENT '主机CPU',
  `cd` varchar(255) DEFAULT NULL COMMENT '主机光驱',
  `ram` varchar(255) DEFAULT NULL COMMENT '主机运行内存',
  `supply_voltage` varchar(255) DEFAULT NULL COMMENT '仪器供电电压',
  `computer_voltage` varchar(255) DEFAULT NULL COMMENT '电压供电电压',
  `waste_liquid_bucket` varchar(255) DEFAULT NULL COMMENT '废液桶',
  `card_box` varchar(255) DEFAULT NULL COMMENT '卡仓盒',
  `video_cable` varchar(255) DEFAULT NULL COMMENT '视频线',
  `diluent_bucket` varchar(255) DEFAULT NULL COMMENT '稀释液桶',
  `instrument_power_cable` varchar(255) DEFAULT NULL COMMENT '仪器电源线',
  `usb_cable` varchar(255) DEFAULT NULL COMMENT 'USB接口线',
  `clean_bucket` varchar(255) DEFAULT NULL COMMENT '清洗液桶',
  `computer_cable` varchar(255) DEFAULT NULL COMMENT '电脑主机电源线',
  `certificate` varchar(255) DEFAULT NULL COMMENT '合格证',
  `maintenance_bucket` varchar(255) DEFAULT NULL COMMENT '保养液桶',
  `monitor_cable` varchar(255) DEFAULT NULL COMMENT '显示器电源线',
  `instrument` varchar(255) DEFAULT NULL COMMENT '仪器',
  `manual` varchar(255) DEFAULT NULL COMMENT '仪器操作手册',
  `computer` varchar(255) DEFAULT NULL COMMENT '电脑主机',
  `monitor` varchar(255) DEFAULT NULL COMMENT '显示器',
  `sample_holder` varchar(255) DEFAULT NULL COMMENT '标本盒支架',
  `mouse` varchar(255) DEFAULT NULL COMMENT '鼠标',
  `keyboard` varchar(255) DEFAULT NULL COMMENT '键盘',
  `supplies` varchar(255) DEFAULT NULL COMMENT '跟机耗材',
  `silicone_tube` varchar(255) DEFAULT NULL COMMENT '硅胶管',
  `whether_doa` varchar(255) DEFAULT NULL COMMENT '是否DOA',
  `doa_information` varchar(255) DEFAULT NULL COMMENT 'doa故障描述信息',
  `hardware` varchar(255) DEFAULT NULL COMMENT '硬件到位',
  `hardware_reason` varchar(255) DEFAULT NULL COMMENT '硬件未到位原因',
  `teacher_operator` varchar(255) DEFAULT NULL COMMENT '老师能否操作',
  `teacher_operator_reason` varchar(255) DEFAULT NULL COMMENT '老师不能操作原因',
  `operation_condition` varchar(255) DEFAULT NULL COMMENT '运行情况',
  `operation_condition_reason` varchar(255) DEFAULT NULL COMMENT '不能运行原因',
  `client_feedback` varchar(255) DEFAULT NULL COMMENT '客户意见',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_id` (`id`),
  KEY `idx_hospital_name` (`hospital_name`),
  KEY `uk_serial_number` (`serial_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of install
-- ----------------------------
INSERT INTO `install` VALUES ('1', '2018-04-11 20:51:20', '2018-04-11 21:12:23', 'az20180213688', '天使踩麻木', '18571686931', '进行中', '2018-04-11 20:51:22', '2018-04-11 20:51:23', '2018-04-11 20:51:25', '2018-04-11 20:51:28', 'dsfsdfs', '88888888888', '6666888888888888', '', '66688888888888', 'dsfsdf', '1848984', '6668888888888888', '666', '888888888888', '88888888888', '88888888888', '8888888888', '66', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '66', '66', '66', '88', '8888', '88', '888', '888', '8888', '888', '88', '8888', '88', '88', '88', '88', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '88', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8');
INSERT INTO `install` VALUES ('2', '2018-02-28 13:40:32', '2018-04-10 16:14:07', 'az20180213689', '天使踩麻木', '18571686931', '已完成', null, null, '2018-02-28 13:40:43', '2018-04-11 15:12:56', 'dsfsfdf', null, null, null, null, 'sdfdsg', 'sdfsdf', 'sdfs', 'fsdfds', null, null, null, null, null, '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `install` VALUES ('3', '2018-04-11 21:02:15', '2018-04-11 21:02:15', 'az20180413347', '天使踩麻木', '18571686933', '已受理', '2018-04-13 21:02:15', null, '2018-04-11 21:02:15', null, '666', null, null, null, null, '66', '6666', '6666', '666', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `install` VALUES ('4', '2018-04-19 10:35:21', '2018-04-19 10:35:21', 'az20180421729', '幻觉肆虐', '15571686935', '已受理', '2018-04-21 10:35:21', null, '2018-04-19 10:35:21', null, 'iojoij', null, null, null, null, 'iojiojoi', 'joijoijoij', 'ojiojioj', 'oojoj', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `install` VALUES ('5', '2018-04-23 17:48:07', '2018-04-23 17:48:07', 'az20180425355', '天使踩麻木', '185716869366', '已受理', '2018-04-25 17:48:07', null, '2018-04-23 17:48:07', null, '湖大医院', null, null, null, null, '66', '666', '66', '66', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `install` VALUES ('6', '2018-04-23 17:50:48', '2018-04-23 17:50:48', 'az20180425585', '报修接收号码', '18571686931', '已受理', '2018-04-25 17:50:48', null, '2018-04-23 17:50:48', null, '66', null, null, null, null, '6', '66', '6', '66', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `install` VALUES ('7', '2018-04-23 17:52:17', '2018-04-23 17:52:17', 'az20180425512', '报修接收号码', '18571686931', '已受理', '2018-04-25 17:52:17', null, '2018-04-23 17:52:17', null, '666', null, null, null, null, '666', '6666', '6', '666', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for maintain
-- ----------------------------
DROP TABLE IF EXISTS `maintain`;
CREATE TABLE `maintain` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '保养单唯一标识id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录修改时间',
  `order_number` varchar(255) DEFAULT NULL COMMENT '工单编号',
  `engineer` varchar(50) DEFAULT NULL COMMENT '维修工程师姓名',
  `engineer_tel` varchar(16) DEFAULT NULL COMMENT '工程师电话',
  `status` varchar(255) DEFAULT NULL COMMENT '订单状态进行中已完成',
  `order_time` datetime DEFAULT NULL COMMENT '派单时间',
  `estimated_arrival_time` datetime DEFAULT NULL COMMENT '预计到达时间',
  `arrivaled_time` datetime DEFAULT NULL COMMENT '实际到达时间',
  `finished_time` datetime DEFAULT NULL COMMENT '订单完成日期',
  `hospital_name` varchar(255) DEFAULT NULL COMMENT '医院名称',
  `hospital_contact` varchar(255) DEFAULT NULL COMMENT '医院联系人姓名',
  `hospital_tel` varchar(255) DEFAULT NULL COMMENT '医院联系人电话',
  `serial_number` varchar(255) DEFAULT NULL COMMENT '仪器唯一标识序列号',
  `product_model` varchar(255) DEFAULT NULL COMMENT '仪器型号',
  `current_sample_count` int(11) DEFAULT NULL COMMENT '当前标本量统计',
  `sample_box_type` varchar(255) DEFAULT NULL COMMENT '标本盒种类',
  `reagent_type` varchar(255) DEFAULT NULL COMMENT '试剂种类',
  `is_insuranced` tinyint(3) unsigned DEFAULT '0' COMMENT '是否过保',
  `is_instrument` tinyint(3) unsigned DEFAULT '1' COMMENT '仪器主机是否处理',
  `is_computer` tinyint(3) unsigned DEFAULT '1' COMMENT '计算机是否处理',
  `is_microscope` tinyint(3) unsigned DEFAULT '1' COMMENT '显微镜是否处理',
  `is_pump` tinyint(3) unsigned DEFAULT '1' COMMENT '泵阀是否处理',
  `is_arm` tinyint(3) unsigned DEFAULT '1' COMMENT '机械臂是否处理',
  `is_stir` tinyint(3) unsigned DEFAULT '1' COMMENT '搅拌部分是否处理',
  `is_gold` tinyint(3) unsigned DEFAULT '1' COMMENT '金标部分是否处理',
  `is_sample` tinyint(3) unsigned DEFAULT '1' COMMENT '送样部分是否处理',
  `is_computer1` tinyint(3) unsigned DEFAULT '1' COMMENT '计算机视频采集卡驱动是否正常',
  `is_computer2` tinyint(3) unsigned DEFAULT '1' COMMENT '显微镜相机驱动是否正常',
  `is_microscope1` tinyint(3) unsigned DEFAULT '1' COMMENT '显微镜焦距是否正常',
  `is_microscope2` tinyint(3) unsigned DEFAULT '1' COMMENT '显微镜上下左右前后是否正常',
  `is_microscope3` tinyint(3) unsigned DEFAULT '1' COMMENT '显微镜皮带松紧度是否合适',
  `is_camera1` tinyint(3) unsigned DEFAULT '1' COMMENT '摄像头性状图像是否清晰',
  `is_camera2` tinyint(3) unsigned DEFAULT '1' COMMENT '摄像头金标图像是否清晰',
  `is_countpool1` tinyint(3) unsigned DEFAULT '1' COMMENT '计数池内是否有异物',
  `is_countpool2` tinyint(3) unsigned DEFAULT '1' COMMENT '计数池通道污点是否清除',
  `is_countpool3` tinyint(3) unsigned DEFAULT '1' COMMENT '计数池表面是否有划痕',
  `is_arm1` tinyint(3) unsigned DEFAULT '1' COMMENT '取样臂运行是否正常',
  `is_arm2` tinyint(3) unsigned DEFAULT '1' COMMENT '取样臂左右前后上下移动是否正常',
  `is_arm3` tinyint(3) unsigned DEFAULT '1' COMMENT '取样臂外壁是否干净',
  `is_arm4` tinyint(3) unsigned DEFAULT '1' COMMENT '取样臂探入标本盒深度是否合适',
  `is_liquid1` tinyint(3) unsigned DEFAULT '1' COMMENT '滴样位置是否正确',
  `is_liquid2` tinyint(3) unsigned DEFAULT '1' COMMENT '滴样是否正常',
  `is_sample1` tinyint(3) unsigned DEFAULT '1' COMMENT '送样传动部件声音是否正常',
  `is_sample2` tinyint(3) unsigned DEFAULT '1' COMMENT '送样运行是否平稳',
  `software1` varchar(255) DEFAULT NULL COMMENT '低倍上下',
  `software2` varchar(255) DEFAULT NULL COMMENT '低倍左右',
  `software3` varchar(255) DEFAULT NULL COMMENT '低倍前后',
  `software4` varchar(255) DEFAULT NULL COMMENT '低倍白平衡',
  `software5` varchar(255) DEFAULT NULL COMMENT '低倍伽马值',
  `software6` varchar(255) DEFAULT NULL COMMENT '低倍曝光值',
  `software7` varchar(255) DEFAULT NULL COMMENT '高倍上下',
  `software8` varchar(255) DEFAULT NULL COMMENT '高倍左右',
  `software9` varchar(255) DEFAULT NULL COMMENT '高倍前后',
  `software10` varchar(255) DEFAULT NULL COMMENT '高倍白平衡',
  `software11` varchar(255) DEFAULT NULL COMMENT '高倍伽马值',
  `software12` varchar(255) DEFAULT NULL COMMENT '高倍曝光值',
  `software13` varchar(255) DEFAULT NULL COMMENT '镜检红细胞',
  `software14` varchar(255) DEFAULT NULL COMMENT '镜检白细胞',
  `software15` varchar(255) DEFAULT NULL COMMENT '镜检虫卵',
  `software16` varchar(255) DEFAULT NULL COMMENT '镜检结果准确率',
  `machine1` varchar(255) DEFAULT NULL COMMENT '整机测试重新断电开机',
  `machine2` varchar(255) DEFAULT NULL COMMENT '深度冲洗',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_id` (`id`),
  KEY `idx_hospital_name` (`hospital_name`),
  KEY `uk_serial_number` (`serial_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of maintain
-- ----------------------------
INSERT INTO `maintain` VALUES ('1', '2018-02-13 15:55:33', '2018-04-12 10:28:06', 'by20180213335', '天使踩麻木', '18571686931', '进行中', '2018-02-13 15:55:33', null, '2018-04-11 14:48:11', '2018-04-11 15:12:59', 'jiojoi', 'sdf', '189489489', 'dsfdf', 'dsfsdfds', '88888', '88', '8', null, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '88', '888', '8888', '888', '888', '88', '88', '8', '88', '8', '8', '8', '88', '8', '8', '88', '888', '888', '888');
INSERT INTO `maintain` VALUES ('2', '2018-04-10 16:12:52', '2018-04-12 10:29:19', 'by20180410398', '天使踩麻木', '18571686931', '已完成', '2018-04-10 16:12:52', null, null, '2018-04-19 10:30:18', 'h9h', 'ouh', '84646', 'fsdff', 'sdffd', '6666', '666', '66', null, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '66', '66', '66', '66', '6', '66', '6', '6', '6', '66', '6', '6', '6', '6', '6', '66', '6', '6', '6');
INSERT INTO `maintain` VALUES ('3', '2018-04-23 17:48:21', '2018-04-23 17:48:21', 'by20180425669', '报修接收号码', '18571686931', '已受理', '2018-04-23 17:48:21', '2018-04-25 17:48:21', null, null, '66', '66', '66', '6', '66', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `maintain` VALUES ('4', '2018-04-23 17:52:44', '2018-04-23 17:52:44', 'by20180425906', '报修接收号码', '18571686931', '已受理', '2018-04-23 17:52:44', '2018-04-25 17:52:44', null, null, '华大医院', '66', '666', '66', '666', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '产品唯一标识Id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录修改时间',
  `serial_number` varchar(255) DEFAULT NULL COMMENT '产品序列号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `product_model` varchar(255) DEFAULT NULL COMMENT '产品型号',
  `product_type` varchar(255) DEFAULT NULL COMMENT '产品类型',
  `hospital_name` varchar(255) DEFAULT NULL COMMENT '医院名称',
  `hospital_address` varchar(255) DEFAULT NULL COMMENT '医院地址',
  `hospital_department` varchar(255) DEFAULT NULL COMMENT '医院科室',
  `hospital_contact` varchar(255) DEFAULT NULL COMMENT '医院联系人姓名',
  `hospital_tel` varchar(255) DEFAULT NULL COMMENT '医院联系人电话',
  `status` varchar(255) DEFAULT NULL COMMENT '仪器状态\r\n库管\r\n已安装\r\n维修\r\n保养\r\n返厂\r\n报废',
  `status_change_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '状态更改的时间',
  `status_operator` varchar(255) DEFAULT NULL COMMENT '状态变更时的操作人',
  `repair_times` int(11) DEFAULT '0' COMMENT '维修次数',
  `maintenance_times` int(11) DEFAULT '0' COMMENT '保养次数',
  `month_sample_count` int(11) DEFAULT '0' COMMENT '月表标本量总数',
  `day_sample_count` int(11) DEFAULT '0' COMMENT '日表标本量总数',
  `current_sample_count` int(11) DEFAULT '0' COMMENT '当前标本量总数',
  `region` varchar(255) DEFAULT NULL COMMENT '所在的大区',
  `province` varchar(255) DEFAULT NULL COMMENT '所在的省',
  `city` varchar(255) DEFAULT NULL COMMENT '所在的市',
  `district` varchar(255) DEFAULT NULL COMMENT '所在的区、县',
  `ship_date` varchar(255) DEFAULT NULL COMMENT '发货日期',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_id` (`id`),
  UNIQUE KEY `uk_serial_number` (`serial_number`),
  KEY `ind_hospital_name` (`hospital_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '2018-04-09 07:50:52', '2018-05-14 14:49:10', '234', '23244', '1231', '', '', '', '', '', '', '', null, '', '0', '0', '0', '0', '0', '', '', '', '', '2018-05-02', '');
INSERT INTO `product` VALUES ('2', '2018-04-11 16:43:57', '2018-04-09 11:33:57', 'sfsdf', 'sfsd', 'fsdf', 'd32d', 'd23', 'de', 'de23', 'de32', 'de2', 'd2e', '2018-04-11 16:43:57', '管理员', '22', '33', '33', '33', '22', 'de2', 'de3', 'de', 'add', null, 'gd');
INSERT INTO `product` VALUES ('3', '2018-04-08 18:55:40', '2018-04-08 18:55:40', 'rfdfg', 'gdg', '', '', '', '', '', '', '', '', '2018-04-08 18:55:40', '管理员', null, null, null, null, null, '', '', '', '', null, '');
INSERT INTO `product` VALUES ('4', '2018-04-20 00:22:29', '2018-05-14 14:50:43', '21ewd', 'ewd', 'hio', 'i', 'jioj', 'ioj', 'oijio', 'jioj', 'oij', 'ioj', '2018-04-20 00:22:29', '管理员', null, null, null, null, null, 'iojio', 'joi', 'jio', 'jio', '2018-05-01', 'jio');
INSERT INTO `product` VALUES ('5', '2018-05-14 14:51:01', '2018-05-14 14:51:01', '2sdf', 'fsdf', 'fds', 'dsf', '', 'sdfsd', 'fsdf', 'dsf', 'dsf', 'fd', '2018-05-14 14:51:01', '管理员', null, null, null, null, null, '', '', '', '', '2018-05-01', '');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '维修工单唯一标识id',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录修改时间',
  `order_number` varchar(255) DEFAULT NULL COMMENT '订单号',
  `openid` varchar(255) DEFAULT NULL COMMENT '医院提交订单时所用的微信唯一openid',
  `engineer` varchar(50) DEFAULT NULL COMMENT '维修工程师姓名',
  `engineer_tel` varchar(16) DEFAULT NULL COMMENT '工程师电话',
  `status` varchar(16) DEFAULT NULL COMMENT '订单状态未处理已受理进行中已完成',
  `apply_time` datetime DEFAULT NULL COMMENT '医院申请的时间',
  `expect_time` varchar(255) DEFAULT NULL COMMENT '医院期望的维修时间',
  `deal_time` datetime DEFAULT NULL COMMENT '派单日期被受理时间',
  `estimated_arrival_time` datetime DEFAULT NULL COMMENT '预计到达时间',
  `arrivaled_time` datetime DEFAULT NULL COMMENT '实际到达时间',
  `finished_time` datetime DEFAULT NULL COMMENT '订单完成时间',
  `hospital_name` varchar(255) DEFAULT NULL COMMENT '用户名称医院名称',
  `hospital_contact` varchar(255) DEFAULT NULL COMMENT '科室负责人姓名',
  `hospital_tel` varchar(255) DEFAULT NULL COMMENT '科室负责人联系电话',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `product_model` varchar(255) DEFAULT NULL COMMENT '产品型号',
  `serial_number` varchar(255) DEFAULT NULL COMMENT '产品序列号',
  `product_version` varchar(255) DEFAULT NULL COMMENT '产品版本',
  `hospital_error_describe` varchar(255) DEFAULT NULL COMMENT '医院提交的故障描述',
  `install_date` datetime DEFAULT NULL COMMENT '装机日期',
  `is_insuranced` tinyint(3) unsigned DEFAULT NULL COMMENT '是否过保',
  `is_return_factory` tinyint(3) unsigned DEFAULT NULL COMMENT '是否返厂',
  `is_replace_product` tinyint(3) unsigned DEFAULT NULL COMMENT '是否更换新机',
  `current_sample_count` int(11) DEFAULT NULL COMMENT '当前标本量统计',
  `sample_box_type` varchar(255) DEFAULT NULL COMMENT '标本盒种类',
  `reagent_type` varchar(255) DEFAULT NULL COMMENT '试剂种类',
  `error_type` varchar(255) DEFAULT NULL COMMENT '故障种类电子软件机构拍照自动识别系统其他',
  `phenomenon` varchar(255) DEFAULT NULL COMMENT '故障现象',
  `reason` varchar(255) DEFAULT NULL COMMENT '故障原因',
  `solution` varchar(255) DEFAULT NULL COMMENT '解决方法',
  `replace_name_code` varchar(255) DEFAULT NULL COMMENT '更换备件名称及编号',
  `original_name_code` varchar(255) DEFAULT NULL COMMENT '原备件名称及编号',
  `product_condition` varchar(255) DEFAULT NULL COMMENT '仪器状况',
  `order_remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `price` decimal(10,0) unsigned DEFAULT NULL COMMENT '维修价格',
  `service_rating` tinyint(3) unsigned DEFAULT NULL COMMENT '服务评价0好评1中评2差评',
  `hospital_complaint` varchar(255) DEFAULT NULL COMMENT '医院投诉的内容',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pk_id` (`id`),
  KEY `idx_hospital_name` (`hospital_name`),
  KEY `uk_serial_number` (`serial_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES ('15', '2018-02-06 16:44:23', '2018-02-06 16:44:23', 'wx20180207972', null, '', null, '未处理', '2018-02-06 16:44:23', '2016-09-01', null, null, null, null, 'dsfdfsdf', '', '', '全自动检测仪', '', '', null, '', null, '0', '1', '0', null, null, null, null, null, null, null, null, null, null, null, null, '0', null);
INSERT INTO `repair` VALUES ('16', '2018-02-06 16:45:00', '2018-02-06 16:45:00', 'wx20180207973', null, '', null, '未处理', '2018-02-06 16:45:00', '2018-09-01', null, null, null, null, '协和医院', '金松', '18571686931', '全自动检测仪', 'XS-2', '855649998713', null, '故障描述在这里', null, '0', '1', '0', null, null, null, null, null, null, null, null, null, null, null, null, '0', null);
INSERT INTO `repair` VALUES ('18', '2018-02-06 16:47:07', '2018-04-19 10:34:50', 'wx20180207974', null, '黎未央', '18571686934', '已受理', '2018-02-06 16:47:07', '2018-07-03', '2018-04-19 10:34:50', null, null, null, '同济医院', '周黎', '13871586812', '全自动检测仪', 'XS-2', '855649998713', null, '哈哈哈哈哈哈哈笑死了', null, '0', '1', '0', null, null, null, null, null, null, null, null, null, null, null, null, '2', null);
INSERT INTO `repair` VALUES ('19', '2018-02-06 16:47:25', '2018-04-19 10:34:37', 'wx20180207975', null, '哈哈哈', '18871686931', '已受理', '2018-02-06 16:47:25', '2018-07-03', '2018-04-19 10:34:37', null, null, null, '同济医院', '周黎', '13871586812', '全自动检测仪', 'XS-2', '855649998713', null, '哈哈哈哈哈哈哈笑死了', null, '0', '1', '0', null, null, null, null, null, null, null, null, null, null, null, null, '2', null);
INSERT INTO `repair` VALUES ('20', '2018-02-07 09:46:51', '2018-04-10 14:16:15', 'wx20180207976', null, 'helloworld', '185716386931', '已受理', '2018-02-07 09:46:51', '2018-02-07', '2018-04-10 14:16:15', null, null, null, null, null, null, '全自动检测仪', 'sjiofsjosjfos', null, null, null, null, '0', '1', '0', null, null, null, null, null, null, null, null, null, null, null, null, '2', null);
INSERT INTO `repair` VALUES ('21', '2018-02-07 10:25:59', '2018-02-18 13:52:08', 'wx20180207436', null, '哈哈哈', '18871686931', '已受理', '2018-02-07 10:25:59', '2017-09-01', null, null, null, null, '北京市人民医院', '黄金甲', '交警搜房建瓯市', '全自动检测仪', 'XS-2', '855649998713', null, '囧事京东覅三等奖否', null, '0', '1', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', null);
INSERT INTO `repair` VALUES ('24', '2018-02-07 13:52:09', '2018-04-10 16:14:16', 'wx20180207182', 'oJxcn0RTiniMFNujqp5_-_dP9UhU', '黎未央', '18571686934', '已受理', '2018-02-07 13:52:09', '2016-09-01', '2018-04-10 16:14:16', null, null, null, 'buihiuh', '', '', '全自动检测仪', 'XS-2', '855649998713', null, '', null, '0', '1', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', null);
INSERT INTO `repair` VALUES ('25', '2018-02-07 13:53:42', '2018-02-18 13:43:45', 'wx20180207262', 'oJxcn0RTiniMFNujqp5_-_dP9UhU', '幻觉肆虐', '15571686935', '已完成', '2018-02-07 13:53:42', '2016-09-01', '2018-02-18 14:09:02', null, null, null, 'dsf', '', '', '全自动检测仪', '', '', null, '', null, '0', '1', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', null);
INSERT INTO `repair` VALUES ('27', '2018-04-11 16:59:54', '2018-04-11 16:59:56', 'wx20180207562', null, '天使踩麻木', '18571686931', '已完成', '2018-04-11 17:09:57', '2018-02-07', '2018-04-11 17:10:00', '2018-04-11 17:10:05', '2018-04-19 10:29:52', '2018-04-19 10:30:08', '666', '666', '666', '666', '666', '777', '7777', null, null, '1', '1', '0', null, '11', '11', null, '99999999999', '888888888888', '8888888888888', '88888888888', '999999999999', '999999999999', '999999999999999', null, null, null);
INSERT INTO `repair` VALUES ('28', '2018-02-09 11:31:28', '2018-02-18 13:38:58', 'wx20180209932', 'oJxcn0RTiniMFNujqp5_-_dP9UhU', '黎未央', '18571686934', '已受理', '2018-02-09 11:31:28', '2018-02-07', '2018-02-18 14:09:08', null, null, null, null, null, null, '全自动检测仪', 'sjiofsjosjfos', null, null, null, null, '0', '1', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', 'gggggg');
INSERT INTO `repair` VALUES ('29', '2018-02-13 14:24:21', '2018-02-18 13:53:37', 'wx20180213506', null, '黎未央', '18571686934', '已受理', '2018-02-13 14:24:21', '2018-02-07', '2018-02-18 14:09:11', null, null, null, null, null, null, null, null, null, null, null, null, '0', '1', '0', null, null, null, null, null, null, null, null, null, null, null, null, '0', null);
INSERT INTO `repair` VALUES ('30', '2018-02-13 14:40:19', '2018-02-13 14:40:19', 'wx20180213737', null, 'Engineer Chen', null, '已受理', '2018-02-13 14:40:19', '2018-02-13', '2018-02-18 14:09:14', null, null, null, '水电费', '的', '1864846', '司法所地方', '似懂非懂是', '防守打法', null, '东方闪电', null, '0', '1', '0', null, null, null, null, null, null, null, null, null, null, null, null, '0', null);
INSERT INTO `repair` VALUES ('31', '2018-02-13 14:41:04', '2018-02-18 13:53:50', 'wx20180213508', null, '黎未央', '18571686934', '已受理', '2018-02-13 14:41:04', '2018-03-13', '2018-02-18 14:09:19', null, null, null, '水电费', '的', '1864846', '司法所地方电风扇', '似懂非懂是第三方', '防守打法', null, '东方闪电', null, '0', '1', '0', null, null, null, null, null, null, null, null, null, null, null, null, '0', null);
INSERT INTO `repair` VALUES ('34', '2018-04-09 14:38:33', '2018-04-10 14:05:30', 'wx20180409113', 'oJxcn0RTiniMFNujqp5_-_dP9UhU', 'helloworld', '185716386931', '已受理', '2018-04-09 14:38:33', '2018-03-01', '2018-04-10 14:05:30', null, null, null, '光大医院', '记得', '4324948', 'g455gg', 'f55f545', 'f55', null, 'hiugiugh9', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '0', null);
INSERT INTO `repair` VALUES ('35', '2018-04-10 14:06:26', '2018-04-10 16:08:40', 'wx20180410111', null, '天使踩麻木', '18571686931', '已完成', '2018-04-10 14:06:26', '2018-06-10', '2018-04-10 16:08:40', null, null, '2018-04-11 15:12:45', '电子科大医院', 'J我偶极矩', '18571686931', 'vg4f4g', 'jiojiojioj', 'oijiojiojoijooi', null, '发3发', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('36', '2018-04-10 14:14:43', '2018-04-10 16:08:20', 'wx20180410629', null, '黎未央', '18571686934', '已受理', '2018-04-10 14:14:43', '2018-05-10', '2018-04-10 16:08:20', null, null, null, 'kjhkjh', 'iuhiuh', '18571686999', 'huihuibg87gbiuh', 'jjljlkj', 'hkjhkjhk', null, 'hiuhouh', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('37', '2018-04-11 14:54:50', '2018-04-11 14:54:50', 'wx20180413132', null, '天使踩麻木', '18571686933', '已受理', '2018-04-11 14:54:50', '2018-05-11', '2018-04-11 14:54:50', '2018-04-13 14:54:50', null, null, 'nm7', 'h87n', '87y', 'h87h8', '78n87', 'n87nm78', null, '78', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('38', '2018-04-23 17:01:40', '2018-04-23 17:01:40', 'wx20180425615', null, '天使踩麻木', '18571686933', '已受理', '2018-04-23 17:01:40', '2018-06-23', '2018-04-23 17:01:40', '2018-04-25 17:01:40', null, null, '春熙路医院', '16816', '16546546', '888', '888', '888', null, 'aaa', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('39', '2018-04-23 17:05:36', '2018-04-23 17:05:36', 'wx20180425387', null, '天使踩麻木', '18571686933', '已受理', '2018-04-23 17:05:36', '2018-04-23', '2018-04-23 17:05:36', '2018-04-25 17:05:36', null, null, '111111', '1111', '111', '11111', '111', '111', null, '1111', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('40', '2018-04-23 17:11:01', '2018-04-23 17:11:01', 'wx20180425817', null, '天使踩麻木', '18571686933', '已受理', '2018-04-23 17:11:01', '2018-05-23', '2018-04-23 17:11:01', '2018-04-25 17:11:01', null, null, '222', '22', '2222', '222222222', '222', '222', null, '22', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('41', '2018-04-23 17:12:59', '2018-04-23 17:12:59', 'wx20180425708', null, '天使踩麻木', '18571686933', '已受理', '2018-04-23 17:12:59', '2018-04-25', '2018-04-23 17:12:59', '2018-04-25 17:12:59', null, null, '33', '3', '33', '333', '33', '3', null, '3', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('42', '2018-04-23 17:14:04', '2018-04-23 17:14:04', 'wx20180425873', null, '天使踩麻木', '18571686933', '已受理', '2018-04-23 17:14:04', '2018-04-23', '2018-04-23 17:14:04', '2018-04-25 17:14:04', null, null, '4', '4', '44', '44', '44', '4', null, '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('43', '2018-04-23 17:22:00', '2018-04-23 17:22:00', 'wx20180425707', null, '天使踩麻木', '18571686931', '已受理', '2018-04-23 17:22:00', '2018-04-23', '2018-04-23 17:22:00', '2018-04-25 17:22:00', null, null, '4', '4', '44', '44', '44', '4', null, '4', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('44', '2018-04-23 17:23:55', '2018-04-23 17:23:55', 'wx20180425447', null, '报修接收号码', '18571686931', '已受理', '2018-04-23 17:23:55', '2018-04-23', '2018-04-23 17:23:55', '2018-04-25 17:23:55', null, null, '55', '55', '55', '55', '555', '5', null, '555', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('45', '2018-04-23 17:27:51', '2018-04-23 17:27:51', 'wx20180425377', null, '报修接收号码', '18571686931', '已受理', '2018-04-23 17:27:51', '2018-04-24', '2018-04-23 17:27:51', '2018-04-25 17:27:51', null, null, '666', '6666', '6666', '666', '666', '666', null, '666', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('46', '2018-04-23 17:44:16', '2018-04-23 17:44:16', 'wx2018020743651', 'oJxcn0RTiniMFNujqp5_-_dP9UhU', '报修接收号码', '18571686931', '未处理', '2018-04-23 17:44:16', '2018-01-01', null, '2018-04-25 17:44:16', null, null, '666', '6666', '6666', '666', '666', '666', '888888', '9', null, '0', '0', '0', null, '88', '88', null, '8484', '8484946', '4646516', '1651', '651', '561', '61', null, null, null);
INSERT INTO `repair` VALUES ('47', '2018-05-07 17:52:40', '2018-05-07 17:52:40', 'wx20180509990', '18571686931', null, null, '未处理', '2018-05-07 17:52:40', '2018-01-01', null, '2018-05-09 17:52:40', null, null, '6', '66', '6', '6', '66', '66', null, '66', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('48', '2018-05-07 17:53:13', '2018-05-07 17:53:13', 'wx20180509918', '18571686931', null, null, '未处理', '2018-05-07 17:53:13', '2018-01-01', null, '2018-05-09 17:53:13', null, null, '', '', '', '', '', '', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('49', '2018-05-07 17:55:10', '2018-05-07 17:55:10', 'wx20180509131', '18571686931', null, null, '未处理', '2018-05-07 17:55:10', '2018-01-01', null, '2018-05-09 17:55:10', null, null, '', '', '', '', '', '', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('50', '2018-05-07 17:58:55', '2018-05-07 17:58:55', 'wx20180509543', '18571686931', null, null, '未处理', '2018-05-07 17:58:55', '2018-01-01', null, '2018-05-09 17:58:55', null, null, '', '', '', '', '', '', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('51', '2018-05-07 18:37:13', '2018-05-07 18:37:13', 'wx20180509524', '18571686931', null, null, '未处理', '2018-05-07 18:37:13', '2018-01-01', null, '2018-05-09 18:37:13', null, null, '6', '66', '66', '666', '66', '666', null, '6', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('52', '2018-05-07 18:38:46', '2018-05-07 18:38:46', 'wx20180509445', '18571686931', null, null, '未处理', '2018-05-07 18:38:46', '2018-01-01', null, '2018-05-09 18:38:46', null, null, '', '', '', '', '', '', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('53', '2018-05-07 18:39:42', '2018-05-07 18:39:42', 'wx20180509715', '18571686931', null, null, '未处理', '2018-05-07 18:39:42', '2018-01-01', null, '2018-05-09 18:39:42', null, null, '', '', '', '', '', '', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('54', '2018-05-07 18:41:05', '2018-05-07 18:41:05', 'wx20180509233', '18571686931', null, null, '未处理', '2018-05-07 18:41:05', '2018-01-01', null, '2018-05-09 18:41:05', null, null, '', '', '', '', '', '', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('55', '2018-05-07 18:42:37', '2018-05-07 18:42:37', 'wx20180509258', '18571686931', null, null, '未处理', '2018-05-07 18:42:37', '2018-01-01', null, '2018-05-09 18:42:37', null, null, '1561', '651', '65165', '865', '15156', '156', null, '16', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('56', '2018-05-07 18:43:57', '2018-05-07 18:43:57', 'wx20180509571', '18571686931', null, null, '未处理', '2018-05-07 18:43:57', '2018-01-01', null, '2018-05-09 18:43:57', null, null, '6516', '516', '516', '5465', '4651', '651', null, '165', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('57', '2018-05-07 18:47:09', '2018-05-07 18:47:09', 'wx20180509448', '18571686931', null, null, '未处理', '2018-05-07 18:47:09', '2018-01-01', null, '2018-05-09 18:47:09', null, null, '', '', '', '', '', '', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('58', '2018-05-07 18:50:26', '2018-05-07 18:50:26', 'wx20180509137', '18571686931', null, null, '未处理', '2018-05-07 18:50:26', '2018-01-01', null, '2018-05-09 18:50:26', null, null, '561', '651', '651', '1561', '56156', '1561', null, '61', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('59', '2018-05-07 18:51:15', '2018-05-07 18:51:15', 'wx20180509267', '18571686931', null, null, '未处理', '2018-05-07 18:51:15', '2018-01-01', null, '2018-05-09 18:51:15', null, null, '651', '651', '61', '1561', '6165', '151', null, '65163', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('60', '2018-05-07 19:09:44', '2018-05-07 19:09:44', 'wx20180509979', '18571686931', null, null, '未处理', '2018-05-07 19:09:44', '2018-01-01', null, '2018-05-09 19:09:44', null, null, '516', '516', '516', '156165', '651', '5616', null, '5', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('61', '2018-05-07 19:10:35', '2018-05-07 19:10:35', 'wx20180509570', '18571686931', null, null, '未处理', '2018-05-07 19:10:35', '2018-01-01', null, '2018-05-09 19:10:35', null, null, '561', '65161', '18571686931', '456', '651', '651', null, '156161', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('62', '2018-05-07 19:11:39', '2018-05-07 19:11:39', 'wx20180509411', '18571686931', null, null, '未处理', '2018-05-07 19:11:39', '2018-01-01', null, '2018-05-09 19:11:39', null, null, '61', '561', '18571686931', '1561', '561', '5615', null, '18616', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `repair` VALUES ('63', '2018-05-08 09:38:03', '2018-05-08 09:38:03', 'wx20180510779', '18571686931', null, null, '未处理', '2018-05-08 09:38:03', '2018-01-01', null, '2018-05-10 09:38:03', null, null, '测试', '测试', '18571686931', '测试', '测试', '测试', null, '测试', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for work
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识ID',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建日期',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改日期',
  `name` varchar(255) DEFAULT NULL COMMENT '人员姓名',
  `attendance_time` varchar(255) DEFAULT NULL COMMENT '出勤日期',
  `work_time` varchar(255) DEFAULT NULL COMMENT '工作开始日期',
  `hospital_name` varchar(255) DEFAULT NULL COMMENT '客户医院名称',
  `product_model` varchar(255) DEFAULT NULL COMMENT '仪器型号',
  `error` varchar(255) DEFAULT NULL COMMENT '故障内容',
  `solution` varchar(255) DEFAULT NULL COMMENT '处理措施',
  `finished` varchar(255) DEFAULT NULL COMMENT '完成情况',
  `finished_time` varchar(255) DEFAULT NULL COMMENT '预计完成日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work
-- ----------------------------
INSERT INTO `work` VALUES ('2', '2018-05-11 14:08:29', '2018-05-11 14:08:29', '156', '2018-05-09', '2018-05-16', '156', '156', '651', '65165', '165', '2018-05-24', '16');
