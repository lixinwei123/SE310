import java.util.ArrayList;

public class Test extends Survey {
//    private ResponseCorrectAnswer RCA;
    private ArrayList<Question>correctAnswers;
//    Test(){
//        this.RCA = new ResponseCorrectAnswer();
//    }

    public void addAnswer(Question q){
        this.correctAnswers.add(q);
    }

}
