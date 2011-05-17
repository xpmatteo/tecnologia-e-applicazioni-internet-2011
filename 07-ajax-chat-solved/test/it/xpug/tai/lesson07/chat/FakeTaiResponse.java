package it.xpug.tai.lesson07.chat;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

import it.xpug.tai.lesson07.chat.jetty.TaiResponse;


public class FakeTaiResponse implements TaiResponse {

	public int statusCode;
	public String contentType;
	public File file;
	public String text;

	@Override
	public void setStatus(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public void copyThisFileToOutput(File theFile) throws IOException {
		this.file = theFile;
	}

	@Override
	public void copyThisTextToOutput(String text) throws IOException {
		this.text = text;
	}


}
