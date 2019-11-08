import java.util.ArrayList;

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
