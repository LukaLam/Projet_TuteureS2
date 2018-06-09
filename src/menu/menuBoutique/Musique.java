package menu.menuBoutique;

import java.util.ArrayList;

public class Musique extends Item{

    private static ArrayList<Musique> listeMusique = new ArrayList<>();

    public Musique(String titre, String chemin, String cheminGrand, String description, int prix) {
        super(titre, chemin, cheminGrand, description, prix);
    }

    public static void initMusique(){
        //Credits : FranzPlays
        listeMusique.add(new Musique("Royalty", "resources/music/royalty_8bit.wav", null, "Description", 50));
        //Credits : Matthew Klingensmith
        listeMusique.add(new Musique("Left Right Exluded", "resources/music/leftrightexcluded.wav", null, "Description", 50));
        //Credits : Matthew Klingensmith
        listeMusique.add(new Musique("Boss A Nova", "resources/music/boss_a_nova.wav", null, "Description", 50));
        //Credits : Syncopika
        listeMusique.add(new Musique("Copycat", "resources/music/copycat.wav", null, "Description", 50));
        //Credits : Elerya
        listeMusique.add(new Musique("Nomads", "resources/music/nomads.wav", null, "Description", 50));
        //Credits : Elerya
        listeMusique.add(new Musique("Liyan", "resources/music/liyan.wav", null, "Description", 50));
    }

    public static ArrayList<Musique> getListeMusique(){
        return listeMusique;
    }
}
