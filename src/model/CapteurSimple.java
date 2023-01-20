package model;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.util.Pair;
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
    public Pair<Node, FXMLLoader> display(CapteurAbstrait c){
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/info/simple.fxml"));
        Node right = new Simple(c);
        return new Pair<Node, FXMLLoader>(right, fxmlloader);
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
