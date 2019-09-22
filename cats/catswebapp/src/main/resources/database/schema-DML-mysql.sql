DELETE FROM `GyV5uYepJV`.`Cats`;
DELETE FROM `GyV5uYepJV`.`UserRole`;
DELETE FROM `GyV5uYepJV`.`Users`;
DELETE FROM `GyV5uYepJV`.`Roles`;


INSERT INTO `GyV5uYepJV`.`Cats` (`id`, `birth`, `name`, `owner`, `weight`) VALUES ('91', '2019-09-01 00:00:00.000000', 'Olek', 'Kasia', '3');
  INSERT INTO `GyV5uYepJV`.`Cats` (`id`, `birth`, `name`, `owner`, `weight`) VALUES ('92', '2019-10-01 00:00:00.000000', 'Kizia', 'Kasia', '1.5');
  INSERT INTO `GyV5uYepJV`.`Cats` (`id`, `birth`, `name`, `owner`, `weight`) VALUES ('93', '2019-09-20 00:00:00.000000', 'Mruk', 'Grześ', '2.7');

  INSERT INTO `GyV5uYepJV`.`Roles` (`id`, `name`, `role`) VALUES ('91', 'user', 'ROLE_USER');
  INSERT INTO `GyV5uYepJV`.`Roles` (`id`, `name`, `role`) VALUES ('92', 'admin', 'ROLE_ADMIN');
  INSERT INTO `GyV5uYepJV`.`Roles` (`id`, `name`, `role`) VALUES ('93', 'none', 'ROLE_NONE');

  INSERT INTO `GyV5uYepJV`.`Users` (`id`, `name`, `login`, `password`, `mail`, `createDate`) VALUES ('91', 'user', 'user', '$2a$11$JttaPSYHQZwG4o3lA0cSXu19TSTjWO8wj6.0.leaRKyTUpQbXt5oy', 'user@gmail.com', '2019-09-01 00:00:00.000000');
  INSERT INTO `GyV5uYepJV`.`Users` (`id`, `name`, `login`, `password`, `mail`, `createDate`) VALUES ('92', 'admin', 'admin', '$2a$11$WRZ/TAcbJa5xKcQzzVCNyu4kHzzYDky4zTDjaeOsLfc3rJNUK4Vki', 'admin@gmail.com', '2019-09-01 00:00:00.000000');
  INSERT INTO `GyV5uYepJV`.`Users` (`id`, `name`, `login`, `password`, `mail`, `createDate`) VALUES ('93', 'none', 'none', '$2y$11$l7zCBc0.n3oNHXDyBRKWXekIrhPTzB7K/bRYqsL50iVYAMkls9Hsu', 'none@gmail.com', '2019-09-11 00:00:00.000000');

  INSERT INTO `GyV5uYepJV`.`UserRole` (`id`, `userId`, `roleId`, `createDate`, `valid`) VALUES ('91', '91', '91', '2019-09-01 00:00:00.000000', '1');
  INSERT INTO `GyV5uYepJV`.`UserRole` (`id`, `userId`, `roleId`, `createDate`, `valid`) VALUES ('92', '92', '92', '2019-09-01 00:00:00.000000', '1');
  INSERT INTO `GyV5uYepJV`.`UserRole` (`id`, `userId`, `roleId`, `createDate`, `valid`) VALUES ('93', '91', '92', '2019-09-01 00:00:00.000000', '0');