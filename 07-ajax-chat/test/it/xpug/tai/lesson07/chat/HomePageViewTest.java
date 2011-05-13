package it.xpug.tai.lesson07.chat;

import name.vaccari.matteo.xml.XmlFragment;

import org.junit.Test;


public class HomePageViewTest {

	@Test
	public void findsImgElements() throws Exception {
		
		HomePageView view = new HomePageView();

		findNodeInHtml("//input[@type='text']", view.toHtml());
	}

	private void findNodeInHtml(String xpath, String html) {
		XmlFragment xml = new XmlFragment(html);
		xml.getNode(xpath);
	}
}
