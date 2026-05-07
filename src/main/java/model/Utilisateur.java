package model;

public abstract class Utilisateur {
    protected int id;
    protected String login;
    protected String motDePasse;
    protected String role;

    public Utilisateur(int id, String login, String motDePasse, String role) {
        this.id = id;
        this.login = login;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public boolean seConnecter() {
        // Logique d'authentification
        return login != null && motDePasse != null;
    }
    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
