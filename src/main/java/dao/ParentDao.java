package dao;
import java.sql.*;
public class ParentDao {
    public int getIdEnfant(int idParent) throws SQLException {
        String query = "SELECT id_etudiant FROM parent WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idParent);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id_etudiant");
            }
        }
        return -1;
    }
}