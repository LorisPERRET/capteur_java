package view.info;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.Capteur.CapteurSimple;
import model.Generation.GenerationBornee;
import model.Generation.GenerationInterval;
import view.MainWindow;
import java.io.IOException;

public class Simple extends VBox {
    private CapteurSimple capteurSimple;
    @FXML
    private TextField textFieldNom;
    @FXML
    private Label labelId;
    @FXML
    private Spinner<Integer> spinnerTempsGen;
    @FXML
    private ComboBox<String> comboBoxGen;
    public Simple(CapteurSimple c, MainWindow mainWindow) {
        this.capteurSimple = c;
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/info/simple.fxml"));
        fxmlloader.setController(this);
        fxmlloader.setRoot(this);
        try {
            fxmlloader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize(){
        textFieldNom.setText(capteurSimple.getNom());
        labelId.setText(String.valueOf(capteurSimple.getId()));
        comboBoxGen.getItems().add("Bornes infinies");
        comboBoxGen.getItems().add("+/- 10");
        comboBoxGen.getItems().add("de -60 à 100");
        comboBoxGen.setValue(capteurSimple.getGenerationStrategie().toString());

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        spinnerTempsGen.setValueFactory(valueFactory);
    }

    public void save(ActionEvent actionEvent){
        String nom = textFieldNom.getText();
        capteurSimple.setNom(nom);

        String gen = comboBoxGen.getValue().toString();
        switch (gen){
            case "+/- 10":
                capteurSimple.setGenerationStrategie(new GenerationInterval(10));
                break;
            case "de -60 à 100":
                capteurSimple.setGenerationStrategie(new GenerationBornee(-60, 100));
                break;
            default:
                capteurSimple.setGenerationStrategie(new GenerationBornee());
        }

        int tmps = spinnerTempsGen.getValue();
        capteurSimple.setTempsGeneration(tmps);
    }
}
