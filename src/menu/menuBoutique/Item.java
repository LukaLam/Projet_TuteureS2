package menu.menuBoutique;

public abstract class Item {
    protected String titre;
    protected String chemin;
    protected String cheminGrand;
    protected String description;
    protected int prix;

    public Item(String titre, String chemin, String cheminGrand, String description, int prix) {
        this.titre = titre;
        this.chemin = chemin;
        this.cheminGrand = cheminGrand;
        this.description = description;
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String getCheminGrand() {
        return cheminGrand;
    }

    public void setCheminGrand(String cheminGrand) {
        this.cheminGrand = cheminGrand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Item{" +
                "titre='" + titre + '\'' +
                ", chemin='" + chemin + '\'' +
                ", cheminGrand='" + cheminGrand + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
