-- MySQL dump 10.13  Distrib 8.0.25, for macos11.3 (x86_64)
--
-- Host: 127.0.0.1    Database: 9tour
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `advertise_topic`
--

DROP TABLE IF EXISTS `advertise_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advertise_topic`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `code`          varchar(255) DEFAULT NULL,
    `created_by`    bigint       DEFAULT NULL,
    `created_date`  datetime     DEFAULT NULL,
    `is_deleted`    bit(1)       DEFAULT NULL,
    `modified_by`   bigint       DEFAULT NULL,
    `modified_date` datetime     DEFAULT NULL,
    `name`          varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertise_topic`
--

LOCK
TABLES `advertise_topic` WRITE;
/*!40000 ALTER TABLE `advertise_topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `advertise_topic` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `anonymous_user`
--

DROP TABLE IF EXISTS `anonymous_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anonymous_user`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `code`          varchar(255) DEFAULT NULL,
    `created_by`    bigint       DEFAULT NULL,
    `created_date`  datetime     DEFAULT NULL,
    `is_deleted`    bit(1)       DEFAULT NULL,
    `modified_by`   bigint       DEFAULT NULL,
    `modified_date` datetime     DEFAULT NULL,
    `nationality`   varchar(255) DEFAULT NULL,
    `sex`           varchar(255) DEFAULT NULL,
    `region_id`     bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_sknj8847b42mss33277gupi9e` (`code`),
    KEY             `FKdcw5hcv4fyvadfeo6frtdlb9g` (`region_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anonymous_user`
--

LOCK
TABLES `anonymous_user` WRITE;
/*!40000 ALTER TABLE `anonymous_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `anonymous_user` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill`
(
    `code`                   varchar(255) DEFAULT NULL,
    `created_by`             bigint       DEFAULT NULL,
    `created_date`           datetime     DEFAULT NULL,
    `is_deleted`             bit(1)       DEFAULT NULL,
    `modified_by`            bigint       DEFAULT NULL,
    `modified_date`          datetime     DEFAULT NULL,
    `adult_price`            double       DEFAULT NULL,
    `adult_quantity`         int          DEFAULT NULL,
    `child_price`            double       DEFAULT NULL,
    `child_quantity`         int          DEFAULT NULL,
    `infant_price`           double       DEFAULT NULL,
    `infant_quantity`        int          DEFAULT NULL,
    `ticket_id`              bigint NOT NULL,
    `trip_id`                bigint       DEFAULT NULL,
    `tour_name`              varchar(255) DEFAULT NULL,
    `trip_code`              varchar(255) DEFAULT NULL,
    `code_of_trip`           varchar(255) DEFAULT NULL,
    `departure_time_of_trip` datetime     DEFAULT NULL,
    PRIMARY KEY (`ticket_id`),
    UNIQUE KEY `UK_8q38o07vmn6x2opedgsq9sewo` (`code`),
    KEY                      `FKo4x5tdw7w6njaaq31ccv6sr2y` (`trip_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK
TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill`
VALUES (NULL, 0, '2021-06-06 23:01:06', _binary '\0', 0, '2021-06-06 23:01:06', 3995000, 1, 2590000, 0, 1500000, 0, 3,
        9, NULL, NULL, NULL, NULL),
       (NULL, 0, '2021-06-06 23:10:11', _binary '\0', 0, '2021-06-06 23:10:11', 3995000, 1, 2590000, 0, 1500000, 0, 4,
        9, NULL, NULL, NULL, NULL),
       (NULL, 0, '2021-06-07 00:37:16', _binary '\0', 0, '2021-06-07 00:37:16', 3995000, 2, 2590000, 0, 1500000, 0, 5,
        9, NULL, NULL, NULL, NULL),
       (NULL, 0, '2021-06-07 20:20:20', _binary '\0', 0, '2021-06-07 20:20:20', 4500000, 1, 2500000, 0, 1000000, 0, 6,
        8, NULL, NULL, NULL, NULL),
       (NULL, 5, '2021-06-07 20:22:57', _binary '\0', 5, '2021-06-07 20:22:57', 4500000, 1, 2500000, 0, 1000000, 0, 7,
        8, NULL, NULL, NULL, NULL),
       (NULL, 5, '2021-06-07 20:27:17', _binary '\0', 5, '2021-06-25 03:19:05', 4500000, 1, 2500000, 1, 1000000, 0, 8,
        8, 'Tour Đà Lạt 1N: Khám Phá Địa Điểm Mới Tại Đà Lạt', NULL, 'TOUR-HCM-ĐL-2909', '2021-07-04 00:06:00'),
       (NULL, 5, '2021-06-07 20:32:01', _binary '\0', 5, '2021-06-07 20:32:01', 1900000, 1, 1500000, 0, 500000, 0, 9, 6,
        NULL, NULL, NULL, NULL),
       (NULL, 5, '2021-06-07 21:14:30', _binary '\0', 5, '2021-06-07 21:14:30', 4500000, 1, 2500000, 0, 1000000, 0, 10,
        8, NULL, NULL, NULL, NULL),
       (NULL, 5, '2021-06-07 21:30:02', _binary '\0', 5, '2021-06-07 21:30:02', 3995000, 1, 2590000, 0, 1500000, 0, 11,
        9, NULL, NULL, NULL, NULL),
       (NULL, 5, '2021-06-07 21:39:06', _binary '\0', 5, '2021-06-07 21:39:06', 4500000, 1, 2500000, 1, 1000000, 1, 12,
        8, NULL, NULL, NULL, NULL),
       (NULL, 3, '2021-06-08 16:41:38', _binary '\0', 3, '2021-06-08 16:41:38', 2000000, 2, 100000, 1, 0, 1, 13, 2,
        NULL, NULL, NULL, NULL),
       (NULL, 5, '2021-06-09 15:58:54', _binary '\0', 5, '2021-06-09 15:58:54', 1900000, 1, 1500000, 1, 1000000, 1, 14,
        7, 'Tour Đà Lạt 1N: Zoodoo', NULL, 'TOUR-HN-ĐL-2909', NULL),
       (NULL, 5, '2021-06-13 20:58:46', _binary '\0', 5, '2021-06-13 20:58:46', 3995000, 1, 2590000, 1, 1500000, 1, 15,
        9, 'Tour Miền Bắc 5N4D: Hà Nội - Bái Đính - Tràng An - Hạ Long - Sapa - Fansipan', NULL, 'TOUR-HCM-HN_29052021',
        NULL),
       (NULL, 5, '2021-06-13 22:40:30', _binary '\0', 5, '2021-06-13 22:40:30', 1900000, 1, 1500000, 1, 500000, 0, 16,
        6, 'Tour Sài Gòn 1/2N: Tham Quan Thành Phố Hồ Chí Minh', NULL, 'TOUR-HN_HCM', NULL),
       (NULL, 5, '2021-06-17 21:28:56', _binary '\0', 5, '2021-06-17 21:28:56', 4000000, 1, 2000000, 0, 500000, 0, 17,
        3, 'Tour Hà Nội 1N: Tham Quan Phố Cổ - Làng Bát Tràng', NULL, 'TOUR-HN-2', NULL),
       (NULL, 5, '2021-06-25 03:21:47', _binary '\0', 5, '2021-06-25 03:21:47', 4000000, 1, 2000000, 0, 500000, 0, 18,
        3, 'Tour Hà Nội 1N: Tham Quan Phố Cổ - Làng Bát Tràng', NULL, 'TOUR-HN-2', NULL),
       (NULL, 5, '2021-06-25 03:21:50', _binary '\0', 5, '2021-06-25 03:21:50', 4000000, 1, 2000000, 0, 500000, 0, 19,
        3, 'Tour Hà Nội 1N: Tham Quan Phố Cổ - Làng Bát Tràng', NULL, 'TOUR-HN-2', NULL);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category`
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `code`              varchar(255) DEFAULT NULL,
    `created_by`        bigint       DEFAULT NULL,
    `created_date`      datetime     DEFAULT NULL,
    `is_deleted`        bit(1)       DEFAULT NULL,
    `modified_by`       bigint       DEFAULT NULL,
    `modified_date`     datetime     DEFAULT NULL,
    `description`       longtext,
    `name`              varchar(255) DEFAULT NULL,
    `short_description` varchar(255) DEFAULT NULL,
    `thumbnail_url`     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_acatplu22q5d1andql2jbvjy7` (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK
TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category`
VALUES (1, 'kinh-nghiem-du-lich', 3, '2021-05-18 00:10:06', _binary '\0', 17, '2021-06-25 01:35:23', NULL,
        'Kinh Nghiệm Du Lịch', 'kinh nghiệm du lịch',
        'http://res.cloudinary.com/jstit/image/upload/v1624559723/9tour/category/p0s12nkiwp719stp8gd0.jpg'),
       (2, 'dich-covid', 3, '2021-05-18 00:10:43', _binary '\0', 17, '2021-06-25 01:37:54', NULL, 'Dịch Covid',
        'Dịch Covid-19',
        'http://res.cloudinary.com/jstit/image/upload/v1624559873/9tour/category/xhsjo9d9fiqhh9bzaeet.jpg'),
       (3, 'review', 17, '2021-06-25 02:16:23', _binary '\0', 17, '2021-06-25 02:28:28', NULL, 'Review',
        'Review du lịch trong và ngoài nước',
        'http://res.cloudinary.com/jstit/image/upload/v1624562906/9tour/category/tfolv94iznkimlgmhity.jpg');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `contact_information`
--

DROP TABLE IF EXISTS `contact_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_information`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `code`          varchar(255) DEFAULT NULL,
    `created_by`    bigint       DEFAULT NULL,
    `created_date`  datetime     DEFAULT NULL,
    `is_deleted`    bit(1)       DEFAULT NULL,
    `modified_by`   bigint       DEFAULT NULL,
    `modified_date` datetime     DEFAULT NULL,
    `address`       varchar(255) DEFAULT NULL,
    `email`         varchar(255) DEFAULT NULL,
    `first_name`    varchar(255) DEFAULT NULL,
    `last_name`     varchar(255) DEFAULT NULL,
    `phone`         varchar(255) DEFAULT NULL,
    `user_id`       bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_imk8blc514mhmovpvjxprdxo7` (`code`),
    KEY             `FKcerygwdnbtcdmusijlp6ni4fo` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_information`
--

LOCK
TABLES `contact_information` WRITE;
/*!40000 ALTER TABLE `contact_information` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact_information` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `extra_bill`
--

DROP TABLE IF EXISTS `extra_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extra_bill`
(
    `id`                 bigint NOT NULL AUTO_INCREMENT,
    `code`               varchar(255) DEFAULT NULL,
    `created_by`         bigint       DEFAULT NULL,
    `created_date`       datetime     DEFAULT NULL,
    `is_deleted`         bit(1)       DEFAULT NULL,
    `modified_by`        bigint       DEFAULT NULL,
    `modified_date`      datetime     DEFAULT NULL,
    `price`              double       DEFAULT NULL,
    `quantity`           int          DEFAULT NULL,
    `bill_ticket_id`     bigint       DEFAULT NULL,
    `extra_service_id`   bigint       DEFAULT NULL,
    `extra_service_name` varchar(255) DEFAULT NULL,
    `name_of_service`    varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_93xdjqdgn4cuxs10b2e5j05hw` (`code`),
    KEY                  `FKo23ijbw9ceen8auk8946upka2` (`bill_ticket_id`),
    KEY                  `FKtpwcf2757edjkb6hehskjv05h` (`extra_service_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extra_bill`
--

LOCK
TABLES `extra_bill` WRITE;
/*!40000 ALTER TABLE `extra_bill` DISABLE KEYS */;
INSERT INTO `extra_bill`
VALUES (1, NULL, 5, '2021-06-07 21:14:30', _binary '\0', 5, '2021-06-07 21:14:30', 500000, 1, 10, 2, NULL, NULL),
       (2, NULL, 5, '2021-06-07 21:39:06', _binary '\0', 5, '2021-06-07 21:39:06', 500000, 0, 12, 2, NULL, NULL),
       (3, NULL, 3, '2021-06-08 16:41:38', _binary '\0', 3, '2021-06-08 16:41:38', 500000, 1, 13, 1, NULL, NULL),
       (4, NULL, 5, '2021-06-13 22:40:30', _binary '\0', 5, '2021-06-13 22:40:30', 500000, 1, 16, 2, NULL, NULL),
       (5, NULL, 5, '2021-06-17 21:28:56', _binary '\0', 5, '2021-06-17 21:28:56', 500000, 1, 17, 1, NULL, 'Dọn phòng'),
       (6, NULL, 5, '2021-06-25 03:21:47', _binary '\0', 5, '2021-06-25 03:21:47', 500000, 1, 18, 1, NULL, 'Dọn phòng'),
       (7, NULL, 5, '2021-06-25 03:21:50', _binary '\0', 5, '2021-06-25 03:21:50', 500000, 1, 19, 1, NULL, 'Dọn phòng');
/*!40000 ALTER TABLE `extra_bill` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `extra_service`
--

DROP TABLE IF EXISTS `extra_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `extra_service`
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `code`              varchar(255) DEFAULT NULL,
    `created_by`        bigint       DEFAULT NULL,
    `created_date`      datetime     DEFAULT NULL,
    `is_deleted`        bit(1)       DEFAULT NULL,
    `modified_by`       bigint       DEFAULT NULL,
    `modified_date`     datetime     DEFAULT NULL,
    `description`       longtext,
    `name`              varchar(255) DEFAULT NULL,
    `price`             varchar(255) DEFAULT NULL,
    `short_description` varchar(255) DEFAULT NULL,
    `thumbnail_url`     varchar(255) DEFAULT NULL,
    `tour_id`           bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_257gwrmsur793qq6wg8an6twd` (`code`),
    KEY                 `FKj4uypijclfbr9h4a8k2u7e007` (`tour_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `extra_service`
--

LOCK
TABLES `extra_service` WRITE;
/*!40000 ALTER TABLE `extra_service` DISABLE KEYS */;
INSERT INTO `extra_service`
VALUES (1, NULL, 3, '2021-05-30 00:19:50', _binary '\0', 3, '2021-05-30 00:19:50', '500000/phòng ', 'Dọn phòng',
        '500000', 'Dọn và khử khuẩn phòng theo chuẩn ISO:9001',
        'http://res.cloudinary.com/jstit/image/upload/v1622308790/9tour/extra-service/p6vzsey1myflsawbjg3n.jpg', NULL),
       (2, NULL, 3, '2021-06-04 23:52:04', _binary '\0', 3, '2021-06-05 21:28:33',
        'Cho thuê xe máy trong suốt quá trình du lịch', 'Thuê xe máy', '500000.0',
        'thuê xe máy trong quá trình du lịch',
        'http://res.cloudinary.com/jstit/image/upload/v1622825524/9tour/extra-service/ioinjtq7tty9kycdvrr5.jpg', NULL),
       (3, NULL, 3, '2021-06-05 22:27:43', _binary '', 3, '2021-06-05 22:29:38', 'adada', '123', '1111111.0', 'dadđ',
        NULL, NULL);
/*!40000 ALTER TABLE `extra_service` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passenger`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `code`          varchar(255) DEFAULT NULL,
    `created_by`    bigint       DEFAULT NULL,
    `created_date`  datetime     DEFAULT NULL,
    `is_deleted`    bit(1)       DEFAULT NULL,
    `modified_by`   bigint       DEFAULT NULL,
    `modified_date` datetime     DEFAULT NULL,
    `address`       varchar(255) DEFAULT NULL,
    `age`           int          DEFAULT NULL,
    `first_name`    varchar(255) DEFAULT NULL,
    `last_name`     varchar(255) DEFAULT NULL,
    `nationality`   varchar(255) DEFAULT NULL,
    `phone`         varchar(255) DEFAULT NULL,
    `sex`           varchar(255) DEFAULT NULL,
    `ticket_id`     bigint       DEFAULT NULL,
    `user_id`       bigint       DEFAULT NULL,
    `birthday`      datetime     DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_oa28fqne8e21lcaj76esowld3` (`code`),
    KEY             `FKos3sguiasei5a6ysi44ovwou2` (`ticket_id`),
    KEY             `FKmk0iuq3k712q80qqjehqdndoa` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK
TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger`
VALUES (1, NULL, 0, '2021-06-07 00:37:16', _binary '\0', 0, '2021-06-07 00:37:16', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', NULL, 5, NULL, '1999-09-23 00:00:00'),
       (2, NULL, 0, '2021-06-07 00:37:16', _binary '\0', 0, '2021-06-07 00:37:16', NULL, NULL, 'Tuấn', 'Trần',
        'Việt Nam', '0902781404', NULL, 5, NULL, '1999-09-23 00:00:00'),
       (3, NULL, 0, '2021-06-07 20:20:20', _binary '\0', 0, '2021-06-07 20:20:20', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 6, NULL, '1999-09-23 00:00:00'),
       (4, NULL, 5, '2021-06-07 20:22:57', _binary '\0', 5, '2021-06-07 20:22:57', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 7, NULL, '1999-09-23 00:00:00'),
       (5, NULL, 5, '2021-06-07 20:27:20', _binary '\0', 5, '2021-06-07 20:27:20', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 8, NULL, '1999-09-23 00:00:00'),
       (6, NULL, 5, '2021-06-07 20:27:23', _binary '\0', 5, '2021-06-07 20:27:23', NULL, NULL, 'Tuấn', 'Trần',
        'Việt Nam', '0902781404', 'Nữ', 8, NULL, '2003-12-23 00:00:00'),
       (7, NULL, 5, '2021-06-07 20:32:04', _binary '\0', 5, '2021-06-07 20:32:04', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 9, NULL, '1999-09-23 00:00:00'),
       (8, NULL, 5, '2021-06-07 21:14:30', _binary '\0', 5, '2021-06-07 21:14:30', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 10, NULL, '1999-09-23 00:00:00'),
       (9, NULL, 5, '2021-06-07 21:30:02', _binary '\0', 5, '2021-06-07 21:30:02', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 11, NULL, '1999-09-23 00:00:00'),
       (10, NULL, 5, '2021-06-07 21:39:06', _binary '\0', 5, '2021-06-07 21:39:06', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 12, NULL, '1999-09-23 00:00:00'),
       (11, NULL, 5, '2021-06-07 21:39:06', _binary '\0', 5, '2021-06-07 21:39:06', NULL, NULL, 'Tuấn', 'Trần',
        'Việt Nam', '0902781404', 'Nam', 12, NULL, NULL),
       (12, NULL, 5, '2021-06-07 21:39:06', _binary '\0', 5, '2021-06-07 21:39:06', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 12, NULL, NULL),
       (13, NULL, 3, '2021-06-08 16:41:38', _binary '\0', 3, '2021-06-08 16:41:38', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 13, NULL, '1999-09-23 00:00:00'),
       (14, NULL, 3, '2021-06-08 16:41:38', _binary '\0', 3, '2021-06-08 16:41:38', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 13, NULL, '1999-09-09 00:00:00'),
       (15, NULL, 3, '2021-06-08 16:41:38', _binary '\0', 3, '2021-06-08 16:41:38', NULL, NULL, 'Tuấn', 'Trần',
        'Việt Nam', '0902781404', 'Nam', 13, NULL, '2018-10-10 00:00:00'),
       (16, NULL, 3, '2021-06-08 16:41:38', _binary '\0', 3, '2021-06-08 16:41:38', NULL, NULL, 'Tuấn', 'Trần',
        'Việt Nam', '0902781404', 'Nam', 13, NULL, '2020-09-23 00:00:00'),
       (17, NULL, 5, '2021-06-09 15:58:54', _binary '\0', 5, '2021-06-09 15:58:54', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 14, NULL, '1999-09-23 00:00:00'),
       (18, NULL, 5, '2021-06-09 15:58:54', _binary '\0', 5, '2021-06-09 15:58:54', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 14, NULL, '2010-01-23 00:00:00'),
       (19, NULL, 5, '2021-06-09 15:58:54', _binary '\0', 5, '2021-06-09 15:58:54', NULL, NULL, 'Tuấn', 'Trần',
        'Việt Nam', '0902781404', 'Nam', 14, NULL, '2018-09-23 00:00:00'),
       (20, NULL, 5, '2021-06-13 20:58:46', _binary '\0', 5, '2021-06-13 20:58:46', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 15, NULL, '1999-09-23 00:00:00'),
       (21, NULL, 5, '2021-06-13 20:58:46', _binary '\0', 5, '2021-06-13 20:58:46', NULL, NULL, 'Thị Hảo', 'Nguyễn',
        'Việt Nam', '', 'Nữ', 15, NULL, '2018-02-23 00:00:00'),
       (22, NULL, 5, '2021-06-13 20:58:46', _binary '\0', 5, '2021-06-13 20:58:46', NULL, NULL, 'Văn Nam', 'Phạm', '',
        '', 'Nam', 15, NULL, '2020-02-22 00:00:00'),
       (23, NULL, 5, '2021-06-13 22:40:30', _binary '\0', 5, '2021-06-13 22:40:30', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 16, NULL, '1999-09-23 00:00:00'),
       (24, NULL, 5, '2021-06-13 22:40:30', _binary '\0', 5, '2021-06-13 22:40:30', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 16, NULL, '1999-09-23 00:00:00'),
       (25, NULL, 5, '2021-06-17 21:28:56', _binary '\0', 5, '2021-06-17 21:28:56', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 17, NULL, '1999-09-23 00:00:00'),
       (26, NULL, 5, '2021-06-25 03:21:47', _binary '\0', 5, '2021-06-25 03:21:47', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 18, NULL, '1999-09-23 00:00:00'),
       (27, NULL, 5, '2021-06-25 03:21:50', _binary '\0', 5, '2021-06-25 03:21:50', NULL, NULL, 'Tran', 'Tuan',
        'Việt Nam', '0902781404', 'Nam', 19, NULL, '1999-09-23 00:00:00');
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `payment_log`
--

DROP TABLE IF EXISTS `payment_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_log`
(
    `id`                 bigint NOT NULL AUTO_INCREMENT,
    `code`               varchar(255) DEFAULT NULL,
    `created_by`         bigint       DEFAULT NULL,
    `created_date`       datetime     DEFAULT NULL,
    `is_deleted`         bit(1)       DEFAULT NULL,
    `modified_by`        bigint       DEFAULT NULL,
    `modified_date`      datetime     DEFAULT NULL,
    `content`            varchar(255) DEFAULT NULL,
    `description`        longtext,
    `method`             varchar(255) DEFAULT NULL,
    `note`               longtext,
    `type`               varchar(255) DEFAULT NULL,
    `bill_ticket_id`     bigint       DEFAULT NULL,
    `amount`             double       DEFAULT NULL,
    `bank_code`          varchar(255) DEFAULT NULL,
    `bill_code`          varchar(255) DEFAULT NULL,
    `pay_date`           datetime     DEFAULT NULL,
    `transaction_number` varchar(255) DEFAULT NULL,
    `bill_number`        varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_t0m6m8m3k4c96tmp9u2t5ux82` (`code`),
    KEY                  `FKt1wsl52madnhv1limtebyrrk9` (`bill_ticket_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_log`
--

LOCK
TABLES `payment_log` WRITE;
/*!40000 ALTER TABLE `payment_log` DISABLE KEYS */;
INSERT INTO `payment_log`
VALUES (3, NULL, 5, '2021-06-07 21:30:02', _binary '\0', 5, '2021-06-07 21:30:02', NULL,
        'Thanh toan cho don hang: 9 3995000.0', 'VNPAY', NULL, NULL, 11, 3995000, 'NCB', NULL, '2021-06-07 21:29:56',
        '13519247', '60564309'),
       (4, NULL, 5, '2021-06-07 21:39:06', _binary '\0', 5, '2021-06-07 21:39:06', NULL,
        'Thanh toan cho don hang: 8 8000000.0', 'VNPAY', NULL, NULL, 12, 8000000, 'NCB', NULL, '2021-06-07 21:39:01',
        '13519251', '96870188'),
       (5, NULL, 3, '2021-06-08 16:41:38', _binary '\0', 3, '2021-06-08 16:41:38', NULL,
        'Thanh toan cho don hang: 2 4600000.0', 'VNPAY', NULL, NULL, 13, 4600000, 'NCB', NULL, '2021-06-08 16:41:32',
        '13520027', '50391505'),
       (6, NULL, 5, '2021-06-09 15:58:54', _binary '\0', 5, '2021-06-09 15:58:54', NULL,
        'Thanh toan cho don hang: 7 4400000.0', 'VNPAY', NULL, NULL, 14, 4400000, 'NCB', NULL, '2021-06-09 15:58:49',
        '13520754', '73518926'),
       (7, NULL, 5, '2021-06-13 20:59:29', _binary '\0', 5, '2021-06-13 20:59:29', NULL,
        'Thanh toan cho don hang: 9 8085000.0', 'VNPAY', NULL, NULL, 15, 8085000, 'NCB', NULL, '2021-06-13 20:59:24',
        '13523208', '67594684'),
       (8, NULL, 5, '2021-06-13 22:38:59', _binary '\0', 5, '2021-06-13 22:38:59', NULL,
        'Thanh toan lai cho don hang: 8 4500000.0', 'VNPAY', NULL, NULL, 7, 4500000, 'NCB', NULL, '2021-06-13 22:38:54',
        '13523234', '33033404'),
       (9, NULL, 5, '2021-06-13 22:40:47', _binary '\0', 5, '2021-06-13 22:40:47', NULL,
        'Thanh toan cho don hang: 6 3900000.0', 'VNPAY', NULL, NULL, 16, 3900000, 'NCB', NULL, '2021-06-13 22:40:41',
        '13523236', '56074162'),
       (10, NULL, 5, '2021-06-13 22:46:51', _binary '\0', 5, '2021-06-13 22:46:51', NULL,
        'Thanh toan lai cho don hang: 8 7000000.0', 'VNPAY', NULL, NULL, 8, 7000000, 'NCB', NULL, '2021-06-13 22:46:42',
        '13523238', '13359246'),
       (11, NULL, 5, '2021-06-17 21:29:21', _binary '\0', 5, '2021-06-17 21:29:21', NULL,
        'Thanh toan cho don hang: 3 4500000.0', 'VNPAY', NULL, NULL, 17, 4500000, 'NCB', NULL, '2021-06-17 21:29:15',
        '13526573', '74936363'),
       (12, NULL, 5, '2021-06-17 21:34:09', _binary '\0', 5, '2021-06-17 21:34:09', NULL,
        'Thanh toan cho don hang: 3 4500000.0', 'VNPAY', NULL, NULL, 9, 4500000, 'NCB', NULL, '2021-06-17 21:29:15',
        '13526573', '74936363'),
       (13, NULL, 5, '2021-06-17 21:36:07', _binary '', 5, '2021-06-17 21:36:07', NULL,
        'Thanh toan lai cho don hang: 8 5000000.0', 'VNPAY', NULL, NULL, 10, 5000000, 'NCB', NULL,
        '2021-06-17 21:35:59', '13526577', '69366028'),
       (14, NULL, 5, '2021-06-25 03:22:15', _binary '\0', 5, '2021-06-25 03:22:15', NULL,
        'Thanh toan cho don hang: 3 4500000.0', 'VNPAY', NULL, NULL, 19, 4500000, 'NCB', NULL, '2021-06-25 03:22:08',
        '13531420', '11036512');
/*!40000 ALTER TABLE `payment_log` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission`
(
    `id`              bigint NOT NULL AUTO_INCREMENT,
    `code`            varchar(255) DEFAULT NULL,
    `created_by`      bigint       DEFAULT NULL,
    `created_date`    datetime     DEFAULT NULL,
    `is_deleted`      bit(1)       DEFAULT NULL,
    `modified_by`     bigint       DEFAULT NULL,
    `modified_date`   datetime     DEFAULT NULL,
    `permission_key`  varchar(255) DEFAULT NULL,
    `permission_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_a7ujv987la0i7a0o91ueevchc` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK
TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `permission_roles`
--

DROP TABLE IF EXISTS `permission_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission_roles`
(
    `permissions_id` bigint NOT NULL,
    `roles_id`       bigint NOT NULL,
    KEY              `FK9k4j9myvlxs8w8omv4awtpcpo` (`roles_id`),
    KEY              `FK9lqx45njk0pt5lcux4lqewijk` (`permissions_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_roles`
--

LOCK
TABLES `permission_roles` WRITE;
/*!40000 ALTER TABLE `permission_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission_roles` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `place`
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `code`              varchar(255) DEFAULT NULL,
    `created_by`        bigint       DEFAULT NULL,
    `created_date`      datetime     DEFAULT NULL,
    `is_deleted`        bit(1)       DEFAULT NULL,
    `modified_by`       bigint       DEFAULT NULL,
    `modified_date`     datetime     DEFAULT NULL,
    `description`       longtext,
    `name`              varchar(255) DEFAULT NULL,
    `short_description` varchar(255) DEFAULT NULL,
    `thumbnail_url`     varchar(255) DEFAULT NULL,
    `parent_place_id`   bigint       DEFAULT NULL,
    `region_id`         bigint       DEFAULT NULL,
    `lat`               varchar(255) DEFAULT NULL,
    `lng`               varchar(255) DEFAULT NULL,
    `hd`                varchar(255) DEFAULT NULL,
    `pt`                varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_r186fpmygnarrcd201o1vxe8j` (`code`),
    KEY                 `FKrnfk5qcvj3fc8ebh7s31u8h7m` (`parent_place_id`),
    KEY                 `FKkuxqythqjj3e6u43grkej2ft8` (`region_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK
TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place`
VALUES (3, 'ha-noi', 3, '2021-05-18 23:09:25', _binary '\0', 3, '2021-05-29 23:14:24', NULL, 'Hà Nội', 'Hà Nội',
        'http://res.cloudinary.com/jstit/image/upload/v1622304864/9tour/place/kub23njg3vmutqdl1mgj.jpg', 3, 1,
        '21.0367723', '105.8346946', NULL, NULL),
       (4, 'da-lat', 3, '2021-05-20 21:40:07', _binary '\0', 3, '2021-05-29 23:12:13', NULL, 'Đà Lạt',
        'Đà Lạt nổi tiếng với danh xưng thành phố ngàn hoa',
        'http://res.cloudinary.com/jstit/image/upload/v1622304680/9tour/place/dhlfju5dxenomwpdq8ln.jpg', 5, 4,
        '11.8968495', '108.4522843', NULL, NULL),
       (5, 'lam-dong', 3, '2021-05-29 23:11:59', _binary '\0', 3, '2021-05-29 23:18:18', NULL, 'Lâm Đồng', '',
        'http://res.cloudinary.com/jstit/image/upload/v1622304982/9tour/place/oubbvhz7fxsyiapguofs.jpg', NULL, 4, '',
        '', NULL, NULL),
       (6, 'tp-ho-chi-minh', 3, '2021-05-29 23:17:53', _binary '\0', 3, '2021-05-29 23:18:06', NULL, 'Tp. Hồ Chí Minh',
        'Thành phố năng động và phát triển bậc nhất Việt Nam',
        'http://res.cloudinary.com/jstit/image/upload/v1622305073/9tour/place/kedmsbowmefkwl5mmall.jpg', 6, 3,
        '10.7778937', '106.696271', NULL, NULL),
       (7, 'sa-pa', 17, '2021-06-25 02:09:56', _binary '\0', 17, '2021-06-25 02:09:56', NULL, 'SaPa',
        'Sapa thành phố trong sương',
        'http://res.cloudinary.com/jstit/image/upload/v1624561795/9tour/place/qptkrzpl27paooptwajm.jpg', NULL, 1,
        '22.3352204', '103.8426823', NULL, NULL);
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post`
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `code`              varchar(255) DEFAULT NULL,
    `created_by`        bigint       DEFAULT NULL,
    `created_date`      datetime     DEFAULT NULL,
    `is_deleted`        bit(1)       DEFAULT NULL,
    `modified_by`       bigint       DEFAULT NULL,
    `modified_date`     datetime     DEFAULT NULL,
    `content`           longtext,
    `short_description` varchar(255) DEFAULT NULL,
    `thumbnail_url`     varchar(255) DEFAULT NULL,
    `title`             varchar(255) DEFAULT NULL,
    `category_id`       bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_tojhbicb8m3fuo82limwdqlaj` (`code`),
    KEY                 `FKg6l1ydp1pwkmyj166teiuov1b` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK
TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post`
VALUES (1, NULL, 3, '2021-05-18 00:12:39', _binary '\0', 3, '2021-05-30 00:44:59',
        '<p><img alt=\"May be an image of lake and nature\" src=\"https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.6435-9/181119171_1828801637546092_6745289687983869832_n.jpg?_nc_cat=105&amp;ccb=1-3&amp;_nc_sid=8bfeb9&amp;_nc_ohc=jFoW1ALiAdoAX9_D6uO&amp;_nc_ht=scontent.fsgn5-2.fna&amp;oh=e8e967d1a27b9c31275d7dd547fede3a&amp;oe=60BEFE41\" /></p>\r\n\r\n<h2><s>Ch&egrave;o SUP l&agrave; g&igrave;?</s></h2>\r\n\r\n<p><s>Ch&egrave;o SUP c&ograve;n gọi l&agrave; ch&egrave;o thuyền đứng một v&aacute;n tr&ecirc;n hồ, s&ocirc;ng, những nơi n&agrave;o đ&oacute; c&oacute; mặt nước phẳng lặng một ch&uacute;t. Tuy nhi&ecirc;n, ch&egrave;o SUP đứng l&agrave; ở kỹ thuật cao về thăng bằng rồi, bạn c&oacute; thể ch&egrave;o SUP khi ngồi cũng kh&ocirc;ng sao cả nh&eacute;.</s></p>\r\n\r\n<h2><s>Lộ tr&igrave;nh ch&egrave;o SUP ở S&agrave;i G&ograve;n</s></h2>\r\n\r\n<p><s>Lộ tr&igrave;nh xuất ph&aacute;t từ c&aacute;c điểm cho thu&ecirc; thuyền SUP, th&ocirc;ng thường sẽ xuất ph&aacute;t ở Thanh Đa, B&igrave;nh Thạnh, ch&uacute;ng ta sẽ ch&egrave;o đến khu vực k&ecirc;nh dừa với 2 h&agrave;ng dừa bao phủ rất đẹp.</s></p>\r\n\r\n<p><img alt=\"May be an image of nature and body of water\" src=\"https://scontent-xsp1-3.xx.fbcdn.net/v/t1.6435-9/179709928_1828801697546086_8878195354796885947_n.jpg?_nc_cat=111&amp;ccb=1-3&amp;_nc_sid=8bfeb9&amp;_nc_ohc=o_1iCP1ge-MAX9tEsTf&amp;_nc_ht=scontent-xsp1-3.xx&amp;oh=4cbaee39bf06ea38cfb73c994419873d&amp;oe=60BF29CC\" /></p>\r\n\r\n<p><img alt=\"May be an image of body of water, tree and nature\" src=\"https://scontent-xsp1-2.xx.fbcdn.net/v/t1.6435-9/183509306_1828801727546083_3940251586275499217_n.jpg?_nc_cat=102&amp;ccb=1-3&amp;_nc_sid=8bfeb9&amp;_nc_ohc=TOtjrEx94pUAX8daN6a&amp;_nc_ht=scontent-xsp1-2.xx&amp;oh=2fbad916dec6d4f1b113fe3c60b54e8d&amp;oe=60BCF3AB\" /></p>\r\n\r\n<p><s>Bạn c&oacute; thể mua dừa tr&aacute;i để uống tr&ecirc;n thuyền SUP từ người địa phương, nạp năng lượng để tiếp tục ch&egrave;o ngược lại điểm xuất ph&aacute;t.</s></p>\r\n\r\n<h2><s>Kinh nghiệm đi ch&egrave;o SUP ở S&agrave;i G&ograve;n</s></h2>\r\n\r\n<p><s><img alt=\"\" sizes=\"(max-width: 800px) 100vw, 800px\" src=\"https://venturology.files.wordpress.com/2021/05/checc80o-thuyecc82cc80n-sup.jpg?w=800\" srcset=\"https://venturology.files.wordpress.com/2021/05/checc80o-thuyecc82cc80n-sup.jpg?w=800 800w, https://venturology.files.wordpress.com/2021/05/checc80o-thuyecc82cc80n-sup.jpg?w=150 150w, https://venturology.files.wordpress.com/2021/05/checc80o-thuyecc82cc80n-sup.jpg?w=300 300w, https://venturology.files.wordpress.com/2021/05/checc80o-thuyecc82cc80n-sup.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/05/checc80o-thuyecc82cc80n-sup.jpg?w=1024 1024w, https://venturology.files.wordpress.com/2021/05/checc80o-thuyecc82cc80n-sup.jpg 1280w\" /></s></p>\r\n\r\n<ul>\r\n	<li><s>Trước khi đi ch&egrave;o, bạn cần phải li&ecirc;n hệ trước với b&ecirc;n thu&ecirc; để kiểm tra lịch thuỷ triều, v&igrave; triều cao mới ch&egrave;o được, nếu thấp qu&aacute; bạn sẽ kh&ocirc;ng được ch&egrave;o</s></li>\r\n	<li><s>Mặc &aacute;o thể thao tay d&agrave;i cho đỡ ch&aacute;y da, mang legging thể thao lu&ocirc;n để đỡ muỗi cắn</s></li>\r\n	<li><s>Thoa đầy đủ kem chống nắng</s></li>\r\n	<li><s>Thời gian cao điểm l&agrave; 4-5 giờ v&igrave; l&uacute;c n&agrave;y m&aacute;t v&agrave; c&oacute; ho&agrave;ng h&ocirc;n, n&ecirc;n nếu ch&egrave;o v&agrave;o khu rặng dừa sẽ kẹt&hellip; xuồng. M&igrave;nh khuy&ecirc;n n&ecirc;n đi khung 3 giờ cho thư thả</s></li>\r\n	<li><s>Nhớ đội n&oacute;n, nắng lắm, dễ say</s></li>\r\n</ul>\r\n\r\n<p><img alt=\"May be an image of nature and body of water\" src=\"https://scontent-xsp1-3.xx.fbcdn.net/v/t1.6435-9/181372152_1828801824212740_6657606060651837914_n.jpg?_nc_cat=109&amp;ccb=1-3&amp;_nc_sid=8bfeb9&amp;_nc_ohc=839gzxyBnKEAX-Qzqv9&amp;_nc_ht=scontent-xsp1-3.xx&amp;oh=c3475d833b6c8c714bb5f6b1965b8249&amp;oe=60C061F5\" /></p>\r\n\r\n<ul>\r\n	<li><s>Nh&eacute;t 20k v&ocirc; đ&acirc;u đ&oacute; trong người để đi ngang khu rặng dừa mua tr&aacute;i dừa uống tr&ecirc;n SUP rất l&agrave; chill, nhưng uống xong th&igrave; đem l&ecirc;n bờ quăng nh&eacute;, đừng c&oacute; vứt xuống s&ocirc;ng trời ơi</s></li>\r\n	<li><s>Ch&egrave;o từ 2-3 tiếng, rất ph&ecirc;, n&ecirc;n h&atilde;y sẵn s&agrave;ng cho đ&ocirc;i tay chuẩn người đẹp tay đ&ocirc; nha</s></li>\r\n</ul>\r\n\r\n<h2><s>Ch&egrave;o thuyền SUP ở đ&acirc;u?</s></h2>\r\n\r\n<p><s>M&igrave;nh ch&egrave;o c&ugrave;ng với&nbsp;<strong>Saigon SUP Club</strong>&nbsp;ở địa chỉ&nbsp;<strong>480/89 B&igrave;nh Quới, B&igrave;nh Thạnh, TPHCM.</strong></s></p>\r\n\r\n<p><s>Gi&aacute; thu&ecirc; l&agrave;&nbsp;<strong>300,000VNĐ/người&nbsp;</strong>gồm c&oacute; m&aacute;i ch&egrave;o, SUP, t&uacute;i đựng chống nước điện thoại.</s></p>\r\n',
        'Nếu như bạn nghĩ rằng Sài Gòn có gì đâu mà chơi ngoài đi ăn uống, trà sữa, bar bủng, thì có lẽ bạn chưa thử những hoạt động ngoài trời mà thôi. Một trong những hoạt động ngoài trời đáng thử ở Sài Gòn không thể không kể đến là chèo thuyền SUP.',
        'http://res.cloudinary.com/jstit/image/upload/v1622303053/9tour/post/yduz6jobticaq8kqxeay.jpg',
        'CHÈO THUYỀN SUP TRÊN SÔNG SÀI GÒN, TRẢI NGHIỆM ĐÁNG THỬ', 1),
       (2, NULL, 3, '2021-05-18 00:14:51', _binary '\0', 3, '2021-05-29 22:56:10',
        '<p>(Chinhphu.vn) - Theo c&aacute;c chuy&ecirc;n gia quốc tế, b&ecirc;n cạnh những th&aacute;ch thức do đại dịch COVID-19 g&acirc;y ra th&igrave; đ&acirc;y ch&iacute;nh ra cơ hội để cho ng&agrave;nh du lịch c&ugrave;ng nh&igrave;n lại m&igrave;nh v&agrave; x&acirc;y dựng một chiến lược du lịch mang t&iacute;nh thực tế hơn v&agrave; bền vững hơn. Đồng thời, tạo ra một thương hiệu quốc gia &ldquo;An to&agrave;n-Xanh-Sạch&rdquo; để thu h&uacute;t kh&aacute;ch quốc tế khi dịch bệnh đ&atilde; được kiểm so&aacute;t tr&ecirc;n to&agrave;n thế giới.</p>\r\n\r\n<table align=\"center\">\r\n	<tbody>\r\n		<tr>\r\n			<td><img src=\"http://baochinhphu.vn/Uploaded/buithuhuong/2021_04_05/WB2.jpg?maxwidth=460\" /></td>\r\n		</tr>\r\n		<tr>\r\n			<td>&Ocirc;ng Ahmed Eiweida, Điều phối vi&ecirc;n to&agrave;n cầu về Di sản văn h&oacute;a v&agrave; Du lịch bền vững của Ng&acirc;n h&agrave;ng Thế giới (WB) - Ảnh: VGP</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n\r\n<p>&Ocirc;ng Ahmed Eiweida, Điều phối vi&ecirc;n to&agrave;n cầu về Di sản văn h&oacute;a v&agrave; Du lịch bền vững của Ng&acirc;n h&agrave;ng Thế giới (WB) nhận định, thời điểm hiện tại, du lịch nội địa chắc chắn sẽ l&agrave; ch&igrave;a kh&oacute;a để l&agrave;m sống lại ng&agrave;nh du lịch Việt Nam bởi ngay trước khi c&oacute; COVID-19 th&igrave; du lịch nội địa cũng đ&atilde; đ&oacute;ng g&oacute;p tới 5,5% tổng GDP cho to&agrave;n bộ nền kinh tế. Ch&iacute;nh phủ Việt Nam đ&atilde; rất th&agrave;nh c&ocirc;ng trong việc khống chế đại dịch n&ecirc;n ng&agrave;nh du lịch d&ugrave; c&oacute; bị ảnh hưởng (giảm 25% ở thời điểm cao nhất) nhưng b&acirc;y giờ vẫn tiếp tục ph&aacute;t triển trở lại. Du lịch trong nước đ&oacute;ng g&oacute;p cho GDP của cả nền kinh tế, tạo ra nhiều c&ocirc;ng ăn việc l&agrave;m n&ecirc;n đ&acirc;y l&agrave; lĩnh vực c&oacute; thể hỗ trợ cho to&agrave;n bộ ng&agrave;nh du lịch n&oacute;i chung.</p>\r\n\r\n<p>Đồng t&igrave;nh với quan điểm tr&ecirc;n, &ocirc;ng Michael Croft, Trưởng đại diện Tổ chức Gi&aacute;o dục, Khoa học v&agrave; Văn h&oacute;a Li&ecirc;n Hợp Quốc (UNESCO) tại Việt Nam cho rằng, đại dịch COVID-19 đ&atilde; gi&uacute;p Việt Nam đ&aacute;nh gi&aacute; đ&uacute;ng mực thị trường du lịch trong nước sau thời gian d&agrave;i chỉ tập trung v&agrave;o đối tượng l&agrave; kh&aacute;ch du lịch quốc tế.</p>\r\n\r\n<p>&ldquo;Thị trường trong nước v&agrave; kh&aacute;ch du lịch trong nước ch&iacute;nh l&agrave; những đối t&aacute;c rất quan trọng ở kh&iacute;a cạnh số lượng. Việt Nam c&oacute; khoảng 100 triệu d&acirc;n, tất nhi&ecirc;n kh&ocirc;ng phải 100 triệu người d&acirc;n n&agrave;y đều đi du lịch. Thế nhưng, con số đ&aacute;ng kể của người Việt du lịch trong nước hơn một lần một năm rất nhiều. Đ&acirc;y ch&iacute;nh ra lợi thế của ng&agrave;nh du lịch trong thời điểm dịch COVID-19&rdquo;, &ocirc;ng Croft cho biết.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<table align=\"center\">\r\n	<tbody>\r\n		<tr>\r\n			<td><img src=\"http://baochinhphu.vn/Uploaded/buithuhuong/2021_04_05/bieu%20do.png?maxwidth=460\" /></td>\r\n		</tr>\r\n		<tr>\r\n			<td>M&ocirc; h&igrave;nh phục hồi du lịch Việt Nam của McKinsey</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n\r\n<p>Theo C&ocirc;ng ty tư vấn quản l&yacute; to&agrave;n cầu McKinsey, nếu Việt Nam c&oacute; thể duy tr&igrave; tỷ lệ l&acirc;y nhiễm COVID-19 thấp th&igrave; ng&agrave;nh du lịch c&oacute; thể phục hồi lại v&agrave;o năm 2024 nhờ du lịch trong nước. McKinsey cũng đưa ra nhận định, Việt Nam c&oacute; thể vực dậy nhu cầu du lịch trong nước bằng c&aacute;ch tập trung v&agrave;o những điểm đến đang thịnh h&agrave;nh v&agrave; phối hợp c&ugrave;ng ch&iacute;nh quyền địa phương, c&ocirc;ng ty lữ h&agrave;nh trực tuyến, điểm du lịch, kh&aacute;ch sạn v&agrave; h&atilde;ng h&agrave;ng kh&ocirc;ng. Để khai th&aacute;c triệt để hơn cơ hội du lịch trong nước, c&aacute;c c&ocirc;ng ty du lịch cần ch&uacute; trọng đến những sản phẩm dịch vụ ph&ugrave; hợp khả năng chi trả của kh&aacute;ch, đồng thời duy tr&igrave; c&aacute;c sản phẩm v&agrave; trải nghiệm chất lượng cao.</p>\r\n\r\n<p><strong>X&acirc;y dựng sản phẩm du lịch mới, mức gi&aacute; mới</strong></p>\r\n\r\n<p>&ldquo;Mong muốn của kh&aacute;ch du lịch trong nước l&agrave; họ lu&ocirc;n hướng tới những điểm du lịch mang t&iacute;nh chất nguy&ecirc;n sơ. Họ mong muốn được kh&aacute;m ph&aacute; v&agrave; trải nghiệm, đặc biệt l&agrave; trải nghiệm những sản phẩm du lịch mới. Từ sở th&iacute;ch của kh&aacute;ch nội địa, ch&uacute;ng ta c&oacute; thể nhận diện ra được thị hiếu của kh&aacute;ch du lịch quốc tế sau khi thị trường du lịch đ&atilde; kh&ocirc;i phục trở lại&rdquo;, &ocirc;ng Michael Croft n&ecirc;u giải ph&aacute;p để k&iacute;ch cầu du lịch nội địa.</p>\r\n\r\n<p>Ngo&agrave;i ph&aacute;t triển sản phẩm mới, theo McKinsey, Việt Nam cũng cần c&acirc;n nhắc m&ocirc; h&igrave;nh gi&aacute; mới để phục hồi nhu cầu. Doanh nghiệp cũng c&oacute; thể t&igrave;m c&aacute;ch b&aacute;n sản phẩm theo g&oacute;i để c&oacute; cơ hội b&aacute;n ch&eacute;o v&agrave; b&aacute;n th&ecirc;m, đồng thời đa dạng h&oacute;a nguồn doanh thu, củng cố sản phẩm cao cấp v&agrave; khả năng thu được mức gi&aacute; cao hơn. V&iacute; dụ, c&aacute;c kh&aacute;ch sạn 5 sao tại H&agrave; Nội v&agrave; Th&agrave;nh phố Hồ Ch&iacute; Minh c&oacute; thể b&aacute;n g&oacute;i &ldquo;staycation&rdquo; cho gia đ&igrave;nh k&egrave;m theo ph&ograve;ng nghỉ cao cấp l&agrave; dịch vụ xe sang đưa đ&oacute;n v&agrave; giảm gi&aacute; dịch vụ ăn uống. C&aacute;c c&ocirc;ng ty du lịch v&agrave; kh&aacute;ch sạn c&oacute; thể phối hợp với nhau để cung cấp trọn g&oacute;i dịch vụ từ v&eacute; m&aacute;y bay, v&eacute; t&agrave;u, xe limousine hoặc xe bus, đến ph&ograve;ng nghỉ. Ngo&agrave;i ra, cũng c&oacute; thể khai th&aacute;c những nhu cầu du lịch đắt tiền đang b&ugrave;ng nổ như du thuyền hay &ldquo;farm stay&rdquo;.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<table align=\"center\">\r\n	<tbody>\r\n		<tr>\r\n			<td><img src=\"http://baochinhphu.vn/Uploaded/buithuhuong/2021_04_05/unesco.jpg?maxwidth=460\" /></td>\r\n		</tr>\r\n		<tr>\r\n			<td>&Ocirc;ng Michael Croft, Trưởng đại diện Tổ chức Gi&aacute;o dục, Khoa học v&agrave; Văn h&oacute;a Li&ecirc;n Hợp Quốc (UNESCO) tại Việt Nam - Ảnh: VGP</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n\r\n<p><strong>Ph&aacute;t triển du lịch bền vững, giảm t&aacute;c động v&agrave;o m&ocirc;i trường</strong></p>\r\n\r\n<p>Theo &ocirc;ng Michael Croft, trong bối cảnh m&agrave; đại dịch COVID-19 vẫn c&ograve;n ho&agrave;nh h&agrave;nh như hiện nay th&igrave; đ&acirc;y ch&iacute;nh l&agrave; thời cơ để ph&aacute;t triển du lịch bền vững.</p>\r\n\r\n<p>&ldquo;T&ocirc;i lấy v&iacute; dụ như điểm du lịch Boracay, Philippines. Trước thời điểm đại dịch, ch&iacute;nh quyền Philippines đ&atilde; rất kh&oacute; khăn khi đối mặt với những vấn đề tồn đọng tại khu vực n&agrave;y, đ&oacute; l&agrave; &ocirc; nhiễm, r&aacute;c thải nhựa do lượng kh&aacute;ch du lịch đến khu vực n&agrave;y rất đ&ocirc;ng. Nhưng sau khi đại dịch diễn ra, d&ugrave; phải chịu cảnh kh&ocirc;ng c&oacute; kh&aacute;ch du lịch quốc tế, kh&ocirc;ng c&oacute; những chuyến bay thương mại quốc tế, nhưng đ&oacute; cũng l&agrave; l&uacute;c m&agrave; ban quản l&yacute; khu du lịch n&agrave;y c&oacute; cơ hội để c&ugrave;ng xem x&eacute;t v&agrave; nh&igrave;n nhận lại những kế hoạch quản l&yacute; du lịch cũng như kế hoạch đầu tư, ng&acirc;n s&aacute;ch đầu tư cho du lịch, ch&iacute;nh s&aacute;ch để ưu ti&ecirc;n ph&aacute;t triển du lịch, để c&oacute; thể đặt m&igrave;nh v&agrave;o vị tr&iacute; sẵn s&agrave;ng hơn v&agrave; cho ra những sản phẩm du lịch tốt hơn sau khi đại dịch lắng xuống. Đ&acirc;y cũng l&agrave; vấn đề m&agrave; Việt Nam cần học hỏi&rdquo;, &ocirc;ng Croft nhấn mạnh.</p>\r\n\r\n<p>&Ocirc;ng Croft nhấn mạnh UNESCO coi trọng t&acirc;m ưu ti&ecirc;n ch&iacute;nh của ph&aacute;t triển du lịch bền vững l&agrave; quan t&acirc;m đến việc bảo tồn, g&igrave;n giữ v&agrave; ph&aacute;t huy gi&aacute; trị của c&aacute;c di sản văn h&oacute;a tại Việt Nam. &ldquo;Khi ch&uacute;ng t&ocirc;i đi đến c&aacute;c khu di sản, ch&uacute;ng t&ocirc;i đều thấy ban quản l&yacute; cũng như ch&iacute;nh quyền địa phương rất quan t&acirc;m đến chiến lược ph&aacute;t triển du lịch bền vững để l&agrave;m sao c&oacute; thể kh&ocirc;i phục lại ng&agrave;nh du lịch sau đại dịch COVID-19. Văn ph&ograve;ng UNESCO H&agrave; Nội cũng như Văn ph&ograve;ng UNESCO ở Paris đang phối hợp c&ugrave;ng với nhau để x&acirc;y dựng những m&ocirc; h&igrave;nh th&iacute; điểm cho Hội An hay Tr&agrave;ng An để mở rộng quy m&ocirc; quản l&yacute; kh&aacute;c du lịch tới 2 khu di sản nổi tiếng n&agrave;y, ph&aacute;t triển du lịch một c&aacute;ch bền vững ngay cả nếu c&oacute; xảy ra đại dịch tương tự v&agrave; khiến lượng kh&aacute;ch du lịch sụt giảm&rdquo;, &ocirc;ng Croft n&oacute;i.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<table align=\"center\">\r\n	<tbody>\r\n		<tr>\r\n			<td><img src=\"http://baochinhphu.vn/Uploaded/buithuhuong/2021_04_05/hoi-an-quang-nam-vntrip-1.jpg?maxwidth=460\" /></td>\r\n		</tr>\r\n		<tr>\r\n			<td>H&ocirc;i An với m&ocirc; h&igrave;nh ph&aacute;t triển du lịch bền vững được trải đều tới cộng đồng địa phương - Ảnh minh họa</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n\r\n<p>Trưởng đại diện UNESCO tại Việt Nam cho biết th&ecirc;m, đối với tổ chức n&agrave;y, ph&aacute;t triển du lịch bền vững l&agrave; đầu tư v&agrave;o ph&aacute;t triển cộng đồng địa phương, ph&aacute;t triển văn h&oacute;a địa phương. Những yếu tố n&agrave;y kh&ocirc;ng thể bị mai một khi ch&uacute;ng ta x&acirc;y dựng c&aacute;c chiến lược ph&aacute;t triển bền vững.</p>\r\n\r\n<p>&ldquo;Lấy Hội An l&agrave;m v&iacute; dụ. 20 năm trước khi UNESCO chưa ghi danh di sản văn h&oacute;a Hội An v&agrave; Mỹ Sơn th&igrave; vấn đề của ch&iacute;nh quyền địa phương, của cộng đồng địa phương, của l&atilde;nh đạo cấp tỉnh l&agrave; Hội An sẽ ph&aacute;t triển du lịch như thế n&agrave;o, họ sẽ x&acirc;y dựng khu di sản như thế n&agrave;o. UNSECO đ&atilde; c&ugrave;ng chung tay v&agrave;o c&acirc;u chuyện đ&oacute;, đưa ra những m&ocirc; h&igrave;nh ph&aacute;t triển bền vững v&agrave; ch&uacute;ng ta thấy rằng 20 năm sau, tại thời điểm n&agrave;y, ch&uacute;ng ta c&oacute; thể nh&igrave;n thấy những đầu tư về ph&aacute;t triển du lịch bền vững ở Hội An được trải đều cho tất cả mọi g&oacute;c cạnh ở th&agrave;nh phố Hội An, trải đều tới cộng đồng địa phương, người d&acirc;n địa phương v&agrave; cho cả những doanh nghiệp nhỏ ở địa phương chứ kh&ocirc;ng chỉ d&agrave;nh ri&ecirc;ng cho doangh nghiệp lớn. Khi đến với Hội An, du kh&aacute;ch sẽ cảm nhận được những n&eacute;t đặc th&ugrave;, nguy&ecirc;n sơ. Đ&acirc;y l&agrave; khu du lịch m&agrave; ch&uacute;ng ta kh&ocirc;ng nh&igrave;n thấy những kh&aacute;ch sạn 5 sao, những t&ograve;a nh&agrave; cao tầng chọc trời.</p>\r\n\r\n<p>Ở b&atilde;i biển An B&agrave;ng chẳng hạn, ch&uacute;ng ta nh&igrave;n thấy những cửa h&agrave;ng địa phương, những homestay địa phương, mang những n&eacute;t rất đặc trưng của Hội An. Ch&iacute;nh v&igrave; vậy, ch&uacute;ng t&ocirc;i coi ph&aacute;t triển du lịch bền vững ch&iacute;nh l&agrave; đầu tư cho ph&aacute;t triển cộng đồng địa phương, g&igrave;n giữ cho những c&aacute;i nền văn h&oacute;a địa phương để l&agrave;m sao mang lại những lợi &iacute;ch, những t&aacute;c động cho người d&acirc;n địa phương. Đ&oacute; cũng ch&iacute;nh l&agrave; quan điểm của UNESCO về ph&aacute;t triển du lịch bền vững&rdquo;, &ocirc;ng Michale Croft nhấn mạnh.</p>\r\n\r\n<p><strong>Tạo thương hiệu về điểm đến &ldquo;An to&agrave;n-Xanh-Sạch&rdquo;</strong></p>\r\n\r\n<p>&Ocirc;ng Ahmed Eiweida nhận định, Ch&iacute;nh phủ Việt Nam đ&atilde; rất th&agrave;nh c&ocirc;ng trong việc khống chế đại dịch n&ecirc;n trong tương lai, ch&uacute;ng ta c&oacute; thể đặt m&igrave;nh ở vị thế l&agrave; điểm đến an to&agrave;n, xanh v&agrave; sạch.</p>\r\n\r\n<p>&ldquo;Khi c&aacute;c nước quản l&yacute; được COVID-19, người d&acirc;n kh&ocirc;ng c&ograve;n bị phong tỏa nữa th&igrave; họ sẽ bắt đầu nghĩ đến việc được hưởng thụ c&aacute;c dịch vụ, c&aacute;c điểm du lịch an to&agrave;n, sạch đẹp. V&agrave; họ sẽ t&igrave;m đến những nơi Việt Nam để được trải nghiệm kh&ocirc;ng kh&iacute; trong l&agrave;nh, thi&ecirc;n nhi&ecirc;n tươi đẹp, những sản phẩm du lịch như rừng, n&uacute;i, bờ biển một c&aacute;ch an to&agrave;n.</p>\r\n\r\n<p>Việt Nam c&oacute; lợi thế về mặt tự nhi&ecirc;n để ph&aacute;t triển du lịch. Khi mở cửa trở lại, t&ocirc;i nghĩ Việt Nam c&oacute; thể đầu tư nhiều hơn để cải thiện được chất lượng của du lịch. Ngo&agrave;i ra, ch&uacute;ng ta cần đầu tư về quản l&yacute; r&aacute;c thải, chất thải rắn hay cho sự an to&agrave;n n&oacute;i chung của du kh&aacute;ch, đầu tư v&agrave;o kỹ năng của nh&acirc;n vi&ecirc;n l&agrave;m dịch vụ du lịch&rdquo;, &ocirc;ng Amed Eiweida cho biết.</p>\r\n\r\n<p>Về cải thiện m&ocirc;i trường, theo &ocirc;ng Eiweida, WB đ&atilde; hỗ trợ những th&agrave;nh phố như Nha Trang, TPHCM cải thiện nước thải, chất thải để ch&uacute;ng ta quản l&yacute;, xử l&yacute; chất thải một c&aacute;ch tốt hơn chứ kh&ocirc;ng đổ chất thải trực tiếp ra m&ocirc;i trường. Những sự đầu tư như vậy cũng sẽ hỗ trợ giảm chi ph&iacute; cho khu vực tự nh&acirc;n, tức l&agrave; khi họ đầu tư v&agrave;o những cơ sở du lịch th&igrave; họ sẽ kh&ocirc;ng cần bỏ chi ph&iacute; để xử l&yacute; m&ocirc;i trường nữa.</p>\r\n\r\n<p>&ldquo;Ch&uacute;ng t&ocirc;i đang đầu tư tr&ecirc;n to&agrave;n bộ đất nước Việt Nam chứ kh&ocirc;ng phải chỉ một v&agrave;i địa điểm. Tất nhi&ecirc;n, b&ecirc;n cạnh đấy, ch&uacute;ng t&ocirc;i cũng đầu tư một số lĩnh vực kh&aacute;c nữa như t&iacute;nh kết nối, đường x&aacute; đi lại, cơ sở hạ tầng, khoa học, CNTT. Tất cả những sự đầu tư n&agrave;y l&agrave; để gi&uacute;p cho Việt Nam c&oacute; thể mở cửa một c&aacute;ch an to&agrave;n, gi&uacute;p Việt Nam l&agrave; một điểm đến du lịch an to&agrave;n, xanh, sạch&rdquo;, &ocirc;ng Eiweida b&agrave;y tỏ.</p>\r\n\r\n<p><strong>&nbsp;Vũ Phong</strong></p>\r\n',
        '(Chinhphu.vn) - Theo các chuyên gia quốc tế, bên cạnh những thách thức do đại dịch COVID-19 gây ra thì đây chính ra cơ hội để cho ngành du lịch cùng nhìn lại mình và xây dựng một chiến lược du lịch mang tính thực tế hơn và bền vững hơn.',
        'http://res.cloudinary.com/jstit/image/upload/v1621271691/9tour/post/zg5wjbd9inshvdti2phx.jpg',
        'Kỳ 5: Du lịch hậu COVID-19: Cơ hội để phát triển bền vững', 2),
       (3, NULL, 3, '2021-05-18 00:15:51', _binary '\0', 17, '2021-06-25 02:47:58',
        '<p>(Chinhphu.vn) -&nbsp; Sau đợt dịch COVID-19 lần thứ 2, c&oacute; thể n&oacute;i, cả ng&agrave;nh du lịch Việt đ&atilde; &ldquo;ngấm đ&ograve;n&rdquo;. Thời điểm n&agrave;y, to&agrave;n ng&agrave;nh đang rục rịch khởi động chương tr&igrave;nh k&iacute;ch cầu du lịch nội địa giai đoạn 2. Nhiều chuy&ecirc;n gia nhận định, đ&acirc;y sẽ l&agrave; &ldquo;đ&ograve;n bẩy&rdquo; gi&uacute;p phục hồi ng&agrave;nh &ldquo;c&ocirc;ng nghiệp kh&ocirc;ng kh&oacute;i&rdquo; hậu COVID-19.</p>\r\n\r\n<table align=\"center\">\r\n	<tbody>\r\n		<tr>\r\n			<td><img src=\"http://baochinhphu.vn/Uploaded/tranthitiep/2020_10_16/Du%20lich03%20Da%20Nang.jpg?maxwidth=460\" /></td>\r\n		</tr>\r\n		<tr>\r\n			<td>Ng&agrave;nh du lịch chịu ảnh hưởng nặng nề v&igrave; đại dịch COVID-19. Ảnh: VGP/Lưu Hương</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n\r\n<p>Du lịch l&agrave; một trong những ng&agrave;nh bị ảnh hưởng nặng nề nhất bởi đại dịch COVID-19. Đại dịch cho ch&uacute;ng ta nhiều b&agrave;i học về x&acirc;y dựng quỹ dự ph&ograve;ng khủng hoảng, về định hướng ph&aacute;t triển &ldquo;đi cả bằng hai ch&acirc;n&rdquo;, ph&aacute;t triển thị trường du lịch quốc tế v&agrave; nội địa. Để phục hồi ng&agrave;nh du lịch trong bối cảnh hiện tại, trước mắt của ch&uacute;ng ta l&agrave; phải tập trung cho thị trường du lịch nội địa với c&aacute;c sản phẩm hấp dẫn, độc đ&aacute;o.</p>\r\n\r\n<p>Đ&acirc;y l&agrave; &yacute; kiến của PGS.TS. Phạm Hồng Long - Trưởng Khoa Du lịch học, Trường Đại học KHXH&amp;NV, Đại học Quốc gia H&agrave; Nội.</p>\r\n\r\n<p><strong>Đại dịch COVID-19 gi&aacute;ng &ldquo;đ&ograve;n ch&iacute; mạng&rdquo; l&ecirc;n ng&agrave;nh du lịch</strong></p>\r\n\r\n<p>Ng&agrave;y 11/3/2020, Tổ chức Y tế Thế giới (WHO) ch&iacute;nh thức c&ocirc;ng bố dịch COVID-19 do virus Corona chủng mới (SARS-CoV-2) l&agrave; đại dịch to&agrave;n cầu. Ng&agrave;nh du lịch l&agrave; ng&agrave;nh chịu t&aacute;c động nghi&ecirc;m trọng nhất do lượng du kh&aacute;ch từ nước ngo&agrave;i, cũng như du lịch nội địa bị hạn chế do lo ngại sự l&acirc;y lan của dịch COVID-19.</p>\r\n\r\n<p>Theo b&aacute;o c&aacute;o của Vụ Lữ h&agrave;nh (Tổng cục Du lịch), số liệu thống k&ecirc; sơ bộ cho thấy khi dịch COVID-19 quay lại, đến nay đ&atilde; c&oacute; h&agrave;ng triệu kh&aacute;ch du lịch ho&atilde;n, hủy tour du lịch. Đơn cử, một số trung t&acirc;m như H&agrave; Nội c&oacute; khoảng 32.000 kh&aacute;ch hủy tour nội địa; TP. Hồ Ch&iacute; Minh c&oacute; tr&ecirc;n 35.000 chương tr&igrave;nh du lịch bao gồm tour trọn g&oacute;i, tour tự chọn, dịch vụ (kh&aacute;ch sạn, v&eacute; m&aacute;y bay, điểm tham quan&hellip;) đ&atilde; bị huỷ.</p>\r\n\r\n<p>Ri&ecirc;ng trong th&aacute;ng 8, hầu hết c&aacute;c doanh nghiệp kinh doanh kh&aacute;ch sạn, homestay x&aacute;c nhận tỉ lệ kh&aacute;ch huỷ ph&ograve;ng đến hơn 90%, c&ograve;n c&aacute;c đơn vị lữ h&agrave;nh cho biết hơn 80% kh&aacute;ch huỷ tour v&agrave; y&ecirc;u cầu ho&agrave;n lại tiền 100% do t&igrave;nh h&igrave;nh diễn biến bệnh qu&aacute; phức tạp&hellip;</p>\r\n\r\n<p>Cũng theo Tổng cục Du lịch, ng&agrave;nh du lịch Việt Nam thiệt hại trong &ldquo;khoảng từ 6 đến 7 tỷ USD&rdquo; trong 2 qu&yacute; đầu năm bởi ri&ecirc;ng du kh&aacute;ch Trung Quốc giảm 90% đến 100%. Ngo&agrave;i Trung Quốc, số lượng kh&aacute;ch từ c&aacute;c quốc gia kh&aacute;c nhập cảnh v&agrave;o Việt Nam cũng giảm mạnh.</p>\r\n\r\n<p>Sau đợt dịch COVID-19 lần thứ 2 quay trở lại, c&oacute; thể n&oacute;i, cả ng&agrave;nh du lịch Việt đ&atilde; gần như &ldquo;kiệt sức&rdquo;.</p>\r\n\r\n<p>PGS.TS. Phạm Hồng Long ph&acirc;n t&iacute;ch, con số thiệt hại ước t&iacute;nh tr&ecirc;n mới chủ yếu dựa tr&ecirc;n những dự b&aacute;o về số liệu thiếu hụt kh&aacute;ch nh&acirc;n với mức chi ti&ecirc;u b&igrave;nh qu&acirc;n, chứ chưa t&iacute;nh đến thiệt hại từ việc &ldquo;kiệt sức&rdquo; của c&aacute;c doanh nghiệp đang hoạt động trong lĩnh vực du lịch, ch&iacute;nh l&agrave; những đối tượng chịu t&aacute;c động mạnh nhất, lớn nhất hiện nay. Đ&oacute; l&agrave; c&aacute;c doanh nghiệp lữ h&agrave;nh, c&aacute;c c&ocirc;ng ty đầu tư hệ thống cơ sở vật chất kỹ thuật du lịch (cơ sở lưu tr&uacute; như c&aacute;c khu nghỉ dưỡng, kh&aacute;ch sạn, hệ thống dịch vụ nh&agrave; h&agrave;ng, khu vui chơi giải tr&iacute;,...). Đồng thời, ng&agrave;nh du lịch c&oacute; t&aacute;c động đa ng&agrave;nh, n&ecirc;n nếu ph&aacute;t triển mạnh th&igrave; c&oacute; thể k&eacute;o theo rất nhiều ng&agrave;nh nghề kh&aacute;c đi l&ecirc;n. Ngược lại, nếu du lịch &ldquo;hắt hơi&rdquo; th&igrave; c&aacute;c lĩnh vực kh&aacute;c sẽ &ldquo;sổ mũi&rdquo; theo.</p>\r\n\r\n<p>&ldquo;N&ecirc;n thiết nghĩ, sẽ kh&ocirc;ng thể t&iacute;nh được tất cả những thiệt hại của ng&agrave;nh du lịch v&agrave; chắc chắn sẽ vượt hơn nhiều con số 7 tỷ USD m&agrave; Tổng cục Du lịch ước t&iacute;nh. Kh&ocirc;ng những thế, con số thiệt hại n&agrave;y được đưa ra khi dịch COVID-19 mới chủ yếu ho&agrave;nh h&agrave;nh ở Trung Quốc (khoảng đầu th&aacute;ng 2), c&ograve;n nay n&oacute; đ&atilde; lan rộng th&agrave;nh đại dịch to&agrave;n cầu&rdquo;, &ocirc;ng Long n&oacute;i.</p>\r\n\r\n<p>Theo PGS.TS. Phạm Hồng Long, Việt Nam đ&atilde; ngưng vận chuyển h&agrave;ng kh&ocirc;ng (tạm mở lại một số đường bay từ ng&agrave;y 15/9/2020) v&agrave; tạm ngừng xuất nhập cảnh, th&igrave; t&aacute;c động v&agrave; thiệt hại của ng&agrave;nh du lịch c&ograve;n lớn hơn rất nhiều. Theo khảo s&aacute;t của Ban Nghi&ecirc;n cứu Ph&aacute;t triển Kinh tế tư nh&acirc;n Việt Nam (thuộc Hội đồng tư vấn cải c&aacute;ch thủ tục h&agrave;nh ch&iacute;nh của Thủ tướng Ch&iacute;nh phủ), nếu COVID-19 k&eacute;o d&agrave;i 6 th&aacute;ng, 74% doanh nghiệp du lịch sẽ ph&aacute; sản. Tr&ecirc;n b&igrave;nh diện thế giới, theo dữ liệu mới nhất của Tổ chức Du lịch Thế giới (UNWTO), lượng kh&aacute;ch du lịch quốc tế giảm 65% trong 6 th&aacute;ng đầu năm 2020, ng&agrave;nh du lịch mất đi 440 triệu lượt kh&aacute;ch quốc tế v&agrave; khoảng 460 tỷ USD doanh thu từ du lịch quốc tế.</p>\r\n\r\n<p>&Ocirc;ng Nguyễn Xu&acirc;n B&igrave;nh, Ph&oacute; Gi&aacute;m đốc Sở du lịch TP. Đ&agrave; Nẵng chia sẻ, Đ&agrave; Nẵng l&agrave; địa phương chịu ảnh hưởng nặng nề nhất sau đợt dịch COVID-19 lần thứ hai. L&agrave; t&acirc;m dịch, du lịch Đ&agrave; Nẵng gần như đ&oacute;ng băng.</p>\r\n\r\n<p>Bắt đầu từ 28/7, thực hiện chỉ đạo của Thủ tướng Ch&iacute;nh phủ về việc tạm dừng mọi phương tiện vận chuyển đến v&agrave; đi từ Đ&agrave; Nẵng để ph&ograve;ng chống dịch bệnh COVID-19, to&agrave;n bộ c&aacute;c doanh nghiệp du lịch gồm 398 đơn vị kinh doanh lữ h&agrave;nh, 16 khu, điểm du lịch, 955/1080 cơ sở lưu tr&uacute;, 350 đơn vị vận chuyển, 27 t&agrave;u du lịch đ&atilde; tạm dừng hoạt động kinh doanh.</p>\r\n\r\n<p>Một số cơ sở lưu tr&uacute; đ&atilde; thực hiện thủ tục mua b&aacute;n, chuyển nhượng bất động sản nghỉ dưỡng, kh&aacute;ch sạn, căn hộ. Theo th&ocirc;ng tin sơ bộ vừa qua, tr&ecirc;n địa b&agrave;n th&agrave;nh phố Đ&agrave; Nẵng c&oacute; khoảng 250 đến 260 kh&aacute;ch sạn/căn hộ/biệt thự đang rao b&aacute;n, chiếm tỷ lệ 24.7% tổng số kh&aacute;ch sạn (1080 kh&aacute;ch sạn).</p>\r\n\r\n<p>Dự kiến năm 2020, ước thiệt hại tổng thu của cả ng&agrave;nh du lịch th&agrave;nh phố Đ&agrave; Nẵng khoảng 26 ngh&igrave;n tỷ đồng, trong đ&oacute;, ước tổng thiệt hại (trực tiếp) tại c&aacute;c doanh nghiệp lữ h&agrave;nh khoảng 659 tỷ đồng, tại c&aacute;c cơ sở lưu tr&uacute; du lịch khoảng 4.800 tỷ đồng, tại doanh nghiệp vận chuyển du lịch khoảng 518 tỷ đồng v&agrave; c&aacute;c khu, điểm du lịch khoảng 827 tỷ đồng. Tổng số lao động tạm ngừng việc, nghỉ việc từ đầu th&aacute;ng 8/2020 đến nay ước khoảng 31.874 người/50.963 người, chiếm 62.5% tổng số lao động.</p>\r\n\r\n<p>Với sự t&aacute;c động mạnh mẽ của dịch bệnh COVID-19 đợt 2, dự kiến cả năm 2020, tổng lượt kh&aacute;ch đến tham quan, du lịch Đ&agrave; Nẵng ước đạt 2,7 triệu lượt, giảm 68,6% so với năm 2019, trong đ&oacute; kh&aacute;ch quốc tế ước đạt hơn 686 ngh&igrave;n lượt, giảm 80,5% so với năm 2019, kh&aacute;ch nội địa ước đạt hơn 2,03 triệu lượt, giảm 60,6% so với năm 2019. Tổng thu du lịch ước đạt 10.788 tỷ đồng, giảm 65,1% so với năm 2019.</p>\r\n\r\n<p>C&oacute; thể n&oacute;i, Đại dịch COVID-19 đ&atilde; gi&aacute;ng một đ&ograve;n ch&iacute; mạng l&ecirc;n ng&agrave;nh du lịch Việt Nam v&agrave; thế giới. Thời điểm n&agrave;y, x&aacute;c định bao giờ để du lịch Việt Nam v&agrave; thế giới mở cửa trở lại b&igrave;nh thường như trước vẫn l&agrave; một dấu hỏi rất lớn, bởi điều đ&oacute; chỉ c&oacute; thể xảy ra khi n&agrave;o t&igrave;m được vaccine ph&ograve;ng ngừa căn bệnh n&agrave;y hoặc khi c&aacute;c nước dần khống chế được đại dịch như Việt Nam.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<table align=\"center\">\r\n	<tbody>\r\n		<tr>\r\n			<td><img src=\"http://baochinhphu.vn/Uploaded/tranthitiep/2020_10_16/dulichhn.jpg?maxwidth=460\" /></td>\r\n		</tr>\r\n		<tr>\r\n			<td>Nhiều b&agrave;i học về phục hồi du lịch được r&uacute;t ra sau đại dịch COVID-19. Ảnh: VGP/Tuấn Trần</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Ph&aacute;t triển du lịch &ndash; phải &ldquo;đi bằng cả hai ch&acirc;n&rdquo;</strong></p>\r\n\r\n<p>Theo PGS.TS. Phạm Hồng Long, đại dịch COVID-19 cho ng&agrave;nh du lịch b&agrave;i học về sự c&acirc;n bằng giữa thị trường du lịch trong nước v&agrave; quốc tế; b&agrave;i học về việc mở rộng, đa dạng thị trường kh&aacute;ch; b&agrave;i học về x&acirc;y dựng quỹ dự ph&ograve;ng khủng hoảng;&nbsp;b&agrave;i học về li&ecirc;n kết hợp t&aacute;c trong việc x&acirc;y dựng v&agrave; ph&aacute;t triển sản phẩm du lịch; b&agrave;i học về biến th&aacute;ch thức th&agrave;nh cơ hội... Nhưng khi bước đầu khống chế được dịch, th&igrave; đ&oacute; cũng l&agrave; thời điểm để du lịch nội địa ph&aacute;t triển, do vậy mọi ch&iacute;nh s&aacute;ch v&agrave; h&agrave;nh động của ng&agrave;nh du lịch sẽ phải tập trung cho thị trường du lịch nội địa. L&agrave;m tốt du lịch nội địa, ng&agrave;nh du lịch Việt Nam cũng sẽ tạo th&ecirc;m được dấu ấn cho thị trường du lịch quốc tế biết đến.</p>\r\n\r\n<p>Cũng cần nh&igrave;n nhận, sự hồi phục của ng&agrave;nh du lịch nội địa sẽ k&eacute;o theo sự phục hồi của nhiều ng&agrave;nh kinh tế kh&aacute;c, v&igrave; bản th&acirc;n ng&agrave;nh du lịch đ&atilde; l&agrave; một ng&agrave;nh kinh tế tổng hợp, n&oacute; li&ecirc;n quan đến h&agrave;ng kh&ocirc;ng, thương mại, t&agrave;i ch&iacute;nh &ndash; ng&acirc;n h&agrave;ng, n&ocirc;ng nghiệp, ngư nghiệp, giao th&ocirc;ng vận tải&hellip; Hiện tại, do ảnh hưởng của dịch n&ecirc;n nhiều cơ sở lưu tr&uacute; phải đ&oacute;ng cửa, nhất l&agrave; c&aacute;c cơ sở lưu tr&uacute; lớn- vốn l&acirc;u nay hay d&agrave;nh để phục vụ kh&aacute;ch du lịch cao cấp người Việt, hoặc l&agrave; kh&aacute;ch nước ngo&agrave;i. Khi thực hiện k&iacute;ch cầu du lịch nội địa th&igrave; kh&aacute;ch du lịch Việt với mức chi ph&iacute; trung b&igrave;nh kh&aacute; cũng c&oacute; thể nghỉ ở những cơ sở lưu tr&uacute; hạng sang. C&aacute;c cơ sở vui chơi giải tr&iacute;, c&aacute;c hoạt động mua sắm đều được k&iacute;ch hoạt. Ngo&agrave;i ra, việc k&iacute;ch cầu du lịch nội địa mang lại nhiều &yacute; nghĩa x&atilde; hội kh&aacute;c, như tạo c&ocirc;ng ăn việc l&agrave;m cho một lượng nhất định người lao động; du lịch nội địa cũng g&oacute;p phần l&agrave;m tăng th&ecirc;m niềm tự h&agrave;o của người Việt - thế giới cũng biết đến Việt Nam l&agrave; một điểm đến an to&agrave;n; mang lại c&aacute;c gi&aacute; trị trải nghiệm giao lưu văn h&oacute;a v&ugrave;ng miền&hellip;</p>\r\n\r\n<p>L&acirc;u nay, ch&uacute;ng ta hay xem trọng thị trường quốc tế v&agrave; đ&uacute;ng l&agrave; du lịch quốc tế l&agrave; một hợp phần rất quan trọng của ng&agrave;nh du lịch Việt Nam. Nhưng thực tế cho thấy, kh&aacute;ch du lịch người Việt đi du lịch v&agrave; ti&ecirc;u tiền kh&ocirc;ng phải &iacute;t v&agrave; lượng kh&aacute;ch nội chiếm số lượng lớn, gấp 4 đến 5 lần kh&aacute;ch du lịch quốc tế. Trong bối cảnh Việt Nam đ&atilde; tương đối an to&agrave;n, việc k&iacute;ch cầu du lịch nội địa l&agrave; giải ph&aacute;p gi&uacute;p ng&agrave;nh du lịch Việt Nam phục hồi &iacute;t nhiều, chứ chưa thể n&oacute;i l&agrave; phục hồi ho&agrave;n to&agrave;n.</p>\r\n\r\n<p>PGS.TS. Phạm Hồng Long cho rằng, về l&acirc;u d&agrave;i cần phải &ldquo;đi bằng cả hai ch&acirc;n&rdquo; l&agrave; du lịch nội địa v&agrave; du lịch quốc tế. Do vậy, giải ph&aacute;p th&uacute;c đẩy du lịch quốc tế cũng phải được ng&agrave;nh du lịch, c&aacute;c ng&agrave;nh v&agrave; doanh nghiệp li&ecirc;n quan xem x&eacute;t. Ch&uacute;ng ta b&agrave;n nhiều đến c&acirc;u chuyện tại sao kh&aacute;ch du lịch đến Việt Nam chưa nhiều, v&igrave; c&ograve;n gặp nhiều kh&oacute; khăn: Về thị thực hạn chế cho c&aacute;c nước, về số ng&agrave;y miễn ph&iacute; ghi trong thị thực c&ograve;n hạn chế, rồi về kinh ph&iacute; đầu tư cho c&ocirc;ng t&aacute;c x&uacute;c tiến quảng b&aacute;, về sản phẩm du lịch chưa đa dạng v&agrave; chất lượng dịch vụ chưa tốt, t&igrave;nh trạng ch&egrave;o k&eacute;o, bu&ocirc;n b&aacute;n chộp giật với kh&aacute;ch nước ngo&agrave;i&hellip;</p>\r\n\r\n<p>Thời điểm n&agrave;y cũng l&agrave; thời điểm ch&uacute;ng ta phải nghĩ tới c&aacute;c giải ph&aacute;p để th&aacute;o gỡ những kh&oacute; khăn đ&oacute;, x&oacute;a đi c&aacute;c r&agrave;o cản để sau khi mở cửa đ&oacute;n kh&aacute;ch du lịch quốc tế trở lại, ch&uacute;ng ta giải quyết triệt để những kh&oacute; khăn n&ecirc;u tr&ecirc;n. Hướng đến một điểm đến Việt Nam cởi mở, an to&agrave;n v&agrave; th&acirc;n thiện.</p>\r\n\r\n<p><strong>Minh Anh &ndash; Lưu Hương</strong></p>\r\n',
        '(Chinhphu.vn) -  Sau đợt dịch COVID-19 lần thứ 2, có thể nói, cả ngành du lịch Việt đã “ngấm đòn”. Thời điểm này, toàn ngành đang rục rịch khởi động chương trình kích cầu du lịch nội địa giai đoạn 2.',
        'http://res.cloudinary.com/jstit/image/upload/v1624564078/9tour/post/ypkxkscr2cmwgkjcyqxn.jpg',
        'Phát triển thị trường du lịch sau COVID-19: Biến thách thức thành cơ hội', 2),
       (4, NULL, 3, '2021-05-18 00:16:24', _binary '\0', 3, '2021-05-29 22:47:35',
        '<p>Nếu như mỗi lần đến với Ph&uacute; Quốc mọi người sẽ ph&acirc;n v&acirc;n lựa chọn giữa đảo Bắc hay đảo Nam đề ở, th&igrave; trong chuyến đi lần n&agrave;y m&igrave;nh lại chọn đến một resort xinh đẹp nằm giữa đảo, chỉ c&aacute;ch 3km từ s&acirc;n bay l&agrave; Sol by Melia.&nbsp;</p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 1024px) 100vw, 1024px\" src=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-10.jpg?w=1024\" srcset=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-10.jpg?w=1024 1024w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-10.jpg?w=2048 2048w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-10.jpg?w=150 150w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-10.jpg?w=300 300w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-10.jpg?w=768 768w\" /></p>\r\n\r\n<p>Điểm ấn tượng đầu ti&ecirc;n khi bước ch&acirc;n đến đ&acirc;y l&agrave; sảnh lobby được d&ugrave;ng gỗ m&agrave;u beige, kết hợp c&ugrave;ng sofa, nệm m&agrave;u xanh bắt mắt, hiện đại.</p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-11.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-11.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-11.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-11.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-11.jpg?w=225 225w\" /></p>\r\n\r\n<p>Bước v&agrave;o b&ecirc;n trong l&agrave; chuỗi những ph&ograve;ng c&oacute; pool connect, một hồ bơi d&agrave;i trải d&agrave;i khắp c&aacute;c d&atilde;y ph&ograve;ng, để bạn chỉ cần ở ph&ograve;ng ở dưới l&agrave; c&oacute; thể tận hưởng hồ bơi cho ri&ecirc;ng m&igrave;nh.</p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-8.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-8.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-8.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-8.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-8.jpg?w=225 225w\" /></p>\r\n\r\n<p>Tất nhi&ecirc;n, nếu muốn một hồ bơi rộng r&atilde;i hơn th&igrave; cứ ra bể bơi ở giữa resort để tận hưởng, hoặc bước v&agrave;i bước l&agrave; đến bờ cắt trắng tuyệt vời.</p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-8.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-8.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-8.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-8.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-8.jpg?w=225 225w\" /></p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-12.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-12.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-12.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-12.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-12.jpg?w=225 225w\" /></p>\r\n\r\n<p>Trong ph&ograve;ng deluxe pool connect của m&igrave;nh được trang tr&iacute; tho&aacute;ng đ&atilde;ng, rộng r&atilde;i ước chừng phải 35m2. Ph&ograve;ng tắm c&oacute; bồn cực rộng, l&agrave;m cho m&igrave;nh c&oacute; cảm tưởng c&oacute; thể ng&acirc;m nước 24/7, từ bồn tắm, xuống vể bơi trước ph&ograve;ng, xong xuống bể giữa, rồi đi xuống biển.&nbsp;</p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-5.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-5.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-5.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-5.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-5.jpg?w=225 225w\" /></p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 1024px) 100vw, 1024px\" src=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-1.jpg?w=1024\" srcset=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-1.jpg?w=1024 1024w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-1.jpg?w=2048 2048w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-1.jpg?w=150 150w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-1.jpg?w=300 300w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-1.jpg?w=768 768w\" /></p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-4.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-4.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-4.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-4.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-4.jpg?w=225 225w\" /></p>\r\n\r\n<p>Điểm m&igrave;nh th&iacute;ch nữa ch&iacute;nh l&agrave; ẩm thực của kh&aacute;ch sạn với &acirc;m hưởng T&acirc;y Ban Nha, vốn l&agrave; ẩm thực Ch&acirc;u &Acirc;u y&ecirc;u th&iacute;ch của m&igrave;nh.</p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-3.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-3.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-3.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-3.jpg?w=112 112w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-3.jpg?w=225 225w\" /></p>\r\n\r\n<p>Nếu như đi cặp đ&ocirc;i, bạn c&oacute; thể nhờ resort bố tr&iacute; một bữa tối l&atilde;ng mạn tr&ecirc;n c&aacute;t ngay bờ biển như m&igrave;nh để trải nghiệm, c&ugrave;ng set menu với c&aacute; biển được bắt mỗi ng&agrave;y.&nbsp;</p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 576px) 100vw, 576px\" src=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-2.jpg?w=576\" srcset=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-2.jpg?w=576 576w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-2.jpg?w=1152 1152w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-2.jpg?w=84 84w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-2.jpg?w=169 169w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-2.jpg?w=768 768w\" /></p>\r\n\r\n<p>Kh&ocirc;ng gian nh&agrave; h&agrave;ng b&ecirc;n bờ biển cũng thật tuyệt để ngồi nh&acirc;m nhi ly tequila sunrise, tận hưởng ho&agrave;ng h&ocirc;n của Ph&uacute; Quốc cho m&ugrave;a h&egrave; của bạn.</p>\r\n\r\n<p>Gi&aacute; ph&ograve;ng: 1,300,000 &ndash; 1,600,000 VNĐ&nbsp;</p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-6.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-6.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-6.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-6.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-6.jpg?w=225 225w\" /></p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-9.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/04/sol-by-melia-9.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-9.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-9.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/04/sol-by-melia-9.jpg?w=225 225w\" /></p>\r\n',
        'Nếu như mỗi lần đến với Phú Quốc mọi người sẽ phân vân lựa chọn giữa đảo Bắc hay đảo Nam đề ở, thì trong chuyến đi lần này mình lại chọn đến một resort xinh đẹp nằm giữa đảo, chỉ cách 3km từ sân bay là Sol by Melia.',
        'http://res.cloudinary.com/jstit/image/upload/v1622303254/9tour/post/rlcpf0ncjnjr7cca1vq3.jpg',
        'SOL BY MELIA, MỘT RESORT XINH ĐẸP GIỮA PHÚ QUỐC', 1),
       (5, NULL, 3, '2021-05-18 13:53:37', _binary '\0', 3, '2021-05-29 22:52:19',
        '<p>Đến qu&aacute;n trong những ng&agrave;y oi ả ở Đ&agrave; Lạt, nắng chiếu tr&ecirc;n đầu chỉ mới 10 giờ. Thật sự kh&oacute; t&igrave;m v&igrave; qu&aacute;n nằm ở một ng&atilde; 3 những con dốc n&ecirc;n dễ lạc.</p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i.jpg?w=225 225w\" /></p>\r\n\r\n<p>Nhưng kh&ocirc;ng v&igrave; vậy m&agrave; qu&aacute;n l&agrave;m m&igrave;nh cảm thấy ngột ngạt, ngược lại c&ograve;n thấy xứng đ&aacute;ng khi gh&eacute; đến đ&acirc;y.</p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-3.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-3.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-3.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-3.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-3.jpg?w=225 225w\" /></p>\r\n\r\n<p>Một ph&iacute;a ban c&ocirc;ng nh&igrave;n ra triền đồi với những ng&ocirc;i nh&agrave; tr&ecirc;n phố n&uacute;i. Những c&acirc;y xanh bao quanh qu&aacute;n đẹp v&agrave; m&aacute;t mẻ.</p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-4.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-4.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-4.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-4.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-4.jpg?w=225 225w\" /></p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-2.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-2.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-2.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-2.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-2.jpg?w=225 225w\" /></p>\r\n\r\n<p>Điều l&agrave;m m&igrave;nh tấm tắc nhất lại ch&iacute;nh l&agrave; những ly c&agrave; ph&ecirc; arabica ngon. M&igrave;nh gọi cold brew, vị chua chua nh&egrave; nhẹ khiến m&igrave;nh ưng &yacute;. Nhưng khi uống k&eacute; ly c&agrave; ph&ecirc; sữa th&igrave; c&ograve;n thấy tuyệt hơn v&igrave; qu&aacute; qu&aacute; ngon. Thật sự đến đ&acirc;y chủ yếu v&igrave; ảnh, nhưng c&agrave; ph&ecirc; ngon lại khiến m&igrave;nh muốn quay lại đến 10 lần.</p>\r\n\r\n<p><img alt=\"\" sizes=\"(max-width: 768px) 100vw, 768px\" src=\"https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-1.jpg?w=768\" srcset=\"https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-1.jpg?w=768 768w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-1.jpg?w=1536 1536w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-1.jpg?w=113 113w, https://venturology.files.wordpress.com/2021/03/phicc81a-tacc82y-macca3cc86t-trocc9bcc80i-1.jpg?w=225 225w\" /></p>\r\n\r\n<p>Nếu bạn chưa từng đến đ&acirc;y khi gh&eacute; Đ&agrave; Lạt, thử xem nh&eacute;.</p>\r\n\r\n<p>Gi&aacute;: 50,000-80,000VND/người<br />\r\nĐịa chỉ: 12/6 L&ecirc; Văn T&aacute;m, Đ&agrave; Lạt</p>\r\n',
        'Đến quán trong những ngày oi ả ở Đà Lạt, nắng chiếu trên đầu chỉ mới 10 giờ. Thật sự khó tìm vì quán nằm ở một ngã 3 những con dốc nên dễ lạc.',
        'http://res.cloudinary.com/jstit/image/upload/v1622303539/9tour/post/rgfplzkvu7nntiljd2kn.jpg',
        'TIỆM CÀ PHÊ PHÍA TÂY MẶT TRỜI Ở ĐÀ LẠT', 1),
       (6, NULL, 17, '2021-06-25 02:32:57', _binary '\0', 17, '2021-06-25 02:34:07',
        '<p>Cứ mỗi lần ra nh&agrave; thăm nh&agrave; tui lại gh&eacute; Đ&agrave; Nẵng- Hội An chơi 1,2 ng&agrave;y rồi mới chịu l&ecirc;n chuyến t&agrave;u chiều ra Quảng Trị. Hai điểm n&agrave;y chỉ l&agrave; điểm trung chuyển n&ecirc;n thời gian ở lại kh&aacute; &iacute;t thường th&igrave; sẽ ở nh&agrave; bạn hoặc homestay của bạn. Nhưng h&ocirc;m rồi tui tự thưởng cho m&igrave;nh 1 đ&ecirc;m ở kh&aacute;ch sạn n&agrave;o đ&oacute; sang chảnh tẹo. Thế l&agrave; d&ograve; hỏi ngay 1 số người bạn nhưng kh&aacute;ch sạn hay resort đẹp dễ thương ở ĐN thường 1 l&agrave; hết ph&ograve;ng hoặc gi&aacute; cao ngất tr&ecirc;n trời kh&ocirc;ng với tới được.<br />\r\nSau một qu&aacute; tr&igrave;nh t&igrave;m kiếm c&aacute;c trang booking tui quyết định chọ LASENTA sau khi được bạn giới thiệu v&agrave; xem review tr&ecirc;n booking.com ( số điểm review tr&ecirc;n 9 điểm).</p>\r\n\r\n<p>Qua nh&agrave; đứa em mượn con xe c&agrave; t&agrave;ng vượt 25km c&ugrave;ng 2 c&aacute;i balo to đ&ugrave;ng đi v&agrave;o Hội An nhắm hướng kh&aacute;ch sạn m&agrave; chạy. Tui 1 thằng d&eacute;p l&ecirc;, quần &aacute;o th&igrave; l&ocirc;i th&ocirc;i đ&acirc;m thẳng v&agrave;o cổng kh&aacute;ch sạn, kh&aacute;ch b&igrave;nh thường người ta biết ngay tới nhận ph&ograve;ng c&ograve;n tui th&igrave; được 3 người hỏi: &ldquo;anh tới đ&acirc;y l&agrave;m g&igrave; ? &ldquo;. Với c&aacute;i bộ dạng của n&agrave;y th&igrave; tui biết chắc chắn sẽ bị hỏi, n&ecirc;n cũng thấy b&igrave;nh thường v&agrave; trả lời lia lịa &ldquo;dạ em tới nhận ph&ograve;ng, dạ em tới nhận ph&ograve;ng, dạ em tới nhận ph&ograve;ng&hellip;&rdquo;.</p>\r\n\r\n<p>Bước v&agrave;o c&aacute;c bạn lễ t&acirc;n th&igrave; kh&aacute;c hẳn cười n&oacute;i duy&ecirc;n dễ sợ, cho check in hướng dẫn c&aacute;c thủ tục cần thiết cuối c&ugrave;ng cũng nhận được ph&ograve;ng view phố kh&aacute; l&agrave; ok. Ph&ograve;ng rộng tolet rộng sạch sẽ c&aacute;i n&agrave;y th&igrave; mặc nhi&ecirc;u rồi v&igrave; theo ti&ecirc;u chuẩn hết.<br />\r\nDuy chỉ c&oacute; c&aacute;i hồ bơi cực kỳ đ&atilde; nằm ở tầm 4 ng&agrave;y tầng tui ở, vừa bơi vừa ngắm cả 1 c&aacute;nh đồng l&uacute;a xanh ng&aacute;t th&iacute;ch ơi l&agrave; th&iacute;ch, hồ nhỏ th&iacute;ch hợp bơi thư giản uống vang v&agrave; ngắm l&uacute;a. Kh&aacute;ch sạn l&aacute;y tone m&agrave;u trắng kem l&agrave;m m&agrave;u chủ đạo n&ecirc;n nh&igrave;n s&aacute;ng, xung quanh l&agrave; ph&ograve;ng ở giữa l&agrave; 1 khoảng s&acirc;n rộng c&oacute; để b&agrave;n ghế ngồi cafe hoặc n&oacute;i chuyện với bạn b&egrave; hợp l&yacute; lắm n&egrave;.<br />\r\nChốt lại:<br />\r\n&ndash; Nh&acirc;n vi&ecirc;n th&acirc;n thiện<br />\r\n&ndash; Đồ ăn s&aacute;ng ngon, phong ph&uacute;<br />\r\n&ndash; Gi&aacute; ph&ograve;ng kh&ocirc;ng qu&aacute; cao<br />\r\n&ndash; Kh&aacute;ch sạn đẹp rất th&iacute;ch hợp cho ai sống ảo<br />\r\n&ndash; Ph&ugrave; hợp cho t&igrave;nh nh&acirc;n hoặc gia đ&igrave;nh, chứ solo như tui th&igrave; chỉ c&oacute; bơi, uống beer ăn rồi ngủ haha</p>\r\n',
        'Cứ mỗi lần ra nhà thăm nhà tui lại ghé Đà Nẵng- Hội An chơi 1,2 ngày rồi mới chịu lên chuyến tàu chiều ra Quảng Trị. Hai điểm này chỉ là điểm trung chuyển nên thời gian ở lại khá ít thường thì sẽ ở nhà bạn hoặc homestay của bạn',
        'http://res.cloudinary.com/jstit/image/upload/v1624563176/9tour/post/hwg8bmslebvq1g2fwpfs.png',
        'Review khách sạn LASENTA- du lịch Hội An', 3);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `code`          varchar(255) DEFAULT NULL,
    `created_by`    bigint       DEFAULT NULL,
    `created_date`  datetime     DEFAULT NULL,
    `is_deleted`    bit(1)       DEFAULT NULL,
    `modified_by`   bigint       DEFAULT NULL,
    `modified_date` datetime     DEFAULT NULL,
    `comment`       longtext,
    `rating`        int          DEFAULT NULL,
    `ticket_id`     bigint       DEFAULT NULL,
    `user_id`       bigint       DEFAULT NULL,
    `tour_id`       bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_sh7qu6hhukit929u91iremdg1` (`code`),
    KEY             `FK5cvopymdsfldp4sqdhf17auc3` (`ticket_id`),
    KEY             `FK5198jys120os2n6sbckipegm5` (`tour_id`),
    KEY             `FKpn05vbx6usw0c65tcyuce4dw5` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK
TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `refund_request`
--

DROP TABLE IF EXISTS `refund_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refund_request`
(
    `id`             bigint NOT NULL AUTO_INCREMENT,
    `code`           varchar(255) DEFAULT NULL,
    `created_by`     bigint       DEFAULT NULL,
    `created_date`   datetime     DEFAULT NULL,
    `is_deleted`     bit(1)       DEFAULT NULL,
    `modified_by`    bigint       DEFAULT NULL,
    `modified_date`  datetime     DEFAULT NULL,
    `contact_mail`   varchar(255) DEFAULT NULL,
    `contact_phone`  varchar(255) DEFAULT NULL,
    `note`           longtext,
    `receiver`       varchar(255) DEFAULT NULL,
    `request`        longtext,
    `response`       longtext,
    `sender`         varchar(255) DEFAULT NULL,
    `status`         varchar(255) DEFAULT NULL,
    `bill_ticket_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY              `FKsgpd7nbk11jch2kl4kvi3y343` (`bill_ticket_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund_request`
--

LOCK
TABLES `refund_request` WRITE;
/*!40000 ALTER TABLE `refund_request` DISABLE KEYS */;
INSERT INTO `refund_request`
VALUES (1, NULL, 5, '2021-06-17 22:45:22', _binary '\0', 5, '2021-06-17 22:45:22', 'tqt@gmail.com', '090909009', NULL,
        NULL, 'muốn hủy', NULL, 'tqt23456@gmail.com', 'waiting', 17),
       (2, NULL, 5, '2021-06-25 02:59:02', _binary '\0', 5, '2021-06-25 03:19:05', 'tqt@gmail.com', '0909090909', '',
        'tqt23456@gmail.com', 'muốn hủy', '', 'tqt23456@gmail.com', 'refunded', 8);
/*!40000 ALTER TABLE `refund_request` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region`
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `code`              varchar(255) DEFAULT NULL,
    `created_by`        bigint       DEFAULT NULL,
    `created_date`      datetime     DEFAULT NULL,
    `is_deleted`        bit(1)       DEFAULT NULL,
    `modified_by`       bigint       DEFAULT NULL,
    `modified_date`     datetime     DEFAULT NULL,
    `description`       longtext,
    `name`              varchar(255) DEFAULT NULL,
    `short_description` varchar(255) DEFAULT NULL,
    `thumbnail_url`     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_kntnlvx8xwbfrg1flw2mnwa9v` (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK
TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region`
VALUES (1, NULL, 3, '2021-05-18 14:19:36', _binary '\0', 3, '2021-05-18 14:19:36', NULL, 'Miền Bắc', 'mien-bac', NULL),
       (4, NULL, 3, '2021-05-18 23:09:52', _binary '\0', 3, '2021-05-18 23:09:59', NULL, 'Miền Trung', 'mien-trung',
        NULL),
       (3, NULL, 3, '2021-05-18 14:20:24', _binary '\0', 3, '2021-05-18 14:20:24', NULL, 'Miền Nam', 'mien-nam', NULL);
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role`
(
    `id`               bigint NOT NULL AUTO_INCREMENT,
    `code`             varchar(255) DEFAULT NULL,
    `created_by`       bigint       DEFAULT NULL,
    `created_date`     datetime     DEFAULT NULL,
    `is_deleted`       bit(1)       DEFAULT NULL,
    `modified_by`      bigint       DEFAULT NULL,
    `modified_date`    datetime     DEFAULT NULL,
    `permission_level` tinyint      DEFAULT NULL,
    `role_key`         varchar(255) DEFAULT NULL,
    `role_name`        varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_c36say97xydpmgigg38qv5l2p` (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK
TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role`
VALUES (1, NULL, NULL, NULL, _binary '\0', NULL, NULL, 127, 'SUPER_ADMIN', 'Quản trị viên'),
       (2, NULL, NULL, NULL, _binary '\0', NULL, NULL, 51, 'STAFF', 'Nhân viên'),
       (3, NULL, NULL, NULL, _binary '\0', NULL, NULL, 1, 'USER', 'Người dùng'),
       (4, NULL, NULL, NULL, _binary '\0', NULL, NULL, 101, 'ADMIN', 'Quản lý');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `subscriber`
--

DROP TABLE IF EXISTS `subscriber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscriber`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `code`          varchar(255) DEFAULT NULL,
    `created_by`    bigint       DEFAULT NULL,
    `created_date`  datetime     DEFAULT NULL,
    `is_deleted`    bit(1)       DEFAULT NULL,
    `modified_by`   bigint       DEFAULT NULL,
    `modified_date` datetime     DEFAULT NULL,
    `email`         varchar(255) DEFAULT NULL,
    `token`         varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriber`
--

LOCK
TABLES `subscriber` WRITE;
/*!40000 ALTER TABLE `subscriber` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriber` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `subscriber_advertise_topics`
--

DROP TABLE IF EXISTS `subscriber_advertise_topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subscriber_advertise_topics`
(
    `subscribers_id`      bigint NOT NULL,
    `advertise_topics_id` bigint NOT NULL,
    KEY                   `FK2jmpkpclwya6vxmhg2rdg4e3d` (`advertise_topics_id`),
    KEY                   `FK73u6ah4f71617ve44w0nd9rw0` (`subscribers_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriber_advertise_topics`
--

LOCK
TABLES `subscriber_advertise_topics` WRITE;
/*!40000 ALTER TABLE `subscriber_advertise_topics` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriber_advertise_topics` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket`
(
    `id`                     bigint NOT NULL AUTO_INCREMENT,
    `code`                   varchar(255) DEFAULT NULL,
    `created_by`             bigint       DEFAULT NULL,
    `created_date`           datetime     DEFAULT NULL,
    `is_deleted`             bit(1)       DEFAULT NULL,
    `modified_by`            bigint       DEFAULT NULL,
    `modified_date`          datetime     DEFAULT NULL,
    `customer_note`          varchar(255) DEFAULT NULL,
    `pickup_place`           varchar(255) DEFAULT NULL,
    `pickup_time`            datetime     DEFAULT NULL,
    `return_place`           varchar(255) DEFAULT NULL,
    `anonymous_user_id`      bigint       DEFAULT NULL,
    `contact_information_id` bigint       DEFAULT NULL,
    `user_id`                bigint       DEFAULT NULL,
    `address`                varchar(255) DEFAULT NULL,
    `email`                  varchar(255) DEFAULT NULL,
    `first_name`             varchar(255) DEFAULT NULL,
    `last_name`              varchar(255) DEFAULT NULL,
    `phone`                  varchar(255) DEFAULT NULL,
    `nationality`            varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_bxs74dmpyjw6rsd4xec3pfr0c` (`code`),
    KEY                      `FKhbjo09wicsutwcvvvt45674co` (`contact_information_id`),
    KEY                      `FKt1atb91nnqi3h05qrs59hxhqs` (`anonymous_user_id`),
    KEY                      `FKdvt57mcco3ogsosi97odw563o` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK
TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket`
VALUES (3, NULL, 0, '2021-06-06 23:00:55', _binary '\0', 0, '2021-06-06 23:00:55', NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'giusequtuan@gmail.com', 'Tran', 'Tuan',
        '0902781404', 'Việt Nam'),
       (4, NULL, 0, '2021-06-06 23:10:07', _binary '\0', 0, '2021-06-06 23:10:07', NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'giusequtuan@gmail.com', 'Tran', 'Tuan',
        '0902781404', 'Việt Nam'),
       (5, NULL, 0, '2021-06-07 00:37:15', _binary '\0', 0, '2021-06-07 00:37:15', NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'giusequtuan@gmail.com', 'Tran', 'Tuan',
        '0902781404', ''),
       (6, NULL, 0, '2021-06-07 20:20:20', _binary '\0', 0, '2021-06-07 20:20:20', NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'giusequtuan@gmail.com', 'Tran', 'Tuan',
        '0902781404', 'Việt Nam'),
       (7, NULL, 5, '2021-06-07 20:22:57', _binary '\0', 5, '2021-06-07 20:22:57', NULL, NULL, NULL, NULL, NULL, NULL,
        5, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', 'Trần',
        '0902781404', 'Việt Nam'),
       (8, NULL, 5, '2021-06-07 20:27:15', _binary '\0', 5, '2021-06-25 03:19:05', NULL, NULL, NULL, NULL, NULL, NULL,
        5, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', 'Trần',
        '0902781404', 'Việt Nam'),
       (9, NULL, 5, '2021-06-07 20:31:57', _binary '\0', 5, '2021-06-07 20:31:57', NULL, NULL, NULL, NULL, NULL, NULL,
        5, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', 'Trần',
        '0902781404', 'Việt Nam'),
       (10, NULL, 5, '2021-06-07 21:14:30', _binary '\0', 5, '2021-06-07 21:14:30', NULL, NULL, NULL, NULL, NULL, NULL,
        5, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', 'Trần',
        '0902781404', 'Việt Nam'),
       (11, NULL, 5, '2021-06-07 21:30:02', _binary '\0', 5, '2021-06-07 21:30:02', NULL, NULL, NULL, NULL, NULL, NULL,
        5, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', 'Trần',
        '0902781404', 'Việt Nam'),
       (12, NULL, 5, '2021-06-07 21:39:06', _binary '\0', 5, '2021-06-07 21:39:06', NULL, NULL, NULL, NULL, NULL, NULL,
        5, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', 'Trần',
        '0902781404', 'Việt Nam'),
       (13, NULL, 3, '2021-06-08 16:41:38', _binary '\0', 3, '2021-06-08 16:41:38', NULL, NULL, NULL, NULL, NULL, NULL,
        3, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'test2@gmail.com', 'Tuấn', 'Trần Quốc',
        '0902781404', ''),
       (14, NULL, 5, '2021-06-09 15:58:54', _binary '\0', 5, '2021-06-09 15:58:54', NULL, NULL, NULL, NULL, NULL, NULL,
        5, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', 'Trần',
        '0902781404', 'Việt Nam'),
       (15, NULL, 5, '2021-06-13 20:58:46', _binary '\0', 5, '2021-06-13 20:58:46', NULL, NULL, NULL, NULL, NULL, NULL,
        5, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', 'Trần',
        '0902781404', 'Việt Nam'),
       (16, NULL, 5, '2021-06-13 22:40:30', _binary '\0', 5, '2021-06-13 22:40:30', NULL, NULL, NULL, NULL, NULL, NULL,
        5, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', 'Trần',
        '0902781404', 'Việt Nam'),
       (17, NULL, 5, '2021-06-17 21:28:56', _binary '\0', 5, '2021-06-17 21:28:56', NULL, NULL, NULL, NULL, NULL, NULL,
        5, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', 'Trần',
        '0902781404', 'Việt Nam'),
       (18, NULL, 5, '2021-06-25 03:21:47', _binary '\0', 5, '2021-06-25 03:21:47', NULL, NULL, NULL, NULL, NULL, NULL,
        5, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', 'Trần',
        '0902781404', 'Việt Nam'),
       (19, NULL, 5, '2021-06-25 03:21:50', _binary '\0', 5, '2021-06-25 03:21:50', NULL, NULL, NULL, NULL, NULL, NULL,
        5, '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', 'Trần',
        '0902781404', 'Việt Nam');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token`
(
    `id`             bigint NOT NULL AUTO_INCREMENT,
    `code`           varchar(255)  DEFAULT NULL,
    `created_by`     bigint        DEFAULT NULL,
    `created_date`   datetime      DEFAULT NULL,
    `is_deleted`     bit(1)        DEFAULT NULL,
    `modified_by`    bigint        DEFAULT NULL,
    `modified_date`  datetime      DEFAULT NULL,
    `token`          varchar(2048) DEFAULT NULL,
    `token_exp_date` datetime      DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_ibgcuaiv8i3op4ck6lg08jjxs` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK
TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tour`
--

DROP TABLE IF EXISTS `tour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour`
(
    `id`                bigint NOT NULL AUTO_INCREMENT,
    `code`              varchar(255) DEFAULT NULL,
    `created_by`        bigint       DEFAULT NULL,
    `created_date`      datetime     DEFAULT NULL,
    `is_deleted`        bit(1)       DEFAULT NULL,
    `modified_by`       bigint       DEFAULT NULL,
    `modified_date`     datetime     DEFAULT NULL,
    `departure`         varchar(255) DEFAULT NULL,
    `description`       longtext,
    `destination`       varchar(255) DEFAULT NULL,
    `during_time`       varchar(255) DEFAULT NULL,
    `faq`               longtext,
    `itinerary`         longtext,
    `name`              varchar(255) DEFAULT NULL,
    `short_description` varchar(255) DEFAULT NULL,
    `term_conditions`   longtext,
    `thumbnail_url`     varchar(255) DEFAULT NULL,
    `transport`         varchar(255) DEFAULT NULL,
    `departure_id`      bigint       DEFAULT NULL,
    `destination_id`    bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_1or90qldxxh4mt7h7peijufnf` (`code`),
    KEY                 `FK4dtppevm7g5y0sjpvwqb0wd5p` (`departure_id`),
    KEY                 `FKph585j7pssufbyjr9tbah3ol1` (`destination_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour`
--

LOCK
TABLES `tour` WRITE;
/*!40000 ALTER TABLE `tour` DISABLE KEYS */;
INSERT INTO `tour`
VALUES (1, NULL, 3, '2021-05-22 20:36:20', _binary '\0', 3, '2021-06-04 14:47:24', NULL,
        '<h3>N&eacute;t văn h&oacute;a Thủ đ&ocirc; ng&agrave;n năm văn hiến</h3>\r\n\r\n<p>H&agrave; Nội l&agrave; thủ đ&ocirc; của Việt Nam v&agrave; cũng l&agrave; một th&agrave;nh phố c&oacute; truyền thống, lịch sử v&agrave; văn h&oacute;a l&acirc;u đời. Khu phố cổ v&agrave; th&agrave;nh cổ thu h&uacute;t du kh&aacute;ch với vẻ cổ k&iacute;nh của c&aacute;c con phố nghề đặc trưng v&agrave; c&aacute;c di t&iacute;ch nổi bật như Văn Miếu &ndash; Quốc Tử Gi&aacute;m. Trong khi đ&oacute;, c&aacute;c c&ocirc;ng tr&igrave;nh mới hơn như Kh&aacute;ch sạn Sofitel Metropole in đậm dấu ấn thời k&igrave; Ph&aacute;p thuộc. Khu phố Ph&aacute;p c&oacute; Nh&agrave; H&aacute;t Lớn v&agrave; Ch&ugrave;a Một Cột, hai điểm tham quan nổi tiếng kh&ocirc;ng thể bỏ qua khi du lịch H&agrave; Nội. C&ugrave;ng iVIVU kh&aacute;m ph&aacute; th&ecirc;m mảnh đất Thủ đ&ocirc; ngay h&ocirc;m nay!</p>\r\n\r\n<h3>Những trải nghiệm th&uacute; vị trong chương tr&igrave;nh</h3>\r\n\r\n<p>Kh&aacute;m ph&aacute; H&agrave; Nội đừng n&ecirc;n bỏ qua...</p>\r\n\r\n<p>-&nbsp;<strong>Ch&ugrave;a Trấn Quốc:</strong>&nbsp;ng&ocirc;i ch&ugrave;a l&acirc;u đời nhất ở Thăng Long &ndash; H&agrave; Nội khi c&oacute; lịch sử l&ecirc;n đến 1500 năm, được trang Thrillist uy t&iacute;n b&igrave;nh chọn l&agrave; một trong những ng&ocirc;i ch&ugrave;a đẹp nhất thế giới.</p>\r\n\r\n<p>-&nbsp;<strong>Văn Miếu Quốc Tử Gi&aacute;m:</strong>&nbsp;ng&ocirc;i trường đại học đầu ti&ecirc;n của Việt Nam, kh&ocirc;ng chỉ l&agrave; di t&iacute;ch lịch sử nổi tiếng của thủ đ&ocirc; H&agrave; Nội m&agrave; c&ograve;n l&agrave; nơi chứa đựng những gi&aacute; trị tinh hoa văn h&oacute;a của những giai đoạn lịch sử phong kiến trước kia v&agrave; lưu giữ những gi&aacute; trị truyền thống của đất Việt.</p>\r\n\r\n<p>-&nbsp;<strong>L&agrave;ng gốm B&aacute;t Tr&agrave;ng:&nbsp;</strong>l&agrave;ng gốm l&acirc;u đời nổi tiếng nhất ở Việt Nam, cũng như l&agrave; địa điểm m&agrave; du kh&aacute;ch trong v&agrave; ngo&agrave;i nước kh&ocirc;ng thể kh&ocirc;ng một lần gh&eacute; thăm. L&agrave;ng gốm B&aacute;t Tr&agrave;ng chuy&ecirc;n sản xuất những loại gốm sứ đa dạng cả về chủng loại lẫn kiểu d&aacute;ng. Điều th&uacute; vị nhất khi đến B&aacute;t Tr&agrave;ng l&agrave; c&aacute;c bạn c&oacute; thể trực tiếp ngắm nh&igrave;n c&aacute;c nghệ nh&acirc;n l&agrave;m ra những sản phẩm gốm đầy tinh tế hay được tự tay nặn những sản phẩm theo &yacute; th&iacute;ch.</p>\r\n',
        NULL, '1 ngày', '',
        '<h3>Chương tr&igrave;nh tour</h3>\r\n\r\n<p><strong>S&aacute;ng: 7h45- 8h30</strong>&nbsp;Xe v&agrave; hướng dẫn vi&ecirc;n đ&oacute;n qu&yacute; kh&aacute;ch tại điểm hẹn. Sau đ&oacute; quy kh&aacute;ch tham quan&nbsp;<strong>ch&ugrave;a Trấn Quốc</strong>&nbsp;được x&acirc;y dựng c&aacute;ch đ&acirc;y 1000 năm.</p>\r\n\r\n<p><strong>9h40</strong>: Qu&yacute; kh&aacute;ch thăm&nbsp;<strong>khu di t&iacute;ch Hồ Ch&iacute; Minh</strong>&nbsp;(<strong>L</strong><strong>ăng Chủ Tịch HCM, nh&agrave; s&agrave;n, ao c&aacute;, ch&ugrave;a Một Cột</strong>).</p>\r\n\r\n<p><em>Ngoại trừ thứ 2 v&agrave; thứ 6 lăng kh&ocirc;ng mở cửa.</em></p>\r\n\r\n<p><em><img alt=\"\" src=\"https://cdn2.ivivu.com/2021/05/06/16/ivivu-lang-bac.gif\" /></em></p>\r\n\r\n<p><em>Lăng B&aacute;c.</em></p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2021/05/06/16/ivivu-chua-mot-cot.gif\" /></p>\r\n\r\n<p><em>Ch&ugrave;a Một Cột.</em></p>\r\n\r\n<p><strong>11h20</strong>: Qu&yacute; kh&aacute;ch thăm tiếp tục đi thăm&nbsp;<strong>Văn Miếu Quốc Tử Gi&aacute;m</strong>- trường đại học đầu ti&ecirc;n của&nbsp;<strong>Việt Nam.</strong></p>\r\n\r\n<p><strong><img alt=\"\" src=\"https://cdn2.ivivu.com/2021/05/06/16/ivivu-van-mieu.gif\" /></strong></p>\r\n\r\n<p><em>Văn Miếu Quốc Tử Gi&aacute;m.</em></p>\r\n\r\n<p>Sau đ&oacute; qu&yacute; kh&aacute;ch sẽ ăn trưa v&agrave; nghỉ ngơi tại nh&agrave; h&agrave;ng.</p>\r\n\r\n<p><strong>14h00-16h00</strong>: Qu&yacute; kh&aacute;ch tiếp tục kh&aacute;m ph&aacute; cuộc sống h&agrave;ng ng&agrave;y của nghề gốm truyền thống tại<strong>&nbsp;l&agrave;ng B&aacute;t Tr&agrave;ng</strong>&nbsp;tr&ecirc;n bờ s&ocirc;ng Hồng. Bạn sẽ biết r&otilde; hơn về lịch sử hơn 700 năm của l&agrave;ng, tham quan c&aacute;c ph&ograve;ng trưng b&agrave;y c&aacute;c sản phẩm gốm v&agrave; kết th&uacute;c tour.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2021/05/06/16/ivivu-lang-gom.gif\" /></p>\r\n\r\n<p><em>L&agrave;ng Gốm B&aacute;t Tr&agrave;ng.</em></p>\r\n',
        'Tour Hà Nội 1N: Tham Quan Phố Cổ - Làng Bát Tràng',
        'Hà Nội là thủ đô của Việt Nam và cũng là một thành phố có truyền thống, lịch sử và văn hóa lâu đời.',
        '<p><em>Lưu &yacute; 1:</em></p>\r\n\r\n<p>- Thứ 2,4,6:&nbsp;<strong>Tham quan L&agrave;ng Gốm B&aacute;t Tr&agrave;ng.</strong></p>\r\n\r\n<p>- Thứ 3,5,7,CN:&nbsp;<strong>Bảo t&agrave;ng d&acirc;n tộc học.</strong></p>\r\n\r\n<p>Qu&yacute; kh&aacute;ch quay trở về trung t&acirc;m H&agrave; Nội thăm&nbsp;<strong>đền Ngọc Sơn v&agrave; Hồ Ho&agrave;n Kiếm.</strong></p>\r\n\r\n<p>Kết th&uacute;c tour v&agrave; ch&agrave;o tạm biệt qu&yacute; kh&aacute;ch.</p>\r\n\r\n<p><em>Lưu &yacute; 2:</em></p>\r\n\r\n<p>- N&ecirc;n mang theo kem chống nắng, mũ hoặc &ocirc;, k&iacute;nh r&acirc;m.</p>\r\n\r\n<p>- Thời gian tham quan c&oacute; thể x&ecirc; dịch một ch&uacute;t t&ugrave;y theo ho&agrave;n cảnh cụ thể.</p>\r\n\r\n<p>- Điểm đ&oacute;n kh&aacute;ch sạn trong phố cổ hoặc Nh&agrave; H&aacute;t Lớn.</p>\r\n\r\n<p><em>Thứ tự tham quan c&oacute; thể thay đổi nhưng vẫn đảm bảo đầy đủ điểm trong chương tr&igrave;nh.</em></p>\r\n',
        'http://res.cloudinary.com/jstit/image/upload/v1622306301/9tour/tour/fo0f1ng8pmu0ansib4oa.jpg', 'Máy bay', 6,
        3),
       (2, NULL, 3, '2021-05-22 21:48:36', _binary '\0', 3, '2021-06-04 14:47:33', NULL,
        '<h3>V&atilde;n cảnh lễ Ch&ugrave;a Hương T&iacute;ch</h3>\r\n\r\n<p>Ch&ugrave;a Hương l&agrave; một trong những ng&ocirc;i ch&ugrave;a lớn nhất tại Việt Nam c&aacute;ch H&agrave; nội khoảng 80 km về ph&iacute;a Nam, với lễ hội ch&ugrave;a Hương k&eacute;o d&agrave;i từ th&aacute;ng 1-3 &acirc;m lịch h&agrave;ng năm đ&atilde; thu h&uacute;t h&agrave;ng trăm ngh&igrave;n lượt kh&aacute;ch h&agrave;ng năm về du ngoạn cảnh ch&ugrave;a với kh&ocirc;ng kh&iacute; m&aacute;t mẻ v&agrave; thả hồn m&igrave;nh trong d&ograve;ng s&ocirc;ng Yến tại chốn linh thi&ecirc;ng. C&ugrave;ng iVIVU kh&aacute;m ph&aacute; ngay h&ocirc;m nay!</p>\r\n\r\n<h3>Những trải nghiệm th&uacute; vị trong chương tr&igrave;nh</h3>\r\n\r\n<p>V&atilde;n cảnh ch&ugrave;a Hương kh&ocirc;ng thể bỏ qua...</p>\r\n\r\n<p>-&nbsp;<strong>Ch&ugrave;a Thi&ecirc;n Tr&ugrave;:</strong>&nbsp;được v&iacute; như một l&acirc;u đ&agrave;i nguy nga, tr&aacute;ng lệ &ldquo;Biệt chiếm nhất nam thi&ecirc;n&rdquo; giữa n&uacute;i rừng Hương Sơn.</p>\r\n\r\n<p>-&nbsp;<strong>Suối Y&ecirc;n:</strong>&nbsp;c&ograve;n mang t&ecirc;n Yến Vĩ, v&igrave; c&oacute; h&igrave;nh d&aacute;ng tựa như chiếc đu&ocirc;i x&ograve;e rộng của một con chim yến, với d&ograve;ng nước trong vắt tr&ocirc;i &ecirc;m ả giữa n&uacute;i đồi xanh ngắt.</p>\r\n',
        NULL, '1 ngày 2 đêm', '',
        '<h3>Chương tr&igrave;nh tour</h3>\r\n\r\n<p>LỊCH TR&Igrave;NH:</p>\r\n\r\n<h3>NG&Agrave;Y 1: CH&Ugrave;A HƯƠNG (ĂN TRƯA)</h3>\r\n\r\n<p><strong>8h30</strong>: Xe v&agrave; hướng dẫn vi&ecirc;n đ&oacute;n qu&yacute; kh&aacute;ch tại điểm hẹn khởi h&agrave;nh đi ch&ugrave;a Hương.</p>\r\n\r\n<p><strong>10h15</strong>: Đo&agrave;n tới bến Đục v&agrave;&nbsp;đi bộ l&ecirc;n bến Đục.</p>\r\n\r\n<p><strong>10h30</strong>: Qu&yacute; kh&aacute;ch đi thuyền dọc theo suối Yến (4 km), thưởng thức cảnh đẹp thơ mộng dọc 2 b&ecirc;n d&ograve;ng s&ocirc;ng v&agrave; d&ograve;ng nước trong m&aacute;t với những thảm tảo tuyệt đẹp. Qu&yacute; kh&aacute;ch đến bến Thi&ecirc;n Tr&ugrave; v&agrave; thăm quan&nbsp;<strong>ch&ugrave;a Thi&ecirc;n Tr&ugrave;.</strong></p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2021/05/06/17/ivivu-chua-thien-tru.gif\" /></p>\r\n\r\n<p><em>Ch&ugrave;a Thi&ecirc;n Tr&ugrave;.</em></p>\r\n\r\n<p><strong>12h15:</strong>&nbsp;Qu&yacute; kh&aacute;ch ăn trưa tại nh&agrave; h&agrave;ng địa phương.</p>\r\n\r\n<p><strong>13h15:</strong>&nbsp;Qu&yacute; kh&aacute;ch tham quan to&agrave;n cảnh&nbsp;<strong>ch&ugrave;a Hương</strong>&nbsp;leo n&uacute;i (5km) hoặc đi c&aacute;p. Sau đ&oacute; qu&yacute; kh&aacute;ch đi thuyền dọc theo&nbsp;<strong>suối Y&ecirc;n</strong>&nbsp;quay trở lại&nbsp;<strong>bến Đục.</strong></p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2021/05/06/17/ivivu-chua-huong.gif\" /></p>\r\n\r\n<p><em>Ch&ugrave;a Hương.</em></p>\r\n\r\n<p><strong>16h30:</strong>&nbsp;Qu&yacute; kh&aacute;ch l&ecirc;n xe trở về&nbsp;<strong>H&agrave; Nội.</strong></p>\r\n\r\n<p><strong>18h30:</strong>&nbsp;Về đến H&agrave; Nội. Kết th&uacute;c tour.</p>\r\n\r\n<p><em>Thứ tự tham quan c&oacute; thể thay đổi nhưng vẫn đảm bảo đầy đủ điểm trong chương tr&igrave;nh.</em></p>\r\n',
        'Tour Hà Nội 1N: Tham quan Chùa Hương',
        'Chùa Hương là một trong những ngôi chùa lớn nhất tại Việt Nam cách Hà nội khoảng 80 km về phía Nam.', '',
        'http://res.cloudinary.com/jstit/image/upload/v1622306617/9tour/tour/tlng5djfkd3lelbjud4z.jpg', 'Ô tô', 6, 3),
       (3, NULL, 3, '2021-05-22 21:50:55', _binary '\0', 3, '2021-06-03 16:07:08', NULL,
        '<h3>Đ&agrave; Lạt - Trẻ Trung Với Thi&ecirc;n Nhi&ecirc;n</h3>\r\n\r\n<p>Những cơn gi&oacute; len giữa th&ocirc;ng gi&agrave; như kh&uacute;c h&aacute;t của đại ng&agrave;n lu&ocirc;n ch&agrave;o đ&oacute;n những người lữ kh&aacute;ch đến với phố hoa Đ&agrave; Lạt! V&ugrave;ng đất T&acirc;y Nguy&ecirc;n qua rạng th&ocirc;ng reo r&igrave; r&agrave;o, những đỉnh n&uacute;i cao h&ugrave;ng vĩ, những đ&aacute;m m&acirc;y, sương bay, đ&oacute;n b&igrave;nh binh, ho&agrave;ng h&ocirc;n tr&ecirc;n đỉnh n&uacute;i, thưởng thức BBQ, rượu Vang Dalat&hellip; H&atilde;y trải nghiệm một cảm gi&aacute;c kỳ th&uacute; khi tham gia tour cắm trại. C&ugrave;ng iVIVU kh&aacute;m ph&aacute; ngay h&ocirc;m nay!&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>Những trải nghiệm th&uacute; vị trong chương tr&igrave;nh</h3>\r\n\r\n<p>Di chuyển bằng du thuy&ecirc;̀n tr&ecirc;n<strong>&nbsp;H&ocirc;̀ Tuy&ecirc;̀n L&acirc;m</strong>&nbsp;đ&ecirc;̉ đ&ecirc;́n khu cắm trại để tận hưởng v&agrave; tham gia c&aacute;c hoạt động:</p>\r\n\r\n<p>- Tham gia c&aacute;c hoạt động lều trại chuy&ecirc;n nghiệp, l&ecirc;̀u cao c&acirc;́p m&ocirc;ng c&ocirc;̉ Hot nh&acirc;́t hi&ecirc;̣n nay được dàn dựng và trang trí với các ph&acirc;n khu: l&ecirc;̀u ngủ, l&ecirc;̀u ăn BBQ, l&ecirc;̀u trung t&acirc;m đ&ecirc;̉ trò chuy&ecirc;̣n chụp hình, khu vực lửa trại và đặc bi&ecirc;̣t khu cắm trại có nhà tắm và toilet.</p>\r\n\r\n<p>- D&ugrave;ng Buffet tr&ecirc;n thảo nguy&ecirc;n xanh với những m&oacute;n ăn hấp dẫn.</p>\r\n\r\n<p>- Ngắm ho&agrave;ng h&ocirc;n, b&igrave;nh minh tr&ecirc;n thảo nguy&ecirc;n xanh.</p>\r\n\r\n<p>- Chi&ecirc;m ngưỡng vẻ đẹp của h&ocirc;̀&nbsp;<strong>Tuy&ecirc;̀n L&acirc;m</strong>&nbsp;thơ m&ocirc;̣ng.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>Bạn c&oacute; sẵn s&agrave;ng</h3>\r\n',
        NULL, '2 ngày 1 đêm', '',
        '<h3>Chương tr&igrave;nh tour</h3>\r\n\r\n<h3>CHIỀU NG&Agrave;Y 1: Đ&Agrave; LẠT - HỒ TUYỀN L&Acirc;M ( ĂN TỐI)</h3>\r\n\r\n<p>14:30: c&ocirc;ng ty&nbsp;đ&oacute;n qu&yacute; kh&aacute;ch tại điểm hẹn hoặc quý khách t&acirc;̣p trung tại Kh&aacute;ch sạn Dala Hotel địa chỉ số 158 B&ugrave;i thị Xu&acirc;n, Phường 2, TP Đ&agrave; Lạt, hoặc c&aacute;c cung đường đ&oacute;n kh&aacute;ch:&nbsp;B&ugrave;i Thị Xu&acirc;n, Phan Bội Ch&acirc;u, Ph&ugrave; Đổng Thi&ecirc;n Vương, Phan Đ&igrave;nh Ph&ugrave;ng, Hai B&agrave; Trưng, Nam Kỳ Khởi Nghĩa, Trần Ph&uacute;, khu vực chợ Đ&agrave; Lạt.&nbsp;</p>\r\n\r\n<p>15h30: Đo&agrave;n sẽ di chuyển thuyền m&aacute;y qua&nbsp;<strong>Hồ Tuyền L&acirc;m</strong>.</p>\r\n\r\n<p>16:00: Di chuy&ecirc;̉n và trải nghi&ecirc;̣m tr&ecirc;n du thuy&ecirc;̀n tại khu du lịch qu&ocirc;́c gia&nbsp;<strong>H&ocirc;̀ Tuy&ecirc;̀n L&acirc;m</strong>&nbsp;ngắm những ngọn đồi th&ocirc;ng để đến với điểm cắm trại.</p>\r\n\r\n<p>16:30: Tham gia chương tr&igrave;nh cắm trại với những trải nghi&ecirc;̣m khác bi&ecirc;̣t nhất. C&ugrave;ng nhau trải nghiệm dựng lều trại. Kiếm những c&agrave;nh củi kh&ocirc; v&agrave; chuẩn bị thịt nướng cho bữa tối.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/10/19/18/ivivu-camping-tuyen-lam-da-lat.gif\" /></p>\r\n\r\n<p><em>Dựng Lều Trại.</em></p>\r\n\r\n<p>Đồ ăn nhẹ cho bữa chiều: B&aacute;nh m&igrave;, bơ, đường, coffee&hellip;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/10/19/18/ivivu-do-an-nhe-tuyen-lam.gif\" /></p>\r\n\r\n<p><em>Đồ Ăn Nhẹ Cho Đo&agrave;n.</em></p>\r\n\r\n<p>18:30: Mọi người c&ugrave;ng nhau thưởng thức Buffet tối và lưu lại những hình ảnh đ&ocirc;̣c nh&acirc;́t tại khu vực l&ecirc;̀u ăn trung t&acirc;m được dựng và trang trí xịn nh&acirc;́t.</p>\r\n\r\n<p>Đồ ăn BBQ (B&ograve; nướng đ&aacute;, Thịt g&agrave; nướng mọi, Thịt heo sả ớt, T&ocirc;m sa tế, Rau salad trộn dầu dấm, Tr&aacute;i c&acirc;y tr&aacute;ng miệng, cơm lam gà nướng)&hellip;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/10/19/18/ivivu-bbq-bua-toi.gif\" /></p>\r\n\r\n<p><em>BBQ Bữa Tối.</em></p>\r\n\r\n<p>Thưởng thức rượu Cần hoặc rượu Vang Dalat trong thời tiết se se lạnh của n&uacute;i rừng&nbsp;<strong>T&acirc;y Nguy&ecirc;n</strong>.</p>\r\n\r\n<p>20:00: C&ugrave;ng nhau tham gia đốt lửa trại và tự do theo sự tùy thích của m&ocirc;̃i người dựa tr&ecirc;n các hoạt đ&ocirc;̣ng sau được&nbsp;c&ocirc;ng ty&nbsp;chu&acirc;̉n bị chu đáo sau:</p>\r\n\r\n<p>- C&ugrave;ng nhau nướng khoai bắp và tr&ograve; truyện k&ecirc;́t n&ocirc;́i với nhau b&ecirc;n bếp lửa với phương ch&acirc;m trước lạ sau quen.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/10/19/18/ivivu-nhom-bep-camping.gif\" /></p>\r\n\r\n<p><em>C&ugrave;ng Nh&oacute;m Bếp.</em></p>\r\n\r\n<p>- Chụp hình tại l&ecirc;̀u lớn trung t&acirc;m được trang trí đèn và v&ocirc; s&ocirc;́ các v&acirc;̣t dụng nhằm giúp bạn có những t&acirc;́m hình chill nh&acirc;́t kh&ocirc;ng nơi đ&acirc;u bằng.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/10/19/18/ivivu-trang-tri-camping.gif\" /></p>\r\n\r\n<p><em>Trang Tr&iacute; Lều Trại.</em></p>\r\n\r\n<p>- Ng&ocirc;̀i suy ng&acirc;̃m, cảm nh&acirc;̣n thi&ecirc;n nhi&ecirc;n, hoặc t&acirc;m sự mỏng. Tại l&ecirc;̀u ngủ của m&ocirc;̃i người đ&ecirc;̀u được trang bị bàn gh&ecirc;́ ri&ecirc;ng nhằm giúp đảm bảo tính ri&ecirc;ng tư và kh&ocirc;ng thích đám đ&ocirc;ng khi đ&ecirc;m v&ecirc;̀.</p>\r\n\r\n<p>22:00: Mọi người đi ngủ sớm để buổi s&aacute;ng thức dậy ở một nơi xa.</p>\r\n\r\n<h3>NG&Agrave;Y 2: KH&Aacute;M PH&Aacute; NG&Agrave;Y MỚI ( ĂN S&Aacute;NG NHẸ)</h3>\r\n\r\n<p>5:00: C&ugrave;ng nhau thức dậy: Vệ sinh c&aacute; nh&acirc;n.</p>\r\n\r\n<p>C&ugrave;ng nhau đ&oacute;n b&igrave;nh minh nơi phố vắng.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/10/20/09/ivivu-cam-trai-tuyen-lam.gif\" /></p>\r\n\r\n<p><em>Đ&oacute;n B&igrave;nh Minh.</em></p>\r\n\r\n<p>Săn m&acirc;y lưu giữ những t&acirc;́m h&igrave;nh đẹp.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/10/20/09/ivivu-san-may-ho-tuyen-lam.gif\" /></p>\r\n\r\n<p><em>Khoảnh Khắc Sương Mờ S&aacute;ng Sớm.</em></p>\r\n\r\n<p>6:30: Tập trung về địa điểm cắm trại.</p>\r\n\r\n<p>Ăn s&aacute;ng nhẹ với&nbsp;mỳ ly&nbsp;hảo hạng giữa rừng th&ocirc;ng hoặc b&aacute;nh m&igrave;, bơ, đường.</p>\r\n\r\n<p>Thưởng thức cafe s&aacute;ng hoặc tr&agrave; n&oacute;ng b&ecirc;n người th&acirc;n.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/10/20/09/ivivu-canh-tuyen-lam.gif\" /></p>\r\n\r\n<p><em>C&ugrave;ng Ngắm Cảnh Đẹp Như Tranh Vẽ.</em></p>\r\n\r\n<p>07:00: Thu gom lều trại, thu gom r&aacute;c v&agrave; c&ugrave;ng nhau tiếp tục h&agrave;nh tr&igrave;nh.&nbsp;</p>\r\n\r\n<p>08:00: Đo&agrave;n sẽ ch&egrave;o thuyền ra khỏi&nbsp;<strong>Hồ Tuyền L&acirc;m</strong>. HDV ch&agrave;o v&agrave; Hẹn gặp lại qu&yacute; kh&aacute;ch !&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/10/20/09/ivivu-cheo-thuyen-kayak.gif\" /></p>\r\n\r\n<p><em>Ch&egrave;o Thuyền.</em></p>\r\n\r\n<p><em>Thứ tự tham quan c&oacute; thể thay đổi nhưng vẫn đảm bảo đầy đủ điểm trong chương tr&igrave;nh.</em></p>\r\n',
        'Tour Camping Đà Lạt 2N1D: Chèo Thuyền - BBQ Hồ Tuyền Lâm', '', '',
        'http://res.cloudinary.com/jstit/image/upload/v1622306899/9tour/tour/e66fumccpvvzsduccbns.jpg', 'Máy bay', 3,
        4),
       (4, NULL, 3, '2021-05-22 21:51:15', _binary '\0', 3, '2021-05-29 23:50:46', NULL,
        '<h3>M&ecirc; đắm trong xứ cổ t&iacute;ch miền phố hoa Đ&agrave; Lạt</h3>\r\n\r\n<p>Đến với Đ&agrave; Lạt ư? V&ocirc; v&agrave;n thứ sẽ dần thấm v&agrave;o trong người bạn, khiến bạn y&ecirc;u n&oacute; l&uacute;c n&agrave;o kh&ocirc;ng biết, c&aacute;i thấm ấy n&oacute; nhẹ nh&agrave;ng len lỏi như những c&aacute;i se se lạnh của th&agrave;nh phố sương m&ugrave; n&agrave;y. Thưởng thức kỳ nghỉ tại &#39;Miền đất lạnh của miền Trung&#39;, kh&aacute;m ph&aacute; những g&igrave; đặc trưng nhất của Đ&agrave; Lạt hẳn l&agrave; một trải nghiệm đ&aacute;ng nhớ c&ugrave;ng người th&acirc;n của m&igrave;nh đ&uacute;ng kh&ocirc;ng? C&ugrave;ng iVIVU kh&aacute;m ph&aacute; ngay h&ocirc;m nay!</p>\r\n\r\n<h3>Những trải nghiệm th&uacute; vị trong chương tr&igrave;nh</h3>\r\n\r\n<p>Đ&agrave; Lạt lu&ocirc;n l&agrave; điểm hấp dẫn du kh&aacute;ch bởi:</p>\r\n\r\n<p>-&nbsp;<strong>Đồi Ch&egrave; Cầu Đất:&nbsp;</strong>được v&iacute; như l&aacute; phổi xanh, l&agrave; linh hồn của Đ&agrave; Lạt.</p>\r\n\r\n<p>-&nbsp;<strong>Que Garden</strong>&nbsp;&ndash; một điểm tham quan mới tại Đ&agrave; Lạt</p>\r\n\r\n<p>-<strong>&nbsp;Hoa Sơn Điền Trang:&nbsp;</strong>điểm đến mới h&uacute;t kh&aacute;ch du lịch,&nbsp;một khu du lịch sinh th&aacute;i kết hợp h&agrave;i h&ograve;a giữa thi&ecirc;n nhi&ecirc;n n&uacute;i rừng thơ mộng v&agrave; những c&ocirc;ng tr&igrave;nh rất s&aacute;ng tạo.</p>\r\n',
        NULL, '3 ngày 3 đêm', '',
        '<h3>Chương tr&igrave;nh tour</h3>\r\n\r\n<h3>Đ&Ecirc;M 1: TP HỒ CH&Iacute; MINH&nbsp;- Đ&Agrave; LẠT (NGHỈ Đ&Ecirc;M TR&Ecirc;N XE)</h3>\r\n\r\n<p>21h30: Xe v&agrave; HDV C&ocirc;ng Ty đ&oacute;n kh&aacute;ch tại điểm hẹn tập trung khởi h&agrave;nh đi Đ&agrave; Lạt.</p>\r\n\r\n<p>Địa điểm đ&oacute;n kh&aacute;ch:</p>\r\n\r\n<p>-&nbsp;<strong>Nh&agrave; Văn h&oacute;a Thanh Ni&ecirc;n &ndash; số 04 Phạm Ngọc Thạch, Quận 1</strong></p>\r\n\r\n<p>-&nbsp;<strong>C&acirc;y xăng Comeco số 3 gần ng&atilde; tư H&agrave;ng Xanh</strong></p>\r\n\r\n<p>-&nbsp;<strong>Ng&atilde; tư Thủ Đức, Xa Lộ H&agrave; Nội</strong></p>\r\n\r\n<p>-&nbsp;<strong>Amata Bi&ecirc;n H&ograve;a, Đồng Nai</strong></p>\r\n\r\n<p>Qu&yacute; Kh&aacute;ch thư giản nghỉ ngơi tr&ecirc;n xe.</p>\r\n\r\n<h3>NG&Agrave;Y 1: Đ&Agrave; LẠT TH&Agrave;NH PHỐ SƯƠNG M&Ugrave; (ĂN S&Aacute;NG, TRƯA, TỐI)</h3>\r\n\r\n<p>5h00: Xe v&agrave; HDV Du Lịch sẽ đưa đo&agrave;n đ&ecirc;n địa điểm tập trung để l&agrave;m vệ sinh c&aacute; nh&acirc;n sau đ&oacute; di chuyển tham quan ch&ugrave;a&nbsp;<strong>Linh Quy Ph&aacute;p Ấn</strong>. Tại đ&acirc;y nếu qu&yacute; kh&aacute;ch n&agrave;o kh&ocirc;ng muốn tham quan c&oacute; thể nghỉ ngơi tr&ecirc;n xe hoặc nằm v&otilde;ng tại c&aacute;c qu&aacute;n Caf&eacute; để chờ đo&agrave;n quay về di chuyển d&ugrave;ng bữa s&aacute;ng.&nbsp;</p>\r\n\r\n<p>Ngắm b&igrave;nh minh tại&nbsp;<strong>cổng trời Đ&agrave; Lạt &ndash; Linh Quy Ph&aacute;p Ấn</strong>. Nếu bạn thuộc kiểu người th&iacute;ch sự b&igrave;nh y&ecirc;n, nhẹ nh&agrave;ng th&igrave; n&ecirc;n đến ngay&nbsp;<strong>ch&ugrave;a Linh Quy Ph&aacute;p Ấn</strong>.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2018/03/16/17/ivivu-chua-linh-quy-phap-an.jpg\" /></p>\r\n\r\n<p><em>Linh Quy Ph&aacute;p Ấn.</em></p>\r\n\r\n<p>Ch&ugrave;a nằm tr&ecirc;n ngọn đồi cao, ẩn m&igrave;nh giữa rừng c&acirc;y v&agrave; vườn ch&egrave;. Xung quanh l&agrave; những thảm cỏ xanh mướt. Nơi đ&acirc;y kh&ocirc;ng chỉ thanh tịnh, b&igrave;nh y&ecirc;n m&agrave; c&ograve;n c&oacute; phong cảnh thi&ecirc;n nhi&ecirc;n bao la đ&aacute;ng để ta chi&ecirc;m ngưỡng.</p>\r\n\r\n<p>Chặng n&agrave;y đường m&ograve;n từ đường nhựa l&ecirc;n đến ch&ugrave;a kh&aacute; kh&oacute; đi, xe oto kh&ocirc;ng đi được. Xe tay ga v&agrave; t&agrave;i xế yếu cũng n&ecirc;n lưu &yacute;. Đến đoạn n&agrave;y bạn c&oacute; thể thu&ecirc; xe &ocirc;m khoảng 60.000đ/người để l&ecirc;n cổng trời. (Chi ph&iacute; xe &ocirc;m tự t&uacute;c)</p>\r\n\r\n<p>7h00: Đến&nbsp;<strong>TP. Bảo Lộc</strong>, Xe đưa đo&agrave;n đến nh&agrave; h&agrave;ng. Qu&yacute; kh&aacute;ch d&ugrave;ng bữa s&aacute;ng.</p>\r\n\r\n<p>10h30: Đo&agrave;n tham quan&nbsp;<strong>Khu Du Lịch Th&aacute;c Pongour</strong>&nbsp;&ndash; một tuyệt t&aacute;c kỳ vỹ m&agrave; thi&ecirc;n nhi&ecirc;n ban tặng cho v&ugrave;ng đất n&agrave;y, th&aacute;c đẹp v&agrave; chia l&agrave;m nhiều tầng n&ecirc;n người ta c&ograve;n gọi đ&acirc;y l&agrave;&nbsp;<strong>Th&aacute;c Bảy Tầng</strong>. Sau đ&oacute; di chuyển đến&nbsp;<strong>TP. Đ&agrave; Lạt</strong>&nbsp;để nhận ph&ograve;ng kh&aacute;ch sạn nghỉ ngơi.&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/06/03/09/ivviu-thac-pongour.gif\" /></p>\r\n\r\n<p><em>Khu Du Lịch Th&aacute;c Pongour.</em></p>\r\n\r\n<p>12h00: Đo&agrave;n d&ugrave;ng bữa trưa tại nh&agrave; h&agrave;ng ở&nbsp;<strong>Đ&agrave; Lạt</strong>. Sau đ&oacute; về Kh&aacute;ch sạn nhận ph&ograve;ng, tự do nghỉ ngơi.</p>\r\n\r\n<p>14h00: Đo&agrave;n khởi h&agrave;nh đi tham quan:</p>\r\n\r\n<p><strong>- Tiệm Caf&eacute; Xứ Sở Thần Ti&ecirc;n</strong>&nbsp;&ndash; Decor đơn giản nhưng ấn tượng, những tiểu cảnh trang tr&iacute; chủ yếu lấy từ c&oacute;i tre. Một chất liệu kh&aacute; quen thuộc. Nhưng nhờ v&agrave;o b&agrave;n tay t&agrave;i hoa của nhiều nghệ nh&acirc;n đ&atilde; biến những vật dụng th&ocirc; sơ n&agrave;y trở n&ecirc;n mềm mại c&oacute; hồn. B&agrave;n ghế được thiết kế bằng tre th&acirc;n thiện với m&ocirc;i trường.&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2021/03/10/14/ivivu-cafe-su-xo-than-tien.gif\" /></p>\r\n\r\n<p><em>Tiệm Caf&eacute; Xứ Sở Thần Ti&ecirc;n</em>.</p>\r\n\r\n<p>18h00: Đo&agrave;n d&ugrave;ng bữa tối tại nh&agrave; h&agrave;ng. Sau đ&oacute; đo&agrave;n tự do kh&aacute;m ph&aacute;<strong>&nbsp;Đ&agrave; Lạt</strong>&nbsp;buổi tối hoặc đăng k&yacute; tham dự đ&ecirc;m lửa trại, uống rượu cần, giao lưu văn h&oacute;a với người T&acirc;y Nguy&ecirc;n (Chi ph&iacute; tự t&uacute;c). Đo&agrave;n nghỉ đ&ecirc;m tại Đ&agrave; Lạt.</p>\r\n\r\n<h3>NG&Agrave;Y 02: Đ&Agrave; LẠT &ndash; TH&Agrave;NH PHỐ NG&Agrave;N HOA (ĂN S&Aacute;NG, TRƯA, TỐI)</h3>\r\n\r\n<p>7h00: Đo&agrave;n Thưởng thức bữa s&aacute;ng tại Nh&agrave; h&agrave;ng.</p>\r\n\r\n<p>8h00: Qu&yacute; kh&aacute;ch khởi h&agrave;nh đi tham quan:&nbsp;</p>\r\n\r\n<p>-&nbsp;<strong>Quảng Trường L&acirc;m Vi&ecirc;n</strong>&nbsp;&ndash; l&agrave; một trong những c&ocirc;ng tr&igrave;nh đặc biệt của th&agrave;nh phố Đ&agrave; Lạt. Để ho&agrave;n th&agrave;nh được c&ocirc;ng tr&igrave;nh n&agrave;y, th&agrave;nh phố Đ&agrave; Lạt đ&atilde; mất tới 6 năm thi c&ocirc;ng, với kinh ph&iacute; 681 tỷ đồng. Từ khi được ho&agrave;n th&agrave;nh v&agrave; đưa v&agrave;o hoạt động v&agrave;o năm 2016, kh&ocirc;ng l&uacute;c n&agrave;o quảng trường L&acirc;m Vi&ecirc;n vắng du kh&aacute;ch đến tham quan.&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/01/03/14/ivivu-quang-truong-lam-vien-1.gif\" /></p>\r\n\r\n<p><em>Quảng Trường L&acirc;m Vi&ecirc;n.</em></p>\r\n\r\n<p>- Tham quan v&agrave; mua sắm tại cơ sở sản xuất Đặc Săn Thanh Nhu.</p>\r\n\r\n<p>-&nbsp;<strong>Frenzy Fram</strong>&nbsp; -&nbsp;<strong>N&ocirc;ng Trại Cừu</strong>&nbsp;&ndash; nơi nu&ocirc;i dưởng h&agrave;ng trăm ch&uacute; cừu xinh xắn với những bộ long trắng mượt &ndash; tại đ&acirc;y qu&yacute; du kh&aacute;ch c&oacute; thể vui đ&ugrave;a c&ugrave;ng đ&agrave;n cừu th&acirc;n thiện hoặc ngồi thư giản caf&eacute; ngắm nh&igrave;n cảnh sắc thi&ecirc;n nhi&ecirc;n an b&igrave;nh tại nơi đ&acirc;y.&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2021/03/10/14/ivivu-frenzy-fram-nong-trai-cuu.gif\" /></p>\r\n\r\n<p><em>Frenzy Fram - N&ocirc;ng Trại Cừu</em>.</p>\r\n\r\n<p>11h30: Đo&agrave;n khởi h&agrave;nh về Nh&agrave; h&agrave;ng d&ugrave;ng cơm trưa . Sau đ&oacute; về Kh&aacute;ch sạn nghỉ ngơi.</p>\r\n\r\n<p>14h00: Đo&agrave;n khởi h&agrave;nh tham quan:</p>\r\n\r\n<p>-&nbsp;<strong>Viếng ch&ugrave;a Linh Phước</strong>&nbsp;&ndash; hay c&ograve;n gọi Ch&ugrave;a Ve Chai với nhiều kiến tr&uacute;c độc đ&aacute;o v&agrave; to lớn (Đại Hồng Chung, Tượng Phật Quan &Acirc;m khổng lồ kết từ hang ng&agrave;n hoa bất tử ...)&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2018/07/03/16/ivivu-chua-linh-phuoc.jpg\" /></p>\r\n\r\n<p><em>Ch&ugrave;a Linh Phước.</em></p>\r\n\r\n<p>-&nbsp;<strong>Vườn hoa cẩm t&uacute; cầu</strong>&nbsp;&ndash; nơi c&oacute; một c&ocirc;ng tr&igrave;nh đặc biệt được m&ocirc; ph&ograve;ng theo kiến tr&uacute;c của&nbsp;<strong>KDL B&agrave; N&agrave; Hill &ndash; Đ&agrave; Nẵng - C&acirc;y Cầu V&agrave;ng</strong>&nbsp;với B&agrave;n Tay Khổng Lồ - được bắt tr&ecirc;n c&aacute;nh đồng hoa tại đ&acirc;y du kh&aacute;ch sẽ c&oacute; được một kh&ocirc;ng gian sống ảo kh&aacute;c lạ v&agrave; lung linh.&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2018/03/16/17/ivivu-vuon-hoa-cam-tu-cau.jpg\" /></p>\r\n\r\n<p><em>Vườn Hoa Cẩm T&uacute; Cầu.</em></p>\r\n\r\n<p>17h30: Xe đưa đo&agrave;n đến nh&agrave; h&agrave;ng, Qu&yacute; kh&aacute;ch d&ugrave;ng Buffet Rau Kh&ocirc;ng Giới Hạn với thực đơn hấp dẫn:</p>\r\n\r\n<p>- Salad trộn, gỏi s&uacute; t&iacute;m Đ&agrave; Lạt, củ quả hấp, rau ăn lẩu,</p>\r\n\r\n<p>- 2 loại nước lẩu, b&uacute;n + m&igrave; trứng, tr&agrave; đ&aacute;.&nbsp;</p>\r\n\r\n<p>- Thức ăn k&egrave;m lẩu bao gồm như: Ba chỉ, b&ograve; fille, c&aacute; di&ecirc;u hồng, v&acirc;y c&aacute; hồi, t&ocirc;m, mực, c&aacute; vi&ecirc;n, c&aacute;c loại, nấm...</p>\r\n\r\n<p>Buổi tối: Đo&agrave;n tự do dạo phố về đ&ecirc;m, thưởng thức ly c&agrave; ph&ecirc; phố n&uacute;i tại Hội Qu&aacute;n C&agrave; Ph&ecirc; Nh&agrave; H&agrave;ng Thủy Tạ b&ecirc;n Hồ Xu&acirc;n Hương, hoặc kh&aacute;m ph&aacute; chợ đ&ecirc;m &Acirc;m Phủ Đ&agrave; Lạt với c&aacute;c m&oacute;n ăn h&egrave; phố (Kh&aacute;ch tự t&uacute;c).</p>\r\n\r\n<h3>NG&Agrave;Y 3: TP. Đ&Agrave; LẠT &ndash; TP. HỒ CH&Iacute; MINH (ĂN TRƯA)</h3>\r\n\r\n<p>7h00: Qu&yacute; kh&aacute;ch trả ph&ograve;ng kh&aacute;ch sạn. Sau đ&oacute; qu&yacute; kh&aacute;ch tự do kh&aacute;m ph&aacute; ẩm thực&nbsp;<strong>Đ&agrave; Lạt</strong>&nbsp;v&agrave;o buổi s&aacute;ng kết hợp Tham quan mua sắm rau củ quả tươi tại&nbsp;<strong>Chợ Đ&agrave; Lạt</strong>&nbsp;về l&agrave;m qu&agrave; cho người th&acirc;n v&agrave; bạn b&egrave;.</p>\r\n\r\n<p>8h30: Qu&yacute; kh&aacute;ch rời&nbsp;<strong>Đ&agrave; Lạt</strong>&nbsp;về&nbsp;<strong>TP.Hồ Ch&iacute; Minh</strong>.</p>\r\n\r\n<p>11h30: Đo&agrave;n dừng ch&acirc;n tại&nbsp;<strong>nh&agrave; h&agrave;ng T&acirc;m Ch&acirc;u Lộc An</strong>, Qu&yacute; kh&aacute;ch thưởng thức đặc sản tr&agrave;, c&agrave; ph&ecirc; miễn ph&iacute;. D&ugrave;ng cơm trưa tại Nh&agrave; h&agrave;ng.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2018/03/16/17/ivivu-nha-hang-tam-chau.jpg\" /></p>\r\n\r\n<p><em>Nh&agrave; H&agrave;ng T&acirc;m Ch&acirc;u.</em></p>\r\n\r\n<p>13h00&nbsp;: Đo&agrave;nTiếp tục khởi h&agrave;nh về&nbsp;<strong>TP. Hồ Ch&iacute; Minh</strong>.</p>\r\n\r\n<p>Dự kiến 18h00: Đo&agrave;n về tới&nbsp;<strong>TP. Hồ Ch&iacute; Minh</strong>. HDV chia tay v&agrave; hẹn gặp lại qu&yacute; kh&aacute;ch ở chuyến tour sau!</p>\r\n\r\n<p><em>Thứ tự tham quan c&oacute; thể thay đổi nhưng vẫn đảm bảo đầy đủ điểm trong chương tr&igrave;nh.</em></p>\r\n',
        'Tour Đà Lạt 3N3D: Đà Lạt và Hoa', '', '',
        'http://res.cloudinary.com/jstit/image/upload/v1622307045/9tour/tour/r4auatrkhdn5nx3frwiq.jpg',
        'Ô tô Phương Trang', 6, 4),
       (5, NULL, 3, '2021-05-22 22:25:13', _binary '\0', 3, '2021-06-04 14:47:53', NULL,
        '<h3>Đặc sắc H&ograve;n Ngọc Viễn Đ&ocirc;ng</h3>\r\n\r\n<p>Th&agrave;nh phố Hồ Ch&iacute; Minh (S&agrave;i G&ograve;n) l&agrave; trung t&acirc;m kinh tế, ch&iacute;nh trị v&agrave; văn h&oacute;a lớn nhất ở miền Nam Việt Nam. S&agrave;i G&ograve;n &ndash; TP.HCM cũng l&agrave; một trung t&acirc;m du lịch lớn, với c&aacute;c di t&iacute;ch lịch sử v&agrave; bảo t&agrave;ng ghi lại dấu ấn thời chiến tranh chống Ph&aacute;p v&agrave; Mỹ&nbsp;như: Bảo t&agrave;ng chứng t&iacute;ch chiến tranh. B&ecirc;n cạnh đ&oacute;, c&aacute;c c&ocirc;ng tr&igrave;nh kiến tr&uacute;c thời thuộc địa cũng l&agrave;m m&atilde;n nh&atilde;n du kh&aacute;ch khi du lịch S&agrave;i G&ograve;n, như Trụ sở Ủy Ban Nh&acirc;n D&acirc;n Th&agrave;nh phố, Nh&agrave; h&aacute;t lớn, Bưu điện trung t&acirc;m, Bến Nh&agrave; Rồng, Chợ Bến Th&agrave;nh v&agrave; Dinh Độc Lập.&nbsp;C&ugrave;ng iVIVU trải nghiệm một ng&agrave;y kh&aacute;m ph&aacute; S&agrave;i G&ograve;n ngay h&ocirc;m nay!</p>\r\n\r\n<h3>Những trải nghiệm th&uacute; vị trong chương tr&igrave;nh</h3>\r\n\r\n<p>Trải nghiệm một ng&agrave;y kh&aacute;m ph&aacute; văn h&oacute;a S&agrave;i G&ograve;n với...</p>\r\n\r\n<p>-&nbsp;<strong>Bảo t&agrave;ng chứng t&iacute;ch chiến tranh:</strong>&nbsp;t&aacute;i hiện lại những năm th&aacute;ng lịch sử h&agrave;o h&ugrave;ng của d&acirc;n tộc, l&agrave; một điểm đến tuyệt vời cho những người y&ecirc;u th&iacute;ch lịch sử, muốn được sống lại những ng&agrave;y kh&oacute;i lửa chiến tranh quyết liệt.</p>\r\n\r\n<p>-&nbsp;<strong>Dinh Độc Lập:&nbsp;</strong>từng l&agrave; c&ocirc;ng thự đẹp nhất &Aacute; &ETH;&ocirc;ng - nơi ở của những người quyền lực nhất v&agrave; cũng l&agrave; nơi chứng kiến nhiều biến cố lịch sử.</p>\r\n\r\n<p>-&nbsp;<strong>Nh&agrave; Thờ Đức B&agrave;:&nbsp;</strong>một trong những c&ocirc;ng tr&igrave;nh kiến tr&uacute;c độc đ&aacute;o nhất tại S&agrave;i G&ograve;n kh&ocirc;ng chỉ thu h&uacute;t sự quan t&acirc;m của h&agrave;ng triệu lượt du kh&aacute;ch cả trong v&agrave; ngo&agrave;i nước đến tham quan, tham dự Th&aacute;nh lễ m&agrave; c&ograve;n l&agrave; nơi tụ tập của c&aacute;c bạn trẻ năng động.&nbsp;Trải qua 130 năm biến động ch&iacute;nh trị, lịch sử, nh&agrave; thờ Đức B&agrave; vẫn sừng sững đứng đ&oacute; m&agrave; dường như kh&ocirc;ng ảnh hưởng bởi thời gian.&nbsp;</p>\r\n\r\n<p>-&nbsp;<strong>Bưu điện th&agrave;nh phố:&nbsp;</strong>bưu điện lớn nhất Việt Nam, l&agrave; địa điểm thu h&uacute;t nhiều kh&aacute;ch du lịch khi đến tham quan th&agrave;nh phố Hồ Ch&iacute; Minh.&nbsp;Trải qua hơn trăm năm tồn tại, t&ograve;a nh&agrave; Bưu điện Trung t&acirc;m S&agrave;i g&ograve;n hiện vẫn l&agrave; một c&ocirc;ng tr&igrave;nh kiến tr&uacute;c đẹp v&agrave; ấn tượng.</p>\r\n',
        NULL, '1 ngày 2 đêm', '',
        '<h3>Chương tr&igrave;nh tour</h3>\r\n\r\n<h3>NG&Agrave;Y 1: CITY TOUR TP. HỒ CH&Iacute; MINH</h3>\r\n\r\n<p><strong>Buổi s&aacute;ng,</strong>&nbsp;Xe v&agrave; hướng dẫn vi&ecirc;n đ&oacute;n Qu&yacute; kh&aacute;ch tại điểm hẹn, khởi h&agrave;nh đi thăm quan:</p>\r\n\r\n<p>-&nbsp;<strong>Bảo t&agrave;ng chứng t&iacute;ch chiến tranh</strong>:&nbsp;Nằm&nbsp;tr&ecirc;n đường V&otilde; Văn Tần, l&agrave; bảo t&agrave;ng chuy&ecirc;n đề nghi&ecirc;n cứu, sưu tầm, lưu trữ, bảo quản v&agrave; trưng b&agrave;y những tư liệu, h&igrave;nh ảnh, hiện vật về những chứng t&iacute;ch tội &aacute;c v&agrave; hậu quả của c&aacute;c cuộc chiến tranh m&agrave; c&aacute;c thế lực x&acirc;m lược đ&atilde; g&acirc;y ra đối với Việt Nam.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2016/08/26/13/ivivu-tour-sai-gon-1n-tham-quan-tp-ho-chi-minh-bao-tang-di-tich-chien-tranh.jpg\" width=\"710\" /></p>\r\n\r\n<p><em>Bảo T&agrave;ng Chứng T&iacute;ch Chiến Tranh.</em></p>\r\n\r\n<p>-&nbsp;<strong>Dinh Độc Lập</strong>&nbsp;(Dinh Thống Nhất):&nbsp;C&ocirc;ng tr&igrave;nh kiến tr&uacute;c nổi tiếng ở Th&agrave;nh phố Hồ Ch&iacute; Minh. Hiện nay, n&oacute; đ&atilde; được thủ tướng ch&iacute;nh phủ Việt Nam xếp hạng l&agrave; di t&iacute;ch quốc gia đặc biệt.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2016/08/26/13/ivivu-tour-sai-gon-1n-tham-quan-tp-ho-chi-minh-dinh-doc-lap.jpg\" width=\"710\" /></p>\r\n\r\n<p><em>Dinh Độc Lập.</em></p>\r\n\r\n<p>-&nbsp;<strong>Nh&agrave; Thờ Đức B&agrave;</strong>: Một tuyệt t&aacute;c kiến tr&uacute;c - một c&ocirc;ng tr&igrave;nh ti&ecirc;u biểu g&oacute;p phần tạo n&ecirc;n diện mạo đ&ocirc; thị S&agrave;i G&ograve;n - Th&agrave;nh phố Hồ Ch&iacute; Minh. C&ocirc;ng tr&igrave;nh cũng ghi nhận sự du nhập, giao lưu v&agrave; tiếp biến của văn h&oacute;a - kiến tr&uacute;c Đ&ocirc;ng - T&acirc;y.&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2016/08/26/13/ivivu-tour-sai-gon-1-ngay-sang-chieu-nha-tho-duc-ba.jpg\" /></p>\r\n\r\n<p><em>Nh&agrave; Thờ Đức B&agrave;.</em></p>\r\n\r\n<p>-&nbsp;<strong>Bưu Điện Th&agrave;nh Phố:</strong>&nbsp;L&agrave; điểm đến tham quan kh&ocirc;ng thể thiếu của du kh&aacute;ch khi đến th&agrave;nh phố&nbsp;<strong>Hồ Ch&iacute; Min</strong>h, một c&ocirc;ng tr&igrave;nh kiến tr&uacute;c cổ, c&oacute; hơn 120 năm tuổi.</p>\r\n\r\n<p>Đo&agrave;n khởi h&agrave;nh về lại điểm đ&oacute;n ban đầu. Chia tay &ndash; Kết th&uacute;c chương tr&igrave;nh du lịch City S&agrave;i G&ograve;n nửa ng&agrave;y. Hẹn gặp lại Qu&yacute; kh&aacute;ch.</p>\r\n\r\n<p><em>Thứ tự tham quan c&oacute; thể thay đổi nhưng vẫn đảm bảo đầy đủ điểm trong chương tr&igrave;nh.</em></p>\r\n',
        'Tour Sài Gòn 1/2N: Tham Quan Thành Phố Hồ Chí Minh', '', '',
        'http://res.cloudinary.com/jstit/image/upload/v1622307193/9tour/tour/k7q3dud4iyjbwgraf98q.jpg',
        'Máy bay VietNam AirLines', 3, 6),
       (6, NULL, 3, '2021-05-22 22:25:44', _binary '\0', 3, '2021-05-30 00:03:44', NULL,
        '<h3>Zoodoo ph&ugrave; hợp cho gia đ&igrave;nh v&agrave; c&aacute;c bạn nhỏ</h3>\r\n\r\n<p>Nếu l&agrave; một người y&ecirc;u động vật chắc chắn bạn sẽ rất th&iacute;ch những con vật vừa l&ugrave;n vừa ngộ nghĩnh c&oacute; tại nơi đ&acirc;y. C&ugrave;ng iVIVU kh&aacute;m ph&aacute; ngay h&ocirc;m nay!&nbsp;</p>\r\n\r\n<h3>Những trải nghiệm th&uacute; vị trong chương tr&igrave;nh</h3>\r\n\r\n<p>Những trải nghiệm mới lạ c&oacute; trong chương tr&igrave;nh:</p>\r\n\r\n<p>-&nbsp;<strong>Zoodoo</strong>&nbsp;c&oacute; rất nhiều loại động vật m&agrave; du kh&aacute;ch những tưởng phải đến tận trời T&acirc;y mới c&oacute;.</p>\r\n\r\n<p>-&nbsp;<strong>Cẩm T&uacute; Cầu</strong>&nbsp;l&agrave; lo&agrave;i hoa nổi tiếng của Đ&agrave; Lạt.</p>\r\n\r\n<p>-&nbsp;<strong>Trang trại c&uacute;n</strong>: Điểm tham quan hot nhất hiện nay với hơn 36 lo&agrave;i ch&oacute; hiếm.</p>\r\n',
        NULL, '1 ngày 2 đêm', '',
        '<h3>Chương tr&igrave;nh tour</h3>\r\n\r\n<p>Kh&aacute;ch lưu tr&uacute; tại kh&aacute;ch sạn/resort hồ Tuyền L&acirc;m tự t&uacute;c tập trung đến địa điểm 38 Tăng Bạt Hổ, Phường 1, TP. Đ&agrave; Lạt.</p>\r\n\r\n<p>8h30: S&aacute;ng, xe HDV của ch&uacute;ng t&ocirc;i sẽ đ&oacute;n qu&yacute; kh&aacute;ch tại kh&aacute;ch sạn trung t&acirc;m sau đ&oacute; khởi h&agrave;nh đi tham quan:</p>\r\n\r\n<p>-&nbsp;<strong>Sở th&uacute; Zoodoo Đ&agrave; Lạt</strong>: Ở Zoodoo c&oacute; rất nhiều loại động vật m&agrave; du kh&aacute;ch những&nbsp;tưởng phải đến tận trời T&acirc;y mới c&oacute; thể được chạm tay v&agrave;o như:&nbsp;kangaroo, khỉ, ngựa l&ugrave;n Pony,&nbsp;lạc đ&agrave; Alpaca, cừu l&ocirc;ng,&hellip; Đặc biệt, du kh&aacute;ch c&oacute; thể lại gần vui chơi v&agrave; cho th&uacute; ăn.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/06/01/15/ivivu-zoodo.gif\" /></p>\r\n\r\n<p><em>Zoodoo Đ&agrave; Lạt.</em></p>\r\n\r\n<p>-&nbsp;<strong>Trang trại rau v&agrave; l&agrave;ng hoa vạn th&agrave;nh</strong>: L&agrave; một trong 3 l&agrave;ng hoa lớn nhất tại&nbsp;<strong>Đ&agrave; Lạt</strong>. Nơi đ&acirc;y trồng rất nhiều loại hoa nổi tiếng như: Hoa hồng, hoa đồng tiền, c&uacute;c họa mi,...</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/06/01/15/ivivu-lang-hoa-van-thanh.gif\" /></p>\r\n\r\n<p><em>L&agrave;ng Hoa Vạn Th&agrave;nh.</em></p>\r\n\r\n<p>Xe đưa qu&yacute; kh&aacute;ch đến nh&agrave; h&agrave;ng, đo&agrave;n d&ugrave;ng bữa trưa tự t&uacute;c. Sau bữa trưa xe v&agrave; HDV đưa qu&yacute; kh&aacute;ch đi tham quan:</p>\r\n\r\n<p>-&nbsp;<strong>C&aacute;nh đồng Cẩm t&uacute; cầu</strong>:&nbsp;<strong>Cẩm T&uacute; Cầu</strong>&nbsp;l&agrave; lo&agrave;i hoa nổi tiếng của&nbsp;<strong>Đ&agrave; Lạt</strong>, được trồng rất nhiều nơi ở trong th&agrave;nh phố v&agrave; v&ugrave;ng ngoại &ocirc;. Ban đầu, những c&aacute;nh đồng hoa như thế n&agrave;y chỉ l&agrave; nơi người d&acirc;n nu&ocirc;i trồng c&acirc;y, sau khi được nhiều người t&igrave;m đến mới trở th&agrave;nh một địa điểm du lịch.&nbsp;Vẻ đẹp của lo&agrave;i hoa n&agrave;y được nhiều người y&ecirc;u th&iacute;ch v&agrave; được trồng rộng r&atilde;i trong c&aacute;c vườn hoa, vườn cảnh, đặc biệt l&agrave; được d&ugrave;ng l&agrave;m hoa cưới.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2017/11/16/15/ivivu-vuon-hoa-cam-tu-cau.jpg\" /></p>\r\n\r\n<p><em>Vườn Hoa Cẩm T&uacute; Cầu.</em></p>\r\n\r\n<p>-&nbsp;<strong>Trang trại c&uacute;n</strong>: Điểm tham quan hot nhất hiện nay với hơn 36 lo&agrave;i ch&oacute; hiếm, hơn 100 c&aacute; thể ch&oacute;. Tại đ&acirc;y, bạn c&oacute; thể tham quan vườn b&iacute; ng&ocirc; khổng lồ + d&acirc;u t&acirc;y c&ocirc;ng nghệ cao + dưa leo baby, dưa pepino + c&agrave; chua cherry, c&aacute;c vườn hoa.. (theo m&ugrave;a) v&agrave; mua c&aacute;c loại n&ocirc;ng sản sạch được trồng tại trang trại.</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/06/01/15/ivivu-trai-cun-puppy-farm.gif\" /></p>\r\n\r\n<p><em>Trang Trại C&uacute;n.</em></p>\r\n\r\n<p>16h: Xe đưa qu&yacute; kh&aacute;ch về lại điểm đ&oacute;n ban đầu, kết th&uacute;c tour v&agrave; hẹn gặp lại.</p>\r\n\r\n<p><em>Thứ tự tham quan c&oacute; thể thay đổi nhưng vẫn đảm bảo đầy đủ điểm trong chương tr&igrave;nh.</em></p>\r\n',
        'Tour Đà Lạt 1N: Zoodoo', '', '',
        'http://res.cloudinary.com/jstit/image/upload/v1622307824/9tour/tour/vylkr6w9kwkmrsvpzpzb.jpg',
        'Máy bay VietNam AirLines', 3, 4),
       (7, NULL, 3, '2021-05-22 22:27:57', _binary '\0', 3, '2021-05-30 00:07:16', NULL,
        '<h3>Tham Gia Nghề N&ocirc;ng Trại Đ&agrave; Lạt</h3>\r\n\r\n<p>Từ l&acirc;u Đ&agrave; Lạt đ&atilde; trở th&agrave;nh thi&ecirc;n đường của rau v&agrave; hoa v&agrave; rất nhiều du kh&aacute;ch, đ&oacute; ch&iacute;nh l&agrave; l&ecirc;n đ&agrave; lạt chỉ để ăn rau củ quả sạch cho đ&atilde; v&igrave; đ&atilde; qu&aacute; ng&aacute;n với c&aacute;c sản phẩm kh&ocirc;ng r&otilde; nguồn gốc. Bạn l&agrave; người y&ecirc;u thi&ecirc;n nhi&ecirc;n, y&ecirc;u c&aacute;i đẹp v&agrave; đặc biệt l&agrave; sự độc lạ, muốn tận tay h&aacute;i rau, củ, quả th&igrave; h&atilde;y c&ugrave;ng iVIVU kh&aacute;m ph&aacute; ngay h&ocirc;m nay!&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>Những trải nghiệm th&uacute; vị trong chương tr&igrave;nh</h3>\r\n\r\n<p>Đến với thi&ecirc;n đường n&ocirc;ng trại chắc chắn sẽ gh&eacute; qua:</p>\r\n\r\n<p>-&nbsp;Khu du lịch&nbsp;<strong>Th&uacute;y Thuận Đ&agrave; Lạt</strong>&nbsp;hay qu&aacute;n cafe Th&uacute;y Thuận Đ&agrave; Lạt đang l&agrave; một trong top những địa điểm du lịch Đ&agrave; Lạt cực kỳ hot trong năm nay. C&oacute; thể n&oacute;i nơi đ&acirc;y l&agrave; xưởng c&agrave; ph&ecirc; lớn nhất Đ&ocirc;ng Nam &Aacute;.</p>\r\n\r\n<p>-&nbsp;<strong>Lạc ti&ecirc;n giới Đ&agrave; Lạt</strong>&nbsp;điểm sống ảo triệu view vừa mới nổi tại Đ&agrave; Lạt. Một địa điểm check in cực kỳ chill đậm chất trời &Acirc;u tại Đ&agrave; Lạt.</p>\r\n\r\n<p>-&nbsp;Chị em n&agrave;o &ldquo;lười&rdquo; di chuyển qua nhiều địa điểm kh&aacute;c nhau để chụp h&igrave;nh th&igrave;&nbsp;<strong>MAMA DALA</strong>&nbsp;l&agrave; một nơi tuyệt vời bạn sẽ được tha hồ check-in sương sương hơn 30 concept chụp ảnh v&agrave; nhiều concept vườn hoa đẹp mắt.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h3>Bạn c&oacute; sẵn s&agrave;ng</h3>\r\n',
        NULL, '1 ngày 2 đêm', '',
        '<h3>Chương tr&igrave;nh tour</h3>\r\n\r\n<p>8h30: Xe v&agrave; HDV&nbsp;c&ocirc;ng ty&nbsp;đ&oacute;n qu&yacute; kh&aacute;ch tại trung t&acirc;m th&agrave;nh phố&nbsp;<strong>Đ&agrave; Lạt</strong>, di chuyển đi tham quan:</p>\r\n\r\n<p>-&nbsp;<strong>Lạc Ti&ecirc;n Giới</strong>&nbsp;&ndash; Thung lũng check in v&ocirc; c&ugrave;ng ảo diệu (trải nghiệm xe jeep k&igrave; th&uacute;, hơn 30 tiểu cảnh tham hồ chọn lựa)&nbsp;</p>\r\n\r\n<p>&nbsp;<img alt=\"\" src=\"https://cdn2.ivivu.com/2021/03/29/09/ivivu-lac-tien-gioi-da-lat.gif\" /></p>\r\n\r\n<p><em>Lạc Ti&ecirc;n Giới.</em></p>\r\n\r\n<p>-&nbsp;<strong>Khu du lịch Th&uacute;y Thuận</strong>&nbsp;&ndash; Xưởng c&agrave; ph&ecirc; lớn ở Đ&agrave; Lạt.&nbsp;Nơi đ&acirc;y c&oacute; diện t&iacute;ch l&ecirc;n đến gần 4 ha, lại c&ograve;n được nằm tr&ecirc;n cao, c&oacute; view ngắm trọn đồi th&ocirc;ng v&agrave; thi&ecirc;n nhi&ecirc;n n&uacute;i rừng ở th&agrave;nh phố t&igrave;nh y&ecirc;u.&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/06/29/14/ivivu-thuy-thuan-coffee.gif\" /></p>\r\n\r\n<p><em>KDL Th&uacute;y Thuận.</em></p>\r\n\r\n<p>-&nbsp;<strong>C&agrave; ph&ecirc; M&ecirc; Linh</strong>&nbsp;- Thưởng thức Cafe Chồn nguy&ecirc;n chất (chi ph&iacute; tự t&uacute;c )</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/06/03/09/ivivu-me-linh-coffee.gif\" /></p>\r\n\r\n<p><em>Caphe M&ecirc; Linh.</em></p>\r\n\r\n<p>- C&aacute;nh đồng hoa&nbsp;<strong>Tam Giác Mạch</strong>, hoa cánh bướm, hoa Hướng Dương&hellip;( Phụ thuộc v&agrave;o từng m&ugrave;a hoa sẽ cho kh&aacute;ch đi tham quan vườn hoa đ&oacute;).</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/06/03/09/ivivu-tam-giac-mach.gif\" /></p>\r\n\r\n<p><em>Vườn Hoa Tam Gi&aacute;c Mạch.</em></p>\r\n\r\n<p>12h: Đo&agrave;n d&ugrave;ng cơm trưa tự t&uacute;c tại nh&agrave; h&agrave;ng, nghỉ ngơi.</p>\r\n\r\n<p>13h30: Đo&agrave;n tiếp tục tham quan:</p>\r\n\r\n<p>- C&aacute;nh đồng hoa&nbsp;<strong>Cẩm T&uacute; Cầu</strong>&nbsp;- Nấc thang l&ecirc;n thi&ecirc;n đường &ndash; C&acirc;y cầu v&agrave;ng.&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2021/02/01/15/ivivu-cau-vang-da-lat.gif\" /></p>\r\n\r\n<p><em>C&acirc;y Cầu V&agrave;ng Đ&agrave; Lạt.</em></p>\r\n\r\n<p>-&nbsp;<strong>Mama Dala</strong>&nbsp;&ndash; Tổ hợp check in si&ecirc;u Hot (hơn 30 concepts với Đồng hồ TikTak, Dương Sĩ cổ đại, nh&agrave; bong b&oacute;ng&hellip;)&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2021/03/29/09/ivivu-mama-da-lat.gif\" /></p>\r\n\r\n<p><em>Mama Dala.</em></p>\r\n\r\n<p>-&nbsp;<strong>Vườn d&acirc;u t&acirc;y</strong>: &nbsp;Những khu vườn xinh xắn trồng d&acirc;u t&acirc;y ở Đ&agrave; Lạt đang trở th&agrave;nh điểm tham quan hấp dẫn. Nhiều du kh&aacute;ch t&igrave;m đến c&aacute;c vườn d&acirc;u để được tự tay chọn những quả đẹp v&agrave; ngon nhất.&nbsp;</p>\r\n\r\n<p><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/06/03/10/ivivu-vuon-dau-dl.gif\" /></p>\r\n\r\n<p><em>Vườn D&acirc;u T&acirc;y.</em></p>\r\n\r\n<p>16h: Xe đưa qu&yacute; kh&aacute;ch về lại điểm đ&oacute;n ban đầu, kết th&uacute;c tour v&agrave; hẹn gặp lại.</p>\r\n\r\n<p><em>Thứ tự tham quan c&oacute; thể thay đổi nhưng vẫn đảm bảo đầy đủ điểm trong chương tr&igrave;nh.</em></p>\r\n',
        'Tour Đà Lạt 1N: Khám Phá Địa Điểm Mới Tại Đà Lạt', '', '',
        'http://res.cloudinary.com/jstit/image/upload/v1622307968/9tour/tour/n3bjstiydmv7kcsvmjay.jpg',
        'Máy bay Vietjet', 6, 4),
       (8, NULL, 3, '2021-05-22 22:28:14', _binary '\0', 3, '2021-05-30 00:40:44', NULL,
        '<h3><s>Giao Lưu Bản Sắc Văn H&oacute;a Miền Bắc Bộ</s></h3>\r\n\r\n<p><s>Miền Bắc &ndash; v&ugrave;ng đất khai cơ của c&aacute;c Vương triều Việt, nơi định đ&ocirc; của hầu hết c&aacute;c triều đại phong kiến Việt Nam ch&iacute;nh v&igrave; vậy nơi đ&acirc;y được xem như l&agrave; đất kinh đ&ocirc; ng&agrave;n năm văn hiến với một bề d&agrave;y văn h&oacute;a, lịch sử s&acirc;u sắc v&agrave; đa dạng. Tuy nhi&ecirc;n, khi đến đ&acirc;y, Du kh&aacute;ch kh&ocirc;ng chỉ được tham quan những c&ocirc;ng tr&igrave;nh văn h&oacute;a - lịch sử cổ k&iacute;nh như: Đền H&ugrave;ng, Văn Miếu, Ho&agrave;ng th&agrave;nh Thăng Long, Cố đ&ocirc; Hoa Lư, Ch&ugrave;a B&aacute;i Đ&iacute;nh&hellip;, trải nghiệm những n&eacute;t văn h&oacute;a đặc sắc của đất kinh đ&ocirc; như : ngoạn cảnh 36 phố phường, thưởng thức ẩm thực, xem m&uacute;a rối nước&hellip;; m&agrave; c&ograve;n c&oacute; thể thăm th&uacute; c&aacute;c thắng cảnh nổi tiếng nơi đ&acirc;y như : Di sản thế giới Danh thắng Tr&agrave;ng An v&agrave; Vịnh Hạ Long...Ngo&agrave;i ra Du kh&aacute;ch c&ograve;n c&oacute; thể h&ograve;a m&igrave;nh v&agrave;o cuộc sống b&igrave;nh dị nhưng đậm bản sắc của cộng đồng d&acirc;n tộc &iacute;t người v&ugrave;ng cao như : Th&aacute;i, Hmong, Dao, Mường, T&agrave;y&hellip; C&ugrave;ng iVIVU kh&aacute;m ph&aacute; ngay h&ocirc;m nay!&nbsp;</s></p>\r\n\r\n<h3><s>Những trải nghiệm th&uacute; vị trong chương tr&igrave;nh</s></h3>\r\n\r\n<p><s>Li&ecirc;n tuyến kh&aacute;m ph&aacute; những điểm tuyệt đẹp của tạo h&oacute;a ban tặng, những c&ocirc;ng tr&igrave;nh kiến tr&uacute;c do b&agrave;n tay con người l&agrave;m ra qua những điểm:</s></p>\r\n\r\n<p><s>-&nbsp;<strong>H&agrave; Nội</strong>&nbsp;với 36 Phố Phường, tham quan th&aacute;p r&ugrave;a, cầu Th&ecirc; H&uacute;c, ch&ugrave;a Trấn Quốc, Lăng B&aacute;c, Văn Miếu Quốc Tử Gi&aacute;m...</s></p>\r\n\r\n<p><s>- Khu du lịch&nbsp;<strong>Tr&agrave;ng An&nbsp;Mới</strong>&nbsp;với những dải đ&aacute; v&ocirc;i, thung lũng v&agrave; những s&ocirc;ng ng&ograve;i đan xen tạo n&ecirc;n một kh&ocirc;ng gian huyền ảo, kỳ b&iacute;.</s></p>\r\n\r\n<p><s>- Kh&aacute;m ph&aacute;&nbsp;<strong>Vịnh Hạ Long</strong>&nbsp;bằng du thuyền đẳng cấp 5 sao.&nbsp;</s></p>\r\n\r\n<p><s>- Chinh phục nốc nh&agrave;&nbsp;<strong>Đ&ocirc;ng Dương Fansipang.</strong></s></p>\r\n',
        NULL, '5 ngày 4 đêm', '',
        '<h3><s>Chương tr&igrave;nh tour</s></h3>\r\n\r\n<h3><s>NG&Agrave;Y 1: Đ&Oacute;N BAY NỘI B&Agrave;I &ndash; H&Agrave; NỘI</s></h3>\r\n\r\n<p><s>Xe đ&oacute;n kh&aacute;ch tại s&acirc;n bay&nbsp;<strong>Nội B&agrave;i</strong>: Xe đ&oacute;n Qu&yacute; Kh&aacute;ch về Kh&aacute;ch sạn nhận ph&ograve;ng (C&ocirc;ng ty sẽ bố tr&iacute; xe đ&oacute;n theo lịch bay của Kh&aacute;ch).</s></p>\r\n\r\n<p><s>Tự do kh&aacute;m ph&aacute; thủ đ&ocirc;&nbsp;<strong>H&agrave; Nội</strong>&nbsp;với 36 Phố Phường, tham quan&nbsp;<strong>Hồ Ho&agrave;n Kiếm, cầu Th&ecirc; H&uacute;c, ch&ugrave;a Trấn Quốc, Lăng B&aacute;c, Văn Miếu Quốc Tử Gi&aacute;m</strong>,&hellip; ( Qu&yacute; Kh&aacute;ch c&oacute; thể thu&ecirc; xe x&iacute;ch l&ocirc;, xe điện hoặc thu&ecirc; xe m&aacute;y tự do kh&aacute;m ph&aacute; thủ đ&ocirc; H&agrave; Nội.)&nbsp;</s></p>\r\n\r\n<p><s><img alt=\"\" src=\"https://cdn2.ivivu.com/2019/12/27/13/ivivu-chua-tran-quoc.gif\" /></s></p>\r\n\r\n<p><s><em>Ch&ugrave;a Trấn Quốc.</em></s></p>\r\n\r\n<p><s>Qu&yacute; Kh&aacute;ch tự do ăn tối. Thưởng thức ẩm thực phố cổ mang n&eacute;t văn h&oacute;a của H&agrave; Nội xưa như chả c&aacute; L&atilde; Vọng, b&uacute;n Thang, phở L&yacute; Quốc Sư, phở cuốn Ngũ X&atilde;,&hellip;.&nbsp;</s></p>\r\n\r\n<p><s>Ngủ đ&ecirc;m tại Kh&aacute;ch sạn 3 sao phố cổ&nbsp;<strong>H&agrave; Nội.</strong></s></p>\r\n\r\n<h3><s>NG&Agrave;Y 2: H&Agrave; NỘI - CH&Ugrave;A B&Aacute;I Đ&Iacute;NH &ndash; KDL TR&Agrave;NG AN &ndash; ĐẠP XE ( ĂN S&Aacute;NG, TRƯA)</s></h3>\r\n\r\n<p><s>S&aacute;ng: Qu&yacute; kh&aacute;ch d&ugrave;ng bữa s&aacute;ng tại kh&aacute;ch sạn.</s></p>\r\n\r\n<p><s>7h00 &ndash; 7h30: Xe v&agrave; Hướng dẫn vi&ecirc;n đ&oacute;n Qu&yacute; kh&aacute;ch tại kh&aacute;ch sạn, sau đ&oacute; khởi h&agrave;nh đi&nbsp;<strong>Ninh B&igrave;nh</strong>&nbsp;&ndash; một trong những điểm du lịch nổi tiếng thuộc ven&nbsp;<strong>Đồng Bằng S&ocirc;ng Hồng</strong>, c&aacute;ch H&agrave; Nội gần 110km về ph&iacute;a nam.</s></p>\r\n\r\n<p><s>10h00:&nbsp;Đo&agrave;n đến khu&acirc;n vi&ecirc;n&nbsp;<strong>ch&ugrave;a B&aacute;i Đ&iacute;nh</strong>, bắt đầu thăm quan v&agrave; lễ phật tại đ&acirc;y. Đo&agrave;n thăm Điện thờ&nbsp;<strong>Tam Thế</strong>&nbsp;v&agrave; Ph&aacute;p Chủ c&oacute; diện t&iacute;ch l&ecirc;n tới 1.000 m2; Phật B&agrave; quan &Acirc;m; Đức Đạt Ma sư tổ; Tượng&nbsp;Phật bằng đồng lớn nhất&nbsp;<strong>Đ&ocirc;ng Nam &Aacute;</strong>; ba pho Tam Thế mỗi pho nặng 50 tấn; Hai quả chu&ocirc;ng lớn nhất Đ&ocirc;ng Nam &Aacute;: 36 v&agrave; 27 tấn.</s></p>\r\n\r\n<p><s><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/06/25/15/ivivu-chua-bai-dinh.gif\" /></s></p>\r\n\r\n<p><s><em>Ch&ugrave;a B&aacute;i Đ&iacute;nh.</em></s></p>\r\n\r\n<p><s>12h00: Qu&yacute; kh&aacute;ch nghỉ ngơi ăn trưa tại nh&agrave; h&agrave;ng với đặc sản thịt d&ecirc; &ndash; cơm ch&aacute;y&nbsp;<strong>Ninh B&igrave;nh.</strong></s></p>\r\n\r\n<p><s>13h30:&nbsp;Sau bữa trưa, xe đưa qu&yacute; kh&aacute;ch ra bến đ&ograve;&nbsp;<strong>Tr&agrave;ng An</strong>&nbsp;l&ecirc;n thuyền đi dọc theo suối giữa c&aacute;nh đồng l&uacute;a thăm&nbsp;khu du lịch&nbsp;<strong>Tr&agrave;ng An&nbsp;Mới</strong>&nbsp;với những dải đ&aacute; v&ocirc;i, thung lũng v&agrave; những s&ocirc;ng ng&ograve;i đan xen tạo n&ecirc;n một kh&ocirc;ng gian huyền ảo, kỳ b&iacute;.</s></p>\r\n\r\n<p><s>&nbsp;<img alt=\"\" src=\"https://cdn2.ivivu.com/2020/11/20/15/ivivu-kdl-trang-an.gif\" /></s></p>\r\n\r\n<p><s><em>KDL Tr&agrave;ng An.</em></s></p>\r\n\r\n<p><s>Với tuyến n&agrave;y qu&yacute; kh&aacute;ch&nbsp;c&oacute; cơ hội tham quan&nbsp;4 hang động&nbsp;tự nhi&ecirc;n trong số những hang động đẹp nhất trong khu du lịch&nbsp;<strong>Tr&agrave;ng An</strong>&nbsp;l&agrave;&nbsp;<strong>Hang Lấm, Hang Vạng, Hang Th&aacute;nh Trượt</strong>&nbsp;v&agrave; hang cuối c&ugrave;ng l&agrave; Hang Đại, qu&yacute; kh&aacute;ch c&ograve;n dừng ch&acirc;n gh&eacute; thăm&nbsp;3 điểm t&acirc;m linh&nbsp;l&agrave; đền Cao Sơn, đền Suối Ti&ecirc;n v&agrave; H&agrave;nh Cung Vũ L&acirc;m. Tuyến n&agrave;y sẽ rất ph&ugrave; hợp với những bạn trẻ, những người th&iacute;ch chụp ảnh Check in, qu&yacute; kh&aacute;ch c&oacute; thể c&oacute; những trải nghiệm đầy đủ về h&agrave;nh tr&igrave;nh ngồi đ&ograve; thăm hang cũng như c&oacute; thể lưu lại những cảm x&uacute;c, những bức h&igrave;nh đẹp trong h&agrave;nh tr&igrave;nh về thăm&nbsp;Tr&agrave;ng An &ndash; Di sản văn h&ograve;a v&agrave; thi&ecirc;n nhi&ecirc;n thế giới!&nbsp;</s></p>\r\n\r\n<p><s><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/11/20/15/ivivu-tham-quan-hang-dong.gif\" /></s></p>\r\n\r\n<p><s><em>Tham Quan Hang Động.</em></s></p>\r\n\r\n<p><s>B&ecirc;n cạnh loại h&igrave;nh ngồi thuyền ngắm cảnh, giờ đ&acirc;y du kh&aacute;ch c&ograve;n c&oacute; th&ecirc;m trải nghiệm mới, đ&oacute; l&agrave; tự&nbsp;ch&egrave;o thuyền Kayak&nbsp;ngắm vẻ đẹp h&ugrave;ng vĩ của Di sản Văn h&oacute;a v&agrave; Thi&ecirc;n nhi&ecirc;n thế giới&nbsp;<strong>Tr&agrave;ng An.</strong></s></p>\r\n\r\n<p><s>16h00: Qu&yacute; kh&aacute;ch đạp xe đạp ngắm cảnh l&agrave;ng Qu&ecirc;, h&iacute;t h&agrave; kh&ocirc;ng kh&iacute; trong l&agrave;nh.</s></p>\r\n\r\n<p><s>17h00: Qu&yacute; kh&aacute;ch l&ecirc;n xe về&nbsp;<strong>H&agrave; Nội.</strong></s></p>\r\n\r\n<p><s>19h30:&nbsp;Về đến H&agrave; Nội, xe đưa Qu&yacute; Kh&aacute;ch về lại Kh&aacute;ch sạn, tự do ăn tối, kh&aacute;m ph&aacute; Phố Cổ nhộn nhịp, đi chợ đ&ecirc;m Đồng Xu&acirc;n, dạo chơi phố đi bộ,&hellip;.</s></p>\r\n\r\n<p><s>Ngủ đ&ecirc;m tại Kh&aacute;ch sạn 3 sao phố cổ H&agrave; Nội.</s></p>\r\n\r\n<h3><s>NG&Agrave;Y 3: H&Agrave; NỘI &ndash; HẠ LONG &nbsp;C&Ugrave;NG DU THUYỀN 5 SAO 1 NG&Agrave;Y ( ĂN S&Aacute;NG, TRƯA)</s></h3>\r\n\r\n<p><s>Qu&yacute; Kh&aacute;ch ăn s&aacute;ng tại Kh&aacute;ch sạn.</s></p>\r\n\r\n<p><s>8h45-9h15:&nbsp;Xe v&agrave; hướng dẫn vi&ecirc;n đ&oacute;n qu&yacute; kh&aacute;ch khởi h&agrave;nh đi&nbsp;<strong>Hạ Long</strong>&nbsp;qua đường cao tốc 5B &ndash; Hải Ph&ograve;ng &ndash; Hạ Long.</s></p>\r\n\r\n<p><s>12h00: Đến cảng t&agrave;u&nbsp;<strong>Tuần Ch&acirc;u</strong>, Qu&yacute; kh&aacute;ch xuống t&agrave;u để bắt đầu h&agrave;nh tr&igrave;nh kh&aacute;m ph&aacute; Vịnh Hạ Long.</s></p>\r\n\r\n<p><s>T&agrave;u sẽ đưa Qu&yacute; kh&aacute;ch đi thăm vịnh theo h&agrave;nh tr&igrave;nh 6 Tiếng, giống với h&agrave;nh tr&igrave;nh của những con t&agrave;u ngủ 2 ng&agrave;y 1 đ&ecirc;m. T&agrave;u sẽ đi qua v&ocirc; v&agrave;n c&aacute;c h&ograve;n đảo lớn nhỏ với nhiều h&igrave;nh d&aacute;ng kh&aacute;c nhau v&agrave; được những ngư d&acirc;n nơi đ&acirc;y đặt cho những c&aacute;c t&ecirc;n đặc biệt như:&nbsp;<strong>H&ograve;n G&agrave; Chọi, h&ograve;n Ch&oacute; Đ&aacute;</strong>, &hellip; Qu&yacute; kh&aacute;ch thưởng thức bữa trưa tr&ecirc;n t&agrave;u.&nbsp;</s></p>\r\n\r\n<p><s>&nbsp;<img alt=\"\" src=\"https://cdn2.ivivu.com/2020/10/19/17/ivivu-hon-cho-da.gif\" /></s></p>\r\n\r\n<p><s><em>H&ograve;n Ch&oacute; Đ&aacute;.</em></s></p>\r\n\r\n<p><s>14h00: Đến đảo&nbsp;<strong>Bồ H&ograve;n</strong>. Qu&yacute; kh&aacute;ch thăm quan&nbsp;<strong>Hang Sửng Sốt</strong>&nbsp; - Một trong những hang động đẹp nhất&nbsp;<strong>vịnh Hạ Long</strong>.&nbsp;</s></p>\r\n\r\n<p><s><img alt=\"\" src=\"https://cdn2.ivivu.com/2019/03/04/16/ivivu-tour-ha-long-hang-sung-sot.jpg\" /></s></p>\r\n\r\n<p><s><em>Hang Sửng Sốt.</em></s></p>\r\n\r\n<p><s>Tiếp theo, Qu&yacute; kh&aacute;ch sẽ ch&egrave;o Kayak hoặc đi thuyền nan thăm&nbsp;<strong>Hang Luồn.</strong></s></p>\r\n\r\n<p><s>Sau 30 ph&uacute;t ch&egrave;o Kayak, Qu&yacute; kh&aacute;ch l&ecirc;n t&agrave;u để đến với đảo&nbsp;<strong>Ti Top</strong>. Qu&yacute; kh&aacute;ch c&oacute; thể tắm biển tại b&atilde;i tắm TiTop với b&atilde;i c&aacute;t trắng, hoặc thử trekking leo l&ecirc;n đỉnh n&uacute;i Ti Top ngắm nh&igrave;n to&agrave;n cảnh&nbsp;<strong>Vịnh Hạ Long.</strong></s></p>\r\n\r\n<p><s><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/06/10/15/ivivu-dao-titop.gif\" /></s></p>\r\n\r\n<p><s><em>Đảo Ti Tốp.</em></s></p>\r\n\r\n<p><s>17h00: Qu&yacute; kh&aacute;ch quay trở lại t&agrave;u. T&agrave;u sẽ di chuyển đưa Qu&yacute; kh&aacute;ch về lại cảng&nbsp;<strong>Tuần Ch&acirc;u</strong>. Qu&yacute; kh&aacute;ch c&oacute; thể tắm nắng tr&ecirc;n boong t&agrave;u, nghe nhạc v&agrave; thư gi&atilde;n, h&ograve;a m&igrave;nh v&agrave;o thi&ecirc;n nhi&ecirc;n của Vịnh Hạ Long.</s></p>\r\n\r\n<p><s>18h00: Về tới cảng t&agrave;u&nbsp;<strong>Tuần Ch&acirc;u</strong>. Qu&yacute; kh&aacute;ch l&ecirc;n xe trở về H&agrave; Nội qua đường cao tốc 5B nối liền&nbsp;<strong>Hạ Long &ndash; H&agrave; Nội.</strong></s></p>\r\n\r\n<p><s>20h30:&nbsp;Qu&yacute; kh&aacute;ch về đến&nbsp;<strong>H&agrave; Nội</strong>. Xe đưa Qu&yacute; kh&aacute;ch về lại kh&aacute;ch sạn. Tự do ăn tối v&agrave; ngủ đ&ecirc;m tại H&agrave; Nội.</s></p>\r\n\r\n<p><s>Ngủ đ&ecirc;m tại Kh&aacute;ch sạn 3 sao phố cổ H&agrave; Nội.</s></p>\r\n\r\n<h3><s>NG&Agrave;Y 4: H&Agrave; NỘI &ndash; SAPA &ndash; KDL H&Agrave;M RỒNG ( ĂN S&Aacute;NG, TRƯA, TỐI)</s></h3>\r\n\r\n<p><s>S&aacute;ng: Qu&yacute; Kh&aacute;ch ăn s&aacute;ng sớm tại kh&aacute;ch sạn v&agrave; l&agrave;m thủ tục trả ph&ograve;ng. (Trường hợp Qu&yacute; kh&aacute;ch kh&ocirc;ng kịp ăn s&aacute;ng, qu&yacute; kh&aacute;ch tự t&uacute;c ăn bữa s&aacute;ng tr&ecirc;n đường đi).</s></p>\r\n\r\n<p><s>6h30: Xe đ&oacute;n Qu&yacute; Kh&aacute;ch ra xe giường nằm để l&ecirc;n xe khởi h&agrave;nh đi&nbsp;<strong>Sapa.</strong></s></p>\r\n\r\n<p><s>9h30-10h00: Xe dừng tại điểm nghỉ tr&ecirc;n đường cao tốc&nbsp;<strong>Nội B&agrave;i &ndash; L&agrave;o Cai</strong>&nbsp;để qu&yacute; kh&aacute;ch nghỉ ngơi v&agrave; vệ sinh c&aacute; nh&acirc;n, tự t&uacute;c ăn s&aacute;ng nếu qu&yacute; kh&aacute;ch chưa ăn s&aacute;ng.</s></p>\r\n\r\n<p><s>12h30-13h00: Xe đến thị trấn<strong>&nbsp;Sa Pa</strong>&nbsp;nơi c&oacute; rất nhiều d&acirc;n tộc sinh sống như&nbsp;<strong>H&rsquo;mong, Dao, T&agrave;y</strong>,&hellip; hướng dẫn vi&ecirc;n sẽ đưa Qu&yacute; kh&aacute;ch về kh&aacute;ch sạn:</s></p>\r\n\r\n<p><s>Ăn trưa tại nh&agrave; h&agrave;ng kh&aacute;ch sạn sau đ&oacute; nhận ph&ograve;ng v&agrave; nghỉ ngơi.</s></p>\r\n\r\n<p><s>14h30: Qu&yacute; kh&aacute;ch khởi động bằng cuộc chinh phục leo l&ecirc;n đỉnh n&uacute;i&nbsp;<strong>H&agrave;m Rồng</strong>&nbsp;&ndash; khu vực địa chất được cấu th&agrave;nh từ nham thạch n&uacute;i lửa, với những h&igrave;nh dạng hết sức độc đ&aacute;o v&agrave; kỳ th&uacute;. Tham quan c&aacute;c điểm:&nbsp;Vườn Lan 1- 2, vườn L&ecirc;, vườn T&aacute;o M&egrave;o,&nbsp;Vườn hoa trung t&acirc;m, H&ograve;n Đ&aacute; G&atilde;y, Cổng trời 1, Cổng trời 2,&nbsp;Đầu Rồng, H&ograve;n C&aacute; Sấu, Khu Thi&ecirc;n Th&aacute;ch L&acirc;m, H&ograve;n Phật B&agrave;, S&acirc;n M&acirc;y, th&aacute;p truyền h&igrave;nh&nbsp;...,Quý khách kh&aacute;m ph&aacute; khu du lịch&nbsp;H&agrave;m Rồng,&nbsp; ngắm nh&igrave;n to&agrave;n cảnh&nbsp;Sa Pa&nbsp;từ tr&ecirc;n cao từ ch&acirc;n tháp truy&ecirc;̀n hình. Đặc biệt, qu&yacute; kh&aacute;ch c&ograve;n được thưởng thức v&agrave; c&ugrave;ng tham gia c&aacute;c điệu m&uacute;a d&acirc;n tộc, c&aacute;c b&agrave;i h&aacute;t của n&uacute;i rừng T&acirc;y Bắc,&hellip;</s></p>\r\n\r\n<p><s><img alt=\"\" src=\"https://cdn2.ivivu.com/2016/10/06/16/ivivu-tour-mien-bac-6n5d-sai-gon-moc-chau-son-la-sapa-ham-rong.jpg\" /></s></p>\r\n\r\n<p><s><em>KDL H&agrave;m Rồng.</em></s></p>\r\n\r\n<p><s>19h00: Qu&yacute; kh&aacute;ch d&ugrave;ng bữa tối.</s></p>\r\n\r\n<p><s>Sau bữa tối, tự do kh&aacute;m ph&aacute; thị trấn&nbsp;<strong>Sapa</strong>&nbsp;về đ&ecirc;m: Thăm&nbsp;<strong>nh&agrave; thờ đ&aacute; Sapa</strong>, đi chợ ẩm thực Sapa v&agrave; thưởng thức những m&oacute;n nướng đặc trưng của v&ugrave;ng đất n&agrave;y,&hellip;</s></p>\r\n\r\n<p><s><img alt=\"\" src=\"https://cdn2.ivivu.com/2019/10/17/14/ivivu-nha-tho-da-sapa4.jpg\" /></s></p>\r\n\r\n<p><s><em>Nh&agrave; Thờ Đ&aacute; Sapa.</em></s></p>\r\n\r\n<p><s>Ngủ đ&ecirc;m tại kh&aacute;ch sạn 3 sao tại&nbsp;<strong>Sapa.</strong></s></p>\r\n\r\n<h3><s>NG&Agrave;Y 5: SAPA &ndash; &nbsp;FANSIPAN &ndash; S&Acirc;N BAY NỘI B&Agrave;I/ H&Agrave; NỘI ( ĂN S&Aacute;NG, TRƯA)</s></h3>\r\n\r\n<p><s>7h00: Qu&yacute; kh&aacute;ch d&ugrave;ng bữa s&aacute;ng tại kh&aacute;ch sạn. L&agrave;m thủ tục trả ph&ograve;ng v&agrave; gửi đồ tại Quầy lễ t&acirc;n.</s></p>\r\n\r\n<p><s>8h00: Sau bữa ăn s&aacute;ng: Xe đ&oacute;n Qu&yacute; kh&aacute;ch khởi h&agrave;nh ra ga c&aacute;p trep Fansipan, đi c&aacute;p treo 3 d&acirc;y d&agrave;i nhất thế giới với chiều d&agrave;i 6.292,5m. Qu&yacute; kh&aacute;ch sẽ được ngắm nh&igrave;n thung lũng&nbsp;<strong>Mường Hoa</strong>, d&atilde;y&nbsp;<strong>Ho&agrave;ng Li&ecirc;n Sơn</strong>&nbsp;v&agrave; biển m&acirc;y tuyệt đẹp từ tr&ecirc;n cao.</s></p>\r\n\r\n<p><s>Sau 15 ph&uacute;t đi c&aacute;p treo, Qu&yacute; kh&aacute;ch tiếp tục leo bộ 604 bậc thang để đến với đỉnh&nbsp;<strong>Fansipan</strong>. Qu&yacute; kh&aacute;ch chụp h&igrave;nh để lưu lại khoảnh kh&aacute;ch đ&aacute;ng nhớ n&agrave;y.</s></p>\r\n\r\n<p><s><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/10/20/15/ivivu-fansipan.gif\" /></s></p>\r\n\r\n<p><s><em>Fansipan.</em></s></p>\r\n\r\n<p><s>Sau đ&oacute; Qu&yacute; kh&aacute;ch trở lại ga c&aacute;p treo để di chuyển xuống.</s></p>\r\n\r\n<p><s>11h30: Qu&yacute; kh&aacute;ch d&ugrave;ng bữa trưa buffet tại nh&agrave; h&agrave;ng&nbsp;<strong>Hải Cảng Fansipan</strong>&nbsp;(&Aacute;p dụng tới 31/12/2020; Sau thời gian tr&ecirc;n sẽ ăn trưa setmenu tại nh&agrave; h&agrave;ng).</s></p>\r\n\r\n<p><s>(Trường hợp Qu&yacute; kh&aacute;ch kh&ocirc;ng muốn đi&nbsp;<strong>Fansipan</strong>&nbsp;m&agrave; muốn đi thăm quan&nbsp;<strong>bản C&aacute;t C&aacute;t</strong>: Phụ 90.000vnđ v&eacute; thăm quan&nbsp;<strong>C&aacute;t C&aacute;t</strong>, v&agrave; sẽ ăn trưa setmenu tại nh&agrave; h&agrave;ng)&nbsp;</s></p>\r\n\r\n<p><s><img alt=\"\" src=\"https://cdn2.ivivu.com/2020/06/15/17/ivivu-ban-cat-cat-2.gif\" /></s></p>\r\n\r\n<p><s><em>Bản C&aacute;t C&aacute;t.</em></s></p>\r\n\r\n<p><s>12h30-13h00: Xe đ&oacute;n Qu&yacute; kh&aacute;ch về lại kh&aacute;ch sạn. Qu&yacute; kh&aacute;ch tự do mua sắm đặc sản địa phương về l&agrave;m qu&agrave; cho người th&acirc;n.</s></p>\r\n\r\n<p><s>15h30-16h00: Qu&yacute; Kh&aacute;ch l&ecirc;n xe giường nằm khởi h&agrave;nh về lại&nbsp;<strong>H&agrave; Nội.</strong></s></p>\r\n\r\n<p><s>+ Đối với những kh&aacute;ch c&oacute; chuyến bay tối n&ecirc;n đặt c&aacute;c chuyến bay sau 22h30: Xe từ&nbsp;<strong>Sapa</strong>&nbsp;về đến s&acirc;n bay Nội B&agrave;i l&uacute;c 20h30-21h00, dừng cho Qu&yacute; kh&aacute;ch v&agrave;o s&acirc;n bay tự t&uacute;c l&agrave;m thủ tục bay.</s></p>\r\n\r\n<p><s>+ Đối với những kh&aacute;ch đ&atilde; đặt chuyến bay về khoảng 21h00-22h30: C&ocirc;ng ty sẽ bố tr&iacute; cho Qu&yacute; kh&aacute;ch l&ecirc;n xe từ&nbsp;<strong>Sapa</strong>&nbsp;về l&uacute;c 13h30 &ndash; V&agrave; về tới s&acirc;n bay&nbsp;<strong>Nội B&agrave;i</strong>&nbsp;khoảng 19h00-19h30.</s></p>\r\n\r\n<p><s>(Qu&yacute; kh&aacute;ch kh&ocirc;ng n&ecirc;n đặt chuyến bay về trước 21h00, nếu Qu&yacute; kh&aacute;ch đặt chuyến bay về trước 21h00 ch&uacute;ng t&ocirc;i sẽ kh&ocirc;ng thể bố tr&iacute; giờ về hợp l&yacute; để chuyến đi của Qu&yacute; kh&aacute;ch được trọn vẹn)</s></p>\r\n\r\n<p><s>+ Đối với những kh&aacute;ch về lại&nbsp;<strong>H&agrave; Nội</strong>: 21h30 về tới&nbsp;<strong>Phố Cổ H&agrave; Nội.</strong></s></p>\r\n\r\n<p><s>Kết th&uacute;c h&agrave;nh tr&igrave;nh du lịch 5 ng&agrave;y 4 đ&ecirc;m đầy th&uacute; vị v&agrave; hẹn gặp lại Qu&yacute; kh&aacute;ch trong những h&agrave;nh tr&igrave;nh du lịch lần sau.</s></p>\r\n\r\n<p><s><em>Lịch tr&igrave;nh thăm quan c&oacute; thể thay đổi nhưng vẫn đảm bảo c&aacute;c điểm thăm quan c&oacute; trong chương tr&igrave;nh !&nbsp;</em></s></p>\r\n',
        'Tour Miền Bắc 5N4D: Hà Nội - Bái Đính - Tràng An - Hạ Long - Sapa - Fansipan', '', '',
        'http://res.cloudinary.com/jstit/image/upload/v1622308144/9tour/tour/mxl32pj1pi7caftib568.jpg',
        'Máy bay Bamboo Airways', 6, 3);
/*!40000 ALTER TABLE `tour` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tour_extra_services`
--

DROP TABLE IF EXISTS `tour_extra_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_extra_services`
(
    `tours_id`          bigint NOT NULL,
    `extra_services_id` bigint NOT NULL,
    KEY                 `FKf49cs45jhtmrrjiv53u752kix` (`extra_services_id`),
    KEY                 `FKh99klfeqcyhn1cpdyd8uoe1ea` (`tours_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_extra_services`
--

LOCK
TABLES `tour_extra_services` WRITE;
/*!40000 ALTER TABLE `tour_extra_services` DISABLE KEYS */;
INSERT INTO `tour_extra_services`
VALUES (2, 1),
       (3, 1),
       (7, 2),
       (5, 2),
       (1, 1),
       (6, 3);
/*!40000 ALTER TABLE `tour_extra_services` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tour_places`
--

DROP TABLE IF EXISTS `tour_places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tour_places`
(
    `tours_id`  bigint NOT NULL,
    `places_id` bigint NOT NULL,
    KEY         `FKeeoc94itytbhjb3m00rlv41vr` (`places_id`),
    KEY         `FKr5boo1eh3er4osyhkebqahr3b` (`tours_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tour_places`
--

LOCK
TABLES `tour_places` WRITE;
/*!40000 ALTER TABLE `tour_places` DISABLE KEYS */;
INSERT INTO `tour_places`
VALUES (7, 4),
       (3, 4),
       (4, 4),
       (6, 4),
       (6, 3),
       (7, 6),
       (8, 3),
       (8, 6),
       (2, 3),
       (1, 3),
       (5, 6);
/*!40000 ALTER TABLE `tour_places` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip`
(
    `id`             bigint NOT NULL AUTO_INCREMENT,
    `code`           varchar(255) DEFAULT NULL,
    `created_by`     bigint       DEFAULT NULL,
    `created_date`   datetime     DEFAULT NULL,
    `is_deleted`     bit(1)       DEFAULT NULL,
    `modified_by`    bigint       DEFAULT NULL,
    `modified_date`  datetime     DEFAULT NULL,
    `adult_maximum`  int          DEFAULT NULL,
    `adult_price`    double       DEFAULT NULL,
    `child_maximum`  int          DEFAULT NULL,
    `child_price`    double       DEFAULT NULL,
    `departure`      varchar(255) DEFAULT NULL,
    `departure_time` datetime     DEFAULT NULL,
    `infant_maximum` int          DEFAULT NULL,
    `infant_price`   double       DEFAULT NULL,
    `return_time`    datetime     DEFAULT NULL,
    `tour_id`        bigint       DEFAULT NULL,
    `note`           longtext,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_823xpn1nks8t68msbnjwvqehi` (`code`),
    KEY              `FKblirw3te2fo677c44ehfrpt2c` (`tour_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK
TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip`
VALUES (1, 'TOUR-CĐ-1', 3, '2021-05-22 20:38:13', _binary '\0', 3, '2021-07-09 10:24:14', 100, 1900000, 50, 100000,
        'Sân bay Tân Sơn Nhất', '2022-12-01 10:00:00', 50, 0, '2022-12-31 11:30:00', 1, ''),
       (2, 'TOUR-ĐL-HN-1', 3, '2021-05-22 21:49:47', _binary '\0', 3, '2021-07-09 10:28:50', 50, 2000000, 20, 100000,
        'Nhà Xe Lâm Đồng', '2021-12-02 16:45:00', 10, 0, '2021-12-04 21:49:00', 2, ''),
       (3, 'TOUR-HN-2', 3, '2021-05-22 21:50:22', _binary '\0', 3, '2021-07-09 10:31:22', 50, 4000000, 10, 2000000,
        'Sân bay Tân Sơn Nhất', '2021-12-10 21:50:00', 0, 500000, '2021-12-23 21:50:00', 1, ''),
       (4, 'TOUR-HN-ĐL', 3, '2021-05-23 21:57:37', _binary '\0', 3, '2021-07-09 10:30:18', 50, 2000000, 25, 500000,
        'Sân bay Nội Bài', '2021-12-07 21:57:00', 25, 0, '2021-12-14 21:57:00', 3, ''),
       (5, 'CĐTST-13123', 3, '2021-05-23 22:00:04', _binary '\0', 3, '2021-07-09 10:33:55', 50, 1300000, 30, 600000,
        'Ga Sài Gòn', '2021-12-31 21:59:00', 30, 200000, '2022-01-02 22:00:00', 4, ''),
       (6, 'TOUR-HN_HCM', 3, '2021-05-29 23:54:10', _binary '\0', 3, '2021-07-09 10:33:00', 100, 1900000, 50, 1500000,
        'Sân bay Nội Bài', '2021-12-21 23:53:00', 50, 500000, '2021-07-22 23:53:00', 5, ''),
       (7, 'TOUR-HN-ĐL-2909', 3, '2021-05-30 00:04:42', _binary '\0', 3, '2021-07-09 10:29:26', 100, 1900000, 50,
        1500000, 'Sân bay Nội Bài', '2021-12-05 02:12:00', 30, 1000000, '2021-12-14 14:22:00', 6, ''),
       (8, 'TOUR-HCM-ĐL-2909', 3, '2021-05-30 00:06:55', _binary '\0', 3, '2021-07-09 10:33:18', 100, 4500000, 100,
        2500000, 'Sân bay Tân Sơn Nhất', '2021-12-11 00:06:00', 100, 1000000, '2021-08-25 00:06:00', 7, ''),
       (9, 'TOUR-HCM-HN_29052021', 3, '2021-05-30 00:10:09', _binary '\0', 3, '2021-07-09 10:32:34', 100, 3995000, 50,
        2590000, 'Văn Phòng Hoàn Kiếm', '2021-12-16 10:00:00', 50, 1500000, '2021-07-18 00:00:00', 8, '');
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`                   bigint NOT NULL AUTO_INCREMENT,
    `code`                 varchar(255) DEFAULT NULL,
    `created_by`           bigint       DEFAULT NULL,
    `created_date`         datetime     DEFAULT NULL,
    `is_deleted`           bit(1)       DEFAULT NULL,
    `modified_by`          bigint       DEFAULT NULL,
    `modified_date`        datetime     DEFAULT NULL,
    `address`              varchar(255) DEFAULT NULL,
    `email`                varchar(255) DEFAULT NULL,
    `first_name`           varchar(255) DEFAULT NULL,
    `is_enable`            bit(1)       DEFAULT NULL,
    `last_name`            varchar(255) DEFAULT NULL,
    `nationality`          varchar(255) DEFAULT NULL,
    `passport`             varchar(255) DEFAULT NULL,
    `password`             varchar(255) DEFAULT NULL,
    `phone`                varchar(255) DEFAULT NULL,
    `reset_password_token` varchar(255) DEFAULT NULL,
    `sex`                  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_h1vneshxbwkd1ailk02vdy2qu` (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK
TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user`
VALUES (4, NULL, 0, '2021-05-18 16:00:07', _binary '', 3, '2021-05-29 23:28:59',
        '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'test01@gmail.com', 'Tran', _binary '', 'Trần',
        'Việt Nam', '', '$2a$10$wKYZIKUB7wB7UNNhr567N.aJb8Ty7pcpNc84d/VYrVqjvZq9mXrwm', '090909090', NULL, 'Nam'),
       (5, NULL, NULL, NULL, _binary '\0', 5, '2021-06-24 23:42:39',
        '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'tqt23456@gmail.com', 'Quốc Tuấn', _binary '',
        'Trần', 'Việt Nam', '19001234', '$2a$10$YGi1tcGXw660k/B54CuFcuzup.Rj9aZwKu5VXAObhbFYYIbK1mRNe', '0902781404',
        NULL, 'Nam'),
       (3, NULL, NULL, NULL, _binary '\0', 3, '2021-06-18 17:02:51', '70 To Ky, Phuong Tan Chanh Hiep, Quan 12',
        'sadmin@9tour.xyz', 'SADMIN', _binary '', 'Trần', 'Việt Nam', '34895792384971',
        '$2y$10$ay5su37r41/kFt/OxI8rW.320BLd04yLzh6PfPHGDPNaWwvMj5X42', '19001234', NULL, 'Nam'),
       (6, NULL, 3, '2021-05-18 20:44:47', _binary '', 3, '2021-05-29 23:30:56',
        '114/50 Tan Chanh Hiep 18, Phuong Tan Chanh Hiep, Quan 12', 'admin@gmail.com', 'Tuấn', _binary '', 'Trần',
        'Việt Nam', '', '$2a$10$PrDMi7lhlvah7.1MylnPH.nsHys0CZyrEjWled18mmJqG/8qB5nTq', '0902781404', NULL, 'Nam'),
       (14, NULL, 3, '2021-06-18 17:03:53', _binary '\0', 3, '2021-06-18 17:03:53', '70 To Ky', 'admin@9tour.xyz',
        'ADMIN', _binary '', 'Nguyễn', 'Viet Nam', '23942394023',
        '$2a$10$1XS46D5DBOKiN.USgXhyqeaayUse8cGeUt7APunMtBtVUxsAfnehW', '19001234', NULL, 'Nam'),
       (15, NULL, 3, '2021-06-18 17:11:52', _binary '\0', 3, '2021-06-18 17:16:44', '70 To Ky', 'contact@nhatbao.xyz',
        'Bảo', _binary '', 'Nguyễn Nhật', 'Viet Nam', '83475923874598',
        '$2a$10$ttzd7i8g6KwHA7jLEcrWG.qolnoIMVJawPxkyoxpIEe5zF6opUEKS', '0332003045', NULL, 'Nam'),
       (16, NULL, 3, '2021-06-18 17:13:47', _binary '\0', 3, '2021-06-18 17:13:47', 'Ho Chi Minh', 'user@9tour.xyz',
        'USER', _binary '', 'Nguyễn', 'Viet Nam', '34534534534',
        '$2a$10$Q4rugdfwkzmE4CFYaqLZeuL7BztO4cEtnq19bdy2dLuMr3qz/q0QG', '12341239480', NULL, 'Nam'),
       (17, NULL, 3, '2021-06-18 17:14:58', _binary '\0', 3, '2021-06-18 17:14:58', 'Ho Chi Minh', 'staff@9tour.xyz',
        'STAFF', _binary '', 'Nguyễn', 'Viet Nam', '9238402938498',
        '$2a$10$EaLTox2zQpmz812.Y7o0/OxneKxhWgL7Udy.CPMRQ23ct147SpB1K', '344582349058', NULL, 'Nam');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles`
(
    `users_id` bigint NOT NULL,
    `roles_id` bigint NOT NULL,
    KEY        `FKj9553ass9uctjrmh0gkqsmv0d` (`roles_id`),
    KEY        `FK7ecyobaa59vxkxckg6t355l86` (`users_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK
TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles`
VALUES (3, 1),
       (17, 2),
       (16, 3),
       (15, 3),
       (15, 2),
       (5, 3),
       (5, 2),
       (12, 3),
       (13, 3),
       (14, 4);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-10 13:25:30
