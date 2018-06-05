package menu;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import jeux.Main;
import sample.SnakeJeu;

import static sample.SnakeJeu.Hauteur;
import static sample.SnakeJeu.Largeur;

public class MenuDifficulte extends Scene {

    private Button buttonFacile,buttonMoyen,buttonDifficile;

    private double vitesse = 0.15 ;
    private int difficulty = 3;
    SnakeJeu snakeJeu;
   /* public MenuDifficulte(Parent root, Button buttonFacile, Button buttonMoyen, Button buttonDifficile, double vitesse) {
        super(root);
        this.buttonFacile = buttonFacile;
        this.buttonMoyen = buttonMoyen;
        this.buttonDifficile = buttonDifficile;
        this.vitesse = vitesse;
    }*/

    public MenuDifficulte() {
        super(new Group(),800,600);
        initAttribut();
    }

    public void initAttribut() {
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
            Main.getStage().setScene(new SnakeJeu(0.20));

        });
        buttonMoyen = new Button();
        buttonMoyen.setText("normal");
        buttonMoyen.setLayoutX(350);
        buttonMoyen.setLayoutY(Hauteur /2+25);
        buttonMoyen.setOnAction(event -> {
            choixDifficultee();
            //setDifficulty(2);
            Main.getStage().setScene(new SnakeJeu(0.15));



        });
        buttonDifficile = new Button();
        buttonDifficile.setText("difficile");
        buttonDifficile.setLayoutX(350);
        buttonDifficile.setLayoutY(Hauteur /2+50);
        buttonDifficile.setOnAction(event -> {
            choixDifficultee();
            //snakeJeu.setDifficulty(2);
            Main.getStage().setScene(new SnakeJeu(0.05));


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