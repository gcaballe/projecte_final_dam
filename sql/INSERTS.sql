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

-- Volcando datos para la tabla m2_gcaballe.categoria: ~0 rows (aproximadamente)
DELETE FROM `categoria`;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` (`id`, `nom`, `pare`) VALUES
	(1, 'Montanyes', NULL),
	(2, 'Rius', NULL),
	(3, 'Rius grans', 2),
	(14, 'Platja', NULL),
	(16, 'Costa brava', 14),
	(19, 'Montanyes nevades', 1),
	(23, 'Fauna', NULL),
	(24, 'Test', NULL),
	(25, 'Fauna voladora', 23),
	(26, 'Fauna aquàtica', 23);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;

-- Volcando datos para la tabla m2_gcaballe.foto: ~0 rows (aproximadamente)
DELETE FROM `foto`;
/*!40000 ALTER TABLE `foto` DISABLE KEYS */;
INSERT INTO `foto` (`titol`, `url`) VALUES
	('Aiguamolls de l\'ebre', '5/aiguamolls.jpg'),
	('Aiguamolls 1', '5/aiguamolls1.jpg'),
	('Aiguamolls 2', '5/aiguamolls2.jpg'),
	('imatge de riu', 'Atuel_River.JPG');
/*!40000 ALTER TABLE `foto` ENABLE KEYS */;

-- Volcando datos para la tabla m2_gcaballe.punt: ~0 rows (aproximadamente)
DELETE FROM `punt`;
/*!40000 ALTER TABLE `punt` DISABLE KEYS */;
INSERT INTO `punt` (`numero`, `nom`, `descripcio`, `hora`, `latitud`, `longitud`, `elevacio`, `foto`, `ruta`) VALUES
	(1, 'Primer punt de la primera ruta. Aqui esmorzem.', 'Es el 1er', 20, 17.171717, 15.150023, 1000, 'Atuel_River.JPG', 1),
	(1, 'Primer punt de la segonaruta', 'Es el 1er', 24, 14.141234, 15.151234, 1000, 'Atuel_River.JPG', 3),
	(1, 'Primer punt de la ruta de l\'ebre', 'Aqui esmorzarem', 40, 41.088932, 0.630199, 50, '5/aiguamolls1.jpg', 5),
	(2, 'segon punt de la 1a ruta', 'blablablablalbal', 14, 17.130022, 14.141414, 1100, 'Atuel_River.JPG', 1),
	(2, 'Segon punt de la ruta d\'ebre.', 'Aqui es pot vuere X i Z.', 65, 41.088932, 0.630199, 60, '5/aiguamolls2.jpg', 5);
/*!40000 ALTER TABLE `punt` ENABLE KEYS */;

-- Volcando datos para la tabla m2_gcaballe.ruta: ~0 rows (aproximadamente)
DELETE FROM `ruta`;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
INSERT INTO `ruta` (`id`, `titol`, `descMarkDown`, `desnivell`, `alcadaMax`, `alcadaMin`, `distanciaKm`, `tempsAprox`, `circular`, `dificultat5`, `gpxFileUrl`, `foto`, `categoria`) VALUES
	(1, 'Primera ruta', '#Descirpcio en MarkDown\r\n**Lorem Ipsum** is simply dummy text of the printing and typesetting industry. **Lorem Ipsum** has been the industry\'s standard dummy text ever since the 1500s, when an unknown **printer** took a galley of type and scrambled it to make a type specimen book.', 300, 1800, 1500, 40, 600, 0, 5, 'a', 'Atuel_River.JPG', 2),
	(2, 'Segona ruta', '<h1>Descirpcio en HTML</h1>\r\n<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>', 400, 1000, 500, 20, 300, 0, 2, 'b', 'Atuel_River.JPG', 3),
	(3, 'Tercera ruta', '<h1>Descirpcio en HTML</h1>\r\n<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>', 300, 1800, 1500, 40, 600, 0, 3, 'a', 'Atuel_River.JPG', 2),
	(4, '4a ruta', '<h1>Descirpcio en HTML</h1>\r\n<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>', 300, 1800, 1500, 40, 600, 0, 3, 'a', 'Atuel_River.JPG', 2),
	(5, 'Ruta pels aiguamolls', '#Benvingut als aiguamolls\r\nUna pasejada curta pels **aiguamolls** per veure ocells', 100, 150, 50, 10, 300, 1, 1, 'b', '5/aiguamolls.jpg', 25);
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;

-- Volcando datos para la tabla m2_gcaballe.taula_autoincrement: ~0 rows (aproximadamente)
DELETE FROM `taula_autoincrement`;
/*!40000 ALTER TABLE `taula_autoincrement` DISABLE KEYS */;
INSERT INTO `taula_autoincrement` (`nom_taula`, `valor`) VALUES
	('categoria', 27);
/*!40000 ALTER TABLE `taula_autoincrement` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
