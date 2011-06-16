package it.xpug.tai.lesson11.chat;

import it.xpug.tai.lesson11.chat.jetty.ReusableJettyApp;


public class ChatMain {
	public static void main(String[] args) {		
		ReusableJettyApp app = new ReusableJettyApp(new ChatJettyHandler());		
		app.start(8080);
	}
}
