package view.info;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.*;
import model.Capteur.*;
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

        TableColumn idColumn = new TableColumn("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle( "-fx-alignment: CENTER;");

        TableColumn nomColumn = new TableColumn("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setStyle( "-fx-alignment: CENTER;");

        TableColumn poidsColumn = new TableColumn("Poids");
        poidsColumn.setCellValueFactory(new PropertyValueFactory<>("poids"));
        poidsColumn.setStyle( "-fx-alignment: CENTER;");

        TableColumn typeColumn = new TableColumn("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setStyle( "-fx-alignment: CENTER;");

        tableView.getColumns().addAll(idColumn, nomColumn, poidsColumn, typeColumn);

        addButtonToTable();

        for (CapteurAbstrait c : capteurComposite.getCapteurList()) {
            String type = String.valueOf(c.getClass()).substring(19);
            tableView.getItems().add(new CapteurModel(c.getId(), type, c.getNom()));
        }
    }

    private void addButtonToTable() {
        TableColumn<CapteurModel, Void> colBtn = new TableColumn("Supprimer");
        Callback<TableColumn<CapteurModel, Void>, TableCell<CapteurModel, Void>> cellFactory = new Callback<TableColumn<CapteurModel, Void>, TableCell<CapteurModel, Void>>() {
            @Override
            public TableCell<CapteurModel, Void> call(final TableColumn<CapteurModel, Void> param) {
                final TableCell<CapteurModel, Void> cell = new TableCell<CapteurModel, Void>() {
                    private final Button btn = new Button("X");{
                        btn.setOnAction((ActionEvent event) -> {
                            CapteurModel selectedCapteur = getTableView().getItems().get(getIndex());
                            capteurComposite.delCaptor(selectedCapteur.getId());
                            tableView.getItems().remove(selectedCapteur);
                            TreeItem<CapteurAbstrait> treeItemPere = recherche(mainWindow.treeViewCaptor.getRoot().getChildren(), capteurComposite.getId());
                            TreeItem<CapteurAbstrait> treeItemFils = recherche(mainWindow.treeViewCaptor.getRoot().getChildren(), selectedCapteur.getId());
                            if(treeItemFils != null){
                                treeItemPere.getChildren().remove(treeItemFils);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        tableView.getColumns().add(colBtn);
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
        TreeItem<CapteurAbstrait> item;
        CapteurAbstrait capteur;
        if(!Objects.equals(nom, "")){
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
            capteurComposite.addCaptor(capteur);
            tableView.getItems().add(new CapteurModel(capteur.getId(), type, nom));
            TreeItem<CapteurAbstrait> treeItem = recherche(mainWindow.treeViewCaptor.getRoot().getChildren(), capteurComposite.getId());
            if(treeItem != null){
                treeItem.getChildren().add(item);
            }
            textFieldNew.setText("");
        }
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

    @Override
    public void update() {
        tableView.refresh();
    }
}
