-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 09. Jan 2020 um 18:57
-- Server-Version: 10.4.10-MariaDB
-- PHP-Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `cr06_gehmayer`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `classrooms`
--

CREATE TABLE `classrooms` (
  `classRoomId` int(11) NOT NULL,
  `className` varchar(20) DEFAULT NULL,
  `fk_teacherId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `classrooms`
--

INSERT INTO `classrooms` (`classRoomId`, `className`, `fk_teacherId`) VALUES
(1, '1a', 1),
(2, '1b', 4),
(3, '2a', 3),
(4, '2b', 4),
(5, '3a', 2),
(6, '3b', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `student`
--

CREATE TABLE `student` (
  `studentId` int(11) NOT NULL,
  `studentName` varchar(55) DEFAULT NULL,
  `studentSurname` varchar(55) DEFAULT NULL,
  `studentEmail` varchar(55) DEFAULT NULL,
  `fk_classRoomId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `student`
--

INSERT INTO `student` (`studentId`, `studentName`, `studentSurname`, `studentEmail`, `fk_classRoomId`) VALUES
(1, 'Max', 'Mustermann', 'mustermann@mail.com', 1),
(2, 'Hubert', 'Deim', 'deim@gmail.com', 1),
(3, 'Mickey', 'Mouse', 'mouse@gmail.com', 2),
(4, 'Sasa', 'Zwutsch', 'zwu@gmail.com', 2),
(5, 'Hannah', 'Korn', 'korn@gmail.com', 3),
(6, 'Mister', 'Drei', 'drei@gmail.com', 4),
(7, 'Misses', 'Eins', 'oansa@gmail.com', 4),
(8, 'Markus', 'Pomf', 'sdsdf@gmail.com', 5),
(9, 'Sigi', 'Schnoesel', 'sigi@gmail.com', 5),
(10, 'Jana', 'Lustig', 'lustig@gmail.com', 6),
(11, 'David', 'Lustig', 'DDlustig@gmail.com', 3),
(12, 'Sara', 'Arnold', 'sara@gmail.com', 6);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `teacher`
--

CREATE TABLE `teacher` (
  `teacherId` int(11) NOT NULL,
  `teacherName` varchar(55) DEFAULT NULL,
  `teacherSurname` varchar(55) DEFAULT NULL,
  `teacherEmail` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `teacher`
--

INSERT INTO `teacher` (`teacherId`, `teacherName`, `teacherSurname`, `teacherEmail`) VALUES
(1, 'Mister', 'Wasois', 'wasois@gmail.com'),
(2, 'Mister', 'Wasnix', 'wasnix@gmail.com'),
(3, 'Misses', 'Wasois', 'mrswasois@gmail.com'),
(4, 'Misses', 'Wasnix', 'mrswasnix@gmail.com'),
(5, 'Herr', 'Klug', 'genius@gmail.com'),
(6, 'Frau', 'Besserwisser', 'besserwisser@gmail.com');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `classrooms`
--
ALTER TABLE `classrooms`
  ADD PRIMARY KEY (`classRoomId`),
  ADD KEY `fk_teacherId` (`fk_teacherId`);

--
-- Indizes für die Tabelle `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentId`),
  ADD KEY `fk_classRoomId` (`fk_classRoomId`);

--
-- Indizes für die Tabelle `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacherId`);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `classrooms`
--
ALTER TABLE `classrooms`
  ADD CONSTRAINT `fk_teacherId` FOREIGN KEY (`fk_teacherId`) REFERENCES `teacher` (`teacherId`);

--
-- Constraints der Tabelle `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `fk_classRoomId` FOREIGN KEY (`fk_classRoomId`) REFERENCES `classrooms` (`classRoomId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
