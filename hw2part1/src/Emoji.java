import java.util.ArrayList;

public class Emoji extends MultipleChoice {
    Emoji(){
        this.choices = new ArrayList<>();
        this.choices.add("very upset");
        this.choices.add("upset");
        this.choices.add("neutral");
        this.choices.add("happy");
        this.choices.add("very happy");
    }
    public void loadQustion() {
        this.loadPrompt("Enter a prompt for your emoji question");
    }
}
