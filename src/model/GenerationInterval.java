package model;

public class GenerationInterval implements IGenerationStrategie{
    private int d;
    private double lastTemp = Math.random()*(60-(-60)+1)+(-60);

    public GenerationInterval(int d) {
        this.d = d;
    }

    @Override
    public double generer() {
        double borneSup = lastTemp + d;
        double borneInf = lastTemp - d;
        double temp = Math.random()*(borneSup-borneInf+1)+borneInf;
        lastTemp = temp;
        return temp;
    }
}
