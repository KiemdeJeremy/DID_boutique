-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 28 mars 2025 à 15:38
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `did_boutique`
--

-- --------------------------------------------------------

--
-- Structure de la table `achat`
--

CREATE TABLE `achat` (
  `idAchat` int(10) NOT NULL,
  `dateAchat` date NOT NULL,
  `montant` double NOT NULL,
  `sommeEncaisse` double NOT NULL,
  `remise` double NOT NULL,
  `idUtilisateur` int(10) DEFAULT NULL,
  `idClient` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `achat`
--

INSERT INTO `achat` (`idAchat`, `dateAchat`, `montant`, `sommeEncaisse`, `remise`, `idUtilisateur`, `idClient`) VALUES
(40, '2025-02-22', 4000, 41000, 37000, 14, 55),
(41, '2025-02-11', 32000, 22500, -9500, 14, 56),
(42, '2025-02-08', 12000, 10000, -2000, 14, 58),
(43, '2025-03-16', 75233, 51321, -23912, 14, 61),
(44, '2025-03-23', 4000, 3323, -677, 14, 62);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `idClient` int(10) NOT NULL,
  `nom` varchar(15) NOT NULL,
  `telephone` int(75) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`idClient`, `nom`, `telephone`) VALUES
(55, 'KEBRE', 76523145),
(56, 'KEBRE', 76523145),
(58, 'Egis', 62356845),
(59, 'Ali KABORE', 68965476),
(61, 'Ali KABORE', 68965476),
(62, 'Ali KABORE', 68965476);

-- --------------------------------------------------------

--
-- Structure de la table `credit`
--

CREATE TABLE `credit` (
  `idCredit` int(10) NOT NULL,
  `dateCredit` date NOT NULL,
  `montantCredit` double NOT NULL,
  `dateReglement` date DEFAULT NULL,
  `statut` varchar(15) NOT NULL,
  `idAchat` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `credit`
--

INSERT INTO `credit` (`idCredit`, `dateCredit`, `montantCredit`, `dateReglement`, `statut`, `idAchat`) VALUES
(10, '2025-02-22', -2000, '2025-02-27', 'paye', 40),
(12, '2025-02-08', -2000, NULL, 'non paye', 42),
(14, '2025-03-23', -677, '2025-03-15', 'paye', 44);

-- --------------------------------------------------------

--
-- Structure de la table `detailachat`
--

CREATE TABLE `detailachat` (
  `idDetailAchat` int(10) NOT NULL,
  `quantite` double NOT NULL,
  `prixUnitaire` double NOT NULL,
  `coutTotal` double NOT NULL,
  `idProduit` int(10) DEFAULT NULL,
  `idAchat` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `detailachat`
--

INSERT INTO `detailachat` (`idDetailAchat`, `quantite`, `prixUnitaire`, `coutTotal`, `idProduit`, `idAchat`) VALUES
(22, 2, 1000, 2000, 14, 40),
(23, 2, 1000, 2000, 17, 40),
(24, 5, 1000, 5000, 14, 42),
(25, 1, 200, 200, 12, 42),
(26, 16, 10, 160, 14, 41),
(27, 2, 1000, 2000, 12, 44);

-- --------------------------------------------------------

--
-- Structure de la table `detaillivraison`
--

CREATE TABLE `detaillivraison` (
  `idDetailLivraison` int(10) NOT NULL,
  `quantite` double NOT NULL,
  `prixUnitaire` double NOT NULL,
  `coutTotal` double NOT NULL,
  `idLivraison` int(10) DEFAULT NULL,
  `idProduit` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `detaillivraison`
--

INSERT INTO `detaillivraison` (`idDetailLivraison`, `quantite`, `prixUnitaire`, `coutTotal`, `idLivraison`, `idProduit`) VALUES
(8, 17, 1600, 27200, 6, 14),
(9, 5, 1000, 5000, 6, 12);

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `idFournisseur` int(10) NOT NULL,
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(75) NOT NULL,
  `telephone1` int(8) NOT NULL,
  `telephone2` int(8) NOT NULL,
  `adresse` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`idFournisseur`, `nom`, `prenom`, `telephone1`, `telephone2`, `adresse`) VALUES
(15, 'KIEMDE', 'Jeremy', 57432654, 62328583, 'BP 1201 Rue YENNEGA'),
(16, 'KEBRE', 'AZIZ', 76523145, 54785223, 'BP 1201 Rue YENNEGA'),
(17, 'TAO', 'Adoul Aziz', 62245260, 75855896, 'BP 1201 Rue YENNEGA'),
(18, 'ZONGO', 'Alice', 56322874, 77125483, 'BP 1201 Rue YENNEGA'),
(19, 'KINDA', 'Fadel', 67965476, 53621774, 'BP 1201 Rue YENNEGA'),
(22, 'KABORE', 'Ali', 68965476, 74552336, 'BP 1201 Rue YENNEGA');

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

CREATE TABLE `livraison` (
  `idLivraison` int(10) NOT NULL,
  `dateLivraison` date NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `montantLivraison` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `livraison`
--

INSERT INTO `livraison` (`idLivraison`, `dateLivraison`, `libelle`, `montantLivraison`) VALUES
(2, '2025-02-11', 'livraison sucre et riz', 146000),
(4, '2025-02-22', 'sucre', 200000),
(5, '2025-02-16', 'chocolat', 25000),
(6, '2025-03-22', 'livraison sucre et sardine', 100000);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `idProduit` int(9) NOT NULL,
  `nom` varchar(16) NOT NULL,
  `prix` double NOT NULL,
  `quantite` double NOT NULL,
  `datePeremption` date NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`idProduit`, `nom`, `prix`, `quantite`, `datePeremption`, `image`) VALUES
(12, 'sucre', 2000, 8, '2025-04-16', 'C:/Users/USER/Desktop/mes projets github/DID_boutique/did_boutique/web/uploads/sucre.jpeg'),
(13, 'riz', 1000, 75, '2025-07-08', 'C:/Users/USER/Desktop/mes projets github/DID_boutique/did_boutique/web/uploads/riz.jpg'),
(14, 'sardine', 300, 21, '2025-04-25', 'C:/Users/USER/Desktop/mes projets github/DID_boutique/did_boutique/web/uploads/sardine.jpeg'),
(15, 'jus de banane', 850, 27, '2025-04-14', 'C:/Users/USER/Desktop/mes projets github/DID_boutique/did_boutique/web/uploads/jus de banane.jpeg'),
(16, 'lait frais', 1500, 21, '2025-02-28', 'C:/Users/USER/Desktop/mes projets github/DID_boutique/did_boutique/web/uploads/lait1.jpeg'),
(17, 'yaourt', 500, 33, '2025-04-27', 'C:/Users/USER/Desktop/mes projets github/DID_boutique/did_boutique/web/uploads/yaourt1.jpeg'),
(18, 'pate', 1200, 33, '2025-02-23', 'C:/Users/USER/Desktop/mes projets github/DID_boutique/did_boutique/web/uploads/pÃ¢te.jpeg'),
(19, 'jus de mangue', 1100, 13, '2025-04-28', 'C:/Users/USER/Desktop/mes projets github/DID_boutique/did_boutique/web/uploads/jus de mangue.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `idUtilisateur` int(10) NOT NULL,
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(75) NOT NULL,
  `sexe` varchar(8) NOT NULL,
  `dateNaissance` date NOT NULL,
  `matricule` varchar(10) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  `telephone` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `nom`, `prenom`, `sexe`, `dateNaissance`, `matricule`, `password`, `role`, `telephone`) VALUES
(14, 'KIEMDE', 'Jeremy', 'masculin', '2002-07-11', '10201524', '$2a$10$gawpOCCh8HH/NFTfcGxLxeqa1o0JzdTo8Iwn5ESSYynfpyeI4kx/C', 'administrateur', 57432654),
(17, 'SAWADOGO', 'Alice', 'feminin', '2025-01-23', '456456', '$2a$10$OrfS/e32bBAlB2BtpKYEeupjsuUaZhBAapyub2d2O2cbhvxEItsMq', 'magasinier', 74552336),
(18, 'KABORE', 'Ali', 'feminin', '2025-01-26', '123123', '$2a$10$EJXrx.cls9DsiDiQdvEYI.eYK27xhZaz4xNRClIQiVeX4xzV.M0PC', 'caissier', 73854116);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `achat`
--
ALTER TABLE `achat`
  ADD PRIMARY KEY (`idAchat`),
  ADD KEY `idUtilisateur` (`idUtilisateur`),
  ADD KEY `idClient` (`idClient`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`);

--
-- Index pour la table `credit`
--
ALTER TABLE `credit`
  ADD PRIMARY KEY (`idCredit`),
  ADD KEY `idAchat` (`idAchat`);

--
-- Index pour la table `detailachat`
--
ALTER TABLE `detailachat`
  ADD PRIMARY KEY (`idDetailAchat`),
  ADD KEY `idProduit` (`idProduit`),
  ADD KEY `idAchat` (`idAchat`);

--
-- Index pour la table `detaillivraison`
--
ALTER TABLE `detaillivraison`
  ADD PRIMARY KEY (`idDetailLivraison`),
  ADD KEY `idLivraison` (`idLivraison`),
  ADD KEY `idProduit` (`idProduit`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`idFournisseur`);

--
-- Index pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`idLivraison`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`idProduit`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`idUtilisateur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `achat`
--
ALTER TABLE `achat`
  MODIFY `idAchat` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT pour la table `credit`
--
ALTER TABLE `credit`
  MODIFY `idCredit` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `detailachat`
--
ALTER TABLE `detailachat`
  MODIFY `idDetailAchat` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `detaillivraison`
--
ALTER TABLE `detaillivraison`
  MODIFY `idDetailLivraison` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `idFournisseur` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `livraison`
--
ALTER TABLE `livraison`
  MODIFY `idLivraison` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `idProduit` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `idUtilisateur` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `achat`
--
ALTER TABLE `achat`
  ADD CONSTRAINT `achat_ibfk_1` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`),
  ADD CONSTRAINT `achat_ibfk_2` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`);

--
-- Contraintes pour la table `credit`
--
ALTER TABLE `credit`
  ADD CONSTRAINT `credit_ibfk_1` FOREIGN KEY (`idAchat`) REFERENCES `achat` (`idAchat`);

--
-- Contraintes pour la table `detailachat`
--
ALTER TABLE `detailachat`
  ADD CONSTRAINT `detailachat_ibfk_1` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`idProduit`),
  ADD CONSTRAINT `detailachat_ibfk_2` FOREIGN KEY (`idAchat`) REFERENCES `achat` (`idAchat`);

--
-- Contraintes pour la table `detaillivraison`
--
ALTER TABLE `detaillivraison`
  ADD CONSTRAINT `detaillivraison_ibfk_1` FOREIGN KEY (`idLivraison`) REFERENCES `livraison` (`idLivraison`),
  ADD CONSTRAINT `detaillivraison_ibfk_2` FOREIGN KEY (`idProduit`) REFERENCES `produit` (`idProduit`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
