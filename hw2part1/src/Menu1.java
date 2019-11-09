import java.util.ArrayList;
import java.util.Scanner;

public class Menu1 extends AbstractMenu {
    private SurveyMenu menu;
    Menu1(){
        this.menuItems = new ArrayList<>();
        this.menuItems.add("1.survey");
        this.menuItems.add("2.test");
        this.menuItems.add("3.quit");
        this.loadMenuItems();
    }

public void loadMenuItems(){
    while(true){
        this.displayMenuItems();
        String userChoice = this.in.promptAndGet("");
        switch (userChoice){
            case "1":
                menu = new SurveyMenu();
                menu.loadMenuItems();
                break;
            case "2":
                menu = new TestMenu();
                menu.loadMenuItems();
                break;
            case "3":
                this.out.display("have a good day~");
                System.exit(0);
            default:
                this.out.display("not a valid input, try again");
                continue;
        }
    }
}


}
