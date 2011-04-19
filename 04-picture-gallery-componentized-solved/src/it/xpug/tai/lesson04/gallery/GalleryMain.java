package it.xpug.tai.lesson04.gallery;

import it.xpug.tai.lesson04.gallery.jetty.ReusableJettyApp;

import java.util.Arrays;
import java.util.List;

public class GalleryMain {
	public static void main(String[] args) {
		PicturesDirectory imagesDirectory = new PicturesDirectory("../pictures-at-an-exhibition");
		
		GalleryController galleryController =
			new GalleryController("Titolo della pagina", imagesDirectory);
	
		PictureController pictureController = 
			new PictureController(imagesDirectory);
		
		List<TaiController> controllers = Arrays.asList(
				galleryController,
				pictureController);

		ReusableJettyApp galleryApp = new ReusableJettyApp(controllers);
		galleryApp.start(8080);
	}
}
