package it.xpug.tai.lesson11.chat.jetty;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.Request;
import org.mortbay.jetty.handler.AbstractHandler;

public abstract class ReusableJettyHandler extends AbstractHandler {
	protected abstract List<TaiController> getControllers(); 
	
	@Override
	public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {		
		for (TaiController controller : getControllers()) {
			if (controller.wantsToHandle(target)) {
				controller.handle(new TaiRequestFromServletRequest(request), new TaiResponseFromServletResponse(response));
				((Request)request).setHandled(true);
				break;
			}
		}
	}
}