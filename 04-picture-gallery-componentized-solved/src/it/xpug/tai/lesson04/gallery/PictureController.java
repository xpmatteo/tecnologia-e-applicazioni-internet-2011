package it.xpug.tai.lesson04.gallery;

import java.io.File;
import java.io.IOException;

public class PictureController implements TaiController {
	
	private final PicturesDirectory picturesDirectory;
	private final PictureUrl pictureUrl = new PictureUrl();

	public PictureController(PicturesDirectory imagesDirectory) {
		this.picturesDirectory = imagesDirectory;
	}
	
	public void handle(String url, TaiResponse response) throws IOException {
		response.setStatus(200);
		response.setContentType("image/jpeg");
		String pictureName = pictureUrl.extractPictureName(url);
		File file = picturesDirectory.getFile(pictureName);
		response.copyThisFileToOutput(file);
	}

	
	public boolean wantsToHandle(String target) {
		return pictureUrl.isPictureUrl(target);
	}

}
