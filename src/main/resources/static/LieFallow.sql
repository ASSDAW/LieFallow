CREATE DATABASE `LieFellow` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `LieFallow`;
CREATE TABLE `user` (
  `userId` varchar(8) NOT NULL,
  `userPassword` varchar(16) NOT NULL,
  `userName` varchar(16) NOT NULL,
  `userAge` int(11) NOT NULL,
  `userSex` varchar(2) DEFAULT NULL,
  `userPhoto` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `remark` (
  `remarkId` int(8) NOT NULL AUTO_INCREMENT,
  `remarkUserId` varchar(16) NOT NULL,
  `remarkContent` varchar(64) NOT NULL,
  `remarkTime` timestamp NOT NULL,
  `articleId` int(8) NOT NULL,
  PRIMARY KEY (`remarkId`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `message` (
  `messageId` int(11) NOT NULL AUTO_INCREMENT,
  `senderId` varchar(45) NOT NULL,
  `receiverId` varchar(45) NOT NULL,
  `sendMessage` varchar(45) NOT NULL,
  `sendTime` timestamp NOT NULL,
  `isRead` int(11) NOT NULL,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `concern` (
  `concernId` int(11) NOT NULL AUTO_INCREMENT,
  `concernUserId` varchar(16) NOT NULL,
  `concernPeopleId` varchar(16) NOT NULL,
  PRIMARY KEY (`concernId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `collection` (
  `collectionId` int(8) NOT NULL AUTO_INCREMENT,
  `collectionUserId` int(8) NOT NULL,
  `collectionArticleId` int(8) NOT NULL,
  PRIMARY KEY (`collectionId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  PRIMARY KEY (`articleId`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into user values('101','123456','亚索',22,'男','/static/uploadFile/yasuo.jpg');
insert into user values('102','123456','提莫',22,'男','/static/uploadFile/Timor.jpg');
insert into user values('103','123456','贾克斯',22,'男','/static/uploadFile/jiakesi.jpg');
insert into user values('104','123456','李青',22,'男','/static/uploadFile/liqing.jpg');
insert into user values('105','123456','瑞兹',22,'男','/static/uploadFile/ruizi.jpg');
insert into user values('105','123456','girl',22,'女','/static/uploadFile/girl.jpg');


insert into remark values(1,'102','评论1','2019-08-21 21:43:49',1);
insert into remark values(2,'101','评论2','2019-08-21 21:43:49',1);
insert into remark values(3,'103','评论3','2019-08-21 21:43:49',2);
insert into remark values(4,'104','评论4','2019-08-21 21:43:49',3);
insert into remark values(5,'105','评论5','2019-08-21 21:43:49',3);
insert into remark values(5,'105','评论6','2019-08-21 21:43:49',4);
insert into remark values(5,'105','评论7','2019-08-21 21:43:49',5);

insert into message values('1', '101', '102', '66', '2019-12-06 21:59:34', '0');
insert into message values('2', '102', '103', '33', '2019-12-06 22:05:39', '0');
insert into message values('3', '101', '102', '666', '2019-12-06 22:05:49', '0');
insert into message values('4', '102', '103', '33', '2019-12-06 22:05:39', '0');

insert into concern values('1', '101', '102');
insert into concern values('2', '101', '103');
insert into concern values('3', '101', '104');
insert into concern values('4', '102', '103');

insert into collection values('1', '101', '1');
insert into collection values('2', '101', '2');
insert into collection values('3', '101', '3');
insert into collection values('4', '102', '4');

insert into article values('1', '帖子1', '2019年8月13日12:38:47', 'read', NULL, '101', '2019-08-13 12:38:49', '2019-08-13 12:38:49', '2', '0');
insert into article values('2', '帖子2', '2019年8月13日12:38:47', 'music', NULL, '101', '2019-08-13 12:38:49', '2019-08-13 12:38:49', '1', '0');
insert into article values('3', '帖子3', '2019年8月13日12:38:47', 'movie',NULL, '101', '2019-08-13 12:38:49', '2019-08-13 12:38:49', '2', '0');
insert into article values('4', '帖子4', '2019年8月13日12:38:47', 'read', NULL, '101', '2019-08-13 12:38:49', '2019-08-13 12:38:49', '1', '0');
insert into article values('5', '帖子5', '2019年8月13日12:38:47', 'read', NULL, '101', '2019-08-13 12:38:49', '2019-08-13 12:38:49', '1', '0');


