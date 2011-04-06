package it.xpug.tai.lesson02.gallery;


import org.mortbay.jetty.Server;


public class GalleryApp {
	
	private Server server;

	public void start(int port) {
		server = new Server(port);
		try {
			server.addHandler(new GalleryHandler());
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

	public static void main(String[] args) {
		GalleryApp galleryApp = new GalleryApp();
		galleryApp.start(8080);
	}
}
