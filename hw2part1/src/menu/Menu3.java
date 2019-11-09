package menu;

import java.util.ArrayList;
import java.util.Scanner;
import question.*;
import survey.Survey;

public class Menu3 extends AbstractMenu {
    private Boolean isTest;
        Menu3(Boolean isTest){
            this.isTest = isTest;
            this.menuItems = new ArrayList<>();
            this.menuItems.add("1.Add a new T/F question");
            this.menuItems.add("2.Add a new multiple-choice question");
            this.menuItems.add("3.Add a new short answer question");
            this.menuItems.add("4.Add a new essay question");
            this.menuItems.add("5.Add a new emoji question");
            this.menuItems.add("6.Add a new matching question");
            this.menuItems.add("7.back");
            this.menuItems.add("8.quit");
        }


    public Survey loadMenuItems(Survey survey)  {
        while(true){
            this.displayMenuItems();
            Scanner userMenuChoice = new Scanner(System.in);
            String currentMenu = userMenuChoice.nextLine();
            switch (currentMenu){
                case "1":
                    this.addTF(survey);
                    break;
                case "2":
                    this.addMC(survey);
                    break;
                case "3":
                    this.addShortAns(survey);
                    break;
                case "4":
                    this.addEssay(survey);
                    break;
                case "5":
                    this.addEmoji(survey);
                    break;
                case "6":
                    this.addMatch(survey);
                    break;
                case "7":
                    return survey;
                case "8":
                    String in = this.in.promptAndGet("are you sure you want to quit? unsaved changes will be discarded. type 'Y' to confirm ");
                    if(in.matches("Y")){
                        System.exit(0);
                    }
                default:
                    this.out.display("not a valid input, try again");
                    continue;
            }
        }
    }

    public void addTF(Survey survey){
        TrueFalse responses = new TrueFalse();
        responses.loadQuestion(); //request user to input all the questions
        if(this.isTest){
            responses.loadCorrectAnswer("Enter the correct answer. true/false");
        }
        survey.addQuestion(responses);
        this.out.display("question added");
    }

    public void addMC(Survey survey){
        MultipleChoice responses = new MultipleChoice();
        responses.loadQuestion(); //request user to input all the questions
        if(this.isTest){
            responses.loadCorrectAnswer("Enter the correct answer");
        }
        survey.addQuestion(responses);
        this.out.display("question added");
    }

    public void addShortAns(Survey survey){
        ShortAnswer sa = new ShortAnswer();
        sa.loadQuestion();
        survey.addQuestion(sa);
        this.out.display("question added");
    }

    public void addEssay(Survey survey){
        Essay essay = new Essay();
        essay.loadQuestion();
        survey.addQuestion(essay);
        this.out.display("question added");
    }

    public void addEmoji(Survey survey){
        Emoji responses = new Emoji();
        responses.loadQustion(); //request user to input all the questions
        survey.addQuestion(responses);
        this.out.display("question added");
    }

    public void addMatch(Survey survey){
        Matching responses = new Matching();
        responses.loadQuestion(); //request user to input all the questions
        if(this.isTest){
            responses.loadCorrectAnswer();
        }
        survey.addQuestion(responses);
        this.out.display("question added");
    }


}
