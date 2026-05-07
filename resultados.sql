-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 07-Maio-2026 às 03:49
-- Versão do servidor: 10.1.38-MariaDB
-- versão do PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `corridas`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `resultados`
--

CREATE TABLE `resultados` (
  `id` int(11) NOT NULL,
  `piloto` varchar(25) DEFAULT NULL,
  `time` varchar(25) DEFAULT NULL,
  `prim_volta` int(11) DEFAULT NULL,
  `seg_volta` int(11) DEFAULT NULL,
  `soma` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `resultados`
--

INSERT INTO `resultados` (`id`, `piloto`, `time`, `prim_volta`, `seg_volta`, `soma`) VALUES
(2, 'Murilo', 'time do Murilo', 3, 2, 5),
(3, '', '', 0, 0, 0),
(4, '', '', 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `resultados`
--
ALTER TABLE `resultados`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `resultados`
--
ALTER TABLE `resultados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
