CREATE TABLE `project` (
    `id`      int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`    varchar(255) NOT NULL,
    `owner`   varchar(255) NOT NULL,
    `intro`   text NOT NULL,
    `status`  int(2) NOT NULL,
    `start_date_time` datetime,
    `end_date_time` datetime,
    `created` datetime  DEFAULT CURRENT_TIMESTAMP,
    `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `uidx_project` (`name`)
)AUTO_INCREMENT = 100;

