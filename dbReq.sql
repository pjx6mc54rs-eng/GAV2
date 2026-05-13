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
                             login VARCHAR(50) UNIQUE,
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

-- ========================
-- Insertion des utilisateurs (d'abord dans utilisateur)
-- ========================

-- Insertion de tous les utilisateurs avec leurs IDs explicites
INSERT INTO utilisateur (id, login, motDePasse, role) VALUES
(1, 'admin', '$2a$12$Sz1mOYZbF4BIrGyFfyPnGOdGnv/QemK.gXqQ7v94l9MZepuYPLfv6', 'ADMINISTRATION'),
(2, 'martin.jean', '$2a$12$Sz1mOYZbF4BIrGyFfyPnGOdGnv/QemK.gXqQ7v94l9MZepuYPLfv6', 'ENSEIGNANT'),
(3, 'dubois.marie', '$2a$12$Sz1mOYZbF4BIrGyFfyPnGOdGnv/QemK.gXqQ7v94l9MZepuYPLfv6', 'ETUDIANT'),
(4, 'dubois.pierre', '$2a$12$Sz1mOYZbF4BIrGyFfyPnGOdGnv/QemK.gXqQ7v94l9MZepuYPLfv6', 'PARENT'),
(5, 'dubois.sophie', '$2a$12$Sz1mOYZbF4BIrGyFfyPnGOdGnv/QemK.gXqQ7v94l9MZepuYPLfv6', 'ENSEIGNANT'),
(6, 'lambert.pierre', '$2a$12$Sz1mOYZbF4BIrGyFfyPnGOdGnv/QemK.gXqQ7v94l9MZepuYPLfv6', 'ENSEIGNANT'),
(7, 'petit.thomas', '$2a$12$Sz1mOYZbF4BIrGyFfyPnGOdGnv/QemK.gXqQ7v94l9MZepuYPLfv6', 'ETUDIANT'),
(8, 'bernard.julie', '$2a$12$Sz1mOYZbF4BIrGyFfyPnGOdGnv/QemK.gXqQ7v94l9MZepuYPLfv6', 'ETUDIANT'),
(9, 'robert.nicolas', '$2a$12$Sz1mOYZbF4BIrGyFfyPnGOdGnv/QemK.gXqQ7v94l9MZepuYPLfv6', 'ETUDIANT'),
(10, 'petit.isabelle', '$2a$12$Sz1mOYZbF4BIrGyFfyPnGOdGnv/QemK.gXqQ7v94l9MZepuYPLfv6', 'PARENT');

-- ========================
-- Insertion des groupes
-- ========================
INSERT INTO groupe (id, nom, capaciteMax) VALUES
(1, 'Génie Logiciel 1', 35),
(2, 'Génie Logiciel 2', 35),
(3, 'Réseaux 1', 30),
(4, 'Réseaux 2', 30);

-- ========================
-- Insertion des détails pour administration
-- ========================
INSERT INTO administration (id, nom, prenom) VALUES
(1, 'ADMIN', 'Système');

-- ========================
-- Insertion des détails pour enseignants (IDs doivent exister dans utilisateur)
-- ========================
INSERT INTO enseignant (id, nom, prenom) VALUES
(2, 'Martin', 'Jean'),
(5, 'Dubois', 'Sophie'),
(6, 'Lambert', 'Pierre');

-- ========================
-- Insertion des détails pour étudiants
-- ========================
INSERT INTO etudiant (id, nom, prenom, cne, id_groupe) VALUES
(3, 'Dubois', 'Marie', 'CNE123456', 1),
(7, 'Petit', 'Thomas', 'CNE123457', 1),
(8, 'Bernard', 'Julie', 'CNE123458', 2),
(9, 'Robert', 'Nicolas', 'CNE123459', 3);

-- ========================
-- Insertion des détails pour parents
-- ========================
INSERT INTO parent (id, nom, prenom, id_etudiant) VALUES
(4, 'Dubois', 'Pierre', 3),
(10, 'Petit', 'Isabelle', 7);

-- ========================
-- Insertion des filières
-- ========================
INSERT INTO filiere (libelle, niveau) VALUES
('Génie Logiciel', 'Bac+5'),
('Réseaux et Télécommunications', 'Bac+5'),
('Data Science', 'Bac+5');

-- ========================
-- Insertion des matières
-- ========================
INSERT INTO matiere (libelle, coefficient) VALUES
('Programmation Java', 4.0),
('Base de données', 3.0),
('Génie logiciel', 4.0),
('Réseaux', 3.5),
('Algorithmique', 3.0);

-- ========================
-- Insertion d'un emploi du temps exemple
-- ========================
INSERT INTO emploi_du_temps (semaine, anneeUniv, id_groupe) VALUES
(1, '2024-2025', 1),
(1, '2024-2025', 2);

-- ========================
-- Insertion de séances exemple
-- ========================
INSERT INTO seance (date, heureDebut, heureFin, id_enseignant, id_matiere, id_emploi) VALUES
('2024-12-02', '08:30:00', '10:30:00', 2, 1, 1),
('2024-12-02', '10:45:00', '12:45:00', 5, 2, 1),
('2024-12-03', '14:00:00', '16:00:00', 6, 4, 2);

-- ========================
-- Insertion d'absences exemple
-- ========================
INSERT INTO absence (dateSaisie, justifie, nbHeures, id_etudiant, id_seance) VALUES
(NOW(), false, 2, 3, 1),
(NOW(), true, 2, 7, 2);

-- ========================
-- Insertion de justifications exemple
-- ========================
INSERT INTO justification (motif, fichierJoint, statut, id_absence) VALUES
('Maladie', 'certificat_medical.pdf', 'APPROUVEE', 2);

-- ========================
-- Insertion d'alertes exemple
-- ========================
INSERT INTO alerte (message, dateEnvoi, lu, id_absence) VALUES
('Absence non justifiée de Marie Dubois en Programmation Java', NOW(), false, 1);