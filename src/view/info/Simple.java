package view.info;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.CapteurAbstrait;
import view.MainWindow;

import java.io.IOException;

public class Simple extends VBox {
    private CapteurAbstrait capteurAbstrait;
    @FXML
    private TextField textFieldNom;
    @FXML
    private Label labelId;
    @FXML
    private Spinner<Integer> spinnerTempsGen;
    @FXML
    private ComboBox<String> comboBoxGen;
    public Simple(CapteurAbstrait c) {
        this.capteurAbstrait = c;
    }

    @FXML
    public void initialize(){
        textFieldNom.setText(capteurAbstrait.getNom());
        labelId.setText(String.valueOf(capteurAbstrait.getId()));
        comboBoxGen.getItems().add("Simple");
        comboBoxGen.getItems().add("Composite");
        comboBoxGen.setValue(capteurAbstrait.getGenerationStrategie().toString());
    }
}
