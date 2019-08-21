ALTER TABLE `person`
ADD COLUMN `is_deleted` BIT(1) NOT NULL DEFAULT b'0' AFTER `is_online`;
