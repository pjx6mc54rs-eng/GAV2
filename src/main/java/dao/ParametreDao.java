package dao;
import java.sql.*;

public class ParametreDao {
    public int getSeuil() throws SQLException {
        String query = "SELECT valeur FROM Parametres WHERE cle = 'seuil_alerte'";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()){
            if(rs.next()) return Integer.parseInt(rs.getString("valeur"));
        }
        return 3; // Default threshold
    }
    
    public void updateSeuil(int seuil) throws SQLException {
        String query = "UPDATE Parametres SET valeur = ? WHERE cle = 'seuil_alerte'";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, String.valueOf(seuil));
            if (ps.executeUpdate() == 0) {
                // Insert if not exists
                try(PreparedStatement psInsert = conn.prepareStatement("INSERT INTO Parametres (cle, valeur) VALUES ('seuil_alerte', ?)")){
                    psInsert.setString(1, String.valueOf(seuil));
                    psInsert.executeUpdate();
                }
            }
        }
    }
}
