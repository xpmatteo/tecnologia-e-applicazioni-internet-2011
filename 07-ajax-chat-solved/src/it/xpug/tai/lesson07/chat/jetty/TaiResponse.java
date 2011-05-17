package it.xpug.tai.lesson07.chat.jetty;

import java.io.File;
import java.io.IOException;

public interface TaiResponse {

	void setStatus(int statusCode);

	void setContentType(String contentType);

	void copyThisFileToOutput(File theFile) throws IOException;

	void copyThisTextToOutput(String text) throws IOException;
}
