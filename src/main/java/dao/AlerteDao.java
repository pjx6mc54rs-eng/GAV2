package dao;
import model.Alerte; import java.sql.*; import java.util.*;
public class AlerteDao {
    public void ajouter(Alerte a) throws SQLException {
        String query = "INSERT INTO alerte (message, dateEnvoi, lu, id_absence) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, a.getMessage()); ps.setTimestamp(2, Timestamp.valueOf(a.getDateEnvoi())); ps.setBoolean(3, a.isLu()); ps.setInt(4, a.getIdAbsence()); ps.executeUpdate();
        }
    }
    public List<Alerte> listerParEtudiant(int idEtudiant) throws SQLException {
        List<Alerte> list = new ArrayList<>();
        String query = "SELECT al.* FROM alerte al JOIN absence a ON al.id_absence = a.id WHERE a.id_etudiant = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idEtudiant);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(new Alerte(rs.getInt("id"), rs.getString("message"), rs.getTimestamp("dateEnvoi").toLocalDateTime(), rs.getBoolean("lu"), rs.getInt("id_absence")));
            }
        }
        return list;
    }
}