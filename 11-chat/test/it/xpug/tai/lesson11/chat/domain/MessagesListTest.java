package it.xpug.tai.lesson11.chat.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static java.util.Arrays.asList;


public class MessagesListTest {

	@Test
	public void returnLastXMessages() throws Exception {
		MessagesList fullList = new MessagesList();
		Message message0 = createMessage();
		Message message1 = createMessage();
		Message message2 = createMessage();
		fullList.add(message0);
		fullList.add(message1);
		fullList.add(message2);
		
		assertContains(new ArrayList<Message>(), fullList.last(0));
		assertContains(asList(message2), fullList.last(1));
		assertContains(asList(message1, message2), fullList.last(2));
		assertContains(asList(message0, message1, message2), fullList.last(3));
		assertContains(asList(message0, message1, message2), fullList.last(4));
	}

	private void assertContains(List<Message> expected, MessagesList actual) {
		List<Message> actualList = new ArrayList<Message>();
		for (Message message : actual) {
			actualList.add(message);			
		}
		assertEquals(expected, actualList);
	}

	private int messageCount = 0;
	private Message createMessage() {
		return new Message(++messageCount, "Message #" + messageCount);
	}
}
