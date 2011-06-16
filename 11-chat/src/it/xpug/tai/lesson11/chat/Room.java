package it.xpug.tai.lesson11.chat;

import java.util.HashMap;
import java.util.Map;

public class Room {

	private Map<String, Object> attributes = new HashMap<String, Object>();  
	
	public Room(int roomId, String description) {
		attributes.put("id", roomId);
		attributes.put("description", description);
	}

	public Object get(String key) {
		return attributes.get(key);
	}

	
}
