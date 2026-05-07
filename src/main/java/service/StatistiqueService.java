package service;

import dao.AbsenceDao;

public class StatistiqueService {
    private final AbsenceDao absenceDao = new AbsenceDao();

    public int countAbsences() {
        return absenceDao.findAll().size();
    }

    public int countJustifiees() {
        return (int) absenceDao.findAll().stream().filter(a -> a.isJustifiee()).count();
    }
}
