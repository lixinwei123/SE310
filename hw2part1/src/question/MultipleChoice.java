package question;

import io.Output;
import java.util.ArrayList;

public class MultipleChoice extends Question {
    ArrayList<String> choices;
    ArrayList<String> correctChoices;
    private Integer numOfCorrectAns;
    protected Output out;
    public MultipleChoice(){
        this.out = new Output();
        this.correctChoices = new ArrayList<>();
    }
    public void loadQuestion(){
        this.loadPrompt("Enter a prompt for your question");
        Integer numberOfChoices;
        while(true){
            numberOfChoices = this.in.getIntegerInput("Enter the number of choices for your multiple-choice question.");
            if(numberOfChoices == null){
                continue;
            }else if (numberOfChoices < 2){
                this.out.display("At least two choices for a multiple choice");
            } else if(numberOfChoices > 500){
                this.out.display("wayyy too many choices for a multiple choice question, don't you agree?");
            }else{
                break;
            }
        }
        this.choices = this.in.getMultipleInput(numberOfChoices,"enter choice");
    }

    @Override
    public void loadCorrectAnswer(String content){
        while(true){
            this.numOfCorrectAns = this.in.getIntegerInput("Enter the number of correct answers for your multiple-choice question.");
            if(numOfCorrectAns == null){
            }else if (numOfCorrectAns < 1){
                this.out.display("you have entered less than the total number of choices you have");
            } else if(numOfCorrectAns > this.choices.size()){
                this.out.display("you have entered more than the total number of choices you have");
            }else{
                break;
            }
        }
        ArrayList<String>temp =  (ArrayList<String>)this.choices.clone();
        while(this.correctChoices.size() < this.numOfCorrectAns){
            for (int j = 1; j < temp.size() + 1; j++) {
                this.out.displaySameLine(j + ")" + temp.get(j - 1) + " ");
            }
            Integer in = this.in.getIntegerInput("\n" + content + " in integer index");
            if (in == null || in > temp.size() || in < 1) {
                this.out.display("The index you have entered is not valid ");
                continue;
            }
            String choice = temp.get(in - 1);
            if(this.correctChoices.contains(choice) || !this.choices.contains(choice)){
                this.out.display("the choice you entered is not found or you have already entered this choice");
            }else{
                temp.remove(choice);
                this.correctChoices.add(choice);
            }
        }
    }

    @Override
    public void display(Boolean bool) {
        this.out.display(this.prompt);
        this.displayChoices(true);
        if(bool){
            this.out.display("The correct choice is ");
            for(int i = 0; i < this.correctChoices.size(); i++){
                this.out.displaySameLine((char)(i + 65) + ")" + this.correctChoices.get(i) + " " + "\n");
            }
        }
    }

    public void displayChoices(Boolean isAlpha) {
        if (isAlpha) {
            for (int i = 0; i < this.choices.size(); i++) {
                this.out.displaySameLine((char) (i + 65) + ")" + this.choices.get(i) + " " + "\n");
            }
        }else{
            for (int i = 0; i < this.choices.size(); i++) {
                this.out.displaySameLine( (i + 1 + ")" + this.choices.get(i) + " " + "\n"));
            }
        }
    }



    @Override
    public void modifyQuestion(Boolean isTest) {
        if(isTest){
            while(true){
                String in = this.in.promptAndGet("Type 'choice' to modify choices. Type 'answer' to modify answers");
                if(in.equals("choice")){
                    this.modifyChoicesOnly();
                    return;
                }else if (in.equals("answer")){
                    this.modifyCorrectAnswer();
                    return;
                }
                else{
                    this.out.display("Not a valid choice, try again");
                }
            }
        }else{
            this.modifyChoicesOnly();
        }
    }

    public void modifyCorrectAnswer(){
        while(true){
            this.out.display("which of the following do you want to modify? Select index");
            this.out.display("1).add correct answer  2).delete correct answer  3).modify correct answer  4)back");
            String inp = this.in.getInput();
            switch(inp){
                case "1":
                    while(true){
                        if(this.correctChoices.size() == this.choices.size()){
                            this.out.display("you can't add anymore correct answer, maximum reached");
                        }else{
                            ArrayList<String>temp =  (ArrayList<String>)this.choices.clone();
                            while(this.correctChoices.size() < this.numOfCorrectAns){
                                for (int j = 1; j <  temp.size() + 1; j++) {
                                    this.out.displaySameLine(j + ")" + temp.get(j - 1) + " ");
                                }
                                Integer in = this.in.getIntegerInput("\n" + "answer" + " in integer index");
                                if (in == null || in > temp.size() || in < 1) {
                                    this.out.display("The index you have entered is not valid ");
                                    continue;
                                }
                                String choice = temp.get(in - 1);
                                if(this.correctChoices.contains(choice) || !this.choices.contains(choice)){
                                    this.out.display("the choice you entered is not found or you have already entered this choice");
                                }else{
                                    temp.remove(choice);
                                    this.correctChoices.add(choice);
                                }
                            }
                        }
                    }
            }
        }
    }

    public void modifyChoicesOnly(){
        while(true){
            this.out.display("which of the following do you want to modify? Select index");
            this.out.display("1).add choice  2).delete choice  3).modify choice 4)modify prompt 5)back");
            String in = this.in.getInput();
            switch (in){
                case "1":
                    while(true){
                        String usrInput = this.in.promptAndGet("Enter a new choice");
                        if(this.choices.contains(usrInput)){
                            this.out.display("There already exists a choice with the same content!");
                            continue;
                        }
                        this.addChoice(usrInput);
                        this.out.display("successfully added");
                        break;
                    }
                    break;
                case "2":
                    this.displayChoices(false);
                    Integer usrInput2 = this.in.getIntegerInput("Enter the index of the choice that you want to delete");
                    try{
                        this.correctChoices.remove(this.choices.get(usrInput2 - 1));
                        this.removeChoice(usrInput2 - 1);
                        this.out.display("successfully removed");
                    }catch (IndexOutOfBoundsException e){
                        this.out.display("Please select from the list");
                    }
                    break;
                case "3":
                    this.displayChoices(false);
                    Integer usrInput3 = this.in.getIntegerInput("Enter the index of the choice that you want to modify") ;
                    try{
                        String c = this.choices.get(usrInput3 - 1);
                        String usrInput4 = this.in.promptAndGet("please enter replacement text for this choice");
                        this.setChoice(usrInput3 - 1,usrInput4);
                        if(this.correctChoices.contains(c)){
                            this.correctChoices.set(this.correctChoices.indexOf(c),usrInput4);
                        }
                        this.out.display("successfully replaced");
                    }catch (IndexOutOfBoundsException e){
                        this.out.display("Please select from the list");
                    }
                    break;
                case "4":
                    this.loadPrompt("Enter a new prompt");
                    break;
                case "5":
                    return;
                default:
                    this.out.display("not a valid input, try again");
            }
        }

    }
    public void addChoice(String choice){
        this.choices.add(choice);
    }

    public void addAnswer(String choice){
        this.correctChoices.add(choice);
    }

    public void removeChoice(Integer index){
        this.choices.remove(this.choices.get(index));
    }

    public void removeAnswer(Integer index){
        this.correctChoices.remove(this.correctChoices.get(index));
    }

    public void setChoice(Integer index, String context){
        this.choices.set(index,context);
    }

    public void setAnswer(Integer index, String context){
        this.correctChoices.set(index,context);
    }
}
