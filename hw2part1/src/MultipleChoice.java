import java.util.ArrayList;

public class MultipleChoice extends Question {
    protected ArrayList<String> choices;
    protected String correctChoice;
    protected Output out;
    MultipleChoice(){
        this.out = new Output();
    }
    public void loadQustion(){
        this.loadPrompt("Enter a prompt for your question");
        Integer numberOfChoices = this.in.getIntegerInput("Enter the number of choices for your multiple-choice question.");
        this.choices = this.in.getMultipleInput(numberOfChoices,"enter choice");
    }

    public void loadCorrectAnswer(){
        while(true){
            String cc = this.in.promptAndGet("Enter the correct answer");
            if(choices.contains(cc) == false){
                this.out.display("Choice is not part of the choices you have entered before, enter again");
            }else{
                return;
            }
        }
    }
}
