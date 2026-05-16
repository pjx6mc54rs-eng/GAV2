package util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    
    // Hash a password using BCrypt
    public static String hashSHA256(String password) {
        // We keep the method name hashSHA256 so we don't break existing calls in the DAO,
        // but we actually use BCrypt under the hood to match the database!
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    
    // Verify a password against a BCrypt hash
    public static boolean verifyPassword(String candidate, String hashed) {
        if (hashed == null || !hashed.startsWith("$2a$")) {
            // Fallback for simple testing or clear text
            return candidate.equals(hashed);
        }
        try {
            return BCrypt.checkpw(candidate, hashed);
        } catch (Exception e) {
            return false;
        }
    }
}
