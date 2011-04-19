package it.xpug.tai.lesson04.gallery;

import it.xpug.tai.lesson04.gallery.PictureController;
import it.xpug.tai.lesson04.gallery.PictureUrl;

import java.io.File;

import org.junit.Test;

import static org.junit.Assert.*;


public class PictureControllerTest {

	private FakeTaiResponse response = new FakeTaiResponse();
	private String url = new PictureUrl().toUrl("foo.jpg");
	private File imageDirectory = new File("");
	private PictureController controller = new PictureController(imageDirectory );

	@Test
	public void handlesPictureUrls() throws Exception {
		assertFalse("non gestisce anythin", controller.wantsToHandle("/anything"));
		assertTrue("gestisce picture urls", 
				controller.wantsToHandle(url));
	}
	
	@Test
	public void returnsHttpSuccessCode() throws Exception {
		controller.handle(url, response);
		assertEquals(200, response.statusSetByController);
	}
	
	@Test
	public void setsContentTypeForJpeg() throws Exception {
		controller.handle(url, response);
		assertEquals("image/jpeg", response.contentTypeSetByController);
	}

	@Test
	public void savesBytesForImage() throws Exception {
		controller.handle(url, response);
		assertEquals(new File(imageDirectory, "foo.jpg"), response.fileCopiedToOutputByController);
	}

}
