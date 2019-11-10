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
        while(true){
            this.totalMatch = this.in.getIntegerInput("enter the total of amount of match choices");
            if(this.totalMatch == null){
            } else if(this.totalMatch > 1000){
                this.out.display("wayy too many choices, don't you agree?");
            } else if (this.totalMatch < 2){
                this.out.display("please enter at least 2 matches");
            }
            else{
                break;
            }
        }
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
        this.out.display("enter the correct answer for each match by typing in the index number");
        for (String s : column1) {
            ArrayList<String> temp =  (ArrayList<String>)this.column2.clone();
            while (true) {
                for (int j = 1; j < temp.size() + 1; j++) {
                    this.out.displaySameLine(j + ")" + temp.get(j - 1) + " ");
                }
                Integer index = this.in.getIntegerInput("\n please select the correct answer for: " + s);
                if (index == null || index > temp.size() || index < 1) {
                    this.out.display("The index you have entered is not valid ");
                    continue;
                }
                String match = temp.get(index - 1);
                if (!column2.contains(match) || this.usedMatches.contains(match)) {
                    this.out.display("the choice you entered is not found or you have already entered this choice, please enter again");
                } else {
                    temp.remove(match);
                    this.correctAnswers.put(s, match);
                    this.usedMatches.add(match);
                    break;
                }
            }
        }
    }

    @Override
    public void display(Boolean bool) {
        this.out.display(prompt);
        for(int i = 1; i < this.totalMatch + 1; i++){
            this.out.display(this.column1.get(i - 1) + "  |  " + this.column2.get(i - 1));
        }
        if(bool){
            int index =1;
            this.out.display("correct answers:");
            for(Map.Entry<String,String> entry : correctAnswers.entrySet()){
                this.out.display(Integer.toString(index) + ")" + entry.getKey() + " ---> " + entry.getValue());
                index += 1;
            }
        }
    }
}
