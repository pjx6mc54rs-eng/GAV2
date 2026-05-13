package controller;

import dao.LoginDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Utilisateur;
import util.SessionManager;

import java.io.IOException;
import java.util.Optional;

public class LoginController {
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;

    private LoginDao loginDao;

    @FXML
    public void initialize() {
        loginDao = new LoginDao();

        // Ajouter la possibilité de se connecter avec la touche Entrée
        passwordField.setOnAction(event -> handleLogin());
        loginField.setOnAction(event -> handleLogin());
    }

    @FXML
    private void handleLogin() {
        String login = loginField.getText().trim();
        String password = passwordField.getText();

        // Validation des champs
        if (login.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Veuillez saisir votre login et mot de passe.");
            errorLabel.setVisible(true);
            return;
        }

        // Désactiver le bouton pendant l'authentification
        loginButton.setDisable(true);
        errorLabel.setVisible(false);

        // Tentative d'authentification
        Optional<Utilisateur> utilisateurOpt = loginDao.authenticate(login, password);

        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            SessionManager.setCurrentUser(utilisateur);

            // Redirection vers le dashboard
            redirectToDashboard();
        } else {
            errorLabel.setText("Login ou mot de passe incorrect.");
            errorLabel.setVisible(true);
            loginButton.setDisable(false);

            // Vider le champ mot de passe
            passwordField.clear();
        }
    }

    private void redirectToDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
            Parent root = loader.load();

            DashboardController dashboardController = loader.getController();
            dashboardController.initData(SessionManager.getCurrentUser());

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard - Gestion des Absences");
            stage.setMaximized(true);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Erreur lors du chargement du dashboard.");
            errorLabel.setVisible(true);
            loginButton.setDisable(false);
        }
    }
}