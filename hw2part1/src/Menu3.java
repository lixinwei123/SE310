import java.util.ArrayList;
import java.util.Scanner;

public class Menu3 extends AbstractMenu {
    private Boolean isTest;
    private Survey survey;
    Menu3(Boolean isTest){
        this.isTest = isTest;
        if(isTest == true){
            survey = new Survey();
        }else{
            survey = new Test();
        }
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

    public Survey loadMenuItems()  {
        while(true){
            this.displayMenuItems();
            Scanner userMenuChoice = new Scanner(System.in);
            String currentMenu = userMenuChoice.nextLine();
            switch (currentMenu){
                case "1":
                    this.addTF();
                    break;
                case "2":
                    this.addMC();
                    break;
                case "3":
                    this.addShortAns();
                    break;
                case "4":
                    this.addEssay();
                    break;
                case "5":
                    this.addEmoji();
                    break;
                case "6":
                    this.addMatch();
                    break;
                case "7":
                    return survey;
                default:
                    this.out.display("not a valid input, try again");
                    continue;
            }
        }
    }

    public void addTF(){
        TrueFalse responses = new TrueFalse();
        responses.loadQustion(); //request user to input all the questions
        if(this.isTest == true){
            responses.loadCorrectAnswer();
        }
        this.survey.addQuestion(responses);
        this.out.display("question added");
    }

    public void addMC(){
        MultipleChoice responses = new MultipleChoice();
        responses.loadQustion(); //request user to input all the questions
        if(this.isTest == true){
            responses.loadCorrectAnswer();
        }
        this.survey.addQuestion(responses);
        this.out.display("question added");
    }

    public void addShortAns(){

    }

    public void addEssay(){

    }

    public void addEmoji(){
        Emoji responses = new Emoji();
        responses.loadQustion(); //request user to input all the questions
        this.survey.addQuestion(responses);
        this.out.display("question added");
    }

    public void addMatch(){

    }


}
