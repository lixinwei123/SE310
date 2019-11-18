package survey;

import question.Question;

import java.io.*;
import java.util.ArrayList;

public class Survey implements Serializable {
    protected ArrayList<Question> questions;
    public Survey(){
        this.questions = new ArrayList<>();
    }
    private Boolean isQuestionsNull = false; //check to see at least one question is created

    public void addQuestion(Question question){
        this.questions.add(question);
    }

    public void setQuestionNull(Boolean bool){
        this.isQuestionsNull = bool;
    }

    public Boolean getQuestionNull(){
        return this.isQuestionsNull;
    }

    public Integer getQuestionSize(){
        return this.questions.size();
    }

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }
}
