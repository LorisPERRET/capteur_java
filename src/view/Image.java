package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.capteur.CapteurAbstrait;

public class Image extends Visualisateur{
    private CapteurAbstrait capteur;
    @FXML
    private ImageView imageView;
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
        javafx.scene.image.Image image;
        if(temp < 22){
            if(temp < 0){
                image = new javafx.scene.image.Image("img/neige.jpg");
            }
            else{
                image = new javafx.scene.image.Image("img/nuage.jpg");
            }
        }
        else{
            image = new javafx.scene.image.Image("img/soleil.jpg");
        }
        imageView.setImage(image);
    }

    @Override
    public void update() {
        couleur(capteur.getTemperature());
    }
}
