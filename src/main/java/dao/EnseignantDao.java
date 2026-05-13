package dao;

import model.Enseignant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnseignantDao {
    public Enseignant findById(int id) {
        String sql = "SELECT id, login, motDePasse, nom, prenom, id_matiere FROM Enseignant WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Enseignant(
                            rs.getInt("id"),
                            rs.getString("login"),
                            rs.getString("motDePasse"),
                            rs.getString("nom"),
                            rs.getString("prenom")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Enseignant> findAll() {
        List<Enseignant> list = new ArrayList<>();
        String sql = "SELECT id, login, motDePasse, nom, prenom, id_matiere FROM Enseignant";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Enseignant(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("motDePasse"),
                        rs.getString("nom"),
                        rs.getString("prenom")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(Enseignant enseignant) {
        String sql = "INSERT INTO Enseignant (login, motDePasse, nom, prenom) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, enseignant.getLogin());
            ps.setString(2, enseignant.getMotDePasse());
            ps.setString(3, enseignant.getNom());
            ps.setString(4, enseignant.getPrenom());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    enseignant.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Enseignant enseignant) {
        String sql = "UPDATE Enseignant SET login = ?, motDePasse = ?, nom = ?, prenom = ?, WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, enseignant.getLogin());
            ps.setString(2, enseignant.getMotDePasse());
            ps.setString(3, enseignant.getNom());
            ps.setString(4, enseignant.getPrenom());
            ps.setInt(5, enseignant.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Enseignant WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
