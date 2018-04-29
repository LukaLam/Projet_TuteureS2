package menu.menuBoutique;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;


public class Main extends Application {

    //TEST
    Player p1 = new Player(1000);

    Text texteArgentTot ;

    public void start(Stage fenBoutiqueTot){
        fenBoutiqueTot.setTitle("Boutique");
        Group root = new Group();
        Scene scene = new Scene(root, 250, 200, Color.WHITE);

        VBox fenBoutique = new VBox();
        fenBoutique.setPadding(new Insets(10,40,40,30));
        fenBoutique.setSpacing(20);
        fenBoutique.setAlignment(Pos.CENTER);

        //FRAMES
        //Texte
        Text annonceBoutique = new Text(10,50, "Que souhaitez-vous acheter ?");

        //Boutons
        Button butDecor = new Button("Décors");
        butDecor.setOnAction(actionEvent -> ouvrirBoutiqueDecors());

        Button butSkins = new Button("Apparence");
        butSkins.setOnAction(actionEvent -> System.out.println("Boutique d'apparence"));

        Button butMusique = new Button("Musiques");
        butMusique.setOnAction(actionEvent -> System.out.println("Boutique de musiques"));

        texteArgentTot = new Text();
        texteArgentTot.setText("Argent : " + p1.getArgent() + " pièces");

        //Ajout à la VBox
        fenBoutique.getChildren().add(annonceBoutique);
        fenBoutique.getChildren().add(butDecor);
        fenBoutique.getChildren().add(butSkins);
        fenBoutique.getChildren().add(butMusique);
        fenBoutique.getChildren().add(texteArgentTot);

        //Ajout à la fen principale
        root.getChildren().add(fenBoutique);

        fenBoutiqueTot.setScene(scene);
        fenBoutiqueTot.show();
    }

    public void ouvrirBoutiqueDecors(){

        //LISTE DECORS
        ArrayList<Decors> listeDecors = new ArrayList<Decors>();
        listeDecors.add(new Decors("Décor 1", "sample/img/decor.png", "sample/img/decorGrand.png","Description", 100));
        listeDecors.add(new Decors("Décor 2", "sample/img/decor.png", "sample/img/decorGrand.png","Description", 100));
        listeDecors.add(new Decors("Décor 3", "sample/img/decor.png", "sample/img/decorGrand.png","Description", 100));
        listeDecors.add(new Decors("Décor 4", "sample/img/decor.png", "sample/img/decorGrand.png","Description", 100));
        listeDecors.add(new Decors("Décor 5", "sample/img/decor.png", "sample/img/decorGrand.png","Description", 100));
        listeDecors.add(new Decors("Décor 6", "sample/img/decor.png", "sample/img/decorGrand.png","Description", 100));


        //FEN ROOT
        Stage fenBoutiqueDecorTot = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        fenBoutiqueDecorTot.setTitle("Décors");


        //FEN PRINCIPALE
        BorderPane fenBoutiqueDecor = new BorderPane();


        Text annonceBoutDecor = new Text("Bienvenue dans la boutique de décors.\n Cliquez sur l'image pour l'agrandir.");
        Text texteArgentDecor = new Text("Argent : " + p1.getArgent() + " pièces");
        annonceBoutDecor.setTextAlignment(TextAlignment.CENTER);
        texteArgentDecor.setTextAlignment(TextAlignment.CENTER);


        //OBJETS DECORS
        GridPane fenDifDecors = new GridPane();
        fenDifDecors.setVgap(50);
        fenDifDecors.setHgap(50);
        fenDifDecors.setPadding(new Insets(10,40,30,30));

        //Parcours de la liste des décors pour ajouter à une box tous les éléments
        ArrayList<VBox> listeVBoxDecors = new ArrayList<VBox>();
        for (Decors elementDecors:listeDecors) {
            VBox decorBox = new VBox();
            Button butAcheter = new Button("Acheter");
            butAcheter.setOnAction(actionEvent -> {
                Alert achatAlerte = new Alert(Alert.AlertType.CONFIRMATION);
                achatAlerte.setTitle("Confirmation d'achat");
                achatAlerte.setHeaderText(null);
                achatAlerte.setContentText("Voulez-vous effectuer cet achat ?");
                Optional<ButtonType> choix = achatAlerte.showAndWait();

                if(choix.get() == ButtonType.OK){
                    if (p1.getArgent() >= elementDecors.getPrix() && !p1.possedeObjet(elementDecors)){

                        Alert achatEffectue = new Alert(Alert.AlertType.INFORMATION);
                        achatEffectue.setTitle("Achat effectué");
                        achatEffectue.setHeaderText(null);
                        achatEffectue.setContentText("Merci pour votre achat !");
                        p1.acheterObjet(elementDecors);
                        texteArgentTot.setText("Argent : " + p1.getArgent() + " pièces");

                        decorBox.getChildren().remove(butAcheter);
                        decorBox.getChildren().add(new Text("Acheté !"));
                        achatEffectue.showAndWait();

                    } else {
                        Alert achatRefuse = new Alert(Alert.AlertType.ERROR);
                        achatRefuse.setTitle("Achat refusé");
                        achatRefuse.setHeaderText(null);
                        achatRefuse.setContentText("Vous n'avez pas assez de pièces.");
                        achatRefuse.showAndWait();
                    }
                }
            });

            decorBox.getChildren().add(new Text(elementDecors.getTitre()));
            ImageView imageDecorView = new ImageView(new Image(elementDecors.getChemin()));
            decorBox.getChildren().add(imageDecorView);
            imageDecorView.setOnMouseClicked(mouseEvent -> {
                Stage fenDecorGrand = new Stage();
                Group rootDecorGrand = new Group();
                Scene sceneDecorGrand = new Scene(rootDecorGrand, 800,800);
                fenDecorGrand.setTitle(elementDecors.getTitre());
                rootDecorGrand.getChildren().add(new ImageView(new Image(elementDecors.getCheminGrand())));
                fenDecorGrand.setScene(sceneDecorGrand);
                fenDecorGrand.showAndWait();

            });
            decorBox.getChildren().add(new Text(elementDecors.getDescription()));
            decorBox.getChildren().add(new Text(String.valueOf(elementDecors.getPrix()) + " pièces"));
            decorBox.getChildren().add(butAcheter);
            listeVBoxDecors.add(decorBox);
        }

        //Parcours de la liste box décors pour les ajouter au gridpane
        int colonne = 0;
        int ligne = 0;
        for (VBox decorBox:listeVBoxDecors) {
            fenDifDecors.add(decorBox, colonne, ligne);
            //Une fois atteint 3 => revient à 0
            colonne = (colonne+1)%3;
            //Si colonne revient à 0, on change de ligne
            if(colonne==0){
                ligne++;
            }
        }


        //Ajout final
        fenBoutiqueDecor.setTop(annonceBoutDecor);
        fenBoutiqueDecor.setCenter(fenDifDecors);
        fenBoutiqueDecor.setBottom(texteArgentDecor);
        root.getChildren().add(fenBoutiqueDecor);

        //AFFICHAGE
        fenBoutiqueDecorTot.setScene(scene);
        fenBoutiqueDecorTot.show();

    }

    public static void main(String[] args){
        Application.launch(Main.class, args);
    }
}
