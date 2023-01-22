package model.generation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenerationCPU implements IGenerationStrategie{
    public GenerationCPU() {
        System.out.println("oui");
    }

    @Override
    public double generer(){ // Fonctionne que sur linux
        String tempFile = "/sys/class/thermal/thermal_zone0/temp";
        try {
            BufferedReader br = new BufferedReader(new FileReader(tempFile));
            String tempString = br.readLine();
            br.close();
            double temp = Double.parseDouble(tempString) / 1000;
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public String toString() {
        return "Génération CPU";
    }
}
