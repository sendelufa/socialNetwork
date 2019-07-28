INSERT INTO `dialog` (`owner_id`, `invite_code`) VALUES ('1', '39X2DeD2C9LiJubkE6');
INSERT INTO `dialog` (`owner_id`, `invite_code`) VALUES ('2', 'MEp8ZBQ5higH4Xe2cN');
INSERT INTO `dialog` (`owner_id`, `invite_code`) VALUES ('1', 'd74VYdwhEY5QyZtFA3');
INSERT INTO `dialog` (`owner_id`, `invite_code`) VALUES ('3', '8MN869p4zJi6VMv2wP');
INSERT INTO `dialog` (`owner_id`, `invite_code`) VALUES ('4', 'sP4yt5s9p2747ZeiV8');

INSERT INTO `message` (`time`, `author_id`, `recipient_id`, `message_text`, `dialog_id`) VALUES ('2019-07-09 16:15:30', '1', '2', 'dialog message person 1 dialog 2', '2');
INSERT INTO `message` (`time`, `author_id`, `recipient_id`, `message_text`, `dialog_id`) VALUES ('2019-07-09 18:19:30', '2', '1', 'dialog message person 2 dialog 2', '2');
INSERT INTO `message` (`time`, `author_id`, `recipient_id`, `message_text`, `dialog_id`) VALUES ('2019-07-09 23:19:30', '3', '3', 'dialog message person 3 dialog 2', '2');
INSERT INTO `message` (`time`, `author_id`, `recipient_id`, `message_text`, `dialog_id`) VALUES ('2019-07-20 06:19:30', '1', '1', 'dialog message person 1 dialog 3', '3');
INSERT INTO `message` (`time`, `author_id`, `recipient_id`, `message_text`, `dialog_id`) VALUES ('2019-07-20 07:21:30', '2', '2', 'dialog message person 2 dialog 3', '3');

INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('1', '1');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('1', '2');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('1', '2');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('2', '2');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('1', '2');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('2', '3');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('1', '3');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('3', '4');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('1', '4');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('4', '5');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('1', '5');
INSERT INTO `person2dialog` (`person_id`, `dialog_id`) VALUES ('2', '5');
