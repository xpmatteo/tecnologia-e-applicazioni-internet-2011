package it.xpug.tai.lesson11.chat.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessagesList implements Iterable<Message> {

	private final List<Message> messages;

	public MessagesList() {
		this.messages = new ArrayList();
	}

	protected MessagesList(List<Message> messages) {
		this.messages = new ArrayList(messages);
	}

	public int size() {
		return messages.size();
	}

	public void add(String text) {
		int messageId = 1 + messages.size();
		add(new Message(messageId, text));
	}

	public void add(Message message) {
		messages.add(message);
	}

	public Message get(int index) {
		return messages.get(index);
	}

	@Override
	public Iterator<Message> iterator() {
		return messages.iterator();
	}

	public MessagesList last(int pageSize) {
		int toIndex = messages.size();
		int fromIndex = Math.max(0, toIndex - pageSize);
		return new MessagesList(messages.subList(fromIndex, toIndex));
	}
}
