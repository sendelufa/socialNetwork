CREATE TABLE `comment_like` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time` TIMESTAMP NOT NULL,
  `person_id` INT NOT NULL,
  `comment_id` INT NOT NULL,
  PRIMARY KEY (`id`));