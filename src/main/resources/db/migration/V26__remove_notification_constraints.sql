SET FOREIGN_KEY_CHECKS=0;
ALTER TABLE `notification` DROP FOREIGN KEY `notification_entity_post_fk`;
ALTER TABLE `notification` DROP FOREIGN KEY `notification_entity_friend_fk`;
ALTER TABLE `notification` DROP FOREIGN KEY `notification_entity_comment_fk`;
ALTER TABLE `notification` DROP FOREIGN KEY `notification_entity_message_fk`;
ALTER TABLE `notification` DROP INDEX  `notification_entity_post_id_idx`;
SET FOREIGN_KEY_CHECKS=1;