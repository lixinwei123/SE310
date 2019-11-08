public abstract class Question {
    protected String prompt;
    protected Input in;

    Question(){
        this.in = new Input();
    }

    public void loadPrompt(String prompt){
        this.prompt = this.in.promptAndGet(prompt);
    }


}
