import java.io.Serializable;
import java.util.ArrayList;

public class Output implements Serializable {


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
}
