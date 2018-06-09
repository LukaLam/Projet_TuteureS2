package menu.menuBoutique;

import java.util.ArrayList;

public class Decors extends Item{

    private static ArrayList<Decors> listeDecors = new ArrayList<>();

    public static void initDecors(){
        listeDecors.add(new Decors("Défaut", "img/decors/blanc_petit.jpg", "img/decors/blanc.jpg", "Description", 0));
        listeDecors.add(new Decors("Egoûts", "img/decors/egout_petit.jpg", "img/decors/egout.jpg","Description", 100));
        listeDecors.add(new Decors("Forêt", "img/decors/foret_petit.jpg", "img/decors/foret.jpg","Description", 100));
        listeDecors.add(new Decors("Caverne de glace", "img/decors/glace_petit.jpg", "img/decors/glace.jpg","Description", 100));
        listeDecors.add(new Decors("Caverne de lave", "img/decors/lave_petit.jpg", "img/decors/lave.jpg","Description", 100));
        listeDecors.add(new Decors("Oasis", "img/decors/oasis_petit.jpg", "img/decors/oasis.jpg","Description", 100));
        listeDecors.add(new Decors("Plage", "img/decors/plage_petit.jpg", "img/decors/plage.jpg","Description", 100));
    }

    public static void supprDecors(){
        listeDecors.clear();
    }

    public static ArrayList<Decors> getListeDecors(){
        return listeDecors;
    }

    public Decors(String titre, String chemin, String cheminGrand, String description, int prix){
        super(titre, chemin, cheminGrand, description, prix);
    }


}
