package service;

import dao.AbsenceDao;
import model.Absence;

import java.util.List;

public class AbsenceService {
    private final AbsenceDao absenceDao = new AbsenceDao();

    public Absence getAbsenceById(int id) {
        return absenceDao.findById(id);
    }

    public List<Absence> getAllAbsences() {
        return absenceDao.findAll();
    }

    public void saveAbsence(Absence absence) {
        absenceDao.save(absence);
    }

    public void updateAbsence(Absence absence) {
        absenceDao.update(absence);
    }

    public void deleteAbsence(int id) {
        absenceDao.delete(id);
    }
}
