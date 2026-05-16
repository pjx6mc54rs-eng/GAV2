package controller;
import dao.AbsenceDao;
import dao.ParentDao;
import dao.AlerteDao;
import model.Absence;
import util.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class ParentController {
    @FXML private TableView<Absence> tableAbsences;
    @FXML private TableColumn<Absence, Integer> colSeance;
    @FXML private TableColumn<Absence, Integer> colHeures;
    @FXML private TableColumn<Absence, Boolean> colJustifiee;
    
    @FXML private Label lblTotalHeures;
    @FXML private ListView<String> listAlertes;

    private AbsenceDao absenceDao = new AbsenceDao();
    private ParentDao parentDao = new ParentDao();
    private AlerteDao alerteDao = new AlerteDao();
    private model.Parent parentModel;
    private int idEnfant;

    public void initialize() {
        parentModel = (model.Parent) SessionManager.getUtilisateurConnecte();
        setupTable();
        try {
            idEnfant = parentDao.getIdEnfant(parentModel.getId());
            chargerDonnees();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupTable() {
        colSeance.setCellValueFactory(new PropertyValueFactory<>("idSeance"));
        colHeures.setCellValueFactory(new PropertyValueFactory<>("nbHeures"));
        colJustifiee.setCellValueFactory(new PropertyValueFactory<>("justifie"));
    }

    private void chargerDonnees() {
        if (idEnfant <= 0) return;
        try {
            List<Absence> absences = absenceDao.listerParEtudiant(idEnfant);
            tableAbsences.setItems(FXCollections.observableArrayList(absences));
            lblTotalHeures.setText("Total absences : " + absenceDao.compterParEtudiant(idEnfant));
            
            // Re-using listerParEtudiant for parents since Alertes are tied to absence, which is tied to the student.
            var alertes = alerteDao.listerParEtudiant(idEnfant);
            listAlertes.getItems().clear();
            for(var a : alertes) listAlertes.getItems().add(a.getMessage() + " (" + a.getDateEnvoi() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleLogout(ActionEvent event) {
        SessionManager.deconnecter();
        try {
            javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
