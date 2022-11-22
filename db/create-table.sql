CREATE TABLE `sys_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) NOT NULL DEFAULT '',
  `permission_name` varchar(100) NOT NULL DEFAULT '',
  `memo` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL DEFAULT '',
  `role_name` varchar(100) NOT NULL DEFAULT '',
  `memo` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sys_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_roles_permissions_0` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL DEFAULT '',
  `avatar` varchar(200) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL COMMENT '0-未提供；1-男；2-女',
  `birthday` varchar(10) DEFAULT NULL COMMENT '格式：yyyy-MM-dd',
  `phone` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(100) NOT NULL DEFAULT '',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '0-禁用；1-启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_roles_0` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `file_upload` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `table_name` VARCHAR(50) NOT NULL DEFAULT '',
    `row_id` BIGINT NOT NULL DEFAULT -1,
    `full_path` VARCHAR(500) NOT NULL DEFAULT '',
    `file_name` VARCHAR(200) NOT NULL DEFAULT '',
    `delete_status` CHAR(1) NOT NULL DEFAULT '0' COMMENT '0-未删除；1-逻辑删除；2-物理删除',
    PRIMARY KEY (`id`),
    KEY `i_file_upload_0` (`table_name` , `row_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `country` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `country_code` VARCHAR(20) NOT NULL DEFAULT '',
    `country_name` VARCHAR(100) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `language` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `language_code` VARCHAR(20) NOT NULL DEFAULT '',
    `language_name` VARCHAR(100) NOT NULL DEFAULT '',
    `is_roman` CHAR(1) NOT NULL DEFAULT '0' COMMENT '0-不是；1-是。不是罗马字母的，需要罗马字母转写表',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ct_roman` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `country_code` VARCHAR(20) NOT NULL DEFAULT '',
    `language_code` VARCHAR(20) NOT NULL DEFAULT '',
    `original_alpha` VARCHAR(10) NOT NULL DEFAULT '',
    `roman_alpha` VARCHAR(10) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`),
    KEY `i_ct_roman_0` (`country_code` , `language_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;