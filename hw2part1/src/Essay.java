public class Essay extends Question {

    @Override
    public void loadQuestion() {
        this.loadPrompt("Enter a prompt for the essay");
    }

    @Override
    public void display() {

    }
}
