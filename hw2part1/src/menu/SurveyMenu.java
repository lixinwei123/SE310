package menu;

import java.io.*;
import java.util.ArrayList;
import survey.Survey;
import question.*;
public class SurveyMenu extends AbstractMenu implements Serializable {
    protected Survey returnedSurvey;
    protected String sep;
    protected Boolean unsavedSurvey;
    protected static final long serialversionUID = 14552024; //some random uid I made
    SurveyMenu(){
        this.sep = File.separator;
        this.unsavedSurvey = false;
        this.menuItems = new ArrayList<>();
        this.menuItems.add("1.Create a new survey");
        this.menuItems.add("2.Display a survey");
        this.menuItems.add("3.Load a survey");
        this.menuItems.add("4.Save current survey");
        this.menuItems.add("5.Continue current survey");
        this.menuItems.add("6.Back");
        this.menuItems.add("7.Quit");
    }
    public void loadMenuItems(){
        while(true){
            this.displayMenuItems();
            String userChoice = this.in.promptAndGet("");
            switch (userChoice){
                case "1":
                    this.createSurvey();
                    break;
                case "2":
                    this.displaySurvey();
                    break;
                case "3":
                    this.loadSurvey();
                    break;
                case "4":
                    this.saveSurvey();
                    break;
                case "5":
                    this.continueSurvey();
                    break;
                case "6":
                    //NEED TO FIX
                    if(this.goBack()){
                        return;
                    }else{
                        break;
                    }
                case "7":
                    this.out.display("Have a good day~");
                    System.exit(0);
                default:
                    this.out.display("not a valid input, try again");
            }
        }
    }

     private Boolean goBack(){
        if(this.returnedSurvey != null){
            while(true){
                String confirmation = this.in.promptAndGet("If you have a unsaved survey/test, your changes will be discarded if you do not save, are you sure? Enter 'Y' to confirm, anything else to cancel");
                if(confirmation.equals("Y")){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return true;
    }

     void createSurvey() {
        Menu3 menu3 = new Menu3(this.isTest());
        if (this.unsavedSurvey == true) {
            String in = this.in.promptAndGet("it seems you have an unsaved " + this.getFileType() + ", enter 5 to continue, 1 to start a new survey, anything else to cancel");
            if(!in.equals("5") && !in.equals("1")){
                return;
            }
            else if (in.equals("5")) {
                this.continueSurvey();
                return;
            }
        }
        this.returnedSurvey = menu3.loadMenuItems(this.returnNewSurvey());
        this.unsavedSurvey = true;
        this.isSurveyNull();
    }

    public void displaySurvey(){
        Survey returnedObj = this.displayAllFiles("display");
        if(returnedObj == null){
            return;
        }
        Integer index = 1;
        for(Question q: returnedObj.getQuestions()){
            this.out.displaySameLine(index.toString() + ") ");
            q.display(this.isTest());
            this.out.display("");
            index +=1;
        }
    }

    public void loadSurvey(){
        Survey temp = this.displayAllFiles( "load");
        if(temp == null){
            return;
        }
        if(this.returnedSurvey != null){
            String in = this.in.promptAndGet("you have an ongoing survey, are you sure you want to discard it and load in the survey from file? enter 'Y' to overwrite, or anything else to cancel");
            if(!in.equals("Y")){
                return;
            }
        }
        this.returnedSurvey = temp;
        this.unsavedSurvey = true;
        this.out.display("survey has been loaded in successfully, now you add more question to the survey by entering '5' command ");
    }

    /*
    save function check to see if there is an ongoing survey before launching it
    check to see if there already exists a survey named that
    check for ioException
    * */
    public void saveSurvey()  {
        if(this.returnedSurvey == null){
            this.out.display("no " + this.getFileType() + " is active at the moment to save");
            return;
        }
             String in = this.in.promptAndGet("please enter a name for your " + this.getFileType());
             File path = new File("Serializable" + this.sep + this.getFileType() + "s" + this.sep + in + ".ser");
//             File absPath = new File(path.getAbsolutePath());
             if(path.exists()){
                 String confirm = this.in.promptAndGet("a file with the name '"+in+"' already exists, are you sure you want to overwrite it? Enter 'Y' to overwrite, anything else to stop");
                 if(!confirm.equals("Y")){
                     this.out.display("save is canceled");
                     return;
                 }
             }
             this.out.serialize(this.returnedSurvey,"Serializable"+ this.sep + this.getFileType() +"s" + this.sep + in + ".ser");
             this.out.display( this.getFileType() + " has been saved");
             this.unsavedSurvey = false;
    }

    public void continueSurvey(){
        if(this.returnedSurvey == null){
            this.out.display("no current " + this.getFileType() + " is active");
        }else{
            Menu3 menu3 = new Menu3(this.isTest());
            this.returnedSurvey = menu3.loadMenuItems(this.returnedSurvey);
            this.isSurveyNull();
        }
    }
    public Survey displayAllFiles(String method) {
        File[] surveyFiles = new File("Serializable" + this.sep + this.getFileType() + "s").listFiles();
        if (surveyFiles.length < 1 && this.returnedSurvey == null) {
            this.out.display("No " + this.getFileType() + "s are stored");
            return null;
        }
        this.out.display("please select a file you want to "+method);
        for (Integer i = 1; i < surveyFiles.length + 1; i++) {
            this.out.display((i).toString() + "." + surveyFiles[i - 1].getName());
        }
        Integer indexForCurrent = surveyFiles.length + 1;
        if(method.equals( "display")){
            this.out.display(indexForCurrent.toString() + "." + "current " + this.getFileType());
        }

        while (true) {
            Integer in = this.in.getIntegerInput("please pick from the list of surveys and enter their numerical index");
            if(in == null) return null;
            if(in.equals(indexForCurrent) && method.equals("display")){
                if(this.returnedSurvey == null){
                    this.out.display("No survey is loaded in or active at the current time");
                    return null;
                }else{
                    return this.returnedSurvey;
                }
            }
            if (in != null && in > 0 && in <= surveyFiles.length) {
                return this.in.deserialize(surveyFiles[in - 1].getPath());
            }
        }
    }

    //if user created a new survey instance but has no questions added, then just set it to null
    public void isSurveyNull(){
        if(this.returnedSurvey.getQuestionSize() == 0){
            this.returnedSurvey = null;
            this.unsavedSurvey = false;
        }
    }

    public Boolean isTest(){
        return false;
    }

    public String getFileType(){
        return "survey";
    }

    public Survey returnNewSurvey(){
        return new Survey();
    }

    //for part 3
    public void ModifySurvey(){

    }

    public void takeSurvey(){

    }

    public void tabulateSurvey(){

    }

    public void gradeTest(){

    }

}
