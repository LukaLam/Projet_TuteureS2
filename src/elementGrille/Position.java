package elementGrille;

public class Position {
    private int axeX;
    private int axeY;

    public Position(){
    }

    public Position(int axeX, int axeY) {
        this.axeX = axeX;
        this.axeY = axeY;
    }

    public int getAxeX() {
        return axeX;
    }

    public int getAxeY() {
        return axeY;
    }

    public void setAxeX(int axeX) {
        this.axeX = axeX;
    }

    public void setAxeY(int axeY) {
        this.axeY = axeY;
    }
}
