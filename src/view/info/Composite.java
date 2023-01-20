package view.info;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.CapteurAbstrait;

public class Composite extends VBox {
    private CapteurAbstrait capteurAbstrait;

    public Composite(CapteurAbstrait c) {
        this.capteurAbstrait = c;
    }
}
