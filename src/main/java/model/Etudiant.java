package model;
public class Etudiant extends Utilisateur {
    private String nom; private String prenom; private String cne; private int idGroupe;
    public Etudiant() { this.setRole("ETUDIANT"); }
    public Etudiant(int id, String login, String motDePasse, String nom, String prenom, String cne, int idGroupe) {
        super(id, login, motDePasse, "ETUDIANT"); this.nom = nom; this.prenom = prenom; this.cne = cne; this.idGroupe = idGroupe;
    }
    public String getNom() { return nom; } public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; } public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getCne() { return cne; } public void setCne(String cne) { this.cne = cne; }
    public int getIdGroupe() { return idGroupe; } public void setIdGroupe(int idGroupe) { this.idGroupe = idGroupe; }
}