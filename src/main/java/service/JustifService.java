package service;
import dao.JustificationDao;
import dao.AbsenceDao;
import model.Justification;
import java.sql.SQLException;

public class JustifService {
    private JustificationDao justificationDao = new JustificationDao();
    private AbsenceDao absenceDao = new AbsenceDao();

    public void soumettre(int idAbsence, String motif, String fichier) throws SQLException {
        Justification j = new Justification(0, motif, fichier, "EN_ATTENTE", idAbsence);
        justificationDao.ajouter(j);
    }

    public void valider(int idJustif) throws SQLException {
        justificationDao.updateStatut(idJustif, "ACCEPTEE");
        Justification j = justificationDao.getById(idJustif);
        if (j != null) {
            absenceDao.modifierJustifiee(j.getIdAbsence(), true);
        }
    }

    public void refuser(int idJustif) throws SQLException {
        justificationDao.updateStatut(idJustif, "REFUSEE");
    }
}
