package it.xpug.tai.lesson09.chat.jetty;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.AbstractHandler;

public class ReusableJettyHandler extends AbstractHandler {
	private final List<TaiController> controllers;

	public ReusableJettyHandler(List<TaiController> controllers) {
		this.controllers = controllers;
	}

	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {		
		for (TaiController controller : controllers) {
			if (controller.wantsToHandle(target)) {
				controller.handle(new TaiRequestFromServletRequest(request), new TaiResponseFromServletResponse(response));
				((Request)request).setHandled(true);
				break;
			}
		}
	}
}