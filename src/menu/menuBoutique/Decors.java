package menu.menuBoutique;
import java.util.ArrayList;
import java.util.List;

public class Decors {
    protected String titre;
    protected String cheminImage;
    protected String cheminImageGrand;
    protected String description;
    protected int prix;

    public Decors(String titre, String cheminImage, String cheminImageGrand, String description, int prix){
        this.titre = titre;
        this.cheminImage = cheminImage;
        this.cheminImageGrand = cheminImageGrand;
        this.description = description;
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public String getCheminImageGrand(){ return cheminImageGrand;}

    public String getDescription() {
        return description;
    }

    public int getPrix() {
        return prix;
    }


    public String toString() {
        return "Decors{" +
                "titre='" + titre + '\'' +
                ", cheminImage='" + cheminImage + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
