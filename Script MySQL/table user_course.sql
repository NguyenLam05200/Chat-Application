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
DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course` (
  -- `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userID` varchar(15) NOT NULL,
  `courseID` varchar(15) NOT NULL,
  `importAt` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `importBy` varchar(15) NOT NULL,
  `isActive` boolean NOT NULL DEFAULT 1,
  `week_1` boolean NOT NULL DEFAULT 0,
  `week_2` boolean NOT NULL DEFAULT 0,
  `week_3` boolean NOT NULL DEFAULT 0,
  `week_4` boolean NOT NULL DEFAULT 0,
  `week_5` boolean NOT NULL DEFAULT 0,
  `week_6` boolean NOT NULL DEFAULT 0,
  `week_7` boolean NOT NULL DEFAULT 0,
  `week_8` boolean NOT NULL DEFAULT 0,
  `week_9` boolean NOT NULL DEFAULT 0,
  `week_10` boolean NOT NULL DEFAULT 0,
  `week_11` boolean NOT NULL DEFAULT 0,
  `week_12` boolean NOT NULL DEFAULT 0,
  `week_13` boolean NOT NULL DEFAULT 0,
  `week_14` boolean NOT NULL DEFAULT 0,
  `week_15` boolean NOT NULL DEFAULT 0,
  PRIMARY KEY (`userID`,`courseID`),
  KEY `idx_fk_userID` (`userID`),
  CONSTRAINT `fk_user_course_user` FOREIGN KEY (`userID`) REFERENCES `users` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_user_course_course` FOREIGN KEY (`courseID`) REFERENCES `courses` (`id`) ON UPDATE CASCADE,
  FOREIGN KEY (`importBy`) REFERENCES users(`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;
