package question;

import io.Output;
import java.util.ArrayList;

public class MultipleChoice extends Question {
    ArrayList<String> choices;
    ArrayList<String> correctChoices;
    private Integer numOfCorrectAns;
    protected Output out;
    public MultipleChoice(){
        this.out = new Output();
        this.correctChoices = new ArrayList<>();
    }
    public void loadQuestion(){
        this.loadPrompt("Enter a prompt for your question");
        Integer numberOfChoices;
        while(true){
            numberOfChoices = this.in.getIntegerInput("Enter the number of choices for your multiple-choice question.");
            if(numberOfChoices == null){
                continue;
            }else if (numberOfChoices < 2){
                this.out.display("At least two choices for a multiple choice");
            } else if(numberOfChoices > 500){
                this.out.display("wayyy too many choices for a multiple choice question, don't you agree?");
            }else{
                break;
            }
        }
        this.choices = this.in.getMultipleInput(numberOfChoices,"enter choice");
    }

    @Override
    public void loadCorrectAnswer(String content){
        while(true){
            this.numOfCorrectAns = this.in.getIntegerInput("Enter the number of correct answers for your multiple-choice question.");
            if(numOfCorrectAns == null){
            }else if (numOfCorrectAns < 1){
                this.out.display("you have entered less than the total number of choices you have");
            } else if(numOfCorrectAns > this.choices.size()){
                this.out.display("you have entered more than the total number of choices you have");
            }else{
                break;
            }
        }
        ArrayList<String>temp =  (ArrayList<String>)this.choices.clone();
        while(this.correctChoices.size() < this.numOfCorrectAns){
            for (int j = 1; j < temp.size() + 1; j++) {
                this.out.displaySameLine(j + ")" + temp.get(j - 1) + " ");
            }
            Integer in = this.in.getIntegerInput("\n" + content + " in integer index");
            if (in == null || in > temp.size() || in < 1) {
                this.out.display("The index you have entered is not valid ");
                continue;
            }
            String choice = temp.get(in - 1);
            if(this.correctChoices.contains(choice) || !this.choices.contains(choice)){
                this.out.display("the choice you entered is not found or you have already entered this choice");
            }else{
                temp.remove(choice);
                this.correctChoices.add(choice);
            }
        }
    }

    @Override
    public void display(Boolean bool) {
        this.out.display(this.prompt);
        for(int i = 0; i < this.choices.size(); i++){
            this.out.displaySameLine((char)(i + 65) + ")" + this.choices.get(i) + " " + "\n");
        }
        if(bool){
            this.out.display("The correct choice is ");
            for(int i = 0; i < this.correctChoices.size(); i++){
                this.out.displaySameLine((char)(i + 65) + ")" + this.correctChoices.get(i) + " " + "\n");
            }
        }
    }

}
