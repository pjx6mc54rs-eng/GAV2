package service;
import dao.EtudiantDao;
import dao.UtilisateurDao;
import dao.GroupeDao;
import model.Etudiant;
import java.sql.SQLException;

public class EtudiantService {
    private UtilisateurDao utilisateurDao = new UtilisateurDao();
    private GroupeDao groupeDao = new GroupeDao();

    public void ajouterEtudiant(Etudiant e) throws SQLException, Exception {
        int count = groupeDao.countEtudiants(e.getIdGroupe());
        // Assume capacity could be customized per group, fetch group to be sure
        model.Groupe g = groupeDao.listerTout().stream().filter(grp -> grp.getId() == e.getIdGroupe()).findFirst().orElse(null);
        if (g != null && count >= g.getCapaciteMax()) {
            throw new Exception("Le groupe est plein (capacité max : " + g.getCapaciteMax() + ")");
        }
        utilisateurDao.ajouter(e);
    }
}
