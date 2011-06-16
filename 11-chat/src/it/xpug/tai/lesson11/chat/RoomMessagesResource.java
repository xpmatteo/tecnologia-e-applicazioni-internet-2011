package it.xpug.tai.lesson11.chat;
import it.xpug.tai.lesson11.chat.jetty.TaiController;
import it.xpug.tai.lesson11.chat.jetty.TaiRequest;
import it.xpug.tai.lesson11.chat.jetty.TaiResponse;

import java.io.IOException;
import java.util.List;


public class RoomMessagesResource implements TaiController {

	private final List<Message> messages;

	public RoomMessagesResource(List<Message> messages) {
		this.messages = messages;
	}

	public void setPageSize(int pageSize) {
	}

	@Override
	public boolean wantsToHandle(String target) {
		throw new RuntimeException("Not yet implemented!");
	}

	@Override
	public void handle(TaiRequest request, TaiResponse response) throws IOException {
		response.copyThisTextToOutput(buildHtml());
	}

	private String buildHtml() {
		String result = "<ol>";
		for (Message message : messages) {
			result += "<li>" + message.toHtml() + "</li>";
		}
		result += "</ol>";
		return result;
	}

}
