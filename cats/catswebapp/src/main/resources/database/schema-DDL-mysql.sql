--DROP TABLE `GyV5uYepJV`.`UserRole`;
--DROP TABLE `GyV5uYepJV`.`Users`;
--DROP TABLE `GyV5uYepJV`.`Roles`;
--DROP VIEW `GyV5uYepJV.mySchema.cat`;
--DROP TABLE `GyV5uYepJV`.`Cats`;
--DROP TABLE `GyV5uYepJV`.`CatFoto`;

create TABLE IF NOT EXISTS `GyV5uYepJV`.`Roles`
(
`id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'unique id, primary key' ,
`name` VARCHAR(30) NULL COMMENT 'short name, alias' ,
`role` VARCHAR(30) NOT NULL COMMENT 'valid java role' ,
CONSTRAINT `PK_ROLES` PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

create TABLE IF NOT EXISTS `GyV5uYepJV`.`Users`
(
`id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'user id' ,
`name` VARCHAR(60) NULL DEFAULT NULL COMMENT 'name' ,
`login` VARCHAR(60) NOT NULL COMMENT 'login' ,
`password` VARCHAR(60) NOT NULL COMMENT 'password in bcrypt format' ,
`mail` VARCHAR(60) NULL COMMENT 'user e-mail' ,
`createDate` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'creation date' ,
CONSTRAINT `PK_USERS` PRIMARY KEY (`id`),
UNIQUE `LOGIN` (`login`)
)
ENGINE = InnoDB;

create TABLE IF NOT EXISTS `GyV5uYepJV`.`UserRole`
(
`id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'user-role connection id' ,
`userId` int(20) UNSIGNED NOT NULL COMMENT 'user id' ,
`roleId` int(20) UNSIGNED NOT NULL COMMENT 'role id' ,
`createDate` TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT 'creation date' ,
`valid` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '\'true\' if permit still valid' ,
CONSTRAINT `PK_USERROLE` PRIMARY KEY (`id`),
CONSTRAINT `FK_USER` FOREIGN KEY (`userId`) REFERENCES `GyV5uYepJV`.`Users`(`id`),
CONSTRAINT `FK_ROLE` FOREIGN KEY (`roleId`) REFERENCES `GyV5uYepJV`.`Roles`(`id`)
)
ENGINE = InnoDB;

create TABLE IF NOT EXISTS `GyV5uYepJV`.`CatFoto`(
`id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'unique id, primary key' ,
`comment` VARCHAR(500) NULL COMMENT 'comment to file' ,
`oldName` VARCHAR(30) NOT NULL COMMENT 'name in uploud' ,
`newName` VARCHAR(30) NOT NULL COMMENT 'name in cloud' ,
`size` BIGINT(30) NOT NULL COMMENT 'size of file in bytes' ,
`type` VARCHAR(30) NOT NULL COMMENT 'type of file' ,
CONSTRAINT `PK_ROLES` PRIMARY KEY (`id`)
)ENGINE = InnoDB;

create TABLE IF NOT EXISTS `GyV5uYepJV`.`Cats`(
`id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'unique id, primary key'
, `fotoId` int(20) UNSIGNED COMMENT 'profile foto id'
, `birth` TIMESTAMP(6) COMMENT 'date of birth in timestamp'
, `name` VARCHAR(60) NOT NULL COMMENT 'cat\`s name'
, `owner` VARCHAR(60) COMMENT 'cat\`s owner'
, `weight` double precision COMMENT 'cat\`s weight'
, CONSTRAINT `PK_CAT` PRIMARY KEY (`id`)
, CONSTRAINT `FK_FOTO` FOREIGN KEY (`fotoId`) REFERENCES `GyV5uYepJV`.`CatFoto`(`id`)
, UNIQUE `FOTOID` (`fotoId`)
)ENGINE = InnoDB;
/* check long */
/* vievs forbidden on remotemysql.com
CREATE VIEW mySchema.cat
AS
SELECT
    c.`id`, c.`birth`, c.`name`, c.`owner`, c.`weight`,
    f.`comment`, f.`oldName`, f.`newName`, f.`size`, f.`type`
FROM
    `GyV5uYepJV`.`CatFoto` AS f LEFT JOIN `GyV5uYepJV`.`Cats` AS c ON f.catId = c.id;
*/