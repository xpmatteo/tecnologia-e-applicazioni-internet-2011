package it.xpug.tai.lesson10.chat;

import it.xpug.tai.lesson10.chat.jetty.ReusableJettyApp;
import it.xpug.tai.lesson10.chat.jetty.TaiController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;


public class ChatMain {
	public static void main(String[] args) {
		List<String> messageLog = Collections.synchronizedList(new ArrayList<String>());
		List<Room> rooms = new ArrayList<Room>();
		rooms.add(new Room(1313, "non entrate in questa stanza"));
		rooms.add(new Room(123, "questa stanza invece va bene"));
		
		RoomsResource roomsPage = new RoomsResource(rooms);
		TaiController chat = new ChatPage(messageLog);
		
		ReusableJettyApp app = new ReusableJettyApp(asList(roomsPage, chat));
		
		app.start(8080);
	}
}
