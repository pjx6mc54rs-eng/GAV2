package model;

import java.util.List;

public class Administration extends Utilisateur {
    private String nom;
    private String prenom;

    public Administration(int id, String login, String motDePasse, String nom, String prenom) {
        super(id, login, motDePasse, "ADMINISTRATION");
        this.nom = nom;
        this.prenom = prenom;
    }

    public void validerJustificatif(Justification justification) {
        justification.setStatut("VALIDE");
        System.out.println("Justificatif validé: " + justification.getId());
    }

    public void exporterRapport() {
        System.out.println("Rapport exporté par " + nom + " " + prenom);
    }

    public List<Etudiant> gererEtudiants() {
        // Retourne la liste des étudiants gérés
        return new java.util.ArrayList<>();
    }

    // Getters & Setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
}
