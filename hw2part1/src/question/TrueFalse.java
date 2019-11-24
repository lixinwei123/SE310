package question;

import java.util.ArrayList;
import java.util.HashMap;

public class TrueFalse extends MultipleChoice{
    public TrueFalse(){
        this.choices = new ArrayList<>();
        this.choices.add("T");
        this.choices.add("F");
        this.tabList = new HashMap<>();
        this.tabList.put("T",0);
        this.tabList.put("F",0);
    }
    @Override
    public void loadQuestion() {
        this.loadPrompt("Enter a prompt for your T/F question");
        this.tabList = new HashMap<>();
        this.tabList.put("T",0);
        this.tabList.put("F",0);
    }

    @Override
    public void loadCorrectAnswer(String content) {
       while(true){
          String in = this.in.promptAndGet(content);
          if(in != null){
              if(in.equals("T") || in.equals("F")){
                  this.correctChoices.add(in);
                  return;
              }else{
                  this.out.display("must enter either T/F");
              }
          }
       }
    }

    @Override
    public void modifyQuestion(Boolean isTest) {
        while(true){
            if(isTest){
                String in = this.in.promptAndGet("1).Modify prompt 2).Modify correct answer 3).back");
                switch(in){
                    case "1":
                        this.loadPrompt("Enter a new prompt");
                        break;
                    case "2":
                        while(true){
                            String in2 = this.in.promptAndGet("Enter new answer: T/F");
                            if(in2.equals("T") || in2.equals("F")){
                                this.correctChoices.set(0,in2);
                                break;
                            }else{
                                this.out.display("The choice you have entered is not valid");
                                continue;
                            }
                        }
                        break;
                    case "3":
                        return;
                    default:
                        this.out.display("not a valid choice, try again");
                }
            }else{
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

    @Override
    public void modifyChoicesOnly() {
        super.modifyChoicesOnly();
    }

    @Override
    public void modifyCorrectAnswer() {
    }

    @Override
    public Integer promptAnswer(Boolean isTest) {
        this.userChoices = new ArrayList<>();
        while(true){
            String usrInput = this.in.promptAndGet("Please enter T/F");
            if(usrInput.equals("T") || usrInput.equals("F")){
                if(usrInput.equals("T")){
                    this.tabList.replace("T",this.tabList.get("T") + 1);
                }else{
                    this.tabList.replace("F",this.tabList.get("F") + 1);
                }
                this.userChoices.add(usrInput);
                break;
            }else{
                this.out.display("The choice you have entered is not valid. T/F only. try again");
            }
        }
        this.responses.add(this.userChoices);
        if(this.correctChoices.size() == 0){
            return 0;
        }
        if(!this.correctChoices.get(0).equals(this.userChoices.get(0))){
            return 0;
        }
        return 10;
    }
}
