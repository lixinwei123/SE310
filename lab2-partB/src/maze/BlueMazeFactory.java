package maze;

class BlueMazeFactory extends MazeFactory {

    @Override
    public Wall makeWall() {
        return new BlueWall();
    }

    @Override
    public Door makeDoor(Room r1, Room r2) {
         return new BrownDoor(r1,r2);
    }

    @Override
    public Room makeRoom(Integer num) {
        return new GreenRoom(num);
    }

}
