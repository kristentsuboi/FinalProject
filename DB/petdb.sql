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
  `address_id` INT NULL,
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
  `address_id` INT NULL,
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
-- Table `service_provided_by_business`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `service_provided_by_business` ;

CREATE TABLE IF NOT EXISTS `service_provided_by_business` (
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
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip_code`) VALUES (4, '123 test address', 'test city', 'NC', '17815');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip_code`) VALUES (5, '123 grooming st', 'grooming city', 'NC', '28306');

COMMIT;


-- -----------------------------------------------------
-- Data for table `business`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `business` (`id`, `name`, `about`, `phone`, `image_url`, `address_id`) VALUES (1, 'fantastic pets', '24h vets', '1234325555', NULL, 1);
INSERT INTO `business` (`id`, `name`, `about`, `phone`, `image_url`, `address_id`) VALUES (2, 'beautiful pet grooming service', 'top-notch grooming service', '4325678485', NULL, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `phone`, `image_url`, `created_at`, `updated_at`, `business_id`, `address_id`) VALUES (1, 'admin', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 1, 'admin', 'admin@email.com', '123456789', NULL, NULL, NULL, NULL, 3);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `phone`, `image_url`, `created_at`, `updated_at`, `business_id`, `address_id`) VALUES (2, 'vet', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 1, 'vet', 'vet@email.com', '987643234', NULL, NULL, NULL, 1, 1);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `phone`, `image_url`, `created_at`, `updated_at`, `business_id`, `address_id`) VALUES (3, 'owner', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 1, 'pet owner', 'dogowner@email.com', '3456789876', NULL, NULL, NULL, NULL, 2);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `phone`, `image_url`, `created_at`, `updated_at`, `business_id`, `address_id`) VALUES (4, 'test', '$2a$10$4SMKDcs9jT18dbFxqtIqDeLEynC7MUrCEUbv1a/bhO.x9an9WGPvm', 1, 'test', 'test@test.com', '123456789', NULL, NULL, NULL, NULL, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `pet` (`id`, `name`, `species`, `breed`, `height`, `weight`, `gender`, `birth`, `color`, `about`, `microchipped`, `image_url`, `allergies`, `enabled`, `created_at`, `updated_at`, `owner_id`) VALUES (1, 'brandon', 'dog', 'chihuahua', 1, 6, 'male', '2018-12-21', 'brown', 'cool dog', 0, NULL, 'n/a', 1, NULL, NULL, 3);
INSERT INTO `pet` (`id`, `name`, `species`, `breed`, `height`, `weight`, `gender`, `birth`, `color`, `about`, `microchipped`, `image_url`, `allergies`, `enabled`, `created_at`, `updated_at`, `owner_id`) VALUES (2, 'deletepet', 'dog', 'noexist', 5, 7, 'non-binary', '2018-12-21', 'white', 'cool dog', 1, NULL, 'none', 1, NULL, NULL, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `medication`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `medication` (`id`, `name`, `last_administered`, `frequency`, `with_food`, `date_started`, `notes`, `pet_id`) VALUES (1, 'tooth paste', '2020-11-11', 'daily', 0, '2020-11-11', 'brush his teeth', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `shot`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `shot` (`id`, `name`, `date_administered`, `frequency`, `notes`, `pet_id`) VALUES (1, 'rabies', '2022-10-10', 'once a year', 'all good', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `pet_comment` (`id`, `body`, `created_at`, `updated_at`, `image_url`, `pet_id`, `user_id`, `replying_to_id`) VALUES (1, 'healthy dog', '2020-11-11', '2020-12-21', NULL, 1, 2, NULL);
INSERT INTO `pet_comment` (`id`, `body`, `created_at`, `updated_at`, `image_url`, `pet_id`, `user_id`, `replying_to_id`) VALUES (2, 'it looks pretty healthy', '2020-11-12', '2020-11-13', NULL, 1, 3, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `diet`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `diet` (`id`, `name`, `type`, `frequency`, `notes`, `amount`, `pet_id`) VALUES (1, 'keto diet', 'lose weight', 'daily', 'summer pack', '3 times a day', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet_provider`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `pet_provider` (`provider_id`, `pet_id`) VALUES (2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `medical_note`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `medical_note` (`id`, `notes`, `created_at`, `updated_at`, `pet_id`, `user_id`) VALUES (1, 'call the other doctor', '2020-11-11', NULL, 1, 2);
INSERT INTO `medical_note` (`id`, `notes`, `created_at`, `updated_at`, `pet_id`, `user_id`) VALUES (2, 'test this medical note', '2020-11-11', NULL, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `owner_uses_business`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `owner_uses_business` (`user_id`, `business_id`) VALUES (3, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `business_rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `business_rating` (`user_id`, `business_id`, `rating`, `remark`, `created_at`, `updated_at`) VALUES (3, 1, 5, 'excellent service', '2022-11-11', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `comment` (`id`, `body`, `created_at`, `updated_at`, `image_url`, `topic`, `user_id`, `replying_to_id`) VALUES (1, 'Can i sleep with a dog with rabies?', '2023-06-11', NULL, NULL, 'general', 3, NULL);
INSERT INTO `comment` (`id`, `body`, `created_at`, `updated_at`, `image_url`, `topic`, `user_id`, `replying_to_id`) VALUES (2, 'what do you wanna sleep with you dog?', '2023-06-12', NULL, NULL, 'general', 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `service_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `service_type` (`id`, `name`, `description`, `image_url`) VALUES (1, 'Veterinarians ', 'we take care of your dog', NULL);
INSERT INTO `service_type` (`id`, `name`, `description`, `image_url`) VALUES (2, 'Grooming', 'we make your dog looks beautiful', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `service_provided_by_business`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `service_provided_by_business` (`service_type_id`, `business_id`) VALUES (1, 1);
INSERT INTO `service_provided_by_business` (`service_type_id`, `business_id`) VALUES (2, 2);

COMMIT;

