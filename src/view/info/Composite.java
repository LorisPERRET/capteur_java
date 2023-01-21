package view.info;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import model.*;
import model.Capteur.CapteurAbstrait;
import model.Capteur.CapteurComposite;
import model.Capteur.CapteurModel;
import model.Capteur.CapteurSimple;
import view.MainWindow;

import java.io.IOException;
import java.util.Objects;

public class Composite extends VBox implements IObserveur {
    private CapteurComposite capteurComposite;
    private MainWindow mainWindow;
    @FXML
    private TextField textFieldNom;
    @FXML
    private Label labelId;
    @FXML
    private TableView tableView;
    @FXML
    private TextField textFieldNew;
    @FXML
    private ComboBox comboBoxNew;
    @FXML
    private Spinner<Integer> spinnerPoids;

    public Composite(CapteurComposite c, MainWindow mainWindow) {
        capteurComposite = c;
        this.mainWindow = mainWindow;
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/info/composite.fxml"));
        fxmlloader.setController(this);
        fxmlloader.setRoot(this);
        try {
            fxmlloader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        capteurComposite.attacher(this);
    }

    @FXML
    public void initialize(){
        textFieldNom.setText(capteurComposite.getNom());
        labelId.setText(String.valueOf(capteurComposite.getId()));
        comboBoxNew.getItems().add("Simple");
        comboBoxNew.getItems().add("Composite");
        comboBoxNew.setValue("Simple");

        TableColumn poidsColumn = new TableColumn("Poids");
        poidsColumn.setCellValueFactory(new PropertyValueFactory<>("poids"));
        poidsColumn.setStyle( "-fx-alignment: CENTER;");

        TableColumn typeColumn = new TableColumn("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setStyle( "-fx-alignment: CENTER;");

        TableColumn nomColumn = new TableColumn("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setStyle( "-fx-alignment: CENTER;");

        tableView.getColumns().addAll(poidsColumn, typeColumn, nomColumn);

        for (CapteurAbstrait c : capteurComposite.getCapteurList()) {
            String type = String.valueOf(c.getClass()).substring(19);
            tableView.getItems().add(new CapteurModel(c.getPoids(), type, c.getNom()));
        }

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        spinnerPoids.setValueFactory(valueFactory);
    }

    public void onEnter(KeyEvent inputMethodEvent){
        if(inputMethodEvent.getCode() == KeyCode.ENTER){
            String nom = textFieldNom.getText();
            capteurComposite.setNom(nom);
        }
    }

    public void addCaptor(){
        String type = comboBoxNew.getValue().toString();
        String nom = textFieldNew.getText();
        Integer poids = spinnerPoids.getValue();
        TreeItem<CapteurAbstrait> item;
        CapteurAbstrait capteur;
        if(!Objects.equals(nom, "")){
            switch (type){
                case "Composite":
                    capteur = new CapteurComposite(nom, poids);
                    item = new TreeItem<>(capteur);
                    item.setExpanded(true);
                    break;
                default:
                    capteur = new CapteurSimple(nom, poids);
                    item = new TreeItem<>(capteur);
            }
            capteurComposite.addCaptor(capteur);
            tableView.getItems().add(new CapteurModel(poids, type, nom));
            TreeItem<CapteurAbstrait> treeItem = recherche(mainWindow.treeViewCaptor.getRoot().getChildren(), capteurComposite.getId());
            if(treeItem != null){
                treeItem.getChildren().add(item);
            }
            textFieldNew.setText("");
        }
    }

    @Override
    public void update() {
        tableView.refresh();
    }


    public TreeItem<CapteurAbstrait> recherche(ObservableList<TreeItem<CapteurAbstrait>> fils, int id){
        if(fils.size() == 0) return null;
        for (TreeItem<CapteurAbstrait> treeItem : fils) {
            if(treeItem.getValue().getId() == id){
                return treeItem;
            }
        }
        for (TreeItem<CapteurAbstrait> treeItem : fils) {
            if(treeItem.isExpanded()){
                return recherche(treeItem.getChildren(), id);
            }
        }
        return null;
    }
}
