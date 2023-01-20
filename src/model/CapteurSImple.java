package model;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import view.MainWindow;
import view.info.Home;
import view.info.Simple;

import java.io.IOException;

public class CapteurSimple extends CapteurAbstrait{
    private int poids = 1;

    public CapteurSimple(String nom) {
        super(nom);
    }

    public int getPoids() {
        return poids;
    }

    @Override
    public double getTempMoy(){
        return this.getTemperature()*this.getPoids();
    }

    private MainWindow mainWindow;

    @Override
    public void display(CapteurAbstrait c){
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/info/home.fxml"));
        Simple right = new Simple(c);
        fxmlloader.setController(right);
        fxmlloader.setRoot(right);
        try {
            fxmlloader.load();
        } catch (IOException e) {

        }

        this.mainWindow.setSplitPane(right);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(super.getTempsGeneration());
                Platform.runLater(() -> {
                    setTemperature(super.getGenerationStrategie().generer());
                });
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
