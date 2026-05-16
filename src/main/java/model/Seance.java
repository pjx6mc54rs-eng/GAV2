package model;
import java.time.LocalDate; import java.time.LocalTime;
public class Seance {
    private int id; private LocalDate date; private LocalTime heureDebut; private LocalTime heureFin; private int idEmploi;
    public Seance() {}
    public Seance(int id, LocalDate date, LocalTime heureDebut, LocalTime heureFin, int idEmploi) {
        this.id = id; this.date = date; this.heureDebut = heureDebut; this.heureFin = heureFin; this.idEmploi = idEmploi;
    }
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public LocalDate getDate() { return date; } public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getHeureDebut() { return heureDebut; } public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }
    public LocalTime getHeureFin() { return heureFin; } public void setHeureFin(LocalTime heureFin) { this.heureFin = heureFin; }
    public int getIdEmploi() { return idEmploi; } public void setIdEmploi(int idEmploi) { this.idEmploi = idEmploi; }
}