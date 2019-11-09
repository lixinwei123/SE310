package question;

public class ShortAnswer extends Essay {
    Integer limit;

    public void setLimit(){
        this.limit = this.in.getIntegerInput("enter a limit for the short answer");
    }

    public void loadQuestion(){
        this.loadPrompt("Enter a prompt for the short answer");
        this.setLimit();
    }

    public void display(Boolean isTest) {
        this.out.display("Short answer prompt:" + this.prompt);
    }
}
