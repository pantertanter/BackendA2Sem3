use startcode;
CREATE TABLE `roles` (
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `user_name` varchar(25) NOT NULL,
  `user_pass` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_roles` (
  `role_name` varchar(20) NOT NULL,
  `user_name` varchar(25) NOT NULL,
  PRIMARY KEY (`role_name`,`user_name`),
  KEY `FK_user_roles_user_name` (`user_name`),
  CONSTRAINT `FK_user_roles_role_name` FOREIGN KEY (`role_name`) REFERENCES `roles` (`role_name`),
  CONSTRAINT `FK_user_roles_user_name` FOREIGN KEY (`user_name`) REFERENCES `users` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `library_item` (
  `library_item_id` int NOT NULL AUTO_INCREMENT,
  `book_key` varchar(16) NOT NULL,
  `rating` int NOT NULL,
  `status` varchar(16) NOT NULL,
  PRIMARY KEY (`library_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_library` (
  `user` varchar(25) NOT NULL,
  `library_item_id` int NOT NULL,
  PRIMARY KEY (`user`,`library_item_id`),
  KEY `FK_user_library_library_item_id` (`library_item_id`),
  CONSTRAINT `FK_user_library_library_item_id` FOREIGN KEY (`library_item_id`) REFERENCES `library_item` (`library_item_id`),
  CONSTRAINT `FK_user_library_user` FOREIGN KEY (`user`) REFERENCES `users` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
