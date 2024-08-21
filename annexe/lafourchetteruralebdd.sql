-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           5.7.36 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.0.0.6468
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour lafourchetterurale
CREATE DATABASE IF NOT EXISTS `lafourchetterurale` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lafourchetterurale`;

-- Listage de la structure de table lafourchetterurale. adh
CREATE TABLE IF NOT EXISTS `adh` (
  `id_adherent` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_genre` bigint(20) DEFAULT NULL,
  `id_regime_alimentaire` bigint(20) DEFAULT NULL,
  `ID_ville` bigint(20) DEFAULT NULL,
  `Vil_ID_ville` bigint(20) DEFAULT NULL,
  `nom` varchar(254) NOT NULL,
  `prenom` varchar(254) NOT NULL,
  `mail` varchar(254) NOT NULL,
  `telephone` bigint(20) DEFAULT NULL,
  `naissance` datetime DEFAULT NULL,
  `rue_livraison` varchar(254) DEFAULT NULL,
  `information_supplementaire` varchar(254) DEFAULT NULL,
  `adresse_facture` varchar(254) DEFAULT NULL,
  `mot_de_passe` varchar(254) DEFAULT NULL,
  `numero_carte_bancaire` bigint(20) DEFAULT NULL,
  `date_validite_carte_bancaire` datetime DEFAULT NULL,
  `nom_porteur_carte_bancaire` varchar(254) DEFAULT NULL,
  `role` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_adherent`),
  KEY `FK_est` (`id_genre`),
  KEY `FK_facturer` (`Vil_ID_ville`),
  KEY `FK_livrer` (`ID_ville`),
  KEY `FK_restreindre` (`id_regime_alimentaire`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.adh : 11 rows
/*!40000 ALTER TABLE `adh` DISABLE KEYS */;
INSERT INTO `adh` (`id_adherent`, `id_genre`, `id_regime_alimentaire`, `ID_ville`, `Vil_ID_ville`, `nom`, `prenom`, `mail`, `telephone`, `naissance`, `rue_livraison`, `information_supplementaire`, `adresse_facture`, `mot_de_passe`, `numero_carte_bancaire`, `date_validite_carte_bancaire`, `nom_porteur_carte_bancaire`, `role`) VALUES
	(1, NULL, NULL, NULL, NULL, 'Dupont', 'Jean', 'jean.dupont@example.com', NULL, NULL, NULL, NULL, NULL, 'motdepasse1', NULL, NULL, NULL, NULL),
	(2, NULL, NULL, NULL, NULL, 'Martin', 'Marie', 'marie.martin@example.com', NULL, NULL, NULL, NULL, NULL, 'motdepasse2', NULL, NULL, NULL, NULL),
	(3, NULL, NULL, NULL, NULL, 'Dubois', 'Pierre', 'pierre.dubois@example.com', NULL, NULL, NULL, NULL, NULL, 'motdepasse3', NULL, NULL, NULL, NULL),
	(4, NULL, NULL, NULL, NULL, 'Lefebvre', 'Sophie', 'sophie.lefebvre@example.com', NULL, NULL, NULL, NULL, NULL, 'motdepasse4', NULL, NULL, NULL, NULL),
	(5, NULL, NULL, NULL, NULL, 'Moreau', 'Julie', 'julie.moreau@example.com', NULL, NULL, NULL, NULL, NULL, 'motdepasse5', NULL, NULL, NULL, NULL),
	(6, 1, 1, 1, 1, 'Dupont', 'Jean', 'jean.dupont@example.com', 234567890, '1980-05-15 00:00:00', '123 Rue de la Liberté', 'Informations supplémentaires pour Jean', '123 Rue de la Facturation', 'motdepasseJean123', 1234567890123456, '2026-01-01 00:00:00', 'Jean Dupont', NULL),
	(7, 2, 2, 2, 2, 'Martin', 'Marie', 'marie.martin@example.com', 876543210, '1975-09-20 00:00:00', '456 Avenue de la Paix', 'Informations supplémentaires pour Marie', '456 Avenue de la Facturation', 'motdepasseMarie456', 6543210987654321, '2027-03-01 00:00:00', 'Marie Martin', NULL),
	(8, 1, 1, 1, 1, 'Dubois', 'Pierre', 'pierre.dubois@example.com', 567890123, '1983-03-10 00:00:00', '789 Boulevard de la République', 'Informations supplémentaires pour Pierre', '789 Boulevard de la Facturation', 'motdepassePierre789', 9876543210987654, '2028-02-15 00:00:00', 'Pierre Dubois', NULL),
	(9, NULL, NULL, NULL, NULL, 'Jean', 'Didine', 'melissaorange.fr', NULL, NULL, NULL, NULL, NULL, 'a', NULL, NULL, NULL, NULL),
	(10, 2, 2, 1, -1, 'Jean', 'Didine', 'melissa@orange.fr', 651743641, '1999-09-07 23:00:00', '123 rue de la liberté', 'Porte violette', '77160 Provins Docteur 36', 'a', 3500000000, '2025-09-07 22:00:00', 'LUDIVIN', NULL),
	(13, 2, 2, 7, -1, 'Liance', 'Rémi', 'liance.remi@gmail.com', 147896523, '1992-01-30 00:00:00', '2 rue de la hutte', 'porte au fond du couloire', '77160 provins rue du docteur march 78', 'aze', 1478147814781476, '2025-05-29 22:00:00', 'Liance Rémi', NULL);
/*!40000 ALTER TABLE `adh` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. adhesion
CREATE TABLE IF NOT EXISTS `adhesion` (
  `id_adhesion` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_type_adhesion` bigint(20) NOT NULL,
  `id_adherent` smallint(6) NOT NULL,
  `debut` datetime DEFAULT NULL,
  `fin_prevu` datetime DEFAULT NULL,
  `fin_effective` datetime DEFAULT NULL,
  `date_paiement` datetime DEFAULT NULL,
  `date_emission_facture` datetime DEFAULT NULL,
  PRIMARY KEY (`id_adhesion`),
  KEY `FK_choisi` (`id_adherent`),
  KEY `FK_correspondre` (`id_type_adhesion`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.adhesion : 10 rows
/*!40000 ALTER TABLE `adhesion` DISABLE KEYS */;
INSERT INTO `adhesion` (`id_adhesion`, `id_type_adhesion`, `id_adherent`, `debut`, `fin_prevu`, `fin_effective`, `date_paiement`, `date_emission_facture`) VALUES
	(1, 1, 1, '2024-02-17 12:21:09', '2024-03-17 12:21:25', '2024-03-07 12:23:44', NULL, NULL),
	(2, 2, 2, '2024-02-24 12:24:53', '2025-02-24 12:28:07', NULL, NULL, NULL),
	(3, 1, 3, '2024-02-25 12:27:27', '2024-03-25 12:28:54', '2024-02-27 12:29:05', NULL, NULL),
	(4, 1, 4, '2024-02-26 12:28:41', '2024-03-26 12:30:03', NULL, NULL, NULL),
	(5, 2, 5, '2024-02-27 12:31:29', NULL, NULL, NULL, NULL),
	(6, 1, 6, '2023-02-17 12:31:47', NULL, NULL, NULL, NULL),
	(7, 2, 10, '2024-03-11 00:00:00', '2025-03-11 00:00:00', NULL, '2024-03-11 00:00:00', '2024-03-11 00:00:00'),
	(8, 1, 11, '2024-03-11 00:00:00', '2024-04-09 23:00:00', NULL, '2024-03-11 00:00:00', '2024-03-11 00:00:00'),
	(9, 1, 12, '2024-03-11 00:00:00', '2024-04-09 23:00:00', NULL, '2024-03-11 00:00:00', '2024-03-11 00:00:00'),
	(10, 1, 13, '2024-03-11 00:00:00', '2024-04-09 23:00:00', NULL, '2024-03-11 00:00:00', '2024-03-11 00:00:00');
/*!40000 ALTER TABLE `adhesion` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. colis
CREATE TABLE IF NOT EXISTS `colis` (
  `id_colis` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_echec_livraison` bigint(20) DEFAULT NULL,
  `id_tournee` bigint(20) DEFAULT NULL,
  `livraison_prevu` datetime DEFAULT NULL,
  `livraison_effective` datetime DEFAULT NULL,
  `numero_colis` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_colis`),
  KEY `FK_definir` (`id_echec_livraison`),
  KEY `FK_transporter` (`id_tournee`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.colis : 7 rows
/*!40000 ALTER TABLE `colis` DISABLE KEYS */;
INSERT INTO `colis` (`id_colis`, `id_echec_livraison`, `id_tournee`, `livraison_prevu`, `livraison_effective`, `numero_colis`) VALUES
	(1, NULL, NULL, '2024-01-03 00:00:00', '2024-01-04 00:00:00', '1'),
	(2, NULL, NULL, '2024-01-03 00:00:00', '2024-01-03 00:00:00', '2'),
	(3, NULL, NULL, '2024-01-03 00:00:00', '2024-01-05 00:00:00', '3'),
	(4, 3, 12, '2024-03-18 00:00:00', '2024-03-11 10:49:03', NULL),
	(5, NULL, 12, '2024-03-18 00:00:00', NULL, NULL),
	(11, NULL, NULL, '2024-03-22 00:00:00', NULL, NULL),
	(10, 3, 21, '2024-03-20 00:00:00', '2024-03-11 15:42:07', NULL);
/*!40000 ALTER TABLE `colis` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. commande
CREATE TABLE IF NOT EXISTS `commande` (
  `id_commande` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_adherent` smallint(6) NOT NULL,
  `id_menu` bigint(20) DEFAULT NULL,
  `validation_com` datetime DEFAULT NULL,
  `annulation_com` datetime DEFAULT NULL,
  PRIMARY KEY (`id_commande`),
  KEY `FK_Association_11` (`id_menu`),
  KEY `FK_passer` (`id_adherent`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.commande : 13 rows
/*!40000 ALTER TABLE `commande` DISABLE KEYS */;
INSERT INTO `commande` (`id_commande`, `id_adherent`, `id_menu`, `validation_com`, `annulation_com`) VALUES
	(1, 6, 1, '2024-02-24 12:34:38', NULL),
	(2, 2, 2, '2024-02-25 12:34:55', '2024-02-26 12:34:59'),
	(3, 2, 3, '2024-02-26 12:37:26', NULL),
	(4, 3, 4, '2024-02-27 12:37:50', NULL),
	(5, 4, 5, '2024-02-27 12:38:50', '2024-02-27 13:39:13'),
	(6, 4, 1, '2024-02-27 13:45:35', NULL),
	(7, 4, 2, '2024-02-27 12:40:45', NULL),
	(8, 4, 5, '2024-02-27 12:41:56', NULL),
	(9, 10, NULL, '2024-03-11 00:00:00', NULL),
	(10, 10, NULL, '2024-03-11 00:00:00', NULL),
	(11, 11, NULL, '2024-03-11 00:00:00', NULL),
	(12, 12, NULL, '2024-03-11 00:00:00', NULL),
	(13, 13, NULL, '2024-03-11 00:00:00', NULL);
/*!40000 ALTER TABLE `commande` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. composition_menu
CREATE TABLE IF NOT EXISTS `composition_menu` (
  `id_compo` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_plat` bigint(20) NOT NULL,
  `id_menu` bigint(20) NOT NULL,
  `date_composition` datetime DEFAULT NULL,
  PRIMARY KEY (`id_compo`),
  KEY `FK_composer` (`id_menu`),
  KEY `FK_integrer` (`id_plat`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.composition_menu : 36 rows
/*!40000 ALTER TABLE `composition_menu` DISABLE KEYS */;
INSERT INTO `composition_menu` (`id_compo`, `id_plat`, `id_menu`, `date_composition`) VALUES
	(25, 13, 1, '2024-01-01 00:00:00'),
	(26, 21, 1, '2024-01-01 00:00:00'),
	(27, 20, 1, '2024-01-01 00:00:00'),
	(3, 3, 2, '2024-01-01 00:00:00'),
	(2, 2, 2, '2024-01-01 00:00:00'),
	(1, 1, 2, '2024-01-01 00:00:00'),
	(6, 6, 3, '2024-01-01 00:00:00'),
	(5, 5, 3, '2024-01-01 00:00:00'),
	(4, 4, 3, '2024-01-01 00:00:00'),
	(13, 10, 4, '2024-01-01 00:00:00'),
	(14, 12, 4, '2024-01-01 00:00:00'),
	(15, 6, 4, '2024-01-01 00:00:00'),
	(17, 14, 5, '2024-01-01 00:00:00'),
	(18, 15, 5, '2024-01-01 00:00:00'),
	(16, 13, 5, '2024-01-01 00:00:00'),
	(19, 16, 6, '2024-01-01 00:00:00'),
	(20, 17, 6, '2024-01-01 00:00:00'),
	(21, 18, 6, '2024-01-01 00:00:00'),
	(22, 7, 7, '2024-01-01 00:00:00'),
	(23, 19, 7, '2024-01-01 00:00:00'),
	(24, 20, 7, '2024-01-01 00:00:00'),
	(9, 9, 8, '2024-01-01 00:00:00'),
	(8, 8, 8, '2024-01-01 00:00:00'),
	(7, 7, 8, '2024-01-01 00:00:00'),
	(12, 3, 9, '2024-01-01 00:00:00'),
	(11, 11, 9, '2024-01-01 00:00:00'),
	(10, 10, 9, '2024-01-01 00:00:00'),
	(28, 22, 10, '2024-01-01 00:00:00'),
	(29, 23, 10, '2024-01-01 00:00:00'),
	(30, 3, 10, '2024-01-01 00:00:00'),
	(31, 24, 11, '2024-01-01 00:00:00'),
	(32, 25, 11, '2024-01-01 00:00:00'),
	(33, 15, 11, '2024-01-01 00:00:00'),
	(34, 26, 12, '2024-01-01 00:00:00'),
	(35, 27, 12, '2024-01-01 00:00:00'),
	(36, 28, 12, '2024-01-01 00:00:00');
/*!40000 ALTER TABLE `composition_menu` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. detail_com
CREATE TABLE IF NOT EXISTS `detail_com` (
  `Id_detail_com` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_colis` bigint(20) NOT NULL,
  `id_commande` bigint(20) NOT NULL,
  `id_plat` bigint(20) DEFAULT NULL,
  `id_menu_ouvert_selection` bigint(20) DEFAULT NULL,
  `Pla_id_plat` bigint(20) DEFAULT NULL,
  `Pla_id_plat2` bigint(20) DEFAULT NULL,
  `Date_choix` datetime DEFAULT NULL,
  `date_validation_choix` datetime DEFAULT NULL,
  `date_effective_repas` datetime DEFAULT NULL,
  `Date_de_reglement` datetime DEFAULT NULL,
  `date_emission_facture` datetime DEFAULT NULL,
  PRIMARY KEY (`Id_detail_com`),
  KEY `FK_choisir` (`id_menu_ouvert_selection`),
  KEY `FK_comporte` (`id_commande`),
  KEY `FK_comporter` (`id_colis`),
  KEY `FK_debuter` (`Pla_id_plat2`),
  KEY `FK_finir` (`Pla_id_plat`),
  KEY `FK_manger` (`id_plat`)
) ENGINE=MyISAM AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.detail_com : 77 rows
/*!40000 ALTER TABLE `detail_com` DISABLE KEYS */;
INSERT INTO `detail_com` (`Id_detail_com`, `id_colis`, `id_commande`, `id_plat`, `id_menu_ouvert_selection`, `Pla_id_plat`, `Pla_id_plat2`, `Date_choix`, `date_validation_choix`, `date_effective_repas`, `Date_de_reglement`, `date_emission_facture`) VALUES
	(1, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-01-10 00:00:00', '2023-06-10 00:00:00'),
	(2, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-01-10 00:00:00', '2023-06-10 00:00:00'),
	(3, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-01-10 00:00:00', '2023-06-10 00:00:00'),
	(4, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-01-10 00:00:00', '2023-06-10 00:00:00'),
	(5, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-01-10 00:00:00', '2023-06-10 00:00:00'),
	(6, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-01-10 00:00:00', '2023-06-10 00:00:00'),
	(7, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-01-10 00:00:00', '2023-09-10 00:00:00'),
	(8, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-01-10 00:00:00', '2023-09-10 00:00:00'),
	(9, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-01-10 00:00:00', '2024-09-10 00:00:00'),
	(10, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-01-10 00:00:00', '2024-03-10 00:00:00'),
	(11, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-01-10 00:00:00', '2024-03-10 00:00:00'),
	(12, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-01-10 00:00:00', '2024-03-10 00:00:00'),
	(13, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', NULL, '2024-03-10 00:00:00'),
	(14, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-03-11 14:37:54', '2024-02-10 00:00:00'),
	(15, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-03-11 14:37:54', '2024-02-10 00:00:00'),
	(16, 1, 1, 2, 1, 1, 1, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', '2024-03-11 14:37:54', '2024-02-10 00:00:00'),
	(17, 2, 2, 5, 2, 2, 2, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', NULL, '2024-02-10 00:00:00'),
	(18, 3, 3, 8, 3, 3, 3, '2024-01-05 00:00:00', '2024-01-05 00:00:00', '2024-01-08 00:00:00', NULL, '2024-02-10 00:00:00'),
	(19, 4, 9, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-18 00:00:00', NULL, '2024-03-18 00:00:00'),
	(20, 4, 9, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-19 00:00:00', NULL, '2024-03-19 00:00:00'),
	(21, 4, 9, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-19 00:00:00', NULL, '2024-03-19 00:00:00'),
	(22, 4, 9, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-03-20 00:00:00'),
	(23, 4, 9, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-03-20 00:00:00'),
	(24, 4, 9, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-21 00:00:00', NULL, '2024-03-21 00:00:00'),
	(25, 4, 9, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-21 00:00:00', NULL, '2024-03-21 00:00:00'),
	(26, 4, 9, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-22 00:00:00', NULL, '2024-03-22 00:00:00'),
	(27, 4, 9, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-22 00:00:00', NULL, '2024-03-22 00:00:00'),
	(28, 4, 9, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-23 00:00:00', NULL, '2024-03-23 00:00:00'),
	(29, 4, 9, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-23 00:00:00', NULL, '2024-03-23 00:00:00'),
	(30, 4, 9, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-24 00:00:00', NULL, '2024-03-24 00:00:00'),
	(31, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-18 00:00:00', NULL, '2024-04-10 23:00:00'),
	(32, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-18 00:00:00', NULL, '2024-04-10 23:00:00'),
	(33, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-19 00:00:00', NULL, '2024-04-10 23:00:00'),
	(34, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-19 00:00:00', NULL, '2024-04-10 23:00:00'),
	(35, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-04-10 23:00:00'),
	(36, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-04-10 23:00:00'),
	(37, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-21 00:00:00', NULL, '2024-04-10 23:00:00'),
	(38, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-21 00:00:00', NULL, '2024-04-10 23:00:00'),
	(39, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-22 00:00:00', NULL, '2024-04-10 23:00:00'),
	(40, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-22 00:00:00', NULL, '2024-04-10 23:00:00'),
	(41, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-23 00:00:00', NULL, '2024-04-10 23:00:00'),
	(42, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-23 00:00:00', NULL, '2024-04-10 23:00:00'),
	(43, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-24 00:00:00', NULL, '2024-04-10 23:00:00'),
	(44, 5, 10, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-24 00:00:00', NULL, '2024-04-10 23:00:00'),
	(45, 6, 11, 6, NULL, 5, 4, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-03-20 00:00:00'),
	(46, 6, 11, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-03-20 00:00:00'),
	(47, 6, 11, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-21 00:00:00', NULL, '2024-03-21 00:00:00'),
	(48, 7, 11, 6, NULL, 5, 4, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-03-20 00:00:00'),
	(49, 7, 11, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-03-20 00:00:00'),
	(50, 7, 11, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-21 00:00:00', NULL, '2024-03-21 00:00:00'),
	(51, 8, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-04-10 23:00:00'),
	(52, 8, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-04-10 23:00:00'),
	(53, 8, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-21 00:00:00', NULL, '2024-04-10 23:00:00'),
	(54, 8, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-21 00:00:00', NULL, '2024-04-10 23:00:00'),
	(55, 9, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-04-10 23:00:00'),
	(56, 9, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-04-10 23:00:00'),
	(57, 9, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-21 00:00:00', NULL, '2024-04-10 23:00:00'),
	(58, 9, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-21 00:00:00', NULL, '2024-04-10 23:00:00'),
	(59, 9, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-22 00:00:00', NULL, '2024-04-10 23:00:00'),
	(60, 9, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-22 00:00:00', NULL, '2024-04-10 23:00:00'),
	(61, 9, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-23 00:00:00', NULL, '2024-04-10 23:00:00'),
	(62, 9, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-23 00:00:00', NULL, '2024-04-10 23:00:00'),
	(63, 9, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-24 00:00:00', NULL, '2024-04-10 23:00:00'),
	(64, 9, 12, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-24 00:00:00', NULL, '2024-04-10 23:00:00'),
	(65, 10, 13, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-03-20 00:00:00'),
	(66, 10, 13, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-03-20 00:00:00'),
	(67, 10, 13, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-21 00:00:00', NULL, '2024-03-21 00:00:00'),
	(68, 11, 13, 3, NULL, 2, 1, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-03-20 00:00:00'),
	(69, 11, 13, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-20 00:00:00', NULL, '2024-03-20 00:00:00'),
	(70, 11, 13, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-21 00:00:00', NULL, '2024-03-21 00:00:00'),
	(71, 11, 13, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-22 00:00:00', NULL, '2024-03-22 00:00:00'),
	(72, 11, 13, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-22 00:00:00', NULL, '2024-03-22 00:00:00'),
	(73, 11, 13, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-23 00:00:00', NULL, '2024-03-23 00:00:00'),
	(74, 11, 13, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-23 00:00:00', NULL, '2024-03-23 00:00:00'),
	(75, 11, 13, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-24 00:00:00', NULL, '2024-03-24 00:00:00'),
	(76, 11, 13, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-24 00:00:00', NULL, '2024-03-24 00:00:00'),
	(77, 11, 13, 13, NULL, 21, 20, '2024-03-11 00:00:00', '2024-03-11 00:00:00', '2024-03-25 00:00:00', NULL, '2024-03-25 00:00:00');
/*!40000 ALTER TABLE `detail_com` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. disponibilite_livraison
CREATE TABLE IF NOT EXISTS `disponibilite_livraison` (
  `id_disponibilite_livraison` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_adherent` bigint(20) DEFAULT NULL,
  `id_jour` bigint(20) DEFAULT NULL,
  `Date_de_choix` datetime DEFAULT NULL,
  `Date_de_retrait` datetime DEFAULT NULL,
  PRIMARY KEY (`id_disponibilite_livraison`),
  KEY `FK_disponible` (`id_adherent`),
  KEY `FK_pointer` (`id_jour`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.disponibilite_livraison : 16 rows
/*!40000 ALTER TABLE `disponibilite_livraison` DISABLE KEYS */;
INSERT INTO `disponibilite_livraison` (`id_disponibilite_livraison`, `id_adherent`, `id_jour`, `Date_de_choix`, `Date_de_retrait`) VALUES
	(1, NULL, 1, '2024-02-27 08:00:00', NULL),
	(2, NULL, 2, '2024-02-28 09:00:00', '2024-02-28 14:00:00'),
	(3, NULL, 3, '2024-02-29 10:00:00', '2024-02-29 13:00:00'),
	(4, NULL, 1, '2024-02-27 08:30:00', NULL),
	(5, NULL, 2, '2024-02-28 10:00:00', '2024-02-28 13:00:00'),
	(6, NULL, 3, '2024-02-29 11:00:00', '2024-02-29 14:00:00'),
	(7, NULL, 1, '2024-02-27 09:00:00', NULL),
	(8, NULL, 2, '2024-02-28 11:00:00', '2024-02-28 15:00:00'),
	(9, NULL, 3, '2024-02-29 12:00:00', '2024-02-29 16:00:00'),
	(10, 10, 1, '2024-03-11 00:00:00', NULL),
	(11, 11, 2, '2024-03-11 00:00:00', NULL),
	(12, 11, 3, '2024-03-11 00:00:00', NULL),
	(13, 12, 2, '2024-03-11 00:00:00', NULL),
	(14, 12, 3, '2024-03-11 00:00:00', NULL),
	(15, 13, 2, '2024-03-11 00:00:00', NULL),
	(16, 13, 3, '2024-03-11 00:00:00', NULL);
/*!40000 ALTER TABLE `disponibilite_livraison` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. distribuer
CREATE TABLE IF NOT EXISTS `distribuer` (
  `ID_ville` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_tournee` bigint(20) NOT NULL,
  PRIMARY KEY (`ID_ville`,`id_tournee`),
  KEY `FK_distribuer` (`id_tournee`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.distribuer : 115 rows
/*!40000 ALTER TABLE `distribuer` DISABLE KEYS */;
INSERT INTO `distribuer` (`ID_ville`, `id_tournee`) VALUES
	(1, 1),
	(1, 2),
	(1, 9),
	(1, 10),
	(1, 11),
	(1, 12),
	(1, 13),
	(1, 14),
	(1, 15),
	(1, 18),
	(1, 19),
	(1, 21),
	(2, 1),
	(2, 9),
	(2, 10),
	(2, 11),
	(2, 12),
	(2, 13),
	(2, 14),
	(2, 15),
	(2, 16),
	(2, 20),
	(2, 22),
	(3, 3),
	(3, 9),
	(3, 10),
	(3, 11),
	(3, 12),
	(3, 13),
	(3, 14),
	(3, 15),
	(3, 16),
	(3, 19),
	(3, 23),
	(4, 4),
	(4, 9),
	(4, 10),
	(4, 11),
	(4, 12),
	(4, 13),
	(4, 14),
	(4, 15),
	(4, 17),
	(4, 20),
	(4, 21),
	(5, 9),
	(5, 10),
	(5, 11),
	(5, 12),
	(5, 13),
	(5, 14),
	(5, 15),
	(5, 17),
	(5, 20),
	(5, 22),
	(6, 9),
	(6, 10),
	(6, 11),
	(6, 12),
	(6, 13),
	(6, 14),
	(6, 15),
	(6, 17),
	(6, 20),
	(6, 21),
	(7, 9),
	(7, 10),
	(7, 11),
	(7, 12),
	(7, 13),
	(7, 14),
	(7, 15),
	(7, 17),
	(7, 19),
	(7, 21),
	(8, 9),
	(8, 10),
	(8, 11),
	(8, 12),
	(8, 13),
	(8, 14),
	(8, 15),
	(8, 16),
	(8, 20),
	(8, 22),
	(9, 9),
	(9, 10),
	(9, 11),
	(9, 12),
	(9, 13),
	(9, 14),
	(9, 15),
	(9, 18),
	(9, 20),
	(9, 23),
	(10, 9),
	(10, 10),
	(10, 11),
	(10, 12),
	(10, 13),
	(10, 14),
	(10, 15),
	(10, 16),
	(10, 20),
	(10, 23),
	(11, 9),
	(11, 10),
	(11, 11),
	(11, 12),
	(11, 13),
	(11, 14),
	(11, 15),
	(11, 18),
	(11, 20),
	(11, 23);
/*!40000 ALTER TABLE `distribuer` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. genre
CREATE TABLE IF NOT EXISTS `genre` (
  `id_genre` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle_genre` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_genre`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.genre : 3 rows
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` (`id_genre`, `libelle_genre`) VALUES
	(1, 'homme'),
	(2, 'femme'),
	(3, 'autre');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. jour_semaine
CREATE TABLE IF NOT EXISTS `jour_semaine` (
  `id_jour` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle_jour` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_jour`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.jour_semaine : 3 rows
/*!40000 ALTER TABLE `jour_semaine` DISABLE KEYS */;
INSERT INTO `jour_semaine` (`id_jour`, `libelle_jour`) VALUES
	(1, 'lundi'),
	(2, 'mercredi'),
	(3, 'vendredi');
/*!40000 ALTER TABLE `jour_semaine` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. livreur
CREATE TABLE IF NOT EXISTS `livreur` (
  `id_livreur` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(254) DEFAULT NULL,
  `prenom` varchar(254) DEFAULT NULL,
  `mail` varchar(254) DEFAULT NULL,
  `mot_de_passe` varchar(254) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_livreur`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.livreur : 3 rows
/*!40000 ALTER TABLE `livreur` DISABLE KEYS */;
INSERT INTO `livreur` (`id_livreur`, `nom`, `prenom`, `mail`, `mot_de_passe`, `role`) VALUES
	(1, 'Dupont', 'Antoine', 'mail@mail.com', 'DA123', NULL),
	(2, 'Leclerc', 'Marie ', 'mail@mail.com', 'LM123', NULL),
	(3, 'Martin', 'Julien', 'mail@mail.com', 'MJ123', NULL);
/*!40000 ALTER TABLE `livreur` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. menu
CREATE TABLE IF NOT EXISTS `menu` (
  `id_menu` bigint(20) NOT NULL AUTO_INCREMENT,
  `Caracteristique_type` varchar(254) DEFAULT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `creation` datetime DEFAULT NULL,
  `retrait` datetime DEFAULT NULL,
  PRIMARY KEY (`id_menu`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.menu : 12 rows
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`id_menu`, `Caracteristique_type`, `nom`, `creation`, `retrait`) VALUES
	(2, 'Défaut', 'Délices de la Mer', '2024-01-01 00:00:00', NULL),
	(3, 'Défaut', 'La Viande à l\'Honneur', '2024-01-01 00:00:00', NULL),
	(8, 'Défaut', 'Héritage Normand', '2024-01-01 00:00:00', NULL),
	(9, 'Défaut', 'Le Cassoulet à l\'Honneur', '2024-01-01 00:00:00', NULL),
	(4, 'Sans porc', 'Expérience Tendre et Croustillante', '2024-01-01 00:00:00', NULL),
	(5, 'Sans porc', 'Saveurs Orientales', '2024-01-01 00:00:00', NULL),
	(6, 'Sans porc', 'Délices Mexicains', '2024-01-01 00:00:00', NULL),
	(7, 'Sans porc', 'Terroir et Saveurs', '2024-01-01 00:00:00', NULL),
	(1, 'Végétarien', 'Plaisir de la Nature', '2024-01-01 00:00:00', NULL),
	(10, 'Végétarien', 'Végétalien et Gourmand', '2024-03-09 11:37:09', NULL),
	(11, 'Végétarien', 'Voyage Végétarien aux Saveurs d\'Orient', '2024-01-01 00:00:00', NULL),
	(12, 'Végétarien', 'Un Festin Italien Végétarien ', '2024-01-01 00:00:00', NULL);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. menu_ouvert_selection
CREATE TABLE IF NOT EXISTS `menu_ouvert_selection` (
  `id_menu_ouvert_selection` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_type_repas` bigint(20) NOT NULL,
  `id_menu` bigint(20) NOT NULL,
  `ouverture_selection` datetime DEFAULT NULL,
  `fermeture_selection` datetime DEFAULT NULL,
  `menu_defaut` tinyint(1) DEFAULT NULL,
  `position_jour` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_menu_ouvert_selection`),
  KEY `FK_Association_13` (`id_menu`),
  KEY `FK_correspond` (`id_type_repas`)
) ENGINE=MyISAM AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.menu_ouvert_selection : 60 rows
/*!40000 ALTER TABLE `menu_ouvert_selection` DISABLE KEYS */;
INSERT INTO `menu_ouvert_selection` (`id_menu_ouvert_selection`, `id_type_repas`, `id_menu`, `ouverture_selection`, `fermeture_selection`, `menu_defaut`, `position_jour`) VALUES
	(1, 1, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 1),
	(2, 2, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 1),
	(3, 1, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 2),
	(4, 2, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 2),
	(5, 1, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 3),
	(6, 2, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 3),
	(7, 1, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 4),
	(8, 2, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 4),
	(9, 1, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 5),
	(10, 2, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 5),
	(11, 1, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 6),
	(12, 2, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 6),
	(13, 1, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 7),
	(14, 2, 1, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 7),
	(15, 1, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 1),
	(16, 2, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 1),
	(17, 1, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 2),
	(18, 2, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 2),
	(19, 1, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 3),
	(20, 2, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 3),
	(21, 1, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 4),
	(22, 2, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 4),
	(23, 1, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 5),
	(24, 2, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 5),
	(25, 1, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 6),
	(26, 2, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 6),
	(27, 1, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 7),
	(28, 2, 3, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 7),
	(29, 1, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 1),
	(30, 2, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 1),
	(31, 1, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 2),
	(32, 2, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 2),
	(33, 1, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 3),
	(34, 2, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 3),
	(35, 1, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 4),
	(36, 2, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 4),
	(37, 1, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 5),
	(38, 2, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 5),
	(39, 1, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 6),
	(40, 2, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 6),
	(41, 1, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 7),
	(42, 2, 2, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 1, 7),
	(43, 1, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 1),
	(44, 2, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 1),
	(45, 1, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 2),
	(46, 2, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 2),
	(47, 1, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 3),
	(48, 2, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 3),
	(49, 1, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 4),
	(50, 2, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 4),
	(51, 1, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 5),
	(52, 2, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 5),
	(53, 1, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 6),
	(54, 2, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 6),
	(55, 1, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 7),
	(56, 2, 4, '2024-02-26 00:00:01', '2024-03-14 23:59:59', 0, 7),
	(57, 1, 5, '2024-03-15 00:00:01', '2024-03-16 23:59:59', 0, 3),
	(58, 2, 6, '2024-03-15 00:00:01', '2024-03-16 23:59:59', 1, 3),
	(59, 1, 7, '2024-03-15 00:00:01', '2024-03-16 23:59:59', 1, 4),
	(60, 2, 8, '2024-03-15 00:00:01', '2024-03-16 23:59:59', 0, 4);
/*!40000 ALTER TABLE `menu_ouvert_selection` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. motif_echec_livraison
CREATE TABLE IF NOT EXISTS `motif_echec_livraison` (
  `id_echec_livraison` bigint(20) NOT NULL AUTO_INCREMENT,
  `motif` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`id_echec_livraison`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.motif_echec_livraison : 5 rows
/*!40000 ALTER TABLE `motif_echec_livraison` DISABLE KEYS */;
INSERT INTO `motif_echec_livraison` (`id_echec_livraison`, `motif`) VALUES
	(1, 'Adhérent absent'),
	(2, 'Adresse introuvable'),
	(3, 'Colis endommagé'),
	(4, 'Refus du colis'),
	(5, 'Problème d’accès à l’adresse');
/*!40000 ALTER TABLE `motif_echec_livraison` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. plat
CREATE TABLE IF NOT EXISTS `plat` (
  `id_plat` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_regime_alimentaire` bigint(20) DEFAULT NULL,
  `id_type_plat` bigint(20) DEFAULT NULL,
  `nom_plat` varchar(254) DEFAULT NULL,
  `description` varchar(254) DEFAULT NULL,
  `url_image` varchar(254) DEFAULT NULL,
  `date_entree` datetime DEFAULT NULL,
  `date_retrait` datetime DEFAULT NULL,
  PRIMARY KEY (`id_plat`),
  KEY `FK_designer` (`id_type_plat`),
  KEY `FK_respecter` (`id_regime_alimentaire`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.plat : 28 rows
/*!40000 ALTER TABLE `plat` DISABLE KEYS */;
INSERT INTO `plat` (`id_plat`, `id_regime_alimentaire`, `id_type_plat`, `nom_plat`, `description`, `url_image`, `date_entree`, `date_retrait`) VALUES
	(1, 1, 1, 'Rillettes de Porc aux Algues', 'Rillettes de porc associée à la frâicheur des lagues et relevées avec du poivre dulse.', 'https://recettes.de/images/blogs/une-aiguille-dans-l-potage/rillettes-de-thon-aux-algues.640x480.jpg', '2024-01-01 00:00:00', NULL),
	(2, 1, 2, 'Dos de cabillaud et Sauce Choron', 'Dos de cabilllaud cuit au four accompagné de sa sauce choron préparée avec une  béchamel, du jaune d\'oeuf et du jus de tomate.', 'https://img.cuisineaz.com/660x660/2022/06/06/i184175-41-dos-de-cabillaud-au-four.jpeg', '2024-01-01 00:00:00', NULL),
	(3, 1, 3, 'Salade de Fruits de Saison', 'Fruits de saison accompagnés d\'une touche de frâicheur avec des feuilles de menthe.', 'https://i.f1g.fr/media/cms/1200x630_crop/2022/07/15/ea00d278dc710b675a1fd7bf08b8dfdba3ff8b73396f9faaaa963d58454801c9.jpg', '2024-01-01 00:00:00', NULL),
	(4, 1, 1, 'Croustillant de Pieds de Porc à la Crème d\'Ail et Ciboulette', 'Pieds de porc : partie souvent oubliée du porc, sublimée par une crème d\'ail et ciboulette. ', 'https://adc-dev-images-recipes.s3.eu-west-1.amazonaws.com/REP_jl_11412_croustillant_pied_porc_sauce_tartare.jpg', '2024-01-01 00:00:00', NULL),
	(5, 1, 2, 'Épaule de Porc Rôtie aux Pommes de Terre et Herbes', 'Épaule de porc tendre accompagnée de ses pommes de terre fondantes.', 'https://epicesdecru.com//upload/recettes/epaule-porc-frais.jpg', '2024-01-01 00:00:00', NULL),
	(6, 1, 3, 'Crumble aux Pommes', 'Crumble aux pommes léger et croustillant.', 'https://www.edelices.com/wordpress/wp-content/uploads/2015/01/Pomme-cannelle_1.jpg', '2024-01-01 00:00:00', NULL),
	(7, 1, 1, 'Croustillant de Camembert aux Noix et Confiture de Poires', 'Un mariage gourmand entre la chaleur du camembert fondu, le croquant des noix et la douceur de la confiture de poires.', 'https://recette-cuisine.net/wp-content/uploads/2020/04/Camembert-chaud-aux-poires-et-noix.jpg', '2024-01-01 00:00:00', NULL),
	(8, 1, 2, 'Tendre Escalope de Porc Normande à la Crème et Champignons', 'Escalope de porc fondante accompagnée d\'une sauce crémeuse aux champignons de Paris, rehaussée d\'une touche de Calvados pour une saveur authentique.', 'https://stephaniehurtubise.files.wordpress.com/2021/10/porc_champignon.jpg?w=640', '2024-01-01 00:00:00', NULL),
	(9, 1, 3, 'Tarte Tatin aux Pommes', 'Dessert classique pour une fin de repas fruitée.', 'https://img.cuisineaz.com/660x660/2016/10/08/i94576-tarte-tatin-aux-pommes.jpg', '2024-01-01 00:00:00', NULL),
	(10, 1, 1, 'Salade de Saison aux Oeufs Mimosa', 'Salade accompagnée des légumes frais de saison associée à la gourmandise des oeufs mimosa.', 'https://www.laylita.com/recettes/wp-content/uploads/2016/08/oeufs-mimosa-au-crabe-640x426.jpg', '2024-01-01 00:00:00', NULL),
	(11, 1, 2, 'Cassoulet Gourmand aux Saucisses et Lardons', 'Cassoulet généreux et savoureux, mijoté avec des haricots blancs, des saucisses de Toulouse, des lardons fumés, des carottes et des oignons.', 'https://img-3.journaldesfemmes.fr/9UAyHePOGIjMQYqRCXIu9mYjWYs=/225x150/smart/6e2d241d560a4726a1fdb3644646912b/ccmcms-jdf/39871588.jpg', '2024-01-01 00:00:00', NULL),
	(12, 3, 2, 'Poulet Frit Croustillant à la Chapelure Panko', 'Poulet frit enrobé d\'une chapelure panko dorée et croustillante, accompagné de sa purée de pomme de terre crémeuse.', 'https://www.lapetitechronique.com/wp-content/uploads/2020/10/Poulet-Katsu.jpg', '2024-01-01 00:00:00', NULL),
	(13, 3, 1, 'Houmous aux Crudités et Pain Pita', 'Purée onctueuse et savoureuse à base de pois chiches, de citron et d\'ail à savourer avec le pain pita.', 'https://img.cuisineaz.com/660x660/2013/12/20/i76796-hummus-et-pain-pita-maison.jpeg', '2024-01-01 00:00:00', NULL),
	(14, 3, 2, 'Tajine de Poulet aux Légumes et Semoule', 'Plat mijoté traditionnellement, accompagné de carottes, de pommes de terre, de poivrons, et d\'oignons.', 'https://assets.afcdn.com/recipe/20170303/8755_w1024h1024c1cx2654cy1771.jpg', '2024-01-01 00:00:00', NULL),
	(15, 3, 3, 'Baklava aux Noix et Miel', 'Pâtisserie orientale croustillante et sucrée.', 'https://www.laurentmariotte.com/wp-content/uploads/2022/11/whatsapp-image-2022-10-24-at-094617.jpeg', '2024-01-01 00:00:00', NULL),
	(16, 3, 1, 'Guacamole et Tortillas', 'Purée onctueuse et savoureuse à base d\'avocats, de tomates, d\'oignons, de coriandre et de citron vert à savourer avec les tortillas chips.', 'https://d1e3z2jco40k3v.cloudfront.net/-/media/project/oneweb/cho21/recipes/guac-with-cholula-chips-desktop.jpg?rev=-1&vd=00010101T000000Z&hash=0C58EA4EAC4B7D20BBBA02F5DB1A8A01', '2024-01-01 00:00:00', NULL),
	(17, 3, 2, 'Fajitas au Poulet et Légumes Grillés', 'Tortillas de maïs garnies de poulet et de légumes grillés de saison.', 'https://img.cuisineaz.com/660x660/2015/06/30/i91097-fajitas-au-poulet-et-aux-legumes.jpg', '2024-01-01 00:00:00', NULL),
	(18, 3, 3, 'Churros Allégés', 'Beignets légèrement parfumés à la cannelle.', 'https://cac.img.pmdstatic.net/scale/http.3A.2F.2Fprd2-bone-image.2Es3-website-eu-west-1.2Eamazonaws.2Ecom.2Fcac.2F2018.2F09.2F25.2Fd1178977-0718-4fde-b2ed-d081bc932e5d.2Ejpeg/autox450/quality/65/churros-sans-huile.jpg', '2024-01-01 00:00:00', NULL),
	(19, 3, 2, 'Cocotte de Poulet à la Normande et aux Champignons', 'Poulet mijoté avec des pommes, des oignons, des champignons et du cidre non alcoolisé. ', 'https://www.undejeunerdesoleil.com/wp-content/uploads/2021/10/Poulet_creme_champignons_recette.jpg', '2024-01-01 00:00:00', NULL),
	(20, 3, 3, 'Tarte Normande aux Pommes', 'Dessert traditionnel gourmand.', 'https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhNSl5iOAARXilTzX23lhc2sqCJkbGrLv71i2FkSGO7gqYmQJcBC2Ry1If8SDd-NE7YJ3KRL49vNBQb7fWwSgHgZZJEZ1Ngf5m9Ma0KRr8vg84U10nlxsgmQhkELR4U1AKHDwznQHPmD7A/s16000/tarte-normande-pommes-facile.jpg', '2024-01-01 00:00:00', NULL),
	(21, 2, 2, 'Risotto aux Champignons', 'Plat gourmand et parfumé, associant la texture crémeuse du risotto à la saveur intense des champignons, et asorti de légumes de saison.', 'https://img.passeportsante.net/1200x675/2021-03-29/i101004-.webp', '2024-01-01 00:00:00', NULL),
	(22, 2, 1, 'Brochettes de Champignons à l\'Ail et au Persil', 'Champignons de Paris marinés avec de l\'ail et du persil puis grillés.', 'https://www.lidl-recettes.fr/var/site/storage/images/_aliases/960x540/2/0/7/7/87702-3-fre-FR/Brochettes-de-champignons-a-l-ail.jpg', '2024-01-01 00:00:00', NULL),
	(23, 2, 2, 'Quiche Végétalienne aux Oignons', 'Pâte brisée maison sans beurre accueille un appareil onctueux de tofu soyeux, de lait végétal et d’oignons caramélisés.', 'https://dubiodansmonbento.com/wp-content/uploads/2019/06/quicheveganoignon6-640x550.jpg', '2024-01-01 00:00:00', NULL),
	(24, 2, 1, 'Salade Fattouche', 'Entrée rafraîchissante composée de tomates, concombres, radis, et oignons rouges, agrémentée de morceaux de pain pita grillé.', 'https://www.papillesetpupilles.fr/wp-content/uploads/2005/11/Salade-fattouch-%C2%A9-Louno-Morose-shutterstock.jpg', '2024-01-01 00:00:00', NULL),
	(25, 2, 2, 'Dahl de Lentilles Corail', 'Lentilles corail tendres, mijotées dans un mélange de lait de coco, de tomates, d’oignons, d’ail, et d’un bouquet d’épices orientales comme le curry, le curcuma et le gingembre. Servi avec un accompagnement de riz basmati.', 'https://yummix.fr/wp-content/uploads/2017/03/dhal-de-lentille-corail-1024x683.jpg', '2024-01-01 00:00:00', NULL),
	(26, 2, 1, 'Bruschetta aux Tomates et Basilic', 'Tranches de pain grillé frottées à l’ail et garnies de tomates fraîches coupées en dés, de basilic émincée et d’un filet d’huile d’olive extra vierge.', 'https://epicetoorecettes.fr/img/recettes/grande/recette-bruschetta-la-tomate-et-au-basilic.jpg', '2024-01-01 00:00:00', NULL),
	(27, 2, 2, 'Risotto aux Lentilles Corail et Légumes Grillés', 'Risotto crémeux de lentilles corail accompagné de légumes grillés de saison, le tout saupoudré de parmesan végétal pour une finition riche et savoureuse.', 'https://www.myrtee.fr/wp-content/uploads/2015/06/image33.jpg', '2024-01-01 00:00:00', NULL),
	(28, 2, 3, ' Panna Cotta Végétalienne à la Vanille et Coulis de Fruits Rouges', 'Panna cotta onctueuse à base de lait d’avoine et de crème de soja, parfumée à la vanille et nappée d’un coulis de fruits rouges frais.', 'https://www.auxdelicesdupalais.net/wp-content/uploads/2020/08/panna-cotta-2.jpg', '2024-01-01 00:00:00', NULL);
/*!40000 ALTER TABLE `plat` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. prefere
CREATE TABLE IF NOT EXISTS `prefere` (
  `id_adherent` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_specificite_alimentaire` bigint(20) NOT NULL,
  PRIMARY KEY (`id_adherent`,`id_specificite_alimentaire`),
  KEY `FK_prefere` (`id_specificite_alimentaire`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.prefere : 9 rows
/*!40000 ALTER TABLE `prefere` DISABLE KEYS */;
INSERT INTO `prefere` (`id_adherent`, `id_specificite_alimentaire`) VALUES
	(1, 1),
	(1, 2),
	(2, 1),
	(3, 3),
	(4, 1),
	(10, 1),
	(11, 1),
	(12, 1),
	(13, 1);
/*!40000 ALTER TABLE `prefere` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. regime_alimentaire
CREATE TABLE IF NOT EXISTS `regime_alimentaire` (
  `id_regime_alimentaire` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(254) DEFAULT NULL,
  `debut_prise_en_charge` datetime DEFAULT NULL,
  `fin_prise_en_charge` datetime DEFAULT NULL,
  PRIMARY KEY (`id_regime_alimentaire`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.regime_alimentaire : 3 rows
/*!40000 ALTER TABLE `regime_alimentaire` DISABLE KEYS */;
INSERT INTO `regime_alimentaire` (`id_regime_alimentaire`, `nom`, `debut_prise_en_charge`, `fin_prise_en_charge`) VALUES
	(1, 'Défaut', '2024-01-01 00:00:00', NULL),
	(2, 'Végétarien', '2024-01-01 00:00:00', NULL),
	(3, 'Sans porc', '2024-01-01 00:00:00', NULL);
/*!40000 ALTER TABLE `regime_alimentaire` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. specificite_alimentaire
CREATE TABLE IF NOT EXISTS `specificite_alimentaire` (
  `id_specificite_alimentaire` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(254) DEFAULT NULL,
  `debut_prise_en_charge` datetime DEFAULT NULL,
  `fin_prise_en_charge` datetime DEFAULT NULL,
  PRIMARY KEY (`id_specificite_alimentaire`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.specificite_alimentaire : 3 rows
/*!40000 ALTER TABLE `specificite_alimentaire` DISABLE KEYS */;
INSERT INTO `specificite_alimentaire` (`id_specificite_alimentaire`, `nom`, `debut_prise_en_charge`, `fin_prise_en_charge`) VALUES
	(1, 'sans spécificité', '2024-01-01 00:00:00', NULL),
	(2, 'appauvrit en sucre', '2024-01-01 00:00:00', NULL),
	(3, 'appauvrit en sel', '2024-01-01 00:00:00', NULL);
/*!40000 ALTER TABLE `specificite_alimentaire` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. tournee
CREATE TABLE IF NOT EXISTS `tournee` (
  `id_tournee` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_livreur` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `debut_tournee` datetime DEFAULT NULL,
  `fin_tournee` datetime DEFAULT NULL,
  PRIMARY KEY (`id_tournee`),
  KEY `FK_livrer` (`id_livreur`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.tournee : 13 rows
/*!40000 ALTER TABLE `tournee` DISABLE KEYS */;
INSERT INTO `tournee` (`id_tournee`, `id_livreur`, `date`, `debut_tournee`, `fin_tournee`) VALUES
	(1, 1, '2024-02-27 00:00:00', '2024-02-27 08:00:00', '2024-02-27 12:00:00'),
	(2, 2, '2024-02-28 00:00:00', '2024-02-28 09:00:00', '2024-02-28 14:00:00'),
	(3, 3, '2024-03-01 00:00:00', '2024-03-01 10:00:00', NULL),
	(4, 4, '2024-03-02 00:00:00', '2024-03-02 11:00:00', NULL),
	(5, 5, '2024-03-03 00:00:00', NULL, NULL),
	(6, 6, '2024-03-04 00:00:00', NULL, NULL),
	(7, 7, '2024-03-05 00:00:00', '2024-03-05 08:30:00', NULL),
	(8, 8, '2024-03-06 00:00:00', '2024-03-06 09:00:00', NULL),
	(9, 3, '2024-03-11 00:00:00', NULL, NULL),
	(10, 3, '2024-03-11 00:00:00', NULL, NULL),
	(23, 3, '2024-03-20 00:00:00', NULL, NULL),
	(21, 1, '2024-03-20 00:00:00', '2024-03-11 15:41:28', '2024-03-11 15:42:11'),
	(22, 2, '2024-03-20 00:00:00', NULL, NULL);
/*!40000 ALTER TABLE `tournee` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. type_adhesion
CREATE TABLE IF NOT EXISTS `type_adhesion` (
  `id_type_adhesion` bigint(20) NOT NULL AUTO_INCREMENT,
  `typ_id_type_adhesion` bigint(20) DEFAULT NULL,
  `nom` varchar(254) DEFAULT NULL,
  `duree` bigint(20) DEFAULT NULL,
  `tarif` bigint(20) DEFAULT NULL,
  `date_de_fin_validite` datetime DEFAULT NULL,
  PRIMARY KEY (`id_type_adhesion`),
  KEY `FK_remplace` (`typ_id_type_adhesion`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.type_adhesion : 2 rows
/*!40000 ALTER TABLE `type_adhesion` DISABLE KEYS */;
INSERT INTO `type_adhesion` (`id_type_adhesion`, `typ_id_type_adhesion`, `nom`, `duree`, `tarif`, `date_de_fin_validite`) VALUES
	(1, NULL, 'mensuelle', 30, 10, NULL),
	(2, NULL, 'annuelle', 365, 100, NULL);
/*!40000 ALTER TABLE `type_adhesion` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. type_plat
CREATE TABLE IF NOT EXISTS `type_plat` (
  `id_type_plat` bigint(20) NOT NULL AUTO_INCREMENT,
  `typ_id_type_plat` bigint(20) DEFAULT NULL,
  `type_plat` varchar(254) DEFAULT NULL,
  `tarif` bigint(20) DEFAULT NULL,
  `Date_de_retrait` datetime DEFAULT NULL,
  PRIMARY KEY (`id_type_plat`),
  KEY `FK_remplacer` (`typ_id_type_plat`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.type_plat : 3 rows
/*!40000 ALTER TABLE `type_plat` DISABLE KEYS */;
INSERT INTO `type_plat` (`id_type_plat`, `typ_id_type_plat`, `type_plat`, `tarif`, `Date_de_retrait`) VALUES
	(1, NULL, 'entrée', 5, NULL),
	(2, NULL, 'plat_principal', 11, NULL),
	(3, NULL, 'dessert', 4, NULL);
/*!40000 ALTER TABLE `type_plat` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. type_repas
CREATE TABLE IF NOT EXISTS `type_repas` (
  `id_type_repas` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle_repas` char(1) DEFAULT NULL,
  PRIMARY KEY (`id_type_repas`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.type_repas : 2 rows
/*!40000 ALTER TABLE `type_repas` DISABLE KEYS */;
INSERT INTO `type_repas` (`id_type_repas`, `libelle_repas`) VALUES
	(1, '1'),
	(2, '2');
/*!40000 ALTER TABLE `type_repas` ENABLE KEYS */;

-- Listage de la structure de table lafourchetterurale. ville
CREATE TABLE IF NOT EXISTS `ville` (
  `ID_ville` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nom_ville` varchar(254) DEFAULT NULL,
  `CP_ville` bigint(20) DEFAULT NULL,
  `date_integ` datetime DEFAULT NULL,
  `date_sortie` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_ville`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Listage des données de la table lafourchetterurale.ville : 11 rows
/*!40000 ALTER TABLE `ville` DISABLE KEYS */;
INSERT INTO `ville` (`ID_ville`, `Nom_ville`, `CP_ville`, `date_integ`, `date_sortie`) VALUES
	(1, 'Clef-Vallée-d\'Eure', 27490, '2024-01-01 00:00:00', NULL),
	(2, 'Autheuil-Authouillet', 27490, '2024-01-01 00:00:00', NULL),
	(3, 'Cailly-sur-Eure', 27490, '2024-01-01 00:00:00', NULL),
	(4, 'Ailly', 27600, '2024-01-01 00:00:00', NULL),
	(5, 'Le Val d\'Hazey', 27600, '2024-01-01 00:00:00', NULL),
	(6, 'Fontaine-Bellenger', 27600, '2024-01-01 00:00:00', NULL),
	(7, 'Gaillon', 27600, '2024-01-01 00:00:00', NULL),
	(8, 'Saint-Pierre-la-Garenne', 27600, '2024-01-01 00:00:00', NULL),
	(9, 'Saint-Julien-de-la-Liègue', 27600, '2024-01-01 00:00:00', NULL),
	(10, 'Heudebouville', 27600, '2024-01-01 00:00:00', NULL),
	(11, 'Saint-Aubin-sur-Gaillon', 27600, '2024-01-01 00:00:00', NULL);
/*!40000 ALTER TABLE `ville` ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
