import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Input implements Serializable {
    private Output out;
    Input(){
        this.out = new Output();
    }
    public String promptAndGet(String prompt){
        this.out.display(prompt);
        while(true){
            try{
                Scanner confirmation = new Scanner(System.in);
                return confirmation.nextLine();
            } catch (Exception e){
                this.out.display("incorrect input, please try again");
            }
        }
    }

    public ArrayList<String> getMultipleInput(Integer num, String prompt){
        ArrayList<String> inputs = new ArrayList<>();
        for(Integer i = 1; i < num + 1; i ++){
            inputs.add(promptAndGet(prompt +" " + i.toString()));
        }
        return inputs;
    }

    public Integer getIntegerInput(String prompt){
        while(true){
            try{
                return Integer.parseInt(this.promptAndGet(prompt));
            }catch(Exception e){
                this.out.display(e);
            }
        }
    }

    public void checkOutOfBoundException(){

    }

}
