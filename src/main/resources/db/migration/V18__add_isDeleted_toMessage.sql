ALTER TABLE `message`
ADD COLUMN `is_deleted` BIT NOT NULL DEFAULT 0 AFTER `dialog_id`;

ALTER TABLE `dialog`
ADD COLUMN `unread_count` INT(11) NOT NULL DEFAULT 0 AFTER `owner_id`;