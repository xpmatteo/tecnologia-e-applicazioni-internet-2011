package it.xpug.tai.lesson03.gallery;

public class GalleryView {
	
	private final String title;
	private final String[] fileNames;
	private PictureUrl pictureUrl = new PictureUrl();

	public GalleryView(String title, String[] fileNames) {
		this.title = title;
		this.fileNames = fileNames;
	}

	public String toHtml() {
		String result = "" +
				"<html>" +
				"<head><title>" + title + "</title></head>" +
				"<body>" +
				"<div id='picture'>" +
				"  <h3>" + title + "</h3>" +
				picturesList() +
				"</div>" +
				"</body>" +
				"</html>";
		return result ;
	}

	private String picturesList() {
		String result = "";
		String[] files = fileNames;
		for (int i = 0; i < files.length; i++) {
			if (files[i].endsWith(".jpg")) {
				result += pictureItem(files[i]);
			}
		}
		return result;
	}
	
	private String pictureItem(String fileName) {
		return String.format("\n<img src='%s'/>", pictureUrl .toUrl(fileName));
	}

}
