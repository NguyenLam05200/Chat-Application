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
 
-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user` (
  -- `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userID` int(11) unsigned NOT NULL,
  `groupID` int(11) unsigned NOT NULL,
  `importAt` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `importBy` int(11) unsigned NOT NULL,
  `isActive` boolean NOT NULL DEFAULT 1,
  PRIMARY KEY (`userID`,`groupID`),
  KEY `idx_fk_userID` (`userID`),
  CONSTRAINT `fk_group_user_user` FOREIGN KEY (`userID`) REFERENCES `users` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_group_user_group` FOREIGN KEY (`groupID`) REFERENCES `groups` (`id`) ON UPDATE CASCADE,
  FOREIGN KEY (`importBy`) REFERENCES users(`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;
