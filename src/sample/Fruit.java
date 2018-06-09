package sample;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;

public enum Fruit {
    POMME(5,"images/pomme.png"),
    MYRTILLE(10,"images/myrtille.png"),
    BANANE(15,"images/banane.png"),
    FRAMBOISE(20,"images/framboise.png"),
    FRAISE(50,"images/fraise.png");


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
        double valeurAleatoire = r.nextDouble();
        if (valeurAleatoire >=0 && valeurAleatoire <=0.4){
            return Fruit.values()[0];
        }else  if (valeurAleatoire >=0.401 && valeurAleatoire <=0.75){
            return Fruit.values()[1];
        }else if (valeurAleatoire >=0.751 && valeurAleatoire <=0.85){
            return Fruit.values()[2];
        }else if (valeurAleatoire >=0.851 && valeurAleatoire <=0.94){
            return Fruit.values()[3];
        }else if (valeurAleatoire >=0.941 && valeurAleatoire <=1){
            return Fruit.values()[4];
        }
        return null;
        //return Fruit.values()[valeurAleatoire];
    }

// fin test
}

