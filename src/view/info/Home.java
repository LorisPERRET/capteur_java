package view.info;

import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import model.CapteurAbstrait;
import model.CapteurComposite;
import model.CapteurSimple;
import view.MainWindow;

import java.io.IOException;

public class Home extends VBox {

    private MainWindow mainWindow;
    private TreeView<CapteurAbstrait> treeView;
    @FXML
    private ComboBox<String> comboBoxType;
    @FXML
    private TextField textFieldNom;
    @FXML
    private Label labelId;

    public Home(TreeView<CapteurAbstrait> treeViewCaptor) {
        this.treeView = treeViewCaptor;
    }

    @FXML
    public void initialize(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
        try {
            Parent root = (Parent) loader.load();
        } catch (IOException ignored) {

        }

        this.mainWindow = loader.getController();

        comboBoxType.getItems().add("Simple");
        comboBoxType.getItems().add("Composite");
        comboBoxType.setValue("Simple");

        labelId.setText(String.valueOf(CapteurAbstrait.idActuel));
    }

    public void onEnter(KeyEvent inputMethodEvent){
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
            treeView.getRoot().getChildren().add(item);
            labelId.setText(String.valueOf(CapteurAbstrait.idActuel));
        }
    }

    public void test(EventTarget e){
        System.out.println("test");
    }
}
