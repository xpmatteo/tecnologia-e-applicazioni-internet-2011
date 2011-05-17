package it.xpug.tai.lesson07.chat;

import it.xpug.tai.lesson07.chat.jetty.TaiController;
import it.xpug.tai.lesson07.chat.jetty.TaiResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ChatController implements TaiController {

	List<String> messages = new ArrayList<String>();
	
	public ChatController(List<String> messages) {
		this.messages = messages;
	}

	@Override
	public boolean wantsToHandle(String target) {
		return "/chat".equals(target);
	}

	@Override
	public void handle(String target, TaiResponse response) throws IOException {
		response.setStatus(200);
		response.setContentType("text/html");
		String html = "<ul>";
		for (String message : messages) {
			html += "<li>" + message + "</li>";
		}
		html += "</ul>";
		response.copyThisTextToOutput(html);
	}

}
