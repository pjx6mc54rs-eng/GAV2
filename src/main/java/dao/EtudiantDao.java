package dao;
import model.Etudiant; import java.sql.*; import java.util.*;
public class EtudiantDao {
    public List<Etudiant> listerParGroupe(int idGroupe) throws SQLException {
        List<Etudiant> list = new ArrayList<>();
        String query = "SELECT e.*, u.login, u.motDePasse FROM etudiant e JOIN utilisateur u ON e.id = u.id WHERE e.id_groupe = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idGroupe);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Etudiant(rs.getInt("id"), rs.getString("login"), rs.getString("motDePasse"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cne"), rs.getInt("id_groupe")));
                }
            }
        }
        return list;
    }
    public List<Etudiant> listerTout() throws SQLException {
        List<Etudiant> list = new ArrayList<>();
        String query = "SELECT e.*, u.login, u.motDePasse FROM etudiant e JOIN utilisateur u ON e.id = u.id";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Etudiant(rs.getInt("id"), rs.getString("login"), rs.getString("motDePasse"), rs.getString("nom"), rs.getString("prenom"), rs.getString("cne"), rs.getInt("id_groupe")));
            }
        }
        return list;
    }
}