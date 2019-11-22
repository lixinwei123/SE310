package question;

public class Essay extends Question {

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
}
