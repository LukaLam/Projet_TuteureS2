package joueur;

import menu.menuBoutique.Decors;
import menu.menuBoutique.Item;
import menu.menuBoutique.Skin;
import menu.menuModele.Modele;

import java.io.Serializable;
import java.util.ArrayList;


public class Joueur implements Serializable {

    private int argent;

    private ArrayList<Item> inventaire = new ArrayList<>();
    private  int scoreJoueur;
    private  int meilleureScore;


    public Joueur(int argent,int scoreJoueur,int meilleureScore) {

        this.argent = argent;
        this.scoreJoueur = scoreJoueur;
        this.meilleureScore = meilleureScore;

     /*   Decors.initDecors();
       Skin.initSkin();
        inventaire.add(Decors.getListeDecors().get(0));
        inventaire.add(Skin.getListSkins().get(0));
        Decors.supprDecors();
       Skin.supprSkin();*/

    }



    public int getArgent() {
        return argent;
    }


    public void setArgent(int argent) {
        this.argent = argent;
    }


    public ArrayList<Item> getInventaire() {
        return inventaire;
    }

    public void setInventaire(ArrayList<Item> inventaire) {
        this.inventaire = inventaire;
    }

    public boolean possedeObjet(Item objet){
        return inventaire.contains(objet);
    }

    public void acheterObjet(Item objet){
        inventaire.add(objet);
        argent -= objet.getPrix();
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

