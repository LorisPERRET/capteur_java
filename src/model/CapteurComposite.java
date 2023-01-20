package model;

import javafx.application.Platform;
import javafx.scene.Node;
import view.MainWindow;
import view.info.Composite;

import java.util.ArrayList;
import java.util.List;

public class CapteurComposite extends CapteurAbstrait{
    List<CapteurAbstrait> capteurList = new ArrayList<>();

    @Override
    public double getTempMoy(){
        double moy = 0;
        for (CapteurAbstrait c : capteurList) {
            moy += c.getTempMoy();
        }
        moy /= capteurList.size();
        return moy;
    }

    public CapteurComposite(String nom, int poids) {
        super(nom, poids);
    }

    public void addCaptor(CapteurAbstrait c){
        capteurList.add(c);
        notifier();
    }

    public void delCaptor(CapteurAbstrait c){
        capteurList.remove(c);
    }

    public List<CapteurAbstrait> getCapteurList() {
        return capteurList;
    }

    @Override
    public Node display(CapteurAbstrait c, MainWindow mainWindow){
        return new Composite((CapteurComposite) c, mainWindow);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(super.getTempsGeneration());
                Platform.runLater(() -> {
                    setTemperature(this.getTempMoy());
                });
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
