package question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Matching extends Question {
    private ArrayList<String> column1;
    private ArrayList<String> column2;
    private ArrayList<String> usedMatches;
    private HashMap<String, String> correctAnswers;
    private Integer totalMatch;
    public Matching(){
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

    @Override
    public void loadCorrectAnswer(String content){
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
    public void display(Boolean bool) {
        this.out.display(prompt);
        for(Integer i = 1; i < this.totalMatch + 1; i++){
            this.out.display(this.column1.get(i - 1) + "    " + this.column2.get(i - 1));
        }
        if(bool){
            Integer index =1;
            this.out.display("correct answers:");
            for(Map.Entry<String,String> entry : correctAnswers.entrySet()){
                this.out.display(index.toString() + "." + entry.getKey() + "   " + entry.getValue());
                index += 1;
            }
        }
    }
}
