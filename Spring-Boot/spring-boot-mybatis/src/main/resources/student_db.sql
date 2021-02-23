/*
 Navicat Premium Data Transfer

 Source Server         : Absolute Shining Core
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : student_db

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 16/05/2020 22:32:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `ID` bigint(20) NOT NULL COMMENT '主键',
  `STUDENT_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学生姓名',
  `STUDENT_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学生学号',
  `SEX` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '性别',
  `AGE` int(11) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES (1, '王疾风', 'G030510', '男', 18);
INSERT INTO `t_student` VALUES (2, '余翰林', 'G030511', '女', 17);
INSERT INTO `t_student` VALUES (3, '刘权', 'G030533', '男', 19);
INSERT INTO `t_student` VALUES (4, '王争成', 'G030512', '男', 19);

SET FOREIGN_KEY_CHECKS = 1;
