package it.xpug.tai.lesson04.gallery;

import it.xpug.tai.lesson04.gallery.PictureController;
import it.xpug.tai.lesson04.gallery.PictureUrl;
import it.xpug.tai.lesson04.gallery.TaiResponse;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import org.junit.Test;

import static org.junit.Assert.*;


public class PictureControllerTest {

	protected int statusSetByController;
	protected String contentTypeSetByController;
	protected File fileCopiedToOutputByController;
	
	TaiResponse response = new TaiResponse() {			
		@Override
		public void setStatus(int statusCode) {
			statusSetByController = statusCode;
		}
		
		@Override
		public void setContentType(String contentType) {
			contentTypeSetByController = contentType;
		}

		@Override
		public void copyThisFileToOutput(File theFile) {
			fileCopiedToOutputByController = theFile;
		}

		@Override
		public Writer getWriter() throws IOException {
			throw new RuntimeException("Not yet implemented!");
		}
	};

	String url = new PictureUrl().toUrl("foo.jpg");
	private File imageDirectory = new File("");
	PictureController controller = new PictureController(imageDirectory );

	@Test
	public void handlesPictureUrls() throws Exception {
		assertFalse("non gestisce anythin", controller.wantsToHandle("/anything"));
		assertTrue("gestisce picture urls", 
				controller.wantsToHandle(url));
	}
	
	@Test
	public void returnsHttpSuccessCode() throws Exception {
		controller.handle(url, response);
		assertEquals(200, statusSetByController);
	}
	
	@Test
	public void setsContentTypeForJpeg() throws Exception {
		controller.handle(url, response);
		assertEquals("image/jpeg", contentTypeSetByController);
	}

	@Test
	public void savesBytesForImage() throws Exception {
		controller.handle(url, response);
		assertEquals(new File(imageDirectory, "foo.jpg"), fileCopiedToOutputByController);
	}

}
