package it.xpug.tai.lesson11.chat.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RoomsList implements Iterable<Room> {
	private List<Room> rooms = new ArrayList<Room>();

	public void add(Room room) {
		rooms.add(room);
	}

	@Override
	public Iterator<Room> iterator() {
		return rooms.iterator();
	}
	
	public Room find(Object roomId) {
		for (Room room : rooms) {
			if (room.roomId.equals(roomId)) {
				return room;
			}
		}
		return null;
	}
	
	public boolean contains(Object roomId) {
		return null != find(roomId);
	}
}
