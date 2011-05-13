package it.xpug.tai.lesson07.chat;

import java.io.IOException;

import it.xpug.tai.lesson07.chat.pages.TaiController;

public class ChatController implements TaiController {

	@Override
	public boolean wantsToHandle(String target) {
		return "/chat".equals(target);
	}

	@Override
	public void handle(String target, TaiResponse response) throws IOException {
		response.setContentType("text/html");
		String html = "<ul><li>ciao</li><li>miao</li></ul>";
		response.getWriter().write(html);
	}

}
