package mvcSnake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {


    public static final int TailleBloc = 40;
    public static final int Largeur = 20 * TailleBloc;
    public static final int Hauteur = 15 * TailleBloc;

    private Direction direction = Direction.RIGHT;
    private boolean Deplacement = false;
    private boolean running = false;

    private Timeline timeline = new Timeline();
    private ObservableList<Node> snake;

    private Parent createContent() {
        Pane main = new Pane();
        main.setPrefSize(Largeur, Hauteur);

        Group snakeBody = new Group();
        snake = snakeBody.getChildren();

        Rectangle fruit = new Rectangle(TailleBloc, TailleBloc);
        fruit.setFill(Color.BLUE);
        fruit.setTranslateX((int)(Math.random() * Largeur - TailleBloc) / TailleBloc * TailleBloc); // les gens le : -Block_size permet de rester dans la grille si jamais
        fruit.setTranslateX((int)(Math.random() * Hauteur - TailleBloc) / TailleBloc * TailleBloc);

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
                    tail.setTranslateY(snake.get(0).getTranslateY() - TailleBloc);
                    break;
                case DOWN :
                    tail.setTranslateX(snake.get(0).getTranslateX());
                    tail.setTranslateY(snake.get(0).getTranslateY() + TailleBloc);
                    break;
                case LEFT:
                    tail.setTranslateX(snake.get(0).getTranslateX()- TailleBloc);
                    tail.setTranslateY(snake.get(0).getTranslateY());
                    break;
                case RIGHT :
                    tail.setTranslateX(snake.get(0).getTranslateX()+ TailleBloc);
                    tail.setTranslateY(snake.get(0).getTranslateY());
                    break;
            }
            Deplacement = true;
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
            if(tail.getTranslateX()<0 || tail.getTranslateX() >= Largeur || tail.getTranslateY() <0 || tail.getTranslateY() >= Hauteur){
                restartGame();
            }
            if(tail.getTranslateX()==fruit.getTranslateX() && tail.getTranslateY() == fruit.getTranslateY()){
                fruit.setTranslateX((int)(Math.random() * (Largeur - TailleBloc)) / TailleBloc * TailleBloc);
                fruit.setTranslateY((int)(Math.random() * (Hauteur - TailleBloc)) / TailleBloc * TailleBloc);

                Rectangle rect = new Rectangle(TailleBloc, TailleBloc);
                rect.setTranslateX(tailX);
                rect.setTranslateY(tailY);

                snake.add(rect);
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        main.getChildren().addAll(fruit,snakeBody);
        return main;
    }
    private void restartGame(){
        stopGame();
        startGame();
    }
    private void startGame(){
        direction= Direction.RIGHT;
        Rectangle teteSerpent = new Rectangle(TailleBloc, TailleBloc);
        snake.add(teteSerpent);
        timeline.play();
        running = true;
    }
    private void stopGame(){
        running = false;
        timeline.stop();
        snake.clear();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mvcSnake.fxml"));
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> {
            if(Deplacement == false){
                return;
            }
            if(Deplacement ==true){
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
                }
            }
            Deplacement = false;
        });
        primaryStage.setTitle("Snake projet tuteuré s2 !");
        primaryStage.setScene(scene);
        primaryStage.show();
        startGame();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
