package it.xpug.tai.lesson07.chat.pages;

import it.xpug.tai.lesson07.chat.TaiResponse;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class HomePageController implements TaiController {

	public void handle(String target, TaiResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		ChatView view = new ChatView();
		response.getWriter().write(view.toHtml());
	}

	public boolean wantsToHandle(String target) {
		return "/".equals(target);
	}

}
