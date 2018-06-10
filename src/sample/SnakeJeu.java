package sample;

import elementGrille.Position;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import jeux.Main;
import menu.MenuPrincipal;
import joueur.Joueur;
import menu.menuModele.Modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class SnakeJeu extends Scene {
    public static List<Position> lp = new ArrayList<>();
    public static final int Taille_Bloc = 40;
    public static final int Largeur = 20 * Taille_Bloc;
    public static final int Hauteur = 15 * Taille_Bloc;
    public Pane root;
    private double difficulte=0.15;


    private Direction direction = Direction.RIGHT;
    private boolean moved = false;
    private boolean running = false;

    //Ajout des variables pour le menu pause
    private boolean pause = false ;
    private boolean canPause = true;
    private Button resumeButton;


    //Variables game over
    private Button gameOverButton;
    private Label gameOverText;
    private Label scoreLabel;
    private Label highScoreLabel;

    private Button mainMenuButton;

    private Timeline timeline = new Timeline();
    public ObservableList<Node> snake;
    private Modele modele;


    private int highScore = 0;
    private int scoreTemporaire=0;

    private Fruit fruitEnum;
    private Rectangle fruit;
   // Scene scene;
   private ControlImage controlImage;
   private Joueur joueur;

    FileReader fileReader;
    String fichierScore;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;

    public SnakeJeu(double frame,Modele modele) {
        super(new Group(),800,600);
        this.modele = modele;
        joueur = modele.getJ1();
        this.difficulte=frame;
        ajouterAddAtribut();
        addKeyboardEvent();

    }

    private Parent initAttribut() {
        root = new Pane();
        root.setPrefSize(Largeur, Hauteur);
        String background = modele.getDecorSelectionne().getCheminGrand();
        root.setBackground(new Background(new BackgroundImage(new Image((background)),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,BackgroundSize.DEFAULT)));


        Group snakeBody = new Group();
        snake = snakeBody.getChildren();

        controlImage = new ControlImage(this,modele);
        controlImage.start();

        try {
            fichierScore = "./resources/scores/scores.txt";
            fileReader = new FileReader(fichierScore);
            bufferedReader = new BufferedReader(fileReader);
            highScore = Integer.parseInt(bufferedReader.readLine());

        }catch (Exception e){
            System.out.println("erreur lecture fichier");
        }



         fruit = new Rectangle(
                Taille_Bloc, Taille_Bloc
        );
        fruitEnum = fruitEnum.getRandomFruit();
//        fruit.setArcWidth(20);
//        fruit.setArcHeight(20);
        fruit.setFill(new ImagePattern(fruitEnum.getImageFruit()));


        /*  Rectangle fruit = new Rectangle(Taille_Bloc, Taille_Bloc);*/




        //    fruit.setFill(fruitEnum.getCouleurFruit());
        fruit.setTranslateX((int)((Math.random()) * Largeur - Taille_Bloc) / Taille_Bloc * Taille_Bloc); // les gens le : -Block_size permet de rester dans la grille si jamais
        fruit.setTranslateY((int)((Math.random() )* Hauteur - Taille_Bloc) / Taille_Bloc * Taille_Bloc);
        if(fruit.getTranslateX()==0 && fruit.getTranslateY()==0){
            fruit.setTranslateX((int) (Math.random() * (Largeur - Taille_Bloc)) / Taille_Bloc * Taille_Bloc);
            fruit.setTranslateY((int) (Math.random() * (Hauteur - Taille_Bloc)) / Taille_Bloc * Taille_Bloc);
        }
        // si le fruit apparait sur le mur ou le snake recrée un fruit
        while(isSurMur() || isSurSnake()) {
            fruit.setTranslateX((int) (Math.random() * (Largeur - Taille_Bloc)) / Taille_Bloc * Taille_Bloc);
            fruit.setTranslateY((int) (Math.random() * (Hauteur - Taille_Bloc)) / Taille_Bloc * Taille_Bloc);
        }





        KeyFrame frame = new KeyFrame(Duration.seconds(difficulte), event -> { // Si on baisse le 0.1, cela augmente la difficulté  vu que le snake ira plus vite ( oué je sais c'est logique xD )
            if(!running){
                return;
            }
            //System.out.println("vitesse :"+vitesse);

            boolean toRemove = snake.size()>1;
            Node QueuSnake = toRemove ? snake.remove(snake.size()-1) : snake.get(0); // regarder la synthaxe d'une condition ternaire sur internet puisque c'est chiant a expliquer

            double tailX = QueuSnake.getTranslateX();
            double tailY = QueuSnake.getTranslateY();
            String directory= modele.getSkinSelectionne().getSkinNom();

            switch(direction){
                case UP:
                    QueuSnake.setTranslateX(snake.get(0).getTranslateX());
                    QueuSnake.setTranslateY(snake.get(0).getTranslateY() - Taille_Bloc);
                    ((Rectangle)snake.get(0)).setFill(new ImagePattern(new Image(directory+"/vertical.png")));
                    break;
                case DOWN :
                    QueuSnake.setTranslateX(snake.get(0).getTranslateX());
                    QueuSnake.setTranslateY(snake.get(0).getTranslateY() + Taille_Bloc);
                    ((Rectangle)snake.get(0)).setFill(new ImagePattern(new Image(directory+"/vertical.png")));
                    break;
                case LEFT:
                    QueuSnake.setTranslateX(snake.get(0).getTranslateX()- Taille_Bloc);
                    QueuSnake.setTranslateY(snake.get(0).getTranslateY());
                    ((Rectangle)snake.get(0)).setFill(new ImagePattern(new Image(directory+"/horizontal.png")));
                    break;
                case RIGHT :
                    QueuSnake.setTranslateX(snake.get(0).getTranslateX()+ Taille_Bloc);
                    QueuSnake.setTranslateY(snake.get(0).getTranslateY());
                    ((Rectangle)snake.get(0)).setFill(new ImagePattern(new Image(directory+"/horizontal.png")));
                    break;
            }
            int dirQueue=0;
            /*
            1=bout a gauche
            2=bout a droite
            3=bout en bas
            4=bout en haut
             */
            if (snake.size()>=2)
            {
                //normalement enlevable si le snake commence avec deux ou trois morceaux, trois étant l'idéal niveau graphique
                if (snake.get(snake.size()-2).getTranslateX()>snake.get(snake.size()-1).getTranslateX())
                    dirQueue=2;
                else if (snake.get(snake.size()-2).getTranslateX()<snake.get(snake.size()-1).getTranslateX())
                    dirQueue=3;
                else if (snake.get(snake.size()-2).getTranslateY()>snake.get(snake.size()-1).getTranslateY())
                    dirQueue=4;
                else if (snake.get(snake.size()-2).getTranslateY()<snake.get(snake.size()-1).getTranslateY())
                    dirQueue=1;
                controlImage.dirQueue=dirQueue;
            }
            moved = true;
            if(toRemove==true){
                snake.add(0,QueuSnake);
            }

            for(Position rect : lp){
                if(rect.getAxeX() == snake.get(0).getTranslateX() && rect.getAxeY() == snake.get(0).getTranslateY()){
                    joueur.setScoreJoueur(scoreTemporaire);
                    if (joueur.getMeilleureScore() < scoreTemporaire){
                        joueur.setMeilleureScore(scoreTemporaire);
                    }

                    System.out.println(scoreTemporaire);
                    System.out.println(joueur.getMeilleureScore());
                    //scoreTemporaire=0;

                    gameOver();
                    break;
                }
            }
            //collision
            for(Node rect : snake){
                if(rect != QueuSnake && QueuSnake.getTranslateX() == rect.getTranslateX() && QueuSnake.getTranslateY() == rect.getTranslateY()){
                    joueur.setScoreJoueur(scoreTemporaire);
                    if (joueur.getMeilleureScore() < scoreTemporaire){
                        joueur.setMeilleureScore(scoreTemporaire);
                    }

                    System.out.println(scoreTemporaire);
                    System.out.println(joueur.getMeilleureScore());
                    //scoreTemporaire=0;

                    gameOver();
                    break;
                }
            }
            if(QueuSnake.getTranslateX()<0 || QueuSnake.getTranslateX() >= Largeur || QueuSnake.getTranslateY() <0 || QueuSnake.getTranslateY() >= Hauteur){
                //Tu peux reprendre le score temporaire ici dans ton game over
                joueur.setScoreJoueur(scoreTemporaire);
                if (joueur.getMeilleureScore() < scoreTemporaire){
                    joueur.setMeilleureScore(scoreTemporaire);
                }
                System.out.println(scoreTemporaire);
                System.out.println(joueur.getMeilleureScore());
                //scoreTemporaire=0;

                gameOver();
            }
            if(snake.size()<=2){
                Rectangle rectangle = new Rectangle(Taille_Bloc, Taille_Bloc);
                rectangle.setTranslateX(tailX);
                rectangle.setTranslateY(tailY);
                //  rect.setFill(new ImagePattern(new Image("images/test.png")));

                snake.add(rectangle);
            }

            if(QueuSnake.getTranslateX()==fruit.getTranslateX() && QueuSnake.getTranslateY() == fruit.getTranslateY()){


                fruit.setTranslateX((int)(Math.random() * (Largeur - Taille_Bloc)) / Taille_Bloc * Taille_Bloc);
                fruit.setTranslateY((int)(Math.random() * (Hauteur - Taille_Bloc)) / Taille_Bloc * Taille_Bloc);


                // fruit apparait a la position d'un mur ou du snake recrée un fruit
                while(isSurMur() || isSurSnake()) {
                    fruit.setTranslateX((int) (Math.random() * (Largeur - Taille_Bloc)) / Taille_Bloc * Taille_Bloc);
                    fruit.setTranslateY((int) (Math.random() * (Hauteur - Taille_Bloc)) / Taille_Bloc * Taille_Bloc);
                }


                Rectangle rect = new Rectangle(Taille_Bloc, Taille_Bloc);
                //J'ajoute le score ici
                scoreTemporaire+=fruitEnum.getValeurFruit()*(1/difficulte);
                fruitEnum = fruitEnum.getRandomFruit();

                fruit.setFill(new ImagePattern(fruitEnum.getImageFruit()));

                joueur.ajoutArgent(fruitEnum.getValeurFruit());

                rect.setTranslateX(tailX);
                rect.setTranslateY(tailY);
              //  rect.setFill(new ImagePattern(new Image("images/test.png")));

                snake.add(rect);
            }
            controlImage.updateCoin();
        });

        //difficultée

        /*buttonFacile = new Button();
        buttonFacile.setText("facile");
        buttonFacile.setLayoutX(350);
        buttonFacile.setLayoutY(Hauteur /2);
        buttonFacile.setOnAction(event -> {
            choixDifficultee();
            vitesse=0.20;

        });
        buttonMoyen = new Button();
        buttonMoyen.setText("normal");
        buttonMoyen.setLayoutX(350);
        buttonMoyen.setLayoutY(Hauteur /2+25);
        buttonMoyen.setOnAction(event -> {
            choixDifficultee();
            vitesse=0.15;


        });
        buttonDifficile = new Button();
        buttonDifficile.setText("difficile");
        buttonDifficile.setLayoutX(350);
        buttonDifficile.setLayoutY(Hauteur /2+50);
        buttonDifficile.setOnAction(event -> {
            choixDifficultee();
            vitesse=0.05;


        });*/


        if(difficulte == 0.15){
            //List<Position> lpN = new ArrayList<>();
            List<Rectangle> lr = new ArrayList<>();
            lp = new ArrayList<>();
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
                root.getChildren().add(r);
            }

        }else if(difficulte == 0.10){
            List<Rectangle> lr = new ArrayList<>();
            lp = new ArrayList<>();
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
                root.getChildren().add(r);
            }
        }

        //creation du bouton, texte et de l'event pour le gameover
        gameOverText = new Label("Game Over ! essaye encore !");
        gameOverText.setTextFill(Color.CADETBLUE);
        gameOverText.setLayoutX(350);
        gameOverText.setLayoutY(50);
        gameOverText.setScaleX(4);
        gameOverText.setScaleY(4);

        scoreLabel = new Label();
        scoreLabel.setText("score : "+ scoreTemporaire);
        scoreLabel.setTextFill(Color.DARKRED);
        scoreLabel.setLayoutX(350);
        scoreLabel.setLayoutY(140);
        scoreLabel.setScaleX(2);
        scoreLabel.setScaleY(2);

        highScoreLabel = new Label("le meilleur score est : "+highScore);
        highScoreLabel.setTextFill(Color.DARKRED);
        highScoreLabel.setLayoutX(350);
        highScoreLabel.setLayoutY(180);
        highScoreLabel.setScaleX(1.5);
        highScoreLabel.setScaleY(1.5);

        gameOverButton = new Button();
        gameOverButton.setText("Restart the game");
        gameOverButton.setLayoutX(300);
        gameOverButton.setLayoutY(Hauteur /2);
        gameOverButton.setScaleX(2);
        gameOverButton.setScaleY(2);
        gameOverButton.setOnAction(event -> {
            restartGame();
            highScoreLabel.setVisible(false);
            mainMenuButton.setVisible(false);
            gameOverButton.setVisible(false);
            scoreLabel.setVisible(false);
            gameOverText.setVisible(false);
        });
        mainMenuButton = new Button();
        mainMenuButton.setText("Retour au menu principal");
        mainMenuButton.setLayoutX(300);
        mainMenuButton.setLayoutY(Hauteur /2+80);
        mainMenuButton.setScaleX(2);
        mainMenuButton.setScaleY(2);
        mainMenuButton.setOnAction(event -> {
            //mainMenuButton.setVisible(false);
           /* if(!modele.test) {
                modele.player.stop();
                modele.player.setAutoPlay(false);
            }*/
           if(modele.test) {
               System.out.println(modele.player.getStatus());
               String status =modele.player.getStatus().toString();
               if (status.equals("PLAYING")) {
                   System.out.println("BITE");
                   modele.player.stop();
                   modele.test = false;
               }
           }
            Main.getStage().setScene(new MenuPrincipal(modele));


        });

        //creation du bouton et de l'event pour le resume
        resumeButton = new Button();
        resumeButton.setText("Resume");
        resumeButton.setLayoutX(370);
        resumeButton.setLayoutY(Hauteur /2);
        resumeButton.setOnAction(event -> {
            timeline.play();
            resumeButton.setVisible(false);
        });



        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
/*
        buttonFacile.setVisible(true);
        buttonMoyen.setVisible(true);
        buttonDifficile.setVisible(true);*/
        highScoreLabel.setVisible(false);
        mainMenuButton.setVisible(false);
        scoreLabel.setVisible(false);
        gameOverText.setVisible(false);
        gameOverButton.setVisible(false);
        resumeButton.setVisible(false);
        root.getChildren().addAll(fruit,snakeBody,resumeButton,gameOverButton,gameOverText,scoreLabel,mainMenuButton,highScoreLabel);
        return root;
    }
    public void addKeyboardEvent() {
        setRoot(initAttribut());


        setOnKeyPressed(event -> {
            if(!moved){

                return;
            }
            if(moved){

                switch(event.getCode()){
                    case Z :

                        if(direction != Direction.DOWN){
                            int[] coin = new int[]{0,1,0}; //{ancienne direction, nouvelle direction, posSnake}
                            if (direction==Direction.LEFT)
                                coin[0]=3;
                            else if (direction==Direction.RIGHT)
                                coin[0]=4;
                            direction = Direction.UP;
                            controlImage.dirTete=1;
                            if(coin[0]!=0)
                                controlImage.coin.add(coin);
                        }
                        break;
                    case S :
                        if(direction != Direction.UP){
                            int[] coin = new int[]{0,2,0};
                            if (direction==Direction.LEFT)
                                coin[0]=3;
                            else if (direction==Direction.RIGHT)
                                coin[0]=4;
                            direction = Direction.DOWN;
                            controlImage.dirTete=4;
                            if(coin[0]!=0)
                                controlImage.coin.add(coin);
                        }
                        break;
                    case Q :
                        if(direction != Direction.RIGHT){
                            int[] coin = new int[]{0,3,0};
                            if (direction==Direction.UP)
                                coin[0]=1;
                            else if (direction==Direction.DOWN)
                                coin[0]=2;
                            direction = Direction.LEFT;
                            controlImage.dirTete=3;
                            if(coin[0]!=0)
                                controlImage.coin.add(coin);
                        }
                        break;
                    case D:
                        if(direction != Direction.LEFT){
                            int[] coin = new int[]{0,4,0};
                            if (direction==Direction.UP)
                                coin[0]=1;
                            else if (direction==Direction.DOWN)
                                coin[0]=2;
                            direction = Direction.RIGHT;
                            controlImage.dirTete=2;
                            if(coin[0]!=0)
                                controlImage.coin.add(coin);
                        }
                        break;
                    //la pause s'active quand on appuie sur "P" (comme pause quoi vu que j'avais pas d'idée)
                    case P:
                        System.out.println(pause);
                        pauseGame();
                        System.out.println(pause);
                        break;
                }
            }
            moved = false;
        });
        jeux.Main.getStage().setTitle("Snake");
//        jeux.Main.getStage().setScene(scene);
//        jeux.Main.getStage().show();
        startGame();

    }

    //si le fruit apparait sur le mur renvoye true
    public boolean isSurMur() {
        for (Position rect : lp) {
            if (rect.getAxeX() == fruit.getTranslateX() && rect.getAxeY() == fruit.getTranslateY()) {
                return true;
            }


        }
        return false;
    }

    //si le fruit apparait sur le snake renvoye true
    public boolean isSurSnake(){
        for(Node a : snake ) {
            if (a.getTranslateX() == fruit.getTranslateX() && a.getTranslateY() == fruit.getTranslateY()) {
                return true;
            }
        }
        return false;
    }


    private void restartGame(){
        stopGame();
        startGame();
        scoreTemporaire = 0;
    }
    private void startGame(){
        direction= Direction.RIGHT;
        Rectangle head = new Rectangle(Taille_Bloc, Taille_Bloc);
       // head.setFill(new ImagePattern(new Image("images\\test.png")));
        snake.add(head);

        controlImage.newGame();
        timeline.play();
        running = true;

    }
    private void stopGame(){
        running = false;
        timeline.stop();
        snake.clear();
    }
    public void startPause() {
        timeline.pause();
    }
    public void stopPause(){
        timeline.play();
    }
    public void pauseGame(){
        if (pause==false && canPause){
            startPause();
            resumeButton.setVisible(true);
            canPause =false;

        }
        // le if ici est pas très utile mais il m'avais aider pour corriger un bug
        if (pause==true && canPause){
            stopPause();
            pause = false;
            canPause = false;
        }
        canPause = true;
    }
    public void gameOver(){
        if (scoreTemporaire > highScore) {
            try {
                fileWriter = new FileWriter(fichierScore);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(scoreTemporaire + "\n");
                bufferedWriter.close();
                highScore = scoreTemporaire;
                highScoreLabel.setText("Bravo ! Tu a marqué un nouveau meilleur score : "+highScore);
            } catch (Exception e) {
                System.out.println("erreur d'ecriture");
            }
        }else{
            highScoreLabel.setText("highscore : "+highScore);
        }
        scoreLabel.setText("score : "+ scoreTemporaire);
        gameOverText.setVisible(true);
        highScoreLabel.setVisible(true);
        scoreLabel.setVisible(true);
        gameOverButton.setVisible(true);
        mainMenuButton.setVisible(true);
        timeline.pause();
    }

































































    public void ajouterAddAtribut(){


    }

}
