package view;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import model.IObserveur;
import java.io.IOException;

public abstract class Visualisateur implements IObserveur {
    public void onClickFermer(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
