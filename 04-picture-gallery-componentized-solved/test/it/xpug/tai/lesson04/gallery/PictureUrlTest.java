package it.xpug.tai.lesson04.gallery;

import org.junit.Test;

import static org.junit.Assert.*;


public class PictureUrlTest {

	@Test
	public void recognizesAnImageUrl() throws Exception {
		PictureUrl imageUrl = new PictureUrl();
		assertTrue("riconosciamo", imageUrl.isPictureUrl("/gallery-image/pippo"));
		assertFalse("non riconosciamo una url straniera", 
				imageUrl.isPictureUrl("/qualsiasialtra"));
	}
	
	@Test
	public void canReturnPathnameFromPathInfo() throws Exception {
		PictureUrl imageUrl = new PictureUrl();
		assertEquals("pippo.jpg", imageUrl.extractPictureName("/gallery-image/pippo.jpg"));
	}
	
	@Test
	public void producesUrlFromPictureName() throws Exception {
		PictureUrl imageUrl = new PictureUrl();
		assertEquals("/gallery-image/pluto.jpg", imageUrl.toUrl("pluto.jpg"));
	}
}
