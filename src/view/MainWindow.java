package view;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.CapteurAbstrait;
import view.info.Home;

import java.io.IOException;

public class MainWindow extends Visualisateur{
    @FXML
    public SplitPane splitPane;
    @FXML
    public TreeView<CapteurAbstrait> treeViewCaptor;

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
    public void initialize() {
        TreeItem<CapteurAbstrait> base = new TreeItem<>();
        base.setExpanded(true);
        treeViewCaptor.setRoot(base);
        treeViewCaptor.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            TreeItem<CapteurAbstrait> selectedItem = (TreeItem<CapteurAbstrait>) newValue;
            Node node = null;
            if(selectedItem.getValue() != null){
                try {
                    node = selectedItem.getValue().display(selectedItem.getValue(), this);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                node = new Home(this);
            }
            addNode(node);
        });

        Home right = new Home(this);
        splitPane.getItems().add(1, right);
    }

    public void addNode(Node n){
        splitPane.getItems().remove(1);
        splitPane.getItems().add(1, n);
        splitPane.setDividerPosition(0, 0.3);
    }

    @Override
    public void update() {
        treeViewCaptor.refresh();
    }
}