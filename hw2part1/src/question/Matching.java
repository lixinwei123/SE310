package question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Matching extends Question {
    private ArrayList<String> column1;
    private ArrayList<String> column2;
    private ArrayList<String> usedMatches;
    private HashMap<String, String> correctAnswers;
    private HashMap<String, String> userAnswers;
    private ArrayList<HashMap<String,String>> userReplies;
    private HashMap<HashMap<String,String>,Integer> tabList;

    private Integer totalMatch;

    public Matching() {
        this.correctAnswers = new HashMap<>();
        this.userAnswers =new HashMap<>();
        this.tabList = new HashMap<>();
        this.usedMatches = new ArrayList<>();
        this.userReplies = new ArrayList<>();
    }

    @Override
    public void loadQuestion() {
        this.loadPrompt("Enter a prompt for your question");
        while (true) {
            this.totalMatch = this.in.getIntegerInput("enter the total of amount of match choices");
            if (this.totalMatch == null) {
            } else if (this.totalMatch > 1000) {
                this.out.display("wayy too many choices, don't you agree?");
            } else if (this.totalMatch < 2) {
                this.out.display("please enter at least 2 matches");
            } else {
                break;
            }
        }
        this.out.display("enter choices for your first column");
        this.column1 = this.loadColumn("first");
        this.out.display("enter choices for your second column");
        this.column2 = this.loadColumn("second");
        this.userReplies = new ArrayList<>();
    }

    public ArrayList<String> loadColumn(String column) {
        return this.in.getMultipleInput(this.totalMatch, "match choice no");
    }

    @Override
    public void loadCorrectAnswer(String content) {
        this.correctAnswers = new HashMap<>();
        this.usedMatches = new ArrayList<>();
        this.out.display("enter the correct answer for each match by typing in the index number");
        ArrayList<String> temp = (ArrayList<String>) this.column2.clone();
        for (String s : column1) {
            while (true) {
                for (int j = 1; j < temp.size() + 1; j++) {
                    this.out.displaySameLine(j + ")" + temp.get(j - 1) + " ");
                }
                Integer index = this.in.getIntegerInput("\n please select the correct answer for: " + s);
                if (index == null || index > temp.size() || index < 1) {
                    this.out.display("The index you have entered is not valid ");
                    continue;
                }
                String match = temp.get(index - 1);
                if (!column2.contains(match) || this.usedMatches.contains(match)) {
                    this.out.display("the choice you entered is not found or you have already entered this choice, please enter again");
                } else {
                    temp.remove(match);
                    this.correctAnswers.put(s, match);
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
            this.out.display(this.column1.get(i - 1) + "  |  " + this.column2.get(i - 1));
        }
        if (bool) {
            int index = 1;
            this.out.display("correct answers:");
            for (Map.Entry<String, String> entry : correctAnswers.entrySet()) {
                this.out.display(Integer.toString(index) + ")" + entry.getKey() + " ---> " + entry.getValue());
                index += 1;
            }
        }
    }

    @Override
    public void modifyQuestion(Boolean isTest) {
        while (true) {
            if (isTest) {
                String in = this.in.promptAndGet("1).Modify prompt 2).Modify left column 3).Modify right column 4).Remove row 5)Add row 6)Modify answer 7).back");
                switch (in) {
                    case "1":
                        this.loadPrompt("Enter a new prompt");
                        break;
                    case "2":
                        this.modifyColumn(true,true);
                        break;
                    case "3":
                        this.modifyColumn(false,true);
                        break;
                    case "4":
                        this.removeRow(true);
                        break;
                    case "5":
                        this.addRow(true);
                        return;
                    case "6":
                        this.loadCorrectAnswer("");
                        this.out.display("Answers updated!");
                        break;
                    case "7":
                        return;
                    default:
                        this.out.display("not a valid choice, try again");;
                }
            }else{
                String in = this.in.promptAndGet("1).Modify prompt 2).Modify left column 3).Modify right column 4).Remove row 5)Add row  6).back");
                switch (in) {
                    case "1":
                        this.loadPrompt("Enter a new prompt");
                        break;
                    case "2":
                        this.modifyColumn(true,false);
                        break;
                    case "3":
                        this.modifyColumn(false,false);
                        break;
                    case "4":
                        this.removeRow(false);
                        break;
                    case "5":
                        this.addRow(false);
                        break;
                    case "6":
                        return;
                    default:
                        this.out.display("not a valid choice, try again");
                }
            }
        }
    }

    public void displayLeftColumn() {
        for (int i = 0; i < column1.size(); i++) {
            this.out.displaySameLine((i + 1 + ")" + column1.get(i) + " " + "\n"));
        }
    }

    public void displayRightColumn() {
        for (int i = 0; i < column2.size(); i++) {
            this.out.displaySameLine((i + 1 + ")" + column2.get(i) + " " + "\n"));
        }
    }

    public void removeRow(Boolean isTest){
        while(true){
            this.displayLeftColumn();
            Integer in2 = this.in.getIntegerInput("\n" + "item" + " in integer index that you want to remove");
            if (in2 == null || in2 < 1 || in2 > this.column1.size()) {
                this.out.display("The index you have entered is not valid ");
                continue;
            }
            String item = this.column1.get(in2 - 1);
            if(isTest){
                this.correctAnswers.forEach((key,value) ->{
                    if(key.equals(item)){
                        this.column1.remove(key);
                        this.column2.remove(value);
                        return;
                    }
                });
                this.correctAnswers.remove(item);
            }else{
                this.column1.remove(in2 -1 );
                while(true){
                    this.displayRightColumn();
                    Integer in3 = this.in.getIntegerInput("\n" + "please remove a corresponding item from the right column");
                    if (in3 == null || in3 < 1 || in2 > this.column2.size()) {
                        this.out.display("The index you have entered is not valid ");
                        continue;
                    }
                    this.column2.remove(in3 - 1);
                    break;
                }
            }
            this.totalMatch -= 1;
            this.out.display("item successfully removed");
            return;
        }
    }
    public void modifyColumn(Boolean isLeft, Boolean isTest){
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
                        String temp = this.correctAnswers.get(item);
                        this.correctAnswers.remove(item);
                        this.correctAnswers.put(usrInput4,temp);
                    }else{
                        this.correctAnswers.forEach((key,val) -> {
                            if(val.equals(item)){
                                this.correctAnswers.replace(key,usrInput4);
                                return;
                            }
                        });
                    }
                }
                this.out.display("successfully modified!");
                return;
            }
        }
    }

    public void addRow(Boolean isTest){
        while(true){
            String in = this.in.promptAndGet("Please enter the string of new match for left column");
            if(this.column1.contains(in)){
                this.out.display("Duplicate item, please try again");
                continue;
            }
            this.column1.add(in);
            while(true){
                String in2 = this.in.promptAndGet("Please enter the string of new match for right column");
                if(this.column2.contains(in2)){
                    this.out.display("Duplicate item, please try again");
                    continue;
                }
                this.column2.add(in2);
                this.correctAnswers.put(in,in2);
                break;
            }
            break;
        }
        this.totalMatch += 1;
        this.out.display("All items have been added");
        if(isTest){
            this.out.display("Correct answers are updated");
        }
    }

    @Override
    public Integer promptAnswer(Boolean isTest) {
        this.userAnswers = new HashMap<>();
        this.usedMatches = new ArrayList<>();
        this.out.display("enter the answer for each match by typing in the index number");
        ArrayList<String> temp = (ArrayList<String>) this.column2.clone();
        for (String s : column1) {
            while (true) {
                for (int j = 1; j < temp.size() + 1; j++) {
                    this.out.displaySameLine(j + ")" + temp.get(j - 1) + " ");
                }
                Integer index = this.in.getIntegerInput("\n please select the answer for: " + s);
                if (index == null || index > temp.size() || index < 1) {
                    this.out.display("The index you have entered is not valid ");
                    continue;
                }
                String match = temp.get(index - 1);
                if (!column2.contains(match) || this.usedMatches.contains(match)) {
                    this.out.display("the choice you entered is not found or you have already entered this choice, please enter again");
                } else {
                    temp.remove(match);
                    this.userAnswers.put(s, match);
                    this.usedMatches.add(match);
                    break;
                }
            }
        }
        this.userReplies.add(this.userAnswers);
        if(!this.tabList.containsKey(this.userAnswers)){
            this.tabList.put(this.userAnswers,1);
        }else{
            this.tabList.put(this.userAnswers, this.tabList.get(this.userAnswers) + 1);
        }
        if(isTest){
            if(this.userAnswers.equals(this.correctAnswers)){
                return 10;
            }else{
                return 0;
            }
        }
        return 0;
    }

    @Override
    public Integer getCorrectPoint() {
        return 10;
    }

    @Override
    public void displayReplies() {
        this.out.display("Replies:");
        for(int i = 0; i < this.userReplies.size();i++){
            this.userReplies.get(i).forEach((key,val)->{
                this.out.display(key + "--->" + val);
            });
            this.out.display("");
        }
    }

    @Override
    public void displayTabulate() {
        this.out.display("Tabulate:");
        this.tabList.forEach((key,val)->{
            this.out.display(val);
            key.forEach((key2,val2)->{
                this.out.display(key2 + "---->" + val2);
            });
            this.out.display("");
        });

    }
}
