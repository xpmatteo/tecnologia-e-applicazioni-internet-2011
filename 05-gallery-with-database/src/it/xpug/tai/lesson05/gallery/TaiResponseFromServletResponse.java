package it.xpug.tai.lesson05.gallery;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

public class TaiResponseFromServletResponse implements TaiResponse {
	
	private final HttpServletResponse response;

	public TaiResponseFromServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Override
	public void setStatus(int statusCode) {
		response.setStatus(statusCode);
	}

	@Override
	public void setContentType(String contentType) {
		response.setContentType(contentType);
	}

	@Override
	public void copyThisFileToOutput(File theFile) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(theFile);
			out = response.getOutputStream();
			copy(in, out);
		} finally {
			close(in);
			close(out);
		}
	}

	@Override
	public Writer getWriter() throws IOException {
		return response.getWriter();
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
