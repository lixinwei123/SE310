package question;

import java.util.ArrayList;

public class TrueFalse extends MultipleChoice{
    public TrueFalse(){
        this.choices = new ArrayList<>();
        this.choices.add("true");
        this.choices.add("false");
    }
    @Override
    public void loadQuestion() {
        this.loadPrompt("Enter a prompt for your True/False question");
    }

}
