package menu;

import survey.Survey;
import survey.Test;

import java.util.ArrayList;

public class TestMenu extends SurveyMenu {

    TestMenu(){
        this.menuItems = new ArrayList<>();
        this.menuItems.add("1.Create a new test");
        this.menuItems.add("2.Display a test");
        this.menuItems.add("3.Load a test");
        this.menuItems.add("4.Save current test");
        this.menuItems.add("5.Continue current test");
        this.menuItems.add("6.Back");
        this.menuItems.add("7.Quit");
    }



    @Override
    public Boolean isTest() {
        return true;
    }


    @Override
    public Survey returnNewSurvey() {
        return new Test();
    }


    @Override
    public String getFileType() {
        return "test";
    }
}
