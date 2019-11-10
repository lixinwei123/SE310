package question;

import java.util.ArrayList;

public class TrueFalse extends MultipleChoice{
    public TrueFalse(){
        this.choices = new ArrayList<>();
        this.choices.add("T");
        this.choices.add("F");
    }
    @Override
    public void loadQuestion() {
        this.loadPrompt("Enter a prompt for your T/F question");
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

}
