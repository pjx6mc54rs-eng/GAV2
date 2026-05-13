package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    /**
     * Hash un mot de passe avec BCrypt
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    /**
     * Vérifie un mot de passe
     */
    public static boolean verifyPassword(String plainPassword, String storedHash) {
        return BCrypt.checkpw(plainPassword, storedHash);
    }

    // Méthode main pour générer des hashs
    public static void main(String[] args) {
        System.out.println("Hash pour 'password123': " + hashPassword("password123"));
        System.out.println("Hash pour 'admin123': " + hashPassword("admin123"));
    }
}