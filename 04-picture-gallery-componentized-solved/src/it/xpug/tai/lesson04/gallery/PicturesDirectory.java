package it.xpug.tai.lesson04.gallery;

import java.io.File;

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

}
