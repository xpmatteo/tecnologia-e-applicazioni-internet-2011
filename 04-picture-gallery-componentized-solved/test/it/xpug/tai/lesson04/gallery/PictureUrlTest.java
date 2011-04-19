package it.xpug.tai.lesson04.gallery;

import org.junit.Test;

import static org.junit.Assert.*;


public class PictureUrlTest {
	PictureUrl imageUrl = new PictureUrl();

	@Test
	public void recognizesAnImageUrl() throws Exception {
		assertTrue("riconosciamo", imageUrl.isPictureUrl("/gallery-image/pippo"));
		assertFalse("non riconosciamo una url straniera", 
				imageUrl.isPictureUrl("/qualsiasialtra"));
	}
	
	@Test
	public void canReturnPathnameFromPathInfo() throws Exception {
		assertEquals("pippo.jpg", imageUrl.extractPictureName("/gallery-image/pippo.jpg"));
	}
	
	@Test
	public void producesUrlFromPicture() throws Exception {
		assertEquals("/gallery-image/pluto.jpg", imageUrl.toUrl(new Picture("/foo/bar/pluto.jpg")));
	}

}
