package elementGrille;

import javafx.scene.paint.Color;

public class Fruit extends ElementGrille {
    //La on est assez libre, on fait différent fruit ?
    // On peut faire des fruits qui font grandir de 2 élément au lieux d'un seul, ou des fruit bonus par exemple, a vous de voir
    int posX = 0;
    int posY = 0;
    Color couleurFruit[] = { Color.LIGHTYELLOW, Color.LIGHTCYAN, Color.LIGHTSEAGREEN};

    //ex génération de fruit : posX = Maths.random * longueurTableauDeJeu
    //                         posY = Maths.random * longueurTableauDeJeu
}
