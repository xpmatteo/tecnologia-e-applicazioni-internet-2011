package it.xpug.tai.lesson07.chat;

import it.xpug.tai.lesson07.chat.jetty.ReusableJettyApp;
import it.xpug.tai.lesson07.chat.pages.HomePageController;
import it.xpug.tai.lesson07.chat.pages.TaiController;

import java.util.Arrays;
import java.util.List;

public class ChatMain {
	public static void main(String[] args) {
		TaiController home = new HomePageController();
		TaiController chat = new ChatController();
		List<TaiController> controllers = Arrays.asList(home, chat);
		ReusableJettyApp app = new ReusableJettyApp(controllers);
		app.start(8080);
	}
}
