package it.xpug.tai.lesson07.chat.jetty;


import it.xpug.tai.lesson07.chat.pages.TaiController;

import java.util.List;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.ResourceHandler;


public class ReusableJettyApp {
	
	private Server server;
	private final List<TaiController> controllers;

	public ReusableJettyApp(List<TaiController> controllers) {
		this.controllers = controllers;
	}

	public void start(int port) {
		server = new Server(port);
		try {
			HandlerList handlers = new HandlerList();
			ResourceHandler resource_handler = new ResourceHandler();
			resource_handler.setResourceBase("public");
			handlers.setHandlers(
					new Handler[]{
							new ReusableJettyHandler(controllers),
							resource_handler, 
							new DefaultHandler()});

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
