package service;
import dao.AlerteDao;
import model.Alerte;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class AlerteService {
    private AlerteDao alerteDao = new AlerteDao();

    public void envoyerAlerteAbsence(int idAbsence, int totalNonJustifiees) {
        Alerte a = new Alerte(0, "Attention, l'étudiant a atteint " + totalNonJustifiees + " absences non justifiées.", LocalDateTime.now(), false, idAbsence);
        try {
            alerteDao.ajouter(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
