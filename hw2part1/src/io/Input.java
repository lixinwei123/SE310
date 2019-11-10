package io;

import survey.Survey;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Input implements Serializable {
    private Output out;
    private static final long serialversionUID = 14552024;
    public Input(){
        this.out = new Output();
    }

    public String promptAndGet(String prompt){
        if(prompt.isBlank() == false){
            this.out.display(prompt);
        }
        return this.getInput();
    }

    public ArrayList<String> getMultipleInput(Integer num, String prompt){
        ArrayList<String> inputs = new ArrayList<>();
        for(Integer i = 1; i < num + 1; i ++){
            while(true){
                String in = promptAndGet(prompt +" " + i.toString());
                if(!inputs.contains(in)){
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
                Integer in = Integer.parseInt(this.promptAndGet(prompt));
                if(in < 0) {
                    this.out.display("must be greater than 0");
                    return null;
                }
                return in;
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
    public String getInput(){
        while(true){
            try{
                Scanner confirmation = new Scanner(System.in);
                String in = confirmation.nextLine().trim();
                if(in.isBlank()){
                    this.out.display("input cannot be blank");
                    continue;
                }
                return in;
            } catch (Exception e){
                this.out.display("incorrect input, please try again");
            }
        }
    }
}
