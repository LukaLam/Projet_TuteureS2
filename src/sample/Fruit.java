package sample;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;

public enum Fruit {
    POMME(5,"pomme.png"),
    MYRTILLE(10,"myrtille.png"),
    BANANE(15,"banane.png"),
    FRAMBOISE(20,"framboise.png"),
    FRAISE(50,"fraise.png");


        private int valeurFruit;
        private Image imageFruit;

    Fruit(int valeurFruit, String couleurFruit) {
        this.valeurFruit = valeurFruit;
        this.imageFruit = new Image(couleurFruit);
    }


    public int getValeurFruit() {
        return valeurFruit;
    }

    public Image getImageFruit() {
        return imageFruit;
    }

    public static Fruit getRandomFruit(){
        Random r = new Random();
        int valeurAleatoire = r.nextInt(5);
        return Fruit.values()[valeurAleatoire];
    }


}

