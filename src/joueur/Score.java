package joueur;

import java.io.Serializable;

public class Score implements Serializable {
    private int ScoreActuel;
    private int MeilleureScore;

    public Score(){
        ScoreActuel = 0;
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

    public void setScoreActuel(int scoreActuel) {
        ScoreActuel = scoreActuel;
    }

    public void setMeilleureScore(int meilleureScore) {
        MeilleureScore = meilleureScore;
    }
}
