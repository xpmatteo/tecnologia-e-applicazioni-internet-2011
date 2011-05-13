package it.xpug.tai.lesson07.chat.pages;


public class PictureUrl {

	private static final String URL_PREFIX = "/gallery-image";

	public boolean isPictureUrl(String pathInfo) {
		return pathInfo.startsWith(URL_PREFIX);
	}

	public String extractPictureName(String pathInfo) {
		int lastSlashIndex = pathInfo.lastIndexOf('/');
		return pathInfo.substring(lastSlashIndex +1);
	}

	public String toUrl(String pictureName) {
		return URL_PREFIX + "/" + pictureName;
	}

}
