package menu.menuBoutique;

public class Achat {
    private int prix;
    private String nom;
    private String description;

    public Achat() {
    }

    public Achat(int prix, String nom) {
        this.prix = prix;
        this.nom = nom;
        this.description = "Aucune description";
    }

    public Achat(int prix, String nom, String description) {
        this.prix = prix;
        this.nom = nom;
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
