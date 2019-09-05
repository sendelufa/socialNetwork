ALTER TABLE `comment_like`
ADD INDEX `like_comment_fk_idx` (`comment_id` ASC) VISIBLE,
ADD INDEX `comment_like_person_fk_idx` (`person_id` ASC) VISIBLE;
;
ALTER TABLE `comment_like`
ADD CONSTRAINT `comment_like_comment_fk`
  FOREIGN KEY (`comment_id`)
  REFERENCES `post_comment` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `comment_like_person_fk`
  FOREIGN KEY (`person_id`)
  REFERENCES `person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;