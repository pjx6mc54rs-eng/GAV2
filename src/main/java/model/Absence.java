package model;

import java.time.LocalDateTime;

public class Absence {
    private int id;
    private LocalDateTime dateSaisie;
    private boolean justifiee;
    private int nbHeures;

    public Absence(int id, LocalDateTime dateSaisie, boolean justifiee, int nbHeures) {
        this.id = id;
        this.dateSaisie = dateSaisie;
        this.justifiee = justifiee;
        this.nbHeures = nbHeures;
    }

    public void verifierSeuil() {
        // Logique pour vérifier si le seuil d'absences est dépassé
        if (nbHeures > 10) {
            System.out.println("Seuil dépassé: " + nbHeures + " heures d'absence.");
        }
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getDateSaisie() { return dateSaisie; }
    public void setDateSaisie(LocalDateTime dateSaisie) { this.dateSaisie = dateSaisie; }

    public boolean isJustifiee() { return justifiee; }
    public void setJustifiee(boolean justifiee) { this.justifiee = justifiee; }

    public int getNbHeures() { return nbHeures; }
    public void setNbHeures(int nbHeures) { this.nbHeures = nbHeures; }
}
