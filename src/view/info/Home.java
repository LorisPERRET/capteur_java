package view.info;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import model.capteur.CapteurAbstrait;
import model.capteur.CapteurComposite;
import model.capteur.CapteurSimple;
import view.MainWindow;
import java.io.IOException;
import java.util.Objects;

public class Home extends VBox {
    private MainWindow mainWindow;
    @FXML
    private ComboBox<String> comboBoxType;
    @FXML
    private TextField textFieldNom;
    @FXML
    private Label labelId;
    @FXML
    private Spinner<Integer> spinnerPoids;

    public Home(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/info/home.fxml"));
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
        comboBoxType.getItems().add("Simple");
        comboBoxType.getItems().add("Composite");
        comboBoxType.setValue("Simple");

        labelId.setText(String.valueOf(CapteurAbstrait.idActuel));

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        spinnerPoids.setValueFactory(valueFactory);
    }

    public void onEnter(KeyEvent inputMethodEvent){
        if(inputMethodEvent.getCode() == KeyCode.ENTER){
            String type = comboBoxType.getValue().toString();
            String nom = textFieldNom.getText();
            Integer poids = spinnerPoids.getValue();
            CapteurAbstrait capteur;
            TreeItem<CapteurAbstrait> item;
            if(!Objects.equals(nom, "")) {
                switch (type) {
                    case "Composite":
                        capteur = new CapteurComposite(nom);
                        item = new TreeItem<>(capteur);
                        item.setExpanded(true);
                        break;
                    default:
                        capteur = new CapteurSimple(nom);
                        item = new TreeItem<>(capteur);
                }
                capteur.attacher(mainWindow);
                mainWindow.treeViewCaptor.getRoot().getChildren().add(item);
                labelId.setText(String.valueOf(CapteurAbstrait.idActuel));
                textFieldNom.setText("");
            }
        }
    }
}
