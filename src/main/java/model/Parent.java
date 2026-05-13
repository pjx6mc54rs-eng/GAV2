package model;

import java.util.List;
import java.util.ArrayList;

public class Parent extends Utilisateur {
    private String nom;
    private String prenom;
    private int id_etudiant; // FK


    public Parent(int id, String login, String motDePasse) {
        super(id, login, motDePasse, "PARENT");
    }


    public List<Absence> consulterAbsences() {
        // Retourne la liste des absences de l'étudiant lié
        return new ArrayList<>();
    }

    // Getters & Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public int getId_etudiant() { return id_etudiant; }
    public void setId_etudiant(int id_etudiant) { this.id_etudiant = id_etudiant; }
}
