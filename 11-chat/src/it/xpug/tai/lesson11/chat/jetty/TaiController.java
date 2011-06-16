package it.xpug.tai.lesson11.chat.jetty;

import java.io.IOException;

public interface TaiController {

	boolean wantsToHandle(String target);

	void handle(TaiRequest request, TaiResponse response) throws IOException;

}
