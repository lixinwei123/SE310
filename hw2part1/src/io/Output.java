package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Output implements Serializable {

    protected static final long serialversionUID = 14552024;
    public void displayStringList(ArrayList<String> items){
        for(int i = 0; i< items.size(); i++){
            this.display(items.get(i));
        }
    }
    //object pass
    public void display(Object c){
        System.out.println(c);
//        if(c instanceof String){
//            System.out.println(c);
//        }
    };

    public void displaySameLine(Object c){
        System.out.print(c);
//        if(c instanceof String){
//            System.out.println(c);
//        }
    };

    public void serialize(Object object, String path){
        try{
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(object);
            file.close();
            out.close();
        }catch (IOException e){
            this.display(e);
        }
    }
}
