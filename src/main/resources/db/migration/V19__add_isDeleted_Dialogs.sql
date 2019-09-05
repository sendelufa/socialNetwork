ALTER TABLE `dialog`
ADD COLUMN `is_deleted` BIT NOT NULL DEFAULT 0 AFTER `unread_count`;

TRUNCATE TABLE person2dialog;

ALTER TABLE `person2dialog`
ADD UNIQUE(person_id,dialog_id);