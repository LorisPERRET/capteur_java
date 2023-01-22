package model.capteur;

public class CapteurModel {
    private Integer poids;
    private String type;
    private String nom;
    private Integer id;

    public CapteurModel(Integer id, String type, String nom) {
        poids = 1;
        this.type = type;
        this.nom = nom;
        this.id = id;
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

    public Integer getId() {
        return id;
    }
}
