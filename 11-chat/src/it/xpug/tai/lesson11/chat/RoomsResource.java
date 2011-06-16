package it.xpug.tai.lesson11.chat;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

import it.xpug.tai.lesson11.chat.jetty.TaiController;
import it.xpug.tai.lesson11.chat.jetty.TaiRequest;
import it.xpug.tai.lesson11.chat.jetty.TaiResponse;

public class RoomsResource implements TaiController {
	private final List<Room> rooms;

	public RoomsResource(List<Room> rooms) {
		this.rooms = rooms;
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
		String html;
		html = htmlForRoomList();
		response.setContentType("text/html");
		response.copyThisTextToOutput(html);
	}

	private String htmlForRoomList() {
		String html = "<ul>";
		for (Room room : rooms) {
			html += format("<li><a href='/rooms/%d'>%s</a></li>", room.get("id"), room.get("description"));
		}
		html += "</ul>";
		return html;
	}

}
