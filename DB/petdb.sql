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
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `enabled` TINYINT(4) NOT NULL,
  `role` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

SET SQL_MODE = '';
DROP USER IF EXISTS pet@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'pet'@'localhost' IDENTIFIED BY 'pet';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'pet'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
<<<<<<< Updated upstream
=======
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip_code`) VALUES (1, '123 Main St', 'Anycity', 'Colorado', '23948');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip_code`) VALUES (2, '456 Main St', 'Fayetteville', 'North Carolina', '28311');
INSERT INTO `address` (`id`, `street`, `city`, `state`, `zip_code`) VALUES (3, '123 Owner st', 'Fayetteville', 'North Carolina', '28304');

COMMIT;


-- -----------------------------------------------------
-- Data for table `business`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `business` (`id`, `name`, `about`, `phone`, `image_url`, `address_id`) VALUES (1, 'fantastic pets', 'certified local vet', '5555555555', NULL, 2);

COMMIT;


-- -----------------------------------------------------
>>>>>>> Stashed changes
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
<<<<<<< Updated upstream
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'admin');
=======
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `phone`, `image_url`, `created_at`, `updated_at`, `business_id`, `address_id`) VALUES (1, 'admin', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'admin', 'admin@email.com', '123456789', NULL, NULL, NULL, NULL, 1);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `phone`, `image_url`, `created_at`, `updated_at`, `business_id`, `address_id`) VALUES (2, 'vet', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'vet', 'vet@email.com', '345234234', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `email`, `phone`, `image_url`, `created_at`, `updated_at`, `business_id`, `address_id`) VALUES (3, 'petowner', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'pawrent', 'owner@email.com', '3214568790', NULL, NULL, NULL, NULL, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet`
-- -----------------------------------------------------
START TRANSACTION;
USE `petdb`;
INSERT INTO `pet` (`id`, `name`, `species`, `breed`, `height`, `weight`, `gender`, `birth`, `color`, `about`, `microchipped`, `image_url`, `allergies`, `enabled`, `created_at`, `updated_at`, `owner_id`) VALUES (1, 'brandon', 'dog', 'chihuahua', 1, 6, 'male', '2018-12-21', 'brown', 'small dog', 0, NULL, 'none', 1, NULL, NULL, 3);
>>>>>>> Stashed changes

COMMIT;

