CREATE SCHEMA IF NOT EXISTS `ePossaLocal`;
USE `ePossaLocal` ;

-- -----------------------------------------------------
-- Table `ePossaLocal`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ePossaLocal`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL,
  `name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `password` VARCHAR(300) NOT NULL,
  `device` VARCHAR(300) NOT NULL,
  `balance` DECIMAL(15,2) NULL,
  `rating` INT NULL DEFAULT 1,
  `status` ENUM('active', 'blocked') NOT NULL DEFAULT 'active',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ePossaLocal`.`transfer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ePossaLocal`.`transfer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NULL,
  `sender` VARCHAR(45) NOT NULL,
  `receiver` VARCHAR(45) NOT NULL,
  `amount` DECIMAL(15,2) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_transfer_sender_idx` (`sender` ASC),
  INDEX `fk_transfer_receiver_idx` (`receiver` ASC),
  CONSTRAINT `fk_transfer_sender`
    FOREIGN KEY (`sender`)
    REFERENCES `ePossaLocal`.`user` (`phone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transfer_receiver`
    FOREIGN KEY (`receiver`)
    REFERENCES `ePossaLocal`.`user` (`phone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
