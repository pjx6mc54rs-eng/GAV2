package dao;
import model.Filiere;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FiliereDao {
    public List<Filiere> listerTout() throws SQLException {
        List<Filiere> list = new ArrayList<>();
        String query = "SELECT * FROM Filiere";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Filiere(rs.getInt("id"), rs.getString("libelle"), rs.getString("niveau")));
            }
        }
        return list;
    }
    public void ajouter(Filiere f) throws SQLException {
        String query = "INSERT INTO Filiere (libelle, niveau) VALUES (?, ?)";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, f.getLibelle()); ps.setString(2, f.getNiveau()); ps.executeUpdate();
        }
    }
    public void modifier(Filiere f) throws SQLException {
        String query = "UPDATE Filiere SET libelle=?, niveau=? WHERE id=?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, f.getLibelle()); ps.setString(2, f.getNiveau()); ps.setInt(3, f.getId()); ps.executeUpdate();
        }
    }
    public void supprimer(int id) throws SQLException {
        String query = "DELETE FROM Filiere WHERE id=?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, id); ps.executeUpdate();
        }
    }
}
