/*
 Navicat Premium Dump SQL

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80011 (8.0.11)
 Source Host           : localhost:3306
 Source Schema         : devices

 Target Server Type    : MySQL
 Target Server Version : 80011 (8.0.11)
 File Encoding         : 65001

 Date: 05/12/2025 16:57:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for assembly_records
-- ----------------------------
DROP TABLE IF EXISTS `assembly_records`;
CREATE TABLE `assembly_records`  (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `workstation_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `part_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `part_type` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `assembly_date_time` datetime NULL DEFAULT NULL,
  `assembly_status` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `device_id` int(6) NULL DEFAULT NULL COMMENT '设备ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_ass_record_device`(`device_id` ASC) USING BTREE,
  CONSTRAINT `fk_ass_record_device` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '组装线监控表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of assembly_records
-- ----------------------------
INSERT INTO `assembly_records` VALUES (6, '1', '1001', '1', '2025-12-06 05:00:00', '1', '在2025-12-06 05:00:00检测到工作站A部件A编号为1001的机械部件组装状态正常', 1);
INSERT INTO `assembly_records` VALUES (11, '3', '1003', '3', '2025-10-30 04:03:00', '1', '在2025-10-30 04:03:00检测到工作站C部件C编号为1003的机械部件组装状态正常', 2);
INSERT INTO `assembly_records` VALUES (12, '3', '1003', '3', '2023-12-30 00:00:00', '1', '在2023-12-30 01:01:01检测到工作站C部件C编号为1003的机械部件组装状态正常', 2);
INSERT INTO `assembly_records` VALUES (13, '1', '1002', '2', '2025-12-01 01:01:00', '1', '在2025-12-01 01:01:00检测到工作站A部件B编号为1002的机械部件组装状态正常', 1);
INSERT INTO `assembly_records` VALUES (14, '2', '1001', '3', '2025-12-05 14:35:00', '1', '在2025-12-05 14:35:00检测到工作站B部件C编号为1001的机械部件组装状态正常', 2);
INSERT INTO `assembly_records` VALUES (15, '2', '1002', '1', '2025-12-05 14:44:00', '1', '在2025-12-05 14:44:00检测到工作站B部件A编号为1002的机械部件组装状态正常', 2);
INSERT INTO `assembly_records` VALUES (16, '2', '1002', '1', '2025-12-06 14:45:00', '1', '在2025-12-06 14:45:00检测到工作站B部件A编号为1002的机械部件组装状态正常', 1);
INSERT INTO `assembly_records` VALUES (17, '2', '1003', '2', '2025-12-05 19:26:00', '1', '在2025-12-05 19:26:00检测到工作站B部件B编号为1003的机械部件组装状态正常', 2);
INSERT INTO `assembly_records` VALUES (18, '1', '1003', '1', '2025-12-05 16:26:00', '1', '在2025-12-05 16:26:00检测到工作站A部件A编号为1003的机械部件组装状态正常', 1);
INSERT INTO `assembly_records` VALUES (19, '1', '1001', '1', '2025-12-05 16:32:00', '1', '在2025-12-05 16:32:00检测到工作站A部件A编号为1001的机械部件组装状态正常', 5);
INSERT INTO `assembly_records` VALUES (22, '1', '1001', '1', '2025-12-05 16:38:00', '1', '在2025-12-05 16:38:00检测到工作站A部件A编号为1001的机械部件组装状态正常', 8);

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`  (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `no` int(12) NULL DEFAULT NULL,
  `type` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `model` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` varchar(6) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `purchase_time` date NULL DEFAULT NULL,
  `dumpe_time` date NULL DEFAULT NULL,
  `admin_id` int(6) NULL DEFAULT NULL COMMENT '设备管理员ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_device_admin`(`admin_id` ASC) USING BTREE,
  CONSTRAINT `fk_device_admin` FOREIGN KEY (`admin_id`) REFERENCES `device_admin` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES (1, 1, 'type1', 'model1', '2', '2025-12-05', '2025-12-31', 1);
INSERT INTO `device` VALUES (2, 3, 'type3', 'model2', '2', '2025-12-01', '2025-12-24', 2);
INSERT INTO `device` VALUES (5, 3, 'type2', 'model0', '1', '2025-12-01', '2026-01-24', 1);
INSERT INTO `device` VALUES (6, 2, 'type2', 'model3', '1', '2025-12-01', '2026-01-08', 1);
INSERT INTO `device` VALUES (8, 5, 'type1', 'model2', '1', '2025-12-05', '2026-01-11', 2);

-- ----------------------------
-- Table structure for device_admin
-- ----------------------------
DROP TABLE IF EXISTS `device_admin`;
CREATE TABLE `device_admin`  (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '设备管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_admin
-- ----------------------------
INSERT INTO `device_admin` VALUES (1, 'administar1', 'admin123');
INSERT INTO `device_admin` VALUES (2, 'administar2', 'admin123');

-- ----------------------------
-- Table structure for quality_check
-- ----------------------------
DROP TABLE IF EXISTS `quality_check`;
CREATE TABLE `quality_check`  (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `part_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `time` date NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_part_id`(`part_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of quality_check
-- ----------------------------
INSERT INTO `quality_check` VALUES (2, '1001', '项目A', '1', '2025-09-16', '');
INSERT INTO `quality_check` VALUES (5, '1001', '项目B', '0', '2025-12-11', '备注');
INSERT INTO `quality_check` VALUES (7, '1001', '项目B', '0', '2025-12-11', '备注');
INSERT INTO `quality_check` VALUES (22, '1003', '项目C', '0', '2025-12-11', '缺陷');
INSERT INTO `quality_check` VALUES (23, '1002', '项目C', '0', '2025-12-12', '缺陷');
INSERT INTO `quality_check` VALUES (25, '1002', '项目B', '1', '2025-11-11', '');
INSERT INTO `quality_check` VALUES (26, '1002', '项目B', '1', '2025-12-05', '');
INSERT INTO `quality_check` VALUES (27, '1003', '项目C', '1', '2025-12-12', '');
INSERT INTO `quality_check` VALUES (28, '1003', '项目C', '1', '2025-02-12', '');

-- ----------------------------
-- Table structure for super_admin
-- ----------------------------
DROP TABLE IF EXISTS `super_admin`;
CREATE TABLE `super_admin`  (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '超级管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of super_admin
-- ----------------------------
INSERT INTO `super_admin` VALUES (1, '超级管理员', 'super123');

SET FOREIGN_KEY_CHECKS = 1;
