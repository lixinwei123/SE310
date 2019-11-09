package io;

import survey.Survey;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Input implements Serializable {
    private Output out;
    public Input(){
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
            while(true){
                String in = promptAndGet(prompt +" " + i.toString());
                if(inputs.contains(in) == false){
                    inputs.add(in);
                    break;
                }else{
                    this.out.display("you have already entered this choice, please enter a different one");
                }
            }
        }
        return inputs;
    }

    public Integer getIntegerInput(String prompt){
        while(true){
            try{
                return Integer.parseInt(this.promptAndGet(prompt));
            }catch(Exception e){
                this.out.display("must be an integer");
                return null;
            }
        }
    }

    public Survey deserialize(String path){
        try{
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream inp = new ObjectInputStream(file);
            Survey surv = (Survey) inp.readObject();
            file.close();
            inp.close();
            return surv;
        }catch(IOException | ClassNotFoundException e){
            this.out.display(e);
            return null;
        }
    }

}
