package maze;


import java.awt.*;

public class GreenRoom extends Room {
    private Integer num;

    GreenRoom(Integer num){
        super(num);
        this.num = num;
    }

    @Override
    public Color getColor() {
        return Color.green;
    }
}
