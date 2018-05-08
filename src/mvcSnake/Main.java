package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import mvcSnake.Direction;


public class Main extends Application {


    public static final int BLOCK_SIZE = 40;
    public static final int APP_W = 20 * BLOCK_SIZE;
    public static final int APP_H = 15 * BLOCK_SIZE;

    private Direction direction = Direction.RIGHT;
    private boolean moved = false;
    private boolean running = false;

    //Ajout des variables pour le menu pause
    private boolean pause = false ;
    private boolean canPause = true;
    private Button resumeButton;

    private Timeline timeline = new Timeline();
    private ObservableList<Node> snake;

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(APP_W,APP_H);

        Group snakeBody = new Group();
        snake = snakeBody.getChildren();

        Rectangle food = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
        food.setFill(Color.BLUE);
        food.setTranslateX((int)(Math.random() * APP_W-BLOCK_SIZE) / BLOCK_SIZE * BLOCK_SIZE); // les gens le : -Block_size permet de rester dans la grille si jamais
        food.setTranslateX((int)(Math.random() * APP_H-BLOCK_SIZE) / BLOCK_SIZE * BLOCK_SIZE);

        KeyFrame frame = new KeyFrame(Duration.seconds(0.1), event -> { // Si on baisse le 0.1, cela augmente la difficulté  vu que le snake ira plus vite ( oué je sais c'est logique xD )
        if(!running){
            return;
        }
        boolean toRemove = snake.size()>1;
        Node tail = toRemove ? snake.remove(snake.size()-1) : snake.get(0); // regarder la synthaxe d'une variable ternaire sur internet puisque c'est chiant a expliquer

            double tailX = tail.getTranslateX();
            double tailY = tail.getTranslateY();

            switch(direction){
                case UP:
                    tail.setTranslateX(snake.get(0).getTranslateX());
                    tail.setTranslateY(snake.get(0).getTranslateY() - BLOCK_SIZE);
                    break;
                case DOWN :
                    tail.setTranslateX(snake.get(0).getTranslateX());
                    tail.setTranslateY(snake.get(0).getTranslateY() + BLOCK_SIZE);
                    break;
                case LEFT:
                    tail.setTranslateX(snake.get(0).getTranslateX()- BLOCK_SIZE);
                    tail.setTranslateY(snake.get(0).getTranslateY());
                    break;
                case RIGHT :
                    tail.setTranslateX(snake.get(0).getTranslateX()+ BLOCK_SIZE);
                    tail.setTranslateY(snake.get(0).getTranslateY());
                    break;
            }
            moved = true;
            if(toRemove==true){
                snake.add(0,tail);
            }
            //collision
            for(Node rect : snake){
                if(rect != tail && tail.getTranslateX() == rect.getTranslateX() && tail.getTranslateY() == rect.getTranslateY()){
                    restartGame();
                    break;
                }
            }
            if(tail.getTranslateX()<0 || tail.getTranslateX() >= APP_W || tail.getTranslateY() <0 || tail.getTranslateY() >= APP_H){
                restartGame();
            }
            if(tail.getTranslateX()==food.getTranslateX() && tail.getTranslateY() == food.getTranslateY()){
                food.setTranslateX((int)(Math.random() * (APP_W-BLOCK_SIZE)) / BLOCK_SIZE * BLOCK_SIZE);
                food.setTranslateY((int)(Math.random() * (APP_H-BLOCK_SIZE)) / BLOCK_SIZE * BLOCK_SIZE);

                Rectangle rect = new Rectangle(BLOCK_SIZE,BLOCK_SIZE);
                rect.setTranslateX(tailX);
                rect.setTranslateY(tailY);

                snake.add(rect);
            }
        });
        //creation du bouton et de l'event
        resumeButton = new Button();
        resumeButton.setText("Resume");
        resumeButton.setLayoutX(370);
        resumeButton.setLayoutY(APP_H/2);
        resumeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline.play();
                resumeButton.setVisible(false);
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        resumeButton.setVisible(false);
        root.getChildren().addAll(food,snakeBody,resumeButton);
        return root;
    }
    private void restartGame(){
        stopGame();
        startGame();
    }
    private void startGame(){
        direction= Direction.RIGHT;
        Rectangle head = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
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
