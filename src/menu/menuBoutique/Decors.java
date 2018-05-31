package menu.menuBoutique;

import java.util.ArrayList;

public class Decors extends Item{

    private static ArrayList<Decors> listeDecors = new ArrayList<>();

    public static void initDecors(){
        listeDecors.add(new Decors("Décor 1", "img/decors/decor.png", "img/decors/decorGrand.png","Description", 100));
        listeDecors.add(new Decors("Décor 2", "img/decors/decor.png", "img/decors/decorGrand.png","Description", 100));
        listeDecors.add(new Decors("Décor 3", "img/decors/decor.png", "img/decors/decorGrand.png","Description", 100));
        listeDecors.add(new Decors("Décor 4", "img/decors/decor.png", "img/decors/decorGrand.png","Description", 100));
        listeDecors.add(new Decors("Décor 5", "img/decors/decor.png", "img/decors/decorGrand.png","Description", 100));
        listeDecors.add(new Decors("Décor 6", "img/decors/decor.png", "img/decors/decorGrand.png","Description", 100));
    }

    public static ArrayList<Decors> getListeDecors(){
        return listeDecors;
    }

    public Decors(String titre, String chemin, String cheminGrand, String description, int prix){
        super(titre, chemin, cheminGrand, description, prix);
    }

}
