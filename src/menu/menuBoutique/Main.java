package menu.menuBoutique;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.lang.management.PlatformManagedObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;


public class Main extends Application {

    //TEST
    private Player p1 = new Player(150);

    private Text texteArgentTotBoutique, texteArgentTotDecors, texteArgentTotMusique, texteArgentTotSkin ;

    private Image bouton_play = new Image("img/musique/bouton_play.png");
    private Image bouton_stop = new Image("img/musique/bouton_stop.png");

    public void start(Stage fenBoutiqueTot){
        Decors.initDecors();
        Musique.initMusique();
        Skin.initSkin();

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
        butSkins.setOnAction(actionEvent -> ouvrirBoutiqueSkins());

        Button butMusique = new Button("Musiques");
        butMusique.setOnAction(actionEvent -> ouvrirBoutiqueMusique());

        texteArgentTotBoutique = new Text("Argent : " + p1.getArgent() + " pièces");

        //Ajout à la VBox
        fenBoutique.getChildren().add(annonceBoutique);
        fenBoutique.getChildren().add(butDecor);
        fenBoutique.getChildren().add(butSkins);
        fenBoutique.getChildren().add(butMusique);
        fenBoutique.getChildren().add(texteArgentTotBoutique);

        //Ajout à la fen principale
        root.getChildren().add(fenBoutique);

        fenBoutiqueTot.setScene(scene);
        fenBoutiqueTot.show();
    }

    public void ouvrirBoutiqueDecors(){

        //FEN ROOT
        Stage fenBoutiqueDecorTot = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        fenBoutiqueDecorTot.setTitle("Décors");


        //FEN PRINCIPALE
        BorderPane fenBoutiqueDecor = new BorderPane();


        Text annonceBoutDecor = new Text("Bienvenue dans la boutique de décors.\n Cliquez sur l'image pour l'agrandir.");
        texteArgentTotDecors = new Text("Argent : " + p1.getArgent() + " pièces");
        annonceBoutDecor.setTextAlignment(TextAlignment.CENTER);
        texteArgentTotDecors.setTextAlignment(TextAlignment.CENTER);


        //OBJETS DECORS
        GridPane fenDifDecors = new GridPane();
        fenDifDecors.setVgap(50);
        fenDifDecors.setHgap(50);
        fenDifDecors.setPadding(new Insets(10,40,30,30));

        //Parcours de la liste des décors pour ajouter à une box tous les éléments
        ArrayList<VBox> listeVBoxDecors = new ArrayList<VBox>();
        for (Decors elementDecors:Decors.getListeDecors()) {
            VBox decorBox = new VBox();

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

            if(!p1.possedeObjet(elementDecors)) {
                Button butAcheter = new Button("Acheter");
                decorBox.getChildren().add(butAcheter);
                butAcheter.setOnAction(actionEvent -> {
                    Alert achatAlerte = new Alert(Alert.AlertType.CONFIRMATION);
                    achatAlerte.setTitle("Confirmation d'achat");
                    achatAlerte.setHeaderText(null);
                    achatAlerte.setContentText("Voulez-vous effectuer cet achat ?");
                    Optional<ButtonType> choix = achatAlerte.showAndWait();

                    if(choix.get() == ButtonType.OK){
                        if (p1.getArgent() >= elementDecors.getPrix()){

                            Alert achatEffectue = new Alert(Alert.AlertType.INFORMATION);
                            achatEffectue.setTitle("Achat effectué");
                            achatEffectue.setHeaderText(null);
                            achatEffectue.setContentText("Merci pour votre achat !");
                            p1.acheterObjet(elementDecors);
                            //Maj texte argent (décors)
                            texteArgentTotDecors.setText("Argent : " + p1.getArgent() + " pièces");
                            //Maj texte argent (boutique)
                            texteArgentTotBoutique.setText("Argent : " + p1.getArgent() + " pièces");

                            //Suppression du bouton Acheter pour mettre un texte
                            decorBox.getChildren().remove(butAcheter);
                            decorBox.getChildren().add(new Text("Acheté !"));

                            achatEffectue.showAndWait();

                            //Message d'erreur si pas assez d'argent
                        } else if(p1.getArgent() < elementDecors.getPrix()){
                            Alert achatRefuse = new Alert(Alert.AlertType.ERROR);
                            achatRefuse.setTitle("Achat refusé");
                            achatRefuse.setHeaderText(null);
                            achatRefuse.setContentText("Vous n'avez pas assez de pièces.");
                            achatRefuse.showAndWait();
                        }
                    }
                });
            } else {
                decorBox.getChildren().add(new Text("Acheté !"));
            }
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
        fenBoutiqueDecor.setBottom(texteArgentTotDecors);
        root.getChildren().add(fenBoutiqueDecor);

        //AFFICHAGE
        fenBoutiqueDecorTot.setScene(scene);
        fenBoutiqueDecorTot.show();

    }

    public void ouvrirBoutiqueSkins(){
        //FEN ROOT
        Stage fenBoutiqueSkinTot = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        fenBoutiqueSkinTot.setTitle("Apparence");


        //FEN PRINCIPALE
        BorderPane fenBoutiqueSkin = new BorderPane();


        Text annonceBoutSkin = new Text("Bienvenue dans la boutique d'apparence.\n Cliquez sur l'image pour l'agrandir.");
        texteArgentTotSkin = new Text("Argent : " + p1.getArgent() + " pièces");
        annonceBoutSkin.setTextAlignment(TextAlignment.CENTER);
        texteArgentTotSkin.setTextAlignment(TextAlignment.CENTER);


        //OBJETS DECORS
        GridPane fenDifSkins = new GridPane();
        fenDifSkins.setVgap(50);
        fenDifSkins.setHgap(50);
        fenDifSkins.setPadding(new Insets(10,40,30,30));

        //Parcours de la liste des décors pour ajouter à une box tous les éléments
        ArrayList<VBox> listeVBoxSkins = new ArrayList<VBox>();
        for (Skin elementSkin:Skin.getListSkins()) {
            VBox skinBox = new VBox();

            skinBox.getChildren().add(new Text(elementSkin.getTitre()));
            ImageView imageDecorView = new ImageView(new Image(elementSkin.getChemin()));
            skinBox.getChildren().add(imageDecorView);
            imageDecorView.setOnMouseClicked(mouseEvent -> {
                Stage fenSkinGrand = new Stage();
                Group rootSkinGrand = new Group();
                Scene sceneSkinGrand = new Scene(rootSkinGrand, 800,800);
                fenSkinGrand.setTitle(elementSkin.getTitre());
                rootSkinGrand.getChildren().add(new ImageView(new Image(elementSkin.getCheminGrand())));
                fenSkinGrand.setScene(sceneSkinGrand);
                fenSkinGrand.showAndWait();

            });
            skinBox.getChildren().add(new Text(elementSkin.getDescription()));
            skinBox.getChildren().add(new Text(String.valueOf(elementSkin.getPrix()) + " pièces"));

            if(!p1.possedeObjet(elementSkin)) {
                Button butAcheter = new Button("Acheter");
                skinBox.getChildren().add(butAcheter);
                butAcheter.setOnAction(actionEvent -> {
                    Alert achatAlerte = new Alert(Alert.AlertType.CONFIRMATION);
                    achatAlerte.setTitle("Confirmation d'achat");
                    achatAlerte.setHeaderText(null);
                    achatAlerte.setContentText("Voulez-vous effectuer cet achat ?");
                    Optional<ButtonType> choix = achatAlerte.showAndWait();

                    if(choix.get() == ButtonType.OK){
                        if (p1.getArgent() >= elementSkin.getPrix()){

                            Alert achatEffectue = new Alert(Alert.AlertType.INFORMATION);
                            achatEffectue.setTitle("Achat effectué");
                            achatEffectue.setHeaderText(null);
                            achatEffectue.setContentText("Merci pour votre achat !");
                            p1.acheterObjet(elementSkin);
                            //Maj texte argent (skins)
                            texteArgentTotSkin.setText("Argent : " + p1.getArgent() + " pièces");
                            //Maj texte argent (boutique)
                            texteArgentTotBoutique.setText("Argent : " + p1.getArgent() + " pièces");

                            //Suppression du bouton Acheter pour mettre un texte
                            skinBox.getChildren().remove(butAcheter);
                            skinBox.getChildren().add(new Text("Acheté !"));

                            achatEffectue.showAndWait();

                            //Message d'erreur si pas assez d'argent
                        } else if(p1.getArgent() < elementSkin.getPrix()){
                            Alert achatRefuse = new Alert(Alert.AlertType.ERROR);
                            achatRefuse.setTitle("Achat refusé");
                            achatRefuse.setHeaderText(null);
                            achatRefuse.setContentText("Vous n'avez pas assez de pièces.");
                            achatRefuse.showAndWait();
                        }
                    }
                });
            } else {
                skinBox.getChildren().add(new Text("Acheté !"));
            }
            listeVBoxSkins.add(skinBox);
        }

        //Parcours de la liste box décors pour les ajouter au gridpane
        int colonne = 0;
        int ligne = 0;
        for (VBox decorBox:listeVBoxSkins) {
            fenDifSkins.add(decorBox, colonne, ligne);
            //Une fois atteint 3 => revient à 0
            colonne = (colonne+1)%3;
            //Si colonne revient à 0, on change de ligne
            if(colonne==0){
                ligne++;
            }
        }


        //Ajout final
        fenBoutiqueSkin.setTop(annonceBoutSkin);
        fenBoutiqueSkin.setCenter(fenDifSkins);
        fenBoutiqueSkin.setBottom(texteArgentTotSkin);
        root.getChildren().add(fenBoutiqueSkin);

        //AFFICHAGE
        fenBoutiqueSkinTot.setScene(scene);
        fenBoutiqueSkinTot.show();
    }

    public void ouvrirBoutiqueMusique(){

        //FEN ROOT
        Stage fenBoutiqueMusiqueTot = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        fenBoutiqueMusiqueTot.setTitle("Musiques");


        //FEN PRINCIPALE
        BorderPane fenBoutiqueMusique = new BorderPane();


        Text annonceBoutMusique = new Text("Bienvenue dans la boutique de musiques.\n Cliquez sur le bouton play pour jouer la musique.");
        texteArgentTotMusique = new Text("Argent : " + p1.getArgent() + " pièces");
        annonceBoutMusique.setTextAlignment(TextAlignment.CENTER);
        texteArgentTotMusique.setTextAlignment(TextAlignment.CENTER);


        //OBJETS DECORS
        GridPane fenDifMusique = new GridPane();
        fenDifMusique.setVgap(50);
        fenDifMusique.setHgap(50);
        fenDifMusique.setPadding(new Insets(10,40,30,30));

        //Parcours de la liste des décors pour ajouter à une box tous les éléments
        ArrayList<VBox> listeVBoxMusique = new ArrayList<VBox>();
        for (Musique elementMusiques:Musique.getListeMusique()) {
            VBox musiqueBox = new VBox();

            musiqueBox.getChildren().add(new Text(elementMusiques.getTitre()));

            ToggleButton togglebouton_play = new ToggleButton();
            togglebouton_play.setGraphic(new ImageView(bouton_play));
            ToggleButton togglebouton_stop = new ToggleButton();
            togglebouton_stop.setGraphic(new ImageView(bouton_stop));
            ToggleGroup groupPlayStop = new ToggleGroup();
            togglebouton_play.setToggleGroup(groupPlayStop);
            togglebouton_stop.setToggleGroup(groupPlayStop);

            File file = new File(elementMusiques.getChemin());
            Media media = new Media(file.toURI().toString());
            MediaPlayer player = new MediaPlayer(media);


            HBox boxBoutonPlayStop = new HBox();
            boxBoutonPlayStop.getChildren().add(togglebouton_play);
            boxBoutonPlayStop.getChildren().add(togglebouton_stop);

            musiqueBox.getChildren().add(boxBoutonPlayStop);

            togglebouton_play.setOnMouseClicked(mouseEvent -> {
                player.play();
                player.setAutoPlay(true);
                togglebouton_play.setSelected(true);
            });

            togglebouton_stop.setOnMouseClicked(mouseEvent -> {
                player.stop();
                togglebouton_play.setSelected(false);
            });

            fenBoutiqueMusiqueTot.setOnCloseRequest(windowEvent -> {
                player.stop();
            });

            musiqueBox.getChildren().add(new Text(elementMusiques.getDescription()));
            musiqueBox.getChildren().add(new Text(String.valueOf(elementMusiques.getPrix()) + " pièces"));

            if(!p1.possedeObjet(elementMusiques)) {
                Button butAcheter = new Button("Acheter");
                musiqueBox.getChildren().add(butAcheter);
                butAcheter.setOnAction(actionEvent -> {
                    Alert achatAlerte = new Alert(Alert.AlertType.CONFIRMATION);
                    achatAlerte.setTitle("Confirmation d'achat");
                    achatAlerte.setHeaderText(null);
                    achatAlerte.setContentText("Voulez-vous effectuer cet achat ?");
                    Optional<ButtonType> choix = achatAlerte.showAndWait();

                    if(choix.get() == ButtonType.OK){
                        if (p1.getArgent() >= elementMusiques.getPrix()){

                            Alert achatEffectue = new Alert(Alert.AlertType.INFORMATION);
                            achatEffectue.setTitle("Achat effectué");
                            achatEffectue.setHeaderText(null);
                            achatEffectue.setContentText("Merci pour votre achat !");
                            p1.acheterObjet(elementMusiques);
                            //Maj texte argent (décors)
                            texteArgentTotMusique.setText("Argent : " + p1.getArgent() + " pièces");
                            //Maj texte argent (boutique)
                            texteArgentTotBoutique.setText("Argent : " + p1.getArgent() + " pièces");

                            //Suppression du bouton Acheter pour mettre un texte
                            musiqueBox.getChildren().remove(butAcheter);
                            musiqueBox.getChildren().add(new Text("Acheté !"));

                            achatEffectue.showAndWait();

                            //Message d'erreur si pas assez d'argent
                        } else if(p1.getArgent() < elementMusiques.getPrix()){
                            Alert achatRefuse = new Alert(Alert.AlertType.ERROR);
                            achatRefuse.setTitle("Achat refusé");
                            achatRefuse.setHeaderText(null);
                            achatRefuse.setContentText("Vous n'avez pas assez de pièces.");
                            achatRefuse.showAndWait();
                        }
                    }
                });
            } else {
                musiqueBox.getChildren().add(new Text("Acheté !"));
            }
            listeVBoxMusique.add(musiqueBox);
        }

        //Parcours de la liste box décors pour les ajouter au gridpane
        int colonne = 0;
        int ligne = 0;
        for (VBox musiqueBox:listeVBoxMusique) {
            fenDifMusique.add(musiqueBox, colonne, ligne);
            //Une fois atteint 3 => revient à 0
            colonne = (colonne+1)%3;
            //Si colonne revient à 0, on change de ligne
            if(colonne==0){
                ligne++;
            }
        }


        //Ajout final
        fenBoutiqueMusique.setTop(annonceBoutMusique);
        fenBoutiqueMusique.setCenter(fenDifMusique);
        fenBoutiqueMusique.setBottom(texteArgentTotMusique);
        root.getChildren().add(fenBoutiqueMusique);

        //AFFICHAGE
        fenBoutiqueMusiqueTot.setScene(scene);
        fenBoutiqueMusiqueTot.show();
    }

    public static void main(String[] args){
        Application.launch(Main.class, args);
    }
}
