package it.xpug.tai.lesson11.chat;

import it.xpug.tai.lesson11.chat.domain.Room;
import it.xpug.tai.lesson11.chat.domain.RoomsList;

import org.junit.Before;
import org.junit.Test;

import static name.vaccari.matteo.html.Assert.assertDomEquals;

import static org.junit.Assert.*;


public class RoomsResourceTest {
	private RoomsList rooms;
	private RoomsResource resource;
	FakeTaiResponse response = new FakeTaiResponse();
	FakeTaiRequest request = new FakeTaiRequest();

	@Before
	public void setUp() throws Exception {
		rooms = new RoomsList();
		resource = new RoomsResource(rooms);
	}

	@Test
	public void handlesItsUrl() throws Exception {
		assertTrue("rooms", resource.wantsToHandle("/rooms"));
		assertFalse("not rooms", resource.wantsToHandle("anything"));
	}
		
	@Test
	public void returnsHtmlForRooms() throws Exception {		
		rooms.add(new Room(123, "Room 123"));
		rooms.add(new Room(456, "Room 456"));
		String expected = "<ul>"
			+ "  <li><a href='/rooms/123'>Room 123</a></li>"
			+ "  <li><a href='/rooms/456'>Room 456</a></li>"
			+ "</ul>";
		
		resource.handle(request, response);
		assertDomEquals(expected, response.text);
	}

	@Test
	public void returnsHtmlContentType() throws Exception {
		resource.handle(request, response);
		assertEquals("text/html", response.contentType);
	}
}
