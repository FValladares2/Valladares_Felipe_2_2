-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 12, 2025 at 02:59 AM
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
  `ID_mueble` int(11) NOT NULL,
  `nombre_mueble` text NOT NULL,
  `tipo` text NOT NULL,
  `precio_base` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `estado` enum('ACTIVO','INACTIVO') NOT NULL,
  `tamaño` enum('GRANDE','MEDIANO','PEQUEÑO') NOT NULL,
  `material` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mueble`
--

INSERT INTO `mueble` (`ID_mueble`, `nombre_mueble`, `tipo`, `precio_base`, `stock`, `estado`, `tamaño`, `material`) VALUES
(1, 'mueble_ejemplo', 'silla', 120, 2, 'ACTIVO', 'MEDIANO', 'madera'),
(2, 'mesa_madera', 'mesa', 15, 8, 'ACTIVO', 'MEDIANO', 'madera');

-- --------------------------------------------------------

--
-- Table structure for table `mueble_seq`
--

CREATE TABLE `mueble_seq` (
  `next_val` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mueble_seq`
--

INSERT INTO `mueble_seq` (`next_val`) VALUES
(2000);

-- --------------------------------------------------------

--
-- Table structure for table `variante`
--

CREATE TABLE `variante` (
  `ID_variante` int(11) NOT NULL,
  `ID_mueble` int(11) NOT NULL,
  `modificacion` text NOT NULL,
  `precio_extra` int(11) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `variante`
--

INSERT INTO `variante` (`ID_variante`, `ID_mueble`, `modificacion`, `precio_extra`, `stock`) VALUES
(1, 1, 'ruedas de escritorio', 500, 2),
(2, 2, 'Normal', 0, 4);

-- --------------------------------------------------------

--
-- Table structure for table `variante_seq`
--

CREATE TABLE `variante_seq` (
  `next_val` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `variante_seq`
--

INSERT INTO `variante_seq` (`next_val`) VALUES
(1950);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mueble`
--
ALTER TABLE `mueble`
  ADD PRIMARY KEY (`ID_mueble`);

--
-- Indexes for table `variante`
--
ALTER TABLE `variante`
  ADD PRIMARY KEY (`ID_variante`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mueble`
--
ALTER TABLE `mueble`
  MODIFY `ID_mueble` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1902;

--
-- AUTO_INCREMENT for table `variante`
--
ALTER TABLE `variante`
  MODIFY `ID_variante` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1853;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
