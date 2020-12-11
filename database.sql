-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: kahoot3
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorie` (
  `idCATEGORIE` int NOT NULL AUTO_INCREMENT,
  `texteCATEGORIE` varchar(1000) NOT NULL,
  PRIMARY KEY (`idCATEGORIE`),
  UNIQUE KEY `texteCATEGORIE` (`texteCATEGORIE`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie`
--

LOCK TABLES `categorie` WRITE;
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;
INSERT INTO `categorie` VALUES (105,'Beaux gosses (Le teint halé et les muscles saillants)'),(107,'Géo pour tous (Là où je t\'emmènerai)'),(106,'Jeux de société (La stratégie est au rendez-vous)');
/*!40000 ALTER TABLE `categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `joueur`
--

DROP TABLE IF EXISTS `joueur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `joueur` (
  `idJOUEUR` int NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `mdp` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idJOUEUR`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `joueur`
--

LOCK TABLES `joueur` WRITE;
/*!40000 ALTER TABLE `joueur` DISABLE KEYS */;
INSERT INTO `joueur` VALUES (1,'a','a'),(2,'b','b'),(3,'c','c'),(4,'d','d');
/*!40000 ALTER TABLE `joueur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `joueur_partie`
--

DROP TABLE IF EXISTS `joueur_partie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `joueur_partie` (
  `ID_PARTIE` int NOT NULL,
  `idJOUEUR` int NOT NULL,
  `SCORE` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `joueur_partie`
--

LOCK TABLES `joueur_partie` WRITE;
/*!40000 ALTER TABLE `joueur_partie` DISABLE KEYS */;
INSERT INTO `joueur_partie` VALUES (0,1,0),(1,2,0),(1,3,0),(1,4,0),(1,5,0),(0,5,0),(1,6,0),(2,6,0),(1,7,0),(1,8,0),(1,9,0),(1,10,0),(1,11,0),(1,12,0),(2,12,0),(1,13,0),(1,14,0),(2,14,0),(1,15,0),(1,16,0),(1,17,0),(1,18,0),(1,19,0),(1,20,0),(1,21,0),(1,22,0),(1,23,0),(2,23,0),(1,24,0),(2,24,0),(1,25,0),(1,26,0),(1,27,0),(2,27,0),(1,28,0),(2,28,0),(1,29,0),(2,29,0),(1,30,0),(2,30,0),(1,31,0),(2,31,0),(0,31,0),(1,32,0),(2,32,0),(3,32,0),(1,33,0),(1,34,0),(2,34,0),(3,34,0),(1,35,0),(2,35,0),(1,36,0),(2,36,0),(1,37,0),(2,37,0),(1,38,0),(2,38,0),(1,39,0),(2,39,0),(1,40,0),(2,40,0),(3,40,0),(1,41,0),(1,42,0),(2,42,0),(3,42,0),(1,43,0),(2,43,0),(1,44,0),(2,44,0),(1,45,0),(2,45,0),(1,46,0),(2,46,0),(1,47,0),(2,47,0),(1,48,0),(2,48,0),(1,49,0),(1,50,0),(1,51,0),(2,51,0),(1,52,0),(2,52,0),(1,53,0),(2,53,0),(1,54,0),(2,54,0),(1,55,0),(2,55,0),(1,56,0),(2,56,0),(1,57,0),(2,57,0),(1,58,0),(2,58,0),(1,59,0),(2,59,0),(1,60,0),(2,60,0),(1,61,0),(2,61,0),(1,62,0),(2,62,0),(1,63,0),(2,63,0),(1,64,0),(2,64,0),(1,65,0),(2,65,0),(1,66,0),(2,66,0),(1,67,0),(2,67,0),(1,68,0),(2,68,0),(1,69,0),(2,69,0),(1,70,0),(2,70,0),(1,71,0),(2,71,0),(1,72,0),(2,72,0),(1,73,0),(2,73,0),(1,74,0),(2,74,0),(1,75,0),(2,75,0),(76,1,2),(76,2,1),(77,1,0),(77,2,0),(78,1,0),(78,2,0),(79,1,0),(79,2,0),(80,1,1),(80,2,0),(81,1,0),(81,2,0),(82,1,0),(82,2,0),(83,1,4),(83,2,3),(84,1,0),(84,2,0),(85,1,4),(85,2,3),(85,3,3),(85,0,0),(86,1,1),(86,2,0),(86,3,0),(87,1,0),(87,2,1),(87,3,0),(87,4,0),(88,2,0),(89,2,2),(90,1,1),(89,3,3),(90,4,2),(91,1,0),(92,3,0),(93,4,0),(94,2,0),(95,1,0),(95,2,0),(96,1,0),(97,2,0),(98,1,1);
/*!40000 ALTER TABLE `joueur_partie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partie`
--

DROP TABLE IF EXISTS `partie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partie` (
  `ID_PARTIE` int NOT NULL AUTO_INCREMENT,
  `ID_HOTE` int NOT NULL,
  `DEBUT` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `code` char(5) DEFAULT NULL,
  `port` int DEFAULT NULL,
  `ID_CATEGORIE` int NOT NULL,
  PRIMARY KEY (`ID_PARTIE`,`ID_HOTE`),
  KEY `FK_partiecategorie_idx` (`ID_CATEGORIE`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partie`
--

LOCK TABLES `partie` WRITE;
/*!40000 ALTER TABLE `partie` DISABLE KEYS */;
INSERT INTO `partie` VALUES (1,0,'2020-12-02 16:57:28','EQAOF',49513,0),(2,1,'2020-12-02 16:58:14','GVRMT',49513,0),(3,1,'2020-12-02 16:58:57','PPXMM',49513,0),(4,1,'2020-12-02 16:59:52','PFIPP',49513,0),(5,1,'2020-12-02 17:11:16','SNXXH',49513,0),(6,1,'2020-12-03 14:49:56','UQTWQ',49513,0),(7,1,'2020-12-03 14:53:27','OQXST',49513,0),(8,1,'2020-12-03 14:54:26','XEZSV',49513,0),(9,1,'2020-12-03 14:56:02','WSTXI',49513,0),(10,1,'2020-12-03 14:58:56','IEVWC',49513,0),(11,1,'2020-12-03 15:03:43','HCBSV',49513,0),(12,1,'2020-12-03 15:06:39','MBQTJ',49513,0),(13,1,'2020-12-03 15:08:23','RIDQN',49513,0),(14,1,'2020-12-03 15:10:34','WXXLF',49513,0),(15,1,'2020-12-03 15:17:23','RILCM',49513,0),(16,1,'2020-12-03 15:19:07','THDEO',49513,0),(17,1,'2020-12-03 15:20:05','DHYZD',49513,0),(18,1,'2020-12-03 15:23:39','OYRSJ',49513,0),(19,1,'2020-12-03 15:25:13','BGFVH',49513,0),(20,1,'2020-12-03 15:25:41','QJVGE',49513,0),(21,1,'2020-12-03 15:26:52','MVZPR',49513,0),(22,1,'2020-12-03 15:37:37','MOZYS',49513,0),(23,1,'2020-12-03 15:40:42','PMVWC',49513,0),(24,1,'2020-12-03 15:42:53','BFVAC',49513,0),(25,1,'2020-12-03 15:50:48','LRSYW',49513,0),(26,1,'2020-12-03 16:11:56','KYKGJ',49513,0),(27,1,'2020-12-03 16:14:16','QLWOH',49513,0),(28,1,'2020-12-03 16:20:30','HDNPZ',49513,0),(29,1,'2020-12-03 16:22:33','AGWHN',49513,0),(30,1,'2020-12-03 16:24:21','WDMPW',49513,0),(31,1,'2020-12-03 16:27:42','TFLCY',49513,0),(32,1,'2020-12-03 16:29:06','PASLE',49513,0),(33,1,'2020-12-03 16:31:28','MVAWF',49513,0),(34,1,'2020-12-03 16:32:07','JTVGR',49513,0),(35,1,'2020-12-03 16:37:54','DXXNJ',49513,0),(36,1,'2020-12-03 16:40:23','STQAJ',49513,0),(37,1,'2020-12-03 16:44:04','CDMEH',49513,0),(38,1,'2020-12-03 16:51:17','WBFFL',49513,0),(39,1,'2020-12-03 16:53:38','HECBA',49513,0),(40,1,'2020-12-03 16:55:40','ABNRY',49513,0),(41,1,'2020-12-03 17:05:55','WFGOK',49513,105),(42,1,'2020-12-03 17:14:17','OZJHB',49513,105),(43,1,'2020-12-03 17:17:15','GOWVV',49513,105),(44,1,'2020-12-03 17:18:24','XHQOF',49513,105),(45,1,'2020-12-03 17:20:50','ELDHA',49513,105),(46,1,'2020-12-03 17:21:25','KDICX',49513,105),(47,1,'2020-12-03 17:24:46','SFUHH',49513,105),(48,1,'2020-12-03 17:25:17','KXMGP',49513,105),(49,1,'2020-12-03 17:28:35','OQJJU',49513,105),(50,1,'2020-12-03 17:28:54','KADTU',49513,105),(51,1,'2020-12-03 17:54:58','LXVST',49513,105),(52,1,'2020-12-03 17:56:09','BWDJU',49513,105),(53,1,'2020-12-03 17:58:01','JXUCY',49513,105),(54,1,'2020-12-03 18:25:08','YUNOD',49513,105),(55,1,'2020-12-03 18:25:53','PHTTY',49513,105),(56,1,'2020-12-03 18:27:08','LIPHC',49513,105),(57,1,'2020-12-03 18:27:51','CTSME',49513,105),(58,1,'2020-12-03 18:29:01','MRBFH',49513,105),(59,1,'2020-12-03 18:40:13','TXNPQ',49513,105),(60,1,'2020-12-03 18:43:30','NGJMO',49513,105),(61,1,'2020-12-03 18:48:48','MXKOV',49513,105),(62,1,'2020-12-03 18:49:26','ARULG',49513,105),(63,1,'2020-12-03 18:50:52','LXLAE',49513,105),(64,1,'2020-12-03 18:54:58','DNVIN',49513,105),(65,1,'2020-12-03 18:57:15','NDHQM',49513,105),(66,1,'2020-12-03 18:58:34','CBPEE',49513,105),(67,1,'2020-12-03 19:00:22','KOZPB',49513,105),(68,1,'2020-12-03 19:01:42','HUVJE',49513,105),(69,1,'2020-12-03 19:03:11','TUTWT',49513,105),(70,1,'2020-12-03 19:04:34','SEVFM',49513,105),(71,1,'2020-12-03 19:06:09','PUZVS',49513,105),(72,1,'2020-12-03 19:06:46','YCMQX',49513,105),(73,1,'2020-12-03 19:22:00','TYTPJ',49513,105),(74,1,'2020-12-03 19:24:51','LFUKF',49513,105),(75,1,'2020-12-03 19:26:17','QPIIG',49513,105),(76,1,'2020-12-08 20:54:51','AYVFM',49513,105),(77,1,'2020-12-08 20:59:43','ISUYR',49513,105),(78,1,'2020-12-08 21:11:08','PDZJF',49513,105),(79,1,'2020-12-08 21:11:38','HCCRP',49513,105),(80,1,'2020-12-08 21:17:12','JNQYF',49513,105),(81,1,'2020-12-08 21:25:49','EFBVW',49513,105),(82,1,'2020-12-08 21:29:53','UVVHS',49513,105),(83,1,'2020-12-08 21:31:40','JBJBA',49513,105),(84,1,'2020-12-08 21:34:20','LRECW',49514,105),(85,1,'2020-12-08 21:38:29','HGVNM',49513,105),(86,1,'2020-12-08 21:45:25','VVMSZ',49513,105),(87,1,'2020-12-08 21:51:30','CHTAE',49513,105),(88,2,'2020-12-08 21:54:29','IYEXP',49514,105),(89,2,'2020-12-08 21:56:53','UCFAR',49513,105),(90,1,'2020-12-08 21:56:54','ILSBF',49514,105),(91,1,'2020-12-08 21:59:52','SUHPQ',49515,105),(92,3,'2020-12-08 21:59:58','OKHYL',49516,105),(93,4,'2020-12-08 22:00:10','XYXIP',49517,105),(94,2,'2020-12-08 22:00:15','CBYWN',49518,105),(95,1,'2020-12-08 22:00:54','DUWBA',49513,105),(96,1,'2020-12-08 22:04:36','GHQNA',49514,105),(97,2,'2020-12-08 22:04:36','WTIGF',49515,105),(98,1,'2020-12-08 22:24:51','AEFCA',49513,107);
/*!40000 ALTER TABLE `partie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propositions`
--

DROP TABLE IF EXISTS `propositions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `propositions` (
  `ID_QUESTION` int NOT NULL,
  `ID_REPONSE` int NOT NULL,
  `bonneReponse` int DEFAULT NULL,
  KEY `fk_QUESTION_has_REPONSE_REPONSE1_idx` (`ID_REPONSE`),
  KEY `fk_QUESTION_has_REPONSE_QUESTION1_idx` (`ID_QUESTION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propositions`
--

LOCK TABLES `propositions` WRITE;
/*!40000 ALTER TABLE `propositions` DISABLE KEYS */;
INSERT INTO `propositions` VALUES (454,1436,0),(454,1437,0),(454,1438,0),(454,1439,1),(455,1440,0),(455,1441,0),(455,1442,0),(455,1443,1),(456,1444,0),(456,1445,0),(456,1446,1),(456,1447,0),(457,1448,0),(457,1449,1),(457,1450,0),(457,1451,0),(458,1452,0),(458,1453,0),(458,1454,1),(458,1455,0),(459,1456,0),(459,1457,1),(459,1458,0),(459,1459,0),(460,1460,0),(460,1461,0),(460,1462,1),(460,1463,0),(461,1464,0),(461,1465,0),(461,1466,0),(461,1467,1),(462,1468,0),(462,1469,0),(462,1470,0),(462,1471,1),(463,1472,0),(463,1473,1),(463,1474,0),(463,1475,0),(464,1476,0),(464,1477,0),(464,1478,1),(464,1479,0),(465,1480,0),(465,1481,0),(465,1482,1),(465,1483,0),(466,1484,1),(466,1485,0),(466,1486,0),(466,1487,0),(467,1488,1),(467,1489,0),(467,1490,0),(467,1491,0),(468,1492,1),(468,1493,0),(468,1494,0),(468,1495,0),(469,1496,0),(469,1497,1),(469,1498,0),(469,1499,0),(470,1500,0),(470,1501,0),(470,1502,1),(470,1503,0),(471,1504,1),(471,1505,0),(471,1506,0),(471,1507,0),(472,1508,0),(472,1509,0),(472,1510,0),(472,1511,1),(473,1512,0),(473,1513,1),(473,1514,0),(473,1515,0),(474,1516,0),(474,1517,1),(474,1518,0),(474,1519,0),(475,1520,1),(475,1521,0),(475,1522,0),(475,1523,0),(476,1524,0),(476,1525,1),(476,1526,0),(476,1527,0),(477,1528,0),(477,1529,1),(477,1530,0),(477,1531,0),(478,1532,0),(478,1533,1),(478,1534,0),(478,1535,0),(479,1536,0),(479,1537,1),(479,1538,0),(479,1539,0),(480,1540,0),(480,1541,0),(480,1542,1),(480,1543,0),(481,1544,0),(481,1545,0),(481,1546,1),(481,1547,0),(482,1548,0),(482,1549,1),(482,1550,0),(482,1551,0),(483,1552,0),(483,1553,1),(483,1554,0),(483,1555,0);
/*!40000 ALTER TABLE `propositions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `ID_QUESTION` int NOT NULL AUTO_INCREMENT,
  `ID_BONNE_REPONSE` int NOT NULL,
  `ID_CATEGORIE` int NOT NULL,
  `texteQUESTION` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID_QUESTION`),
  UNIQUE KEY `texteQUESTION` (`texteQUESTION`),
  KEY `fk_QUESTION_REPONSE1_idx` (`ID_BONNE_REPONSE`),
  KEY `fk_QUESTION_CATEGORIE1_idx` (`ID_CATEGORIE`)
) ENGINE=InnoDB AUTO_INCREMENT=484 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (454,1439,105,'Quel acteur français fut le compagnon de Halle Berry et de Kylie Minogue ?'),(455,1443,105,'Qui a épousé Jennifer Flavin, avec qui il a eu trois filles : Sophia, Sistine et Scarlet ?'),(456,1446,105,'De quel acteur césarisé Claude Chabrol dit-il qu\'il est le Al Pacino français ?'),(457,1449,105,'De quel bel Irlandais Charlize Theron a-t-elle partagé sa vie de 2002 à 2010 ?'),(458,1454,105,'De quel magazine le montrant nu Christophe Dechavanne a-t-il obtenu un retrait en 2006 ?'),(459,1457,105,'Quel acteur britannique très sexy a été ambassadeur du parfum « Hypnôse » de Lancôme ?'),(460,1462,105,'Quel chanteur du groupe Good Charlotte est le père des enfants de Nicole Richie ?'),(461,1467,105,'Qui a risqué d\'être arrêté pour avoir embrassé en Inde et en public l\'actrice Shilpa Shetty ?'),(462,1471,105,'Quel joueur de foot a été surnommé Petit Bison par Emmanuel Petit ?'),(463,1473,105,'À quel sex-symbol des années 1970 l\'actrice Kelly Preston est-elle mariée ?'),(464,1478,106,'Dans quel jeu de société de 64 pions faut-il obtenir un maximum de pions de sa couleur ?'),(465,1482,106,'Quel jeu de stratégie édité par MB comprend cinq engrenages blancs de taille différente ?'),(466,1484,106,'Laquelle de ces armes ne figure pas au « Cluedo », imaginé par Anthony Pratt et sa femme ?'),(467,1488,106,'Quel jeu de stratégie consiste à pousser les six billes de l\'adversaire dans une rigole ?'),(468,1492,106,'Quel jeu de société, créé par John Spinello, s\'appelait « Opération » à sa création en 1965 ?'),(469,1497,106,'Quel jeu de stratégie est divisé en pas moins de 42 territoires et 6 continents ?'),(470,1502,106,'Qui a été assassiné au « Cluedo » dans un manoir anglais, le Manoir Tudor ?'),(471,1504,106,'Quel jeu de cartes américain a été créé en 1971 par Merle Robbins et édité par Mattel ?'),(472,1511,106,'Quel animal représente la distance de 100 km au jeu des « 1000 bornes » ?'),(473,1513,106,'Quel nom porte la variante du jeu combinatoire abstrait « Othello » ?'),(474,1517,107,'Sur quelle île à 120 km du sud de l\'Angleterre se trouvent Grand Havre et Port Soif ?'),(475,1520,107,'Dans quel pays peut-on par exemple passer une nuit à Mandalay et la suivante à Rangoon ?'),(476,1525,107,'Dans quel État du Midwest des États-Unis se trouvent Mineapolis et sa jumelle St Paul ?'),(477,1529,107,'Vers quel lac la ville d\'Irkoutsk plonge-t-elle son regard à 66 kilomètres à l\'ouest ?'),(478,1533,107,'Quelle grande ville trouve-t-on sur la mer d\'Oman, près de l\'embouchure de l\'Indus ?'),(479,1537,107,'Quelle capitale pakistanaise, située au nord du pays, jouxte la ville de Rawalpindi ?'),(480,1542,107,'Dans quel État américain du Nord-Est du pays se trouvent Rome, Syracuse et Albany ?'),(481,1546,107,'De quel pays situé à environ 200 km au nord de l\'équateur Kuala Lumpur est-elle la capitale ?'),(482,1549,107,'Combien de mers baignent la Russie, le plus vaste pays de notre planète ?'),(483,1553,107,'Sur quelle île se trouve Manille, deuxième ville la plus peuplée des Philippines après Quezon ?');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reponse`
--

DROP TABLE IF EXISTS `reponse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reponse` (
  `ID_REPONSE` int NOT NULL AUTO_INCREMENT,
  `texteREPONSE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID_REPONSE`)
) ENGINE=InnoDB AUTO_INCREMENT=1556 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reponse`
--

LOCK TABLES `reponse` WRITE;
/*!40000 ALTER TABLE `reponse` DISABLE KEYS */;
INSERT INTO `reponse` VALUES (1436,'Bruno Putzulu'),(1437,'Mathieu Amalric'),(1438,'Mathieu Kassovitz'),(1439,'Olivier Martinez'),(1440,'Jason Statham'),(1441,'Dolph Lundgren'),(1442,'Eric Roberts'),(1443,'Sylvester Stallone'),(1444,'Vincent Cassel'),(1445,'Mathieu Amalric'),(1446,'François Cluzet'),(1447,'Daniel Auteuil'),(1448,'Hugh Leonard'),(1449,'Stuart Townsend'),(1450,'Jim Sheridan'),(1451,'Adrian Dunbar'),(1452,'Paris Match'),(1453,'VSD'),(1454,'Choc'),(1455,'Le Figaro'),(1456,'Sean Connery'),(1457,'Clive Owen'),(1458,'Michael York'),(1459,'Chris Hemsworth'),(1460,'Billy Martin'),(1461,'Paul Thomas'),(1462,'Joel Madden'),(1463,'Dean Butterworth'),(1464,'Joaquin Phoenix'),(1465,'Colin Farrell'),(1466,'Johnny Depp'),(1467,'Richard Gere'),(1468,'Laurent Blanc'),(1469,'Christophe Dugarry'),(1470,'Thierry Henry'),(1471,'Bixente Lizarazu'),(1472,'Kevin Costner'),(1473,'John Travolta'),(1474,'Hugh Jackman'),(1475,'Ryan Gosling'),(1476,'Awalé'),(1477,'Pentago'),(1478,'Othello'),(1479,'Puissance 4'),(1480,'Uno'),(1481,'Abalone'),(1482,'Dix de Chute'),(1483,'Risk'),(1484,'Pic à glace'),(1485,'Revolver'),(1486,'Chandelier'),(1487,'Matraque'),(1488,'Abalone'),(1489,'Risk'),(1490,'Dix de chute'),(1491,'Othello'),(1492,'Docteur Maboul'),(1493,'Taboo'),(1494,'Reversi'),(1495,'Kubb'),(1496,'Battle Cry'),(1497,'Risk'),(1498,'Harpoon'),(1499,'Starfire'),(1500,'Le Colonel Moutarde'),(1501,'Le Professeur Violet'),(1502,'Le Docteur Lenoir'),(1503,'Madame Leblanc'),(1504,'Uno'),(1505,'1000 bornes'),(1506,'Korsar'),(1507,'Dobble'),(1508,'Un papillon'),(1509,'Une hirondelle'),(1510,'Un canard'),(1511,'Un lièvre'),(1512,'Uno'),(1513,'Reversi'),(1514,'Othidis'),(1515,'Abalone'),(1516,'Ortac'),(1517,'Guernesey'),(1518,'Herm'),(1519,'Aurigny'),(1520,'En Birmanie'),(1521,'Aux Philippines'),(1522,'En Malaisie'),(1523,'En Indonésie'),(1524,'Le Missouri'),(1525,'Le Minnesota'),(1526,'Le Michigan'),(1527,'Le Montana'),(1528,'Érié'),(1529,'Baïkal'),(1530,'Malawi'),(1531,'Tanganyika'),(1532,'Mingora'),(1533,'Karachi'),(1534,'Burewala'),(1535,'Islamabad'),(1536,'Quetta'),(1537,'Islamabad'),(1538,'Wah'),(1539,'Karachi'),(1540,'Idaho'),(1541,'Alabama'),(1542,'New York'),(1543,'Maryland'),(1544,'Le Cambodge'),(1545,'Le Laos'),(1546,'La Malaisie'),(1547,'La Birmanie'),(1548,'Six'),(1549,'Douze'),(1550,'Huit'),(1551,'Dix'),(1552,'Guam'),(1553,'Luçon'),(1554,'Shikoku'),(1555,'Visayas');
/*!40000 ALTER TABLE `reponse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-11 14:58:50
