CREATE TABLE `person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NULL,
  `reg_date` DATE NOT NULL,
  `birth_date` DATE NULL,
  `e_mail` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `photo` VARCHAR(2048) NULL,
  `about` VARCHAR(2048) NULL,
  `town` VARCHAR(45) NULL,
  `confirmation_code` VARCHAR(45) NULL,
  `is_approved` BIT NOT NULL DEFAULT 0,
  `messages_permission` ENUM('ALL', 'FRIENDS') NOT NULL DEFAULT 'ALL',
  `last_online_time` TIMESTAMP NULL,
  `is_blocked` BIT NOT NULL DEFAULT 0,
   PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE);

CREATE TABLE `block_history`(
  `id` INT NOT NULL AUTO_INCREMENT,
  `time` TIMESTAMP NOT NULL,
  `person_id` INT NOT NULL,
  `post_id` INT NULL,
  `comment_id` INT NULL,
  `action` ENUM('BLOCK', 'UNBLOCK') NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `e_mail` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `type` ENUM('MODERATOR', 'ADMIN') NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `e_mail_UNIQUE` (`e_mail` ASC) VISIBLE);

  CREATE TABLE `friendship` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status_id` INT NOT NULL,
  `src_person_id` INT NOT NULL,
  `dst_person_id` INT NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `friendship_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time` TIMESTAMP NULL,
  `name` VARCHAR(255) NULL,
  `code` ENUM('REQUEST', 'FRIEND', 'BLOCKED', 'DECLINED', 'SUBSCRIBED') NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time` TIMESTAMP NOT NULL,
  `author_id` INT NOT NULL,
  `recipient_id` INT NOT NULL,
  `message_text` VARCHAR(2048) NULL,
  `read_status` ENUM('SENT', 'READ') NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time` TIMESTAMP NOT NULL,
  `author_id` INT NOT NULL,
  `title` VARCHAR(255) NULL,
  `post_text` VARCHAR(4096) NOT NULL,
  `is_blocked` BIT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

CREATE TABLE `tag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tag` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `tag_UNIQUE` (`tag` ASC) VISIBLE);

CREATE TABLE `post2tag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `post_id` INT NOT NULL,
  `tag_id` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `post_like` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time` TIMESTAMP NOT NULL,
  `person_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `post_file` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `post_id` INT NOT NULL,
  `name` VARCHAR(255) NULL,
  `path` VARCHAR(2048) NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `post_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `time` TIMESTAMP NOT NULL,
  `post_id` INT NOT NULL,
  `parent_id` INT NULL,
  `author_id` INT NOT NULL,
  `comment_text` VARCHAR(2048) NULL,
  `is_blocked` BIT NULL DEFAULT 0,
  PRIMARY KEY (`id`));

  CREATE TABLE `notification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type_id` INT NOT NULL,
  `sent_time` TIMESTAMP NOT NULL,
  `person_id` INT NOT NULL,
  `entity_id` INT NOT NULL,
  `contact` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `notification_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` INT NOT NULL,
  `name` ENUM('POST', 'POST_COMMENT', 'COMMENT_COMMENT', 'FRIEND_REQUEST', 'MESSAGE') NOT NULL,
  PRIMARY KEY (`id`));