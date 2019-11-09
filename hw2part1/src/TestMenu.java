import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TestMenu extends SurveyMenu {

    TestMenu(){
        this.menuItems = new ArrayList<>();
        this.menuItems.add("1.Create a new test");
        this.menuItems.add("2.Display a test");
        this.menuItems.add("3.Load a test");
        this.menuItems.add("4.Save a test");
        this.menuItems.add("5.Continue current test");
        this.menuItems.add("6.Back");
        this.menuItems.add("7.Quit");
    }


    @Override
    public void createSurvey() {
        Menu3 menu3 = new Menu3(true);
        this.returnedSurvey = menu3.loadMenuItems(new Test());
    }

    @Override
    public void continueSurvey() {
        if(this.returnedSurvey == null){
            this.out.display("No current test is active");
        }else{
            Menu3 menu3 = new Menu3(false);
            this.returnedSurvey = menu3.loadMenuItems(this.returnedSurvey);
        }
    }

    @Override
    public void saveSurvey(String fileType) {
        super.saveSurvey("test");
    }
}
