package menu.menuModele;

import javafx.scene.paint.Color;
import joueur.Joueur;
import menu.MenuPrincipal;
import menu.menuBoutique.Decors;
import menu.menuBoutique.Musique;
import menu.menuBoutique.Skin;

public class Modele {

    MenuPrincipal menuPrincipal;
    private Decors decorSelectionne;
    private Skin skinSelectionne;
    private Musique musiqueSelectionee;
    private Joueur j1;


    public Modele(){

        j1 = new Joueur(500,0,0);
        Decors.initDecors();
        decorSelectionne = Decors.getListeDecors().get(0);
        Skin.initSkin();
        skinSelectionne = Skin.getListSkins().get(0);
        menuPrincipal = new MenuPrincipal(this);

    }

    public Joueur getJ1() {
        return j1;
    }

    public Decors getDecorSelectionne() {
        return decorSelectionne;
    }

    public Skin getSkinSelectionne() {
        return skinSelectionne;
    }

    public Musique getMusiqueSelectionee() {
        return musiqueSelectionee;
    }

    public void setDecorSelectionne(Decors decorSelectionne) {
        this.decorSelectionne = decorSelectionne;
    }

    public void setSkinSelectionne(Skin skinSelectionne) {
        this.skinSelectionne = skinSelectionne;
    }

    public void setMusiqueSelectionee(Musique musiqueSelectionee) {
        this.musiqueSelectionee = musiqueSelectionee;
    }

    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }
}
