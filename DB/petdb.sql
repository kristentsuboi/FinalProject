-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema petdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `petdb` ;

-- -----------------------------------------------------
-- Schema petdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `petdb` DEFAULT CHARACTER SET utf8 ;
USE `petdb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(200) NOT NULL,
  `city` VARCHAR(100) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `zip_code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `business`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `business` ;

CREATE TABLE IF NOT EXISTS `business` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `about` TEXT NULL,
  `phone` VARCHAR(100) NOT NULL,
  `image_url` VARCHAR(3000) NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_business_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_business_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `enabled` TINYINT(4) NOT NULL,
  `role` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(200) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `image_url` VARCHAR(3000) NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `business_id` INT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_business1_idx` (`business_id` ASC),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_business1`
    FOREIGN KEY (`business_id`)
    REFERENCES `business` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet` ;

CREATE TABLE IF NOT EXISTS `pet` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `species` VARCHAR(100) NOT NULL,
  `breed` VARCHAR(100) NULL DEFAULT NULL,
  `height` INT(11) NULL DEFAULT NULL,
  `weight` INT(11) NULL DEFAULT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `birth` DATE NULL DEFAULT NULL,
  `color` VARCHAR(45) NULL DEFAULT NULL,
  `about` TEXT NULL DEFAULT NULL,
  `microchipped` TINYINT NULL DEFAULT NULL,
  `image_url` VARCHAR(4000) NULL DEFAULT NULL,
  `allergies` VARCHAR(45) NULL,
  `enabled` TINYINT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `owner_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pet_user1_idx` (`owner_id` ASC),
  CONSTRAINT `fk_pet_user1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `medication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medication` ;

CREATE TABLE IF NOT EXISTS `medication` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `last_administered` DATE NULL DEFAULT NULL,
  `frequency` VARCHAR(100) NULL DEFAULT NULL,
  `with_food` TINYINT NULL,
  `date_started` DATE NULL,
  `notes` TEXT NULL,
  `pet_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_medication_pet1_idx` (`pet_id` ASC),
  CONSTRAINT `fk_medication_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `shot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `shot` ;

CREATE TABLE IF NOT EXISTS `shot` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `date_administered` DATE NULL,
  `frequency` VARCHAR(100) NULL,
  `notes` TEXT NULL,
  `pet_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_shot_pet1_idx` (`pet_id` ASC),
  CONSTRAINT `fk_shot_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet_comment` ;

CREATE TABLE IF NOT EXISTS `pet_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `body` TEXT NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `image_url` VARCHAR(3000) NULL,
  `pet_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `replying_to_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_pet2_idx` (`pet_id` ASC),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_comment_comment1_idx` (`replying_to_id` ASC),
  CONSTRAINT `fk_comment_pet2`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`replying_to_id`)
    REFERENCES `pet_comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `diet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `diet` ;

CREATE TABLE IF NOT EXISTS `diet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(300) NULL,
  `type` VARCHAR(300) NULL,
  `frequency` VARCHAR(45) NULL,
  `notes` TEXT NULL,
  `amount` VARCHAR(100) NULL,
  `pet_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_diet_pet2_idx` (`pet_id` ASC),
  CONSTRAINT `fk_diet_pet2`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_provider`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet_provider` ;

CREATE TABLE IF NOT EXISTS `pet_provider` (
  `provider_id` INT(11) NOT NULL,
  `pet_id` INT(11) NOT NULL,
  PRIMARY KEY (`provider_id`, `pet_id`),
  INDEX `fk_user_has_pet_pet1_idx` (`pet_id` ASC),
  INDEX `fk_user_has_pet_user1_idx` (`provider_id` ASC),
  CONSTRAINT `fk_user_has_pet_user1`
    FOREIGN KEY (`provider_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_pet_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `medical_note`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `medical_note` ;

CREATE TABLE IF NOT EXISTS `medical_note` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `notes` TEXT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `pet_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_medical_note_pet1_idx` (`pet_id` ASC),
  INDEX `fk_medical_note_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_medical_note_pet1`
    FOREIGN KEY (`pet_id`)
    REFERENCES `pet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medical_note_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `owner_uses_business`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `owner_uses_business` ;

CREATE TABLE IF NOT EXISTS `owner_uses_business` (
  `user_id` INT(11) NOT NULL,
  `business_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `business_id`),
  INDEX `fk_user_has_business_business1_idx` (`business_id` ASC),
  INDEX `fk_user_has_business_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_business_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_business_business1`
    FOREIGN KEY (`business_id`)
    REFERENCES `business` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `business_rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `business_rating` ;

CREATE TABLE IF NOT EXISTS `business_rating` (
  `user_id` INT(11) NOT NULL,
  `business_id` INT NOT NULL,
  `rating` INT NULL,
  `remark` TEXT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`user_id`, `business_id`),
  INDEX `fk_user_has_business_business2_idx` (`business_id` ASC),
  INDEX `fk_user_has_business_user2_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_business_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_business_business2`
    FOREIGN KEY (`business_id`)
    REFERENCES `business` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `body` TEXT NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  `image_url` VARCHAR(3000) NULL,
  `topic` VARCHAR(300) NULL,
  `user_id` INT(11) NOT NULL,
  `replying_to_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_comment_comment1_idx` (`replying_to_id` ASC),
  CONSTRAINT `fk_comment_user10`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_comment10`
    FOREIGN KEY (`replying_to_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `service_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `service_type` ;

CREATE TABLE IF NOT EXISTS `service_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(450) NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(3000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `service_provided_ by_business`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `service_provided_ by_business` ;

CREATE TABLE IF NOT EXISTS `service_provided_ by_business` (
  `service_type_id` INT NOT NULL,
  `business_id` INT NOT NULL,
  PRIMARY KEY (`service_type_id`, `business_id`),
  INDEX `fk_service_type_has_business_business1_idx` (`business_id` ASC),
  INDEX `fk_service_type_has_business_service_type1_idx` (`service_type_id` ASC),
  CONSTRAINT `fk_service_type_has_business_service_type1`
    FOREIGN KEY (`service_type_id`)
    REFERENCES `service_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_type_has_business_business1`
    FOREIGN KEY (`business_id`)
    REFERENCES `business` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS pet;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'pet' IDENTIFIED BY 'pet';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'pet';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip_code`) VALUES (1, '123 vet st', 'fayetteville', 'NC', '28311');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip_code`) VALUES (2, '321 owner st', 'fayetteville', 'NC', '28304');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip_code`) VALUES (3, '456 admin st', 'bloomsburg', 'PA', '17815');

COMMIT;


-- -----------------------------------------------------
-- Data for table `business`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `business` (`id`, `name`, `about`, `phone`, `image_url`, `address_id`) VALUES (1, 'fatastic pets', '24h vets', '1234325555', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `phone`, `image_url`, `created_at`, `updated_at`, `business_id`, `address_id`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'admin', 'admin@email.com', '123456789', NULL, NULL, NULL, NULL, 3);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `phone`, `image_url`, `created_at`, `updated_at`, `business_id`, `address_id`) VALUES (2, 'vet', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'vet', 'vet@email.com', '987643234', NULL, NULL, NULL, 1, 1);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `phone`, `image_url`, `created_at`, `updated_at`, `business_id`, `address_id`) VALUES (3, 'owner', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'pet owner', 'dogowner@email.com', '3456789876', NULL, NULL, NULL, NULL, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `pet` (`id`, `name`, `species`, `breed`, `height`, `weight`, `gender`, `birth`, `color`, `about`, `microchipped`, `image_url`, `allergies`, `enabled`, `created_at`, `updated_at`, `owner_id`) VALUES (1, 'brandon', 'dog', 'chihuahua', 1, 6, 'male', '2018-12-21', 'brown', 'cool dog', 0, NULL, 'n/a', 1, NULL, NULL, 3);

COMMIT;

