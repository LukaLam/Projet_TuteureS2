package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import joueur.Joueur;
import menu.menuModele.Modele;

import java.util.ArrayList;
import java.util.List;

public class ControlImage extends AnimationTimer
{
    SnakeJeu jeu;
    int dirTete;
    int dirQueue;
    Joueur joueur;
    Modele modele;
    String skinSnake;
    /*
    1=haut
    2=droite
    3=gauche
    4=bas
     */
    List<int[]> coin;

    public ControlImage(SnakeJeu jeu, Modele modele)
    {
        this.joueur = modele.getJ1();
        this.jeu=jeu;
        this.modele=modele;
        skinSnake = modele.getSkinSelectionne().getSkinNom();
        newGame();
    }

    public void newGame()
    {
        dirTete=2;
        dirQueue=2;
        coin = new ArrayList<>();
    }

    @Override
    public void handle(long now) 
    {
        updateImage();
    }

    public void addCoin(int dir)
    {
        coin.add(new int[]{dir, 0});
    }

    public void updateImage()
    {
        for(Node n : jeu.snake)
        {
          //  joueur.
           //String dir = "skin_default/";
            String dir = skinSnake+"/";

            if (jeu.snake.indexOf(n)==0)
                ((Rectangle)n).setFill(new ImagePattern(new Image(dir+"tete"+dirTete+".png")));
            else if (jeu.snake.indexOf(n)==jeu.snake.size()-1)
                ((Rectangle)n).setFill(new ImagePattern(new Image(dir+"queue"+dirQueue+".png")));
            else for (int[] i : coin)
                {
                    if (jeu.snake.indexOf(n)==i[2])
                    {
                        int dirCoin=0;
                        /*
                        dirCoin :
                        1=bas/droite
                        2=bas/gauche
                        3=haut/droite
                        4=haut/gauche
                         */
                        /*
                        pour i[0] et i[1] :
                        1=haut
                        2=bas
                        3=droite
                        4=gauche
                         */
                        if ((i[0]==1 && i[1]==3))
                            dirCoin=2;
                        else if ((i[0]==1 && i[1]==4))
                            dirCoin=1;
                        else if ((i[0]==2 && i[1]==3))
                            dirCoin=4;
                        else if ((i[0]==2 && i[1]==4))
                            dirCoin=3;

                        else if((i[0]==3 && i[1]==1))
                            dirCoin=3;
                        else if((i[0]==4 && i[1]==2))
                            dirCoin=2;
                        else if((i[0]==3 && i[1]==2))
                            dirCoin=1;
                        else if((i[0]==4 && i[1]==1))
                            dirCoin=4;
                        ((Rectangle)n).setFill(new ImagePattern(new Image(dir+"coin"+dirCoin+".png")));
                    }
                    /*else
                        for(int[] y : coin)
                        {
                            System.out.println("pas de coin : ["+y[0]+","+y[1]+","+y[2]+"]"+"\n" +
                                    "taille tab coin : "+coin.size());
                        }*/
                }
        }
    }

    public void updateCoin()
    {
        for(int[] i : coin)
        {
            int index = coin.indexOf(i);
            i[2]++;
            coin.set(index, i);
        }
    }
}