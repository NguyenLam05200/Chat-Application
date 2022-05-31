/*
 Navicat Premium Data Transfer

 Source Server         : mysql-mamp
 Source Server Type    : MySQL
 Source Server Version : 50638
 Source Host           : 127.0.0.1:8889
 Source Schema         : sakila

 Target Server Type    : MySQL
 Target Server Version : 50638
 File Encoding         : 65001

 Date: 04/11/2020 17:19:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  -- `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `id` varchar(15) NOT NULL,
  `name` varchar(255) NOT NULL,
  `createAt` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `createBy` varchar(15) NOT NULL,
  `dayStart` date DEFAULT (CURRENT_DATE),
  `dayFinish` date DEFAULT (CURRENT_DATE),
  `weekday` int(1) unsigned NOT NULL DEFAULT 2 CHECK (weekday > 1 AND weekday < 9),
  `timeStart` time DEFAULT (CURRENT_TIME),
  `timeFinish` time DEFAULT (CURRENT_TIME),
  `roomName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  FOREIGN KEY (`createBy`) REFERENCES users(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
