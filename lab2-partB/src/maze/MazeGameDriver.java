package maze;

import maze.ui.MazeViewer;

import java.util.Iterator;

public class MazeGameDriver {

    public static void  main(String[] args)
    {
        Maze maze;
        MazeFactory mazeFactory;
        if(args[0].equals( "blue")){
            maze = loadMaze("large.maze", new BlueMazeFactory());
        }
        else{
            maze = loadMaze("large.maze", new RedMazeFactory());
        }
        Iterator<Room> rooms = maze.iterator();
        MazeViewer viewer = new MazeViewer(maze);
        viewer.run();
    }

    public static Maze loadMaze(String path, MazeFactory factory){
        return factory.loadMaze(path);
    }

}
