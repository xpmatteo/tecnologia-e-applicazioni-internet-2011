package it.xpug.tai.lesson04.gallery;

import java.io.File;

import org.junit.Test;

import static org.junit.Assert.*;


public class GalleryControllerTest {

	private FakeTaiResponse response = new FakeTaiResponse();
	private File picturesDirectory = new File("fixture");
	private GalleryController controller = new GalleryController("a title", picturesDirectory);

	@Test
	public void returnsSuccessCode() throws Exception {
		controller.handle("/", response);
		assertEquals(200, response.statusSetByController);	
	}
	
	@Test
	public void setsContentTypeHtml() throws Exception {
		controller.handle("/", response);
		assertEquals("text/html", response.contentTypeSetByController);	
	}
	
	@Test
	public void handlesOnlyRootUrl() throws Exception {
		assertTrue("handles /", controller.wantsToHandle("/"));
		assertFalse("does not handle anything else", controller.wantsToHandle("/anything"));
	}
	
	@Test
	public void sendsOutputFromGalleryView() throws Exception {
		GalleryView view = new GalleryView("a title", new String[] {"foo.jpg"});
		controller.handle("/", response);
		assertEquals(view.toHtml(), response.htmlWrittenByController);
	}
}
