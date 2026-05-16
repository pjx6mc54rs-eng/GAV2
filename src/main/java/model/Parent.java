package model;
public class Parent extends Utilisateur {
    private String nom; private String prenom; private int idEtudiant;
    public Parent() { this.setRole("PARENT"); }
    public Parent(int id, String login, String motDePasse, String nom, String prenom, int idEtudiant) {
        super(id, login, motDePasse, "PARENT"); this.nom = nom; this.prenom = prenom; this.idEtudiant = idEtudiant;
    }
    public String getNom() { return nom; } public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; } public void setPrenom(String prenom) { this.prenom = prenom; }
    public int getIdEtudiant() { return idEtudiant; } public void setIdEtudiant(int idEtudiant) { this.idEtudiant = idEtudiant; }
}