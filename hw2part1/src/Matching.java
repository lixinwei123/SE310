import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Matching extends Question {
    private ArrayList<String> column1;
    private ArrayList<String> column2;
    private ArrayList<String> usedMatches;
    private HashMap<String, String> correctAnswers;
    private Integer totalMatch;
    Matching(){
        this.correctAnswers = new HashMap<>();
        this.usedMatches = new ArrayList<>();
    }
    @Override
    public void loadQuestion() {
        this.loadPrompt("Enter a prompt for your question");
        this.totalMatch = this.in.getIntegerInput("enter the total of amount of match choices");
        this.out.display("enter choices for your first column");
        this.column1 =this.loadColumn("first");
        this.out.display("enter choices for your second column");
        this.column2 = this.loadColumn("second");
    }

    public ArrayList<String> loadColumn(String column){
        return this.in.getMultipleInput(this.totalMatch,"match choice no");
    }

    public void loadCorrectAnswer(){
        this.out.display("enter the correct answer for each match ");
        for(var i = 0; i < column1.size(); i++){
            while(true){
                String match = this.in.promptAndGet(column1.get(i));
                if(column2.contains(match) == false || this.usedMatches.contains(match) == true){
                    this.out.display("the choice you entered is not found or you have already entered this choice, please enter again");
                }else{
                    this.correctAnswers.put(column1.get(i),match);
                    this.usedMatches.add(match);
                    break;
                }
            }
        }
    }

    @Override
    public void display() {

    }
}
