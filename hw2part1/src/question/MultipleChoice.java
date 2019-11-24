package question;

import io.Output;
import java.util.ArrayList;
import java.util.HashMap;

public class MultipleChoice extends Question {
    protected ArrayList<String> choices;
    protected ArrayList<String> correctChoices;
    protected ArrayList<String> userChoices;
    protected  HashMap<String,Integer> tabList;
    protected ArrayList<ArrayList<String>> responses;
    Integer id = 0;
    protected Integer numOfCorrectAns;
    protected Output out;
    public MultipleChoice(){
        this.out = new Output();
        this.correctChoices = new ArrayList<>();
        this.responses = new ArrayList<>();
        this.tabList = new HashMap<>();

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
        this.setUpTab();
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
        this.displayChoices(true,this.choices);
        if(bool){
            this.out.display("The correct choice is ");
            this.displayChoices(false,this.correctChoices);
        }
    }



    public void displayChoices(Boolean isAlpha, ArrayList choices) {
        if (isAlpha) {
            for (int i = 0; i < choices.size(); i++) {
                this.out.displaySameLine((char) (i + 65) + ")" + choices.get(i) + " " + "\n");
            }
        }else{
            for (int i = 0; i < choices.size(); i++) {
                this.out.displaySameLine( (i + 1 + ")" + choices.get(i) + " " + "\n"));
            }
        }
    }

    public void setUpTab(){
        for (int i = 0; i < choices.size(); i++) {
            this.out.displaySameLine((char) (i + 65) + ")" + choices.get(i) + " " + "\n");
            String letter = Character.toString((char) (i + 65));
            this.tabList.put(letter,0);
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
            this.out.display("1).add correct answer  2).delete correct answer  3).back ");
            String inp = this.in.getInput();
            switch(inp){
                case "1":
                    while(true){
                        if(this.correctChoices.size() == this.choices.size()){
                            this.out.display("you can't add anymore correct answer, maximum reached");
                            break;
                        }
                        while(this.correctChoices.size() < this.choices.size()){
                            this.displayChoices(false,this.choices);
                            Integer in = this.in.getIntegerInput("\n" + "answer" + " in integer index");
                            if (in == null || in > this.choices.size() || in < 1) {
                                this.out.display("The index you have entered is not valid ");
                                continue;
                            }
                            String choice = this.choices.get(in - 1);
                            if(this.correctChoices.contains(choice) || !this.choices.contains(choice)){
                                this.out.display("the choice you entered is not found or you have already entered this choice");
                            }else{
                                this.addAnswer(choice);
                                this.out.display("Correct choice added successfully");
                                break;
                            }
                        }
                        break;
                    }
                    break;
                case "2":
                    if(this.correctChoices.size() < 2){
                       this.out.display("you can't delete anymore, you need at least 1 correct answer");
                       break;
                    }
                    while(true){
                         this.displayChoices(false,this.correctChoices);
                        Integer in = this.in.getIntegerInput("\n" + "answer to delete"  + " in integer index");
                        if (in == null || in > this.correctChoices.size() || in < 1) {
                            this.out.display("The index you have entered is not valid ");
                            continue;
                        }
                        this.removeAnswer(in - 1);
                        this.out.display("Answer successfully removed");
                        break;
                    }
                    break;
//                case "3":
//                    this.displayChoices(false,this.correctChoices);
//                    Integer usrInput3 = this.in.getIntegerInput("Enter the index of the correct choice that you want to modify") ;
//                    try{
//                        String c = this.correctChoices.get(usrInput3 - 1);
//                        String usrInput4 = this.in.promptAndGet("please enter replacement text for this choice");
//                        if(this.correctChoices.contains(usrInput4)){
//                            this.out.display("There already exists a choice with the same content!");
//                            continue;
//                        }
//                        this.setChoice(usrInput3 - 1,usrInput4);
//                        if(this.correctChoices.contains(c)){
//                            this.correctChoices.set(this.correctChoices.indexOf(c),usrInput4);
//                        }
//                        this.out.display("successfully replaced");
//                    }catch (IndexOutOfBoundsException e){
//                        this.out.display("Please select from the list");
//                        continue;
//                    }
//                    break;
                case "3":
                    return;

                default:
                    this.out.display("not a valid input");
                    break;
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
                    if(this.choices.size() < 3){
                        this.out.display("you cannot delete anymore choices, you need a minimum of 2 choices");
                    }
                    this.displayChoices(false,this.choices);
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
                    this.displayChoices(false,this.choices);
                    Integer usrInput3 = this.in.getIntegerInput("Enter the index of the choice that you want to modify") ;
                    try{
                        String c = this.choices.get(usrInput3 - 1);
                        String usrInput4 = this.in.promptAndGet("please enter replacement text for this choice");
                        if(this.choices.contains(usrInput4)){
                            this.out.display("There already exists a choice with the same content!");
                            continue;
                        }
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
                    this.out.display("Prompt successfully changed");
                    break;
                case "5":
                    return;
                default:
                    this.out.display("not a valid input, try again");
            }
        }
    }

    @Override
    public Integer promptAnswer(Boolean isTest) {
        this.userChoices = new ArrayList<>();
        if(isTest){
            this.out.display("Enter all of the correct answer, there are " + this.numOfCorrectAns.toString() + " correct answers" + ". Ex: A/B/C/D ..");
        }else{
            this.out.display("Enter all of the possible answer, type 'done' to stop " + ". Ex: A/B/C/D ..");
        }
        if(isTest){
            while(this.userChoices.size() < this.numOfCorrectAns){
                String usrInput = this.in.getInput();
                int index = (int)usrInput.toCharArray()[0] - 65;
                if(index> this.choices.size() - 1 || index < 0 || this.userChoices.contains(usrInput)){
                    this.out.display("The choice you have entered is not valid or you have already entered this, try again");
                }else{
                    this.tabList.replace(usrInput,this.tabList.get(usrInput) + 1);
                    this.userChoices.add(usrInput);
                }
            }
        }else{
            while(this.userChoices.size() < this.choices.size()){
                String usrInput = this.in.getInput();
                if(usrInput.equals("done")){
                    if(this.userChoices.size() < 1){
                        this.out.display("Please enter at least 1 answer!");
                        continue;
                    }
                    break;
                }
                int index = (int)usrInput.toCharArray()[0] - 65;
                if(index> this.choices.size() - 1 || index < 0 || this.userChoices.contains(usrInput)){
                    this.out.display("The choice you have entered is not valid or you have already entered this, try again");
                }else{
                    this.tabList.replace(usrInput,this.tabList.get(usrInput) + 1);
                    this.userChoices.add(usrInput);
                }
            }
        }

        this.responses.add(this.userChoices);
        if(this.correctChoices.size() == 0){
            return 0;
        }
        for(int i =0; i < this.userChoices.size(); i ++){
            int index = (int)this.userChoices.get(i).toCharArray()[0] - 65;
            if(!this.correctChoices.contains(this.choices.get(index))){
                return 0;
            }
        }

        this.id += 1;
        return 10;
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

    @Override
    public Integer getCorrectPoint(){
        return 10;
    }

    @Override
    public void displayReplies() {
        this.out.display("Replies:");
        for(int i = 0; i <this.responses.size(); i++){
            String temp = "";
            for(int j = 0; j <this.responses.get(i).size(); j ++){
                temp +=  this.responses.get(i).get(j) + ",";
            }
            this.out.display(temp.substring(0,temp.length() -1));
        }
    }

    @Override
    public void displayTabulate() {
        this.out.display("Tabulation:");
        this.out.display(this.prompt);
        this.tabList.forEach((key,val)->{
            this.out.display(key +": "+val);
        });
    }
}
