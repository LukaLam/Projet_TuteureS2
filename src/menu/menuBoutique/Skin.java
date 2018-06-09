package menu.menuBoutique;

import java.util.ArrayList;

public class Skin extends Item{

    private static ArrayList<Skin> listSkins = new ArrayList<>();

    private String tete1, tete2, tete3, tete4;

    private String queue1,queue2,queue3,queue4;

    private String coin1, coin2, coin3, coin4;

    private String horizontal, vertical;

    public Skin(String titre, String chemin, String cheminGrand, String description, int prix, String tete1, String tete2, String tete3, String tete4, String queue1, String queue2, String queue3, String queue4, String coin1, String coin2, String coin3, String coin4, String horizontal, String vertical) {
        super(titre, chemin, cheminGrand, description, prix);
        this.tete1 = tete1;
        this.tete2 = tete2;
        this.tete3 = tete3;
        this.tete4 = tete4;
        this.queue1 = queue1;
        this.queue2 = queue2;
        this.queue3 = queue3;
        this.queue4 = queue4;
        this.coin1 = coin1;
        this.coin2 = coin2;
        this.coin3 = coin3;
        this.coin4 = coin4;
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public static void initSkin(){
        listSkins.add(new Skin("Défaut", "img/skins/skin_default_petit.png", "img/skins/skin_default.png", "Description", 0,
                "skin_default/tete1.png", "skin_default/tete2.png", "skin_default/tete3.png", "skin_default/tete4.png",
                "skin_default/queue1.png","skin_default/queue2.png", "skin_default/queue3.png", "skin_default/queue4.png",
                "skin_default/coin1.png", "skin_default/coin2.png", "skin_default/coin3.png", "skin_default/coin4.png",
                "skin_default/horizontal.png", "skin_default/vertical.png"
        ));
        listSkins.add(new Skin("Albinos", "img/skins/skin_albinos_petit.png", "img/skins/skin_albinos.png", "Description", 100,
                "skin_albinos/tete1.png", "skin_albinos/tete2.png", "skin_albinos/tete3.png", "skin_albinos/tete4.png",
                "skin_albinos/queue1.png","skin_albinos/queue2.png", "skin_albinos/queue3.png", "skin_albinos/queue4.png",
                "skin_albinos/coin1.png", "skin_albinos/coin2.png", "skin_albinos/coin3.png", "skin_albinos/coin4.png",
                "skin_albinos/horizontal.png", "skin_albinos/vertical.png"
        ));
        listSkins.add(new Skin("Bleu", "img/skins/skin_bleu_petit.png", "img/skins/skin_bleu.png", "Description", 1000,
                "skin_bleu/tete1.png", "skin_bleu/tete2.png", "skin_bleu/tete3.png", "skin_bleu/tete4.png",
                "skin_bleu/queue1.png","skin_bleu/queue2.png", "skin_bleu/queue3.png", "skin_bleu/queue4.png",
                "skin_bleu/coin1.png", "skin_bleu/coin2.png", "skin_bleu/coin3.png", "skin_bleu/coin4.png",
                "skin_bleu/horizontal.png", "skin_bleu/vertical.png"
        ));
        listSkins.add(new Skin("Jaune", "img/skins/skin_jaune_petit.png", "img/skins/skin_jaune.png", "Description", 1000,
                "skin_jaune/tete1.png", "skin_jaune/tete2.png", "skin_jaune/tete3.png", "skin_jaune/tete4.png",
                "skin_jaune/queue1.png","skin_jaune/queue2.png", "skin_jaune/queue3.png", "skin_jaune/queue4.png",
                "skin_jaune/coin1.png", "skin_jaune/coin2.png", "skin_jaune/coin3.png", "skin_jaune/coin4.png",
                "skin_jaune/horizontal.png", "skin_jaune/vertical.png"
        ));
        listSkins.add(new Skin("Rose", "img/skins/skin_rose_petit.png", "img/skins/skin_rose.png", "Une touche girly\npour votre snake", 1000,
                "skin_rose/tete1.png", "skin_rose/tete2.png", "skin_rose/tete3.png", "skin_rose/tete4.png",
                "skin_rose/queue1.png","skin_rose/queue2.png", "skin_rose/queue3.png", "skin_rose/queue4.png",
                "skin_rose/coin1.png", "skin_rose/coin2.png", "skin_rose/coin3.png", "skin_rose/coin4.png",
                "skin_rose/horizontal.png", "skin_rose/vertical.png"
        ));
        listSkins.add(new Skin("Rouge orangé", "img/skins/skin_rougeorange_petit.png", "img/skins/skin_rougeorange.png", "Description", 1000,
                "skin_rouge/tete1.png", "skin_rouge/tete2.png", "skin_rouge/tete3.png", "skin_rouge/tete4.png",
                "skin_rouge/queue1.png","skin_rouge/queue2.png", "skin_rouge/queue3.png", "skin_rouge/queue4.png",
                "skin_rouge/coin1.png", "skin_rouge/coin2.png", "skin_rouge/coin3.png", "skin_rouge/coin4.png",
                "skin_rouge/horizontal.png", "skin_rouge/vertical.png"
        ));

    }

    public static void supprSkin(){
        listSkins.clear();
    }

    public static ArrayList<Skin> getListSkins() {
        return listSkins;
    }

    public String getTete1() {
        return tete1;
    }

    public String getTete2() {
        return tete2;
    }

    public String getTete3() {
        return tete3;
    }

    public String getTete4() {
        return tete4;
    }

    public String getQueue1() {
        return queue1;
    }

    public String getQueue2() {
        return queue2;
    }

    public String getQueue3() {
        return queue3;
    }

    public String getQueue4() {
        return queue4;
    }

    public String getCoin1() {
        return coin1;
    }

    public String getCoin2() {
        return coin2;
    }

    public String getCoin3() {
        return coin3;
    }

    public String getCoin4() {
        return coin4;
    }

    public String getHorizontal() {
        return horizontal;
    }

    public String getVertical() {
        return vertical;
    }
}
