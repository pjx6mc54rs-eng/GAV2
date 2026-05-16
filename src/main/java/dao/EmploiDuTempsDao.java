package dao;

import model.EmploiDuTemps;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmploiDuTempsDao {
    public List<EmploiDuTemps> getByGroupe(int idGroupe) throws SQLException {
        List<EmploiDuTemps> list = new ArrayList<>();
        String query = "SELECT * FROM emploi_du_temps WHERE id_groupe = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idGroupe);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new EmploiDuTemps(
                        rs.getInt("id"),
                        rs.getString("semestre"),
                        rs.getString("anneeUniv"),
                        rs.getString("jourSemaine"),
                        rs.getString("periode"),
                        rs.getInt("id_groupe"),
                        rs.getInt("id_matiere"),
                        rs.getInt("id_enseignant")
                    ));
                }
            }
        }
        return list;
    }
}
