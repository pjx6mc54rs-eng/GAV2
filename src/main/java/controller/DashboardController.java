package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.*;
import util.SessionManager;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label statsAbsencesLabel;

    @FXML
    private Label statsJustifieesLabel;

    @FXML
    private Label statsAlertesLabel;

    private Utilisateur currentUser;

    @FXML
    public void initialize() {
        // Initialisation supplémentaire si nécessaire
        System.out.println("DashboardController initialisé");
    }

    public void initData(Utilisateur utilisateur) {
        this.currentUser = utilisateur;

        if (utilisateur != null) {
            String nomComplet = getNomComplet(utilisateur);
            welcomeLabel.setText("Bienvenue, " + nomComplet);
            roleLabel.setText("Rôle : " + getRoleFrancais(utilisateur.getRole()));

            // Charger les statistiques (à implémenter avec vos DAO)
            loadStatistics();
        }
    }

    @FXML
    private void handleLogout() {
        System.out.println("Déconnexion...");
        SessionManager.logout();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Connexion - Gestion des Absences");
            stage.setMaximized(false);
            stage.setWidth(400);
            stage.setHeight(250);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors du retour à l'écran de connexion: " + e.getMessage());
        }
    }

    @FXML
    private void handleExit() {
        System.out.println("Fermeture de l'application...");
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleListeAbsences() {
        System.out.println("Ouverture de la liste des absences");
        // Implémenter l'ouverture de la vue des absences
    }

    @FXML
    private void handleAjouterAbsence() {
        System.out.println("Ouverture du formulaire d'ajout d'absence");
        // Implémenter l'ouverture du formulaire d'absence
    }

    @FXML
    private void handleJustifierAbsence() {
        System.out.println("Ouverture du formulaire de justification");
        // Implémenter l'ouverture du formulaire de justification
    }

    @FXML
    private void handleListeEtudiants() {
        System.out.println("Ouverture de la liste des étudiants");
        // Implémenter l'ouverture de la vue des étudiants
    }

    @FXML
    private void handleAjouterEtudiant() {
        System.out.println("Ouverture du formulaire d'ajout d'étudiant");
        // Implémenter l'ouverture du formulaire d'étudiant
    }

    @FXML
    private void handleConsulterEmploi() {
        System.out.println("Ouverture de l'emploi du temps");
        // Implémenter l'ouverture de l'emploi du temps
    }

    @FXML
    private void handleModifierEmploi() {
        System.out.println("Ouverture du formulaire de modification de l'emploi du temps");
        // Implémenter la modification de l'emploi du temps
    }

    @FXML
    private void handleStatistiques() {
        System.out.println("Ouverture des statistiques");
        // Implémenter l'ouverture des statistiques
    }

    @FXML
    private void handleExportPDF() {
        System.out.println("Export PDF des statistiques");
        // Implémenter l'export PDF
    }

    @FXML
    private void handleExportExcel() {
        System.out.println("Export Excel des statistiques");
        // Implémenter l'export Excel
    }

    @FXML
    private void handleDocumentation() {
        System.out.println("Ouverture de la documentation");
        // Implémenter l'ouverture de la documentation
    }

    @FXML
    private void handleAPropos() {
        System.out.println("Ouverture de la boîte de dialogue À propos");
        // Implémenter l'affichage des informations sur l'application
    }

    private void loadStatistics() {
        // À implémenter avec vos DAO
        statsAbsencesLabel.setText("0");
        statsJustifieesLabel.setText("0");
        statsAlertesLabel.setText("0");
    }

    private String getNomComplet(Utilisateur utilisateur) {
        String role = utilisateur.getRole();

        switch (role.toUpperCase()) {
            case "ADMINISTRATION":
                if (utilisateur instanceof Administration admin) {
                    return admin.getPrenom() + " " + admin.getNom();
                }
                return "Administrateur";
            case "ENSEIGNANT":
                if (utilisateur instanceof Enseignant enseignant) {
                    return enseignant.getPrenom() + " " + enseignant.getNom();
                }
                return "Enseignant";
            case "ETUDIANT":
                if (utilisateur instanceof Etudiant etudiant) {
                    return etudiant.getPrenom() + " " + etudiant.getNom();
                }
                return "Étudiant";
            case "PARENT":
                if (utilisateur instanceof model.Parent parent) {
                    return parent.getPrenom() + " " + parent.getNom();
                }
                return "Parent";
            default:
                return utilisateur.getLogin();
        }
    }

    private String getRoleFrancais(String role) {
        switch (role.toUpperCase()) {
            case "ADMINISTRATION":
                return "Administrateur";
            case "ENSEIGNANT":
                return "Enseignant";
            case "ETUDIANT":
                return "Étudiant";
            case "PARENT":
                return "Parent";
            default:
                return role;
        }
    }


}
