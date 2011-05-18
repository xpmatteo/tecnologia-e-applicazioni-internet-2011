package it.xpug.tai.lesson07.chat;

import it.xpug.tai.lesson07.chat.jetty.TaiController;
import it.xpug.tai.lesson07.chat.jetty.TaiRequest;
import it.xpug.tai.lesson07.chat.jetty.TaiResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ChatController implements TaiController {

	private List<String> messages;
	private Date lastModified;
	
	public ChatController(List<String> messages) {
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

	private boolean modified(TaiRequest request) {
		String clientTimestamp = request.getHeader("If-Modified-Since");
		System.out.println(clientTimestamp + " " + lastModified);
		if (null == clientTimestamp) {
			return true;
		}
		return lastModified.after(parseTimestamp(clientTimestamp));
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

	private Date parseTimestamp(String modified) {		
		SimpleDateFormat format = rfcDateFormat();	
		try {
			return format.parse(modified);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private SimpleDateFormat rfcDateFormat() {
		String RFC_PATTERN = "EEE, dd MMM yyyy HH:mm:ss Z";;
		return new SimpleDateFormat(RFC_PATTERN, Locale.ENGLISH);
	}

//	private void randomDelay() {
//		if (Math.random() > 0.5) {
//			try {
//				Thread.sleep(30000);
//			} catch (InterruptedException e) {
//				throw new RuntimeException(e);
//			}
//		}
//	}

}
