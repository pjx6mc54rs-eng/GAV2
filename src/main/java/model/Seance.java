package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class Seance {
    private int id;
    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private int id_enseignant; // FK

    public Seance(int id, LocalDate date, LocalTime heureDebut, LocalTime heureFin, int id_enseignant) {
        this.id = id;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.id_enseignant = id_enseignant;
    }

    public List<Absence> getAbsences() {
        // Retourne la liste des absences enregistrées pour cette séance
        return new ArrayList<>();
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }

    public LocalTime getHeureFin() { return heureFin; }
    public void setHeureFin(LocalTime heureFin) { this.heureFin = heureFin; }

    public int getId_enseignant() { return id_enseignant; }
    public void setId_enseignant(int id_enseignant) { this.id_enseignant = id_enseignant; }
}
