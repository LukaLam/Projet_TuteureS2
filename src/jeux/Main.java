package jeux;

import javafx.application.Application;
import javafx.stage.Stage;
import menu.MenuPrincipal;
import menu.menuModele.Modele;

import static sample.SnakeJeu.Hauteur;
import static sample.SnakeJeu.Largeur;

public class Main extends Application {
    public static Stage stage;
    public static Modele modele;

    public static Stage getStage() {
        return stage;
    }

    public static Modele getModele() {
        return modele;
    }

    public static void main (String[] args){
        Application.launch(Main.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.stage=primaryStage;

        modele = new Modele();

        primaryStage.setTitle("Snake");
        primaryStage.setScene(modele.getMenuPrincipal());
        primaryStage.show();

    }
}