package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Joueur implements Serializable {


    private  int scoreJoueur;
    private  int meilleureScore;
    private int argent;

    public Joueur() {
    }

    public Joueur(int argent, int scoreJoueur, int meilleureScore) {
        this.argent = argent;
        this.scoreJoueur = scoreJoueur;
        this.meilleureScore = meilleureScore;
    }

    public int getScoreJoueur() {
        return scoreJoueur;
    }

    public void setScoreJoueur(int scoreJoueur) {
        this.scoreJoueur = scoreJoueur;
    }

    public int getMeilleureScore() {
        return meilleureScore;
    }

    public void setMeilleureScore(int meilleureScore) {
        this.meilleureScore = meilleureScore;
    }
    public void ajoutArgent(int somme){
        argent+= somme;
    }

    public void retraitArgent(int somme){
        argent-= somme;
    }


}
