
ALTER TABLE `person`
CHANGE COLUMN `city` `city` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `country` `country` VARCHAR(45) NULL DEFAULT NULL ;

  UPDATE person
  SET city = "Москва", country = "Россия" WHERE id <= 10;
  
  DROP TABLE `city`;
  DROP TABLE `country`;



