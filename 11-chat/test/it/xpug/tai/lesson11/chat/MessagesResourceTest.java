package it.xpug.tai.lesson11.chat;

import it.xpug.tai.lesson11.chat.domain.Message;
import it.xpug.tai.lesson11.chat.domain.Room;
import it.xpug.tai.lesson11.chat.domain.RoomsList;

import org.junit.Before;
import org.junit.Test;

import static name.vaccari.matteo.html.Assert.assertDomEquals;

import static org.junit.Assert.*;


public class MessagesResourceTest {

	private static final int PAGE_SIZE = 3;
	
	private Room room = new Room(111, "A room");
	private RoomsList rooms = new RoomsList() {{
		add(room);
		add(new Room(222, "Another room"));
	}};

	private MessagesResource resource = new MessagesResource(rooms);
	FakeTaiResponse response = new FakeTaiResponse();
	FakeTaiRequest request = new FakeTaiRequest();
	
	@Before
	public void setUp() throws Exception {
		resource.setPageSize(PAGE_SIZE);
		request.setupTarget("/rooms/111/messages");
	}

	@Test
	public void handlesItsUrl() throws Exception {
		assertTrue("messages url", resource.wantsToHandle("/rooms/464/messages"));
		assertFalse("any other url", resource.wantsToHandle("/anything"));
		assertFalse("partial url", resource.wantsToHandle("/rooms"));
		assertFalse("partial url 1", resource.wantsToHandle("/rooms/123"));
		assertFalse("partial url 2", resource.wantsToHandle("/rooms/123/"));
		assertFalse("partial url 3", resource.wantsToHandle("/rooms/123/zot"));
	}
	
	@Test
	public void returnsAllMessagesIfLessThanPageSize() throws Exception {
		Message message123 = new Message(123, "messaggio 1");
		Message message124 = new Message(124, "messaggio 2");

		room.addMessage(message123);
		room.addMessage(message124);

		String expected = "<ul>" 
			+ "  <li>" + message123.toHtml() + "</li>"
			+ "  <li>" + message124.toHtml() + "</li>"
			+ "</ul>";

		resource.handle(request, response);
		assertDomEquals(expected, response.text);
	}
	
	@Test
	public void returnsHtml() throws Exception {
		resource.handle(request, response);
		assertEquals("text/html", response.contentType);
	}

	@Test
	public void postAMessage() throws Exception {
		request.setupMethodPost();
		request.setupParameter("text", "some text");
		resource.handle(request, response);
		assertEquals(1, room.messages().size());
		assertEquals(new Message(1, "some text"), room.messages().get(0));
	}
}
