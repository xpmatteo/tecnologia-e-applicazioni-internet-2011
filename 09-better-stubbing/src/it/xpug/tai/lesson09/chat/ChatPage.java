package it.xpug.tai.lesson09.chat;

import it.xpug.tai.lesson09.chat.jetty.TaiController;
import it.xpug.tai.lesson09.chat.jetty.TaiRequest;
import it.xpug.tai.lesson09.chat.jetty.TaiResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ChatPage implements TaiController {

	private List<String> messages;
	private Date lastModified;
	
	public ChatPage(List<String> messages) {
		this.lastModified = new Date();
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
			lastModified = new Date();
		} 
		
		response.setStatus(200);
		response.setContentType("text/html");
		addTimestamp(response);

		response.copyThisTextToOutput(produceHtml());
	}

	private String produceHtml() {
		String html = "<ul>";		
		for (String message : messages) {
			html += "<li>" + message + "</li>";
		}
		html += "</ul>";
		return html;
	}

	private void addTimestamp(TaiResponse response) {		
		response.setHeader("Last-Modified", rfcDateFormat().format(lastModified ));
	}

	private SimpleDateFormat rfcDateFormat() {
		String RFC_PATTERN = "EEE, dd MMM yyyy HH:mm:ss Z";;
		return new SimpleDateFormat(RFC_PATTERN, Locale.ENGLISH);
	}

}
