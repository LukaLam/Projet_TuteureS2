package mvcSnake;

public class ModelSnake {
    // Ce qui se passe sur le snake lorsque l'on joue au jeux
    int xVelocite=0;
    int yVelocite=0;

    public int getxVelocite() {
        return xVelocite;
    }

    public int getyVelocite() {
        return yVelocite;
    }


    public void setxVelocite(int xVelocite) {
        this.xVelocite = xVelocite;
    }

    public void setyVelocite(int yVelocite) {
        this.yVelocite = yVelocite;
    }

    public void goHaut(){
        xVelocite=0;
        yVelocite=1;
    }
    public void goBas(){
        xVelocite=0;
        yVelocite=-1;
    }
    public void goGauche(){
        xVelocite=-1;
        yVelocite=0;
    }
    public void goDroite(){
        xVelocite=1;
        yVelocite=0;
    }
}
