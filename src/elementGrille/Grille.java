package elementGrille;

public class Grille {
    protected int Largeur;
    protected int Longueur;

    public Grille(){
        Largeur = 10;
        Longueur = 10;
    }

    public Grille (int cote){
        Largeur = cote;
        Longueur = cote;
    }

    public Grille(int largeur, int longueur) {
        Largeur = largeur;
        Longueur = longueur;
    }

    public int getLargeur() {
        return Largeur;
    }

    public void setLargeur(int largeur) {
        Largeur = largeur;
    }

    public int getLongueur() {
        return Longueur;
    }

    public void setLongueur(int longueur) {
        Longueur = longueur;
    }
}
