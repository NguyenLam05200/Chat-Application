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
DROP TABLE IF EXISTS `course_week`;
CREATE TABLE `course_week` (
  -- `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `weekID` int(1) unsigned NOT NULL DEFAULT 1 CHECK (weekday > 0 AND weekday < 30),
  `courseID` varchar(15) NOT NULL,
  `updateAt` timestamp DEFAULT (CURRENT_TIMESTAMP),
  `updateBy` varchar(15) NOT NULL,
  `weekday` int(1) unsigned NOT NULL DEFAULT 2 CHECK (weekday > 1 AND weekday < 9),
  `day` date DEFAULT (CURRENT_DATE),
  `timeStart` time DEFAULT (CURRENT_TIME),
  `timeFinish` time DEFAULT (CURRENT_TIME),
  `roomName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`weekID`,`courseID`),
  KEY `idx_fk_weekID` (`weekID`),
  CONSTRAINT `fk_course_week_course` FOREIGN KEY (`courseID`) REFERENCES `courses` (`id`) ON UPDATE CASCADE,
  FOREIGN KEY (`updateBy`) REFERENCES users(`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;
