package question;


import io.Output;
import io.Input;
import rca.ResponseCorrectAnswer;

public abstract class Question  {
    protected String prompt;
    protected Input in;
    protected Output out;
    protected ResponseCorrectAnswer rca;
    public Question(){
        this.in = new Input();
        this.out = new Output();
    }

    public void loadPrompt(String prompt){
        this.prompt = this.in.promptAndGet(prompt);
    }

    public abstract void loadQuestion();

    public abstract void display(Boolean isTest);

    public void loadCorrectAnswer(String str){ }

}
