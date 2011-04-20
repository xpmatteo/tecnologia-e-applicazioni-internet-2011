package it.xpug.tai.lesson05.gallery;

import it.xpug.tai.lesson05.gallery.pages.GalleryView;
import name.vaccari.matteo.xml.XmlFragment;

import org.junit.Test;


public class GalleryViewTest {

	@Test
	public void findsImgElements() throws Exception {
		
		GalleryView view = new GalleryView("pippo", new String[] {"pippo.jpg", "pluto.jpg"});

		findNodeInHtml("//img[@src='/gallery-image/pippo.jpg']", view.toHtml());
		findNodeInHtml("//img[@src='/gallery-image/pluto.jpg']", view.toHtml());
	}

	private void findNodeInHtml(String xpath, String html) {
		XmlFragment xml = new XmlFragment(html);
		xml.getNode(xpath);
	}
}
