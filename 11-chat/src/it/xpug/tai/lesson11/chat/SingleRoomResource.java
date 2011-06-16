package it.xpug.tai.lesson11.chat;

import it.xpug.tai.lesson11.chat.domain.Room;
import it.xpug.tai.lesson11.chat.domain.RoomsList;
import it.xpug.tai.lesson11.chat.html.Element;
import it.xpug.tai.lesson11.chat.html.Link;
import it.xpug.tai.lesson11.chat.jetty.TaiController;
import it.xpug.tai.lesson11.chat.jetty.TaiRequest;
import it.xpug.tai.lesson11.chat.jetty.TaiResponse;

import java.io.IOException;

import static java.lang.String.format;

public class SingleRoomResource implements TaiController {

	private final RoomsList rooms;

	public SingleRoomResource(RoomsList roomsList) {
		this.rooms = roomsList;
	}

	@Override
	public boolean wantsToHandle(String target) {
		if (target.matches("/rooms/\\d+")) {
			Object roomId = extractRoomIdFromTarget(target);			
			return rooms.contains(roomId);
		}
		return false;
	}

	@Override
	public void handle(TaiRequest request, TaiResponse response) throws IOException {
		response.setContentType("text/html");
		String target = request.getTarget();
		Room room = rooms.find(extractRoomIdFromTarget(target));
		response.copyThisTextToOutput(htmlForSingleRoom(room));
	}

	private String htmlForSingleRoom(final Room room) {
		return new Element("div") {{
			add(new Element("h1") {{ 
				with("class", "description");
				text(room.description);
			}});
			add(new Element("ul") {{
				add(new Element("li") {{
					add(messagesLink(room));
				}});
				add(new Element("li") {{
					add(usersLink(room));
				}});
			}});
		}}.toHtml();
	}
	
	private Element usersLink(final Room room) {
		String url = urlFor(room) + "/users";
		return new Link(url, "Users").with("rel", "users");
	}
	
	private Element messagesLink(final Room room) {
		String url = urlFor(room) + "/messages";
		return new Link(url, "Messages").with("rel", "messages");
	}
	
	private String urlFor(Room room) {
		return format("/rooms/%s", room.roomId);
	}
	
	private Object extractRoomIdFromTarget(String target) {
		int lastIndexOf = target.lastIndexOf("/");
		return Integer.parseInt(target.substring(lastIndexOf+1));
	}

}
