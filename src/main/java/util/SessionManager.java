package util;
import model.Utilisateur;

public class SessionManager {
    private static Utilisateur utilisateurConnecte;

    public static void setUtilisateurConnecte(Utilisateur u) {
        utilisateurConnecte = u;
    }

    public static Utilisateur getUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    public static String getRoleConnecte() {
        return utilisateurConnecte != null ? utilisateurConnecte.getRole() : null;
    }

    public static void deconnecter() {
        utilisateurConnecte = null;
    }
}