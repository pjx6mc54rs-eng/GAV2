package dao;

import model.Justification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JustificationDao {
    public Justification findById(int id) {
        String sql = "SELECT id, motif, fichierJoint, statut FROM Justification WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Justification(
                            rs.getInt("id"),
                            rs.getString("motif"),
                            rs.getString("fichierJoint"),
                            rs.getString("statut")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Justification> findAll() {
        List<Justification> list = new ArrayList<>();
        String sql = "SELECT id, motif, fichierJoint, statut FROM Justification";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Justification(
                        rs.getInt("id"),
                        rs.getString("motif"),
                        rs.getString("fichierJoint"),
                        rs.getString("statut")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(Justification justification) {
        String sql = "INSERT INTO Justification (motif, fichierJoint, statut) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, justification.getMotif());
            ps.setString(2, justification.getFichierJoint());
            ps.setString(3, justification.getStatut());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    justification.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Justification justification) {
        String sql = "UPDATE Justification SET motif = ?, fichierJoint = ?, statut = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, justification.getMotif());
            ps.setString(2, justification.getFichierJoint());
            ps.setString(3, justification.getStatut());
            ps.setInt(4, justification.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Justification WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
