ALTER TABLE `person`
CHANGE COLUMN `town` `city` VARCHAR(45) NULL DEFAULT NULL ;

CREATE TABLE `country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title_short` VARCHAR(32) NOT NULL,
  `title` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country_id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `city`
ADD INDEX `country_fk_idx` (`country_id` ASC) VISIBLE;

ALTER TABLE `city`
ADD CONSTRAINT `country_fk`
  FOREIGN KEY (`country_id`)
  REFERENCES `country` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `person`
CHANGE COLUMN `city` `city` INT NULL DEFAULT NULL ,
CHANGE COLUMN `country` `country` INT NULL DEFAULT NULL ;



