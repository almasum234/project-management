CREATE TABLE `project` (
   `id` 	  bigint NOT NULL AUTO_INCREMENT,
   `name`    varchar(255) NOT NULL,
   `owner`   varchar(255) NOT NULL,
   `intro`   text NOT NULL,
   `status`  int NOT NULL,
   `start_date_time` datetime,
   `end_date_time` datetime,
   `created` datetime  DEFAULT CURRENT_TIMESTAMP,
   `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE INDEX `uidx_project` (`name`)
) AUTO_INCREMENT = 100;

CREATE TABLE `user_account` (
    `id`       bigint NOT NULL AUTO_INCREMENT,
    `password` varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    `nickname` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);