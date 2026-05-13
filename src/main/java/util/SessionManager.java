package util;

import model.Utilisateur;

public class SessionManager {
    private static Utilisateur currentUser;

    public static void setCurrentUser(Utilisateur user) {
        currentUser = user;
    }

    public static Utilisateur getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean hasRole(String role) {
        return currentUser != null && currentUser.getRole().equalsIgnoreCase(role);
    }
}