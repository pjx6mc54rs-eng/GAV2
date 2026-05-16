package model;
public class Justification {
    private int id; private String motif; private String fichierJoint; private String statut; private int idAbsence;
    public Justification() {}
    public Justification(int id, String motif, String fichierJoint, String statut, int idAbsence) {
        this.id = id; this.motif = motif; this.fichierJoint = fichierJoint; this.statut = statut; this.idAbsence = idAbsence;
    }
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public String getMotif() { return motif; } public void setMotif(String motif) { this.motif = motif; }
    public String getFichierJoint() { return fichierJoint; } public void setFichierJoint(String fichierJoint) { this.fichierJoint = fichierJoint; }
    public String getStatut() { return statut; } public void setStatut(String statut) { this.statut = statut; }
    public int getIdAbsence() { return idAbsence; } public void setIdAbsence(int idAbsence) { this.idAbsence = idAbsence; }
}