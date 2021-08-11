DROP database IF EXISTS `pricemonitoring`;
-- -----------------------------------------------------
-- Schema pricemonitoring
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pricemonitoring` DEFAULT CHARACTER SET utf8 ;
USE `pricemonitoring` ;

-- -----------------------------------------------------
-- Table `pricemonitoring`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pricemonitoring`.`addresses` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `city` VARCHAR(20) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `building` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pricemonitoring`.`sections`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pricemonitoring`.`sections` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pricemonitoring`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pricemonitoring`.`categories` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `sections_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_categories_sections1_idx` (`sections_id` ASC) VISIBLE,
  CONSTRAINT `fk_categories_sections1`
    FOREIGN KEY (`sections_id`)
    REFERENCES `pricemonitoring`.`sections` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pricemonitoring`.`units`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pricemonitoring`.`units` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pricemonitoring`.`origin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pricemonitoring`.`origin` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pricemonitoring`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pricemonitoring`.`products` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `units_id` BIGINT NOT NULL,
  `origin_id` BIGINT NOT NULL,
  `categories_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_products_units1_idx` (`units_id` ASC) VISIBLE,
  INDEX `fk_products_origin1_idx` (`origin_id` ASC) VISIBLE,
  INDEX `fk_products_categories1_idx` (`categories_id` ASC) VISIBLE,
  CONSTRAINT `fk_products_units1`
    FOREIGN KEY (`units_id`)
    REFERENCES `pricemonitoring`.`units` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_products_origin1`
    FOREIGN KEY (`origin_id`)
    REFERENCES `pricemonitoring`.`origin` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_products_categories1`
    FOREIGN KEY (`categories_id`)
    REFERENCES `pricemonitoring`.`categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pricemonitoring`.`stores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pricemonitoring`.`stores` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL,
  `addresses_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tradestores_addresses1_idx` (`addresses_id` ASC) VISIBLE,
  CONSTRAINT `fk_tradestores_addresses1`
    FOREIGN KEY (`addresses_id`)
    REFERENCES `pricemonitoring`.`addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pricemonitoring`.`assortment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pricemonitoring`.`assortment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NOT NULL,
  `update_date` BIGINT NOT NULL,
  `products_id` BIGINT NOT NULL,
  `stores_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_productlines_products1_idx` (`products_id` ASC) VISIBLE,
  INDEX `fk_assortment_stores1_idx` (`stores_id` ASC) VISIBLE,
  CONSTRAINT `fk_productlines_products1`
    FOREIGN KEY (`products_id`)
    REFERENCES `pricemonitoring`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_assortment_stores1`
    FOREIGN KEY (`stores_id`)
    REFERENCES `pricemonitoring`.`stores` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pricemonitoring`.`histories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pricemonitoring`.`histories` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `price_date` BIGINT NOT NULL,
  `price` DOUBLE NOT NULL,
  `products_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_histories_products1_idx` (`products_id` ASC) VISIBLE,
  CONSTRAINT `fk_histories_products1`
    FOREIGN KEY (`products_id`)
    REFERENCES `pricemonitoring`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pricemonitoring`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pricemonitoring`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pricemonitoring`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pricemonitoring`.`roles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pricemonitoring`.`users_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pricemonitoring`.`users_roles` (
  `users_id` BIGINT NOT NULL,
  `roles_id` BIGINT NOT NULL,
  PRIMARY KEY (`users_id`, `roles_id`),
  INDEX `fk_users_has_roles_roles1_idx` (`roles_id` ASC) VISIBLE,
  INDEX `fk_users_has_roles_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_roles_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `pricemonitoring`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_roles_roles1`
    FOREIGN KEY (`roles_id`)
    REFERENCES `pricemonitoring`.`roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
