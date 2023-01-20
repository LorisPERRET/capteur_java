package model;

import javafx.scene.Node;
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
    public int getPoids() {
        return poids;
    }

    public CapteurAbstrait(String nom, int poids) {
        this.id = idActuel;
        this.nom = nom;
        this.poids = poids;
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

    public double getTemperature() {
        return temperature;
    }

    public abstract double getTempMoy();

    public void setTempsGeneration(int tempsGeneration) {
        this.tempsGeneration = tempsGeneration;
    }

    public int getTempsGeneration() {
        return tempsGeneration;
    }

    public IGenerationStrategie getGenerationStrategie() {
        return generationStrategie;
    }

    public void setTemperature(double temp) {
        this.temperature = temp;
        this.notifier();
    }

    public void setGenerationStrategie(IGenerationStrategie generation) {
        this.generationStrategie = generation;
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

    public void setNom(String n) {
        this.nom = n;
        this.notifier();
    }
}
