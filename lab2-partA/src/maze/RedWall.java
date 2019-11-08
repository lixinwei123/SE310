package maze;

import java.awt.*;

public class RedWall extends Wall {
    RedWall(){
        super();
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }
}
