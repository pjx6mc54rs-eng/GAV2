package model;
public class EmploiDuTemps {
    private int id; private String semestre; private String anneeUniv; private String jourSemaine; private String periode; private int idGroupe; private int idMatiere; private int idEnseignant;
    public EmploiDuTemps() {}
    public EmploiDuTemps(int id, String semestre, String anneeUniv, String jourSemaine, String periode, int idGroupe, int idMatiere, int idEnseignant) {
        this.id = id; this.semestre = semestre; this.anneeUniv = anneeUniv; this.jourSemaine = jourSemaine; this.periode = periode; this.idGroupe = idGroupe; this.idMatiere = idMatiere; this.idEnseignant = idEnseignant;
    }
    public int getId() { return id; } public void setId(int id) { this.id = id; }
    public String getSemestre() { return semestre; } public void setSemestre(String semestre) { this.semestre = semestre; }
    public String getAnneeUniv() { return anneeUniv; } public void setAnneeUniv(String anneeUniv) { this.anneeUniv = anneeUniv; }
    public String getJourSemaine() { return jourSemaine; } public void setJourSemaine(String jourSemaine) { this.jourSemaine = jourSemaine; }
    public String getPeriode() { return periode; } public void setPeriode(String periode) { this.periode = periode; }
    public int getIdGroupe() { return idGroupe; } public void setIdGroupe(int idGroupe) { this.idGroupe = idGroupe; }
    public int getIdMatiere() { return idMatiere; } public void setIdMatiere(int idMatiere) { this.idMatiere = idMatiere; }
    public int getIdEnseignant() { return idEnseignant; } public void setIdEnseignant(int idEnseignant) { this.idEnseignant = idEnseignant; }
}