import java.util.ArrayList;

public class TrueFalse extends MultipleChoice{
    TrueFalse(){
        this.choices = new ArrayList<>();
        this.choices.add("true");
        this.choices.add("false");
    }
    @Override
    public void loadQuestion() {
        this.loadPrompt("Enter a prompt for your True/False question");
    }

//    @Override
//    public void loadCorrectAnswer() {
//        while(true){
//            String cc = this.in.promptAndGet("Enter the correct answer, true/false");
//            if(choices.contains(cc) == false){
//                this.out.display("Choice is not part of the choices you have entered before, enter again");
//            }else{
//                return;
//            }
//        }
//    }

}
