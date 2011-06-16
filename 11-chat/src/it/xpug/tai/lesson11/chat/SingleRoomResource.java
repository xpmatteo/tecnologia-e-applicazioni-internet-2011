package it.xpug.tai.lesson11.chat;

import it.xpug.tai.lesson11.chat.jetty.TaiController;
import it.xpug.tai.lesson11.chat.jetty.TaiRequest;
import it.xpug.tai.lesson11.chat.jetty.TaiResponse;

import java.io.IOException;
import java.util.List;

public class SingleRoomResource implements TaiController {

	private final List<Room> rooms;

	public SingleRoomResource(List<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public boolean wantsToHandle(String target) {
		if (target.matches("/rooms/\\d+")) {
			Object roomId = extractRoomIdFromTarget(target);			
			return roomsListContains(roomId);
		}
		return false;
	}

	@Override
	public void handle(TaiRequest request, TaiResponse response) throws IOException {
		String target = request.getTarget();
		String html = htmlForSingleRoom(roomFor(extractRoomIdFromTarget(target)));
		response.setContentType("text/html");
		response.copyThisTextToOutput(html);
	}

	private String htmlForSingleRoom(Object roomId) {
		return "<div></div>";
	}


	private boolean roomsListContains(Object roomId) {
		return null != roomFor(roomId);
	}

	private Room roomFor(Object roomId) {
		for (Room room : rooms) {
			if (room.get("id").equals(roomId)) {
				return room;
			}
		}
		return null;
	}

	private Object extractRoomIdFromTarget(String target) {
		int lastIndexOf = target.lastIndexOf("/");
		return Integer.parseInt(target.substring(lastIndexOf+1));
	}

}
