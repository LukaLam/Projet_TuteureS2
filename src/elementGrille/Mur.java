package elementGrille;
import java.util.List;

public class Mur extends ElementGrille {
    private List<Position> mur;

    public Mur() {
    }

    public Mur(List<Position> mur) {
        this.mur = mur;
    }

    public Mur(Position pos, List<Position> mur) {
        super(pos);
        this.mur = mur;
    }

    public List<Position> getMur() {
        return mur;
    }

    public void setMur(List<Position> mur) {
        this.mur = mur;
    }


}
