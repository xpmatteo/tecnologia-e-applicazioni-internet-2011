package it.xpug.tai.lesson11.chat;

import it.xpug.tai.lesson11.chat.domain.Room;
import it.xpug.tai.lesson11.chat.domain.RoomsList;
import it.xpug.tai.lesson11.chat.jetty.TaiController;
import it.xpug.tai.lesson11.chat.jetty.TaiRequest;
import it.xpug.tai.lesson11.chat.jetty.TaiResponse;

import java.io.IOException;

import static java.lang.String.format;

public class RoomsResource implements TaiController {
	private final RoomsList rooms;

	public RoomsResource(RoomsList roomsList) {
		this.rooms = roomsList;
	}

	@Override
	public boolean wantsToHandle(String target) {
		if ("/rooms".equals(target)) {
			return true;
		}
		return false;
	}

	@Override
	public void handle(TaiRequest request, TaiResponse response) throws IOException {
		response.setContentType("text/html");
		response.copyThisTextToOutput(htmlForRoomList());
	}

	private String htmlForRoomList() {
		String html = "<ul>";
		for (Room room : rooms) {
			html += format("<li><a href='/rooms/%d'>%s</a></li>", room.roomId, room.description);
		}
		html += "</ul>";
		return html;
	}

}
