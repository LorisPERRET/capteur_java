package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.CapteurAbstrait;
import model.CapteurComposite;
import model.CapteurSimple;
import model.GenerationBornee;
import view.info.Home;

import java.io.IOException;

public class MainWindow {
    @FXML
    public SplitPane splitPane;
    @FXML
    private TreeView<CapteurAbstrait> treeViewCaptor;

    public void setSplitPane(Node o) {
        splitPane.getItems().add(1, o);
    }

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
    public void initialize() throws IOException {
        TreeItem<CapteurAbstrait> base = new TreeItem<>();
        base.setExpanded(true);
        treeViewCaptor.setRoot(base);
        treeViewCaptor.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
            TreeItem<CapteurAbstrait> selectedItem = (TreeItem<CapteurAbstrait>) newValue;
            selectedItem.getValue().display(selectedItem.getValue());
        });

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/info/home.fxml"));
        Home right = new Home(treeViewCaptor);
        fxmlloader.setController(right);
        fxmlloader.setRoot(right);
        fxmlloader.load();
        splitPane.getItems().add(1, right);
    }
}