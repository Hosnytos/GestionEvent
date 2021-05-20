-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 20 mai 2021 à 22:16
-- Version du serveur :  5.7.31
-- Version de PHP : 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_gestion_event`
--

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

DROP TABLE IF EXISTS `contact`;
CREATE TABLE IF NOT EXISTS `contact` (
  `id_contact` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_msg` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `sujet` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_contact`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contact`
--

INSERT INTO `contact` (`id_contact`, `date_msg`, `email`, `message`, `nom`, `sujet`) VALUES
(5, '2021-05-20 00:00:00', 'pJokic@cia.com', 'J\'aimerai qu\'on aborde la question de mon salaire...', 'Jeremy Ferrand', 'Plainte'),
(6, NULL, 'hosnynaiya27@gmail.com', 'k jkjjl m   \'ukv.', 'HOSNY NAIYA DEMOKOLO', 'Plainte');

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id_event` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_event` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `localisation` varchar(255) DEFAULT NULL,
  `nom_event` varchar(255) DEFAULT NULL,
  `prix` double NOT NULL,
  `quantite` double NOT NULL,
  `type_event` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_event`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id_event`, `date_event`, `description`, `localisation`, `nom_event`, `prix`, `quantite`, `type_event`) VALUES
(4, '2022-02-09', 'Suite du film Black Panther', 'Los Angeles', 'Black Panther 2 : T\'challa\'s Legacy', 50, 599, 'Film'),
(5, '2024-05-16', 'JackyBoy is back !!!', 'Los Angeles', 'Pirates of the Caribbean', 35, 549, 'Film'),
(7, '2016-08-10', 'Combat de prison illégal', 'Guantánamo', 'El Perro - El Lobo', 750, 99, 'Combat'),
(8, '2021-06-15', 'Finale NBA entre les champions en titre de Lebron James et les Warriors de Stephen Curry', 'Staples Center', 'Lakers - Warriors', 650, 5699, 'Match'),
(9, '2019-04-27', 'Finale du tournoi de Roland-Garros entre deux légendes', 'Paris', 'Nadal - Djokovic', 1000, 12000, 'Match'),
(10, '2022-10-22', 'Retrouvez vos héros préférés au Valhalla !', 'Canada', 'Vikings : The movie', 15, 1299, 'Film'),
(11, '2021-11-01', 'Rejoignez la marche pour lutter contre le massacre des écureuils en Picardie !', 'Paris', 'Je suis Alvin', 0, 199999, 'Manifestation'),
(12, '2018-05-11', 'Opéra tirée du jeu vidéo Assassin\'s Creed II et réalisé par le WDR Funkhausorchester', 'Cologne', 'Ezio’s Family Concert Suite', 120, 7000, 'Concert'),
(13, '2019-05-11', 'Fête démentielle de 5 jours', 'Miami', 'Party in Peace', 300, 701, 'Festival'),
(14, '2021-12-31', 'Wrestlemania XXVI', 'Massachusetts', 'John Cena - Randy Orton', 100, 1500, 'Catch');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `id_perso` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mdp` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_perso`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`id_perso`, `age`, `email`, `mdp`, `nom`, `prenom`) VALUES
(1, 47, 'pJokic@cia.com', '$2a$10$DKFfZN/kklcM9kQj9Xw78uBh8w7iKT4QGxLueB9ITym9yE3.lGEEC', 'Jokic', 'Paulo'),
(2, 28, 'jCruz@gign.fr', '$2a$10$eOCmk.hcVY9G62pdxgqBt.C.qHVoZPeViNqNAwaCRVatorKXm5fj.', 'Cruz', 'Jessica'),
(3, 30, 'fHansel@reich.deu', '$2a$10$XeVQ3OSmOQnnXgtJ9amUGudSF7qRGskJxZE4psTEDo8VxDh.yw2Ru', 'Friedriech', 'Hansel');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id_res` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_res` datetime DEFAULT NULL,
  `id_event` bigint(20) DEFAULT NULL,
  `id_perso` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_res`),
  KEY `FK27ksoa8xtfkusy23gct1a2n7l` (`id_event`),
  KEY `FK3f6op4jt02o6kg6d1j2xeh18h` (`id_perso`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_res`, `date_res`, `id_event`, `id_perso`) VALUES
(6, '2021-05-20 16:19:09', 5, 2),
(7, '2021-05-20 22:54:54', 7, 1),
(8, '2021-05-20 22:55:06', 10, 1),
(9, '2021-05-20 22:55:47', 8, 3),
(10, '2021-05-20 22:55:56', 11, 3),
(11, '2021-05-21 00:06:08', 4, 2);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id_role`, `nom`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE IF NOT EXISTS `users_roles` (
  `personnes_id_perso` bigint(20) NOT NULL,
  `roles_id_role` int(11) NOT NULL,
  KEY `FKfha5cfvb7wcbcjtpyi4xuinl8` (`roles_id_role`),
  KEY `FKc2d2eq39381biomtoxq9adwsj` (`personnes_id_perso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users_roles`
--

INSERT INTO `users_roles` (`personnes_id_perso`, `roles_id_role`) VALUES
(2, 2),
(2, 1),
(1, 1),
(3, 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK27ksoa8xtfkusy23gct1a2n7l` FOREIGN KEY (`id_event`) REFERENCES `evenement` (`id_event`),
  ADD CONSTRAINT `FK3f6op4jt02o6kg6d1j2xeh18h` FOREIGN KEY (`id_perso`) REFERENCES `personne` (`id_perso`);

--
-- Contraintes pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FKc2d2eq39381biomtoxq9adwsj` FOREIGN KEY (`personnes_id_perso`) REFERENCES `personne` (`id_perso`),
  ADD CONSTRAINT `FKfha5cfvb7wcbcjtpyi4xuinl8` FOREIGN KEY (`roles_id_role`) REFERENCES `role` (`id_role`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
