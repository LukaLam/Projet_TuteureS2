package menu.menuBoutique;

import java.util.ArrayList;

public class Skin extends Item{

    private static ArrayList<Skin> listSkins = new ArrayList<>();

    private String skinNom;

    public Skin(String titre, String chemin, String cheminGrand, String description, int prix, String skinNom) {
        super(titre, chemin, cheminGrand, description, prix);
        this.skinNom = skinNom;

    }

    public static void initSkin(){
        listSkins.add(new Skin("Défaut", "img/skins/skin_default_petit.png", "img/skins/skin_default.png", "Description", 0,
                "skin_default"
        ));
        listSkins.add(new Skin("Albinos", "img/skins/skin_albinos_petit.png", "img/skins/skin_albinos.png", "Description", 100,
                "skin_albinos"
        ));
        listSkins.add(new Skin("Bleu", "img/skins/skin_bleu_petit.png", "img/skins/skin_bleu.png", "Description", 1000,
                "skin_bleu"
        ));
        listSkins.add(new Skin("Jaune", "img/skins/skin_jaune_petit.png", "img/skins/skin_jaune.png", "Description", 1000,
                "skin_jaune"
        ));
        listSkins.add(new Skin("Rose", "img/skins/skin_rose_petit.png", "img/skins/skin_rose.png", "Une touche girly\npour votre snake", 1000,
                "skin_rose"
        ));
        listSkins.add(new Skin("Rouge orangé", "img/skins/skin_rougeorange_petit.png", "img/skins/skin_rougeorange.png", "Description", 1000,
                "skin_rouge"
        ));

    }

    public static void supprSkin(){
        listSkins.clear();
    }

    public static ArrayList<Skin> getListSkins() {
        return listSkins;
    }

    public String getSkinNom() {
        return skinNom;
    }

}
