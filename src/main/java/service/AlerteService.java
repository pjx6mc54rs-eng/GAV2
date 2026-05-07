package service;

import dao.AlerteDao;
import model.Alerte;

import java.util.List;

public class AlerteService {
    private final AlerteDao alerteDao = new AlerteDao();

    public Alerte getAlerteById(int id) {
        return alerteDao.findById(id);
    }

    public List<Alerte> getAllAlertes() {
        return alerteDao.findAll();
    }

    public void saveAlerte(Alerte alerte) {
        alerteDao.save(alerte);
    }

    public void updateAlerte(Alerte alerte) {
        alerteDao.update(alerte);
    }

    public void deleteAlerte(int id) {
        alerteDao.delete(id);
    }
}
