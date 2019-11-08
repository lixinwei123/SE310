import java.util.ArrayList;
import java.util.Scanner;

public class SurveyMenu extends AbstractMenu {
    protected Survey returnedSurvey;
    SurveyMenu(){
        this.menuItems = new ArrayList<>();
        this.menuItems.add("1.Create a new Survey");
        this.menuItems.add("2.Display a Survey");
        this.menuItems.add("3.Load a Survey");
        this.menuItems.add("4.Save a Survey");
        this.menuItems.add("5.Back");
        this.menuItems.add("6.Quit");
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
                    //NEED TO FIX
                    if(this.goBack()){
                        return;
                    }else{
                        break;
                    }
                case "6":
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
                    break;
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
        this.returnedSurvey = menu3.loadMenuItems();
    }

    public void displaySurvey(){

    }

    public void loadSurvey(){

    }

    public void saveSurvey(){

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
