package it.xpug.tai.lesson03.gallery;

import name.vaccari.matteo.xml.XmlFragment;

import org.junit.Test;


public class GalleryViewTest {

	@Test
	public void findsImgElements() throws Exception {
		
		GalleryView view = new GalleryView("pippo", new String[] {"pippo.jpg", "pluto.jpg"});

		findNodeInHtml("//img[@src='/image/pippo.jpg']", view.toHtml());
		findNodeInHtml("//img[@src='/image/pluto.jpg']", view.toHtml());
	}

	private void findNodeInHtml(String xpath, String html) {
		XmlFragment xml = new XmlFragment(html);
		xml.getNode(xpath);
	}
}
