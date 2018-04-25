package menu.menuBoutique;
import joueur.Joueur;
import java.util.List;

public class ModeleBoutique {
    //Le modele de la boutique est différent en fonction du joueur ( suivant les achats qu'il a fait ou non )
    private static List<Achat> objetBoutique;

    private Joueur joueur;
    private List<Achat> achat;

    public ModeleBoutique(){

    }

    public ModeleBoutique(Joueur joueur) {
        this.joueur = joueur;
        this.achetable();
    }

    public void achetable(){
        // Doit pouvoir acheter un objet X
        // Le resultat se stock dans "achat"
    }

}
