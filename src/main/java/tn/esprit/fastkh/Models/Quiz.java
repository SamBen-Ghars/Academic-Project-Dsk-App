package tn.esprit.fastkh.Models;

public class Quiz {

    private int idQuiz ;
    private String tire  ;
    private String Question ;


    public Quiz (){}

    public Quiz(int idQuiz, String tire, String question) {
        this.idQuiz = idQuiz;
        this.tire = tire;
        Question = question;
    }


    public Quiz(String tire, String question) {
        this.tire = tire;
        Question = question;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getTire() {
        return tire;
    }

    public void setTire(String tire) {
        this.tire = tire;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "idQuiz=" + idQuiz +
                ", tire='" + tire + '\'' +
                ", Question='" + Question + '\'' +
                '}';
    }
}
