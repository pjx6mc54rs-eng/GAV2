package dao;

import model.Seance;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SeanceDao {
    public Seance findById(int id) {
        String sql = "SELECT id, date, heureDebut, heureFin, id_enseignant FROM Seance WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapSeance(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Seance> findAll() {
        List<Seance> list = new ArrayList<>();
        String sql = "SELECT id, date, heureDebut, heureFin, id_enseignant FROM Seance";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapSeance(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(Seance seance) {
        String sql = "INSERT INTO Seance (date, heureDebut, heureFin, id_enseignant) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, Date.valueOf(seance.getDate()));
            ps.setTime(2, Time.valueOf(seance.getHeureDebut()));
            ps.setTime(3, Time.valueOf(seance.getHeureFin()));
            ps.setInt(4, seance.getId_enseignant());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    seance.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Seance seance) {
        String sql = "UPDATE Seance SET date = ?, heureDebut = ?, heureFin = ?, id_enseignant = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(seance.getDate()));
            ps.setTime(2, Time.valueOf(seance.getHeureDebut()));
            ps.setTime(3, Time.valueOf(seance.getHeureFin()));
            ps.setInt(4, seance.getId_enseignant());
            ps.setInt(5, seance.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Seance WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Seance mapSeance(ResultSet rs) throws SQLException {
        return new Seance(
                rs.getInt("id"),
                rs.getDate("date").toLocalDate(),
                rs.getTime("heureDebut").toLocalTime(),
                rs.getTime("heureFin").toLocalTime(),
                rs.getInt("id_enseignant")
        );
    }
}
