package dao;
import model.Justification; import java.sql.*; import java.util.*;
public class JustificationDao {
    public void ajouter(Justification j) throws SQLException {
        String query = "INSERT INTO justification (motif, fichierJoint, statut, id_absence) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, j.getMotif()); ps.setString(2, j.getFichierJoint()); ps.setString(3, j.getStatut()); ps.setInt(4, j.getIdAbsence()); ps.executeUpdate();
        }
    }
    public List<Justification> getByEtudiant(int idEtudiant) throws SQLException {
        List<Justification> list = new ArrayList<>();
        String query = "SELECT j.* FROM justification j JOIN absence a ON j.id_absence = a.id WHERE a.id_etudiant = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idEtudiant);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(new Justification(rs.getInt("id"), rs.getString("motif"), rs.getString("fichierJoint"), rs.getString("statut"), rs.getInt("id_absence")));
            }
        }
        return list;
    }
    public Justification getById(int id) throws SQLException {
        String query = "SELECT * FROM justification WHERE id = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) return new Justification(rs.getInt("id"), rs.getString("motif"), rs.getString("fichierJoint"), rs.getString("statut"), rs.getInt("id_absence"));
            }
        }
        return null;
    }
    public void updateStatut(int idJustif, String statut) throws SQLException {
        String query = "UPDATE justification SET statut = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, statut); ps.setInt(2, idJustif); ps.executeUpdate();
        }
    }
}