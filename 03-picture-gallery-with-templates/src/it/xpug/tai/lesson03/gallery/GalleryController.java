package it.xpug.tai.lesson03.gallery;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class GalleryController {

	private final String title;
	private final File picturesDirectory;
	private PictureUrl pictureUrl = new PictureUrl();

	public GalleryController(String title, File picturesDirectory) {
		this.title = title;
		this.picturesDirectory = picturesDirectory;
	}
	
	public void handle(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		response.getWriter().write(toHtml());
	}

	private String toHtml() {
		String result = "" +
				"<html>" +
				"<head><title>" + title + "</title></head>" +
				"<body>" +
				"<div id='picture'>" +
				"  <h3>" + title + "</h3>" +
				picturesList() +
				"</div>" +
				"</body>" +
				"</html>";
		return result ;
	}

	private String picturesList() {
		String result = "";
		String[] files = picturesDirectory.list();
		for (int i = 0; i < files.length; i++) {
			if (files[i].endsWith(".jpg")) {
				result += pictureItem(files[i]);
			}
		}
		return result;
	}

	private String pictureItem(String fileName) {
		return String.format("\n<img src='%s'/>", pictureUrl.toUrl(fileName));
	}

}
