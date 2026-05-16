package dao;
import model.Groupe; import java.sql.*; import java.util.*;
public class GroupeDao {
    public List<Groupe> listerTout() throws SQLException {
        List<Groupe> list = new ArrayList<>();
        String query = "SELECT * FROM groupe";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(new Groupe(rs.getInt("id"), rs.getString("nom"), rs.getInt("capaciteMax")));
        }
        return list;
    }
    public int countEtudiants(int idGroupe) throws SQLException {
        String query = "SELECT COUNT(*) FROM etudiant WHERE id_groupe = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idGroupe);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }
}