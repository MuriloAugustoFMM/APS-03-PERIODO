-- phpMyAdmin SQL Dump
-- version 5.2.1deb3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Tempo de geração: 05/05/2026 às 22:07
-- Versão do servidor: 8.0.45-0ubuntu0.24.04.1
-- Versão do PHP: 8.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `corridas`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `pilots`
--

CREATE TABLE `pilots` (
  `Pilot_id` int NOT NULL,
  `Pilot_name` varchar(255) NOT NULL,
  `Pilot_age` int DEFAULT NULL,
  `Pilot_team` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `race`
--

CREATE TABLE `race` (
  `Race_id` int NOT NULL,
  `Race_name` varchar(255) NOT NULL,
  `Race_city` varchar(255) DEFAULT NULL,
  `Race_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Despejando dados para a tabela `race`
--

INSERT INTO `race` (`Race_id`, `Race_name`, `Race_city`, `Race_date`) VALUES
(1, '11111', '1111', '1111-11-11');

-- --------------------------------------------------------

--
-- Estrutura para tabela `races_link`
--

CREATE TABLE `races_link` (
  `Race_link_id` int NOT NULL,
  `Race_id` int NOT NULL,
  `Pilot_id` int NOT NULL,
  `Team_id` int NOT NULL,
  `Vehicle_id` int NOT NULL,
  `First_round` varchar(50) DEFAULT NULL,
  `Second_round` varchar(50) DEFAULT NULL,
  `Sum_rounds` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `teams`
--

CREATE TABLE `teams` (
  `Team_id` int NOT NULL,
  `Team_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `vehicles`
--

CREATE TABLE `vehicles` (
  `Vehicle_id` int NOT NULL,
  `Vehicle_model` varchar(255) NOT NULL,
  `Vehicle_brand` varchar(255) NOT NULL,
  `Vehicle_power` int DEFAULT NULL,
  `Vehicle_team` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `pilots`
--
ALTER TABLE `pilots`
  ADD PRIMARY KEY (`Pilot_id`),
  ADD KEY `fk_pilot_team` (`Pilot_team`);

--
-- Índices de tabela `race`
--
ALTER TABLE `race`
  ADD PRIMARY KEY (`Race_id`);

--
-- Índices de tabela `races_link`
--
ALTER TABLE `races_link`
  ADD PRIMARY KEY (`Race_link_id`),
  ADD KEY `fk_link_race` (`Race_id`),
  ADD KEY `fk_link_pilot` (`Pilot_id`),
  ADD KEY `fk_link_team` (`Team_id`),
  ADD KEY `fk_link_vehicle` (`Vehicle_id`);

--
-- Índices de tabela `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`Team_id`);

--
-- Índices de tabela `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`Vehicle_id`),
  ADD KEY `fk_vehicle_team` (`Vehicle_team`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `pilots`
--
ALTER TABLE `pilots`
  MODIFY `Pilot_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `race`
--
ALTER TABLE `race`
  MODIFY `Race_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `races_link`
--
ALTER TABLE `races_link`
  MODIFY `Race_link_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `teams`
--
ALTER TABLE `teams`
  MODIFY `Team_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `vehicles`
--
ALTER TABLE `vehicles`
  MODIFY `Vehicle_id` int NOT NULL AUTO_INCREMENT;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `pilots`
--
ALTER TABLE `pilots`
  ADD CONSTRAINT `fk_pilot_team` FOREIGN KEY (`Pilot_team`) REFERENCES `teams` (`Team_id`);

--
-- Restrições para tabelas `races_link`
--
ALTER TABLE `races_link`
  ADD CONSTRAINT `fk_link_pilot` FOREIGN KEY (`Pilot_id`) REFERENCES `pilots` (`Pilot_id`),
  ADD CONSTRAINT `fk_link_race` FOREIGN KEY (`Race_id`) REFERENCES `race` (`Race_id`),
  ADD CONSTRAINT `fk_link_team` FOREIGN KEY (`Team_id`) REFERENCES `teams` (`Team_id`),
  ADD CONSTRAINT `fk_link_vehicle` FOREIGN KEY (`Vehicle_id`) REFERENCES `vehicles` (`Vehicle_id`);

--
-- Restrições para tabelas `vehicles`
--
ALTER TABLE `vehicles`
  ADD CONSTRAINT `fk_vehicle_team` FOREIGN KEY (`Vehicle_team`) REFERENCES `teams` (`Team_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
