package joueur;

import java.io.Serializable;

public class Score implements Serializable {
    private int ScoreActuel;
    private int MeilleureScore;

    public Score(){
    }

    public Score(int scoreActuel) {
        ScoreActuel = scoreActuel;
        MeilleureScore = 0;
    }

    public Score(int scoreActuel, int meilleureScore) {
        ScoreActuel = scoreActuel;
        MeilleureScore = meilleureScore;
    }

    public int getScoreActuel() {
        return ScoreActuel;
    }

    public int getMeilleureScore() {
        return MeilleureScore;
    }

}
