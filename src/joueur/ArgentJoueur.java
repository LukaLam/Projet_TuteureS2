package joueur;

import java.io.Serializable;

public class ArgentJoueur implements Serializable {
    private int argent;

    public ArgentJoueur(){
    }

    public ArgentJoueur(int argent) {
        this.argent = argent;
    }

    public boolean argentSuffisant(int somme){
            if (somme<=argent){
                return true;
            }
            return false;
    }

    public int getArgent() {
        return argent;
    }

    public void ajoutArgent(int somme){
        // On ajoute X argent au joueur
        argent += somme;
    }

    public void retraitArgent(int somme){
        // On retire X argent au joueur
        argent -= somme;
    }


}
