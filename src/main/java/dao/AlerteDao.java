package dao;

import model.Alerte;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlerteDao {
    public Alerte findById(int id) {
        String sql = "SELECT id, message, dateEnvoi, lue FROM Alerte WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapAlerte(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Alerte> findAll() {
        List<Alerte> list = new ArrayList<>();
        String sql = "SELECT id, message, dateEnvoi, lue FROM Alerte";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapAlerte(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(Alerte alerte) {
        String sql = "INSERT INTO Alerte (message, dateEnvoi, lue) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, alerte.getMessage());
            ps.setTimestamp(2, Timestamp.valueOf(alerte.getDateEnvoi()));
            ps.setBoolean(3, alerte.isLue());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    alerte.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Alerte alerte) {
        String sql = "UPDATE Alerte SET message = ?, dateEnvoi = ?, lue = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, alerte.getMessage());
            ps.setTimestamp(2, Timestamp.valueOf(alerte.getDateEnvoi()));
            ps.setBoolean(3, alerte.isLue());
            ps.setInt(4, alerte.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Alerte WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Alerte mapAlerte(ResultSet rs) throws SQLException {
        return new Alerte(
                rs.getInt("id"),
                rs.getString("message"),
                rs.getTimestamp("dateEnvoi").toLocalDateTime(),
                rs.getBoolean("lue")
        );
    }
}
