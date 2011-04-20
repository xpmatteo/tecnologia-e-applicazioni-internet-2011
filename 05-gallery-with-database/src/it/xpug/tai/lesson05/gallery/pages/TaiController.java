package it.xpug.tai.lesson05.gallery.pages;

import it.xpug.tai.lesson05.gallery.TaiResponse;

import java.io.IOException;

public interface TaiController {

	boolean wantsToHandle(String target);

	void handle(String target, TaiResponse taiResponse) throws IOException;

}
