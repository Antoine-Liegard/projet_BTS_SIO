-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 12 avr. 2018 à 12:56
-- Version du serveur :  5.7.19
-- Version de PHP :  7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `stages`
--

-- --------------------------------------------------------

--
-- Structure de la table `tablecentresformation`
--

DROP TABLE IF EXISTS `tablecentresformation`;
CREATE TABLE IF NOT EXISTS `tablecentresformation` (
  `idCentreFormation` int(11) NOT NULL AUTO_INCREMENT,
  `commune` varchar(30)  NOT NULL,
  `codePostal` varchar(11) NOT NULL,
  `numeroVoie` varchar(6) NOT NULL,
  `typeVoie` varchar(20)  NOT NULL,
  `nomVoie` varchar(30)  NOT NULL,
  `mail1` varchar(30)  NOT NULL,
  `telephone2` varchar(11) NOT NULL,
  `telephone1` varchar(11) NOT NULL,
  `mail2` varchar(30)  NOT NULL,
  PRIMARY KEY (`idCentreFormation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tabledomainesactivites`
--

DROP TABLE IF EXISTS `tabledomainesactivites`;
CREATE TABLE IF NOT EXISTS `tabledomainesactivites` (
  `idDomaineActivite` int(30) NOT NULL,
  `nomDomaineActivite` varchar(30) NOT NULL,
  `descriptionDomaineActivite` varchar(30) NOT NULL,
  PRIMARY KEY (`idDomaineActivite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tableentreprises`
--

DROP TABLE IF EXISTS `tableentreprises`;
CREATE TABLE IF NOT EXISTS `tableentreprises` (
  `idEntreprise` int(10) NOT NULL,
  `nom` varchar(25)  NOT NULL,
  `siret` varchar(40) NOT NULL,
  `nombreStagiaires` int(10) NOT NULL,
  `dateDernierStage` date NOT NULL,
  `commune` varchar(40) NOT NULL,
  `codePostal` varchar(5) NOT NULL,
  `numeroVoie` varchar(6) NOT NULL,
  `typeVoie` varchar(25) NOT NULL,
  `nomVoie` varchar(40) NOT NULL,
  `mail1` varchar(40) NOT NULL,
  `mail2` varchar(40) NOT NULL,
  `telephone1` varchar(25) NOT NULL,
  `telephone2` varchar(25) NOT NULL,
  PRIMARY KEY (`idEntreprise`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tableetudiants`
--

DROP TABLE IF EXISTS `tableetudiants`;
CREATE TABLE IF NOT EXISTS `tableetudiants` (
  `idEtudiant` int(11) NOT NULL AUTO_INCREMENT,
  `idSessionFormation` int(11) DEFAULT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `dateNaissance` date NOT NULL,
  `numeroSS` varchar(15) NOT NULL,
  `commune` varchar(30) NOT NULL,
  `codePostal` varchar(10) NOT NULL,
  `numeroVoie` varchar(10) DEFAULT NULL,
  `typeVoie` varchar(20) NOT NULL,
  `nomVoie` varchar(50) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `telephone1` varchar(12) NOT NULL,
  `telephone2` varchar(12) DEFAULT NULL, 
  PRIMARY KEY (`idEtudiant`),
  KEY `fk_etudiant_sessionFormation` (`idSessionFormation`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tableformations`
--

DROP TABLE IF EXISTS `tableformations`;
CREATE TABLE IF NOT EXISTS `tableformations` (
  `idFormation` int(11) NOT NULL AUTO_INCREMENT,
  `libelleDiplome` varchar(30) NOT NULL,
  `optionDiplome` varchar(30) NOT NULL,
  PRIMARY KEY (`idFormation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tableperiodesstages`
--

DROP TABLE IF EXISTS `tableperiodesstages`;
CREATE TABLE IF NOT EXISTS `tableperiodesstages` (
  `idPeriodeStage` int(11) NOT NULL AUTO_INCREMENT,
  `idEtudiant` int(11) NOT NULL,
  `idEnseignant` int(11) NOT NULL,
  `idTuteurEntreprise` int(11) NOT NULL,
  `idEntreprise` int(11) NOT NULL,
  `sujet` varchar(512) NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `infosComplementaires` varchar(2000) NOT NULL,
  `dureeHebdomadaire` int(11) NOT NULL,
  `etatConvention` int(11) NOT NULL,
  `dateVisite` date NOT NULL,
  `heureDebutVisite` datetime NOT NULL,
  `heureFinVisite` datetime NOT NULL,
  `visiteRealisee` int(11) NOT NULL,
  `OMValide` int(11) NOT NULL,
  PRIMARY KEY (`idPeriodeStage`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tablepersonnelpedagogique`
--

DROP TABLE IF EXISTS `tablepersonnelpedagogique`;
CREATE TABLE IF NOT EXISTS `tablepersonnelpedagogique` (
  `IdPersonnelPedagogique` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `infosComplementaires` varchar(60) NOT NULL,
  `prenoms` varchar(30) NOT NULL,
  `identifiant` varchar(30) NOT NULL,
  `motDePasse` varchar(30) NOT NULL,
  `commune` varchar(30) NOT NULL,
  `codePostal` varchar(10) NOT NULL,
  `numeroVoie` varchar(10) NOT NULL,
  `TypeVoie` varchar(20) NOT NULL,
  `nomVoie` varchar(50) NOT NULL,
  `mail1` varchar(50) NOT NULL,
  `telephone2` varchar(12) NOT NULL,
  `telephone1` varchar(12) NOT NULL,
  `mail2` varchar(50) NOT NULL,
  PRIMARY KEY (`IdPersonnelPedagogique`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tablesessionsformations`
--

DROP TABLE IF EXISTS `tablesessionsformations`;
CREATE TABLE IF NOT EXISTS `tablesessionsformations` (
  `idSessionFormation` int(11) NOT NULL AUTO_INCREMENT,
  `idFormation` int(11) NOT NULL,
  `idCentreFormation` int(11) NOT NULL,
  `debutSession` date NOT NULL,
  `finSession` date NOT NULL,
  PRIMARY KEY (`idSessionFormation`),
  KEY `fk_sessionFormation_Formation` (`idFormation`),
  KEY `fk_sessionFormation_centreFormation` (`idCentreFormation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tablesuivirecherchestage`
--

DROP TABLE IF EXISTS `tablesuivirecherchestage`;
CREATE TABLE IF NOT EXISTS `tablesuivirecherchestage` (
  `idSuiviRechercheStage` int(11) NOT NULL AUTO_INCREMENT,
  `idEtudiant` int(11) NOT NULL,
  `idEntreprise` int(11) NOT NULL,
  `datePremierContact` date NOT NULL,
  `typePremierContact` int(11) NOT NULL,
  `commentaires` varchar(2000) NOT NULL,
  `nombreRelances` int(11) NOT NULL,
  `dateRelance` date NOT NULL,
  `statut` int(11) NOT NULL,
  PRIMARY KEY (`idSuiviRechercheStage`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `tabletests`
--

DROP TABLE IF EXISTS `tabletests`;
CREATE TABLE IF NOT EXISTS `tabletests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `dateNaissance` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `tableetudiants`
--
ALTER TABLE `tableetudiants`
  ADD CONSTRAINT `fk_etudiant_sessionFormation` FOREIGN KEY (`idSessionFormation`) REFERENCES `tablesessionsformations` (`idSessionFormation`);

--
-- Contraintes pour la table `tablesessionsformations`
--
ALTER TABLE `tablesessionsformations`
  ADD CONSTRAINT `fk_sessionFormation_Formation` FOREIGN KEY (`idFormation`) REFERENCES `tableformations` (`idFormation`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sessionFormation_centreFormation` FOREIGN KEY (`idCentreFormation`) REFERENCES `tablecentresformation` (`idCentreFormation`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
