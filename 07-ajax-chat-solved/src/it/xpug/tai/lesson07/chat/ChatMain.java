package it.xpug.tai.lesson07.chat;

import it.xpug.tai.lesson07.chat.jetty.ReusableJettyApp;
import it.xpug.tai.lesson07.chat.jetty.TaiController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;


public class ChatMain {
	public static void main(String[] args) {
		List<String> messageLog = Collections.synchronizedList(new ArrayList<String>());		
		TaiController chat = new ChatController(messageLog);
		ReusableJettyApp app = new ReusableJettyApp(asList(chat));
		
		app.start(8080);
	}
}
