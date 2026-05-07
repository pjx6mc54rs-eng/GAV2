package model;

import java.time.LocalDateTime; // espace manquant corrigé

// ===================== JUSTIFICATION =====================
public class Justification {
    private int id;
    private String motif;
    private String fichierJoint;
    private String statut;

    public Justification(int id, String motif, String fichierJoint, String statut) {
        this.id = id;
        this.motif = motif;
        this.fichierJoint = fichierJoint;
        this.statut = statut;
    }

    public void soumettre() {
        this.statut = "EN_ATTENTE";
        System.out.println("Justification soumise avec le motif: " + motif);
    }

    public void valider() {
        this.statut = "VALIDE";
        System.out.println("Justification validée.");
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getFichierJoint() {
        return fichierJoint;
    }

    public void setFichierJoint(String fichierJoint) {
        this.fichierJoint = fichierJoint;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}