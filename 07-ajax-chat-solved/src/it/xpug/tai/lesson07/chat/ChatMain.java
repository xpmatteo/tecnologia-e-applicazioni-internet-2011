package it.xpug.tai.lesson07.chat;

import it.xpug.tai.lesson07.chat.jetty.ReusableJettyApp;
import it.xpug.tai.lesson07.chat.jetty.TaiController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ChatMain {
	public static void main(String[] args) {
		List<String> messageLog = Collections.synchronizedList(new ArrayList<String>());
		
		TaiController home = new HomePageController();
		TaiController chat = new ChatController(messageLog);
		List<TaiController> controllers = Arrays.asList(home, chat);
		
		ReusableJettyApp app = new ReusableJettyApp(controllers);
		
		app.start(8080);
	}
}
