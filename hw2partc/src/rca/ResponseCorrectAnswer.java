package rca;

import question.Question;

import java.util.ArrayList;
/*will be implemented for part C */
public class ResponseCorrectAnswer {
    private ArrayList<Question> responses;
    private ArrayList<Question> correctAnswers;

    public void setResponses(ArrayList<Question> res){
        this.responses = res;
    }

    public void setCorrectAnswers(ArrayList<Question> ca){
        this.responses = ca;
    }
}
