import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class SurveyMenu extends AbstractMenu implements Serializable {
    protected Survey returnedSurvey;
    protected String sep;
    protected Boolean unsavedSurvey;
    SurveyMenu(){
        this.sep = File.separator;
        this.unsavedSurvey = false;
        this.menuItems = new ArrayList<>();
        this.menuItems.add("1.Create a new Survey");
        this.menuItems.add("2.Display a survey");
        this.menuItems.add("3.Load a survey");
        this.menuItems.add("4.Save a survey");
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
                    this.createSurvey("survey");
                    break;
                case "2":
                    this.displaySurvey("survey");
                    break;
                case "3":
                    this.loadSurvey();
                    break;
                case "4":
                    this.saveSurvey("survey");
                    break;
                case "5":
                    this.continueSurvey("survey");
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
                    continue;
            }
        }
    }

    public Boolean goBack(){
        if(this.returnedSurvey != null){
            while(true){
                String confirmation = this.in.promptAndGet("If you have a unsaved survey/test, your changes will be discarded if you do not save, are you sure? T/F");
                if(confirmation.equals("T")){
                    return true;
                }else if(confirmation.equals("F")){
                    return false;
                }else{
                    this.out.display("invalid input, please try again");
                    continue;
                }
            }
        }
        return true;
    }

    public void createSurvey(String fileType) {
        Menu3 menu3 = new Menu3(this.isTest());
        if (this.unsavedSurvey == true) {
            String in = this.in.promptAndGet("it seems you have an unsaved " + fileType + ", enter 5 to continue, enter 1 to start a new survey ");
            if (in.equals("5")) {
                this.continueSurvey(fileType);
                return;
            }
        }
        this.returnedSurvey = menu3.loadMenuItems(new Survey());
        this.unsavedSurvey = true;
        this.isSurveyNull();
    }

    public void displaySurvey(String fileType){
        File[] surveyFiles = new File("Serializable" + this.sep + fileType+"s").listFiles();
        if(surveyFiles.length < 1){
            this.out.display("No " + fileType + "s are stored");
            return;
        }
        this.out.display("please select a file you want to display");
        for(Integer i = 1; i < surveyFiles.length + 1;i++){
            this.out.display((i).toString() + "." + surveyFiles[i -1].getName());
        }
        while(true){
            Integer in = this.in.getIntegerInput("please pick from the list of surveys and enter their numerical index");
            Integer index = 1;
            if(in != null && in > 0 && in <= surveyFiles.length) {
                    Survey returnedObj = this.in.deserialize(surveyFiles[in - 1].getPath());
                    for(Question q: returnedObj.getQuestions()){
                        this.out.displaySameLine(index.toString() + ") ");
                        q.display(this.isTest());
                        this.out.display("");
                        index +=1;
                    }
                return;
            }
        }


    }

    public void loadSurvey(){

    }

    public Boolean isTest(){
        return false;
    }

    /*
    save function check to see if there is an ongoing survey before launching it
    check to see if there already exists a survey named that
    check for ioException
    * */
    public void saveSurvey(String fileType)  {
        if(this.returnedSurvey == null){
            this.out.display("no " + fileType + " is active at the moment to save");
            return;
        }
             String in = this.in.promptAndGet("please enter a name for your " + fileType);
             File path = new File("Serializable" + this.sep + fileType + "s" + this.sep + in + ".ser");
//             File absPath = new File(path.getAbsolutePath());
             if(path.exists()){
                 String confirm = this.in.promptAndGet("a file with the name '"+in+"' already exists, are you sure you want to overwrite it? Enter 'T' to overwrite, else to stop");
                 if(!confirm.equals("T")){
                     this.out.display("save is canceled");
                     return;
                 }
             }
             this.out.serialize(this.returnedSurvey,"Serializable"+ this.sep + fileType +"s" + this.sep + in + ".ser");
             this.out.display( fileType + " has been saved");
             this.unsavedSurvey = false;
    }

    public void continueSurvey(String fileType){
        if(this.returnedSurvey == null){
            this.out.display("no current " + fileType + " is active");
        }else{
            Menu3 menu3 = new Menu3(this.isTest());
            this.returnedSurvey = menu3.loadMenuItems(this.returnedSurvey);
            this.isSurveyNull();
        }
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

    //if user created a new survey instance but has no questions added, then just set it to null
    public void isSurveyNull(){
        if(this.returnedSurvey.getQuestionSize() == 0){
            this.returnedSurvey = null;
            this.unsavedSurvey = false;
        }
    }

}
