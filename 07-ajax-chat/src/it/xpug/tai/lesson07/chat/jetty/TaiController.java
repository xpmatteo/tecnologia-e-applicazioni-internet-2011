package it.xpug.tai.lesson07.chat.jetty;


import java.io.IOException;

public interface TaiController {

	boolean wantsToHandle(String target);

	void handle(String target, TaiResponse taiResponse) throws IOException;

}
