package model;

import java.time.LocalDateTime;

public class Alerte {
    private int id;
    private String message;
    private LocalDateTime dateEnvoi;
    private boolean lue;

    public Alerte(int id, String message, LocalDateTime dateEnvoi, boolean lue) {
        this.id = id;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
        this.lue = lue;
    }

    public void envoyer() {
        this.dateEnvoi = LocalDateTime.now();
        System.out.println("Alerte envoyée: " + message);
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getDateEnvoi() { return dateEnvoi; }
    public void setDateEnvoi(LocalDateTime dateEnvoi) { this.dateEnvoi = dateEnvoi; }

    public boolean isLue() { return lue; }
    public void setLue(boolean lue) { this.lue = lue; }
}
