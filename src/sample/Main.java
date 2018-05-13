package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    public static final int Taille_Bloc = 40;
    public static final int Largeur = 20 * Taille_Bloc;
    public static final int Hauteur = 15 * Taille_Bloc;

    private Direction direction = Direction.RIGHT;
    private boolean moved = false;
    private boolean running = false;
    private boolean snakeAlive = true;

    //Ajout des variables pour le menu pause
    private boolean pause = false ;
    private boolean canPause = true;
    private Button resumeButton;


    //Variables game over
    private Button gameOverButton;
    private Label gameOverText;
    private Label scoreLabel;

    private Timeline timeline = new Timeline();
    private ObservableList<Node> snake;
    private Joueur joueur = new Joueur(200,0,0);
    private int scoreTemporaire=0;
    private Fruit fruitEnum;





    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(Largeur, Hauteur);

        Group snakeBody = new Group();
        snake = snakeBody.getChildren();


        Rectangle fruit = new Rectangle(
               Taille_Bloc, Taille_Bloc
        );
        fruitEnum = fruitEnum.getRandomFruit();
        fruit.setArcWidth(20);
        fruit.setArcHeight(20);
        fruit.setFill(new ImagePattern(fruitEnum.getImageFruit()));


      /*  Rectangle fruit = new Rectangle(Taille_Bloc, Taille_Bloc);*/




    //    fruit.setFill(fruitEnum.getCouleurFruit());
        fruit.setTranslateX((int)(Math.random() * Largeur - Taille_Bloc) / Taille_Bloc * Taille_Bloc); // les gens le : -Block_size permet de rester dans la grille si jamais
        fruit.setTranslateX((int)(Math.random() * Hauteur - Taille_Bloc) / Taille_Bloc * Taille_Bloc);

        KeyFrame frame = new KeyFrame(Duration.seconds(0.1), event -> { // Si on baisse le 0.1, cela augmente la difficulté  vu que le snake ira plus vite ( oué je sais c'est logique xD )
        if(!running){
            return;
        }
        boolean toRemove = snake.size()>1;
        Node QueuSnake = toRemove ? snake.remove(snake.size()-1) : snake.get(0); // regarder la synthaxe d'une condition ternaire sur internet puisque c'est chiant a expliquer

            double tailX = QueuSnake.getTranslateX();
            double tailY = QueuSnake.getTranslateY();

            switch(direction){
                case UP:
                    QueuSnake.setTranslateX(snake.get(0).getTranslateX());
                    QueuSnake.setTranslateY(snake.get(0).getTranslateY() - Taille_Bloc);
                    break;
                case DOWN :
                    QueuSnake.setTranslateX(snake.get(0).getTranslateX());
                    QueuSnake.setTranslateY(snake.get(0).getTranslateY() + Taille_Bloc);
                    break;
                case LEFT:
                    QueuSnake.setTranslateX(snake.get(0).getTranslateX()- Taille_Bloc);
                    QueuSnake.setTranslateY(snake.get(0).getTranslateY());
                    break;
                case RIGHT :
                    QueuSnake.setTranslateX(snake.get(0).getTranslateX()+ Taille_Bloc);
                    QueuSnake.setTranslateY(snake.get(0).getTranslateY());
                    break;
            }
            moved = true;
            if(toRemove==true){
                snake.add(0,QueuSnake);
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
                    scoreTemporaire=0;

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
                scoreTemporaire=0;

                gameOver();
            }
            if(QueuSnake.getTranslateX()==fruit.getTranslateX() && QueuSnake.getTranslateY() == fruit.getTranslateY()){
                fruit.setTranslateX((int)(Math.random() * (Largeur - Taille_Bloc)) / Taille_Bloc * Taille_Bloc);
                fruit.setTranslateY((int)(Math.random() * (Hauteur - Taille_Bloc)) / Taille_Bloc * Taille_Bloc);

                Rectangle rect = new Rectangle(Taille_Bloc, Taille_Bloc);
                //J'ajoute le score ici
                scoreTemporaire+=fruitEnum.getValeurFruit();
                fruitEnum = fruitEnum.getRandomFruit();

                fruit.setFill(new ImagePattern(fruitEnum.getImageFruit()));

                joueur.ajoutArgent(fruitEnum.getValeurFruit());

                rect.setTranslateX(tailX);
                rect.setTranslateY(tailY);

                snake.add(rect);
            }
        });



        //creation du bouton, texte et de l'event pour le gameover
        gameOverText = new Label("Game Over ! essaye encore !");
        gameOverText.setLayoutX(350);
        gameOverText.setLayoutY(200);
        gameOverText.setScaleX(3);
        gameOverText.setScaleY(3);

        scoreLabel = new Label("score : "+ scoreTemporaire);
        scoreLabel.setLayoutX(360);
        scoreLabel.setLayoutY(250);
        scoreLabel.setScaleX(2);
        scoreLabel.setScaleY(2);

        gameOverButton = new Button();
        gameOverButton.setText("Restart the game");
        gameOverButton.setLayoutX(350);
        gameOverButton.setLayoutY(Hauteur /2);
        gameOverButton.setOnAction(event -> {
            restartGame();
            gameOverButton.setVisible(false);
            scoreLabel.setVisible(false);
            gameOverText.setVisible(false);
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

        scoreLabel.setVisible(false);
        gameOverText.setVisible(false);
        gameOverButton.setVisible(false);
        resumeButton.setVisible(false);
        root.getChildren().addAll(fruit,snakeBody,resumeButton,gameOverButton,gameOverText,scoreLabel);
        return root;
    }



    private void restartGame(){
        stopGame();
        startGame();
    }
    private void startGame(){
        direction= Direction.RIGHT;
        Rectangle head = new Rectangle(Taille_Bloc, Taille_Bloc);
        snake.add(head);
        timeline.play();
        running = true;
    }
    private void stopGame(){
        running = false;
        timeline.stop();
        snake.clear();
    }

    //fonctions pour la menu pause
    public void startPause() {
        timeline.pause();
        System.out.println("pause");
    }
    public void stopPause(){
        timeline.play();
        System.out.println("playing");
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
        gameOverText.setVisible(true);
        scoreLabel.setVisible(true);
        gameOverButton.setVisible(true);
        timeline.pause();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> {
            if(moved == false){
                return;
            }
            if(moved==true){
                switch(event.getCode()){
                    case Z :
                        if(direction != Direction.DOWN){
                            direction = Direction.UP;
                        }
                        break;
                    case S :
                        if(direction != Direction.UP){
                            direction = Direction.DOWN;
                        }
                        break;
                    case Q :
                        if(direction != Direction.RIGHT){
                            direction = Direction.LEFT;
                        }
                        break;
                    case D:
                        if(direction != Direction.LEFT){
                            direction = Direction.RIGHT;
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
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
        startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
