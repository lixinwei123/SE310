import java.util.ArrayList;
import java.util.Scanner;

public class TestMenu extends SurveyMenu {

    TestMenu(){
        this.menuItems = new ArrayList<>();
        this.menuItems.add("1.Create a new Test");
        this.menuItems.add("2.Display a Test");
        this.menuItems.add("3.Load a Test");
        this.menuItems.add("4.Save a Test");
        this.menuItems.add("5.Back");
        this.menuItems.add("6.Quit");
    }


    @Override
    public void createSurvey() {
        Menu3 menu3 = new Menu3(true);
        this.returnedSurvey = menu3.loadMenuItems();
    }


}
