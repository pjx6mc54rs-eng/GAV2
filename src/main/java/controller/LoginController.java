package controller;

import dao.UtilisateurDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Utilisateur;
import util.SessionManager;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML private TextField txtLogin;
    @FXML private PasswordField txtPassword;

    private UtilisateurDao utilisateurDao = new UtilisateurDao();

    @FXML
    public void handleLogin(ActionEvent event) {
        String login = txtLogin.getText();
        String password = txtPassword.getText();

        if (login.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        try {
            Utilisateur user = utilisateurDao.login(login, password);
            if (user != null) {
                SessionManager.setUtilisateurConnecte(user);
                redirigerSelonRole(user.getRole(), event);
            } else {
                showAlert(Alert.AlertType.ERROR, "Erreur de connexion", "Identifiants incorrects ou compte inactif.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur Base de données", "Erreur lors de la connexion à la base de données.");
        }
    }

    private void redirigerSelonRole(String role, ActionEvent event) {
        String fxmlFile = "";
        switch (role) {
            case "ENSEIGNANT": fxmlFile = "/view/DashboardEnseignant.fxml"; break;
            case "ETUDIANT":   fxmlFile = "/view/DashboardEtudiant.fxml"; break;
            case "PARENT":     fxmlFile = "/view/DashboardParent.fxml"; break;
            case "ADMINISTRATION": fxmlFile = "/view/DashboardAdmin.fxml"; break;
        }
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur Navigation", "Impossible de charger le tableau de bord.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
