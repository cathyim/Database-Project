-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ResultTracker
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ResultTracker
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ResultTracker` DEFAULT CHARACTER SET utf8 ;
USE `ResultTracker` ;

-- -----------------------------------------------------
-- Table `ResultTracker`.`players`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ResultTracker`.`players` (
  `player_id` INT NOT NULL,
  `tag` VARCHAR(45) NOT NULL,
  `real_name` VARCHAR(45) NOT NULL,
  `nationality` VARCHAR(2) NOT NULL,
  `birthday` DATE NOT NULL,
  `game_race` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`player_id`),
  UNIQUE INDEX `player_id_UNIQUE` (`player_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ResultTracker`.`teams`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ResultTracker`.`teams` (
  `team_id` INT NOT NULL,
  `team_name` VARCHAR(45) NOT NULL,
  `founded` DATE NOT NULL,
  `disbanded` DATE NULL,
  PRIMARY KEY (`team_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ResultTracker`.`tournaments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ResultTracker`.`tournaments` (
  `tournament_id` INT NOT NULL,
  `tournament_name` VARCHAR(45) NOT NULL,
  `region` VARCHAR(2) NOT NULL,
  `major` TINYINT(1) NOT NULL,
  PRIMARY KEY (`tournament_id`),
  UNIQUE INDEX `tournament_id_UNIQUE` (`tournament_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ResultTracker`.`matches`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ResultTracker`.`matches` (
  `match_id` INT NOT NULL,
  `match_date` DATE NOT NULL,
  `tournament_id` INT NOT NULL,
  `playerA_id` INT NOT NULL,
  `playerB_id` INT NOT NULL,
  `playerA_score` INT NOT NULL,
  `playerB_score` INT NOT NULL,
  `offline` TINYINT(1) NOT NULL,
  PRIMARY KEY (`match_id`),
  UNIQUE INDEX `match_id_UNIQUE` (`match_id` ASC) VISIBLE,
  INDEX `fk_matches_tournaments1_idx` (`tournament_id` ASC) VISIBLE,
  INDEX `fk_macthes_players2_idx` (`playerB_id` ASC) VISIBLE,
  INDEX `fk_matches_players1_idx` (`playerA_id` ASC) VISIBLE,
  CONSTRAINT `fk_matches_tournaments1`
    FOREIGN KEY (`tournament_id`)
    REFERENCES `ResultTracker`.`tournaments` (`tournament_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_matches_players1`
    FOREIGN KEY (`playerA_id`)
    REFERENCES `ResultTracker`.`players` (`player_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_macthes_players2`
    FOREIGN KEY (`playerB_id`)
    REFERENCES `ResultTracker`.`players` (`player_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ResultTracker`.`members`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ResultTracker`.`members` (
  `player_id` INT NOT NULL,
  `team_id` INT NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NULL,
  INDEX `fk_members_teams1_idx` (`team_id` ASC) VISIBLE,
  CONSTRAINT `fk_members_players`
    FOREIGN KEY (`player_id`)
    REFERENCES `ResultTracker`.`players` (`player_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_members_teams1`
    FOREIGN KEY (`team_id`)
    REFERENCES `ResultTracker`.`teams` (`team_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ResultTracker`.`earnings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ResultTracker`.`earnings` (
  `tournament_id` INT NOT NULL,
  `player_id` INT NOT NULL,
  `prize_money` INT NOT NULL,
  `position` INT NOT NULL,
  INDEX `fk_earnings_tournaments1_idx` (`tournament_id` ASC) VISIBLE,
  CONSTRAINT `fk_earnings_tournaments1`
    FOREIGN KEY (`tournament_id`)
    REFERENCES `ResultTracker`.`tournaments` (`tournament_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_earnings_players1`
    FOREIGN KEY (`player_id`)
    REFERENCES `ResultTracker`.`players` (`player_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
