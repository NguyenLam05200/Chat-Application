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
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `createAt` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `createBy` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  FOREIGN KEY (`createBy`) REFERENCES users(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
