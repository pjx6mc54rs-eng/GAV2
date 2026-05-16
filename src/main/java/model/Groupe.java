package model;
public class Groupe {
    private int id; private String nom; private int capaciteMax;
    public Groupe() {}
    public Groupe(int id, String nom, int capaciteMax) { this.id = id; this.nom = nom; this.capaciteMax = capaciteMax; }
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public String getNom() { return nom; } public void setNom(String nom) { this.nom = nom; }
    public int getCapaciteMax() { return capaciteMax; } public void setCapaciteMax(int capaciteMax) { this.capaciteMax = capaciteMax; }
}