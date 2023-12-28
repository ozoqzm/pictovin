DROP TABLE ALARM CASCADE CONSTRAINTS;
DROP TABLE ALBUM CASCADE CONSTRAINTS;
DROP TABLE MEMBERSHIP CASCADE CONSTRAINTS;
DROP TABLE PERSON CASCADE CONSTRAINTS;
DROP TABLE USERINFO CASCADE CONSTRAINTS;
DROP TABLE REPLY CASCADE CONSTRAINTS;
DROP TABLE STUDIO CASCADE CONSTRAINTS;
DROP TABLE TAG CASCADE CONSTRAINTS;
DROP TABLE UNSHAREDALBUM CASCADE CONSTRAINTS;
DROP TABLE POST CASCADE CONSTRAINTS;
DROP TABLE PHOTO CASCADE CONSTRAINTS;
DROP TABLE DECOITEM CASCADE CONSTRAINTS;

DROP VIEW UnsharedAlbum;


DROP SEQUENCE Sequence_alarmId;
DROP SEQUENCE Sequence_albumId;
DROP SEQUENCE Sequence_commentId;
DROP SEQUENCE Sequence_membershipId;
DROP SEQUENCE Sequence_postId;
DROP SEQUENCE Sequence_studioId;
DROP SEQUENCE Sequence_userId;




CREATE SEQUENCE Sequence_alarmId
   INCREMENT BY 1
   START WITH 1000001;

CREATE SEQUENCE Sequence_albumId
   INCREMENT BY 1
   START WITH 1;

CREATE SEQUENCE Sequence_commentId
   INCREMENT BY 1
   START WITH 100001;

CREATE SEQUENCE Sequence_membershipId
   INCREMENT BY 1
   START WITH 1001;

CREATE SEQUENCE Sequence_postId
   INCREMENT BY 1
   START WITH 1;

CREATE SEQUENCE Sequence_studioId
   INCREMENT BY 1
   START WITH 1;

CREATE SEQUENCE Sequence_userId
   INCREMENT BY 1
   START WITH 1;

CREATE TABLE Album
(
   albumId              INTEGER  NOT NULL ,
   albumName            VARCHAR2(20)  NULL ,
   createDate           VARCHAR2(20)  NULL ,
   creatorName          VARCHAR2(20)  NULL ,
   explanation          VARCHAR2(20)  NULL ,
   isShared             VARCHAR2(20)  NULL ,
   userId               INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKAlbum ON Album
(albumId   ASC);

ALTER TABLE Album
   ADD CONSTRAINT  XPKAlbum PRIMARY KEY (albumId);

CREATE TABLE USERINFO
(
   userId               INTEGER  NOT NULL ,
   userName             VARCHAR2(20)  NULL ,
   password             VARCHAR2(20)  NULL ,
   email                VARCHAR2(20)  NULL 
);

CREATE UNIQUE INDEX XPKUser ON USERINFO
(userId   ASC);

ALTER TABLE USERINFO
   ADD CONSTRAINT  XPKUser PRIMARY KEY (userId);

CREATE TABLE Membership
(
   membershipId         INTEGER  NOT NULL ,
   userId               INTEGER  NOT NULL ,
   albumId              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKMembership ON Membership
(membershipId   ASC);

ALTER TABLE Membership
   ADD CONSTRAINT  XPKMembership PRIMARY KEY (membershipId);

CREATE TABLE Alarm
(
   alarmId              INTEGER  NOT NULL ,
   alarmCategory        VARCHAR2(20)  NULL ,
   alarmContents        VARCHAR2(20)  NULL ,
   userId               INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKAlarm ON Alarm
(alarmId   ASC);

ALTER TABLE Alarm
   ADD CONSTRAINT  XPKAlarm PRIMARY KEY (alarmId);

CREATE TABLE Photo
(
   imagePath            VARCHAR2(20)  NULL ,
   postId               INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKPhoto ON Photo
(postId   ASC);

ALTER TABLE Photo
   ADD CONSTRAINT  XPKPhoto PRIMARY KEY (postId);

CREATE TABLE Tag
(
   tagName              VARCHAR2(20)  NOT NULL ,
   tagCategory          VARCHAR2(20)  NULL ,
   isSelected           INTEGER  NULL ,
   postId               INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKTag ON Tag
(tagName   ASC);

ALTER TABLE Tag
   ADD CONSTRAINT  XPKTag PRIMARY KEY (tagName);

CREATE TABLE Decoitem
(
   itemName             VARCHAR2(20)  NOT NULL ,
   itemImage            VARCHAR2(20)  NULL ,
   itemDescription      VARCHAR2(20)  NULL ,
   deco_size            VARCHAR2(20)  NULL ,
   deco_location        VARCHAR2(20)  NULL ,
   postId               INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKDecoitem ON Decoitem
(itemName   ASC);

ALTER TABLE Decoitem
   ADD CONSTRAINT  XPKDecoitem PRIMARY KEY (itemName);

CREATE TABLE Post
(
   postId               INTEGER  NOT NULL ,
   postContent          VARCHAR2(50)  NULL ,
   privacyStatus        VARCHAR2(20)  NULL ,
   postDate             VARCHAR2(20)  NULL ,
   userId               INTEGER  NOT NULL ,
   albumId              INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKPost ON Post
(postId   ASC);

ALTER TABLE Post
   ADD CONSTRAINT  XPKPost PRIMARY KEY (postId);

CREATE TABLE Reply
(
   commentId            INTEGER  NOT NULL ,
   commentText          VARCHAR2(20)  NULL ,
   commentDate          VARCHAR2(20)  NULL ,
   userId               INTEGER  NOT NULL ,
   postId               INTEGER  NOT NULL 
);

CREATE UNIQUE INDEX XPKComment ON Reply
(commentId   ASC);

ALTER TABLE Reply
   ADD CONSTRAINT  XPKComment PRIMARY KEY (commentId);

CREATE TABLE Studio
(
   studioId             VARCHAR2(20)  NOT NULL ,
   studioName           VARCHAR2(20)  NULL ,
   frameCount           INTEGER  NULL ,
   frameImages          VARCHAR2(20)  NULL 
);

CREATE UNIQUE INDEX XPKStudio ON Studio
(studioId   ASC);

ALTER TABLE Studio
   ADD CONSTRAINT  XPKStudio PRIMARY KEY (studioId);

ALTER TABLE Album
   ADD (
CONSTRAINT R_4 FOREIGN KEY (userId) REFERENCES USERINFO (userId));

ALTER TABLE Membership
   ADD (
CONSTRAINT R_2 FOREIGN KEY (userId) REFERENCES USERINFO (userId));

ALTER TABLE Membership
   ADD (
CONSTRAINT R_12 FOREIGN KEY (albumId) REFERENCES Album (albumId));

ALTER TABLE Alarm
   ADD (
CONSTRAINT R_5 FOREIGN KEY (userId) REFERENCES USERINFO (userId));

ALTER TABLE Photo
   ADD (
CONSTRAINT R_9 FOREIGN KEY (postId) REFERENCES Post (postId));

ALTER TABLE Tag
   ADD (
CONSTRAINT R_11 FOREIGN KEY (postId) REFERENCES Photo (postId));

ALTER TABLE Decoitem
   ADD (
CONSTRAINT R_10 FOREIGN KEY (postId) REFERENCES Photo (postId));

ALTER TABLE Post
   ADD (
CONSTRAINT R_6 FOREIGN KEY (userId) REFERENCES USERINFO (userId));

ALTER TABLE Post
   ADD (
CONSTRAINT R_8 FOREIGN KEY (albumId) REFERENCES Album (albumId));

ALTER TABLE Reply
   ADD (
CONSTRAINT R_3 FOREIGN KEY (userId) REFERENCES USERINFO (userId));

ALTER TABLE Reply
   ADD (
CONSTRAINT R_7 FOREIGN KEY (postId) REFERENCES Post (postId));

