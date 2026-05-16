package model;
import java.time.LocalDateTime;
public class Absence {
    private int id; private LocalDateTime dateSaisie; private boolean justifie; private int nbHeures; private int idEtudiant; private int idSeance;
    public Absence() {}
    public Absence(int id, LocalDateTime dateSaisie, boolean justifie, int nbHeures, int idEtudiant, int idSeance) {
        this.id = id; this.dateSaisie = dateSaisie; this.justifie = justifie; this.nbHeures = nbHeures; this.idEtudiant = idEtudiant; this.idSeance = idSeance;
    }
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public LocalDateTime getDateSaisie() { return dateSaisie; } public void setDateSaisie(LocalDateTime dateSaisie) { this.dateSaisie = dateSaisie; }
    public boolean isJustifie() { return justifie; } public void setJustifie(boolean justifie) { this.justifie = justifie; }
    public int getNbHeures() { return nbHeures; } public void setNbHeures(int nbHeures) { this.nbHeures = nbHeures; }
    public int getIdEtudiant() { return idEtudiant; } public void setIdEtudiant(int idEtudiant) { this.idEtudiant = idEtudiant; }
    public int getIdSeance() { return idSeance; } public void setIdSeance(int idSeance) { this.idSeance = idSeance; }
}