CREATE TABLE `country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `short_name` VARCHAR(32) NOT NULL,
  `title` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `town` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country_id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `town`
ADD INDEX `country_fk_idx` (`country_id` ASC) VISIBLE;

ALTER TABLE `town`
ADD CONSTRAINT `country_fk`
  FOREIGN KEY (`country_id`)
  REFERENCES `country` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

UPDATE person
SET town = NULL;

ALTER TABLE `person`
CHANGE COLUMN `town` `town` INT NULL DEFAULT NULL ,
CHANGE COLUMN `country` `country` INT NULL DEFAULT NULL ;

INSERT INTO `country` (`short_name`, `title`) VALUES ('RU', 'Россия');
INSERT INTO `town` (`country_id`, `title`) VALUES ('1', 'Москва');

  UPDATE social_network.person
  SET town = 1, country = 1 WHERE id < 5;



