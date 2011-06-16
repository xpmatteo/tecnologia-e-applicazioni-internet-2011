package it.xpug.tai.lesson11.chat;

import it.xpug.tai.lesson11.chat.RoomMessagesResource;
import it.xpug.tai.lesson11.chat.domain.Message;

import java.util.ArrayList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;

import static name.vaccari.matteo.html.Assert.assertDomEquals;


public class RoomMessagesResourceTest {

	private static int PAGE_SIZE = 3;
	private List<Message> messages;
	private RoomMessagesResource resource;
	
	@Before
	public void setUp() throws Exception {
		messages = new ArrayList<Message>();
		resource = new RoomMessagesResource(messages);
		resource.setPageSize(PAGE_SIZE);
	}

	@Test
	public void returnsAllMessagesIfLessThanPageSize() throws Exception {
		Message message123 = new Message(123, "messaggio 1");
		Message message124 = new Message(124, "messaggio 2");
		messages.add(message123);
		messages.add(message124);

		String expected = "<ol>" 
			+ "  <li>" + message123.toHtml() + "</li>"
			+ "  <li>" + message123.toHtml() + "</li>"
			+ "</ol>";

		FakeTaiResponse response = new FakeTaiResponse();
		FakeTaiRequest request = new FakeTaiRequest();

		resource.handle(request , response);
		assertDomEquals(expected, response.text);
	}
}
