package maze;

class RedMazeFactory extends MazeFactory {

    RedMazeFactory(){
        super();
    }

    @Override
    public Wall makeWall() {
        return new RedWall();
    }

    @Override
    public Door makeDoor(Room r1, Room r2) {
         return new Door(r1,r2);
    }

    @Override
    public Room makeRoom(Integer num) {
        return new RedRoom(num);
    }

    @Override
    void move() {

    }
}
