package it.xpug.tai.lesson11.chat.domain;

import static java.lang.String.format;

public class Message {

	private final String text;
	private final Integer messageId;

	public Message(Integer messageId, String text) {
		this.messageId = messageId;
		this.text = text;
	}

	public String toHtml() {
		String html = "" +
			"<p class='message'>" +
			"  <span class='message-id'>%d</span>" +
			"  <span class='message'>%s</span>" +
			"</p>";

		return format(html, messageId, text);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((messageId == null) ? 0 : messageId.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Message))
			return false;
		Message other = (Message) obj;
		if (messageId == null) {
			if (other.messageId != null)
				return false;
		} else if (!messageId.equals(other.messageId))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [" + messageId + ": " + text + "]";
	}
	
	
}
