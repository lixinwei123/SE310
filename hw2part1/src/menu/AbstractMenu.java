package menu;
import io.Input;
import io.Output;
import java.util.ArrayList;
/*an abstract class is used to reduce redundant code such as displayMenuItems*/
public abstract class AbstractMenu {
    protected ArrayList<String> menuItems;
    protected Input in;
    protected Output out;
    AbstractMenu(){
        in = new Input();
        out = new Output();
    }

    public void displayMenuItems(){
        Output output = new Output();
        output.displayStringList(this.menuItems);
    }



}
