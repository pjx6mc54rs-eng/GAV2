package controller;
import dao.AbsenceDao;
import dao.JustificationDao;
import dao.AlerteDao;
import model.Etudiant;
import model.Absence;
import model.Justification;
import model.Alerte;
import service.JustifService;
import util.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class EtudiantController {
    @FXML private TableView<Absence> tableAbsences;
    @FXML private TableColumn<Absence, Integer> colSeance;
    @FXML private TableColumn<Absence, Integer> colHeures;
    @FXML private TableColumn<Absence, Boolean> colJustifiee;
    
    @FXML private Label lblTotalHeures;
    @FXML private Label lblNonJustifiees;
    @FXML private ListView<String> listAlertes;
    @FXML private ListView<String> listJustificatifs;

    private AbsenceDao absenceDao = new AbsenceDao();
    private JustificationDao justificationDao = new JustificationDao();
    private AlerteDao alerteDao = new AlerteDao();
    private JustifService justifService = new JustifService();
    private Etudiant etudiant;

    public void initialize() {
        etudiant = (Etudiant) SessionManager.getUtilisateurConnecte();
        setupTable();
        chargerDonnees();
    }

    private void setupTable() {
        colSeance.setCellValueFactory(new PropertyValueFactory<>("idSeance"));
        colHeures.setCellValueFactory(new PropertyValueFactory<>("nbHeures"));
        colJustifiee.setCellValueFactory(new PropertyValueFactory<>("justifie")); // Fixed from justifiee
    }

    private void chargerDonnees() {
        try {
            int id = etudiant.getId();
            List<Absence> absences = absenceDao.listerParEtudiant(id);
            tableAbsences.setItems(FXCollections.observableArrayList(absences));
            lblTotalHeures.setText("Total heures : " + absenceDao.totalHeures(id));
            lblNonJustifiees.setText("Non justifiées : " + absenceDao.compterNonJustifiees(id));
            
            List<Alerte> alertes = alerteDao.listerParEtudiant(id);
            listAlertes.getItems().clear();
            for(Alerte a : alertes) listAlertes.getItems().add(a.getMessage() + " (" + a.getDateEnvoi() + ")");
            
            List<Justification> justifs = justificationDao.getByEtudiant(id);
            listJustificatifs.getItems().clear();
            for(Justification j : justifs) listJustificatifs.getItems().add("Absence " + j.getIdAbsence() + " - " + j.getStatut());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
