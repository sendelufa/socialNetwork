CREATE TABLE `person2dialog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT(11) NOT NULL,
  `dialog_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `dialog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `owner_id` INT NULL,
  `invite_code` VARCHAR(255) NULL,
  UNIQUE INDEX `invite_code_UNIQUE` (`invite_code` ASC) VISIBLE,
  PRIMARY KEY (`id`));

ALTER TABLE `message`
ADD COLUMN `dialog_id` INT(11) NULL AFTER `read_status`;

ALTER TABLE `person2dialog`
ADD INDEX `dialog_fk_idx` (`dialog_id` ASC) VISIBLE,
ADD INDEX `person_fk_idx` (`person_id` ASC) VISIBLE;
;
ALTER TABLE `person2dialog`
ADD CONSTRAINT `person_fk`
  FOREIGN KEY (`person_id`)
  REFERENCES `person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `dialog_fk`
  FOREIGN KEY (`dialog_id`)
  REFERENCES `dialog` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
