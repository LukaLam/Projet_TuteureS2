package joueur;
import menu.menuBoutique.Achat;
import java.io.Serializable;
import java.util.List;

public class Joueur implements Serializable {
    private ArgentJoueur argent;
    private Score scoreJoueur;
    private List<Achat> objetAchete;

    public Joueur(){

    }

    public Joueur(ArgentJoueur argent, List<Achat> objetAchete) {
        this.argent = argent;
        this.scoreJoueur = 0;
        this.objetAchete = objetAchete;
    }

    public Joueur(ArgentJoueur argent, Score scoreJoueur, List<Achat> objetAchete) {
        this.argent = argent;
        this.scoreJoueur = scoreJoueur;
        this.objetAchete = objetAchete;
    }

    public int getArgent() {
        return argent.getArgent();
    }

    public int getScoreJoueur() {
        return scoreJoueur.getScoreActuel();
    }

    public List<Achat> getObjetAchete() {
        return objetAchete;
    }
}