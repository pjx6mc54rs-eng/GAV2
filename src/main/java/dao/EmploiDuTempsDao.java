package dao;

import model.EmploiDuTemps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmploiDuTempsDao {
    public EmploiDuTemps findById(int id) {
        String sql = "SELECT id, nom, id_groupe, description FROM EmploiDuTemps WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapEmploiDuTemps(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<EmploiDuTemps> findAll() {
        List<EmploiDuTemps> emplois = new ArrayList<>();
        String sql = "SELECT id, nom, id_groupe, description FROM EmploiDuTemps";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                emplois.add(mapEmploiDuTemps(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emplois;
    }

    public void save(EmploiDuTemps emploi) {
        String sql = "INSERT INTO EmploiDuTemps (nom, id_groupe, description) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, emploi.getNom());
            ps.setInt(2, emploi.getId_groupe());
            ps.setString(3, emploi.getDescription());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    emploi.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(EmploiDuTemps emploi) {
        String sql = "UPDATE EmploiDuTemps SET nom = ?, id_groupe = ?, description = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emploi.getNom());
            ps.setInt(2, emploi.getId_groupe());
            ps.setString(3, emploi.getDescription());
            ps.setInt(4, emploi.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM EmploiDuTemps WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private EmploiDuTemps mapEmploiDuTemps(ResultSet rs) throws SQLException {
        return new EmploiDuTemps(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getInt("id_groupe"),
                rs.getString("description")
        );
    }
}
