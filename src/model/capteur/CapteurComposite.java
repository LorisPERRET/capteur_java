package model.capteur;

import javafx.application.Platform;
import javafx.scene.Node;
import view.MainWindow;
import view.info.Composite;
import java.util.ArrayList;
import java.util.List;

public class CapteurComposite extends CapteurAbstrait{
    List<CapteurAbstrait> capteurList = new ArrayList<>();

    public CapteurComposite(String nom) {
        super(nom);
    }

    public List<CapteurAbstrait> getCapteurList() {
        return capteurList;
    }

    @Override
    public double getTempMoy(){
        double moy = 0;
        int poids = 1;
        for (CapteurAbstrait c : capteurList) {
            moy += c.getTempMoy();
            poids += c.getPoids();
        }
        moy /= poids;
        return moy;
    }

    public void addCaptor(CapteurAbstrait c){
        capteurList.add(c);
        notifier();
    }

    public void delCaptor(int idCaptor){
        for (CapteurAbstrait capteur : capteurList) {
            if(capteur.getId() == idCaptor){
                capteurList.remove(capteur);
                notifier();
                break;
            }
        }
    }

    @Override
    public Node display(CapteurAbstrait c, MainWindow mainWindow){
        return new Composite((CapteurComposite) c, mainWindow);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(super.getTempsGeneration()*1000);
                Platform.runLater(() -> {
                    setTemperature(this.getTempMoy());
                });
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
