package service;

import dao.JustificationDao;
import model.Justification;

import java.util.List;

public class JustifService {
    private final JustificationDao justificationDao = new JustificationDao();

    public Justification getJustificationById(int id) {
        return justificationDao.findById(id);
    }

    public List<Justification> getAllJustifications() {
        return justificationDao.findAll();
    }

    public void saveJustification(Justification justification) {
        justificationDao.save(justification);
    }

    public void updateJustification(Justification justification) {
        justificationDao.update(justification);
    }

    public void deleteJustification(int id) {
        justificationDao.delete(id);
    }
}
