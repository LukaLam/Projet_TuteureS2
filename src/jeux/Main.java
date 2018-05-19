package jeux;

import javafx.application.Application;
import javafx.stage.Stage;
import menu.MenuPrincipal;

import static sample.SnakeJeu.Hauteur;
import static sample.SnakeJeu.Largeur;

public class Main extends Application {
    public static Stage stage;

    public static Stage getStage() {
        return stage;
    }
    public static void main (String[] args){
        Application.launch(Main.class);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.stage=primaryStage;

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new MenuPrincipal());
        primaryStage.show();

    }
}