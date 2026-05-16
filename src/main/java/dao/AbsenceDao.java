package dao;
import model.Absence; import java.sql.*; import java.util.*;
public class AbsenceDao {
    public void ajouter(Absence a) throws SQLException {
        String query = "INSERT INTO absence (dateSaisie, justifie, nbHeures, id_etudiant, id_seance) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, Timestamp.valueOf(a.getDateSaisie())); ps.setBoolean(2, a.isJustifie()); ps.setInt(3, a.getNbHeures()); ps.setInt(4, a.getIdEtudiant()); ps.setInt(5, a.getIdSeance());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) a.setId(rs.getInt(1));
            }
        }
    }
    public List<Absence> listerParEtudiant(int idEtudiant) throws SQLException {
        List<Absence> list = new ArrayList<>();
        String query = "SELECT * FROM absence WHERE id_etudiant = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idEtudiant);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(new Absence(rs.getInt("id"), rs.getTimestamp("dateSaisie").toLocalDateTime(), rs.getBoolean("justifie"), rs.getInt("nbHeures"), rs.getInt("id_etudiant"), rs.getInt("id_seance")));
            }
        }
        return list;
    }
    public int totalHeures(int idEtudiant) throws SQLException { return getIntResult("SELECT SUM(nbHeures) FROM absence WHERE id_etudiant = ?", idEtudiant); }
    public int compterNonJustifiees(int idEtudiant) throws SQLException { return getIntResult("SELECT COUNT(*) FROM absence WHERE id_etudiant = ? AND justifie = 0", idEtudiant); }
    public int compterParEtudiant(int idEtudiant) throws SQLException { return getIntResult("SELECT COUNT(*) FROM absence WHERE id_etudiant = ?", idEtudiant); }
    private int getIntResult(String query, int param) throws SQLException {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, param);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return rs.getInt(1); }
        }
        return 0;
    }
    public void modifierJustifiee(int idAbsence, boolean justifie) throws SQLException {
        String query = "UPDATE absence SET justifie = ? WHERE id = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setBoolean(1, justifie); ps.setInt(2, idAbsence); ps.executeUpdate();
        }
    }
}