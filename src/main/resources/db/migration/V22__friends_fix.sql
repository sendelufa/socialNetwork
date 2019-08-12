ALTER TABLE `friendship_status`
DROP FOREIGN KEY `frienship_id_fk`;

ALTER TABLE `friendship`
ADD COLUMN `code` ENUM('REQUEST', 'FRIEND', 'BLOCKED', 'DECLINED', 'SUBSCRIBED') NOT NULL AFTER `dst_person_id`;

DROP TABLE `friendship_status`;

ALTER TABLE `friendship`
DROP COLUMN `status_id`;