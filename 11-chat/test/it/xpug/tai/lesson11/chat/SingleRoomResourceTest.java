package it.xpug.tai.lesson11.chat;

import it.xpug.tai.lesson11.chat.Room;
import it.xpug.tai.lesson11.chat.SingleRoomResource;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static name.vaccari.matteo.html.Assert.assertDomEquals;

import static org.junit.Assert.*;


public class SingleRoomResourceTest {
	private List<Room> rooms;
	private SingleRoomResource resource;
	FakeTaiResponse response = new FakeTaiResponse();
	FakeTaiRequest request = new FakeTaiRequest();

	@Before
	public void setUp() throws Exception {
		rooms = new ArrayList<Room>();
		resource = new SingleRoomResource(rooms);
	}

	@Test
	public void handlesUrlForASingleRoom() throws Exception {
		rooms.add(new Room(1234, "a room"));
		assertTrue("rooms existing", resource.wantsToHandle("/rooms/1234"));
		assertFalse("room not existing", resource.wantsToHandle("/rooms/9999"));
	}
	
	@Test
	public void returnsHtmlForSingleRoom() throws Exception {		
		rooms.add(new Room(456, "Room 123"));
		String expected = "" +
			"<div>" +
//			"  <h1 class='name'>Room 123</h1>" +
//			"  <ul>  " +
//			"    <li><a href='/rooms/123/messages' rel='messages'>Messages</a></li>" +
//			"    <li><a href='/rooms/123/users' rel='users'>Users</a></li>" +
//			"  </ul>" +
			"</div>";	

		request.setupTarget("/rooms/123");
		resource.handle(request, response);
		assertEquals("text/html", response.contentType);
		assertDomEquals(expected, response.text);
	}

}
