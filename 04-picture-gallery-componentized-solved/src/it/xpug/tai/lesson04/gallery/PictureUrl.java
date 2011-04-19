package it.xpug.tai.lesson04.gallery;

import java.io.File;


public class PictureUrl {

	private static final String URL_PREFIX = "/gallery-image";

	public boolean isPictureUrl(String pathInfo) {
		return pathInfo.startsWith(URL_PREFIX);
	}

	public String extractPictureName(String pathInfo) {
		int lastSlashIndex = pathInfo.lastIndexOf('/');
		return pathInfo.substring(lastSlashIndex +1);
	}

	public String toUrl(Picture picture) {
		return URL_PREFIX + "/" + picture.getFile().getName();
	}

}
