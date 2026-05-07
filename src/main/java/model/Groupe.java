package model;

public class Groupe {
    private int id;
    private String nom;
    private int id_filiere;

    public Groupe(int id, String nom, int id_filiere) {
        this.id = id;
        this.nom = nom;
        this.id_filiere = id_filiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_filiere() {
        return id_filiere;
    }

    public void setId_filiere(int id_filiere) {
        this.id_filiere = id_filiere;
    }
}
