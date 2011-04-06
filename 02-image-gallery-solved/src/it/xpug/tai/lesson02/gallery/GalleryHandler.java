package it.xpug.tai.lesson02.gallery;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.AbstractHandler;

public class GalleryHandler extends AbstractHandler {
	private File imagesDirectory = new File("../pictures-at-an-exhibition");
	private PictureUrl pictureUrl = new PictureUrl();

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {
		if (pictureUrl.isPictureUrl(request.getPathInfo())) {
			PictureController pictureController = new PictureController(imagesDirectory);
			pictureController.handle(target, response);
		} else {
			GalleryController controller =
				new GalleryController("Titolo della pagina", imagesDirectory);
			controller.handle(response);
		}
		((Request)request).setHandled(true);			
	}

}