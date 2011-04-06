package it.xpug.tai.lesson02.gallery;

public class PicturePage {
	
	private final String title;
	private final String pictureUrl;
	private final String caption;

	public PicturePage(String title, String pictureUrl, String caption) {
		this.title = title;
		this.pictureUrl = pictureUrl;
		this.caption = caption;
	}

	public String toHtml() {
		String result = "" +
				"<html>" +
				"<head><title>" + title + "</title></head>" +
				"<body>" +
				"<div id='picture'>" +
				"  <h3>" + title + "</h3>" +
				"  <img src='" + pictureUrl + "' alt='some picture' />" +
				"  <p>" + caption + "</p>" +
				"</div>" +
				"</body>" +
				"</html>";
		return result ;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

}
