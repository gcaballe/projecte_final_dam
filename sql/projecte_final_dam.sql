-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-05-2020 a las 20:54:41
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `projecte_final_dam`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(8) NOT NULL,
  `nom` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `pare` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nom`, `pare`) VALUES
(1, 'Montanyes', NULL),
(2, 'Rius', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `foto`
--

CREATE TABLE `foto` (
  `titol` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `url` varchar(256) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `foto`
--

INSERT INTO `foto` (`titol`, `url`) VALUES
('imatge de riu', 'https://upload.wikimedia.org/wikipedia/commons/5/56/Atuel_River.JPG');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `punt`
--

CREATE TABLE `punt` (
  `numero` int(8) NOT NULL,
  `nom` varchar(256) COLLATE utf8_spanish_ci NOT NULL,
  `descripcio` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `hora` int(11) DEFAULT NULL,
  `latitud` double(9,6) DEFAULT NULL,
  `longitud` double(9,6) DEFAULT NULL,
  `elevacio` int(8) DEFAULT NULL,
  `foto` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `ruta` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `punt`
--

INSERT INTO `punt` (`numero`, `nom`, `descripcio`, `hora`, `latitud`, `longitud`, `elevacio`, `foto`, `ruta`) VALUES
(1, 'Primer punt de la primera ruta', 'Es el 1er', 22, 14.140000, 15.150000, 1000, 'https://upload.wikimedia.org/wikipedia/commons/5/56/Atuel_River.JPG', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

CREATE TABLE `ruta` (
  `id` int(8) NOT NULL,
  `titol` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `descMarkDown` varchar(1024) COLLATE utf8_spanish_ci NOT NULL,
  `desnivell` int(8) NOT NULL,
  `alcadaMax` int(8) NOT NULL,
  `alcadaMin` int(8) NOT NULL,
  `distanciaKm` int(8) NOT NULL,
  `tempsAprox` int(8) NOT NULL,
  `circular` tinyint(1) NOT NULL,
  `dificultat5` int(1) NOT NULL,
  `gpxFileUrl` varchar(256) COLLATE utf8_spanish_ci DEFAULT NULL,
  `foto` varchar(128) COLLATE utf8_spanish_ci NOT NULL,
  `categoria` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `ruta`
--

INSERT INTO `ruta` (`id`, `titol`, `descMarkDown`, `desnivell`, `alcadaMax`, `alcadaMin`, `distanciaKm`, `tempsAprox`, `circular`, `dificultat5`, `gpxFileUrl`, `foto`, `categoria`) VALUES
(1, 'Primera ruta', '<h1>Descirpcio en HTML</h1>\r\n<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>', 300, 1800, 1500, 40, 600, 0, 5, NULL, 'https://upload.wikimedia.org/wikipedia/commons/5/56/Atuel_River.JPG', 2),
(2, 'Segona ruta', '<h1>Descirpcio en HTML</h1>\r\n<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>', 400, 1000, 500, 20, 300, 0, 2, NULL, 'https://upload.wikimedia.org/wikipedia/commons/5/56/Atuel_River.JPG', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQUE_NOM` (`nom`) USING BTREE,
  ADD KEY `FK_CATEGORIA_CATEGORIA` (`pare`);

--
-- Indices de la tabla `foto`
--
ALTER TABLE `foto`
  ADD PRIMARY KEY (`url`);

--
-- Indices de la tabla `punt`
--
ALTER TABLE `punt`
  ADD PRIMARY KEY (`numero`),
  ADD UNIQUE KEY `UNIQUE_NOM` (`nom`) USING BTREE,
  ADD KEY `FK_PUNT_FOTO` (`foto`),
  ADD KEY `FK_PUNT_RUTA` (`ruta`);

--
-- Indices de la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_RUTA_CATEGORIA` (`categoria`),
  ADD KEY `FK_RUTA_FOTO` (`foto`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD CONSTRAINT `FK_CATEGORIA_CATEGORIA` FOREIGN KEY (`pare`) REFERENCES `categoria` (`id`);

--
-- Filtros para la tabla `punt`
--
ALTER TABLE `punt`
  ADD CONSTRAINT `FK_PUNT_FOTO` FOREIGN KEY (`foto`) REFERENCES `foto` (`url`),
  ADD CONSTRAINT `FK_PUNT_RUTA` FOREIGN KEY (`ruta`) REFERENCES `ruta` (`id`);

--
-- Filtros para la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD CONSTRAINT `FK_RUTA_CATEGORIA` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `FK_RUTA_FOTO` FOREIGN KEY (`foto`) REFERENCES `foto` (`url`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
