package model;

public class GenerationBornee implements IGenerationStrategie{
    private double borneInf;
    private double borneSup;

    public GenerationBornee(double min, double max) {
        borneInf = min;
        borneSup = max;
    }

    public GenerationBornee() {
        borneInf = Double.MIN_VALUE;
        borneSup = Double.MAX_VALUE;
    }

    @Override
    public double generer(){
        return Math.random()*(borneSup-borneInf+1)+borneInf;
    }
}
