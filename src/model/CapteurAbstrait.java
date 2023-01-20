package model;

import javafx.application.Platform;

public abstract class CapteurAbstrait extends Sujet implements Runnable{
    public static int idActuel = 1;
    private int id;
    private String nom;
    private double temperature;
    private int tempsGeneration;
    private IGenerationStrategie generationStrategie;
    private Thread thread;

    public CapteurAbstrait(String nom) {
        this.id = idActuel;
        this.nom = nom;
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


    public abstract void display(CapteurAbstrait c);

    @Override
    public String toString() {
        return id + ": " + nom;
    }
}
