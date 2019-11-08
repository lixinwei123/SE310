package maze;

import java.awt.*;

public class BrownDoor  extends Door{
    private Room r1;
    private Room r2;
    BrownDoor(Room r1, Room r2){
        super(r1,r2);
        this.r1 = r1;
        this.r2 = r2;
    }

    @Override
    public Color getColor() {
        return new Color(101,67,33);
    }
}
