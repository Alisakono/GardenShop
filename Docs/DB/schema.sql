-- MySQL Script generated by MySQL Workbench
-- Sun Aug 11 13:06:12 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema garden
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema garden
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `garden` DEFAULT CHARACTER SET utf8 ;
USE `garden` ;

-- -----------------------------------------------------
-- Table `garden`.`carts`
DROP TABLE IF EXISTS `garden`.`carts`;

CREATE TABLE IF NOT EXISTS `garden`.`carts` (
  `cart_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`cart_id`));


-- -----------------------------------------------------
-- Table `garden`.`user`
DROP TABLE IF EXISTS `garden`.`users`;

CREATE TABLE IF NOT EXISTS `garden`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NULL DEFAULT CURRENT_TIMESTAMP,
  `password_hash` VARCHAR(45) NOT NULL,
  `role` ENUM('admin', 'manager', 'user') NOT NULL,
  PRIMARY KEY (`user_id`));



-- -----------------------------------------------------
-- Table `garden`.`categories`
DROP TABLE IF EXISTS `garden`.`categories`;

CREATE TABLE IF NOT EXISTS `garden`.`categories` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `garden`.`products`
DROP TABLE IF EXISTS `garden`.`products`;

CREATE TABLE IF NOT EXISTS `garden`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `price` DECIMAL NOT NULL,
  `image_url` VARCHAR(45) NOT NULL,
  `discount_price` DECIMAL NOT NULL,
  `creates_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`product_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `garden`.`cart_items`
DROP TABLE IF EXISTS `garden`.`carts_items`;

CREATE TABLE IF NOT EXISTS `garden`.`cart_items` (
  `cart_items_id` INT NOT NULL AUTO_INCREMENT,
  `cart_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NULL,
  PRIMARY KEY (`cart_items_id`))

ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `garden`.`orders`
DROP TABLE IF EXISTS `garden`.`orders`;

CREATE TABLE IF NOT EXISTS `garden`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `delivery_address` VARCHAR(45) NOT NULL,
  `contact_phone` VARCHAR(45) NULL,
  `delivery_method` VARCHAR(45) NOT NULL,
  `status` ENUM('avaibel', 'notavaibel') NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`order_id`))

ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `garden`.`favorites`
DROP TABLE IF EXISTS `garden`.`favorites`;

CREATE TABLE IF NOT EXISTS `garden`.`favorites` (
  `favorite_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`favorite_id`))

ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `garden`.`order_items`
DROP TABLE IF EXISTS `garden`.`order_items`;

CREATE TABLE IF NOT EXISTS `garden`.`order_items` (
  `order_items_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NULL,
  `price_at_purchase` DECIMAL NOT NULL,
  PRIMARY KEY (`order_items_id`))

ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `garden`.`products_has_orders`
DROP TABLE IF EXISTS `garden`.`products_has_orders`;

CREATE TABLE IF NOT EXISTS `garden`.`products_has_orders`
(
    `products_product_id`         INT NOT NULL,
    `orders_order_id`             INT NOT NULL,
    `orders_users_user_id`        INT NOT NULL,
    `orders_users_carts_carts_id` INT NOT NULL,
    `product_id`                  int not null,
    PRIMARY KEY (`product_id`)
)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
