/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import maze.ui.MazeViewer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @author Kevin Li
 */
abstract class MazeFactory implements makeDog
{
	/**
	 * Creates a small maze.
	 */

	public Maze createMaze()
	{

		Maze maze = new Maze();
		Room room0 = makeRoom(0);
		Wall wall = new Wall();
		Room room1= makeRoom(1);

		maze.addRoom(room0);
		maze.addRoom(room1);
		Door door = makeDoor(room0,room1);
		room0.setSide(Direction.North,wall);
		room0.setSide(Direction.South,wall);
		room0.setSide(Direction.East,door);
		room0.setSide(Direction.West,wall);

		room1.setSide(Direction.North,wall);
		room1.setSide(Direction.South,wall);
		room1.setSide(Direction.East,wall);
		room1.setSide(Direction.West,door);
		return maze;
		

	}



	public  Maze loadMaze(final String path)
	{
		Map<String,Door > doors = new HashMap<String, Door>(); //create a hashmap to store doors
		Map<Integer,String[] > roomInfoArray = new HashMap<Integer, String[]>();//create a hashmap to store rooms which I later create
		Maze maze = new Maze();
		File file = new File(path);
		String absolutePath = file.getAbsolutePath(); //need the absolute path since windows has \ and mac has /
		try {
			BufferedReader mazeReader = new BufferedReader(new FileReader(absolutePath)); //create a bufferreader for reading file
			String mazeLine = mazeReader.readLine(); //read first line
			while (mazeLine != null && mazeLine.isBlank() == false){ //loop through the whole file line by line
				String[] lineToArray = mazeLine.split(" ");
				if(lineToArray[0].equals("room")){
					Integer roomNumber = Integer.parseInt(lineToArray[1]); //extract room number
					maze.addRoom(makeRoom(roomNumber));
					String[] roomInfo;
					roomInfo = new String[4];  //create a new string array
					//assign the 4 pieces of direction to this string array
					roomInfo[0] = lineToArray[2];
					roomInfo[1] = lineToArray[3];
					roomInfo[2] = lineToArray[4];
					roomInfo[3] = lineToArray[5];
					roomInfoArray.put(roomNumber,roomInfo);
				}else{
					Room room1 = maze.getRoom(Integer.parseInt(lineToArray[2]));
					Room room2 = maze.getRoom(Integer.parseInt(lineToArray[3]));
					Door door = makeDoor(room1,room2); //create a door
					doors.put(lineToArray[1],door);
				}
				mazeLine = mazeReader.readLine();
			}
		} catch(IOException error){
			error.printStackTrace();;
		}
		Iterator iterator = roomInfoArray.entrySet().iterator();
		//loop through the rooms hashmap, then set the different direction
		while (iterator.hasNext()) {
			Map.Entry infoPair = (Map.Entry) iterator.next();
			Room currentRoom = maze.getRoom(Integer.parseInt(infoPair.getKey().toString()));
			Wall wall = makeWall();
			String[] roomInfoValues = (String[]) infoPair.getValue();

			if(roomInfoValues[0].equals("wall")){
				currentRoom.setSide(Direction.North,wall);
			}else if (roomInfoValues[0].contains("d")){
				currentRoom.setSide(Direction.North,doors.get(roomInfoValues[0]));
			}else{
				currentRoom.setSide(Direction.North,maze.getRoom(Integer.parseInt(roomInfoValues[0])));
			}

			if(roomInfoValues[1].equals("wall")){
				currentRoom.setSide(Direction.South,wall);
			}else if (roomInfoValues[1].contains("d")){
				currentRoom.setSide(Direction.South,doors.get(roomInfoValues[1]));
			}else{
				currentRoom.setSide(Direction.South,maze.getRoom(Integer.parseInt(roomInfoValues[1])));
			}

			if(roomInfoValues[2].equals("wall")){
				currentRoom.setSide(Direction.East,wall);
			}else if (roomInfoValues[2].contains("d")){
				currentRoom.setSide(Direction.East,doors.get(roomInfoValues[2]));
			}else{
				currentRoom.setSide(Direction.East,maze.getRoom(Integer.parseInt(roomInfoValues[2])));
			}

			if(roomInfoValues[3].equals("wall")){
				currentRoom.setSide(Direction.West,wall);
			}else if (roomInfoValues[3].contains("d")){
				currentRoom.setSide(Direction.West,doors.get(roomInfoValues[3]));
			}else{
				currentRoom.setSide(Direction.West,maze.getRoom(Integer.parseInt(roomInfoValues[3])));
			}

		}
		maze.setCurrentRoom(maze.getRoom(0));
		return maze;
	}

}

	abstract void move();

	public Wall makeWall(){
		return new Wall();
	}

	public Door makeDoor(Room r1, Room r2){
		return new Door(r1, r2);
	}

	public Room makeRoom(Integer num){
		return new Room(num);
	}


}
