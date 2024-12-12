-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-12-2024 a las 18:38:23
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdtarea3dwes_oscar`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales`
--

CREATE TABLE `credenciales` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `credenciales`
--

INSERT INTO `credenciales` (`id`, `password`, `usuario`) VALUES
(1, 'admin', 'admin'),
(2, 'juan123', 'juan'),
(3, 'carlos123', 'carlos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplares`
--

CREATE TABLE `ejemplares` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `idplanta` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ejemplares`
--

INSERT INTO `ejemplares` (`id`, `nombre`, `idplanta`) VALUES
(1, 'PINO_1', 2),
(2, 'PINO_2', 2),
(3, 'ABETO_3', 4),
(4, 'ABETO_4', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `id` bigint(20) NOT NULL,
  `fechahora` date DEFAULT NULL,
  `mensaje` varchar(255) DEFAULT NULL,
  `idejemplar` bigint(20) DEFAULT NULL,
  `idpersona` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`id`, `fechahora`, `mensaje`, `idejemplar`, `idpersona`) VALUES
(1, '2024-12-01', 'Añadido nuevo ejemplar PINO_1 por admin (2024-12-12 ).', 1, 1),
(2, '2024-12-02', 'Añadido nuevo ejemplar PINO_2 por admin (2024-12-12 ).', 2, 1),
(3, '2024-12-10', 'Añadido nuevo ejemplar ABETO_3 por admin (2024-12-12 ).', 3, 1),
(4, '2024-12-11', 'Añadido nuevo ejemplar ABETO_4 por carlos (2024-12-12 ).', 4, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `idcredencial` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id`, `email`, `nombre`, `idcredencial`) VALUES
(1, 'juan@juan.es', 'Juan', 1),
(2, 'juan@micorreo.es', 'Juan', 2),
(3, 'Carlos@micorreo.es', 'Carlos', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plantas`
--

CREATE TABLE `plantas` (
  `id` bigint(20) NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `nombrecientifico` varchar(255) DEFAULT NULL,
  `nombrecomun` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `plantas`
--

INSERT INTO `plantas` (`id`, `codigo`, `nombrecientifico`, `nombrecomun`) VALUES
(1, 'NOGAL', 'Juglans regia', 'Nogal'),
(2, 'PINO', 'Pinus', 'Pino'),
(3, 'ROBLE', 'Quercus', 'Roble'),
(4, 'ABETO', 'Abies alba', 'Abeto');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKgl50fmouks2ue8s9yclvv059j` (`usuario`);

--
-- Indices de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKclleiwyydddhkx72v38u6uw0l` (`idplanta`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi0faybj2dxdm0opshpnd6ofpk` (`idejemplar`),
  ADD KEY `FKownge66kxf3cic28hro55y809` (`idpersona`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKa9nsn5goh6u4d0iauvejw4ixf` (`idcredencial`);

--
-- Indices de la tabla `plantas`
--
ALTER TABLE `plantas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKbqo6lbeads0ifdh6dohhfhryp` (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `plantas`
--
ALTER TABLE `plantas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD CONSTRAINT `FKclleiwyydddhkx72v38u6uw0l` FOREIGN KEY (`idplanta`) REFERENCES `plantas` (`id`);

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `FKi0faybj2dxdm0opshpnd6ofpk` FOREIGN KEY (`idejemplar`) REFERENCES `ejemplares` (`id`),
  ADD CONSTRAINT `FKownge66kxf3cic28hro55y809` FOREIGN KEY (`idpersona`) REFERENCES `personas` (`id`);

--
-- Filtros para la tabla `personas`
--
ALTER TABLE `personas`
  ADD CONSTRAINT `FKdnp6v917jdx8smpjgqx0aqbln` FOREIGN KEY (`idcredencial`) REFERENCES `credenciales` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
