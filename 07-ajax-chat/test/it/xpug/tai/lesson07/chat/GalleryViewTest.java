package it.xpug.tai.lesson07.chat;

import it.xpug.tai.lesson07.chat.pages.ChatView;
import name.vaccari.matteo.xml.XmlFragment;

import org.junit.Test;


public class GalleryViewTest {

	@Test
	public void findsImgElements() throws Exception {
		
		ChatView view = new ChatView();

		findNodeInHtml("//input[@type='text']", view.toHtml());
	}

	private void findNodeInHtml(String xpath, String html) {
		XmlFragment xml = new XmlFragment(html);
		xml.getNode(xpath);
	}
}
