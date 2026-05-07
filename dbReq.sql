-- Suppression des tables si elles existent (ordre inverse des dépendances)
DROP TABLE IF EXISTS alerte;
DROP TABLE IF EXISTS justification;
DROP TABLE IF EXISTS absence;
DROP TABLE IF EXISTS seance;
DROP TABLE IF EXISTS emploi_du_temps;
DROP TABLE IF EXISTS parent;
DROP TABLE IF EXISTS etudiant;
DROP TABLE IF EXISTS enseignant;
DROP TABLE IF EXISTS administration;
DROP TABLE IF EXISTS matiere;
DROP TABLE IF EXISTS filiere;
DROP TABLE IF EXISTS groupe;
DROP TABLE IF EXISTS utilisateur;

-- ========================
-- Table utilisateur (abstraite)
-- ========================
CREATE TABLE utilisateur (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             login VARCHAR(50),
                             motDePasse VARCHAR(255),
                             role VARCHAR(50)
);

-- ========================
-- Héritage
-- ========================
CREATE TABLE enseignant (
                            id INT PRIMARY KEY,
                            nom VARCHAR(50),
                            prenom VARCHAR(50),
                            FOREIGN KEY (id) REFERENCES utilisateur(id)
);

CREATE TABLE administration (
                                id INT PRIMARY KEY,
                                nom VARCHAR(50),
                                prenom VARCHAR(50),
                                FOREIGN KEY (id) REFERENCES utilisateur(id)
);

CREATE TABLE groupe (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nom VARCHAR(50),
                        capaciteMax INT
);

CREATE TABLE etudiant (
                          id INT PRIMARY KEY,
                          nom VARCHAR(50),
                          prenom VARCHAR(50),
                          cne VARCHAR(50),
                          id_groupe INT,
                          FOREIGN KEY (id) REFERENCES utilisateur(id),
                          FOREIGN KEY (id_groupe) REFERENCES groupe(id)
);

CREATE TABLE parent (
                        id INT PRIMARY KEY,
                        nom VARCHAR(50),
                        prenom VARCHAR(50),
                        id_etudiant INT,
                        FOREIGN KEY (id) REFERENCES utilisateur(id),
                        FOREIGN KEY (id_etudiant) REFERENCES etudiant(id)
);

-- ========================
-- Filiere & Matiere
-- ========================
CREATE TABLE filiere (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         libelle VARCHAR(100),
                         niveau VARCHAR(50)
);

CREATE TABLE matiere (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         libelle VARCHAR(100),
                         coefficient FLOAT
);

-- ========================
-- Emploi du temps
-- ========================
CREATE TABLE emploi_du_temps (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 semaine INT,
                                 anneeUniv VARCHAR(20),
                                 id_groupe INT,
                                 FOREIGN KEY (id_groupe) REFERENCES groupe(id)
);

-- ========================
-- Séance
-- ========================
CREATE TABLE seance (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        date DATE,
                        heureDebut TIME,
                        heureFin TIME,
                        id_enseignant INT,
                        id_matiere INT,
                        id_emploi INT,
                        FOREIGN KEY (id_enseignant) REFERENCES enseignant(id),
                        FOREIGN KEY (id_matiere) REFERENCES matiere(id),
                        FOREIGN KEY (id_emploi) REFERENCES emploi_du_temps(id)
);

-- ========================
-- Absence
-- ========================
CREATE TABLE absence (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         dateSaisie DATETIME,
                         justifie BOOLEAN,
                         nbHeures INT,
                         id_etudiant INT,
                         id_seance INT,
                         FOREIGN KEY (id_etudiant) REFERENCES etudiant(id),
                         FOREIGN KEY (id_seance) REFERENCES seance(id)
);

-- ========================
-- Justification (1:1)
-- ========================
CREATE TABLE justification (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               motif VARCHAR(255),
                               fichierJoint VARCHAR(255),
                               statut VARCHAR(50),
                               id_absence INT UNIQUE,
                               FOREIGN KEY (id_absence) REFERENCES absence(id)
);

-- ========================
-- Alerte
-- ========================
CREATE TABLE alerte (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        message VARCHAR(255),
                        dateEnvoi DATETIME,
                        lu BOOLEAN,
                        id_absence INT,
                        FOREIGN KEY (id_absence) REFERENCES absence(id)
);
