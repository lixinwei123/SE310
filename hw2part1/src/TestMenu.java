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
    public void createSurvey(String fileType) {
        super.createSurvey("test");
    }

    @Override
    public void continueSurvey(String fileType) {
        super.continueSurvey("test");
    }

    @Override
    public void saveSurvey(String fileType) {
        super.saveSurvey("test");
    }

    @Override
    public Boolean isTest() {
        return true;
    }

    @Override
    public void displaySurvey(String fileType) {
        super.displaySurvey("test");
    }


}
