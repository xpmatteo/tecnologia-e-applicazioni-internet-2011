package it.xpug.tai.lesson07.chat;

import name.vaccari.matteo.xml.XmlFragment;

import org.junit.Test;



public class HomePageViewTest {
	HomePageView view = new HomePageView();

	@Test
	public void hasFormWithMessage() throws Exception {
		findNodeInHtml("//form", view.toHtml());
		findNodeInHtml("//form//input[@type='text'][@name='message']", view.toHtml());
	}

	@Test
	public void hasDivWithLog() throws Exception {
		findNodeInHtml("//div[@id='log']", view.toHtml());
	}
	
	private void findNodeInHtml(String xpath, String html) {
		XmlFragment xml = new XmlFragment(html);
		xml.getNode(xpath);
	}
}
