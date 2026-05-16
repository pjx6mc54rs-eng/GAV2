package controller;

import dao.GroupeDao;
import dao.SeanceDao;
import dao.EtudiantDao;
import model.Enseignant;
import model.Groupe;
import model.Seance;
import model.Etudiant;
import model.Absence;
import service.AbsenceService;
import util.SessionManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class EnseignantController {
    @FXML
    private ComboBox<Groupe> comboGroupe;
    @FXML
    private ComboBox<Seance> comboSeance;
    @FXML
    private ListView<EtudiantCheck> listEtudiants;

    private GroupeDao groupeDao = new GroupeDao();
    private SeanceDao seanceDao = new SeanceDao();
    private EtudiantDao etudiantDao = new EtudiantDao();
    private AbsenceService absenceService = new AbsenceService();
    private Enseignant enseignant;

    public void initialize() {
        enseignant = (Enseignant) SessionManager.getUtilisateurConnecte();
        chargerGroupes();
    }

    private void chargerGroupes() {
        try {
            List<Groupe> groupes = groupeDao.listerTout(); // Simplified: Enseignant can see all groups or groups
                                                           // filtered by filiere
            comboGroupe.setItems(FXCollections.observableArrayList(groupes));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onGroupeSelected(ActionEvent event) {
        Groupe selected = comboGroupe.getValue();
        if (selected != null) {
            chargerSeances(selected.getId());
            chargerEtudiants(selected.getId());
        }
    }

    private void chargerSeances(int idGroupe) {
        try {
            List<Seance> seances = seanceDao.getByGroupe(idGroupe);
            comboSeance.setItems(FXCollections.observableArrayList(seances));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void chargerEtudiants(int idGroupe) {
        try {
            List<Etudiant> etudiants = etudiantDao.listerParGroupe(idGroupe);
            ObservableList<EtudiantCheck> items = FXCollections.observableArrayList();
            for (Etudiant e : etudiants) {
                items.add(new EtudiantCheck(e));
            }
            listEtudiants.setItems(items);
            listEtudiants.setCellFactory(param -> new ListCell<EtudiantCheck>() {
                @Override
                protected void updateItem(EtudiantCheck item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        CheckBox cb = new CheckBox(item.etudiant.getNom() + " " + item.etudiant.getPrenom());
                        cb.selectedProperty().bindBidirectional(item.selectedProperty);
                        setGraphic(cb);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void enregistrerAbsences(ActionEvent event) {
        Seance seance = comboSeance.getValue();
        if (seance == null) {
            showAlert("Erreur", "Veuillez sélectionner une séance.");
            return;
        }

        int nbHeures = java.time.Duration.between(seance.getHeureDebut(), seance.getHeureFin()).toHoursPart();
        if (nbHeures == 0)
            nbHeures = 1;

        for (EtudiantCheck ec : listEtudiants.getItems()) {
            if (ec.selectedProperty.get()) {
                Absence a = new Absence(0, LocalDateTime.now(), false, nbHeures, ec.etudiant.getId(), seance.getId());
                try {
                    absenceService.enregistrerAbsence(a);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        showAlert("Succès", "Absences enregistrées.");
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Helper class for ListView with CheckBox
    public static class EtudiantCheck {
        Etudiant etudiant;
        javafx.beans.property.BooleanProperty selectedProperty = new javafx.beans.property.SimpleBooleanProperty(false);

        public EtudiantCheck(Etudiant e) {
            this.etudiant = e;
        }
    }
}
