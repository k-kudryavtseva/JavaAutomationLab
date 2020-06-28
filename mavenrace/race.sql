-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema race
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema race
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `race` DEFAULT CHARACTER SET utf8 ;
USE `race` ;

-- -----------------------------------------------------
-- Table `race`.`engines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `race`.`engines` (
  `type` VARCHAR(45) NOT NULL,
  `cylinders` INT NULL,
  PRIMARY KEY (`type`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race`.`wheelTypes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `race`.`wheelTypes` (
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race`.`vehicles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `race`.`vehicles` (
  `v_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `engine_type` VARCHAR(45) NOT NULL,
  `wheel_type` VARCHAR(45) NOT NULL,
  `mass` FLOAT NOT NULL,
  PRIMARY KEY (`v_id`),
  INDEX `engineType_idx` (`engine_type` ASC) VISIBLE,
  INDEX `wheel_type_idx` (`wheel_type` ASC) VISIBLE,
  CONSTRAINT `engine_type`
    FOREIGN KEY (`engine_type`)
    REFERENCES `race`.`engines` (`type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `wheel_type`
    FOREIGN KEY (`wheel_type`)
    REFERENCES `race`.`wheelTypes` (`type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race`.`materials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `race`.`materials` (
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race`.`frictionCoefTypes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `race`.`frictionCoefTypes` (
  `wheel_type` VARCHAR(45) NULL,
  `material_name` VARCHAR(45) NULL,
  `friction_coef` FLOAT NOT NULL,
  INDEX `material_name_idx` (`material_name` ASC) VISIBLE,
  INDEX `wheel_type_idx` (`wheel_type` ASC) VISIBLE,
  CONSTRAINT `wheel_type`
    FOREIGN KEY (`wheel_type`)
    REFERENCES `race`.`wheelTypes` (`type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `material_name`
    FOREIGN KEY (`material_name`)
    REFERENCES `race`.`materials` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race`.`routeSections`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `race`.`routeSections` (
  `r_id` INT NOT NULL AUTO_INCREMENT,
  `material_name` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `r_id_UNIQUE` (`r_id` ASC) VISIBLE,
  PRIMARY KEY (`r_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race`.`routes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `race`.`routes` (
  `route_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`route_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race`.`routes_RouteSections`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `race`.`routes_RouteSections` (
  `route_id` INT NOT NULL,
  `routesection_id` INT NOT NULL,
  `routesection_index` INT NOT NULL,
  INDEX `route_id_idx` (`route_id` ASC) VISIBLE,
  INDEX `routesection_id_idx` (`routesection_id` ASC) VISIBLE,
  CONSTRAINT `route_id`
    FOREIGN KEY (`route_id`)
    REFERENCES `race`.`routes` (`route_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `routesection_id`
    FOREIGN KEY (`routesection_id`)
    REFERENCES `race`.`routeSections` (`r_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race`.`supervisors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `race`.`supervisors` (
  `supervisor_id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`supervisor_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race`.`races`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `race`.`races` (
  `race_id` INT NOT NULL,
  `route_id` INT NOT NULL,
  `supervisor_id` INT NOT NULL,
  INDEX `supervisor_id_idx` (`supervisor_id` ASC) VISIBLE,
  INDEX `route_id_idx` (`route_id` ASC) VISIBLE,
  PRIMARY KEY (`race_id`),
  CONSTRAINT `supervisor_id`
    FOREIGN KEY (`supervisor_id`)
    REFERENCES `race`.`supervisors` (`supervisor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `route_id`
    FOREIGN KEY (`route_id`)
    REFERENCES `race`.`routes` (`route_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race`.`raceProgress`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `race`.`raceProgress` (
  `race_id` INT NOT NULL,
  `vehicle_id` INT NOT NULL,
  `tick` FLOAT NULL,
  `distance` FLOAT NULL,
  INDEX `race_id_idx` (`race_id` ASC) VISIBLE,
  INDEX `vehicle_id_idx` (`vehicle_id` ASC) VISIBLE,
  CONSTRAINT `race_id`
    FOREIGN KEY (`race_id`)
    REFERENCES `race`.`races` (`race_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `vehicle_id`
    FOREIGN KEY (`vehicle_id`)
    REFERENCES `race`.`vehicles` (`v_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `race`.`races_vehicles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `race`.`races_vehicles` (
  `race_id` INT NOT NULL,
  `vehicle_id` INT NOT NULL,
  INDEX `race_id_idx` (`race_id` ASC) VISIBLE,
  INDEX `vehicle_id_idx` (`vehicle_id` ASC) VISIBLE,
  CONSTRAINT `race_id`
    FOREIGN KEY (`race_id`)
    REFERENCES `race`.`races` (`race_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `vehicle_id`
    FOREIGN KEY (`vehicle_id`)
    REFERENCES `race`.`vehicles` (`v_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
