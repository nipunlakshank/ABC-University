-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 28, 2023 at 06:19 PM
-- Server version: 8.0.33
-- PHP Version: 8.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `abc_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int NOT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `mobile` varchar(14) DEFAULT NULL,
  `email_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `fname`, `lname`, `mobile`, `email_id`, `created_at`, `updated_at`) VALUES
(1, 'Madhavi', 'Illangakoon', '0771234567', 13, '2023-08-28 17:44:41', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `degree`
--

CREATE TABLE `degree` (
  `id` int NOT NULL,
  `title` varchar(254) DEFAULT NULL,
  `is_active` tinyint DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `degree`
--

INSERT INTO `degree` (`id`, `title`, `is_active`, `created_at`, `updated_at`) VALUES
(1, 'Information Technology', 1, '2023-08-23 09:26:19', '2023-08-25 21:02:00'),
(2, 'Communicative English', 1, '2023-08-23 09:29:09', NULL),
(3, 'Counseling Psychology', 1, '2023-08-23 09:29:09', '2023-08-25 21:02:23'),
(4, 'Management Studies', 0, '2023-08-23 09:29:09', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `degree_has_student`
--

CREATE TABLE `degree_has_student` (
  `id` int NOT NULL,
  `degree_id` int NOT NULL,
  `student_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `degree_has_student`
--

INSERT INTO `degree_has_student` (`id`, `degree_id`, `student_id`) VALUES
(3, 1, 1),
(4, 2, 1),
(5, 1, 2),
(6, 3, 3),
(7, 4, 3),
(8, 4, 4),
(9, 1, 4),
(10, 1, 6),
(11, 2, 6),
(12, 2, 7),
(13, 1, 7);

-- --------------------------------------------------------

--
-- Table structure for table `email`
--

CREATE TABLE `email` (
  `id` int NOT NULL,
  `address` varchar(254) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `email`
--

INSERT INTO `email` (`id`, `address`) VALUES
(2, 'dilshan@gmail.com'),
(3, 'mewan@gmail.com'),
(4, 'kevin@gmail.com'),
(5, 'yohan@gmail.com'),
(6, 'nipunfernando@gmail.com'),
(7, 'ashen@gmail.com'),
(8, 'nadun@gmail.com'),
(9, 'sudeepa@gmail.com'),
(11, 'dilan@gmail.com'),
(12, 'oshan@gmail.com'),
(13, 'madhaviillangakoon@gmail.com'),
(14, 'nimalikularathne@gmail.com'),
(15, 'sewmini@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `id` int NOT NULL,
  `subject_id` int NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `status` tinyint DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`id`, `subject_id`, `code`, `status`, `created_at`, `updated_at`) VALUES
(2, 1, 'IT 165 / EX 02', 1, '2023-08-28 12:43:54', NULL),
(3, 5, 'IT 182 / EX 03', 1, '2023-08-28 13:14:21', NULL),
(4, 28, 'ENG 170 / EX 04', 0, '2023-08-28 13:14:34', NULL),
(5, 7, 'IT 177 / EX 05', 0, '2023-08-28 17:26:14', NULL),
(6, 5, 'IT 182 / EX 06', 1, '2023-08-28 18:10:03', NULL),
(7, 6, 'IT 188 / EX 07', 0, '2023-08-28 18:10:52', NULL),
(8, 8, 'IT 191 / EX 08', 0, '2023-08-28 18:11:14', NULL),
(9, 9, 'IT 184 / EX 09', 0, '2023-08-28 18:11:35', NULL),
(10, 10, 'IT 172 / EX 10', 0, '2023-08-28 18:11:56', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `lecturer`
--

CREATE TABLE `lecturer` (
  `id` int NOT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `email_id` int NOT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `lecturer`
--

INSERT INTO `lecturer` (`id`, `fname`, `lname`, `email_id`, `mobile`, `created_at`, `updated_at`) VALUES
(1, 'Dilshan', 'Fernando', 2, '0784356364', '2023-08-27 08:46:15', NULL),
(2, 'Mewan', 'Perera', 3, '0724565745', '2023-08-27 08:46:42', NULL),
(3, 'Sudeepa', 'Hewage', 9, '0753598344', '2023-08-27 08:51:59', NULL),
(4, 'Dilan', 'Perera', 11, '0785344634', '2023-08-28 17:23:30', NULL),
(5, 'Nimali', 'Kularathne', 14, '0763214562', '2023-08-28 17:46:39', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `lecturer_has_subject`
--

CREATE TABLE `lecturer_has_subject` (
  `id` int NOT NULL,
  `lecturer_id` int NOT NULL,
  `subject_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `lecturer_has_subject`
--

INSERT INTO `lecturer_has_subject` (`id`, `lecturer_id`, `subject_id`) VALUES
(1, 1, 20),
(2, 1, 21),
(3, 1, 25),
(4, 3, 25),
(5, 2, 25),
(6, 1, 45),
(7, 3, 2),
(8, 2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `password`
--

CREATE TABLE `password` (
  `id` int NOT NULL,
  `hash` varchar(254) DEFAULT NULL,
  `salt` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `password`
--

INSERT INTO `password` (`id`, `hash`, `salt`) VALUES
(1, '4MrvzJuocI4Pc/W5e/lfq8WEhGCirw1KAdiZJn17av4=', 'A6k0EIDZCXDAGK55ZTTUH4CxnGJGfS'),
(2, 'RlSvHMBur9wMt9D87yyFrB4Xp8O8g6g8sX0qM65rE0o=', 'v9bjOz5gCexzAViTTxP4lZmVMG8urp'),
(3, 'CE2J9tn+QzvY2thdj/dvuDqFmJiFlLW+YM2q798bCiM=', 'ysszMz5v7BOaGsV2IfOqcOlkXesk5j'),
(4, 'DAcFWgQp9d49bFmTfNrqXbZl7wbc2MUnSc229S+MXaM=', 'Ve52fns30xEwdPkqtWanbK2oKUNXEe'),
(5, 'PZG3QTYn1lZwGsOqCHc/cC4ikfeNc0QSQqDBon660ck=', 'TXbhrxT7wLMDMsG6o5eITLOrqjjllu'),
(6, 'RGwtDhKZH8M3BLKh6ljKGVff+TYhTJ4G3wzM62uf+as=', 'xJDh5MWTGKNZplobUUeucmEczuEujI'),
(7, 'w8V79D71Z8TOhp5dhp09uKN/JqGXh0A4nUdZkE9yGyE=', 'Pyd1bppZy3f85q9FsK3gAzc7SK0x9T'),
(8, '6eV/hTCMGAq8o+uj8LMGboQFdbILE2Kb0zqwNwHupJQ=', 'lE9kiTOHQDQRShIyZHzhQrDyLkXJSg'),
(9, '1NiTi1pqyvaI+hkJ4fNcaARzTd90fUKZLhVelzULb4M=', 'StDbrh1L734BcOIpwwmrnSIrYlcReD'),
(10, 'q0X6AUQCIA4o2QKAQP3BQFi0E1WGcXyPGNjb5JkhuD0=', 'e53rwqFlpmROgKHe1Q0WfGGMKuzroa'),
(11, 'x570zeqLfNiPEsqs+dz0/VdmpHHE+ZtewigNczq8vcQ=', 'JEcsiJEPhRZuuvkATn8VXLm4wwXX8U');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int NOT NULL,
  `fname` varchar(45) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `sno` varchar(45) DEFAULT NULL,
  `email_id` int NOT NULL,
  `mobile` varchar(14) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `fname`, `lname`, `sno`, `email_id`, `mobile`, `created_at`, `updated_at`) VALUES
(1, 'Kevin', 'Perera', 'ST 0001', 4, '0774565645', '2023-08-27 08:47:48', '2023-08-27 08:47:48'),
(2, 'Yohan', 'Sanjith', 'ST 0002', 5, '0786356356', '2023-08-27 08:48:21', '2023-08-27 08:48:21'),
(3, 'Nipun', 'Fernando', 'ST 0003', 6, '0764534565', '2023-08-27 08:48:57', '2023-08-27 08:48:57'),
(4, 'Ashen', 'Malshan', 'ST 0004', 7, '0725645773', '2023-08-27 08:50:05', '2023-08-27 08:50:05'),
(5, 'Nadun', 'Deshan', 'ST 0005', 8, '0746563453', '2023-08-27 08:50:36', '2023-08-27 08:50:36'),
(6, 'Oshan', 'Dinilka', 'ST 0006', 12, '0743525573', '2023-08-28 17:24:31', '2023-08-28 17:24:31'),
(7, 'Sewmini', 'Perera', 'ST 0007', 15, '0763465462', '2023-08-28 17:47:32', '2023-08-28 17:47:32');

-- --------------------------------------------------------

--
-- Table structure for table `student_has_exam`
--

CREATE TABLE `student_has_exam` (
  `id` int NOT NULL,
  `student_id` int NOT NULL,
  `exam_id` int NOT NULL,
  `marks` double DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `student_has_exam`
--

INSERT INTO `student_has_exam` (`id`, `student_id`, `exam_id`, `marks`, `created_at`, `updated_at`) VALUES
(7, 1, 2, 90, '2023-08-28 12:43:54', NULL),
(8, 2, 2, 100, '2023-08-28 12:43:54', NULL),
(9, 4, 2, 56.5, '2023-08-28 12:43:54', NULL),
(10, 1, 3, 50, '2023-08-28 13:14:22', NULL),
(11, 2, 3, 87, '2023-08-28 13:14:22', NULL),
(12, 4, 3, 12.5, '2023-08-28 13:14:22', NULL),
(13, 1, 4, 80, '2023-08-28 13:14:34', NULL),
(14, 1, 5, 98.99, '2023-08-28 17:26:14', NULL),
(15, 2, 5, 68, '2023-08-28 17:26:14', NULL),
(16, 4, 5, 67, '2023-08-28 17:26:14', NULL),
(17, 6, 5, 98, '2023-08-28 17:26:14', NULL),
(18, 1, 6, NULL, '2023-08-28 18:10:03', NULL),
(19, 2, 6, NULL, '2023-08-28 18:10:03', NULL),
(20, 4, 6, NULL, '2023-08-28 18:10:03', NULL),
(21, 6, 6, NULL, '2023-08-28 18:10:03', NULL),
(22, 7, 6, 87, '2023-08-28 18:10:03', NULL),
(23, 1, 7, NULL, '2023-08-28 18:10:52', NULL),
(24, 2, 7, NULL, '2023-08-28 18:10:52', NULL),
(25, 4, 7, NULL, '2023-08-28 18:10:52', NULL),
(26, 6, 7, NULL, '2023-08-28 18:10:52', NULL),
(27, 7, 7, 78, '2023-08-28 18:10:52', NULL),
(28, 1, 8, NULL, '2023-08-28 18:11:14', NULL),
(29, 2, 8, NULL, '2023-08-28 18:11:14', NULL),
(30, 4, 8, NULL, '2023-08-28 18:11:14', NULL),
(31, 6, 8, NULL, '2023-08-28 18:11:14', NULL),
(32, 7, 8, 67, '2023-08-28 18:11:14', NULL),
(33, 1, 9, NULL, '2023-08-28 18:11:35', NULL),
(34, 2, 9, NULL, '2023-08-28 18:11:35', NULL),
(35, 4, 9, NULL, '2023-08-28 18:11:35', NULL),
(36, 6, 9, NULL, '2023-08-28 18:11:35', NULL),
(37, 7, 9, 96, '2023-08-28 18:11:35', NULL),
(38, 1, 10, NULL, '2023-08-28 18:11:57', NULL),
(39, 2, 10, NULL, '2023-08-28 18:11:57', NULL),
(40, 4, 10, NULL, '2023-08-28 18:11:57', NULL),
(41, 6, 10, NULL, '2023-08-28 18:11:57', NULL),
(42, 7, 10, 56, '2023-08-28 18:11:57', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `id` int NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `title` varchar(254) DEFAULT NULL,
  `credits` double DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `degree_id` int NOT NULL,
  `state` varchar(5) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`id`, `code`, `title`, `credits`, `semester`, `degree_id`, `state`, `created_at`, `updated_at`) VALUES
(1, 'IT 165', 'Introduction to Computing', 3, 1, 1, NULL, '2023-08-23 09:42:23', '2023-08-23 09:44:15'),
(2, 'IT 166', 'Presentation Skills', 1, 1, 1, NULL, '2023-08-23 09:44:15', '2023-08-23 10:55:11'),
(3, 'IT 170', 'Computer 2D Animations and Graphic Designinig', 2, 2, 1, NULL, '2023-08-23 10:55:12', NULL),
(4, 'IT 171', 'Foundation of Mathematics', 1, 2, 1, NULL, '2023-08-23 10:55:12', NULL),
(5, 'IT 182', 'Programming Methodologies', 2, 3, 1, NULL, '2023-08-23 10:55:12', NULL),
(6, 'IT 188', 'System Analysis and Design', 2, 3, 1, NULL, '2023-08-23 10:55:12', NULL),
(7, 'IT 177', 'Web Designing', 2, 3, 1, NULL, '2023-08-23 10:55:12', NULL),
(8, 'IT 191', 'Computer Programming I', 2, 4, 1, NULL, '2023-08-23 10:55:12', NULL),
(9, 'IT 184', 'Database Management Systems I', 2, 4, 1, NULL, '2023-08-23 10:55:12', NULL),
(10, 'IT 172', 'Computer Ethics', 1, 4, 1, NULL, '2023-08-23 10:55:12', NULL),
(11, 'IT 241', 'Software Engineering', 2, 4, 1, NULL, '2023-08-23 10:55:12', NULL),
(12, 'IT 181', 'Mathematics for Computing I', 2, 5, 1, NULL, '2023-08-23 10:55:12', NULL),
(13, 'IT 192', 'Information Systems', 2, 5, 1, NULL, '2023-08-23 10:55:12', NULL),
(14, 'IT 236', 'Database Management Systems II', 2, 5, 1, NULL, '2023-08-23 11:03:53', NULL),
(15, 'IT 194', 'Computer System Organization', 2, 5, 1, NULL, '2023-08-23 11:03:53', NULL),
(16, 'IT 248', 'Computer Programming II', 2, 6, 1, NULL, '2023-08-23 11:03:53', NULL),
(17, 'IT 197', 'Web Development', 2, 6, 1, NULL, '2023-08-23 11:03:53', NULL),
(18, 'IT 360', 'Project Work', 2, 6, 1, NULL, '2023-08-23 11:03:53', NULL),
(19, 'IT 189', 'Advanced Topics in IT', 2, 6, 1, NULL, '2023-08-23 11:03:53', NULL),
(20, 'ENG 150', 'Advanced English Grammer & Vocabulary Development Skills', 2, 1, 2, NULL, '2023-08-23 11:33:22', NULL),
(21, 'ENG 153', 'Advanced English Speaking & Listening Skills', 1, 1, 2, NULL, '2023-08-23 11:33:22', NULL),
(22, 'ENG 156', 'Advanced English Writing & Reading Skills', 2, 1, 2, NULL, '2023-08-23 11:33:22', NULL),
(23, 'ENG 158', 'Techniques of Higher Education', 1, 1, 2, NULL, '2023-08-23 11:33:22', NULL),
(24, 'ENG 160', 'Academic Essay Writing Skills', 1, 2, 2, NULL, '2023-08-23 11:33:22', NULL),
(25, 'ENG 163', 'Introduction to Presentation Skills in English', 2, 2, 2, NULL, '2023-08-23 11:33:22', NULL),
(26, 'ENG 166', 'Business Communication Skills', 2, 2, 2, NULL, '2023-08-23 11:33:22', NULL),
(27, 'ENG 168', 'Introduction to Linguistics', 2, 2, 2, NULL, '2023-08-23 11:33:22', NULL),
(28, 'ENG 170', 'Communicative Writing I', 1, 2, 2, NULL, '2023-08-23 11:33:22', NULL),
(29, 'ENG 200', 'Introduction to English Literature Study Skills', 2, 3, 2, NULL, '2023-08-23 11:33:22', NULL),
(30, 'ENG 203', 'Introduction to Public Speaking Skills', 1, 3, 2, NULL, '2023-08-23 11:33:22', NULL),
(31, 'ENG 206', 'Introduction to English Creative Writing Skills', 2, 3, 2, NULL, '2023-08-23 11:33:22', NULL),
(32, 'ENG 209', 'Advanced English Pronounciation Skills', 1, 3, 2, NULL, '2023-08-23 11:33:22', NULL),
(33, 'ENG 212', 'Communicative Writing II', 1, 4, 2, NULL, '2023-08-23 11:33:22', NULL),
(34, 'ENG 215', 'Effective Discourse Skills', 2, 4, 2, NULL, '2023-08-23 11:33:22', NULL),
(35, 'ENG 218', 'Analytical Reading', 2, 4, 2, NULL, '2023-08-23 11:33:22', NULL),
(36, 'ENG 220', 'Introduction to TESL', 2, 4, 2, NULL, '2023-08-23 11:33:22', NULL),
(37, 'ENG 223', 'Analytical Writing', 2, 5, 2, NULL, '2023-08-23 11:33:22', NULL),
(38, 'ENG 226', 'IELTS Study Skills', 2, 5, 2, NULL, '2023-08-23 11:33:22', NULL),
(39, 'ENG 228', 'Introduction to English Literature', 2, 5, 2, NULL, '2023-08-23 11:33:22', NULL),
(40, 'ENG 230', 'Introduction to Journalism', 2, 5, 2, NULL, '2023-08-23 11:33:22', NULL),
(41, 'ENG 300', 'Advanced Public Speaking Skills', 2, 6, 2, NULL, '2023-08-23 11:33:22', NULL),
(42, 'ENG 301', 'Advanced English Creative Writing Skills', 2, 6, 2, NULL, '2023-08-23 11:33:22', NULL),
(43, 'ENG 302', 'Advanced English Presentaion Skills', 2, 6, 2, NULL, '2023-08-23 11:33:22', NULL),
(44, 'CNS 165', 'Wetern Counselling Psychology - I (Theoretical Approach)', 3, 1, 3, NULL, '2023-08-23 11:45:28', NULL),
(45, 'CNS 200', 'Buddhist Counselling Psychology', 3, 1, 3, NULL, '2023-08-23 11:45:28', NULL),
(46, 'CNS 166', 'Western Counseling Psychology - II (Therapeutic Methods)', 3, 2, 3, NULL, '2023-08-23 11:45:28', NULL),
(47, 'CNS 204', 'Personality Development', 3, 2, 3, NULL, '2023-08-23 11:45:28', NULL),
(48, 'CNS 167', 'Family Counselling', 2, 3, 3, NULL, '2023-08-23 11:45:28', NULL),
(49, 'CNS 168', 'Child Psychology', 2, 3, 3, NULL, '2023-08-23 11:45:28', NULL),
(50, 'CNS 169', 'Educational Counselling', 2, 4, 3, NULL, '2023-08-23 11:45:28', NULL),
(51, 'CNS 170', 'Career Counselling', 2, 4, 3, NULL, '2023-08-23 11:45:28', NULL),
(52, 'CNS 201', 'Treatment Planning and Buddhist Counselling', 3, 5, 3, NULL, '2023-08-23 11:45:28', NULL),
(53, 'CNS 202', 'Counselling Ethics', 3, 5, 3, NULL, '2023-08-23 11:45:28', NULL),
(54, 'CNS 203', 'Counselling Skills', 2, 6, 3, NULL, '2023-08-23 11:45:28', NULL),
(55, 'CNS 300', 'Project Work', 2, 6, 3, NULL, '2023-08-23 11:45:28', NULL),
(56, 'MGT 165', 'Principles of Management', 2, 1, 4, NULL, '2023-08-23 12:01:47', NULL),
(57, 'MGT 166', 'Marketing Management', 2, 1, 4, NULL, '2023-08-23 12:01:47', NULL),
(58, 'MGT 167', 'Organizational Leadership', 2, 1, 4, NULL, '2023-08-23 12:01:47', NULL),
(59, 'MGT 168', 'Industrial Law and Industrial Relations', 2, 2, 4, NULL, '2023-08-23 12:01:47', NULL),
(60, 'MGT 169', 'Introduction to Financial Accounting', 2, 2, 4, NULL, '2023-08-23 12:01:47', NULL),
(61, 'MGT 343', 'Exposure Visits I', 0, 2, 4, NULL, '2023-08-23 12:01:47', NULL),
(62, 'MGT 170', 'Organizational Behavior', 2, 3, 4, NULL, '2023-08-23 12:01:47', NULL),
(63, 'MGT 171', 'Entrepreneurship', 2, 3, 4, NULL, '2023-08-23 12:01:47', NULL),
(64, 'MGT 172', 'Basic Statistics', 2, 3, 4, NULL, '2023-08-23 12:01:47', NULL),
(65, 'MGT 173', 'Operations Management', 2, 4, 4, NULL, '2023-08-23 12:01:47', NULL),
(66, 'MGT 174', 'Project Management', 2, 4, 4, NULL, '2023-08-23 12:01:47', NULL),
(67, 'MGT 175', 'Human Resource Mangement', 2, 4, 4, NULL, '2023-08-23 12:01:47', NULL),
(68, 'MGT 176', 'Management Information Systems', 2, 5, 4, NULL, '2023-08-23 12:01:47', NULL),
(69, 'MGT 177', 'Strategic Management', 2, 5, 4, NULL, '2023-08-23 12:01:47', NULL),
(70, 'MGT 178', 'Carrer Development', 2, 6, 4, NULL, '2023-08-23 12:01:47', NULL),
(71, 'MGT 344', 'Exposure Visits II', 0, 6, 4, NULL, '2023-08-23 12:01:47', NULL),
(72, 'MGT 345', 'Project Work', 6, 6, 4, NULL, '2023-08-23 12:01:47', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `email_id` int NOT NULL,
  `password_id` int NOT NULL,
  `user_type_id` int NOT NULL,
  `is_active` tinyint DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email_id`, `password_id`, `user_type_id`, `is_active`, `created_at`, `updated_at`) VALUES
(2, 2, 2, 4, 1, '2023-08-27 08:52:33', NULL),
(3, 4, 3, 5, 1, '2023-08-27 15:32:02', NULL),
(4, 8, 4, 5, 1, '2023-08-27 15:40:54', NULL),
(6, 3, 6, 4, 1, '2023-08-28 13:20:18', NULL),
(7, 11, 7, 4, 1, '2023-08-28 17:25:35', NULL),
(8, 12, 8, 5, 1, '2023-08-28 17:28:04', NULL),
(9, 13, 9, 3, 1, '2023-08-28 17:44:41', NULL),
(10, 14, 10, 4, 1, '2023-08-28 17:48:32', NULL),
(11, 15, 11, 5, 1, '2023-08-28 17:49:46', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_type`
--

CREATE TABLE `user_type` (
  `id` int NOT NULL,
  `type` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `user_type`
--

INSERT INTO `user_type` (`id`, `type`) VALUES
(3, 'admin'),
(4, 'lecturer'),
(5, 'student');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_admin_email1_idx` (`email_id`);

--
-- Indexes for table `degree`
--
ALTER TABLE `degree`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `degree_has_student`
--
ALTER TABLE `degree_has_student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_degree_has_student_degree1_idx` (`degree_id`),
  ADD KEY `fk_degree_has_student_student1_idx` (`student_id`);

--
-- Indexes for table `email`
--
ALTER TABLE `email`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `exam`
--
ALTER TABLE `exam`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_exam_subject1_idx` (`subject_id`);

--
-- Indexes for table `lecturer`
--
ALTER TABLE `lecturer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_lecturer_email1_idx` (`email_id`);

--
-- Indexes for table `lecturer_has_subject`
--
ALTER TABLE `lecturer_has_subject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_lecturer_has_subject_subject1_idx` (`subject_id`),
  ADD KEY `fk_lecturer_has_subject_lecturer1_idx` (`lecturer_id`);

--
-- Indexes for table `password`
--
ALTER TABLE `password`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_student_email1_idx` (`email_id`);

--
-- Indexes for table `student_has_exam`
--
ALTER TABLE `student_has_exam`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_student_has_exam_exam1_idx` (`exam_id`),
  ADD KEY `fk_student_has_exam_student1_idx` (`student_id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_subject_degree1_idx` (`degree_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user_user_type_idx` (`user_type_id`),
  ADD KEY `fk_user_email1_idx` (`email_id`),
  ADD KEY `fk_user_password1_idx` (`password_id`);

--
-- Indexes for table `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `degree`
--
ALTER TABLE `degree`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `degree_has_student`
--
ALTER TABLE `degree_has_student`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `email`
--
ALTER TABLE `email`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `exam`
--
ALTER TABLE `exam`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `lecturer`
--
ALTER TABLE `lecturer`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `lecturer_has_subject`
--
ALTER TABLE `lecturer_has_subject`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `password`
--
ALTER TABLE `password`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `student_has_exam`
--
ALTER TABLE `student_has_exam`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `user_type`
--
ALTER TABLE `user_type`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `fk_admin_email1` FOREIGN KEY (`email_id`) REFERENCES `email` (`id`);

--
-- Constraints for table `degree_has_student`
--
ALTER TABLE `degree_has_student`
  ADD CONSTRAINT `fk_degree_has_student_degree1` FOREIGN KEY (`degree_id`) REFERENCES `degree` (`id`),
  ADD CONSTRAINT `fk_degree_has_student_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);

--
-- Constraints for table `exam`
--
ALTER TABLE `exam`
  ADD CONSTRAINT `fk_exam_subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`);

--
-- Constraints for table `lecturer`
--
ALTER TABLE `lecturer`
  ADD CONSTRAINT `fk_lecturer_email1` FOREIGN KEY (`email_id`) REFERENCES `email` (`id`);

--
-- Constraints for table `lecturer_has_subject`
--
ALTER TABLE `lecturer_has_subject`
  ADD CONSTRAINT `fk_lecturer_has_subject_lecturer1` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturer` (`id`),
  ADD CONSTRAINT `fk_lecturer_has_subject_subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `fk_student_email1` FOREIGN KEY (`email_id`) REFERENCES `email` (`id`);

--
-- Constraints for table `student_has_exam`
--
ALTER TABLE `student_has_exam`
  ADD CONSTRAINT `fk_student_has_exam_exam1` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`),
  ADD CONSTRAINT `fk_student_has_exam_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);

--
-- Constraints for table `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `fk_subject_degree1` FOREIGN KEY (`degree_id`) REFERENCES `degree` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_email1` FOREIGN KEY (`email_id`) REFERENCES `email` (`id`),
  ADD CONSTRAINT `fk_user_password1` FOREIGN KEY (`password_id`) REFERENCES `password` (`id`),
  ADD CONSTRAINT `fk_user_user_type` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
