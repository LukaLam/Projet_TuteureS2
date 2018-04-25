package sample;

import javafx.scene.Node;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.Parent;

public class Clavier extends Parent {

    private Bouton[] boutons;
    private Instru instru;//on déclare un objet de type Instru
    public Clavier(Instru ins) {
        instru = ins;
        Rectangle fond_clavier = new Rectangle();
        fond_clavier.setWidth(900);
        fond_clavier.setHeight(625);
        fond_clavier.setArcWidth(150);
        fond_clavier.setArcHeight(100);
        fond_clavier.setFill( //on remplie notre rectangle avec un dégradé
                new LinearGradient(0f, 0f, 0f, 1f, true, CycleMethod.NO_CYCLE,
                        new Stop[]{
                                new Stop(0, Color.web("#333333")),
                                new Stop(1, Color.web("#000000"))
                        }
                )
        );
        Reflection r = new Reflection();//on applique un effet de réflection
        r.setFraction(0.25);
        r.setBottomOpacity(0);
        r.setTopOpacity(0.5);
        fond_clavier.setEffect(r);

        boutons = new Bouton[]{
                new Bouton("Jouer à la CAMPAGNE",300,15,60,instru),
                new Bouton("Jouer en mode ARCADE",300,115,62,instru),
                new Bouton("Acceder à la boutique",300,215,64,instru),
                new Bouton("Profil",300,320,65,instru),
                new Bouton("Option",300,425,67,instru),
                new Bouton("Aide",300,530,69,instru),
        };







        this.setTranslateX(200);
        this.setTranslateY(10);
        this.getChildren().add(fond_clavier);

        for (Bouton bouton: boutons){

            this.getChildren().add(bouton);

        }

    }
}