package model.capteur;

import javafx.scene.Node;
import model.generation.GenerationBornee;
import model.generation.IGenerationStrategie;
import model.Sujet;
import view.MainWindow;
import java.io.IOException;
import java.util.Objects;

public abstract class CapteurAbstrait extends Sujet implements Runnable{
    public static int idActuel = 1;
    private int id;
    private String nom;
    private double temperature;
    private int tempsGeneration;
    private IGenerationStrategie generationStrategie;
    private Thread thread;
    private int poids = 1;
    private boolean active;

    public CapteurAbstrait(String nom) {
        id = idActuel;
        this.nom = nom;
        poids = 1;
        active = true;
        generationStrategie = new GenerationBornee();
        idActuel ++;
        tempsGeneration = 1;
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
        notifier();
    }

    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temp) {
        this.temperature = temp;
        notifier();
    }

    public abstract double getTempMoy();

    public int getPoids() {
        return poids;
    }

    public int getTempsGeneration() {
        return tempsGeneration;
    }
    public void setTempsGeneration(int tempsGeneration) {
        this.tempsGeneration = tempsGeneration;
    }

    public IGenerationStrategie getGenerationStrategie() {
        return generationStrategie;
    }
    public void setGenerationStrategie(IGenerationStrategie generation) {
        this.generationStrategie = generation;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public abstract Node display(CapteurAbstrait c, MainWindow mainWindow) throws IOException;

    @Override
    public String toString() {
        return id + ": " + nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        CapteurAbstrait that = (CapteurAbstrait) o;
        return id == that.id && Objects.equals(nom, that.nom);
    }
}
