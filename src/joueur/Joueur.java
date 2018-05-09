package joueur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Joueur implements Serializable {

    /**
     *Argent du joueur
     */
    private ArgentJoueur argent;

    /**
     *Score du joueur
     */
    private Score scoreJoueur;


    /**
     *Instancie un joueur, sans argent ni achat
     */
    public Joueur(){}

    /**
     *Instancie un joueur avec de l'argent et une liste d'achat
     *@param argent - argent du joueur

     */
    public Joueur(ArgentJoueur argent) {
        this.argent = argent;
        this.scoreJoueur.setScoreActuel(0);

    }

    /**
     *Instancie un joueur avec de l'argent, un score et une liste d'achat
     *@param argent - argent du joueur

     *@param scoreJoueur - score du joueur
     */
    public Joueur(ArgentJoueur argent, Score scoreJoueur) {
        this.argent = argent;
        this.scoreJoueur = scoreJoueur;

    }

    /**
     *Retourne la somme d'argent que le joueur possède
     *@return argent
     */
    public int getArgent() {
        return argent.getArgent();
    }

    /**
     *Retourne les scores du joueur
     *@return score
     */
    public int getScoreJoueur() {
        return scoreJoueur.getScoreActuel();
    }

    /**
     *Retourne la liste des achats du joueur
     *@return liste achat
     */


    /**
     *Set la somme d'argent que le joueur possède
     *@param argent - argent du joueur
     */
    public void setArgent(ArgentJoueur argent) {
        this.argent = argent;
    }

    /**
     *Set les scores du joueur
     *@param scoreJoueur - score du joueur
     */
    public void setScoreJoueur(Score scoreJoueur) {
        this.scoreJoueur = scoreJoueur;
    }

    /**
     *Set la liste d'achat du joueur
     *@param objetAchete - liste d'achat
     */

}
