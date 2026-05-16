package dao;
import model.*; import util.PasswordUtil; import java.sql.*;
public class UtilisateurDao {
    public Utilisateur login(String login, String motDePasse) throws SQLException {
        String query = "SELECT * FROM utilisateur WHERE login = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedPw = rs.getString("motDePasse");
                    if(PasswordUtil.verifyPassword(motDePasse, hashedPw)) {
                        String role = rs.getString("role");
                        int id = rs.getInt("id");
                        return fetchChildDetails(id, role, login, hashedPw);
                    }
                }
            }
        }
        return null;
    }
    private Utilisateur fetchChildDetails(int id, String role, String login, String motDePasse) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            if ("ENSEIGNANT".equals(role)) {
                try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM enseignant WHERE id=?")){
                    ps.setInt(1, id); ResultSet rs = ps.executeQuery();
                    if(rs.next()) return new Enseignant(id, login, motDePasse, rs.getString("nom"), rs.getString("prenom"));
                }
            } else if ("ETUDIANT".equals(role)) {
                try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM etudiant WHERE id=?")){
                    ps.setInt(1, id); ResultSet rs = ps.executeQuery();
                    if(rs.next()) return new Etudiant(id, login, motDePasse, rs.getString("nom"), rs.getString("prenom"), rs.getString("cne"), rs.getInt("id_groupe"));
                }
            } else if ("PARENT".equals(role)) {
                try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM parent WHERE id=?")){
                    ps.setInt(1, id); ResultSet rs = ps.executeQuery();
                    if(rs.next()) return new Parent(id, login, motDePasse, rs.getString("nom"), rs.getString("prenom"), rs.getInt("id_etudiant"));
                }
            } else if ("ADMINISTRATION".equals(role)) {
                try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM administration WHERE id=?")){
                    ps.setInt(1, id); ResultSet rs = ps.executeQuery();
                    if(rs.next()) return new Administration(id, login, motDePasse, rs.getString("nom"), rs.getString("prenom"));
                }
            }
        }
        return null;
    }

    public void ajouter(Utilisateur u) throws SQLException {
        String query = "INSERT INTO utilisateur (login, motDePasse, role) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getLogin());
            ps.setString(2, PasswordUtil.hashSHA256(u.getMotDePasse()));
            ps.setString(3, u.getRole());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    u.setId(rs.getInt(1));
                    ajouterDetails(u, conn);
                }
            }
        }
    }

    private void ajouterDetails(Utilisateur u, Connection conn) throws SQLException {
        if (u instanceof Etudiant) {
            Etudiant e = (Etudiant) u;
            String q = "INSERT INTO etudiant (id, nom, prenom, cne, id_groupe) VALUES (?, ?, ?, ?, ?)";
            try(PreparedStatement ps = conn.prepareStatement(q)){
                ps.setInt(1, e.getId()); ps.setString(2, e.getNom()); ps.setString(3, e.getPrenom());
                ps.setString(4, e.getCne()); ps.setInt(5, e.getIdGroupe());
                ps.executeUpdate();
            }
        } else if (u instanceof Enseignant) {
            Enseignant e = (Enseignant) u;
            String q = "INSERT INTO enseignant (id, nom, prenom) VALUES (?, ?, ?)";
            try(PreparedStatement ps = conn.prepareStatement(q)){
                ps.setInt(1, e.getId()); ps.setString(2, e.getNom()); ps.setString(3, e.getPrenom());
                ps.executeUpdate();
            }
        } else if (u instanceof Parent) {
            Parent p = (Parent) u;
            String q = "INSERT INTO parent (id, nom, prenom, id_etudiant) VALUES (?, ?, ?, ?)";
            try(PreparedStatement ps = conn.prepareStatement(q)){
                ps.setInt(1, p.getId()); ps.setString(2, p.getNom()); ps.setString(3, p.getPrenom());
                ps.setInt(4, p.getIdEtudiant());
                ps.executeUpdate();
            }
        } else if (u instanceof Administration) {
            Administration a = (Administration) u;
            String q = "INSERT INTO administration (id, nom, prenom) VALUES (?, ?, ?)";
            try(PreparedStatement ps = conn.prepareStatement(q)){
                ps.setInt(1, a.getId()); ps.setString(2, a.getNom()); ps.setString(3, a.getPrenom());
                ps.executeUpdate();
            }
        }
    }
}