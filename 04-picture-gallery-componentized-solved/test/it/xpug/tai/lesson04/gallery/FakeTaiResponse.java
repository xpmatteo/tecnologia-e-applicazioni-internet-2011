package it.xpug.tai.lesson04.gallery;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

final class FakeTaiResponse implements TaiResponse {
	public int statusSetByController;
	public String contentTypeSetByController;
	public File fileCopiedToOutputByController;
	public String htmlWrittenByController;
	

	@Override
	public void setStatus(int statusCode) {
		statusSetByController = statusCode;
	}

	@Override
	public void setContentType(String contentType) {
		contentTypeSetByController = contentType;
	}

	@Override
	public void copyThisFileToOutput(File theFile) {
		fileCopiedToOutputByController = theFile;
	}

	@Override
	public Writer getWriter() throws IOException {
		return new StringWriter();
	}

	@Override
	public void sendHtml(String html) throws IOException {
		this.htmlWrittenByController = html;
	}
}