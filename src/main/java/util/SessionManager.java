package util;

import model.Utilisateur;

public class SessionManager {
    private static Utilisateur currentUser;

    public static Utilisateur getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Utilisateur currentUser) {
        SessionManager.currentUser = currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}
