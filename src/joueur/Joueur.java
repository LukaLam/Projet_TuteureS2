package joueur;

import menu.menuBoutique.Decors;
import menu.menuBoutique.Item;
import menu.menuBoutique.Skin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Joueur implements Serializable {

    private int argent;
    private int score;
    private ArrayList<Item> inventaire = new ArrayList<>();

    public Joueur(int argent) {
        this.argent = argent;
        score = 0;
        Decors.initDecors();
        Skin.initSkin();
        inventaire.add(Decors.getListeDecors().get(0));
        inventaire.add(Skin.getListSkins().get(0));
        Decors.supprDecors();
        Skin.supprSkin();

    }

    public int getArgent() {
        return argent;
    }

    public int getScoreJoueur() {
        return score;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public void setScore(int score) {
        this.score = score;
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

}
