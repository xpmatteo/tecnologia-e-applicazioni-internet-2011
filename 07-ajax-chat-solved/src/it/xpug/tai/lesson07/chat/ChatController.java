package it.xpug.tai.lesson07.chat;

import it.xpug.tai.lesson07.chat.jetty.TaiController;
import it.xpug.tai.lesson07.chat.jetty.TaiRequest;
import it.xpug.tai.lesson07.chat.jetty.TaiResponse;

import java.io.IOException;
import java.util.List;


public class ChatController implements TaiController {

	private List<String> messages;
	
	public ChatController(List<String> messages) {
		this.messages = messages;
	}

	@Override
	public boolean wantsToHandle(String target) {
		return "/chat".equals(target);
	}

	@Override
	public void handle(TaiRequest request, TaiResponse response) throws IOException {		
		if (null != request.getParameter("message")) {
			messages.add(request.getParameter("message"));			
		}
		
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
