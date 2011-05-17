package it.xpug.tai.lesson07.chat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static name.vaccari.matteo.html.Assert.assertDomEquals;

import static org.junit.Assert.*;


public class ChatControllerTest {
	List<String> messageLog = new ArrayList<String>();
	FakeTaiResponse response = new FakeTaiResponse();
	FakeTaiRequest request = new FakeTaiRequest();
	ChatController controller = new ChatController(messageLog);

	@Test
	public void handlesChatUrl() throws Exception {
		assertTrue("/chat", controller.wantsToHandle("/chat"));
		assertFalse("/anything", controller.wantsToHandle("/anything"));		
	}
	
	@Test
	public void returnsHtml() throws Exception {
		controller.handle(request, response);
		
		assertEquals(200, response.statusCode);
		assertEquals("text/html", response.contentType);
	}
	
	@Test
	public void buildsHtmlFromMessages() throws Exception {
		messageLog.add("primo");
		messageLog.add("secondo");
		
		controller.handle(request, response);

		assertDomEquals("<ul><li>primo</li><li>secondo</li></ul>", response.text);
	}
	
	@Test
	public void addsMessageToLog() throws Exception {
		request.setupParameter("message", "a message");
		request.setupMethod("POST");

		controller.handle(request, response);
		
		assertEquals(1, messageLog.size());
		assertEquals("a message", messageLog.get(0));
		assertDomEquals("<ul><li>a message</li></ul>", response.text);
	}
}
