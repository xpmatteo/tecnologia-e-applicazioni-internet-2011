package it.xpug.tai.lesson02.gallery;


import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;


public class GalleryApp {
	
	private Server server;
	private Handler handler;

	public GalleryApp(Handler handler) {
		this.handler = handler;
	}
	
	public void start(int port) {
		server = new Server(port);
		try {
			server.addHandler(handler);
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
		String title = "Titolo!";
		String pictureUrl = "/mypicture";
		String caption = "A caption";
		
		Handler handler = new GalleryHandler(new PicturePage(title , pictureUrl , caption));
		GalleryApp galleryApp = new GalleryApp(handler);
		
		galleryApp.start(8080);
	}
}
