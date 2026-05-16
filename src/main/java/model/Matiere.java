package model;

public class Matiere {
    private int id;
    private String libelle;
    private double coefficient;

    public Matiere() {}

    public Matiere(int id, String libelle, double coefficient) {
        this.id = id;
        this.libelle = libelle;
        this.coefficient = coefficient;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public double getCoefficient() { return coefficient; }
    public void setCoefficient(double coefficient) { this.coefficient = coefficient; }
}
