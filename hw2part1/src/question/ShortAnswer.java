package question;

import java.util.ArrayList;

public class ShortAnswer extends Essay {
    private Integer limit;
    private Integer numOfCorrectAns;
    private ArrayList<String> correctChoices;

    public ShortAnswer(){
        this.correctChoices = new ArrayList<>();
    }
    public void setLimit(){
        while(true){
            this.limit = this.in.getIntegerInput("enter a limit of characters for the short answer");
            if( this.limit < 1){
                this.out.display("limit must be at least 1");
            } else if(this.limit != null){
                return;
            }
        }
    }

    public void loadQuestion(){
        this.loadPrompt("Enter a prompt for the short answer");
        this.setLimit();
    }

    @Override
    public void loadCorrectAnswer(String content){
        while(true){
            this.numOfCorrectAns = this.in.getIntegerInput("Enter the number of correct answers for your multiple-choice question.");
            if(numOfCorrectAns == null){
                continue;
            }else if (numOfCorrectAns < 1){
                this.out.display("you have to enter at least 1 correct answer");
            } else{
                break;
            }
        }
        Integer index = 1;
        while(this.correctChoices.size() < this.numOfCorrectAns){
            String in = this.in.promptAndGet(index.toString() + ")" + content);
            if(this.correctChoices.contains(in) ){
                this.out.display("the choice you entered is not found or you have already entered this choice");
            }else if(in.split(" ").length > this.limit){
                this.out.display("the choice you entered is greater than the total limit that you have set");
                continue;
            }
            else{
                this.correctChoices.add(in);
            }
            index += 1;
        }
    }
    @Override
    public void display(Boolean isTest) {
        this.out.display("Short answer prompt:" + this.prompt);
        if(isTest && this.numOfCorrectAns > 0){
            this.out.display("correct answers:");
            for(int i = 1; i < this.numOfCorrectAns + 1; i ++){
                this.out.display(i + ")" + this.correctChoices.get(i - 1));
            }
        }
    } 

}
