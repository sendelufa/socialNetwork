CREATE TABLE `notification_settings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `person_id` INT NOT NULL,
  `notification_type_id` INT NOT NULL,
  `enable` BIT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));
