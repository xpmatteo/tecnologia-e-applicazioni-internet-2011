package it.xpug.tai.lesson10.chat;

import it.xpug.tai.lesson10.chat.Room;
import it.xpug.tai.lesson10.chat.RoomsResource;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static name.vaccari.matteo.html.Assert.assertDomEquals;

import static org.junit.Assert.*;


public class RoomsResourceTest {
	
	private List<Room> rooms;
	private RoomsResource page;

	@Before
	public void setUp() throws Exception {
		rooms = new ArrayList<Room>();
		page = new RoomsResource(rooms);

	}

	@Test
	public void handlesItsUrl() throws Exception {
		assertTrue("rooms", page.wantsToHandle("/rooms"));
		assertFalse("not rooms", page.wantsToHandle("anything"));
	}
	
	@Test
	public void returnsHtmlForRooms() throws Exception {		
		rooms.add(new Room(123, "Room 123"));
		rooms.add(new Room(456, "Room 456"));
		String expected = "<ul>"
			+ "  <li><a href='/rooms/123'>Room 123</a></li>"
			+ "  <li><a href='/rooms/456'>Room 456</a></li>"
			+ "</ul>";
		
		FakeTaiResponse response = new FakeTaiResponse();
		
		page.handle(null, response );
		assertDomEquals(expected, response.text);
	}
	
	@Test
	public void returnsHtmlContentType() throws Exception {
		FakeTaiResponse response = new FakeTaiResponse();
		
		page.handle(null, response );
		assertEquals("text/html", response.contentType);
	}
}
