package it.xpug.tai.lesson04.gallery;

import java.io.File;

public class Picture {

	private File file;

	public Picture(String fileName) {
		this.file = new File(fileName);
	}

	public File getFile() {
		return file;
	}

}
