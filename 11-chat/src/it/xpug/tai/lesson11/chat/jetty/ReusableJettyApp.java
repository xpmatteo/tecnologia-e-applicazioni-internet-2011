package it.xpug.tai.lesson11.chat.jetty;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.ResourceHandler;

public class ReusableJettyApp {

	private Server server;
	private final ReusableJettyHandler handler;

	public ReusableJettyApp(ReusableJettyHandler handler) {
		this.handler = handler;
	}

	public void start(int port) {
		server = new Server(port);
		try {
			HandlerList handlers = new HandlerList();
			ResourceHandler resource_handler = new ResourceHandler();
			resource_handler.setResourceBase("public");
			handlers.setHandlers(new Handler[] { handler, resource_handler, new DefaultHandler() });

			server.setHandler(handlers);
			server.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void shutdown() throws InterruptedException {
		try {
			server.stop();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
