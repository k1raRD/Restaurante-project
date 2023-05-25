-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: restaurante
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `alimento`
--

DROP TABLE IF EXISTS `alimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alimento` (
  `idAlimento` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) COLLATE utf8_bin NOT NULL,
  `imagen` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `descripcion` varchar(300) COLLATE utf8_bin NOT NULL,
  `idTipoAlimento` int NOT NULL,
  `descuento` int DEFAULT NULL,
  `precio` decimal(4,2) NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `estatus` tinyint(1) NOT NULL,
  `idRestaurante` int DEFAULT NULL,
  `idMenu` int DEFAULT NULL,
  PRIMARY KEY (`idAlimento`),
  KEY `fk_alimento_tipo_alimento_idx` (`idTipoAlimento`),
  KEY `fk_alimento_restaurante_idx` (`idRestaurante`),
  KEY `fk_alimento_menu_idx` (`idMenu`),
  CONSTRAINT `fk_alimento_menu` FOREIGN KEY (`idMenu`) REFERENCES `menu` (`idMenu`),
  CONSTRAINT `fk_alimento_restaurante` FOREIGN KEY (`idRestaurante`) REFERENCES `restaurante` (`idRestaurante`),
  CONSTRAINT `fk_alimento_tipo_alimento` FOREIGN KEY (`idTipoAlimento`) REFERENCES `tipo_alimento` (`idTipoAlimento`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alimento`
--

LOCK TABLES `alimento` WRITE;
/*!40000 ALTER TABLE `alimento` DISABLE KEYS */;
/*!40000 ALTER TABLE `alimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `idEmpleado` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8_bin NOT NULL,
  `primerApellido` varchar(45) COLLATE utf8_bin NOT NULL,
  `segundoApellido` varchar(45) COLLATE utf8_bin NOT NULL,
  `idRol` int NOT NULL,
  `idSucursal` int DEFAULT NULL,
  `usuario` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(45) COLLATE utf8_bin NOT NULL,
  `email` varchar(150) COLLATE utf8_bin NOT NULL,
  `superadmin` tinyint(1) DEFAULT NULL,
  `superadmingeneral` tinyint(1) DEFAULT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `estatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`idEmpleado`),
  KEY `fk_empleado_rol1_idx` (`idRol`),
  KEY `fk_empleado_sucursal1_idx1` (`idSucursal`),
  CONSTRAINT `fk_empleado_rol1` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`),
  CONSTRAINT `fk_empleado_sucursal1` FOREIGN KEY (`idSucursal`) REFERENCES `sucursal` (`idSucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `idMenu` int NOT NULL AUTO_INCREMENT,
  `clave` varchar(11) COLLATE utf8_bin NOT NULL,
  `descripcion` varchar(500) COLLATE utf8_bin NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `estatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`idMenu`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurante`
--

DROP TABLE IF EXISTS `restaurante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurante` (
  `idRestaurante` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_bin NOT NULL,
  `imagen` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `slogan` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `idTipoRestaurante` int NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `estatus` tinyint(1) NOT NULL,
  `idMenu` int NOT NULL,
  PRIMARY KEY (`idRestaurante`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `fk_restaurante_tipo_restaurante1_idx` (`idTipoRestaurante`),
  KEY `fk_restaurante_menu1_idx` (`idMenu`),
  CONSTRAINT `fk_restaurante_menu1` FOREIGN KEY (`idMenu`) REFERENCES `menu` (`idMenu`),
  CONSTRAINT `fk_restaurante_tipo_restaurante1` FOREIGN KEY (`idTipoRestaurante`) REFERENCES `tipo_restaurante` (`idTipoRestaurante`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurante`
--

LOCK TABLES `restaurante` WRITE;
/*!40000 ALTER TABLE `restaurante` DISABLE KEYS */;
/*!40000 ALTER TABLE `restaurante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurante_has_tipo_alimento`
--

DROP TABLE IF EXISTS `restaurante_has_tipo_alimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurante_has_tipo_alimento` (
  `idRestaurante` int NOT NULL,
  `idTipoAlimento` int NOT NULL,
  PRIMARY KEY (`idRestaurante`,`idTipoAlimento`),
  KEY `fk_restaurante_has_tipo_alimento_tipo_alimento1_idx` (`idTipoAlimento`),
  KEY `fk_restaurante_has_tipo_alimento_restaurante1_idx` (`idRestaurante`),
  CONSTRAINT `fk_restaurante_has_tipo_alimento_restaurante1` FOREIGN KEY (`idRestaurante`) REFERENCES `restaurante` (`idRestaurante`),
  CONSTRAINT `fk_restaurante_has_tipo_alimento_tipo_alimento1` FOREIGN KEY (`idTipoAlimento`) REFERENCES `tipo_alimento` (`idTipoAlimento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurante_has_tipo_alimento`
--

LOCK TABLES `restaurante_has_tipo_alimento` WRITE;
/*!40000 ALTER TABLE `restaurante_has_tipo_alimento` DISABLE KEYS */;
/*!40000 ALTER TABLE `restaurante_has_tipo_alimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idRol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8_bin NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `estatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sucursal` (
  `idSucursal` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `idRestaurante` int NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `estatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`idSucursal`),
  KEY `fk_sucursal_restaurante1_idx` (`idRestaurante`),
  CONSTRAINT `fk_sucursal_restaurante1` FOREIGN KEY (`idRestaurante`) REFERENCES `restaurante` (`idRestaurante`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_alimento`
--

DROP TABLE IF EXISTS `tipo_alimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_alimento` (
  `idTipoAlimento` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) COLLATE utf8_bin NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `estatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`idTipoAlimento`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_alimento`
--

LOCK TABLES `tipo_alimento` WRITE;
/*!40000 ALTER TABLE `tipo_alimento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_alimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_restaurante`
--

DROP TABLE IF EXISTS `tipo_restaurante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_restaurante` (
  `idTipoRestaurante` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `fechaCreacion` datetime NOT NULL,
  `fechaModificacion` datetime DEFAULT NULL,
  `estatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`idTipoRestaurante`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_restaurante`
--

LOCK TABLES `tipo_restaurante` WRITE;
/*!40000 ALTER TABLE `tipo_restaurante` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_restaurante` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-11 23:36:18
