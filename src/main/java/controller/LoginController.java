package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Utilisateur;
import util.SessionManager;

public class LoginController {
    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin() {
        String login = loginField.getText();
        String password = passwordField.getText();

        if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
            SessionManager.setCurrentUser(new Utilisateur(0, login, password, "UTILISATEUR") {});
            errorLabel.setText("Connexion réussie");
        } else {
            errorLabel.setText("Veuillez saisir vos identifiants.");
        }
    }
}
