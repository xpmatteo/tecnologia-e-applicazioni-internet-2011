package it.xpug.tai.paste;

public class LineNumbersDecorator implements LineDecorator {

	int lineNumber = 0;
	
	@Override
	public String decorate(String line) {
		lineNumber++;
		return "" + lineNumber + " " + line;
	}

}
