package it.xpug.tai.lesson04.gallery;

import java.io.File;
import java.io.IOException;

public class PictureController implements TaiController {
	
	private final File picturesDirectory;
	private final PictureUrl pictureUrl = new PictureUrl();

	public PictureController(File picturesDirectory) {
		this.picturesDirectory = picturesDirectory;
	}
	
	public void handle(String url, TaiResponse response) throws IOException {
		response.setStatus(200);
		response.setContentType("image/jpeg");
		String pictureName = pictureUrl.extractPictureName(url);
		File file = new File(picturesDirectory, pictureName);
		response.copyThisFileToOutput(file);
	}

	
	public boolean wantsToHandle(String target) {
		return pictureUrl.isPictureUrl(target);
	}

}
