package model;

import java.util.List;
import java.util.ArrayList;

public class Etudiant extends Utilisateur {
    private String nom;
    private String prenom;
    private String cne;
    private int id_groupe; // FK

    public Etudiant(int id, String login, String motDePasse, String nom, String prenom, String cne, int id_groupe) {
        super(id, login, motDePasse, "ETUDIANT");
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.id_groupe = id_groupe;
    }

    public List<Absence> consulterAbsences() {
        // Retourne la liste des absences de cet étudiant
        return new ArrayList<>();
    }

    // Getters & Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getCne() { return cne; }
    public void setCne(String cne) { this.cne = cne; }

    public int getId_groupe() { return id_groupe; }
    public void setId_groupe(int id_groupe) { this.id_groupe = id_groupe; }
}
