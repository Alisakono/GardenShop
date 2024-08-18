SET
@OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET
@OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET
@OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema garden
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `garden` DEFAULT CHARACTER SET utf8;
USE
`garden`;

-- -----------------------------------------------------
-- Table `garden`.`carts`
DROP TABLE IF EXISTS `garden`.`carts`;

CREATE TABLE IF NOT EXISTS `garden`.`carts`
(
    `cart_id`
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    `email`
    INT
    NOT
    NULL,
    PRIMARY
    KEY
(
    `cart_id`
),
    CONSTRAINT fk_cart_user FOREIGN KEY (`user_id`) REFERENCES `users` (`email`)
    );

-- -----------------------------------------------------
-- Table `garden`.`users`
DROP TABLE IF EXISTS `garden`.`users`;

CREATE TABLE IF NOT EXISTS `users` (
    name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    phone_number VARCHAR(45) NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('admin', 'manager', 'user') NOT NULL,
    PRIMARY KEY (email)
    );

-- -----------------------------------------------------
-- Table `garden`.`categories`
DROP TABLE IF EXISTS `garden`.`categories`;

CREATE TABLE IF NOT EXISTS `garden`.`categories`
(    `category_id`INT NOT NULL AUTO_INCREMENT,
     `name`VARCHAR(45) NOT NULL,
    PRIMARY KEY
(
    `category_id`
)

    );

-- -----------------------------------------------------
-- Table `garden`.`products`
DROP TABLE IF EXISTS `garden`.`products`;

CREATE TABLE IF NOT EXISTS `garden`.`products`
(
    `product_id`
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    `name`
    VARCHAR
(
    45
) NOT NULL,
    `description` TEXT NOT NULL,
    `price` DECIMAL
(
    10,
    2
) NOT NULL,
    `category_id` INT NOT NULL,
    `image_url` VARCHAR
(
    45
) NOT NULL,
    `discount_price` DECIMAL
(
    10,
    2
) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `updated_at` TIMESTAMP NOT NULL,
    PRIMARY KEY
(
    `product_id`
)

    );

-- -----------------------------------------------------
-- Table `garden`.`cart_items`
DROP TABLE IF EXISTS `garden`.`cart_items`;

CREATE TABLE IF NOT EXISTS `garden`.`cart_items`
(
    `cart_items_id`
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    `cart_id`
    INT
    NOT
    NULL,
    `product_id`
    INT
    NOT
    NULL,
    `quantity`
    INT
    NULL,
    PRIMARY
    KEY
(`cart_items_id`),
    CONSTRAINT fk_cart_item_cart FOREIGN KEY (`cart_id`) REFERENCES `carts` (`cart_id`),
    CONSTRAINT fk_cart_item_product FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
    );

-- -----------------------------------------------------
-- Table `garden`.`orders`
DROP TABLE IF EXISTS `garden`.`orders`;

CREATE TABLE IF NOT EXISTS `garden`.`orders`
(
    `order_id`
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    `email`
    INT
    NOT
    NULL,
    `created_at`
    TIMESTAMP
    NOT
    NULL,
    `delivery_address`
    VARCHAR
(
    45
) NOT NULL,
    `contact_phone` VARCHAR
(
    45
) NULL,
    `delivery_method` VARCHAR
(
    45
) NOT NULL,
    `status` ENUM
(
    'pending',
    'shipped',
    'delivered',
    'canceled'
) NOT NULL,
    `updated_at` TIMESTAMP NOT NULL,
    PRIMARY KEY
(
    `order_id`
),
    CONSTRAINT fk_order_user FOREIGN KEY (`email`) REFERENCES `users` (`email`)
    );

-- -----------------------------------------------------
-- Table `garden`.`favorites`
DROP TABLE IF EXISTS `garden`.`favorites`;

CREATE TABLE IF NOT EXISTS `garden`.`favorites`
(
    `favorite_id`
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    `email`
    INT
    NOT
    NULL,
    `product_id`
    INT
    NOT
    NULL,
    PRIMARY
    KEY
(
    `favorite_id`
),
    CONSTRAINT fk_favorite_user FOREIGN KEY (`email`) REFERENCES `users` (`email`),
    CONSTRAINT fk_favorite_product FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
    );

-- -----------------------------------------------------
-- Table `garden`.`order_items`
DROP TABLE IF EXISTS `garden`.`order_items`;

CREATE TABLE IF NOT EXISTS `garden`.`order_items`
(
    `order_items_id`
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    `order_id`
    INT
    NOT
    NULL,
    `product_id`
    INT
    NOT
    NULL,
    `quantity`
    INT
    NULL,
    `price_at_purchase`
    DECIMAL
(
    10,
    2
) NOT NULL,
    PRIMARY KEY
(
    `order_items_id`
),
    CONSTRAINT fk_order_item_order FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
    CONSTRAINT fk_order_item_product FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
    );

SET
SQL_MODE=@OLD_SQL_MODE;
SET
FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET
UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
