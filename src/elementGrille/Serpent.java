package elementGrille;

import java.util.List;

public class Serpent extends ElementGrille {
    private List<Position> positionCorps;
    private Position testPosition;
    // pas d'attribut positionTête car on l'hérite de élémentGrille

    public Serpent(){}

    /*public Serpent(){
        // on initialise deux bout de corps
    }*/

    public Serpent(List<Position> positionCorps, Position testPosition) {
        this.positionCorps = positionCorps;
        this.testPosition = testPosition;
    }

    public Serpent(Position pos, List<Position> positionCorps, Position testPosition) {
        super(pos);
        this.positionCorps = positionCorps;
        this.testPosition = testPosition;
    }

    public Position getTete(){
        //donne la position de la tête
        return testPosition;
    }

    public List<Position> getPositionCorps() {
        return positionCorps;
    }

    public void setPositionCorps(List<Position> positionCorps) {
        this.positionCorps = positionCorps;
    }

    public Position getTestPosition() {
        return testPosition;
    }

    public void setTestPosition(Position testPosition) {
        this.testPosition = testPosition;
    }
}
