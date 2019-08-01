ALTER TABLE `dialog`
ADD COLUMN `is_deleted` BIT NOT NULL DEFAULT 0 AFTER `unread_count`;

TRUNCATE TABLE person2dialog;

ALTER TABLE `person2dialog`
ADD UNIQUE(person_id,dialog_id);

INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('1', '1');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('1', '2');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('2', '2');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('2', '3');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('1', '3');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('3', '4');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('1', '4');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('4', '5');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('1', '5');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('2', '5');