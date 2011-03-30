package it.xpug.tai.lesson02.gallery;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.AbstractHandler;

public class GalleryHandler extends AbstractHandler {
	private String title = "Titolo!";
	private String caption = "Una didascalia";
	private String pictureUrl = "/mypicture";
	private String picturePathName = "/Users/matteo/Pictures/quadri/g.de_chirico_la_torre_rossa.jpeg";

	private String getMainPage() {
		String result = "" +
				"<html>" +
				"<head><title>" + title + "</title></head>" +
				"<body>" +
				"<div id='picture'>" +
				"  <h3>" + title + "</h3>" +
				"  <img src='" + pictureUrl + "' alt='some picture' />" +
				"  <p>" + caption + "</p>" +
				"</div>" +
				"</body>" +
				"</html>";
		return result ;
	}

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {
		if (isPictureUrl(request.getPathInfo())) {
			sendPicture(response, picturePathName);
		} else {
			sendMainPage(response);
		}
		((Request)request).setHandled(true);			
	}

	private boolean isPictureUrl(String pathInfo) {
		return pictureUrl.equals(pathInfo);
	}

	private void sendPicture(HttpServletResponse response, String pathName) throws IOException {
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("image/jpeg");
			
			outputStream = response.getOutputStream();
			inputStream = new FileInputStream(pathName);
			copy(inputStream, outputStream);				
		} finally {
			close(outputStream);
			close(inputStream);
		}
	}

	private void close(Closeable stream) {
		if (null != stream) {
			try {
				stream.close();
			} catch (IOException ignored) {}
		}
	}

	private void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
		int b;
		while (-1 != (b = inputStream.read())) {
			outputStream.write(b);
		}
	}

	private void sendMainPage(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		response.getWriter().write(getMainPage());
	}
}