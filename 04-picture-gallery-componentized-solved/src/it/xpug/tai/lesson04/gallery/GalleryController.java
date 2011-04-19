package it.xpug.tai.lesson04.gallery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class GalleryController implements TaiController {

	private final String title;
	private final PicturesDirectory picturesDirectory;

	public GalleryController(String title, PicturesDirectory picturesDirectory) {
		this.title = title;
		this.picturesDirectory = picturesDirectory;
	}
	
	public void handle(String target, TaiResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		GalleryView view = new GalleryView(title, picturesDirectory.getPictures());
		response.sendHtml(view.toHtml());
	}

	public boolean wantsToHandle(String target) {
		return "/".equals(target);
	}

}
