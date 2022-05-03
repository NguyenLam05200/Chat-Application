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
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL DEFAULT 'not update yet',
  `lastSeen` datetime DEFAULT (CURRENT_TIMESTAMP),
  `image` varchar(2048) DEFAULT NULL,
  `gender` boolean NOT NULL DEFAULT 0,
  `isActive` boolean NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
