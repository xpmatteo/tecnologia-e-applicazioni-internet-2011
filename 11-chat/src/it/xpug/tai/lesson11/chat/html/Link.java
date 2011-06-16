package it.xpug.tai.lesson11.chat.html;

public class Link extends Element {

	public Link(String url, String label) {
		super("a");
		with("href", url);
		text(label);
	}

}
