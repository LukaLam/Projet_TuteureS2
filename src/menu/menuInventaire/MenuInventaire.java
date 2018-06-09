package menu.menuInventaire;
import javafx.geometry.Insets;
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
import jeux.Main;
import joueur.Joueur;
import menu.menuBoutique.Item;
import menu.menuBoutique.Decors;
import menu.menuBoutique.Musique;
import menu.menuBoutique.Skin;
import menu.menuModele.Modele;

import java.io.File;
import java.util.ArrayList;


public class MenuInventaire extends Scene {
    Joueur joueur;

    Group root;
    VBox container;
    Text annonceInventaire;
    Button butInventaireDecor, butInventaireSkin, butInventaireMusique, butMenuPrincipal;
    Image bouton_play, bouton_stop;

    public MenuInventaire(Joueur joueur){
        super(new Group(), 800, 800);

        this.joueur = joueur;

        initAttribut();
        ajouterAddAttribut();
        addMouseEvent();
    }

    public void initAttribut(){

        bouton_play = new Image("img/musique/bouton_play.png");
        bouton_stop = new Image("img/musique/bouton_stop.png");

        annonceInventaire = new Text(50,100,"Voici votre inventaire. Vous y trouverez tous les objets que vous avez acheté dans la boutique.");
        butInventaireDecor = new Button("Vos décors");
        butInventaireSkin = new Button("Vos modifications d'apparence");
        butInventaireMusique = new Button("Vos musiques");
        butMenuPrincipal = new Button("Quitter");

        container = new VBox();
    }

    public void ajouterAddAttribut(){
        root = (Group)this.getRoot();

        container.getChildren().add(annonceInventaire);
        container.getChildren().add(butInventaireDecor);
        container.getChildren().add(butInventaireSkin);
        container.getChildren().add(butInventaireMusique);
        container.getChildren().add(butMenuPrincipal);

        root.getChildren().add(container);
    }

    public void addMouseEvent(){
        butInventaireDecor.setOnAction(actionEvent -> ouvrirInventaireDecor());
        butInventaireSkin.setOnAction(actionEvent -> ouvrirInventaireSkin());
        butInventaireMusique.setOnAction(actionEvent -> ouvrirInventaireMusique());
        butMenuPrincipal.setOnAction(event -> {
            //mainMenuButton.setVisible(false);
            Main.getStage().setScene(Main.getModele().getMenuPrincipal());


        });
    }

    public void ouvrirInventaireDecor(){
        //Fen root
        Stage fenInventaireDecorTot = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 800,520, Color.WHITE);

        //Fen principale
        BorderPane fenInventaireDecor = new BorderPane();
        Text annonceInventaireDecor = new Text("Sélectionnez un décor pour l'appliquer en jeu. \nCliquez sur l'image pour l'agrandir");
        annonceInventaireDecor.setTextAlignment(TextAlignment.CENTER);

        //Objets décors
        GridPane fenInventaireDifDecors = new GridPane();
        fenInventaireDifDecors.setVgap(50);
        fenInventaireDifDecors.setHgap(50);
        fenInventaireDifDecors.setPadding(new Insets(10,40,30,30));

        ArrayList<VBox> listeVBoxDecors = new ArrayList<>();
        for(Item item: joueur.getInventaire()){
            if(item instanceof Decors){
                VBox decorInventaireBox = new VBox();
                decorInventaireBox.getChildren().add(new Text(item.getTitre()));

                ImageView imageDecorView = new ImageView(new Image(item.getChemin()));
                decorInventaireBox.getChildren().add(imageDecorView);
                imageDecorView.setOnMouseClicked(mouseEvent -> {
                    Stage fenDecorGrand = new Stage();
                    Group rootDecorGrand = new Group();
                    Scene sceneDecorGrand = new Scene(rootDecorGrand, 1280,720);
                    fenDecorGrand.setTitle(item.getTitre());
                    rootDecorGrand.getChildren().add(new ImageView(new Image(item.getCheminGrand())));
                    fenDecorGrand.setScene(sceneDecorGrand);
                    fenDecorGrand.showAndWait();

                });

                decorInventaireBox.getChildren().add(new Text(item.getDescription()));

                Text textSelectionne = new Text("Sélectionné !");
                if(Main.getModele().getDecorSelectionne() != item){
                    Button boutonSelection = new Button("Sélectionner");
                    decorInventaireBox.getChildren().add(boutonSelection);
                    boutonSelection.setOnAction(actionEvent -> {
                        Main.getModele().setDecorSelectionne((Decors)item);
                        for(VBox vbox: listeVBoxDecors){
                            if(Main.getModele().getDecorSelectionne() != item){
                                vbox.getChildren().remove(textSelectionne);
                                vbox.getChildren().add(boutonSelection);
                            } else {
                                vbox.getChildren().remove(boutonSelection);
                                vbox.getChildren().add(textSelectionne);
                            }
                        }
                        fenInventaireDecorTot.close();
                        ouvrirInventaireDecor();
                    });
                } else {
                    decorInventaireBox.getChildren().add(textSelectionne);
                }
                listeVBoxDecors.add(decorInventaireBox);
            }
        }

        int colonne = 0;
        int ligne = 0;
        for (VBox decorBox:listeVBoxDecors) {
            fenInventaireDifDecors.add(decorBox, colonne, ligne);
            //Une fois atteint 3 => revient à 0
            colonne = (colonne+1)%3;
            //Si colonne revient à 0, on change de ligne
            if(colonne==0){
                ligne++;
            }
        }

        //Ajout final
        fenInventaireDecor.setTop(annonceInventaireDecor);
        fenInventaireDecor.setCenter(fenInventaireDifDecors);
        root.getChildren().add(fenInventaireDecor);

        //AFFICHAGE
        fenInventaireDecorTot.setScene(scene);
        fenInventaireDecorTot.show();

    }

    public void ouvrirInventaireSkin(){
        //Fen root
        Stage fenInventaireSkinTot = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 500,500, Color.WHITE);

        //Fen principale
        BorderPane fenInventaireSkin = new BorderPane();
        Text annonceInventaireSkin = new Text("Sélectionnez une apparence pour l'appliquer sur le serpent en jeu. \nCliquez sur l'image pour l'agrandir");
        annonceInventaireSkin.setTextAlignment(TextAlignment.CENTER);

        //Objets décors
        GridPane fenInventaireDifSkin = new GridPane();
        fenInventaireDifSkin.setVgap(50);
        fenInventaireDifSkin.setHgap(50);
        fenInventaireDifSkin.setPadding(new Insets(10,40,30,30));

        ArrayList<VBox> listeVBoxSkin = new ArrayList<>();
        for(Item item: joueur.getInventaire()){
            if(item instanceof Skin){
                VBox skinInventaireBox = new VBox();
                skinInventaireBox.getChildren().add(new Text(item.getTitre()));

                ImageView imageDecorView = new ImageView(new Image(item.getChemin()));
                skinInventaireBox.getChildren().add(imageDecorView);
                imageDecorView.setOnMouseClicked(mouseEvent -> {
                    Stage fenSkinGrand = new Stage();
                    Group rootSkinGrand = new Group();
                    Scene sceneSkinGrand = new Scene(rootSkinGrand, 190,63);
                    fenSkinGrand.setTitle(item.getTitre());
                    rootSkinGrand.getChildren().add(new ImageView(new Image(item.getCheminGrand())));
                    fenSkinGrand.setScene(sceneSkinGrand);
                    fenSkinGrand.showAndWait();

                });

                skinInventaireBox.getChildren().add(new Text(item.getDescription()));

                Text textSelectionne = new Text("Sélectionné !");
                if(Main.getModele().getSkinSelectionne() != item){
                    Button boutonSelection = new Button("Sélectionner");
                    skinInventaireBox.getChildren().add(boutonSelection);
                    boutonSelection.setOnAction(actionEvent -> {
                        Main.getModele().setSkinSelectionne((Skin)item);
                        for(VBox vbox: listeVBoxSkin){
                            if(Main.getModele().getSkinSelectionne() != item){
                                vbox.getChildren().remove(textSelectionne);
                                vbox.getChildren().add(boutonSelection);
                            } else {
                                vbox.getChildren().remove(boutonSelection);
                                vbox.getChildren().add(textSelectionne);
                            }
                        }
                        fenInventaireSkinTot.close();
                        ouvrirInventaireSkin();
                    });
                } else {
                    skinInventaireBox.getChildren().add(textSelectionne);
                }
                listeVBoxSkin.add(skinInventaireBox);
            }
        }

        int colonne = 0;
        int ligne = 0;
        for (VBox decorBox:listeVBoxSkin) {
            fenInventaireDifSkin.add(decorBox, colonne, ligne);
            //Une fois atteint 3 => revient à 0
            colonne = (colonne+1)%3;
            //Si colonne revient à 0, on change de ligne
            if(colonne==0){
                ligne++;
            }
        }

        //Ajout final
        fenInventaireSkin.setTop(annonceInventaireSkin);
        fenInventaireSkin.setCenter(fenInventaireDifSkin);
        root.getChildren().add(fenInventaireSkin);

        //AFFICHAGE
        fenInventaireSkinTot.setScene(scene);
        fenInventaireSkinTot.show();
    }

    public void ouvrirInventaireMusique(){
        //FEN ROOT
        Stage fenInventaireMusiqueTot = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        fenInventaireMusiqueTot.setTitle("Musiques");


        //FEN PRINCIPALE
        BorderPane fenInventaireMusique = new BorderPane();


        Text annonceInventaireMusique = new Text("Voici vos musiques. Sélectionnez-en une pour l'appliquer dans le jeu.\n Cliquez sur le bouton play pour jouer la musique.");
        annonceInventaireMusique.setTextAlignment(TextAlignment.CENTER);


        //OBJETS DECORS
        GridPane fenDifInventaireMusique = new GridPane();
        fenDifInventaireMusique.setVgap(50);
        fenDifInventaireMusique.setHgap(50);
        fenDifInventaireMusique.setPadding(new Insets(10,40,30,30));

        //Parcours de la liste des décors pour ajouter à une box tous les éléments
        ArrayList<VBox> listeVBoxInventaireMusique = new ArrayList<VBox>();
        for (Item item:joueur.getInventaire()) {
            if(item instanceof Musique) {
                VBox musiqueBox = new VBox();

                musiqueBox.getChildren().add(new Text(item.getTitre()));

                ToggleButton togglebouton_play = new ToggleButton();
                togglebouton_play.setGraphic(new ImageView(bouton_play));
                ToggleButton togglebouton_stop = new ToggleButton();
                togglebouton_stop.setGraphic(new ImageView(bouton_stop));
                ToggleGroup groupPlayStop = new ToggleGroup();
                togglebouton_play.setToggleGroup(groupPlayStop);
                togglebouton_stop.setToggleGroup(groupPlayStop);

                File file = new File(item.getChemin());
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

                fenInventaireMusiqueTot.setOnCloseRequest(windowEvent -> {
                    player.stop();
                });

                musiqueBox.getChildren().add(new Text(item.getDescription()));

                Text textSelectionne = new Text("Sélectionné !");
                if(Main.getModele().getMusiqueSelectionee() != item){
                    Button boutonSelection = new Button("Sélectionner");
                    musiqueBox.getChildren().add(boutonSelection);
                    boutonSelection.setOnAction(actionEvent -> {
                        Main.getModele().setMusiqueSelectionee((Musique)item);
                        for(VBox vbox: listeVBoxInventaireMusique){
                            if(Main.getModele().getMusiqueSelectionee() != item){
                                vbox.getChildren().remove(textSelectionne);
                                vbox.getChildren().add(boutonSelection);
                            } else {
                                vbox.getChildren().remove(boutonSelection);
                                vbox.getChildren().add(textSelectionne);
                            }
                        }
                        fenInventaireMusiqueTot.close();
                        ouvrirInventaireMusique();
                    });
                } else {
                    musiqueBox.getChildren().add(textSelectionne);
                }
                listeVBoxInventaireMusique.add(musiqueBox);
            }


        }

        //Parcours de la liste box décors pour les ajouter au gridpane
        int colonne = 0;
        int ligne = 0;
        for (VBox musiqueBox:listeVBoxInventaireMusique) {
            fenDifInventaireMusique.add(musiqueBox, colonne, ligne);
            //Une fois atteint 3 => revient à 0
            colonne = (colonne+1)%3;
            //Si colonne revient à 0, on change de ligne
            if(colonne==0){
                ligne++;
            }
        }


        //Ajout final
        fenInventaireMusique.setTop(annonceInventaireMusique);
        fenInventaireMusique.setCenter(fenDifInventaireMusique);
        root.getChildren().add(fenInventaireMusique);

        //AFFICHAGE
        fenInventaireMusiqueTot.setScene(scene);
        fenInventaireMusiqueTot.show();
    }
}
