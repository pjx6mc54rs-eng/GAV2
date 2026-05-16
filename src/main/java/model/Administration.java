package model;
public class Administration extends Utilisateur {
    private String nom; private String prenom;
    public Administration() { this.setRole("ADMINISTRATION"); }
    public Administration(int id, String login, String motDePasse, String nom, String prenom) {
        super(id, login, motDePasse, "ADMINISTRATION"); this.nom = nom; this.prenom = prenom;
    }
    public String getNom() { return nom; } public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; } public void setPrenom(String prenom) { this.prenom = prenom; }
}