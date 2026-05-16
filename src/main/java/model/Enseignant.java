package model;
public class Enseignant extends Utilisateur {
    private String nom; private String prenom;
    public Enseignant() { this.setRole("ENSEIGNANT"); }
    public Enseignant(int id, String login, String motDePasse, String nom, String prenom) {
        super(id, login, motDePasse, "ENSEIGNANT"); this.nom = nom; this.prenom = prenom;
    }
    public String getNom() { return nom; } public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; } public void setPrenom(String prenom) { this.prenom = prenom; }
}