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
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `garden`.`carts` (
  `carts_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`carts_id`));


-- -----------------------------------------------------
-- Table `garden`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `garden`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NULL,
  `password_hash` VARCHAR(45) NOT NULL,
  `role` ENUM('admin', 'manager', 'user') NOT NULL,
  `favorites_favorite_id` INT NOT NULL,
  `orders_order_id` INT NOT NULL,
  `carts_carts_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `carts_carts_id`),
  INDEX `fk_users_carts_idx` (`carts_carts_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_carts`
    FOREIGN KEY (`carts_carts_id`)
    REFERENCES `garden`.`carts` (`carts_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `garden`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `garden`.`categories` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `garden`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `garden`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `price` DECIMAL NOT NULL,
  `category_id` INT NOT NULL,
  `iamge_url` VARCHAR(45) NOT NULL,
  `discount_price` DECIMAL NOT NULL,
  `creates_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  `categories_category_id` INT NOT NULL,
  `carts_carts_id` INT NOT NULL,
  PRIMARY KEY (`product_id`, `categories_category_id`),
  INDEX `fk_products_categories1_idx` (`categories_category_id` ASC) VISIBLE,
  INDEX `fk_products_carts1_idx` (`carts_carts_id` ASC) VISIBLE,
  CONSTRAINT `fk_products_categories1`
    FOREIGN KEY (`categories_category_id`)
    REFERENCES `garden`.`categories` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_products_carts1`
    FOREIGN KEY (`carts_carts_id`)
    REFERENCES `garden`.`carts` (`carts_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `garden`.`cart_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `garden`.`cart_items` (
  `cart_items_id` INT NOT NULL AUTO_INCREMENT,
  `cart_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NULL,
  `carts_carts_id` INT NOT NULL,
  `products_product_id` INT NOT NULL,
  PRIMARY KEY (`cart_items_id`, `carts_carts_id`, `products_product_id`),
  INDEX `fk_cart_items_carts1_idx` (`carts_carts_id` ASC) VISIBLE,
  INDEX `fk_cart_items_products1_idx` (`products_product_id` ASC) VISIBLE,
  CONSTRAINT `fk_cart_items_carts1`
    FOREIGN KEY (`carts_carts_id`)
    REFERENCES `garden`.`carts` (`carts_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_items_products1`
    FOREIGN KEY (`products_product_id`)
    REFERENCES `garden`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `garden`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `garden`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `delivery_address` VARCHAR(45) NOT NULL,
  `contact_phone` VARCHAR(45) NULL,
  `delivery_method` VARCHAR(45) NOT NULL,
  `status` ENUM('avaibel', 'notavaibel') NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  `order_items_order_item_id` INT NOT NULL,
  `users_user_id` INT NOT NULL,
  `users_carts_carts_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `users_user_id`, `users_carts_carts_id`),
  INDEX `fk_orders_users1_idx` (`users_user_id` ASC, `users_carts_carts_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`users_user_id` , `users_carts_carts_id`)
    REFERENCES `garden`.`users` (`user_id` , `carts_carts_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `garden`.`favorites`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `garden`.`favorites` (
  `favorite_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `users_user_id` INT NOT NULL,
  `users_carts_carts_id` INT NOT NULL,
  `products_product_id` INT NOT NULL,
  `products_categories_category_id` INT NOT NULL,
  PRIMARY KEY (`favorite_id`, `users_user_id`, `users_carts_carts_id`, `products_product_id`, `products_categories_category_id`),
  INDEX `fk_favorites_users1_idx` (`users_user_id` ASC, `users_carts_carts_id` ASC) VISIBLE,
  INDEX `fk_favorites_products1_idx` (`products_product_id` ASC, `products_categories_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_favorites_users1`
    FOREIGN KEY (`users_user_id` , `users_carts_carts_id`)
    REFERENCES `garden`.`users` (`user_id` , `carts_carts_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_favorites_products1`
    FOREIGN KEY (`products_product_id` , `products_categories_category_id`)
    REFERENCES `garden`.`products` (`product_id` , `categories_category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `garden`.`order_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `garden`.`order_items` (
  `order_items_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NULL,
  `price_at_purchase` DECIMAL NOT NULL,
  `orders_order_id` INT NOT NULL,
  `orders_users_user_id` INT NOT NULL,
  `orders_users_carts_carts_id` INT NOT NULL,
  `products_product_id` INT NOT NULL,
  PRIMARY KEY (`order_items_id`),
  INDEX `fk_order_items_orders1_idx` (`orders_order_id` ASC, `orders_users_user_id` ASC, `orders_users_carts_carts_id` ASC) VISIBLE,
  INDEX `fk_order_items_products1_idx` (`products_product_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_items_orders1`
    FOREIGN KEY (`orders_order_id` , `orders_users_user_id` , `orders_users_carts_carts_id`)
    REFERENCES `garden`.`orders` (`order_id` , `users_user_id` , `users_carts_carts_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_items_products1`
    FOREIGN KEY (`products_product_id`)
    REFERENCES `garden`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `garden`.`products_has_orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `garden`.`products_has_orders` (
  `products_product_id` INT NOT NULL,
  `orders_order_id` INT NOT NULL,
  `orders_users_user_id` INT NOT NULL,
  `orders_users_carts_carts_id` INT NOT NULL,
  PRIMARY KEY (`products_product_id`, `orders_order_id`, `orders_users_user_id`, `orders_users_carts_carts_id`),
  INDEX `fk_products_has_orders_orders1_idx` (`orders_order_id` ASC, `orders_users_user_id` ASC, `orders_users_carts_carts_id` ASC) VISIBLE,
  INDEX `fk_products_has_orders_products1_idx` (`products_product_id` ASC) VISIBLE,
  CONSTRAINT `fk_products_has_orders_products1`
    FOREIGN KEY (`products_product_id`)
    REFERENCES `garden`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_products_has_orders_orders1`
    FOREIGN KEY (`orders_order_id` , `orders_users_user_id` , `orders_users_carts_carts_id`)
    REFERENCES `garden`.`orders` (`order_id` , `users_user_id` , `users_carts_carts_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
