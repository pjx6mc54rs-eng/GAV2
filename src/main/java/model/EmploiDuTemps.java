package model;

public class EmploiDuTemps {
    private int id;
    private String nom;
    private int id_groupe;
    private String description;

    public EmploiDuTemps(int id, String nom, int id_groupe, String description) {
        this.id = id;
        this.nom = nom;
        this.id_groupe = id_groupe;
        this.description = description;
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

    public int getId_groupe() {
        return id_groupe;
    }

    public void setId_groupe(int id_groupe) {
        this.id_groupe = id_groupe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
