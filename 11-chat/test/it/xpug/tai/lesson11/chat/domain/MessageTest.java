package it.xpug.tai.lesson11.chat.domain;

import org.junit.Test;

import static name.vaccari.matteo.html.Assert.assertDomEquals;


public class MessageTest {

	@Test
	public void producesHtml() throws Exception {
		Message message = new Message(123, "some text");
		String expected = "" +
			"<p class='message'>" +
			"  <span class='message-id'>123</span>" +
			"  <span class='message'>some text</span>" +
//			"  <span class='timestamp'>2011-06-08 10:01:00</span>" +
			"</p>";

		assertDomEquals(expected, message.toHtml());
	}
}
