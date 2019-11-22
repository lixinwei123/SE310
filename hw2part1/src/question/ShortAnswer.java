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
            this.numOfCorrectAns = this.in.getIntegerInput("Enter the number of correct answers for your short answer question.");
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

    @Override
    public void modifyQuestion(Boolean isTest) {
        while(true){
            if(isTest){
                String in = this.in.promptAndGet("1).Modify prompt 2).Modify correct answer 3).Remove correct answer 4).Add correct answer 5)Modify word limit 6).back");
                switch(in){
                    case "1":
                        this.loadPrompt("Enter a new prompt");
                        break;
                    case "2":
                        while(true){
                            this.displayCorrectAnswer();
                            Integer in2 = this.in.getIntegerInput("\n" + "answer" + " in integer index");
                            if (in2 == null || in2 < 1 || in2 > this.correctChoices.size()) {
                                this.out.display("The index you have entered is not valid ");
                                continue;
                            }
                            String choice = this.correctChoices.get(in2 - 1);
                            while(true){
                                String usrInput4 = this.in.promptAndGet("please enter replacement text for this answer");
                                if(this.correctChoices.contains(usrInput4)){
                                    this.out.display("You have already entered this, please try again.");
                                    continue;
                                }
                                    this.correctChoices.set(in2-1,usrInput4);
                                    break;
                            }
                            break;
                        }
                        break;
                    case "3":
                        while(true){
                            this.displayCorrectAnswer();
                            Integer in2 = this.in.getIntegerInput("\n" + "answer" + " in integer index");
                            if (in2 == null || in2 < 1 || in2 > this.correctChoices.size()) {
                                this.out.display("The index you have entered is not valid ");
                                continue;
                            }
                            String choice = this.correctChoices.get(in2 - 1);
                            this.correctChoices.remove(choice);
                            this.out.display("Successfully removed");
                            break;
                        }
                        break;
                    case "4":
                        while(true){
                            this.displayCorrectAnswer();
                            while(true){
                                String usrInput4 = this.in.promptAndGet("please enter a new answer");
                                if(this.correctChoices.contains(usrInput4)){
                                    this.out.display("You have already entered this, please try again.");
                                    continue;
                                }
                                this.correctChoices.add(usrInput4);
                                break;
                            }
                            break;
                        }
                        break;
                    case "5":
                        this.displayLimit();
                        break;
                    case "6":
                        return;
                    default:
                        this.out.display("not a valid choice, try again");
                        break;
                }
            }else{
                String in = this.in.promptAndGet("1).Modify prompt 2).Modify word limit 3).Back");
                switch(in){
                    case "1":
                        this.loadPrompt("Enter a new prompt");
                        break;
                    case "2":
                        this.displayLimit();
                        break;
                    case "3":
                        return;
                    default:
                        this.out.display("not a valid choice, try again");
                        break;
                }
            }
        }
    }

    public void displayCorrectAnswer(){
        this.out.display("limit:" + this.limit);
        for (int i = 0; i < correctChoices.size(); i++) {
            this.out.displaySameLine( (i + 1 + ")" + correctChoices.get(i) + " " + "\n"));
        }

    }
    public void displayLimit(){
        while(true){
            Integer in2 = this.in.getIntegerInput("please enter a new limit");
            if(in2 < 0){
                this.out.display("Invalid limit, please try again");
                continue;
            }
            this.limit = in2;
            break;
        }
    }

}
