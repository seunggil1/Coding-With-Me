-- MySQL dump 10.13  Distrib 8.0.30, for Linux (x86_64)
--
-- Host: localhost    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.20.04.2

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
-- Table structure for table `attendance_record`
--

DROP TABLE IF EXISTS `attendance_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance_record` (
  `attendance_id` bigint NOT NULL AUTO_INCREMENT,
  `att_end_time` time DEFAULT NULL,
  `att_start_time` time DEFAULT NULL,
  `date` date DEFAULT NULL,
  `conference_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`attendance_id`),
  KEY `FK70l31itk8uc70ci0etdqo20ve` (`conference_id`),
  KEY `FKlhef7jh53hbb54jhfen5how1n` (`user_id`),
  CONSTRAINT `FK70l31itk8uc70ci0etdqo20ve` FOREIGN KEY (`conference_id`) REFERENCES `conference` (`conference_id`),
  CONSTRAINT `FKlhef7jh53hbb54jhfen5how1n` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=291 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_record`
--

LOCK TABLES `attendance_record` WRITE;
/*!40000 ALTER TABLE `attendance_record` DISABLE KEYS */;
INSERT INTO `attendance_record` VALUES (6,'15:05:17','15:01:03','2022-07-05',1,3),(7,'16:05:17','15:01:03','2022-07-12',2,3),(8,'16:05:17','15:01:03','2022-07-19',3,3),(9,'15:05:17','15:01:03','2022-07-26',4,3),(10,'16:05:17','15:01:03','2022-08-02',5,3),(11,'16:05:17','15:01:03','2022-07-05',1,4),(12,'16:05:17','15:01:03','2022-07-12',2,4),(13,'16:05:17','15:01:03','2022-07-19',3,4),(14,'16:05:17','15:01:03','2022-07-26',4,4),(15,'16:05:17','15:01:03','2022-08-02',5,4),(16,'16:05:17','15:01:03','2022-07-05',1,5),(17,'16:05:17','15:01:03','2022-07-12',2,5),(18,'15:05:17','15:01:03','2022-07-19',3,5),(19,'15:05:17','15:01:03','2022-07-26',4,5),(20,'15:05:17','15:01:03','2022-08-02',5,5),(21,'16:05:17','15:01:03','2022-07-05',1,6),(22,'16:05:17','15:01:03','2022-07-12',2,6),(23,'16:05:17','15:01:03','2022-07-19',3,6),(24,'16:05:17','15:01:03','2022-07-26',4,6),(25,'16:05:17','15:01:03','2022-08-02',5,6),(275,'07:47:05','07:47:03','2022-08-16',508,31),(276,'10:09:19','10:09:17','2022-08-16',514,31),(277,'10:10:42','10:10:40','2022-08-16',515,31),(278,'10:25:24','10:25:23','2022-08-16',515,31),(279,'11:11:29','11:11:27','2022-08-16',515,31),(280,'11:39:29','11:39:28','2022-08-16',516,3),(281,'11:39:42','11:39:41','2022-08-16',516,5),(282,'11:39:46','11:39:44','2022-08-16',516,4),(283,'11:40:38','11:40:36','2022-08-16',516,5),(284,'11:56:35','11:56:33','2022-08-16',518,31),(285,'12:01:20','12:01:19','2022-08-16',520,31),(286,'12:24:12','12:24:10','2022-08-16',521,3),(287,'13:35:16','13:35:14','2022-08-16',522,40),(288,'13:38:36','13:38:34','2022-08-16',523,40),(289,'13:44:10','13:44:09','2022-08-16',523,40),(290,'13:52:25','13:52:23','2022-08-16',524,41);
/*!40000 ALTER TABLE `attendance_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `class_id` bigint NOT NULL AUTO_INCREMENT,
  `class_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `tutor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`class_id`),
  KEY `FKaw027lnecd9fj8ehbwalt9h5l` (`tutor_id`),
  CONSTRAINT `FKaw027lnecd9fj8ehbwalt9h5l` FOREIGN KEY (`tutor_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,'토스 코딩테스트 준비반','코딩테스트 준비반',1),(2,'공통프로젝트','서울 2반',1),(3,'공통프로젝트','부산 1반',22),(4,'공통프로젝트','대구 1반',23),(5,'3','refe',26),(6,'cowcow','cow 반',27),(7,'ㅇㅇ','마이반',29),(8,'최정예 스파르타 코딩 반','새로운 반',36),(9,'특화프로젝트','서울 3반',1),(10,'test입니다','test',39);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference`
--

DROP TABLE IF EXISTS `conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conference` (
  `conference_id` bigint NOT NULL AUTO_INCREMENT,
  `conf_end_time` time DEFAULT NULL,
  `conf_start_time` time DEFAULT NULL,
  `conference_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `owner_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rtc_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `thumbnail_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `class_id` bigint DEFAULT NULL,
  PRIMARY KEY (`conference_id`),
  KEY `FKfutji5j35yj2w717vk5ie2irp` (`class_id`),
  CONSTRAINT `FKfutji5j35yj2w717vk5ie2irp` FOREIGN KEY (`class_id`) REFERENCES `classes` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=526 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference`
--

LOCK TABLES `conference` WRITE;
/*!40000 ALTER TABLE `conference` DISABLE KEYS */;
INSERT INTO `conference` VALUES (1,'16:06:36','15:00:16','1주차 알고리즘 수업','2022-07-05',_binary '\0','cow',NULL,NULL,1),(2,'16:06:36','15:06:36','2주차 알고리즘 수업','2022-07-12',_binary '\0','cow',NULL,NULL,1),(3,'16:06:36','15:06:36','3주차 알고리즘 수업','2022-07-19',_binary '\0','cow',NULL,NULL,1),(4,'16:06:36','15:06:36','4주차 알고리즘 수업','2022-07-26',_binary '\0','cow',NULL,NULL,1),(5,'16:06:36','15:06:36','5주차 알고리즘 수업','2022-08-02',_binary '\0','cow',NULL,NULL,1),(507,'07:57:54','07:40:25','ddd','2022-08-16',_binary '\0','te4438','507',NULL,7),(508,'07:47:25','07:46:52','sdfa','2022-08-16',_binary '\0','ksgg1','508',NULL,5),(510,'09:17:21','09:14:54','ㅇㅊㅇㄴㅊ','2022-08-16',_binary '\0','ksgg1','510',NULL,5),(511,'09:19:18','09:17:24','ㅇㄴㄹㄴ','2022-08-16',_binary '\0','ksgg1','511',NULL,5),(512,'09:38:21','09:19:23','ㅇㅊㅍㅇㄴ','2022-08-16',_binary '\0','ksgg1','512',NULL,5),(513,'10:06:12','09:38:33','ㄴㅇㄹㄴ','2022-08-16',_binary '\0','ksgg1','513',NULL,5),(514,'10:10:35','10:06:20','sa','2022-08-16',_binary '\0','ksgg1','514',NULL,5),(515,'11:12:09','10:10:35','sdsd','2022-08-16',_binary '\0','ksgg1','515',NULL,5),(516,'11:53:03','11:39:18','6주차 알고리즘 수업','2022-08-16',_binary '\0','cow','516',NULL,1),(517,'11:54:25','11:54:04','sdf','2022-08-16',_binary '\0','ksgg1','517',NULL,5),(518,'12:00:47','11:55:54','dfsa','2022-08-16',_binary '\0','ksgg1','518',NULL,5),(519,'11:56:42','11:56:07','1주차 자바 강의','2022-08-16',_binary '\0','cow','519',NULL,9),(520,'15:46:57','12:01:05','dcs','2022-08-16',_binary '\0','ksgg1','520',NULL,5),(521,'12:30:27','12:24:03','7주차 알고리즘 수업','2022-08-16',_binary '\0','cow','521',NULL,1),(522,'13:36:01','13:34:58','강의시작!!','2022-08-16',_binary '\0','me1st','522',NULL,10),(523,'13:47:02','13:37:49','강의시작2','2022-08-16',_binary '\0','me1st','523',NULL,10),(524,NULL,'13:52:11','ㅁㄴㅇㄹ','2022-08-16',_binary '','live','524',NULL,10),(525,'16:02:38','15:46:57','wewd','2022-08-16',_binary '\0','ksgg1','525',NULL,5);
/*!40000 ALTER TABLE `conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference_history`
--

DROP TABLE IF EXISTS `conference_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conference_history` (
  `conference_id` bigint NOT NULL AUTO_INCREMENT,
  `class_id` bigint DEFAULT NULL,
  `conf_end_time` time DEFAULT NULL,
  `conf_start_time` time DEFAULT NULL,
  `conference_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `owner_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `thumbnail_path` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`conference_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference_history`
--

LOCK TABLES `conference_history` WRITE;
/*!40000 ALTER TABLE `conference_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `conference_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `files` (
  `file_id` bigint NOT NULL AUTO_INCREMENT,
  `file_ori_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `test_id` bigint DEFAULT NULL,
  PRIMARY KEY (`file_id`),
  KEY `FKja8tusjk72c3chmlwbrjcrdpj` (`test_id`),
  CONSTRAINT `FKja8tusjk72c3chmlwbrjcrdpj` FOREIGN KEY (`test_id`) REFERENCES `test` (`test_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
INSERT INTO `files` VALUES (3,'4181번.pdf','/home/ubuntu/upload/','7bfl3AGwhwB0MxxcBVAloe1QZpS3r2zz.pdf',2),(4,'testPage.pdf','/home/ubuntu/upload/','K3sOXKTCi4jxxZ6lJdFH8AJRweuXae1u.pdf',3),(10,'사칙연산.pdf','/home/ubuntu/upload/','QqD4SsEWnO7YH6VE9cBdkPmNO4HY6Rjr.pdf',17),(11,'사칙연산 시험문제.pdf','/home/ubuntu/upload/','RCLtW05CxS7NMBXWrpvgY0l2XeV8fSkB.pdf',18);
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `test_id` bigint NOT NULL AUTO_INCREMENT,
  `test_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `test_qno` int DEFAULT NULL,
  `testcase` varchar(7000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `class_id` bigint DEFAULT NULL,
  PRIMARY KEY (`test_id`),
  KEY `FKlf8u1hjebnxk4icc7lgfe4xy7` (`class_id`),
  CONSTRAINT `FKlf8u1hjebnxk4icc7lgfe4xy7` FOREIGN KEY (`class_id`) REFERENCES `classes` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (2,'examName',1,'{\"testcaseList\":[{\"qno\":1,\"testcase\":[{\"input\":\"5\\n10\",\"output\":\"15\"},{\"input\":\"1\\n3\",\"output\":\"4\"}]}]}',5),(3,'백준 골드1~플래4 문제들',3,'{\"testcaseList\":[{\"qno\":1,\"testcase\":[{\"input\":\"5\\n1 1 Y\\n1 -1 Y\\n0 0 N\\n-1 -1 Y\\n-1 1 Y\",\"output\":\"4\\n-1 -1\\n1 -1\\n1 1\\n-1 1\"}]},{\"qno\":2,\"testcase\":[{\"input\":\"1 7\\nf0.F..1\",\"output\":\"7\"},{\"input\":\"5 5\\n....1\\n#1###\\n.1.#0\\n....A\\n.1.#.\",\"output\":\"-1\"},{\"input\":\"7 8\\na#c#eF.1\\n.#.#.#..\",\"output\":\"55\"}]},{\"qno\":3,\"testcase\":[{\"input\":\"5\\n 5 1 2 3 4\\n3\\n2 4 1\\n6 6 6\\n1 5 2\",\"output\":\"2\\n0\\n3\"}]}]}',5),(7,'시험이다',1,'{\"testcaseList\":[{\"qno\":1,\"testcase\":[{\"input\":\"5\\n10\",\"output\":\"15\"},{\"input\":\"1\\n3\",\"output\":\"4\"}]}]}',7),(13,'뺄셈 프로그램',1,'{\"testcaseList\":[{\"qno\":1,\"testcase\":[{\"input\":\"5\\n10\",\"output\":\"15\"},{\"input\":\"1\\n3\",\"output\":\"4\"}]}]}',1),(17,'사칙연산',3,'{\"testcaseList\":[{\"qno\":1,\"testcase\":[{\"input\":\"3 1\",\"output\":\"4\"},{\"input\":\"2 5\",\"output\":\"7\"}]},{\"qno\":2,\"testcase\":[{\"input\":\"3 1\",\"output\":\"2\"}]},{\"qno\":3,\"testcase\":[{\"input\":\"3 3\",\"output\":\"9\"}]}]}',1),(18,'사칙연산',3,'{\"testcaseList\":[{\"qno\":1,\"testcase\":[{\"input\":\"5\\n10\",\"output\":\"15\"},{\"input\":\"1\\n3\",\"output\":\"4\"}]},{\"qno\":2,\"testcase\":[{\"input\":\"5 3\",\"output\":\"2\"}]},{\"qno\":3,\"testcase\":[{\"input\":\"2 5 \",\"output\":\"10\"}]}]}',10);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_record`
--

DROP TABLE IF EXISTS `test_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test_record` (
  `test_record_id` bigint NOT NULL AUTO_INCREMENT,
  `correct_count` int DEFAULT NULL,
  `date` date DEFAULT NULL,
  `lang` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `source_code` varchar(7000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `test_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`test_record_id`),
  KEY `FK7pj8vhuitjjj1le0akw8ichhi` (`test_id`),
  KEY `FKrtp0n6jfsgd9mlkjt5afh2oir` (`user_id`),
  CONSTRAINT `FK7pj8vhuitjjj1le0akw8ichhi` FOREIGN KEY (`test_id`) REFERENCES `test` (`test_id`),
  CONSTRAINT `FKrtp0n6jfsgd9mlkjt5afh2oir` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_record`
--

LOCK TABLES `test_record` WRITE;
/*!40000 ALTER TABLE `test_record` DISABLE KEYS */;
INSERT INTO `test_record` VALUES (5,0,'2022-08-12','java','{\"answers\":[{\"qno\":1,\"code\":\"import java.util.*;\\nimport java.io.*;\\n\\npublic class Main{\\n    public static void main(String[] args) throws IOException {\\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\\n       \\n        int a = Integer.parseInt(re.readLine());\\n        int b = Integer.parseInt(re.readLine());\\n\\n        System.out.println(a+b);\\n        re.close();\\n    }\\n}\"},{\"qno\":2,\"code\":\"import java.util.*;\\nimport java.io.*;\\n\\npublic class Main{\\n    public static void main(String[] args) throws IOException {\\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\\n       \\n        int a = Integer.parseInt(re.readLine());\\n        int b = Integer.parseInt(re.readLine());\\n\\n        System.out.println(a+b);\\n        re.close();\\n    }\\n}\"},{\"qno\":3,\"code\":\"import java.util.*;\\nimport java.io.*;\\n\\npublic class Main{\\n    public static void main(String[] args) throws IOException {\\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\\n       \\n        int a = Integer.parseInt(re.readLine());\\n        int b = Integer.parseInt(re.readLine());\\n\\n        System.out.println(a+b);\\n        re.close();\\n    }\\n}\"}]}',3,31),(6,0,'2022-08-15','java','{\"answers\":[{\"qno\":1,\"code\":\"import java.util.*;\\nimport java.io.*;\\n\\npublic class Main{\\n    public static void main(String[] args) throws IOException {\\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\\n       \\n        int a = Integer.parseInt(re.readLine());\\n        int b = Integer.parseInt(re.readLine());\\n\\n        System.out.println(a+b);\\n        re.close();\\n    }\\n}\"},{\"qno\":2,\"code\":\"import java.util.*;\\nimport java.io.*;\\n\\npublic class Main{\\n    public static void main(String[] args) throws IOException {\\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\\n       \\n        int a = Integer.parseInt(re.readLine());\\n        int b = Integer.parseInt(re.readLine());\\n\\n        System.out.println(a+b);\\n        re.close();\\n    }\\n}\"},{\"qno\":3,\"code\":\"import java.util.*;\\nimport java.io.*;\\n\\npublic class Main{\\n    public static void main(String[] args) throws IOException {\\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\\n       \\n        int a = Integer.parseInt(re.readLine());\\n        int b = Integer.parseInt(re.readLine());\\n\\n        System.out.println(a+b);\\n        re.close();\\n    }\\n}\"}]}',3,35),(21,3,'2022-08-16','java','{\"answers\":[{\"qno\":1,\"code\":\"import java.util.*;\\nimport java.io.*;\\npublic class Main{\\n    public static void main(String[] args) throws IOException {\\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\\n        StringTokenizer st = new StringTokenizer(re.readLine());\\n       \\n        int a = Integer.parseInt(st.nextToken());\\n        int b = Integer.parseInt(st.nextToken());\\n\\n        System.out.println(a+b);\\n    }\\n\\n}\"},{\"qno\":2,\"code\":\"import java.util.*;\\nimport java.io.*;\\npublic class Main{\\n    public static void main(String[] args) throws IOException {\\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\\n        StringTokenizer st = new StringTokenizer(re.readLine());\\n       \\n        int a = Integer.parseInt(st.nextToken());\\n        int b = Integer.parseInt(st.nextToken());\\n        \\n        System.out.println(a-b);\\n    }\\n\\n}\"},{\"qno\":3,\"code\":\"import java.util.*;\\nimport java.io.*;\\npublic class Main{\\n    public static void main(String[] args) throws IOException {\\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\\n        StringTokenizer st = new StringTokenizer(re.readLine());\\n       \\n        int a = Integer.parseInt(st.nextToken());\\n        int b = Integer.parseInt(st.nextToken());\\n        \\n        System.out.println(a*b);\\n    }\\n\\n}\"}]}',17,3),(22,1,'2022-08-16','java','{\"answers\":[{\"qno\":1,\"code\":\"import java.util.*;\\nimport java.io.*;\\n\\npublic class Main{\\n    public static void main(String[] args) throws IOException {\\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\\n       \\n        int a = Integer.parseInt(re.readLine());\\n        int b = Integer.parseInt(re.readLine());\\n\\n        System.out.println(a+b);\\n        re.close();\\n    }\\n}\"},{\"qno\":2,\"code\":\"import java.util.*;\\nimport java.io.*;\\n\\npublic class Main{\\n    public static void main(String[] args) throws IOException {\\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\\n       \\n        int a = Integer.parseInt(re.readLine());\\n        int b = Integer.parseInt(re.readLine());\\n\\n        System.out.println(a-b);\\n        re.close();\\n    }\\n}\"},{\"qno\":3,\"code\":\"import java.util.*;\\nimport java.io.*;\\n\\npublic class Main{\\n    public static void main(String[] args) throws IOException {\\n        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));\\n       \\n        int a = Integer.parseInt(re.readLine());\\n        int b = Integer.parseInt(re.readLine());\\n\\n        System.out.println(a*b);\\n        re.close();\\n    }\\n}\"}]}',18,40);
/*!40000 ALTER TABLE `test_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `birth_dt` date NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `profile_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1980-12-12','kdw508@naver.com','cow','김동욱','포도맛캔디','$2a$10$v51pKrop5Ie8fF95qgYrUeMrsGOXjfiJFFHx.iZPJkW3K3jOdIy0q','010-1111-2222',NULL,'강사'),(2,'1996-04-17','rabbit@ssafy.com','goat','김혜린','염소맛캔디','$2a$10$I47xJ0xXfLu5vC5ISUUnDet8dSFLpJrBZ77pVN7BJwBF8G51WdI0.','010-1112-2222',NULL,'학생'),(3,'1998-02-14','sheep@ssafy.com','rabbit','강승길','바나나맛캔디','$2a$10$Twlb0IS2dRXdO/my5SXDxe1YScNE4WUwt2foAUf.VjbLP3IxyRnY2','010-1113-2222',NULL,'학생'),(4,'1998-01-14','asdfwerq@ssafy.com','cvbndf','최태순','사과맛캔디','$2a$10$vqXKgBHK4fnHwQROw3/xausWF3D96SWscSMWvuNR2mAGaVkFwIdTK','010-1114-2222',NULL,'학생'),(5,'1998-07-13','xcvbnsdf@ssafy.com','sdxcv','이성민','귤맛캔디','$2a$10$pf3fUybwliHVGOMcl8CvOeh2aEp.oOiAr/nA4XHn1Gb78erptks/y','010-1116-2222',NULL,'학생'),(6,'1996-01-05','rtywer@ssafy.com','bvbewer','김개발','홍차맛캔디','$2a$10$plgQ1t8g1F3tQMboFOPrvOBg3lkJTt7ly5F90c6.pQZSR/dfMQzIG','010-1117-2222',NULL,'학생'),(7,'1998-09-22','bzxcv@ssafy.com','bzxcv','김서준','위병조장','$2a$10$XsU41UuJYL912M1bHc3ND.wwsbiPlJb82KxbAyKvziLPxnijKMN0K','010-1145-2222',NULL,'학생'),(8,'1998-01-14','bzsdfcv@ssafy.com','bzxcvsdf','박하준','선자춘설','$2a$10$8iqrg1yGAQQAKQcHBj16reDRp6IKoeHGMAQGb/h7nYopiMweHoNhW','010-1245-2222',NULL,'학생'),(9,'1997-01-12','bzsdsdfcv@ssafy.com','aasda','최도윤','군왕군웅','$2a$10$V3jC80ueWZzfhaWMgflpjOT8AsMALIXwQC2bEpOORZjLjnr6S829m','010-1245-2222',NULL,'학생'),(10,'1997-01-13','bzsdszxcdfcv@ssafy.com','aaxcvxsda','박시우','빠뿌쟁이','$2a$10$MQR1E5R6/QZ5faxQ6XUnae41hKkm6XDtJIrvzH7wWiyUDrb19bTaG','010-1235-2222',NULL,'학생'),(11,'1996-01-13','zxcdfcv@ssafy.com','zxcdfcv','김은우','대연평도','$2a$10$6aUHUhRhXo2ppQJIcz6JeuVODtKROOyGqiRmY9WOdozF7ybpvHhsO','010-1235-2223',NULL,'학생'),(12,'1996-01-18','zxxcdfcv@ssafy.com','zxcdxxfcv','유지호','둥구레칼','$2a$10$T9HM34b.r8Khp67pIa/JUeHwdw/XhD.PtdS85WE58PDWNviWWDviS','010-1235-2258',NULL,'학생'),(13,'1996-07-18','zxcv@ssafy.com','zxfcv','이예준','계속대비','$2a$10$sK9Xhxlwm7xMKB/g.erJN.sK7RX3msRSTo6MBtZXoTPLgUq6hY/EW','010-1235-2158',NULL,'학생'),(14,'1996-07-02','zxwxcv@ssafy.com','zxfwwcv','박예린','시초시계','$2a$10$vTp2lT6tHIAhwKnJkGVsOOAgJIMDvKsAkphGjQktwTuHxlqy7xJ86','010-1235-2151',NULL,'학생'),(15,'1996-07-03','xcvbrv@ssafy.com','wert','이민준','부스레기','$2a$10$W9Cq6.Z0fsiaVq3YjjkO/.P.W1FO8kWtkK7Q0BOAd0kKQkdFn1xeG','010-1235-2131',NULL,'학생'),(16,'1999-11-11','xhjkcvbrv@ssafy.com','hjktyu','최주원','오목주발','$2a$10$Sla0jft2D/l7UT19DdspAuunr3hPLG8CiGXx25vTzk0JWTbUP6x/S','010-1235-2133',NULL,'학생'),(17,'1999-01-11','zvvvvvvds@ssafy.com','qwasd','이준','원매원말','$2a$10$hTlBGARuLkY4JFVZ/YN8y.6mDzfgA/d5wow3QCrfcJlXxCDAAmo3y','010-1235-2134',NULL,'학생'),(18,'1999-01-21','zvvvv@ssafy.com','qwasdbcvb','박우진','차강인의','$2a$10$83XUAZ1pIWVYHAfmPljBlOrnLB47Ga9x/B2eTgUfTX5Od/1hg7Jei','010-1225-2134',NULL,'학생'),(19,'1999-08-21','fghfgh@ssafy.com','dfgerdf','김예령','첨절제사','$2a$10$wPceEZtWYmamJlnYnEC4MeKh36YzEtTAiAal26tY6gXVyYx2FztGG','010-1225-2104',NULL,'학생'),(20,'1999-08-22','fgh@ssafy.com','dfrdf','최지우','정악정악','$2a$10$49WBrd0uL/ei2ScsTpHNAOLZ71G6idA6bgPbQ4XSonfvRe5f5WYze','010-1225-2102',NULL,'학생'),(21,'1999-06-22','fghjfgh@ssafy.com','tyurty','서준우','자시자시','$2a$10$6jJ7larvwPOqNBhluAaW5OmzsWdNZ.UCQ/Dfnp.Z4bDbI0Nj93PM2','010-8225-2102',NULL,'학생'),(22,'1992-06-04','park@ssafy.com','dragon','박용미','나는프로','$2a$10$vNBLBaTHJpEY03GFHG0fxuT2KQ1qSh60TMCwwD8DtMU/s/xq7EflC','010-5225-2102',NULL,'강사'),(23,'1992-06-04','kimsu@ssafy.com','hyeon','김수현','올빼미','$2a$10$S4XfrcsVRDTu/iDqi43mF.z1H/E0YdPjqgrmUB4OaXUdVoRH1k0i.','010-5225-2103',NULL,'강사'),(24,'2022-07-28','aprilkim98@hanyang.ac.kr','aprilkim98','김프론트','김혜린','$2a$10$nD4OK0SkbJxFrKr8OsgVzuUelYdOMYDh8q2ELjGKnwGK6K.TjT3Wm','01003423434',NULL,'학생'),(25,'2022-08-04','asd1234@asd.com','asd1234','qweqwe','sssss','$2a$10$AsSB6jAUNCng0sQykD7yxe620Elb.SgBpZ3ACz5ic47BnAA24csQq','01011111111',NULL,'강사'),(26,'2022-08-11','ksgg1@naver.com','ksgg1','강승길','ksdf','$2a$10$R1m6pLP.sbI6fV53ewlAV.smWy4iBIyTaYsJRJKQsLornu7/qipc.','01212313121',NULL,'강사'),(27,'2022-08-05','cowcow@cowcow.com','cowcow','비번cowcow','cowcow','$2a$10$Bd8uWxU4c5fH5zjK3KoxgeIFMiELWxtvU0sPz3TtVvBvTggc3IQ4y','30232020203',NULL,'강사'),(28,'2022-08-02','cowcow@cowcow.com','cowcowstudent','비번cowcow','cowcow학생','$2a$10$ZvkmGjcnNy5st4xpmXtrfOyP98.yXwugmlnsafJBhXbQBlJYJ6GhW','11111111111',NULL,'학생'),(29,'2022-08-03','qwe@qwe.com','te4438','황태순두부','닉네임','$2a$10$9e1yG4.8X.41YuRyDkWBS.wooSXxzwurRK7Lmz9AAWvUoGvRcS0VS','11111111111',NULL,'강사'),(31,'2022-08-10','ksgg1@naver.com','aaa','aaa','ksdf','$2a$10$IfTAugmAFLrn2tih/80xzu61ctsm.1CwvpydJ6HJ4ByETCjZBQwy.','01212313121',NULL,'학생'),(33,'2022-08-02','asd1234@asd.com','te44381','동태순두부','동태순두부','$2a$10$PLfmubENVYGRvuNQGlo6guf8gf7kF8uF8qUH2N5wV82sCqLLKf6N.','12341241111',NULL,'학생'),(34,'2015-02-17','te44380@asd.com','te44380','강사님','클래스없는강사님','$2a$10$hF5e1C3O4SpLO2PnnuT2DuMDCBb2ZocoUC8JM1QZL5fT7mNcIafNa','12547885666',NULL,'강사'),(35,'2022-08-03','3d@ndsfa.com','bbb','이름 테스트2','2121','$2a$10$fSitcwuf9sLgiLzz2BzA2OG3Us9HL6joftXfE3JNaZMqiVR4bZVUi','01212313121',NULL,'학생'),(36,'2022-08-03','asd12341234@asd.com','asd123412','강사강사','강사강사강사','$2a$10$E4RBAGRwAfWJgQaPNXMcleHoat9qpIV/HtDCDt5MJc2fxyvloQ6D.','46384564745',NULL,'강사'),(37,'2022-08-16','test@test.com','yoyo','서요셉','yoyo','$2a$10$FGdDBDRhjYE97m3.OFQUpu/5F9HGVIOaWw69.WvxeXPHqRr6XhiWe','01022223333',NULL,'학생'),(38,'1996-04-16','minsu@naver.com','minsu01','김민수','용감한용가리','$2a$10$HgE83xEKrBXGB7xbbbf86OgHvvOvJsSwjB54JZ//XcvD/QmJOS/pm','01012341234',NULL,'학생'),(39,'2009-03-17','ll8470@naver.com','me1st','me1st','캔디캔디','$2a$10$C49/f3E79iijua7oZVedIeH0gdSJCq.TIwD.nbi3I3uaDw.xUyNQG','01011112222',NULL,'강사'),(40,'1999-12-17','vkfksqlcgkx@naver.com','daeunblue','김다은','다람쥐','$2a$10$3X1y8mrPv6tMNNovkuwyRuVZcYVAKBXB20g5aUsPYOyoGrR1tpOV.','01049237502',NULL,'학생'),(41,'1995-11-21','jang@ssafy.com','live','이현주','캔디캔디s','$2a$10$1NM9avtlIQYPqW9YSaj.5OE9W2N0xlFv2k9UuGOfohozuhoz8XdNq','01011112222',NULL,'학생');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_class`
--

DROP TABLE IF EXISTS `user_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_class` (
  `user_class_id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` bigint DEFAULT NULL,
  `class_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_class_id`),
  KEY `FKkhs7fpce6ik7xujf0cn5sx3cl` (`class_id`),
  CONSTRAINT `FKkhs7fpce6ik7xujf0cn5sx3cl` FOREIGN KEY (`class_id`) REFERENCES `classes` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_class`
--

LOCK TABLES `user_class` WRITE;
/*!40000 ALTER TABLE `user_class` DISABLE KEYS */;
INSERT INTO `user_class` VALUES (1,2,1),(2,3,1),(3,4,1),(4,5,1),(5,6,1),(6,7,3),(7,8,3),(8,9,3),(9,10,3),(10,11,3),(11,12,2),(12,13,2),(13,14,2),(14,15,2),(15,16,2),(16,17,4),(17,18,4),(18,19,4),(20,21,4),(23,31,5),(25,35,5),(54,33,7),(57,20,1),(58,38,9);
/*!40000 ALTER TABLE `user_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification`
--

DROP TABLE IF EXISTS `verification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification` (
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `verification_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification`
--

LOCK TABLES `verification` WRITE;
/*!40000 ALTER TABLE `verification` DISABLE KEYS */;
/*!40000 ALTER TABLE `verification` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-16 16:20:01
