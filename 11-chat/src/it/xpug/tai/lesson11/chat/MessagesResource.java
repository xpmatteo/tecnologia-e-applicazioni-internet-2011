package it.xpug.tai.lesson11.chat;
import it.xpug.tai.lesson11.chat.domain.Message;
import it.xpug.tai.lesson11.chat.domain.MessagesList;
import it.xpug.tai.lesson11.chat.domain.Room;
import it.xpug.tai.lesson11.chat.domain.RoomsList;
import it.xpug.tai.lesson11.chat.html.Element;
import it.xpug.tai.lesson11.chat.jetty.TaiController;
import it.xpug.tai.lesson11.chat.jetty.TaiRequest;
import it.xpug.tai.lesson11.chat.jetty.TaiResponse;

import java.io.IOException;


public class MessagesResource implements TaiController {

	private static final int DEFAULT_PAGE_SIZE = 100;
	private final RoomsList rooms;
	private int pageSize = DEFAULT_PAGE_SIZE;

	public MessagesResource(RoomsList rooms) {
		this.rooms = rooms;
	}

	public void setPageSize(int pageSize) {
		this.pageSize  = pageSize;
	}

	@Override
	public boolean wantsToHandle(String target) {
		return target.matches("/rooms/\\d+/messages");
	}

	@Override
	public void handle(TaiRequest request, TaiResponse response) throws IOException {
		Room room = findRoom(request);
		if (request.isPost()) {
			room.messages().add(request.getParameter("text"));
		}
		response.setContentType("text/html");
		response.copyThisTextToOutput(buildHtml(room.messages()));
	}

	private Room findRoom(TaiRequest request) {
		String[] split = request.getTarget().split("/");		
		Integer roomId = Integer.parseInt(split[2]);
		return rooms.find(roomId);
	}

	private String buildHtml(MessagesList messages) {
		Element list = new Element("ul");
		for (Message message : messages.last(pageSize)) {
			list.add(new Element("li").text(message.toHtml()));
		}		
		return list.toHtml();
	}
}
