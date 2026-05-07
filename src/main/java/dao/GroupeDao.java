package dao;

import model.Groupe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupeDao {
    public Groupe findById(int id) {
        String sql = "SELECT id, nom, id_filiere FROM Groupe WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapGroupe(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Groupe> findAll() {
        List<Groupe> groupes = new ArrayList<>();
        String sql = "SELECT id, nom, id_filiere FROM Groupe";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                groupes.add(mapGroupe(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupes;
    }

    public void save(Groupe groupe) {
        String sql = "INSERT INTO Groupe (nom, id_filiere) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, groupe.getNom());
            ps.setInt(2, groupe.getId_filiere());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    groupe.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Groupe groupe) {
        String sql = "UPDATE Groupe SET nom = ?, id_filiere = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, groupe.getNom());
            ps.setInt(2, groupe.getId_filiere());
            ps.setInt(3, groupe.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Groupe WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Groupe mapGroupe(ResultSet rs) throws SQLException {
        return new Groupe(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getInt("id_filiere")
        );
    }
}
