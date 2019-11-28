package question;

import java.util.ArrayList;

public class Essay extends Question {
    ArrayList<String> responses;

    public Essay(){
        this.responses = new ArrayList<>();
    }
    @Override
    public void loadQuestion() {
        this.loadPrompt("Enter a prompt for the essay");
    }

    @Override
    public void display(Boolean isTest) {
        this.out.display("Essay prompt:" + this.prompt);
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

    @Override
    public Integer promptAnswer(Boolean isTest) {
        String in = this.in.promptAndGet("Please enter the essay text below");
        this.responses.add(in);
        return 0;
    }

    @Override
    public Integer getCorrectPoint() {
        return 0;
    }

    @Override
    public void displayReplies() {
        this.out.display("Replies:");
        for(int i = 0; i <this.responses.size(); i++){
            this.out.display(this.responses.get(i));
        }
    }

    @Override
    public void displayTabulate() {
        this.out.display("Tabulation:");
        for(int i = 0; i <this.responses.size(); i++){
            this.out.display(this.responses.get(i) );
        }
    }
}
