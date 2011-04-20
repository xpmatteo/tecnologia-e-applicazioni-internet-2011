package it.xpug.tai.lesson05.gallery.pages;

import it.xpug.tai.lesson05.gallery.TaiResponse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class GalleryController implements TaiController {

	private final String title;
	private final File picturesDirectory;

	public GalleryController(String title, File picturesDirectory) {
		this.title = title;
		this.picturesDirectory = picturesDirectory;
	}
	
	public void handle(String target, TaiResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		GalleryView view = new GalleryView(title, filesList());
		response.getWriter().write(view.toHtml());
	}

	private String[] filesList() {
		List<String> result = new ArrayList<String>();
		String[] files = picturesDirectory.list();
		for (int i = 0; i < files.length; i++) {
			if (files[i].endsWith(".jpg")) {
				result.add(files[i]);
			}
		}
		return result.toArray(new String[0]);
	}

	public boolean wantsToHandle(String target) {
		return "/".equals(target);
	}

}
