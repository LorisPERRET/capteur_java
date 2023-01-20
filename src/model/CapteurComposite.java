package model;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import view.info.Composite;
import view.info.Simple;

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

    public CapteurComposite(String nom) {
        super(nom);
    }

    public void addCaptor(CapteurAbstrait c){
        capteurList.add(c);
    }

    public void delCaptor(CapteurAbstrait c){
        capteurList.remove(c);
    }

    @Override
    public Pair<Node, FXMLLoader> display(CapteurAbstrait c){
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/fxml/info/composite.fxml"));
        Node right = new Composite(c);
        return new Pair<Node, FXMLLoader>(right, fxmlloader);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                Platform.runLater(() -> {
                    setTemperature(this.getTempMoy());
                });
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
