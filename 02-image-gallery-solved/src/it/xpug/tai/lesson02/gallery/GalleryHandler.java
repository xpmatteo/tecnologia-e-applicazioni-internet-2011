package it.xpug.tai.lesson02.gallery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.AbstractHandler;

public class GalleryHandler extends AbstractHandler {
	private String picturePathName = "/Users/matteo/Pictures/quadri/g.de_chirico_la_torre_rossa.jpeg";
	private PicturePage picturePage;

	public GalleryHandler(PicturePage page) {
		this.picturePage = page;
	}
	
	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {
		if (isPictureUrl(request.getPathInfo())) {
			sendPicture(response, new Picture(picturePathName));
		} else {
			sendMainPage(response);
		}
		((Request)request).setHandled(true);			
	}

	private boolean isPictureUrl(String pathInfo) {
		return picturePage.getPictureUrl().equals(pathInfo);
	}

	private void sendPicture(HttpServletResponse response, Picture picture) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("image/jpeg");
		picture.copyTo(response.getOutputStream());
	}

	private void sendMainPage(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		response.getWriter().write(picturePage.toHtml());
	}
}