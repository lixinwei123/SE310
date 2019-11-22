package question;

import java.util.ArrayList;

public class Emoji extends MultipleChoice {

    public Emoji(){
        this.choices = new ArrayList<>();
        this.choices.add("very upset");
        this.choices.add("upset");
        this.choices.add("neutral");
        this.choices.add("happy");
        this.choices.add("very happy");
    }
    @Override
    public void loadQuestion() {
        this.loadPrompt("Enter a prompt for your emoji question");
    }

    @Override
    public void display(Boolean bool) {
        super.display(false);
    }

    @Override
    public void loadCorrectAnswer(String content) {
    }

    @Override
    public void modifyQuestion(Boolean isTest) {
        while(true){
                String in = this.in.promptAndGet("1).Modify prompt 2).Back");
                if(in.equals("1")){
                    this.loadPrompt("Enter a new prompt");
                }else if(in.equals("2")) {
                    return;
                }else{
                    this.out.display("not a valid choice, try again");
                }
        }
    }
}


