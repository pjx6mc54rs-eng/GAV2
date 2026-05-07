package dao;

import model.Filiere;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FiliereDao {
    public Filiere findById(int id) {
        String sql = "SELECT id, nom, description FROM Filiere WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapFiliere(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Filiere> findAll() {
        List<Filiere> filieres = new ArrayList<>();
        String sql = "SELECT id, nom, description FROM Filiere";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                filieres.add(mapFiliere(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filieres;
    }

    public void save(Filiere filiere) {
        String sql = "INSERT INTO Filiere (nom, description) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, filiere.getNom());
            ps.setString(2, filiere.getDescription());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    filiere.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Filiere filiere) {
        String sql = "UPDATE Filiere SET nom = ?, description = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, filiere.getNom());
            ps.setString(2, filiere.getDescription());
            ps.setInt(3, filiere.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Filiere WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Filiere mapFiliere(ResultSet rs) throws SQLException {
        return new Filiere(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("description")
        );
    }
}
