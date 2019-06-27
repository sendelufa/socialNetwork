/* Foreign keys setup */

ALTER TABLE `block_history`
ADD INDEX `block_history_comment_fk_idx` (`comment_id` ASC) VISIBLE,
ADD INDEX `block_history_person_fk_idx` (`person_id` ASC) VISIBLE,
ADD INDEX `block_history_post_fk_idx` (`post_id` ASC) VISIBLE;
;
ALTER TABLE `block_history`
ADD CONSTRAINT `block_history_comment_fk`
  FOREIGN KEY (`comment_id`)
  REFERENCES `post_comment` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
ADD CONSTRAINT `block_history_person_fk`
  FOREIGN KEY (`person_id`)
  REFERENCES `person` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
ADD CONSTRAINT `block_history_post_fk`
  FOREIGN KEY (`post_id`)
  REFERENCES `post` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;


ALTER TABLE `friendship`
ADD INDEX `src_person_fk_idx` (`src_person_id` ASC) VISIBLE,
ADD INDEX `dst_person_fk_idx` (`dst_person_id` ASC) VISIBLE;
;
ALTER TABLE `friendship`
ADD CONSTRAINT `src_person_fk`
  FOREIGN KEY (`src_person_id`)
  REFERENCES `person` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
ADD CONSTRAINT `dst_person_fk`
  FOREIGN KEY (`dst_person_id`)
  REFERENCES `person` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;


ALTER TABLE `friendship_status`
ADD CONSTRAINT `frienship_id_fk`
  FOREIGN KEY (`id`)
  REFERENCES `friendship` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `message`
ADD INDEX `author_person_fk_idx` (`author_id` ASC) VISIBLE,
ADD INDEX `recipient_person_fk_idx` (`recipient_id` ASC) VISIBLE;
;
ALTER TABLE `message`
ADD CONSTRAINT `author_person_fk`
  FOREIGN KEY (`author_id`)
  REFERENCES `person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `recipient_person_fk`
  FOREIGN KEY (`recipient_id`)
  REFERENCES `person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `post`
ADD INDEX `authorpost_person_fk_idx` (`author_id` ASC) VISIBLE;
;
ALTER TABLE `post`
ADD CONSTRAINT `authorpost_person_fk`
  FOREIGN KEY (`author_id`)
  REFERENCES `person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  ALTER TABLE `post2tag`
ADD INDEX `posttag_post_fk_idx` (`post_id` ASC) VISIBLE,
ADD INDEX `posttag_tag_fk_idx` (`tag_id` ASC) VISIBLE;
;
ALTER TABLE `post2tag`
ADD CONSTRAINT `posttag_post_fk`
  FOREIGN KEY (`post_id`)
  REFERENCES `post` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `posttag_tag_fk`
  FOREIGN KEY (`tag_id`)
  REFERENCES `tag` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `post_comment`
ADD INDEX `comment_post_fk_idx` (`post_id` ASC) VISIBLE,
ADD INDEX `comment_parent_fk_idx` (`parent_id` ASC) VISIBLE,
ADD INDEX `comment_person_fk_idx` (`author_id` ASC) VISIBLE;
;
ALTER TABLE `post_comment`
ADD CONSTRAINT `comment_post_fk`
  FOREIGN KEY (`post_id`)
  REFERENCES `post` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `comment_parent_fk`
  FOREIGN KEY (`parent_id`)
  REFERENCES `post_comment` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `comment_person_fk`
  FOREIGN KEY (`author_id`)
  REFERENCES `person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `post_file`
ADD INDEX `file_post_fk_idx` (`post_id` ASC) VISIBLE;
;
ALTER TABLE `post_file`
ADD CONSTRAINT `file_post_fk`
  FOREIGN KEY (`post_id`)
  REFERENCES `post` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `post_like`
ADD INDEX `like_post_fk_idx` (`post_id` ASC) VISIBLE,
ADD INDEX `like_person_fk_idx` (`person_id` ASC) VISIBLE;
;
ALTER TABLE `post_like`
ADD CONSTRAINT `like_post_fk`
  FOREIGN KEY (`post_id`)
  REFERENCES `post` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `like_person_fk`
  FOREIGN KEY (`person_id`)
  REFERENCES `person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `notification`
ADD INDEX `notification_type_fk_idx` (`type_id` ASC) VISIBLE,
ADD INDEX `notification_person_fk_idx` (`person_id` ASC) VISIBLE,
ADD INDEX `notification_entity_post_id_idx` (`entity_id` ASC) VISIBLE;
;
ALTER TABLE `notification`
ADD CONSTRAINT `notification_type_fk`
  FOREIGN KEY (`type_id`)
  REFERENCES `notification_type` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `notification_person_fk`
  FOREIGN KEY (`person_id`)
  REFERENCES `person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `notification_entity_post_fk`
  FOREIGN KEY (`entity_id`)
  REFERENCES `post` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `notification_entity_friend_fk`
  FOREIGN KEY (`entity_id`)
  REFERENCES `friendship` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `notification_entity_comment_fk`
  FOREIGN KEY (`entity_id`)
  REFERENCES `post_comment` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `notification_entity_message_fk`
  FOREIGN KEY (`entity_id`)
  REFERENCES `message` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `notification_settings`
ADD INDEX `notification_settings_person_fk_idx` (`person_id` ASC) VISIBLE,
ADD INDEX `notification_settings_type_fk_idx` (`notification_type_id` ASC) VISIBLE;
;
ALTER TABLE `notification_settings`
ADD CONSTRAINT `notification_settings_person_fk`
  FOREIGN KEY (`person_id`)
  REFERENCES `person` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
ADD CONSTRAINT `notification_settings_type_fk`
  FOREIGN KEY (`notification_type_id`)
  REFERENCES `notification_type` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;