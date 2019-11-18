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

    }
}
