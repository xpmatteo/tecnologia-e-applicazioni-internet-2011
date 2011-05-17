package it.xpug.tai.lesson07.chat;


import it.xpug.tai.lesson07.chat.jetty.TaiController;
import it.xpug.tai.lesson07.chat.jetty.TaiRequest;
import it.xpug.tai.lesson07.chat.jetty.TaiResponse;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class HomePageController implements TaiController {

	public void handle(TaiRequest request, TaiResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/html");
		HomePageView view = new HomePageView();
		response.copyThisTextToOutput(view.toHtml());
	}

	public boolean wantsToHandle(String target) {
		return "/".equals(target);
	}

}
