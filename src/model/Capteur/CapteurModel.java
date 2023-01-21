package model.Capteur;

public class CapteurModel {
    private Integer poids;
    private String type;
    private String nom;

    public CapteurModel(Integer poids, String type, String nom) {
        this.poids = poids;
        this.type = type;
        this.nom = nom;
    }

    public Integer getPoids() {
        return poids;
    }

    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }
}
