package it.xpug.tai.lesson04.gallery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PicturesDirectory {

	private final String pathName;

	public PicturesDirectory(String pathName) {
		this.pathName = pathName;
	}

	public String[] list() {
		return new File(pathName).list();
	}

	public File getFile(String pictureName) {
		return new File(pathName, pictureName);
	}

	public Picture[] getPictures() {
		List<Picture> result = new ArrayList<Picture>();
		String[] files = new File(pathName).list();
		for (int i = 0; i < files.length; i++) {
			if (files[i].endsWith(".jpg")) {
				result.add(new Picture(files[i]));
			}
		}
		return result.toArray(new Picture[0]);
	}


}
