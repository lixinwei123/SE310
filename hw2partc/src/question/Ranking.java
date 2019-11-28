package question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ranking extends Matching {
//    HashMap<Integer, String> correctAnswers;
    ArrayList<String> correctAnswers;
    ArrayList<String> userAnswers;
    ArrayList<ArrayList<String>> userReplies;
    HashMap<ArrayList<String>,Integer> tabList;
    public Ranking(){
        this.column1 = new ArrayList<>();
    }
    @Override
    public void loadQuestion() {
        this.loadPrompt("Enter a prompt for your question");
        while (true) {
            this.totalMatch = this.in.getIntegerInput("enter the total of amount of ranking choices");
            if (this.totalMatch == null) {
            } else if (this.totalMatch > 1000) {
                this.out.display("wayy too many choices, don't you agree?");
            } else if (this.totalMatch < 2) {
                this.out.display("please enter at least 2 choices");
            } else {
                break;
            }
        }
        this.column1 = this.loadColumn("first");
        this.userReplies = new ArrayList<>();
    }

    @Override
    public ArrayList<String> loadColumn(String column) {
        return this.in.getMultipleInput(this.totalMatch, "rank choice no");
    }

    @Override
    public void loadCorrectAnswer(String content) {
        this.correctAnswers = new ArrayList<>();
        this.usedMatches = new ArrayList<>();
        this.out.display("enter the correct sequence for each choice by typing in the index number");
        ArrayList<String> temp = (ArrayList<String>) this.column1.clone();
        int counter = 1;
        for (String s : column1) {
            while (true) {
                for (int j = 1; j < temp.size() + 1; j++) {
                    this.out.displaySameLine(j + ")" + temp.get(j - 1) + " ");
                }
                Integer index = this.in.getIntegerInput("\n please select choice no " + counter);
                counter += 1;
                if (index == null || index > temp.size() || index < 1) {
                    this.out.display("The index you have entered is not valid ");
                    continue;
                }
                String match = temp.get(index - 1);
                if (this.usedMatches.contains(match)) {
                    this.out.display("You have entered this choice");
                } else {
                    temp.remove(match);
                    this.correctAnswers.add(match);
                    this.usedMatches.add(match);
                    break;
                }
            }
        }
    }

    @Override
    public void display(Boolean bool) {
        this.out.display(prompt);
        for (int i = 1; i < this.totalMatch + 1; i++) {
            this.out.display(i + "  |  " + this.column1.get(i - 1));
        }
        if (bool) {
            int index = 1;
            this.out.display("correct answers:");
            for (int i = 0; i < this.column1.size(); i ++) {
                this.out.display(index + ")" + i + 1 + " ---> " + this.column1.get(i));
                index += 1;
            }
        }
    }

    @Override
    public void modifyQuestion(Boolean isTest) {
        while (true) {
            if (isTest) {
                String in = this.in.promptAndGet("1).Modify prompt 2).Modify rank choices 3).Remove rank choice 4)Add Rank choice 5)Modify answers 6).back");
                switch (in) {
                    case "1":
                        this.loadPrompt("Enter a new prompt");
                        break;
                    case "2":
                        this.modifyColumn(true,true);
                        break;
                    case "3":
                        this.removeRow(true);
                        break;
                    case "4":
                        this.addRow(true);
                        return;
                    case "5":
                        this.loadCorrectAnswer("");
                        this.out.display("Answers updated!");
                        break;
                    case "6":
                        return;
                    default:
                        this.out.display("not a valid choice, try again");;
                }
            }else{
                String in = this.in.promptAndGet("1).Modify prompt 2).Modify rank choices 3).Remove rank choice 4)Add Rank choice  5).back");
                switch (in) {
                    case "1":
                        this.loadPrompt("Enter a new prompt");
                        break;
                    case "2":
                        this.modifyColumn(true,false);
                        break;
                    case "3":
                        this.removeRow(false);
                        break;
                    case "4":
                        this.addRow(false);
                        break;
                    case "5":
                        return;
                    default:
                        this.out.display("not a valid choice, try again");
                }
            }
        }
    }

    @Override
    public void displayLeftColumn() {
        super.displayLeftColumn();
    }

    @Override
    public void displayRightColumn() {
        super.displayRightColumn();
    }

    @Override
    public void removeRow(Boolean isTest) {
        while(true){
            this.displayLeftColumn();
            Integer in2 = this.in.getIntegerInput("\n" + "item" + " in integer index that you want to remove");
            if (in2 == null || in2 < 1 || in2 > this.column1.size()) {
                this.out.display("The index you have entered is not valid ");
                continue;
            }
            String item = this.column1.get(in2 - 1);
            if(isTest){
                this.correctAnswers.remove(item);
                this.column1.remove(item);
            }else{
                this.column1.remove(in2 -1 );
            }
            this.totalMatch -= 1;
            this.out.display("item successfully removed");
            return;
        }
    }

    @Override
    public void modifyColumn(Boolean isLeft, Boolean isTest) {
        ArrayList column;
        if(isLeft)column=this.column1;else column = this.column2;
        while(true){
            if(isLeft) this.displayLeftColumn(); else this.displayRightColumn();
            Integer in2 = this.in.getIntegerInput("\n" + "item" + " in integer index");
            if (in2 == null || in2 < 1 || in2 > column.size()) {
                this.out.display("The index you have entered is not valid ");
                continue;
            }
            String item = (String)column.get(in2 - 1);
            while(true){
                String usrInput4 = this.in.promptAndGet("please enter replacement text for this item");
                if(column.contains(usrInput4)){
                    this.out.display("You have already entered this, please try again.");
                    continue;
                }
                column.set(in2-1,usrInput4);
                if (isTest){
                    if(isLeft){
                        Integer temp = this.correctAnswers.indexOf(item);
                        this.correctAnswers.set(temp,usrInput4);
                    }
                }
                this.out.display("successfully modified!");
                return;
            }
        }
    }

    @Override
    public void addRow(Boolean isTest) {
        while (true) {
            String in = this.in.promptAndGet("Please enter the string of new rank choice ");
            if (this.column1.contains(in)) {
                this.out.display("Duplicate item, please try again");
                continue;
            }
            this.column1.add(in);
            if(isTest){
                this.correctAnswers.add(in);
            }
            this.totalMatch += 1;
            this.out.display("All items have been added");
            if (isTest) {
                this.out.display("Correct answers are updated");
            }
            return;
        }
    }

    @Override
    public Integer promptAnswer(Boolean isTest) {
        if (this.userReplies == null) {
            this.userReplies = new ArrayList<>();
        }
        if (this.tabList == null) {
            this.tabList = new HashMap<>();
        }
        this.userAnswers = new ArrayList<>();
        this.usedMatches = new ArrayList<>();
        this.out.display("select the ranking choices in order by typing in the index number");
        ArrayList<String> temp = (ArrayList<String>) this.column1.clone();
        Integer counter = 1;
        for (String s : column1) {
            while (true) {
                for (int j = 1; j < temp.size() + 1; j++) {
                    this.out.displaySameLine(j + ")" + temp.get(j - 1) + " ");
                }
                Integer index = this.in.getIntegerInput("\n please select the answer for: rank no." + counter);
                if (index == null || index > temp.size() || index < 1) {
                    this.out.display("The index you have entered is not valid ");
                    continue;
                }
                String match = temp.get(index - 1);
                if (this.usedMatches.contains(match)) {
                    this.out.display("you have already entered this choice, please enter again");
                } else {
                    temp.remove(match);
                    this.userAnswers.add(match);
                    this.usedMatches.add(match);
                    break;
                }
            }
            counter += 1;
        }
        this.userReplies.add(this.userAnswers);
        if (!this.tabList.containsKey(this.userAnswers)) {
            this.tabList.put(this.userAnswers, 1);
        } else {
            this.tabList.put(this.userAnswers, this.tabList.get(this.userAnswers) + 1);
        }
        if (isTest) {
            for (int i = 0; i < this.correctAnswers.size(); i++) {
                if (!this.userAnswers.get(i).equals(this.correctAnswers.get(i))) {
                    return 0;
                }
            }
            return 10;
        }
        return 0;
    }

    @Override
    public Integer getCorrectPoint() {
        return super.getCorrectPoint();
    }

    @Override
    public void displayReplies() {
        this.out.display("Replies:");
        for(int i = 0; i < this.userReplies.size();i++){
            for(int j = 0; j <this.userReplies.get(i).size(); j++){
                this.out.display(j + 1 + "--->" + this.userReplies.get(i).get(j));
            }
            this.out.display("");
        }
    }

    @Override
    public void displayTabulate() {
        this.out.display("Tabulate:");
        this.out.display(this.prompt);
        this.tabList.forEach((key,val)->{
            this.out.display(val);
            key.forEach((val2)->{
                this.out.display(key.indexOf(val2) + 1 + "---->" + val2);
            });
            this.out.display("");
        });
    }
}
