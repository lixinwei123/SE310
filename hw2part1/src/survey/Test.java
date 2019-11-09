package survey;

import question.Question;

import java.util.ArrayList;

public class Test extends Survey {
    private ArrayList<Question>correctAnswers;

    public void addAnswer(Question q){
        this.correctAnswers.add(q);
    }

}
