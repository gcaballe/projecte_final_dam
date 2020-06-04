-- --------------------------------------------------------
-- Host:                         iguixa.hopto.org
-- Versión del servidor:         8.0.11 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura para tabla m2_gcaballe.categoria
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` int(8) NOT NULL,
  `nom` varchar(128) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `pare` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_NOM` (`nom`) USING BTREE,
  KEY `FK_CATEGORIA_CATEGORIA` (`pare`),
  CONSTRAINT `FK_CATEGORIA_CATEGORIA` FOREIGN KEY (`pare`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla m2_gcaballe.foto
CREATE TABLE IF NOT EXISTS `foto` (
  `titol` varchar(128) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla m2_gcaballe.punt
CREATE TABLE IF NOT EXISTS `punt` (
  `numero` int(8) NOT NULL,
  `nom` varchar(256) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `descripcio` varchar(1024) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `hora` int(11) DEFAULT NULL,
  `latitud` double(9,6) DEFAULT NULL,
  `longitud` double(9,6) DEFAULT NULL,
  `elevacio` int(8) DEFAULT NULL,
  `foto` varchar(128) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `ruta` int(8) NOT NULL,
  PRIMARY KEY (`numero`,`ruta`),
  UNIQUE KEY `UNIQUE_NOM` (`nom`) USING BTREE,
  KEY `FK_PUNT_FOTO` (`foto`),
  KEY `FK_PUNT_RUTA` (`ruta`),
  CONSTRAINT `FK_PUNT_FOTO` FOREIGN KEY (`foto`) REFERENCES `foto` (`url`) ON UPDATE CASCADE,
  CONSTRAINT `FK_PUNT_RUTA` FOREIGN KEY (`ruta`) REFERENCES `ruta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla m2_gcaballe.ruta
CREATE TABLE IF NOT EXISTS `ruta` (
  `id` int(8) NOT NULL,
  `titol` varchar(128) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `descMarkDown` varchar(1024) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `desnivell` int(8) NOT NULL,
  `alcadaMax` int(8) NOT NULL,
  `alcadaMin` int(8) NOT NULL,
  `distanciaKm` int(8) NOT NULL,
  `tempsAprox` int(8) NOT NULL,
  `circular` tinyint(1) NOT NULL,
  `dificultat5` int(1) NOT NULL,
  `gpxFileUrl` varchar(256) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `foto` varchar(128) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `categoria` int(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `titol` (`titol`),
  KEY `FK_RUTA_CATEGORIA` (`categoria`),
  KEY `FK_RUTA_FOTO` (`foto`),
  CONSTRAINT `FK_RUTA_CATEGORIA` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`id`),
  CONSTRAINT `FK_RUTA_FOTO` FOREIGN KEY (`foto`) REFERENCES `foto` (`url`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla m2_gcaballe.taula_autoincrement
CREATE TABLE IF NOT EXISTS `taula_autoincrement` (
  `nom_taula` varchar(128) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `valor` bigint(8) NOT NULL,
  PRIMARY KEY (`nom_taula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- La exportación de datos fue deseleccionada.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
