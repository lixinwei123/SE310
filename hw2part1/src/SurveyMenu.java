import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class SurveyMenu extends AbstractMenu implements Serializable {
    protected Survey returnedSurvey;
    protected String sep;
    SurveyMenu(){
        this.sep = File.separator;
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
                    this.createSurvey();
                    break;
                case "2":
                    this.displaySurvey();
                    break;
                case "3":
                    this.loadSurvey();
                    break;
                case "4":
                    this.saveSurvey("survey");
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
                    continue;
            }
        }
    }

    public Boolean goBack(){
        if(this.returnedSurvey != null){
            while(true){
                String confirmation = this.in.promptAndGet("you have a unsaved survey/test, your changes will be discarded if you do not save, are you sure? T/F");
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

    public void createSurvey(){
        Menu3 menu3 = new Menu3(false);
        this.returnedSurvey = menu3.loadMenuItems(new Survey());
    }

    public void displaySurvey(){
        //finish display, allow to see current survey
    }

    public void loadSurvey(){

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
        try{
             String in = this.in.promptAndGet("please enter a name for your " + fileType);
             FileOutputStream file = new FileOutputStream("Serializable"+ this.sep + fileType +"s" + this.sep + in + ".json");
             ObjectOutputStream out = new ObjectOutputStream(file);
             File path = new File("Serializable" + this.sep + fileType + "s" + this.sep + in + ".json");
             if(path.exists() == true){
                 String confirm = this.in.promptAndGet("a file with the name '"+in+"' already exists, are you sure you want to overwrite it? T/F");
                 if(confirm.equals("T")){
                     out.writeObject(this.returnedSurvey);
                     this.out.display( fileType + "has been saved");
                 }else{
                     this.out.display("save is canceled");
                     return;
                 }
             }

        } catch(IOException e){
            this.out.display(e);
        }
    }

    public void continueSurvey(){
        if(this.returnedSurvey == null){
            this.out.display("no current survey is active");
        }else{
            Menu3 menu3 = new Menu3(false);
            this.returnedSurvey = menu3.loadMenuItems(this.returnedSurvey);
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
}
