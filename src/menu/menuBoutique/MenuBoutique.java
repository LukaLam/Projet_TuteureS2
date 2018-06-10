package menu.menuBoutique;

import jeux.Main;
import joueur.Joueur;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.ArrayList;
import java.util.Optional;

public class MenuBoutique extends Scene {
    private Joueur joueur;

    private Text texteArgentTotBoutique, texteArgentTotDecors, texteArgentTotMusique, texteArgentTotSkin ;

    private Image bouton_play ;
    private Image bouton_stop;
    Button butDecor, butSkins, butMusique, butMenuPrincipal;
    VBox gp;
    Text annonceBoutique;
    Stage fenBoutiqueDecorTot;
    Group root;
    Scene scene;
    Text annonceBoutDecor;

    public MenuBoutique(Joueur joueur) {
        //super(new Group(),800,600);
        super(new Group(), 250, 300);
        this.joueur = joueur;
        initAttribut();
        ajouterAddAtribut();
        addMouseEvent();
    }
    public void initAttribut(){
        bouton_play = new Image("img/musique/bouton_play.png");
        bouton_stop = new Image("img/musique/bouton_stop.png");

        butDecor = new Button("Décors");
        butSkins = new Button("Apparence");
        butMusique = new Button("Musiques");
        butMenuPrincipal = new Button("Quitter");

        texteArgentTotDecors = new Text();
        texteArgentTotBoutique = new Text();
        annonceBoutique = new Text(10,50, "Que souhaitez-vous acheter ?");
        texteArgentTotDecors.setText("Argent : " + joueur.getArgent() + " pièces");
        //Maj texte argent (boutique)
        texteArgentTotBoutique.setText("Argent : " + joueur.getArgent() + " pièces");
        gp = new VBox();
        Decors.initDecors();
        Musique.initMusique();
        Skin.initSkin();



    }
    public void ajouterAddAtribut(){
        root = (Group)this.getRoot();
        GridPane gp = new GridPane();
        gp.addColumn(0,butDecor);
        root.getChildren().add(gp);

        this.gp.setPadding(new Insets(10,40,40,30));
        this.gp.setSpacing(20);
        this.gp.setAlignment(Pos.CENTER);

        this.gp.getChildren().add(annonceBoutique);
        this.gp.getChildren().add(butDecor);
        this.gp.getChildren().add(butSkins);
        this.gp.getChildren().add(butMusique);
        this.gp.getChildren().add(butMenuPrincipal);
        this.gp.getChildren().add(texteArgentTotBoutique);

        root.getChildren().add(this.gp);

    }


    public void addMouseEvent(){
        butDecor.setOnAction(actionEvent -> ouvrirBoutiqueDecors());
        butSkins.setOnAction(actionEvent -> ouvrirBoutiqueSkins());
        butMusique.setOnAction(actionEvent -> ouvrirBoutiqueMusique());
        butMenuPrincipal.setOnAction(event -> {
            //mainMenuButton.setVisible(false);
            Main.getStage().setScene(Main.getModele().getMenuPrincipal());


        });

    }

    public void ouvrirBoutiqueDecors(){

        //FEN ROOT
        fenBoutiqueDecorTot = new Stage();
        root = new Group();
        scene = new Scene(root, 800, 520, Color.WHITE);
        fenBoutiqueDecorTot.setTitle("Décors");


        //FEN PRINCIPALE
        BorderPane fenBoutiqueDecor = new BorderPane();


        annonceBoutDecor = new Text("Bienvenue dans la boutique de décors.\n Cliquez sur l'image pour l'agrandir.");
        texteArgentTotDecors = new Text("Argent : " + joueur.getArgent() + " pièces");
        annonceBoutDecor.setTextAlignment(TextAlignment.CENTER);
        texteArgentTotDecors.setTextAlignment(TextAlignment.CENTER);


        //OBJETS DECORS
        GridPane fenDifDecors = new GridPane();
        fenDifDecors.setVgap(50);
        fenDifDecors.setHgap(50);
        fenDifDecors.setPadding(new Insets(10,40,30,30));

        //Parcours de la liste des décors pour ajouter à une box tous les éléments
        ArrayList<VBox> listeVBoxDecors = new ArrayList<VBox>();
        int compteur = 0;
        for (Decors elementDecors:Decors.getListeDecors()) {
            if(compteur > 0) {
                VBox decorBox = new VBox();

                decorBox.getChildren().add(new Text(elementDecors.getTitre()));
                ImageView imageDecorView = new ImageView(new Image(elementDecors.getChemin()));
                decorBox.getChildren().add(imageDecorView);
                imageDecorView.setOnMouseClicked(mouseEvent -> {
                    Stage fenDecorGrand = new Stage();
                    Group rootDecorGrand = new Group();
                    Scene sceneDecorGrand = new Scene(rootDecorGrand, 1280, 720);
                    fenDecorGrand.setTitle(elementDecors.getTitre());
                    rootDecorGrand.getChildren().add(new ImageView(new Image(elementDecors.getCheminGrand())));
                    fenDecorGrand.setScene(sceneDecorGrand);
                    fenDecorGrand.showAndWait();

                });
                decorBox.getChildren().add(new Text(elementDecors.getDescription()));
                decorBox.getChildren().add(new Text(String.valueOf(elementDecors.getPrix()) + " pièces"));

                if (!joueur.possedeObjet(elementDecors)) {
                    Button butAcheter = new Button("Acheter");
                    decorBox.getChildren().add(butAcheter);
                    butAcheter.setOnAction(actionEvent -> {
                        Alert achatAlerte = new Alert(Alert.AlertType.CONFIRMATION);
                        achatAlerte.setTitle("Confirmation d'achat");
                        achatAlerte.setHeaderText(null);
                        achatAlerte.setContentText("Voulez-vous effectuer cet achat ?");
                        Optional<ButtonType> choix = achatAlerte.showAndWait();

                        if (choix.get() == ButtonType.OK) {
                            if (joueur.getArgent() >= elementDecors.getPrix()) {

                                Alert achatEffectue = new Alert(Alert.AlertType.INFORMATION);
                                achatEffectue.setTitle("Achat effectué");
                                achatEffectue.setHeaderText(null);
                                achatEffectue.setContentText("Merci pour votre achat !");
                                joueur.acheterObjet(elementDecors);
                                //Maj texte argent (décors)
                                texteArgentTotDecors.setText("Argent : " + joueur.getArgent() + " pièces");
                                //Maj texte argent (boutique)
                                texteArgentTotBoutique.setText("Argent : " + joueur.getArgent() + " pièces");

                                //Suppression du bouton Acheter pour mettre un texte
                                decorBox.getChildren().remove(butAcheter);
                                decorBox.getChildren().add(new Text("Acheté !"));

                                achatEffectue.showAndWait();

                                //Message d'erreur si pas assez d'argent
                            } else if (joueur.getArgent() < elementDecors.getPrix()) {
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
            compteur++;
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
        texteArgentTotSkin = new Text("Argent : " + joueur.getArgent() + " pièces");
        annonceBoutSkin.setTextAlignment(TextAlignment.CENTER);
        texteArgentTotSkin.setTextAlignment(TextAlignment.CENTER);


        //OBJETS DECORS
        GridPane fenDifSkins = new GridPane();
        fenDifSkins.setVgap(50);
        fenDifSkins.setHgap(50);
        fenDifSkins.setPadding(new Insets(10,40,30,30));

        //Parcours de la liste des décors pour ajouter à une box tous les éléments
        ArrayList<VBox> listeVBoxSkins = new ArrayList<VBox>();
        int compteur = 0;
        for (Skin elementSkin:Skin.getListSkins()) {
            if (compteur > 0) {
                VBox skinBox = new VBox();

                skinBox.getChildren().add(new Text(elementSkin.getTitre()));
                ImageView imageDecorView = new ImageView(new Image(elementSkin.getChemin()));
                skinBox.getChildren().add(imageDecorView);
                imageDecorView.setOnMouseClicked(mouseEvent -> {
                    Stage fenSkinGrand = new Stage();
                    Group rootSkinGrand = new Group();
                    Scene sceneSkinGrand = new Scene(rootSkinGrand, 190, 63);
                    fenSkinGrand.setTitle(elementSkin.getTitre());
                    rootSkinGrand.getChildren().add(new ImageView(new Image(elementSkin.getCheminGrand())));
                    fenSkinGrand.setScene(sceneSkinGrand);
                    fenSkinGrand.showAndWait();

                });
                skinBox.getChildren().add(new Text(elementSkin.getDescription()));
                skinBox.getChildren().add(new Text(String.valueOf(elementSkin.getPrix()) + " pièces"));

                if (!joueur.possedeObjet(elementSkin)) {
                    Button butAcheter = new Button("Acheter");
                    skinBox.getChildren().add(butAcheter);
                    butAcheter.setOnAction(actionEvent -> {
                        Alert achatAlerte = new Alert(Alert.AlertType.CONFIRMATION);
                        achatAlerte.setTitle("Confirmation d'achat");
                        achatAlerte.setHeaderText(null);
                        achatAlerte.setContentText("Voulez-vous effectuer cet achat ?");
                        Optional<ButtonType> choix = achatAlerte.showAndWait();

                        if (choix.get() == ButtonType.OK) {
                            if (joueur.getArgent() >= elementSkin.getPrix()) {

                                Alert achatEffectue = new Alert(Alert.AlertType.INFORMATION);
                                achatEffectue.setTitle("Achat effectué");
                                achatEffectue.setHeaderText(null);
                                achatEffectue.setContentText("Merci pour votre achat !");
                                joueur.acheterObjet(elementSkin);
                                //Maj texte argent (skins)
                                texteArgentTotSkin.setText("Argent : " + joueur.getArgent() + " pièces");
                                //Maj texte argent (boutique)
                                texteArgentTotBoutique.setText("Argent : " + joueur.getArgent() + " pièces");

                                //Suppression du bouton Acheter pour mettre un texte
                                skinBox.getChildren().remove(butAcheter);
                                skinBox.getChildren().add(new Text("Acheté !"));

                                achatEffectue.showAndWait();

                                //Message d'erreur si pas assez d'argent
                            } else if (joueur.getArgent() < elementSkin.getPrix()) {
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
            compteur++;
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
        Scene scene = new Scene(root, 600, 500, Color.WHITE);
        fenBoutiqueMusiqueTot.setTitle("Musiques");


        //FEN PRINCIPALE
        BorderPane fenBoutiqueMusique = new BorderPane();


        Text annonceBoutMusique = new Text("Bienvenue dans la boutique de musiques.\n Cliquez sur le bouton play pour jouer la musique.");
        texteArgentTotMusique = new Text("Argent : " + joueur.getArgent() + " pièces");
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

            if(!joueur.possedeObjet(elementMusiques)) {
                Button butAcheter = new Button("Acheter");
                musiqueBox.getChildren().add(butAcheter);
                butAcheter.setOnAction(actionEvent -> {
                    Alert achatAlerte = new Alert(Alert.AlertType.CONFIRMATION);
                    achatAlerte.setTitle("Confirmation d'achat");
                    achatAlerte.setHeaderText(null);
                    achatAlerte.setContentText("Voulez-vous effectuer cet achat ?");
                    Optional<ButtonType> choix = achatAlerte.showAndWait();

                    if(choix.get() == ButtonType.OK){
                        if (joueur.getArgent() >= elementMusiques.getPrix()){

                            Alert achatEffectue = new Alert(Alert.AlertType.INFORMATION);
                            achatEffectue.setTitle("Achat effectué");
                            achatEffectue.setHeaderText(null);
                            achatEffectue.setContentText("Merci pour votre achat !");
                            joueur.acheterObjet(elementMusiques);
                            //Maj texte argent (décors)
                            texteArgentTotMusique.setText("Argent : " + joueur.getArgent() + " pièces");
                            //Maj texte argent (boutique)
                            texteArgentTotBoutique.setText("Argent : " + joueur.getArgent() + " pièces");

                            //Suppression du bouton Acheter pour mettre un texte
                            musiqueBox.getChildren().remove(butAcheter);
                            musiqueBox.getChildren().add(new Text("Acheté !"));

                            achatEffectue.showAndWait();

                            //Message d'erreur si pas assez d'argent
                        } else if(joueur.getArgent() < elementMusiques.getPrix()){
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


}
