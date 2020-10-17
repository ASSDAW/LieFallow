CREATE DATABASE `LieFallow` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `LieFallow`;
CREATE TABLE `article` (
  `articleId` int(11) NOT NULL AUTO_INCREMENT,
  `articleTitle` varchar(64) NOT NULL,
  `articleContent` varchar(512) NOT NULL,
  `articleTag` varchar(16) NOT NULL,
  `articleInputFileURL` varchar(256) DEFAULT NULL,
  `articleAuthorId` varchar(16) NOT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `lastRemarkTime` timestamp NULL DEFAULT NULL,
  `remarkNum` int(11) DEFAULT NULL,
  `hitNum` int(11) DEFAULT NULL,
  `isDel` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `collection` (
  `collectionId` int(8) NOT NULL AUTO_INCREMENT,
  `collectionUserId` int(8) NOT NULL,
  `collectionArticleId` int(8) NOT NULL,
  PRIMARY KEY (`collectionId`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `concern` (
  `concernId` int(11) NOT NULL AUTO_INCREMENT,
  `concernUserId` varchar(16) NOT NULL,
  `concernPeopleId` varchar(16) NOT NULL,
  PRIMARY KEY (`concernId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `message` (
  `messageId` int(11) NOT NULL AUTO_INCREMENT,
  `senderId` varchar(45) NOT NULL,
  `receiverId` varchar(45) NOT NULL,
  `sendMessage` varchar(45) NOT NULL,
  `sendTime` timestamp NOT NULL,
  `isRead` int(11) NOT NULL,
  `isDel` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `remark` (
  `remarkId` int(8) NOT NULL AUTO_INCREMENT,
  `remarkUserId` varchar(16) NOT NULL,
  `remarkContent` varchar(64) NOT NULL,
  `remarkTime` timestamp NOT NULL,
  `articleId` int(8) NOT NULL,
  `isDel` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`remarkId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `user` (
  `userId` varchar(8) NOT NULL,
  `userPassword` varchar(128) NOT NULL,
  `salt` varchar(128) NOT NULL,
  `userName` varchar(16) NOT NULL,
  `userAge` int(11) NOT NULL,
  `userSex` varchar(2) DEFAULT NULL,
  `userPhoto` varchar(256) DEFAULT NULL,
  `isLock` tinyint(4) DEFAULT '0',
  `isAdmin` int(11) DEFAULT '0',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
