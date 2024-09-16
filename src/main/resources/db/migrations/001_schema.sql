-- liquibase formatted sql
-- changeset Konovalov:001

CREATE SCHEMA IF NOT EXISTS `garden` DEFAULT CHARACTER SET utf8;
USE `garden`;

CREATE TABLE IF NOT EXISTS `garden`.`users` (
name VARCHAR(45) NOT NULL,
email VARCHAR(45) NOT NULL,
phone_number VARCHAR(45) NULL,
password_hash VARCHAR(255) NOT NULL,
role ENUM('admin', 'manager', 'user') NOT NULL,
PRIMARY KEY (email)
);

CREATE TABLE IF NOT EXISTS `garden`.`categories` (
category_id VARCHAR(45) NOT NULL,
category_name VARCHAR(45) NOT NULL,
PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS `garden`.`products` (
product_id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(45) NOT NULL,
description TEXT NOT NULL,
price DECIMAL(10,2) NOT NULL,
category_id VARCHAR(45) NOT NULL,
image_url VARCHAR(45) NOT NULL,
discount_price DECIMAL(10,2) NOT NULL,
created_at TIMESTAMP NOT NULL,
updated_at TIMESTAMP NOT NULL,
PRIMARY KEY (product_id),
FOREIGN KEY (category_id) REFERENCES categories (category_id)
);

CREATE TABLE IF NOT EXISTS `garden`.`carts` (
cart_id INT NOT NULL AUTO_INCREMENT,
email VARCHAR(45) NOT NULL,
PRIMARY KEY (cart_id),
FOREIGN KEY (email) REFERENCES users (email)
);

CREATE TABLE IF NOT EXISTS `garden`.`cart_items` (
cart_items_id INT NOT NULL AUTO_INCREMENT,
cart_id INT NOT NULL,
product_id INT NOT NULL,
quantity INT NULL,
PRIMARY KEY (cart_items_id),
FOREIGN KEY (cart_id) REFERENCES carts (cart_id),
FOREIGN KEY (product_id) REFERENCES products (product_id)
);

CREATE TABLE IF NOT EXISTS `garden`.`orders` (
order_id INT NOT NULL AUTO_INCREMENT,
email VARCHAR(45) NOT NULL,
created_at TIMESTAMP NOT NULL,
delivery_address VARCHAR(45) NOT NULL,
contact_phone VARCHAR(45) NULL,
delivery_method VARCHAR(45) NOT NULL,
status ENUM('pending', 'shipped', 'delivered', 'canceled') NOT NULL,
updated_at TIMESTAMP NOT NULL,
PRIMARY KEY (order_id),
FOREIGN KEY (email) REFERENCES users (email)
);

CREATE TABLE IF NOT EXISTS `garden`.`favorites` (
favorite_id INT NOT NULL AUTO_INCREMENT,
email VARCHAR(45) NOT NULL,
product_id INT NOT NULL,
PRIMARY KEY (favorite_id),
FOREIGN KEY (email) REFERENCES users (email),
FOREIGN KEY (product_id) REFERENCES products (product_id)
);

CREATE TABLE IF NOT EXISTS `garden`.`order_items` (
order_items_id INT NOT NULL AUTO_INCREMENT,
order_id INT NOT NULL,
product_id INT NOT NULL,
quantity INT NULL,
price_at_purchase DECIMAL(10,2) NOT NULL,
PRIMARY KEY (order_items_id),
INDEX idx_order_id (order_id),
INDEX idx_product_id (product_id),
FOREIGN KEY (order_id) REFERENCES orders (order_id),
FOREIGN KEY (product_id) REFERENCES products (product_id)
);
