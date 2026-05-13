package dao;

import model.Administration;
import model.Enseignant;
import model.Etudiant;
import model.Parent;
import model.Utilisateur;
import util.PasswordUtil;

import java.sql.*;
import java.util.Optional;

public class LoginDao {

    /**
     * Authentifie un utilisateur et retourne l'objet Utilisateur correspondant
     * @param login Le login de l'utilisateur
     * @param motDePasse Le mot de passe en clair
     * @return Optional contenant l'utilisateur si authentification réussie
     */
    public Optional<Utilisateur> authenticate(String login, String motDePasse) {
        String sql = "SELECT id, login, motDePasse, role FROM utilisateur WHERE login = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("motDePasse");

                    // Vérifier le mot de passe (en production, utilisez un hash comme BCrypt)
                    if (PasswordUtil.verifyPassword(motDePasse, hashedPassword)) {
                        int id = rs.getInt("id");
                        String role = rs.getString("role");

                        Utilisateur utilisateur = chargerDetailsUtilisateur(id, role, login, hashedPassword);
                        return Optional.of(utilisateur);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * Charge les détails spécifiques d'un utilisateur selon son rôle
     */
    private Utilisateur chargerDetailsUtilisateur(int id, String role, String login, String motDePasse) {
        switch (role.toUpperCase()) {
            case "ENSEIGNANT":
                return chargerEnseignant(id, login, motDePasse);
            case "ETUDIANT":
                return chargerEtudiant(id, login, motDePasse);
            case "PARENT":
                return chargerParent(id, login, motDePasse);
            case "ADMINISTRATION":
                return chargerAdministration(id, login, motDePasse);
            default:
                return new Utilisateur(id, login, motDePasse, role) {};
        }
    }

    private Enseignant chargerEnseignant(int id, String login, String motDePasse) {
        String sql = "SELECT nom, prenom FROM enseignant WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Enseignant(
                            id,
                            login,
                            motDePasse,
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

    private Etudiant chargerEtudiant(int id, String login, String motDePasse) {
        String sql = "SELECT nom, prenom, cne, id_groupe FROM etudiant WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Etudiant(
                            id,
                            login,
                            motDePasse,
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("cne"),
                            rs.getInt("id_groupe")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Parent chargerParent(int id, String login, String motDePasse) {
        String sql = "SELECT nom, prenom, id_etudiant FROM parent WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Parent(
                            id,
                            login,
                            motDePasse
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Administration chargerAdministration(int id, String login, String motDePasse) {
        String sql = "SELECT nom, prenom FROM administration WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Administration(
                            id,
                            login,
                            motDePasse,
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

    /**
     * Vérifie si un login existe déjà
     */
    public boolean loginExists(String login) {
        String sql = "SELECT COUNT(*) FROM utilisateur WHERE login = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Change le mot de passe d'un utilisateur
     */
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        String sql = "SELECT motDePasse FROM utilisateur WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && PasswordUtil.verifyPassword(oldPassword, rs.getString("motDePasse"))) {
                    // Mettre à jour le mot de passe
                    String updateSql = "UPDATE utilisateur SET motDePasse = ? WHERE id = ?";
                    try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
                        updatePs.setString(1, PasswordUtil.hashPassword(newPassword));
                        updatePs.setInt(2, userId);
                        return updatePs.executeUpdate() > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}