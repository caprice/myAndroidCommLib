CREATE SCHEMA crs DEFAULT CHARACTER SET utf8 ;

CREATE TABLE crs.user (
  username VARCHAR(50) NOT NULL ,
  name VARCHAR(50) NULL ,
  password VARCHAR(50) NULL ,
  email VARCHAR(50) NULL ,
  status SMALLINT NULL ,
  createDate varchar(20),
  updateDate varchar(20),
  PRIMARY KEY (username)
);

CREATE TABLE crs.userdetail (
  username VARCHAR(50) NOT NULL ,
  nickname VARCHAR(50) NULL ,
  plate VARCHAR(50) NULL ,
  phone VARCHAR(50) NULL ,
  idcard varchar(30) NULL,
  photo varchar(80) NULL,
  sex SMALLINT NULL ,
  age INT NULL ,
  birth varchar(20),
  profession VARCHAR(45) NULL ,
  signature VARCHAR(255) NULL ,
  location VARCHAR(255) NULL ,
  PRIMARY KEY (username)
);


CREATE TABLE crs.trip (
  id int NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL ,
  latitude decimal(10,6) NULL ,
  longitude decimal(10,6) NULL ,
  title VARCHAR(50) NULL ,
  mapurl VARCHAR(500) NULL ,
  recordtime bigint,
  geohashcode varchar(50) NULL,
  PRIMARY KEY (id)
);

CREATE TABLE crs.triptrack (
  id int NOT NULL AUTO_INCREMENT ,
  tripid VARCHAR(50) NOT NULL ,
  latitude decimal(10,6) NULL ,
  longitude decimal(10,6) NULL ,
  altitude decimal(10,6) NULL ,
  recordtime bigint,
  distance decimal(10,6) NULL,
  speed decimal(10,6) NULL,
  routetitle varchar(100) NULL,
  PRIMARY KEY (id)
);
