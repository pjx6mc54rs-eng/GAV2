package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import service.StatistiqueService;

public class StatController {
    @FXML
    private Label totalAbsencesLabel;

    @FXML
    private Label totalJustifieesLabel;

    private final StatistiqueService statistiqueService = new StatistiqueService();

    @FXML
    public void initialize() {
        totalAbsencesLabel.setText(String.valueOf(statistiqueService.countAbsences()));
        totalJustifieesLabel.setText(String.valueOf(statistiqueService.countJustifiees()));
    }
}
