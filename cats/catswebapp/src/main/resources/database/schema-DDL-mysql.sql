--DROP TABLE `GyV5uYepJV`.`UserRole`;
--DROP TABLE `GyV5uYepJV`.`Users`;
--DROP TABLE `GyV5uYepJV`.`Roles`;

CREATE TABLE IF NOT EXISTS `GyV5uYepJV`.`Cats`
(
`id` SERIAL NOT NULL AUTO_INCREMENT COMMENT 'unique id, primary key' ,
`birth` TIMESTAMP(6) COMMENT 'date of birth in timestamp' ,
`name` VARCHAR(60) NOT NULL COMMENT 'cat\'s name' ,
`owner` VARCHAR(60) COMMENT 'cat\'s owner' ,
`weight` double precision COMMENT 'cat\'s weight' ,
CONSTRAINT `PK_CAT` PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `GyV5uYepJV`.`Roles`
(
`id` SERIAL NOT NULL AUTO_INCREMENT COMMENT 'unique id, primary key' ,
`name` VARCHAR(30) NULL COMMENT 'short name, alias' ,
`role` VARCHAR(30) NOT NULL COMMENT 'valid java role' ,
CONSTRAINT `PK_ROLES` PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `GyV5uYepJV`.`Users`
(
`id` SERIAL NOT NULL COMMENT 'user id' ,
`name` VARCHAR(60) NULL DEFAULT NULL COMMENT 'name' ,
`login` VARCHAR(60) NOT NULL COMMENT 'login' ,
`password` VARCHAR(60) NOT NULL COMMENT 'password in bcrypt format' ,
`mail` VARCHAR(60) NULL COMMENT 'user e-mail' ,
`createDate` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'creation date' ,
CONSTRAINT `PK_USERS` PRIMARY KEY (`id`),
UNIQUE `LOGIN` (`login`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `GyV5uYepJV`.`UserRole`
(
`id` SERIAL NOT NULL AUTO_INCREMENT COMMENT 'user-role connection id' ,
`userId` BIGINT(20) UNSIGNED COMMENT 'user id' ,
`roleId` BIGINT(20) UNSIGNED COMMENT 'role id' ,
`createDate` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'creation date' ,
`valid` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '\'true\' if permit still valid' ,
CONSTRAINT `PK_USERROLE` PRIMARY KEY (`id`),
CONSTRAINT `FK_USER` FOREIGN KEY (`userId`) REFERENCES Users(id),
CONSTRAINT `FK_ROLE` FOREIGN KEY (`roleId`) REFERENCES Roles(id)
)
ENGINE = InnoDB;