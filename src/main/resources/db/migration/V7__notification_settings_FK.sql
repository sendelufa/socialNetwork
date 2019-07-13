ALTER TABLE `notification_settings`
ADD INDEX `notification_settings_person_fk_idx` (`person_id` ASC) VISIBLE,
ADD INDEX `notification_settings_type_fk_idx` (`notification_type_id` ASC) VISIBLE;
;
ALTER TABLE `notification_settings`
ADD CONSTRAINT `notification_settings_person_fk`
  FOREIGN KEY (`person_id`)
  REFERENCES `person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `notification_settings_type_fk`
  FOREIGN KEY (`notification_type_id`)
  REFERENCES `notification_type` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
