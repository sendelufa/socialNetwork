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

insert into `comment_like`(`id`,`time`,`person_id`, `comment_id`) values
('1','2018-12-06 08:35:15', '2','2'),
('2','2015-11-14 07:01:56', '1','8'),
('3','2013-08-02 11:55:01', '5','4'),
('4','2011-07-04 12:12:10', '3','5'),
('5','2018-05-01 17:59:22', '1','2'),
('6','2016-03-16 18:17:39', '10','3'),
('7','2017-04-17 23:31:47', '4','7'),
('8','2016-11-12 12:02:54', '8','1'),
('9','2013-11-14 04:12:12', '9','3'),
('10','2019-01-18 05:14:51', '4','8'),
('11','2014-07-15 03:07:02', '3','7'),
('12','2015-06-19 17:12:15', '5','6'),
('13','2019-08-15 22:22:15', '3','7');