package service;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StatistiqueService {
    public Map<String, Integer> getAbsencesParMois() {
        Map<String, Integer> map = new HashMap<>();
        String query = "SELECT MONTH(dateSaisie) as mois, COUNT(*) as total FROM Absence GROUP BY MONTH(dateSaisie)";
        try(Connection conn = dao.DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                map.put("Mois " + rs.getInt("mois"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
    
    public Map<String, Integer> getTauxAbsenceParGroupe() {
        Map<String, Integer> map = new HashMap<>();
        String query = "SELECT g.nom, COUNT(a.id) as total FROM Groupe g JOIN Utilisateur u ON g.id = u.idGroupe JOIN Absence a ON u.id = a.idEtudiant GROUP BY g.nom";
        try(Connection conn = dao.DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                map.put(rs.getString("nom"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
