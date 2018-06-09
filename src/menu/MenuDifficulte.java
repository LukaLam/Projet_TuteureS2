package menu;

import elementGrille.Mur;
import elementGrille.Position;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import jeux.Main;
import joueur.Joueur;
import menu.menuModele.Modele;
import sample.SnakeJeu;

import java.util.ArrayList;
import java.util.List;

import static sample.SnakeJeu.Hauteur;
import static sample.SnakeJeu.Largeur;

public class MenuDifficulte extends Scene {

    private Button buttonFacile,buttonMoyen,buttonDifficile;
    private double vitesse = 0.15 ;
    private int difficulty = 3;
    SnakeJeu snakeJeu;
    private Joueur j1;
   /* public MenuDifficulte(Parent root, Button buttonFacile, Button buttonMoyen, Button buttonDifficile, double vitesse) {
        super(root);
        this.buttonFacile = buttonFacile;
        this.buttonMoyen = buttonMoyen;
        this.buttonDifficile = buttonDifficile;
        this.vitesse = vitesse;
    }*/

    public MenuDifficulte(Modele modele) {
        super(new Group(),800,600);
        initAttribut(modele);
    }

    public void initAttribut(Modele modele) {
        this.j1 = j1;
/*
        snakeJeu = new SnakeJeu(getDifficulty());
*/

        Group root = (Group)this.getRoot();
        //root.setPrefSize(Largeur, Hauteur);

        buttonFacile = new Button();
        buttonFacile.setText("facile");
        buttonFacile.setLayoutX(350);
        buttonFacile.setLayoutY(Hauteur /2);
        buttonFacile.setOnAction(event -> {
            choixDifficultee();
            //setDifficulty(1);
            List<Position> lp = new ArrayList<>();
            SnakeJeu.lp= lp ;
            Main.getStage().setScene(new SnakeJeu(0.20,modele));

        });
        buttonMoyen = new Button();
        buttonMoyen.setText("normal");
        buttonMoyen.setLayoutX(350);
        buttonMoyen.setLayoutY(Hauteur /2+25);

        buttonMoyen.setOnAction(event -> {
            choixDifficultee();
            //setDifficulty(2);
            SnakeJeu sj = new SnakeJeu(0.15,modele);
            //ajout de quelques murs
            /*List<Position> lp = new ArrayList<>();
            List<Rectangle> lr = new ArrayList<>();
            lp.add(new Position(360,160));
            lp.add(new Position(360,200));
            lp.add(new Position(360,240));

            lp.add(new Position(360,320));
            lp.add(new Position(360,360));
            lp.add(new Position(360,400));


            for(Position p:lp){
                lr.add(new Rectangle(p.getAxeX(),p.getAxeY(),40,40));

            }
            for(Rectangle r:lr){
                r.setFill(new ImagePattern(new Image("images/mur.png")));
                sj.root.getChildren().add(r);
            }


            SnakeJeu.lp= lp ;*/

            Main.getStage().setScene(sj);



        });
        buttonDifficile = new Button();
        buttonDifficile.setText("difficile");
        buttonDifficile.setLayoutX(350);
        buttonDifficile.setLayoutY(Hauteur /2+50);
        buttonDifficile.setOnAction(event -> {
            choixDifficultee();
            //snakeJeu.setDifficulty(2);
            /*List<Position> lp = new ArrayList<>();
            List<Rectangle> lr = new ArrayList<>();
            //ajout de quelques murs
            lp.add(new Position(400,480));
            lp.add(new Position(400,520));
            lp.add(new Position(400,560));
            lp.add(new Position(760,0));
            lp.add(new Position(760,40));
            lp.add(new Position(760,80));
            lp.add(new Position(160,240));
            lp.add(new Position(160,280));
            lp.add(new Position(160,320));
            lp.add(new Position(320,0));
            lp.add(new Position(320,40));
            lp.add(new Position(320,80));
            lp.add(new Position(680,240));
            lp.add(new Position(680,280));
            lp.add(new Position(680,320));
            lp.add(new Position(360,280));
            lp.add(new Position(400,280));
            lp.add(new Position(440,280));


            for(Position p:lp){
                lr.add(new Rectangle(p.getAxeX(),p.getAxeY(),40,40));



            }

            for(Rectangle r:lr){
                r.setFill(new ImagePattern(new Image("images/mur.png")));
                sj.root.getChildren().add(r);
            }


            SnakeJeu.lp= lp ;*/
            SnakeJeu sj = new SnakeJeu(0.10,modele);

            Main.getStage().setScene(sj);


        });
        buttonFacile.setVisible(true);
        buttonMoyen.setVisible(true);
        buttonDifficile.setVisible(true);

        root.getChildren().addAll(buttonFacile,buttonMoyen,buttonDifficile);


    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void choixDifficultee(){
        buttonFacile.setVisible(false);
        buttonMoyen.setVisible(false);
        buttonDifficile.setVisible(false);
    }
}