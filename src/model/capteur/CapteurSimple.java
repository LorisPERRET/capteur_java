package model.capteur;

import javafx.application.Platform;
import javafx.scene.Node;
import view.MainWindow;
import view.info.Simple;

public class CapteurSimple extends CapteurAbstrait{
    public CapteurSimple(String nom) {
        super(nom);
    }

    @Override
    public double getTempMoy(){
        return this.getTemperature()*this.getPoids();
    }

    @Override
    public Node display(CapteurAbstrait c, MainWindow mainWindow){
        return new Simple((CapteurSimple) c, mainWindow);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(super.getTempsGeneration()*1000);
                if(this.isActive()){
                    Platform.runLater(() -> {
                        setTemperature(this.getGenerationStrategie().generer());
                    });
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
