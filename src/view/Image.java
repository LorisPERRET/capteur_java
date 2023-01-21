package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import model.Capteur.CapteurAbstrait;
import static javafx.scene.paint.Color.*;

public class Image extends Visualisateur{
    private CapteurAbstrait capteur;
    @FXML
    private Rectangle rectangle;
    @FXML
    private Label labelCapteur;
    public Image(CapteurAbstrait capteur) {
        this.capteur = capteur;
    }

    @FXML
    public void initialize() {
        capteur.attacher(this);
        String id = String.valueOf(capteur.getId());
        labelCapteur.setText(id);
        couleur(capteur.getTemperature());
    }

    public void couleur(double temp){
        if(temp < 22){
            if(temp < 0){
                rectangle.setFill(DODGERBLUE);
            }
            else{
                rectangle.setFill(GREEN);
            }
        }
        else{
            rectangle.setFill(RED);
        }
    }

    @Override
    public void update() {
        couleur(capteur.getTemperature());
    }
}
