import java.util.ArrayList;

public class Output {


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
}
