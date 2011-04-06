package it.xpug.tai.lesson03.gallery;

import it.xpug.tai.lesson03.gallery.GalleryView;

import name.vaccari.matteo.xml.XmlFragment;

import org.junit.Test;

import static org.junit.Assert.*;


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
