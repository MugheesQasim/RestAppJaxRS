CREATE TABLE `item_category` (
                                 `id` int(11) NOT NULL,
                                 `category_name` varchar(45) NOT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `item_location` (
                                 `id` int(11) NOT NULL,
                                 `location_name` varchar(45) NOT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `inventory` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `item_name` varchar(45) NOT NULL,
                             `item_quantity` int(11) NOT NULL,
                             `item_category_id` int(11) NOT NULL,
                             `item_location_id` int(11) NOT NULL,
                             PRIMARY KEY (`id`),
                             KEY `item_category_id_idx` (`item_category_id`),
                             KEY `item_location_id_idx` (`item_location_id`),
                             CONSTRAINT `item_category_id` FOREIGN KEY (`item_category_id`) REFERENCES `item_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                             CONSTRAINT `item_location_id` FOREIGN KEY (`item_location_id`) REFERENCES `item_location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE users
(
    username VARCHAR(90) PRIMARY KEY,
    password VARCHAR(90)
);