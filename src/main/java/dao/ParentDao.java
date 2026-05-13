package dao;

import model.Parent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParentDao {
    public Parent findById(int id) {
        String sql = "SELECT id, login, motDePasse, role, nom, prenom, id_etudiant FROM parent WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Parent parent = new Parent(
                            rs.getInt("id"),
                            rs.getString("login"),
                            rs.getString("motDePasse")
                    );
                    parent.setNom(rs.getString("nom"));
                    parent.setPrenom(rs.getString("prenom"));
                    parent.setId_etudiant(rs.getInt("id_etudiant"));
                    return parent;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Parent> findAll() {
        List<Parent> list = new ArrayList<>();
        String sql = "SELECT id, login, motDePasse, role, nom, prenom, id_etudiant FROM parent";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Parent parent = new Parent(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("motDePasse")
                );
                parent.setNom(rs.getString("nom"));
                parent.setPrenom(rs.getString("prenom"));
                parent.setId_etudiant(rs.getInt("id_etudiant"));
                list.add(parent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(Parent parent) {
        String sql = "INSERT INTO parent (login, motDePasse, role, nom, prenom, id_etudiant) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, parent.getLogin());
            ps.setString(2, parent.getMotDePasse());
            ps.setString(3, parent.getRole());
            ps.setString(4, parent.getNom());
            ps.setString(5, parent.getPrenom());
            ps.setInt(6, parent.getId_etudiant());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    parent.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Parent parent) {
        String sql = "UPDATE parent SET login = ?, motDePasse = ?, role = ?, nom = ?, prenom = ?, id_etudiant = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, parent.getLogin());
            ps.setString(2, parent.getMotDePasse());
            ps.setString(3, parent.getRole());
            ps.setString(4, parent.getNom());
            ps.setString(5, parent.getPrenom());
            ps.setInt(6, parent.getId_etudiant());
            ps.setInt(7, parent.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM parent WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
