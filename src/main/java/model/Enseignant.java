package model;

import java.util.List;
import java.util.ArrayList;

public class Enseignant extends Utilisateur {
    private String nom;
    private String prenom;
    private int id_matiere; // FK

    public Enseignant(int id, String login, String motDePasse, String nom, String prenom, int id_matiere) {
        super(id, login, motDePasse, "ENSEIGNANT");
        this.nom = nom;
        this.prenom = prenom;
        this.id_matiere = id_matiere;
    }

    public void faireLAppel(Seance seance) {
        // Logique pour faire l'appel d'une séance
        System.out.println("Appel effectué pour la séance du " + seance.getDate());
    }

    public void validerSaisie(Absence absence) {
        // Logique pour valider la saisie d'une absence
        System.out.println("Saisie validée pour l'absence ID: " + absence.getId());
    }

    // Getters & Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public int getId_matiere() { return id_matiere; }
    public void setId_matiere(int id_matiere) { this.id_matiere = id_matiere; }
}
