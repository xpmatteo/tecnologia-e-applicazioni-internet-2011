package it.xpug.tai.lesson02.gallery;

import org.junit.Test;

import static org.junit.Assert.*;


public class PictureUrlTest {

	@Test
	public void recognizesAnImageUrl() throws Exception {
		PictureUrl imageUrl = new PictureUrl();
		assertTrue("riconosciamo", imageUrl.isPictureUrl("/image/pippo"));
		assertFalse("non riconosciamo una url straniera", 
				imageUrl.isPictureUrl("/qualsiasialtra"));
	}
	
	@Test
	public void canReturnPathnameFromPathInfo() throws Exception {
		PictureUrl imageUrl = new PictureUrl();
		assertEquals("pippo.jpg", imageUrl.extractPictureName("/image/pippo.jpg"));
	}
	
	@Test
	public void producesUrlFromPictureName() throws Exception {
		PictureUrl imageUrl = new PictureUrl();
		assertEquals("/image/pluto.jpg", imageUrl.toUrl("pluto.jpg"));
	}
}
