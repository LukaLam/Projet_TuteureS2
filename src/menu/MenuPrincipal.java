package menu;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import jeux.Main;
import joueur.Joueur;
import menu.menuBoutique.MenuBoutique;
import menu.menuInventaire.MenuInventaire;
import menu.menuModele.Modele;


public class MenuPrincipal extends Scene {
    Button[] boutons;
    Son son;
    Image image;
    ImageView iv;

    Joueur joueur1;
    Modele modele;
    MenuBoutique menuBoutique;
    MenuInventaire menuInventaire;


    public MenuPrincipal(Modele modele){
        super(new Group(),800,600);
        this.joueur1 = modele.getJ1();
        this.modele = modele;
        menuBoutique = new MenuBoutique(joueur1);
        menuInventaire = new MenuInventaire(joueur1);
        initAttribut();
        ajouterAddAtribut();
        addMouseEvent();

    }
    public void initAttribut(){
        son = new Son();
        boutons = new Button[]{
                new Button("Snake"),
                new Button("Boutique"),
                new Button("Inventaire")
        };

        image = new Image("file:Background.png");
        iv= new ImageView();
    }
    public void ajouterAddAtribut(){
        Group root = (Group)this.getRoot();
        GridPane gp = new GridPane();
        gp.addColumn(0,boutons);
        root.getChildren().add(gp);

        iv.setImage(image);
        iv.setFitWidth(800);
        iv.setFitHeight(600);

        root.getChildren().add(iv);

    }

    public void addMouseEvent() {

        boutons[0].setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("btest");
                Main.getStage().setScene(new MenuDifficulte(modele));

            }
        });

        boutons[1].setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("btest");
                Main.getStage().setScene(menuBoutique);
            }
        });

        boutons[2].setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.getStage().setScene(menuInventaire);
            }
        });
    }
}
