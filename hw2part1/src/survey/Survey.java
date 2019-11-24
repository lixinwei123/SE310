package survey;

import io.Output;
import question.Matching;
import question.MultipleChoice;
import question.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Survey implements Serializable {
    protected ArrayList<Question> questions;
    protected Integer totalScore = 0;
    protected Integer availScore = 0;
    protected Integer surveyId;
    protected Output out;
    protected HashMap<String,String> surveys;
    String surveyName;
    public Survey(){
        this.questions = new ArrayList<>();
        this.surveys = new HashMap<>();
//        this.totalScore =0;
//        this.availScore = 0;
        this.out = new Output();
        this.surveyId = 0;
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

    public Question getQuestion(Integer index){
        return this.questions.get(index - 1);
    }

    public void takeSurvey(Boolean isTest){
        this.totalScore = 0;
        this.availScore = 0;
        this.out = new Output();
        if(isTest){
            for(int i =0; i <questions.size(); i++){
                this.questions.get(i).display(false);
                this.totalScore += this.questions.get(i).promptAnswer(true);
                this.availScore += this.questions.get(i).getCorrectPoint();
            }
            this.out.display("Total score is " + this.totalScore.toString() + "/" + this.availScore.toString());
            this.surveys.put(this.surveyId.toString(), this.totalScore.toString() + "/" + this.availScore.toString());
        }else{
            for(int i =0; i <questions.size(); i++){
                this.questions.get(i).display(false);
                this.questions.get(i).promptAnswer(false);
            }
        }
    }

    public String getSurveyId(){
        return this.surveyId.toString();
    }

    public void incId(){
        this.surveyId += 1;
    }

    public void setSurveyName(String name){
        this.surveyName = name;
    }

    public String getSurveyName(){
        return this.surveyName;
    }

    public void displayGrade(){
        this.surveys.forEach((key,val)->{
            this.out.display("id " + key +": "+val);
        });
    }
}
