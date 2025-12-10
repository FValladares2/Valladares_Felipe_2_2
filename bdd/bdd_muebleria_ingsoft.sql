-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 10, 2025 at 11:33 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bdd_muebleria_ingsoft`
--

-- --------------------------------------------------------

--
-- Table structure for table `mueble`
--

CREATE TABLE `mueble` (
  `id_mueble` int(11) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `nombre_mueble` varchar(255) DEFAULT NULL,
  `precio_base` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `tamano` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mueble`
--

INSERT INTO `mueble` (`id_mueble`, `estado`, `material`, `nombre_mueble`, `precio_base`, `stock`, `tipo`, `tamano`) VALUES
(52, 'activo', 'si', 'testing 1', 3, 10, 'silla', 'mediano'),
(102, 'activo', 'madera', 'escritorio pc', 15, 11, 'mesa', 'mediano');

-- --------------------------------------------------------

--
-- Table structure for table `mueble_seq`
--

CREATE TABLE `mueble_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mueble_seq`
--

INSERT INTO `mueble_seq` (`next_val`) VALUES
(201);

-- --------------------------------------------------------

--
-- Table structure for table `variante`
--

CREATE TABLE `variante` (
  `id_variante` int(11) NOT NULL,
  `modificacion` varchar(255) DEFAULT NULL,
  `precio_extra` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `id_mueble` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `variante`
--

INSERT INTO `variante` (`id_variante`, `modificacion`, `precio_extra`, `stock`, `id_mueble`) VALUES
(102, 'Normal', 0, 4, 52),
(103, 'metal', 2, 6, 52),
(152, 'Normal', 0, 8, 102),
(153, 'Versión limitada', 8, 3, 102);

-- --------------------------------------------------------

--
-- Table structure for table `variante_seq`
--

CREATE TABLE `variante_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `variante_seq`
--

INSERT INTO `variante_seq` (`next_val`) VALUES
(251);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mueble`
--
ALTER TABLE `mueble`
  ADD PRIMARY KEY (`id_mueble`);

--
-- Indexes for table `variante`
--
ALTER TABLE `variante`
  ADD PRIMARY KEY (`id_variante`),
  ADD KEY `FK4j3mty4fml5nfyf0w9ulg4yb5` (`id_mueble`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `variante`
--
ALTER TABLE `variante`
  ADD CONSTRAINT `FK4j3mty4fml5nfyf0w9ulg4yb5` FOREIGN KEY (`id_mueble`) REFERENCES `mueble` (`id_mueble`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
