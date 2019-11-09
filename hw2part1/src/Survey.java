import java.io.*;
import java.util.ArrayList;

public class Survey implements Serializable {
    protected ArrayList<Question> questions;
    Survey(){
        this.questions = new ArrayList<>();
    }
    private Boolean isQuestionsNull = false; //check to see at least one question is created

    public void setQuestionNull(Boolean bool){
        this.isQuestionsNull = bool;
    }

    public Boolean getQuestionNull(){
        return this.isQuestionsNull;
    }

    public void addQuestion(Question question){
        this.questions.add(question);
    }


}
