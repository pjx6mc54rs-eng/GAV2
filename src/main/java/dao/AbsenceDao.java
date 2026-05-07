package dao;

import model.Absence;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AbsenceDao {
    public Absence findById(int id) {
        String sql = "SELECT id, dateSaisie, justifiee, nbHeures FROM Absence WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapAbsence(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Absence> findAll() {
        List<Absence> absences = new ArrayList<>();
        String sql = "SELECT id, dateSaisie, justifiee, nbHeures FROM Absence";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                absences.add(mapAbsence(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absences;
    }

    public void save(Absence absence) {
        String sql = "INSERT INTO Absence (dateSaisie, justifiee, nbHeures) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setTimestamp(1, Timestamp.valueOf(absence.getDateSaisie()));
            ps.setBoolean(2, absence.isJustifiee());
            ps.setInt(3, absence.getNbHeures());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    absence.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Absence absence) {
        String sql = "UPDATE Absence SET dateSaisie = ?, justifiee = ?, nbHeures = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(absence.getDateSaisie()));
            ps.setBoolean(2, absence.isJustifiee());
            ps.setInt(3, absence.getNbHeures());
            ps.setInt(4, absence.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Absence WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Absence mapAbsence(ResultSet rs) throws SQLException {
        return new Absence(
                rs.getInt("id"),
                rs.getTimestamp("dateSaisie").toLocalDateTime(),
                rs.getBoolean("justifiee"),
                rs.getInt("nbHeures")
        );
    }
}
