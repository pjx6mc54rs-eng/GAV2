package dao;

import model.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDao {
    public Etudiant findById(int id) {
        String sql = "SELECT id_etudiant, login, motDePasse, nom, prenom, cne, id_groupe " +
                "FROM Etudiant WHERE id_etudiant = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapEtudiant(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT id_etudiant, login, motDePasse, nom, prenom, cne, id_groupe FROM Etudiant";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                etudiants.add(mapEtudiant(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    public void save(Etudiant etudiant) {
        String sql = "INSERT INTO Etudiant (login, motDePasse, nom, prenom, cne, id_groupe) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, etudiant.getLogin());
            ps.setString(2, etudiant.getMotDePasse());
            ps.setString(3, etudiant.getNom());
            ps.setString(4, etudiant.getPrenom());
            ps.setString(5, etudiant.getCne());
            ps.setInt(6, etudiant.getId_groupe());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    etudiant.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Etudiant etudiant) {
        String sql = "UPDATE Etudiant SET login = ?, motDePasse = ?, nom = ?, prenom = ?, cne = ?, id_groupe = ? WHERE id_etudiant = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, etudiant.getLogin());
            ps.setString(2, etudiant.getMotDePasse());
            ps.setString(3, etudiant.getNom());
            ps.setString(4, etudiant.getPrenom());
            ps.setString(5, etudiant.getCne());
            ps.setInt(6, etudiant.getId_groupe());
            ps.setInt(7, etudiant.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Etudiant WHERE id_etudiant = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Etudiant mapEtudiant(ResultSet rs) throws SQLException {
        return new Etudiant(
                rs.getInt("id_etudiant"),
                rs.getString("login"),
                rs.getString("motDePasse"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("cne"),
                rs.getInt("id_groupe")
        );
    }
}
