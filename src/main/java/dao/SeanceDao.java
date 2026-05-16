package dao;
import model.Seance; import java.sql.*; import java.util.*;
public class SeanceDao {
    public List<Seance> getByGroupe(int idGroupe) throws SQLException {
        List<Seance> list = new ArrayList<>();
        String query = "SELECT s.* FROM seance s JOIN emploi_du_temps e ON s.id_emploi = e.id WHERE e.id_groupe = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idGroupe);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Seance(rs.getInt("id"), rs.getDate("date").toLocalDate(), rs.getTime("heureDebut").toLocalTime(), rs.getTime("heureFin").toLocalTime(), rs.getInt("id_emploi")));
                }
            }
        }
        return list;
    }
}