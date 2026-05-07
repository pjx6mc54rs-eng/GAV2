package dao;

import model.Administration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministrationDao {
    public Administration findById(int id) {
        String sql = "SELECT id, login, motDePasse, nom, prenom FROM Administration WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Administration(
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

    public List<Administration> findAll() {
        List<Administration> list = new ArrayList<>();
        String sql = "SELECT id, login, motDePasse, nom, prenom FROM Administration";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Administration(
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

    public void save(Administration administration) {
        String sql = "INSERT INTO Administration (login, motDePasse, nom, prenom) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, administration.getLogin());
            ps.setString(2, administration.getMotDePasse());
            ps.setString(3, administration.getNom());
            ps.setString(4, administration.getPrenom());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    administration.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Administration administration) {
        String sql = "UPDATE Administration SET login = ?, motDePasse = ?, nom = ?, prenom = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, administration.getLogin());
            ps.setString(2, administration.getMotDePasse());
            ps.setString(3, administration.getNom());
            ps.setString(4, administration.getPrenom());
            ps.setInt(5, administration.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Administration WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
