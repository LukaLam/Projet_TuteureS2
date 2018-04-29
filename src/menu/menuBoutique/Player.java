package menu.menuBoutique;

import java.util.ArrayList;

public class Player {
    protected int argent;
    protected ArrayList<Item> inventaire;

    public Player(int argent){
        this.argent = argent;
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

    public void acheterObjet(Item objet){
        inventaire.add(objet);
        argent -= objet.getPrix();
    }

    public boolean possedeObjet(Item objet){
        return inventaire.contains(objet);
    }
}
