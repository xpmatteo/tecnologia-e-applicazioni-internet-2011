package it.xpug.tai.lesson04.gallery;

import java.io.IOException;

public interface TaiController {

	boolean wantsToHandle(String target);

	void handle(String target, TaiResponse taiResponse) throws IOException;

}
