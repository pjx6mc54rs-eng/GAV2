package service;
import dao.AbsenceDao;
import dao.ParametreDao;
import model.Absence;
import java.sql.SQLException;

public class AbsenceService {
    private AbsenceDao absenceDao = new AbsenceDao();
    private ParametreDao parametreDao = new ParametreDao();
    private AlerteService alerteService = new AlerteService();

    public void enregistrerAbsence(Absence a) throws SQLException {
        absenceDao.ajouter(a);
        int totalNonJustifiees = absenceDao.compterNonJustifiees(a.getIdEtudiant());
        //int seuil = parametreDao.getSeuil();
        
//        if (totalNonJustifiees >= seuil) {
//            // We now use the newly created Absence ID to send the alert.
//            alerteService.envoyerAlerteAbsence(a.getId(), totalNonJustifiees);
//        }
    }
}
