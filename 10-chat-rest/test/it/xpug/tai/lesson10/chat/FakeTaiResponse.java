package it.xpug.tai.lesson10.chat;
import it.xpug.tai.lesson10.chat.jetty.TaiResponse;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FakeTaiResponse implements TaiResponse {

	public int statusCode;
	public String contentType;
	public File file;
	public String text;
	private Map<String, String> headers = new HashMap<String, String>();

	public FakeTaiResponse() {
	}

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

	@Override
	public void setHeader(String name, String value) {
		this.headers.put(name.toLowerCase(), value);
	}

	public String getHeader(String name) {
		return this.headers.get(name.toLowerCase());
	}


}
