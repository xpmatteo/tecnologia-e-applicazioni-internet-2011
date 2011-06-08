package it.xpug.tai.lesson10.chat;
import it.xpug.tai.lesson10.chat.jetty.TaiController;
import it.xpug.tai.lesson10.chat.jetty.TaiRequest;
import it.xpug.tai.lesson10.chat.jetty.TaiResponse;

import java.io.IOException;
import java.util.List;


public class RoomMessagesResource implements TaiController {

	public RoomMessagesResource(List<Message> messages) {
	}

	public void setPageSize(int pAGE_SIZE) {
		throw new RuntimeException("Not yet implemented!");
	}

	@Override
	public boolean wantsToHandle(String target) {
		throw new RuntimeException("Not yet implemented!");
	}

	@Override
	public void handle(TaiRequest request, TaiResponse taiResponse) throws IOException {
		throw new RuntimeException("Not yet implemented!");
	}

}
