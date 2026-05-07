package service;

import dao.EtudiantDao;
import model.Etudiant;

import java.util.List;

public class EtudiantService {
    private final EtudiantDao etudiantDao = new EtudiantDao();

    public Etudiant getEtudiantById(int id) {
        return etudiantDao.findById(id);
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantDao.findAll();
    }

    public void saveEtudiant(Etudiant etudiant) {
        etudiantDao.save(etudiant);
    }

    public void updateEtudiant(Etudiant etudiant) {
        etudiantDao.update(etudiant);
    }

    public void deleteEtudiant(int id) {
        etudiantDao.delete(id);
    }
}
