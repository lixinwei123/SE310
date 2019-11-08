package maze;

import java.awt.*;

public class BlueWall extends Wall{
    BlueWall(){
        super();
    }

    @Override
    public Color getColor() {
        return Color.BLUE;
    }
}
