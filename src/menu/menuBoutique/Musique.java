package menu.menuBoutique;

import java.util.ArrayList;

public class Musique extends Item{

    private static ArrayList<Musique> listeMusique = new ArrayList<>();

    public Musique(String titre, String chemin, String cheminGrand, String description, int prix) {
        super(titre, chemin, cheminGrand, description, prix);
    }

    public static void initMusique(){
        listeMusique.add(new Musique("Musique 1", "src/music/royalty_8bit.wav", null, "Description", 50));
    }

    public static ArrayList<Musique> getListeMusique(){
        return listeMusique;
    }
}
