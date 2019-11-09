import java.io.Serializable;

public abstract class Question implements Serializable {
    protected String prompt;
    protected Input in;
    protected Output out;
    Question(){
        this.in = new Input();
        this.out = new Output();
    }

    public void loadPrompt(String prompt){
        this.prompt = this.in.promptAndGet(prompt);
    }

    public abstract void loadQuestion();

    public abstract void display(Boolean isTest);
}
