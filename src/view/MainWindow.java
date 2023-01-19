package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.CapteurAbstrait;
import model.CapteurComposite;
import model.CapteurSimple;
import model.GenerationBornee;

import java.io.IOException;

public class MainWindow {
    @FXML
    private TreeView<CapteurAbstrait> treeViewCaptor;
    @FXML
    private ComboBox<String> comboBoxType;
    @FXML
    private TextField textFieldNom;
    @FXML
    private Label labelId;
    @FXML
    public void onClickButtonSlider(ActionEvent actionEvent) throws IOException {
        Stage slider = new Stage();
        FXMLLoader loaderSlider = new FXMLLoader(getClass().getResource("/fxml/SliderWindow.fxml"));
        loaderSlider.setController(new Slider());
        Scene sceneSlider = new Scene(loaderSlider.load(), 200, 100);
        slider.setScene(sceneSlider);
        slider.show();
    }

    @FXML
    public void onClickButtonImage(ActionEvent actionEvent) throws IOException {
        Stage image = new Stage();
        FXMLLoader loaderImage = new FXMLLoader(getClass().getResource("/fxml/ImageWindow.fxml"));
        loaderImage.setController(new Image());
        Scene sceneImage = new Scene(loaderImage.load(), 200, 100);
        image.setScene(sceneImage);
        image.show();
    }


    @FXML
    public void initialize(){
        comboBoxType.getItems().add("Simple");
        comboBoxType.getItems().add("Composite");
        comboBoxType.setValue("Simple");

        labelId.setText(String.valueOf(CapteurAbstrait.idActuel));

        TreeItem<CapteurAbstrait> base = new TreeItem<>();
        base.setExpanded(true);
        treeViewCaptor.setRoot(base);
    }

    public void onEnter(KeyEvent inputMethodEvent) {
        if(inputMethodEvent.getCode() == KeyCode.ENTER){
            String type = comboBoxType.getValue().toString();
            String nom = textFieldNom.getText();
            CapteurAbstrait capteur;
            TreeItem<CapteurAbstrait> item;

            switch (type){
                case "Composite":
                    capteur = new CapteurComposite(nom);
                    item = new TreeItem<>(capteur);
                    item.setExpanded(true);
                    break;
                default:
                    capteur = new CapteurSimple(nom);
                    item = new TreeItem<>(capteur);
            }
            treeViewCaptor.getRoot().getChildren().add(item);
            labelId.setText(String.valueOf(CapteurAbstrait.idActuel));
        }
    }
}