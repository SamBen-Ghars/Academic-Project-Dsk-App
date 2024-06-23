package tn.esprit.fastkh.models;

public class Question {

    private int idQues ;
    private  String contenus;
    private String difficulte ;
    private  int score ;


    public Question (){}

    public Question(int idQues, String contenus, String difficulte, int score) {
        this.idQues = idQues;
        this.contenus = contenus;
        this.difficulte = difficulte;
        this.score = score;
    }

    public Question(String contenus, String difficulte, int score) {
        this.contenus = contenus;
        this.difficulte = difficulte;
        this.score = score;
    }

    public int getIdQues() {
        return idQues;
    }

    public void setIdQues(int idQues) {
        this.idQues = idQues;
    }

    public String getContenus() {
        return contenus;
    }

    public void setContenus(String contenus) {
        this.contenus = contenus;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Question{" +
                "idQues=" + idQues +
                ", contenus='" + contenus + '\'' +
                ", difficulte='" + difficulte + '\'' +
                ", score=" + score +
                '}';
    }
}
