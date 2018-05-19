package menu.menuBoutique;

import java.util.ArrayList;

public class Skin extends Item{

    private static ArrayList<Skin> listSkins = new ArrayList<>();

    public Skin(String titre, String chemin, String cheminGrand, String description, int prix) {
        super(titre, chemin, cheminGrand, description, prix);
    }

    public static void initSkin(){
        listSkins.add(new Skin("Skin 1", "img/skins/moustache.jpg", "img/skins/moustache_grand.jpg", "Description", 200));
    }

    public static ArrayList<Skin> getListSkins() {
        return listSkins;
    }
}
