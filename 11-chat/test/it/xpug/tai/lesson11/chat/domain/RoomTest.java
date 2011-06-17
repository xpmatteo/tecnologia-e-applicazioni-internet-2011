package it.xpug.tai.lesson11.chat.domain;

import java.util.Iterator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RoomTest {

	Room room = new Room(123, "some room");

	@Test
	public void hasNoMessagesWhenNew() throws Exception {
		assertEquals(0, room.messages().size());
	}
	
	@Test
	public void postAMessage() throws Exception {
		room.addMessage("some text");
		assertEquals(1, room.messages().size());
		assertEquals(new Message(1, "some text"), room.messages().get(0));
	}

	@Test
	public void postTwoMessages() throws Exception {
		room.addMessage("one");
		room.addMessage("two");
		assertEquals(2, room.messages().size());
		Iterator<Message> iterator = room.messages().iterator();
		assertEquals(new Message(1, "one"), iterator.next());
		assertEquals(new Message(2, "two"), iterator.next());
	}
}
