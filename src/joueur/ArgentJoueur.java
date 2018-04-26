package joueur;

import java.io.Serializable;

public class ArgentJoueur implements Serializable {
    private int argent;
    private boolean test;

    public ArgentJoueur(){
    }

    public ArgentJoueur(int argent) {
        this.argent = argent;
    }

    public boolean argentSuffisant(int somme){
            //Verifie si l'argent du joueur est suffisante pour acheter
        return test;
    }

    public int getArgent() {
        return argent;
    }

    public void ajoutArgent(int somme){
        // On ajoute X argent au joueur
    }

    public void retraitArgent(int somme){
        // On retire X argent au joueur
    }


}
