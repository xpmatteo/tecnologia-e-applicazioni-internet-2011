package it.xpug.tai.lesson02.gallery;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Picture {

	private final String pathName;

	public Picture(String pathName) {
		this.pathName = pathName;
	}

	public void copyTo(OutputStream outputStream) throws IOException {
		InputStream inputStream = null;
		try {
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
}
