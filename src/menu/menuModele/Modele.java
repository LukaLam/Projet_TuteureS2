package menu.menuModele;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import joueur.Joueur;
import menu.MenuPrincipal;
import menu.menuBoutique.Decors;
import menu.menuBoutique.Musique;
import menu.menuBoutique.Skin;

import java.io.File;

public class Modele {

    MenuPrincipal menuPrincipal;
    private Decors decorSelectionne;
    private Skin skinSelectionne;
    private Musique musiqueSelectionee;
    private Joueur j1;
    public boolean test=false;
    public MediaPlayer player;
    public File file;
    public Media media;


    public Modele(){

        j1 = new Joueur(500,0,0);
        Decors.initDecors();
        decorSelectionne = Decors.getListeDecors().get(0);
        Decors.supprDecors();
        Skin.initSkin();
        skinSelectionne = Skin.getListSkins().get(0);
        Skin.supprSkin();
        musiqueSelectionee= null;
        menuPrincipal = new MenuPrincipal(this);


    }

    public Joueur getJ1() {
        return j1;
    }

    public void attribuerMusic(){
        file = new File(getMusiqueSelectionee().getChemin());
        media = new Media(file.toURI().toString());
        player = new MediaPlayer(media);
        test=true;
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
