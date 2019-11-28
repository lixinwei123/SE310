package question;

import java.util.ArrayList;
import java.util.HashMap;

public class Emoji extends MultipleChoice {
    public Emoji() {
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
        this.tabList = new HashMap<>();
        this.tabList.put("very upset",0);
        this.tabList.put("upset",0);
        this.tabList.put("neutral",0);
        this.tabList.put("happy",0);
        this.tabList.put("very happy",0);
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
        while (true) {
            String in = this.in.promptAndGet("1).Modify prompt 2).Back");
            if (in.equals("1")) {
                this.loadPrompt("Enter a new prompt");
            } else if (in.equals("2")) {
                return;
            } else {
                this.out.display("not a valid choice, try again");
            }
        }
    }

    @Override
    public Integer getCorrectPoint() {
        return 0;
    }

    @Override
    public Integer promptAnswer(Boolean isTest) {
        this.userChoices = new ArrayList<>();
        while(true){
            String usrInput = this.in.promptAndGet("Please enter the answer. A/B/C/D..");
            int index = (int)usrInput.toCharArray()[0] - 65;
            if(index > this.choices.size() - 1 || index <0){
                this.out.display("The choice you have entered is not valid, try again");
                continue;
            }
                this.tabList.replace(this.choices.get(index), this.tabList.get(this.choices.get(index)) + 1);
                this.userChoices.add(this.choices.get(index));
                break;
        }
        this.responses.add(this.userChoices);
        return 0;
    }
}


