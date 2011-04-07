package it.xpug.tai.lesson03.gallery;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class PictureController {
	
	private final File picturesDirectory;
	private final PictureUrl pictureUrl = new PictureUrl();

	public PictureController(File picturesDirectory) {
		this.picturesDirectory = picturesDirectory;
	}
	
	public void handle(String pathInfo, HttpServletResponse response) throws IOException {
		OutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("image/jpeg");
			
			outputStream = response.getOutputStream();
			String pictureName = pictureUrl.extractPictureName(pathInfo);
			File file = new File(picturesDirectory, pictureName);
			inputStream = new FileInputStream(file);
			copy(inputStream, outputStream);				
		} finally {
			close(outputStream);
			close(inputStream);
		}
	}
	
	private void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
		int b;
		while (-1 != (b = inputStream.read())) {
			outputStream.write(b);
		}
	}

	private void close(Closeable stream) {
		if (null != stream) {
			try {
				stream.close();
			} catch (IOException ignored) {}
		}
	}
}
