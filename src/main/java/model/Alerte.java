package model;
import java.time.LocalDateTime;
public class Alerte {
    private int id; private String message; private LocalDateTime dateEnvoi; private boolean lu; private int idAbsence;
    public Alerte() {}
    public Alerte(int id, String message, LocalDateTime dateEnvoi, boolean lu, int idAbsence) {
        this.id = id; this.message = message; this.dateEnvoi = dateEnvoi; this.lu = lu; this.idAbsence = idAbsence;
    }
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public String getMessage() { return message; } public void setMessage(String message) { this.message = message; }
    public LocalDateTime getDateEnvoi() { return dateEnvoi; } public void setDateEnvoi(LocalDateTime dateEnvoi) { this.dateEnvoi = dateEnvoi; }
    public boolean isLu() { return lu; } public void setLu(boolean lu) { this.lu = lu; }
    public int getIdAbsence() { return idAbsence; } public void setIdAbsence(int idAbsence) { this.idAbsence = idAbsence; }
}