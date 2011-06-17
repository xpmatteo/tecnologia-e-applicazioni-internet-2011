package it.xpug.tai.lesson11.chat.domain;


public class Room {

	public Integer roomId;
	public String description;
	private MessagesList messages = new MessagesList();

	public Room(Integer roomId, String description) {
		this.roomId = roomId;
		this.description = description;
	}

	public MessagesList messages() {
		return messages;
	}

	public void addMessage(String text) {
		messages.add(text);
	}

	public void addMessage(Message message) {
		messages.add(message);
	}
}
