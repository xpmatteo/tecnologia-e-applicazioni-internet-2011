package it.xpug.tai.paste;

public class DoNothingDecorator implements LineDecorator {

	@Override
	public String decorate(String line) {
		return line;
	}

}
