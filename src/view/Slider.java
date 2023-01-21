package view;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Capteur.CapteurAbstrait;

public class Slider extends Visualisateur{

    private CapteurAbstrait capteur;
    @FXML
    private javafx.scene.control.Slider slider;
    @FXML
    private Label labelCapteur;
    @FXML
    private Label labelTemp;

    public Slider(CapteurAbstrait capteur) {
        this.capteur = capteur;
    }

    @FXML
    public void initialize() {
        capteur.attacher(this);
        String id = String.valueOf(capteur.getId());
        String temp = String.format("%.2f", capteur.getTemperature());
        labelCapteur.setText(id);
        labelTemp.setText(temp);
        slider.setValue(capteur.getTemperature());
    }

    public void onChange(){
        double value = slider.getValue();
        capteur.setTemperature(value);
    }

    @Override
    public void update() {
        String temp = String.format("%.2f", capteur.getTemperature());
        labelTemp.setText(temp);
    }
}
