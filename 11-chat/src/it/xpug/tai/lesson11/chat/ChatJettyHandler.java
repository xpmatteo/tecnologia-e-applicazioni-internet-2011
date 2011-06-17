package it.xpug.tai.lesson11.chat;

import it.xpug.tai.lesson11.chat.domain.Room;
import it.xpug.tai.lesson11.chat.domain.RoomsList;
import it.xpug.tai.lesson11.chat.jetty.ReusableJettyHandler;
import it.xpug.tai.lesson11.chat.jetty.TaiController;

import java.util.List;

import static java.util.Arrays.asList;

public class ChatJettyHandler extends ReusableJettyHandler {
	RoomsList roomsList = new RoomsList() {{
		add(new Room(1313, "non entrate in questa stanza"));
		add(new Room(123, "questa stanza invece va bene"));
	}};

	@Override
	protected List<TaiController> getControllers() {
		TaiController rooms = new RoomsResource(roomsList);
		TaiController singleRoom = new SingleRoomResource(roomsList);
		TaiController messages = new MessagesResource(roomsList);
		return asList(rooms, singleRoom, messages);
	}
}

