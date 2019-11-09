import java.util.ArrayList;

public class MultipleChoice extends Question {
    protected ArrayList<String> choices;
    protected String correctChoice;
    protected Output out;
    MultipleChoice(){
        this.out = new Output();
    }
    public void loadQuestion(){
        this.loadPrompt("Enter a prompt for your question");
        Integer numberOfChoices;
        while(true){
            numberOfChoices = this.in.getIntegerInput("Enter the number of choices for your multiple-choice question.");
            if(numberOfChoices > 10){
                this.out.display("wayyy too many choices for a multiple choice question, don't you agree?");
            }else{
                break;
            }
        }
        this.choices = this.in.getMultipleInput(numberOfChoices,"enter choice");
    }

    public void loadCorrectAnswer(String content){
        while(true){
            this.correctChoice = this.in.promptAndGet(content);
            if(choices.contains(this.correctChoice) == false){
                this.out.display("the choice you have entered does not match the criteria");
            }else{
                return;
            }
        }
    }

    @Override
    public void display() {
        this.out.display(this.prompt);
        for(Integer i = 0; i < this.choices.size();i++){
            this.out.displaySameLine((char)(i + 65) + "." + this.choices.get(i));
        }
    }

    public void displayCorrectA(){
        this.out.displaySameLine("The correct choice is" + this.correctChoice);
    }
}
