-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: social_network
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `block_history`
--

LOCK TABLES `block_history` WRITE;
/*!40000 ALTER TABLE `block_history` DISABLE KEYS */;
INSERT INTO `block_history` VALUES (1,'2019-05-15 08:12:14',4,1,1,'UNBLOCK'),(2,'2016-01-10 13:16:33',2,1,2,'UNBLOCK'),(3,'2017-05-12 09:12:51',10,2,3,'UNBLOCK'),(4,'2017-10-10 00:00:42',2,4,4,'UNBLOCK'),(5,'2017-11-10 00:05:52',3,4,5,'UNBLOCK'),(6,'2018-08-08 12:17:10',2,4,6,'UNBLOCK'),(7,'2019-01-20 21:42:48',3,9,7,'UNBLOCK'),(8,'2019-02-25 03:32:16',4,9,8,'UNBLOCK'),(9,'2019-04-14 05:57:14',3,9,9,'UNBLOCK'),(10,'2019-06-12 08:50:30',4,9,10,'UNBLOCK');
/*!40000 ALTER TABLE `block_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comment_like`
--

LOCK TABLES `comment_like` WRITE;
/*!40000 ALTER TABLE `comment_like` DISABLE KEYS */;
INSERT INTO `comment_like` VALUES (1,'2018-12-06 03:35:15',2,2),(2,'2015-11-14 02:01:56',1,8),(3,'2013-08-02 06:55:01',5,4),(4,'2011-07-04 07:12:10',3,5),(5,'2018-05-01 12:59:22',1,2),(6,'2016-03-16 13:17:39',10,3),(7,'2017-04-17 18:31:47',4,7),(8,'2016-11-12 07:02:54',8,1),(9,'2013-11-13 23:12:12',9,3),(10,'2019-01-18 00:14:51',4,8),(11,'2014-07-14 22:07:02',3,7),(12,'2015-06-19 12:12:15',5,6),(13,'2019-08-15 17:22:15',3,7);
/*!40000 ALTER TABLE `comment_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dialog`
--

LOCK TABLES `dialog` WRITE;
/*!40000 ALTER TABLE `dialog` DISABLE KEYS */;
INSERT INTO `dialog` VALUES (1,1,0,_binary '\0','39X2DeD2C9LiJubkE6'),(2,2,0,_binary '\0','MEp8ZBQ5higH4Xe2cN'),(3,1,0,_binary '\0','d74VYdwhEY5QyZtFA3'),(4,3,0,_binary '\0','8MN869p4zJi6VMv2wP'),(5,4,0,_binary '\0','sP4yt5s9p2747ZeiV8');
/*!40000 ALTER TABLE `dialog` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Dumping data for table `friendship`
--

LOCK TABLES `friendship` WRITE;
/*!40000 ALTER TABLE `friendship` DISABLE KEYS */;
INSERT INTO `friendship` VALUES (1,1,2,'FRIEND'),(2,2,1,'FRIEND'),(3,4,5,'SUBSCRIBED'),(4,5,4,'REQUEST'),(5,7,1,'DECLINED'),(6,1,7,'SUBSCRIBED'),(7,2,4,'FRIEND'),(8,4,2,'FRIEND'),(9,2,9,'FRIEND'),(10,9,2,'FRIEND');
/*!40000 ALTER TABLE `friendship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'2019-04-14 12:50:30',1,3,'привет, пойдем гулять','READ',NULL,_binary '\0'),(2,'2018-02-19 08:01:10',2,9,'привет, СДЕЛАЛ ДОМАШКУ','READ',NULL,_binary '\0'),(3,'2018-12-06 03:35:15',3,5,'пока','READ',NULL,_binary '\0'),(4,'2019-05-17 12:12:10',7,5,'привет, познакомимся','READ',NULL,_binary '\0'),(5,'2019-05-17 13:47:33',5,7,'привет, НЕТ','SENT',NULL,_binary '\0'),(6,'2019-07-28 18:55:30',10,1,'удачи','READ',NULL,_binary '\0'),(7,'2019-09-24 06:57:30',8,5,'привет, до завтра','SENT',NULL,_binary '\0'),(8,'2019-10-16 23:14:30',5,4,'зимой поеду домой','READ',NULL,_binary '\0'),(9,'2019-04-14 13:22:39',3,1,'через час дождь','SENT',NULL,_binary '\0'),(10,'2019-06-09 11:15:30',8,9,'конференция через 3 месяца','SENT',NULL,_binary '\0'),(11,'2019-07-09 11:15:30',1,2,'dialog message person 1 dialog 2','SENT',2,_binary '\0'),(12,'2019-07-09 13:19:30',2,1,'dialog message person 2 dialog 2','SENT',2,_binary '\0'),(13,'2019-07-09 18:19:30',3,3,'dialog message person 3 dialog 2','SENT',2,_binary '\0'),(14,'2019-07-20 01:19:30',1,1,'dialog message person 1 dialog 3','SENT',3,_binary '\0'),(15,'2019-07-20 02:21:30',2,2,'dialog message person 2 dialog 3','SENT',3,_binary '\0');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,2,'2018-12-06 06:00:15',2,1,'sidorovmaxim@mail.ru',_binary '\0'),(2,2,'2018-12-06 10:40:15',4,1,'roman1991@mail.ru',_binary '\0'),(3,5,'2019-04-14 12:50:30',3,1,'mihailovsergei@mail.ru',_binary '\0'),(4,5,'2019-04-14 13:22:39',1,9,'ivaniavanov@mail.ru',_binary '\0'),(5,4,'2019-06-12 08:50:30',3,4,'nasty1998@mail.ru',_binary '\0'),(6,1,'2018-12-06 03:35:15',8,9,'roman1991@mail.ru',_binary '\0'),(7,2,'2019-03-02 12:11:00',2,4,'dumova1995@mail.ru',_binary '\0'),(8,3,'2019-03-02 13:51:00',5,5,'sidorovmaxim@mail.ru',_binary '\0'),(9,3,'2019-03-02 13:54:00',6,3,'mihailovsergei@mail.ru',_binary '\0'),(10,2,'2018-12-28 16:10:15',10,3,'roman1991@mail.ru',_binary '\0');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notification_settings`
--

LOCK TABLES `notification_settings` WRITE;
/*!40000 ALTER TABLE `notification_settings` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification_settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `notification_type`
--

LOCK TABLES `notification_type` WRITE;
/*!40000 ALTER TABLE `notification_type` DISABLE KEYS */;
INSERT INTO `notification_type` VALUES (1,101,'FRIEND_BIRTHDAY'),(2,102,'POST_COMMENT'),(3,103,'COMMENT_COMMENT'),(4,104,'FRIEND_REQUEST'),(5,105,'MESSAGE');
/*!40000 ALTER TABLE `notification_type` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Иванов','Иван','2015-01-21','1963-03-25','ivaniavanov@mail.ru','79234567895','$2a$10$BF8z1SVci201H8NgBPccAOAZyn1xb4rDLaO4uw6gQdoA/2B6PkOPi',NULL,'good man','Москва','Россия','',_binary '\0','ALL','2013-10-08 11:07:28',_binary '\0',_binary '\0',_binary '\0'),(2,'Сидоров','Максим','2010-07-22','1985-05-14','sidorovmaxim@mail.ru','79856321456','$2a$10$rYdERqNj9jEyYMpuPPxTb.0Y7S3bUNFBSgr4tyPAeBFICWTje4m6O',NULL,'good man','Москва','Россия','',_binary '\0','ALL','2019-06-08 05:09:29',_binary '\0',_binary '\0',_binary '\0'),(3,'Михайлов','Сергей','2012-07-01','1990-05-19','mihailovsergei@mail.ru','79135546932','$2a$10$CkOdN9ky5yvWSpU6BluVQOGWYLyaODHLA3hmE8.mIedVTXea3pv0q',NULL,'funny man','Москва','Россия','',_binary '\0','FRIENDS','2019-06-15 10:07:28',_binary '\0',_binary '\0',_binary '\0'),(4,'Твердохлебов','Роман','2005-05-05','1991-06-12','roman1991@mail.ru','79423652145','$2a$10$0RRyhLUpk7BQD0STA/gj1OeBIpuzjhq7EAqbNhudNsGtUbkz0VpXO',NULL,'strong man','Москва','Россия','',_binary '\0','FRIENDS','2019-06-16 11:08:20',_binary '\0',_binary '\0',_binary '\0'),(5,'Быкова','Анастасия','2013-12-02','1998-01-17','nasty1998@mail.ru','79456328975','$2a$10$dbpSQRrS3jrvnR6MIgljbeatY/w3bg7w8iKNl5Vj9kijQv.Xl2oVG',NULL,'nice girl','Москва','Россия','',_binary '\0','FRIENDS','2019-05-15 00:07:12',_binary '\0',_binary '\0',_binary '\0'),(6,'Ефремова','Алена','2015-08-08','1987-06-06','efremovaalena@mail.ru','79238856712','$2a$10$g5BfEUH3x6XZzrkchuPXj.IRBpr8xYJUfqg1fLfAfaGJORsiNhIJG',NULL,'happy woman','Москва','Россия','',_binary '\0','FRIENDS','2019-06-16 07:09:28',_binary '\0',_binary '\0',_binary '\0'),(7,'Губкин','Иван','2012-01-09','1993-03-15','ivangubkin@mail.ru','79452548235','$2a$10$tXxSg6XUY580zsIVxv9oI.wT30kEa2.6xeydPS3I4xOGRtXTbuIIe',NULL,'busy man','Москва','Россия','',_binary '\0','ALL','2019-06-15 16:01:28',_binary '\0',_binary '\0',_binary '\0'),(8,'Никифоров','Никита','2019-01-08','2005-09-09','nikita2005@mail.ru','79234435614','$2a$10$EPaLUZhQmBXSKXUJHIFq4ub5gjni5d.HcS0i.0FvOyNDpvHS4VP4i',NULL,'little man','Москва','Россия','',_binary '\0','ALL','2019-06-15 17:57:28',_binary '\0',_binary '\0',_binary '\0'),(9,'Васов','Вадим','2018-07-07','2003-01-05','vadikvasov@mail.ru','79839541265','$2a$10$eqF2r1j2LsCJiXpwffN.juUCSPTdX6m1guBb3RShm./mQWYjRSYri',NULL,'cool','Москва','Россия','',_binary '\0','FRIENDS','2019-06-15 18:01:56',_binary '\0',_binary '\0',_binary '\0'),(10,'Дымова','Дарья','2015-09-07','1995-11-15','dumova1995@mail.ru','79234331552','$2a$10$5dg449b2hmHxKqrcb9F6Fu8ftMl7Mznfx14KUVEvLsgvjCOUeF5H.',NULL,'happy','Москва','Россия','',_binary '\0','FRIENDS','2019-06-15 08:52:14',_binary '\0',_binary '\0',_binary '\0');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Dumping data for table `person2dialog`
--

LOCK TABLES `person2dialog` WRITE;
/*!40000 ALTER TABLE `person2dialog` DISABLE KEYS */;
INSERT INTO `person2dialog` VALUES (1,1,1),(2,1,2),(5,1,3),(7,1,4),(9,1,5),(3,2,2),(4,2,3),(10,2,5),(6,3,4),(8,4,5);
/*!40000 ALTER TABLE `person2dialog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'2018-12-06 03:35:15',2,'Конференция','В МГУ 1 марта будет проходить международная конференция среди молодых ученых, заявки принимаются до 1 февраля',_binary '',_binary '\0'),(2,'2018-12-28 13:14:15',4,'Новый год','Мандарины - символ Нового года. В магазинах \"Пятерочка\" всего за 69 р/кг',_binary '',_binary '\0'),(3,'2019-02-05 04:26:15',6,'Новый год по всоточному календарю','Многие жители нашей многонациональной страны празднуют Новый год по восточному календарю',_binary '',_binary '\0'),(4,'2019-03-01 13:18:15',10,'Отдых за границей','Названы самые бюджетные варианты стран, куда можно отправиться всей семьей на море',_binary '',_binary '\0'),(5,'2019-03-24 07:52:15',1,'Масленица','В площади Ленина в предстоящие выходные пройдет ярмарка в честь масленницы',_binary '',_binary '\0'),(6,'2019-04-01 10:00:15',5,'Горящие туры','Успейте приобрести тур в солнечную Анапу по выгодным ценам',_binary '',_binary '\0'),(7,'2019-04-07 13:35:15',7,'Робототехника в России','Российские ученые изобрели робота-водителя',_binary '',_binary '\0'),(8,'2019-04-12 06:53:15',9,'День космонавтики','В день космонавтики мэр вручил дипломы ректору нашего университета',_binary '',_binary '\0'),(9,'2019-05-01 03:00:15',8,'Сборная России в чемпионате Европы пробился в полуфинал','Вратарь сборной проявил себя ловким и не пропустил ни одного гола. Благодаря ему команда одержала победу',_binary '',_binary '\0'),(10,'2019-05-05 13:35:15',5,'Худеть легко','Добавьте в свой ежедневный рацион грейфрут',_binary '',_binary '\0');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `post2tag`
--

LOCK TABLES `post2tag` WRITE;
/*!40000 ALTER TABLE `post2tag` DISABLE KEYS */;
INSERT INTO `post2tag` VALUES (1,1,4),(2,2,9),(3,3,8),(4,4,6),(5,5,2),(6,6,6),(7,7,4),(8,8,2),(9,9,7),(10,10,9);
/*!40000 ALTER TABLE `post2tag` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `post_comment` WRITE;
/*!40000 ALTER TABLE `post_comment` DISABLE KEYS */;
INSERT INTO `post_comment` VALUES (1,'2018-12-06 06:00:15',1,NULL,4,'Дистанционно можно участвовать?',_binary '\0',_binary '\0'),(2,'2018-12-06 10:40:15',1,1,2,'Да, взнос 1000 руб',_binary '\0',_binary '\0'),(3,'2018-12-28 16:10:15',2,NULL,10,'Еще бы',_binary '\0',_binary '\0'),(4,'2019-03-02 12:11:00',4,NULL,2,'Какое море, на рыбалку с пацанами съездить бы хоть',_binary '\0',_binary '\0'),(5,'2019-03-02 13:51:00',4,5,3,'Точняк, хоть нормально отдохням без бабских \"А сфотай так, а сфотай сидя еще...\" ',_binary '\0',_binary '\0'),(6,'2019-03-02 13:54:00',4,6,2,'Все, на майские поедем',_binary '\0',_binary '\0'),(7,'2019-05-01 13:01:15',9,NULL,3,'Красавик Акинфеев!!! Тащит всю команду',_binary '\0',_binary '\0'),(8,'2019-05-01 13:40:15',9,7,4,'Как в ЧМ. Гордость нашего народа. Ты до конца смотрел матч',_binary '\0',_binary '\0'),(9,'2019-05-01 13:45:15',9,8,3,'Нет, до 3 раунда',_binary '\0',_binary '\0'),(10,'2019-05-01 13:45:15',9,9,4,'Тогда зрелищное пропустил в конце 4 раунда',_binary '\0',_binary '\0');
/*!40000 ALTER TABLE `post_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `post_file`
--

LOCK TABLES `post_file` WRITE;
/*!40000 ALTER TABLE `post_file` DISABLE KEYS */;
INSERT INTO `post_file` VALUES (1,1,'forma.doc','d:/Conference/'),(2,1,'zaiavlenie.doc','d:/Conference/'),(3,1,'list.doc','d:/Conference/'),(4,2,'orange.png','d:/Images/'),(5,5,'vesna.mp3','d:/Music/'),(6,6,'sun.png','d:/Images/'),(7,6,'komorovo.mp3','d:/Music/'),(8,10,'fruit.png','d:/Images/'),(9,7,'robot.png','d:/Images/');
/*!40000 ALTER TABLE `post_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `post_like`
--

LOCK TABLES `post_like` WRITE;
/*!40000 ALTER TABLE `post_like` DISABLE KEYS */;
INSERT INTO `post_like` VALUES (1,'2018-12-06 03:35:15',2,2),(2,'2015-11-14 02:01:56',1,8),(3,'2013-08-02 06:55:01',5,4),(4,'2011-07-04 07:12:10',3,5),(5,'2018-05-01 12:59:22',1,2),(6,'2016-03-16 13:17:39',10,3),(7,'2017-04-17 18:31:47',4,7),(8,'2016-11-12 07:02:54',8,1),(9,'2013-11-13 23:12:12',9,3),(10,'2019-01-18 00:14:51',4,8),(11,'2014-07-14 22:07:02',3,7),(12,'2015-06-19 12:12:15',5,6),(13,'2019-08-15 17:22:15',3,7);
/*!40000 ALTER TABLE `post_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (9,'Еда'),(3,'Здоровье'),(4,'Наука'),(1,'Погода'),(5,'Политика'),(2,'Праздники'),(6,'Путешествия'),(8,'Религия'),(7,'Спорт'),(10,'Технологии');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Алексей','alex_inbox@gmail.com','$2a$10$RRepeZvEBd0uZ6P9sP/8zuoZxl66Vk/bPUoRkgLZsFgvM4nuCAq8q','ADMIN'),(2,'Вася','vasya_inbox@gmail.com','$2a$10$2IvAFuMJA/ZxCz395E9unOe.S0N9x.WCu90oKc3dQbLMKhXCFiOJa','ADMIN'),(3,'Света','sveta2000@gmail.com','$2a$10$SXrGyZjfPgBugbMBDYj7DOXPZMAArbh1IqlrYkN/Vut3oW6HpNNhe','MODERATOR'),(4,'Игорь','igor1995@gmail.com','$2a$10$ILwURGlFZ3BcQ7THRWL5wehc64B1R0Z9d9lNrLFBDo7fm8NRsr9e.','MODERATOR'),(5,'Артем','tema_box@gmail.com','$2a$10$hxsSQokBp473kaLa8KvZ9.GQ1WisuLDyZyIBVkEafTwCvNWAXG.r.','MODERATOR'),(6,'Кирилл','kirill_box@gmail.com','$2a$10$L3eCECaCrh6aJMbujxbvVuSJ8LjFDC1RUEubHyTCTYHT1gXth52HG','MODERATOR'),(7,'Даня','danil2001@gmail.com','$2a$10$lqqzSgbVugfIFQ02eLGSSeU1YYIjJKDg1jWQLBkdNXtlF3jRFUOLe','ADMIN'),(8,'Виталий','vitali_inbox@gmail.com','$2a$10$pfs5UEQRAiFIdgYZX.h65ekga9urxVtd2S58F/8ovYrb1nEt3cvGS','MODERATOR'),(9,'Максим','max_box@gmail.com','$2a$10$CQTI3Ub4UXxog/NLQ6hzC.fx4kIUtSpP3IN.Wni303uab11ZWvlD2','ADMIN'),(10,'Айгуль','igul8888@gmail.com','$2a$10$BrU5tKYvw0tSXyvOqxKlguBDScp1.I.yzBfQRd577h75Ujcv9aHUi','ADMIN');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-05 17:13:32
