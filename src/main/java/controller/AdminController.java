package controller;

import util.SessionManager;
import dao.EtudiantDao;
import model.Etudiant;
import service.StatistiqueService;
import service.EtudiantService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class AdminController {
    @FXML private TableView<Etudiant> tableEtudiants;
    @FXML private TableColumn<Etudiant, String> colNom;
    @FXML private TableColumn<Etudiant, String> colPrenom;
    @FXML private TableColumn<Etudiant, String> colCne;
    
    private EtudiantDao etudiantDao = new EtudiantDao();
    private EtudiantService etudiantService = new EtudiantService();

    public void initialize() {
        setupTable();
        chargerDonnees();
    }

    private void setupTable() {
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCne.setCellValueFactory(new PropertyValueFactory<>("cne"));
    }

    private void chargerDonnees() {
        try {
            List<Etudiant> etudiants = etudiantDao.listerTout();
            tableEtudiants.setItems(FXCollections.observableArrayList(etudiants));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void exportPdf(ActionEvent event) {
        // Assume PDF Generation handled by utility using iTextPDF
        util.ExportPDF.generer(tableEtudiants.getItems(), "absences.pdf");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "PDF généré avec succès !");
        alert.show();
    }
    
    @FXML
    public void handleLogout(ActionEvent event) {
        SessionManager.deconnecter();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
