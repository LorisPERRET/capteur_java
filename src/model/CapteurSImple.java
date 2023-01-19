package model;

import javafx.application.Platform;

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

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Platform.runLater(() -> {
                    setTemperature(super.getGenerationStrategie().generer());
                });
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
