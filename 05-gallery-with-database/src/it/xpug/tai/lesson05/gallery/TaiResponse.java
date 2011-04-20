package it.xpug.tai.lesson05.gallery;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

public interface TaiResponse {

	void setStatus(int statusCode);

	void setContentType(String contentType);

	void copyThisFileToOutput(File theFile) throws IOException;

	Writer getWriter() throws IOException;
}
